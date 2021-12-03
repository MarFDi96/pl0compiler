package compilador;

import java.io.IOException;

public class AnalizadorSintactico {

    //TODO:
    /*
    ¿Address? (writeln y write) === ADDRESS - OFFSET + TOPEMEMORIA + 20 (memoria[193] + memoria[194]*256 + memoria[195]*256*256 + memoria[196]*256*256*256
    Preguntar por el shift (>> 8??)     listo
    Preguntar por final del archivo (¿FIXUP MOV EDI 00 00 00 00?) listo
    ¿Limpiar los push y pop?
     */
    private final AnalizadorLexico aLex;
    private final AnalizadorSemantico aSem;
    private final GeneradorDeCodigo genCod;
    private final IndicadorDeErrores indicadorDeErrores;
    private int contadorVariables;

    public AnalizadorSintactico(AnalizadorLexico aLex, AnalizadorSemantico aSem, GeneradorDeCodigo genCod, IndicadorDeErrores indicadorDeErrores) {
        this.aLex = aLex;
        this.aSem = aSem;
        this.genCod = genCod;
        this.indicadorDeErrores = indicadorDeErrores;
    }

    public void analizar() throws IOException {
        aLex.escanear();
        programa();
        System.out.println("Analisis sintactico exitoso");
        genCod.volcar();
        System.out.println("Compilacion exitosa");
    }


    private void programa() throws IOException {
        genCod.cargarBytes(0xBF, 0x00, 0x00, 0x00, 0x00);
        int topeMemoriaFixUp = genCod.getTopeMemoria() - 4;
        bloque(0);
        // LINUX
        genCod.cargarByte(0xE9);
        int puntoDePartida = genCod.getTopeMemoria() + 4;
        genCod.cargarEntero(768 - puntoDePartida);
        if (Terminal.PUNTO.equals(aLex.getS())) {
            aLex.escanear();
        } else {
            indicadorDeErrores.mostrar(Errores.PUNTO_ESPERADO, aLex.getCad() + "\n Se esperaba un .)");
        }
        int address = genCod.obtenerEntero(193);
        int offset = genCod.obtenerEntero(197);
        genCod.cargarEntero(address - offset + genCod.getTopeMemoria(), topeMemoriaFixUp);
        for (int i = 0; i < contadorVariables; i++) {
            cargarBytes(0x00, 0x00, 0x00, 0x00);
        }
        
        
        // LINUX
        genCod.cargarEntero(genCod.getTopeMemoria(), 68);
        genCod.cargarEntero(genCod.getTopeMemoria(), 72);
        genCod.cargarEntero(genCod.getTopeMemoria() - 224, 201);
//        genCod.cargarByte(0x03, 201);
//        genCod.cargarByte(0x05, 202);
//        genCod.cargarByte(0x00, 203);
//        genCod.cargarByte(0x00, 204);
    }
    
        private void proposicion(int base, int desplazamiento) throws IOException {
        switch (aLex.getS()) {
            case IDENTIFICADOR:
                int posicionIdentificador = aSem.getIdentificador(aLex.getCad(), base, desplazamiento);
                IdentificadorBean identificador = aSem.getIdentificador(posicionIdentificador);
                aLex.escanear();
                if (Terminal.ASIGNACION.equals(aLex.getS())) {
                    aLex.escanear();
                    expresion(base, desplazamiento);
                    genCod.cargarPopEax();
                    cargarBytes(0x89, 0x87);
                    genCod.cargarEntero(identificador.getValor());
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba :=");
                }
                break;
            case CALL:
                aLex.escanear();
                if (Terminal.IDENTIFICADOR.equals(aLex.getS())) {
                    int posicionProcedure = aSem.getIdentificador(aLex.getCad(), base, desplazamiento);
                    IdentificadorBean procedure = aSem.getIdentificador(posicionProcedure);
                    cargarBytes(0xE8);
                    genCod.cargarEntero(procedure.getValor() - genCod.getTopeMemoria() - 4);
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IDENTIFICADOR");
                }
                break;
            case BEGIN:
                aLex.escanear();
                proposicion(base, desplazamiento);
                while (Terminal.PUNTO_Y_COMA.equals(aLex.getS())) {
                    aLex.escanear();
                    proposicion(base, desplazamiento);
                }
                if (Terminal.END.equals(aLex.getS())) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba END");
                }
                break;
            case IF:
                aLex.escanear();
                condicion(base, desplazamiento);
                int fixupDireccion = genCod.getTopeMemoria();
                if (Terminal.THEN.equals(aLex.getS())) {
                    aLex.escanear();
                    proposicion(base, desplazamiento);                    
                    genCod.cargarEntero((genCod.getTopeMemoria() - fixupDireccion), fixupDireccion - 4);
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba THEN");
                }
                break;
            case WHILE:
                aLex.escanear();
                int direccionPreCondicion = genCod.getTopeMemoria();
                condicion(base, desplazamiento);
                fixupDireccion = genCod.getTopeMemoria();
                if (Terminal.DO.equals(aLex.getS())) {
                    aLex.escanear();
                    proposicion(base, desplazamiento);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(direccionPreCondicion - genCod.getTopeMemoria() - 4 );
                    genCod.cargarEntero((genCod.getTopeMemoria() - fixupDireccion), fixupDireccion - 4);
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba DO");
                }
                break;
            case READLN:
                aLex.escanear();
                if (Terminal.ABRE_PARENTESIS.equals(aLex.getS())) {
                    do{
                        aLex.escanear();
                        if(Terminal.IDENTIFICADOR.equals(aLex.getS())){
                            genCod.cargarByte(0xE8);
                            int puntoDePartida = genCod.getTopeMemoria() + 4;
                            genCod.cargarEntero(784 - puntoDePartida);
                            posicionIdentificador = aSem.getIdentificador(aLex.getCad(), base, desplazamiento);
                            identificador = aSem.getIdentificador(posicionIdentificador);
                            cargarBytes(0x89, 0x87);
                            genCod.cargarEntero(identificador.getValor());
                            aLex.escanear();
                        }else{
                            indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_DECLARADO, "");
                        }
                    }while(Terminal.COMA.equals(aLex.getS()));
                    
                    if(Terminal.CIERRA_PARENTESIS.equals(aLex.getS())){
                        aLex.escanear();
                    }else{
                        indicadorDeErrores.mostrar(Errores.CIERRE_PARENTESIS_ESPERADO, "");
                    }
                } else {
                    indicadorDeErrores.mostrar(Errores.ABRE_PARENTESIS_ESPERADO, "");
                }
                break;
            case WRITELN:
                aLex.escanear();
                if (Terminal.ABRE_PARENTESIS.equals(aLex.getS())) {
                    do{
                        aLex.escanear();
                        if(Terminal.CADENA_LITERAL.equals(aLex.getS())){
                            String cadena = aLex.getCad().replace("'", "");
                            int cadLen = cadena.length();
                            int direccionAbsoluta = genCod.obtenerEntero(193)
                                - genCod.obtenerEntero(197)
                                + genCod.getTopeMemoria() + 20;

                            genCod.cargarByte(0xB9);
                            genCod.cargarEntero(direccionAbsoluta);

                            genCod.cargarByte(0xBA);
                            genCod.cargarEntero(cadena.length());
                            genCod.cargarByte(0xE8);
                            int puntoDePartida = genCod.getTopeMemoria() + 4;
                            genCod.cargarEntero(368 - puntoDePartida);
                            cargarBytes(0xE9, 0x00, 0x00, 0x00, 0x00);
                            fixupDireccion = genCod.getTopeMemoria();
                            for (byte b : cadena.getBytes()) {
                                genCod.cargarByte((int) b);
                            }
                            genCod.cargarEntero(genCod.getTopeMemoria() - fixupDireccion, fixupDireccion - 4);
                            aLex.escanear();
                        }else{
                            expresion(base, desplazamiento);
                            genCod.cargarPopEax();
                            genCod.cargarByte(0xE8);
                            int puntoDePartida = genCod.getTopeMemoria() + 4;
                            genCod.cargarEntero(400 - puntoDePartida);

                            genCod.cargarByte(0xE8);
                            genCod.cargarEntero(384 - (genCod.getTopeMemoria() + 4));
                        }
                    }while(Terminal.COMA.equals(aLex.getS()));
                    
                    if(Terminal.CIERRA_PARENTESIS.equals(aLex.getS())){
                        aLex.escanear();
                    }else{
                        indicadorDeErrores.mostrar(Errores.CIERRE_PARENTESIS_ESPERADO, "");
                    }
                }else{
                     genCod.cargarByte(0xE8);
                     genCod.cargarEntero(384 - (genCod.getTopeMemoria() + 4));
                }


                break;
            case WRITE:
                aLex.escanear();
                if (Terminal.ABRE_PARENTESIS.equals(aLex.getS())) {
                    do{
                        aLex.escanear();
                        if(Terminal.CADENA_LITERAL.equals(aLex.getS())){
                            String cadena = aLex.getCad().replace("'", "");
                            int cadLen = cadena.length();
                            int direccionAbsoluta = genCod.obtenerEntero(193)
                                - genCod.obtenerEntero(197)
                                + genCod.getTopeMemoria() + 20;

                            genCod.cargarByte(0xB9);
                            genCod.cargarEntero(direccionAbsoluta);

                            genCod.cargarByte(0xBA);
                            genCod.cargarEntero(cadena.length());
                            genCod.cargarByte(0xE8);
                            int puntoDePartida = genCod.getTopeMemoria() + 4;
                            genCod.cargarEntero(368 - puntoDePartida);
                            cargarBytes(0xE9, 0x00, 0x00, 0x00, 0x00);
                            fixupDireccion = genCod.getTopeMemoria();
                            for(byte b : cadena.getBytes()){
                                genCod.cargarByte(b);
                            }

                            genCod.cargarEntero(genCod.getTopeMemoria() - fixupDireccion, fixupDireccion - 4);
                            aLex.escanear();
                        }else{
                            expresion(base, desplazamiento);
                            genCod.cargarPopEax();
                            genCod.cargarByte(0xE8);
                            int puntoDePartida = genCod.getTopeMemoria() + 4;
                            genCod.cargarEntero(400 - puntoDePartida);
                        }
                    }while(Terminal.COMA.equals(aLex.getS()));
                    
                    if(Terminal.CIERRA_PARENTESIS.equals(aLex.getS())){
                        aLex.escanear();
                    }else{
                        indicadorDeErrores.mostrar(Errores.CIERRE_PARENTESIS_ESPERADO, "");
                    }
                }else{
                     indicadorDeErrores.mostrar(Errores.ABRE_PARENTESIS_ESPERADO, "");
                }
                break;
            default:
                // proposicion puede no tener nada
                break;
        }
    }

    private void bloque(int base) throws IOException {
        genCod.cargarBytes(0xE9, 0x00, 0x00, 0x00, 0x00);
        int iniBloque = genCod.getTopeMemoria();

        int desplazamiento = 0;
        if (aLex.getS().equals(Terminal.CONST)) {
            do {
                aLex.escanear();
                String nombreIdentificador = aLex.getCad();
                if (Terminal.IDENTIFICADOR.equals(aLex.getS())) {
                    aLex.escanear();
                    if (Terminal.IGUAL.equals(aLex.getS())) {
                        aLex.escanear();
                        if (Terminal.NUMERO.equals(aLex.getS())) {
                            IdentificadorBean identificador = new IdentificadorBean(nombreIdentificador, Terminal.CONST, Integer.valueOf(aLex.getCad()));
                            aSem.insertarEnTabla(identificador, base, desplazamiento);
                            desplazamiento++;
                            aLex.escanear();
                        } else {
                            indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba NUMERO");
                        }
                    } else {
                        indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IGUAL");
                    }
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IDENTIFICADOR");

                }
            } while (Terminal.COMA.equals(aLex.getS()));
            if (Terminal.PUNTO_Y_COMA.equals(aLex.getS())) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba PUNTO Y COMA");
            }
        }
        if (aLex.getS().equals(Terminal.VAR)) {
            aLex.escanear();
            if (Terminal.IDENTIFICADOR.equals(aLex.getS())) {
                IdentificadorBean identificador = new IdentificadorBean(aLex.getCad(), Terminal.VAR, (contadorVariables++) * 4);
                aSem.insertarEnTabla(identificador, base, desplazamiento);
                desplazamiento++;
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IDENTIFICADOR");
            }
            while (Terminal.COMA.equals(aLex.getS())) {
                aLex.escanear();
                if (Terminal.IDENTIFICADOR.equals(aLex.getS())) {
                    IdentificadorBean identificador = new IdentificadorBean(aLex.getCad(), Terminal.VAR, (contadorVariables++) * 4);
                    aSem.insertarEnTabla(identificador, base, desplazamiento);
                    desplazamiento++;
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IDENTIFICADOR");
                }
            }
            if (Terminal.PUNTO_Y_COMA.equals(aLex.getS())) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba PUNTO Y COMA");
            }
        }
        if (aLex.getS().equals(Terminal.PROCEDURE)) {
            while (Terminal.PROCEDURE.equals(aLex.getS())) {
                aLex.escanear();
                if (Terminal.IDENTIFICADOR.equals(aLex.getS())) {
                    IdentificadorBean identificador = new IdentificadorBean(aLex.getCad(), Terminal.PROCEDURE, genCod.getTopeMemoria());
                    aSem.insertarEnTabla(identificador, base, desplazamiento);
                    desplazamiento++;
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IDENTIFICADOR");

                }
                if (Terminal.PUNTO_Y_COMA.equals(aLex.getS())) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba PUNTO Y COMA");

                }
                bloque(base + desplazamiento);
                cargarBytes(0xC3);
                if (Terminal.PUNTO_Y_COMA.equals(aLex.getS())) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba PUNTO Y COMA");
                }
            }
        }

        if (genCod.getTopeMemoria() - iniBloque == 0) {
            genCod.setTopeMemoria(iniBloque - 5);
        } else {
            genCod.cargarEntero(genCod.getTopeMemoria() - iniBloque, iniBloque - 4);
        }
        proposicion(base, desplazamiento);

    }

    private void condicion(int base, int desplazamiento) throws IOException {
        if (Terminal.ODD.equals(aLex.getS())) {
            aLex.escanear();
            expresion(base, desplazamiento);
            genCod.cargarPopEax();
            cargarBytes(0xA8, 0x01, 0x7B, 0x05, 0xE9, 0x00, 0x00, 0x00, 0x00);
        } else {
            expresion(base, desplazamiento);
            int[] bytesACargar = new int[2];
            switch (aLex.getS()) {
                case IGUAL:
                    bytesACargar = new int[]{0x74, 0x05};
                    aLex.escanear();
                    break;
                case DISTINTO:
                    bytesACargar = new int[]{0x75, 0x05};
                    aLex.escanear();
                    break;
                case MENOR:
                    bytesACargar = new int[]{0x7C, 0x05};
                    aLex.escanear();
                    break;
                case MENOR_IGUAL:
                    bytesACargar = new int[]{0x7E, 0x05};
                    aLex.escanear();
                    break;
                case MAYOR:
                    bytesACargar = new int[]{0x7F, 0x05};
                    aLex.escanear();
                    break;
                case MAYOR_IGUAL:
                    bytesACargar = new int[]{0x7D, 0x05};
                    aLex.escanear();
                    break;
                default:
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, " Se recibio " + aLex.getCad() + "\n"
                            + "Se esperaba =,<>,<,<=,>, >=");
                    break;
            }
            expresion(base, desplazamiento);
            genCod.cargarPopEax();
            cargarBytes(0x5B, 0x39, 0xC3);
            cargarBytes(bytesACargar);
            cargarBytes(0xE9, 0x00, 0x00, 0x00, 0x00);
        }

    }

    private void expresion(int base, int desplazamiento) throws IOException {
        Terminal operador = aLex.getS();
        switch (aLex.getS()) {
            case MAS:
                aLex.escanear();
                break;
            case MENOS:
                aLex.escanear();
                break;
            default:
                break;
        }

        termino(base, desplazamiento);
        if (operador.equals(Terminal.MENOS)) {
            genCod.cargarPopEax();
            genCod.cargarBytes(0xF7, 0xD8, 0x50);
        }
        while (Terminal.MAS.equals(aLex.getS()) || Terminal.MENOS.equals(aLex.getS())) {
            operador = aLex.getS();
            aLex.escanear();
            termino(base, desplazamiento);
            if (operador.equals(Terminal.MAS)) {
                genCod.cargarPopEax();
                genCod.cargarBytes(0x5B, 0x01, 0xD8, 0x50);
            } else {
                genCod.cargarPopEax();
                genCod.cargarBytes(0x5B, 0x93, 0x29, 0xD8, 0x50);
            }
        }

    }

    private void termino(int base, int desplazamiento) throws IOException {
        factor(base, desplazamiento);
        while (Terminal.POR.equals(aLex.getS()) || Terminal.DIVIDIDO.equals(aLex.getS())) {
            Terminal operador = aLex.getS();
            aLex.escanear();
            factor(base, desplazamiento);
            if (operador.equals(Terminal.POR)) {
                genCod.cargarPopEax();
                genCod.cargarByte(0x5B);
                genCod.cargarBytes(0xF7, 0xEB);
                genCod.cargarByte(0x50);
            } else {
                genCod.cargarPopEax();
                genCod.cargarBytes(0x5B, 0x93, 0x99, 0xF7, 0xFB, 0x50);
            }
        }
    }

    private void factor(int base, int desplazamiento) throws IOException {
        // regla 4
        switch (aLex.getS()) {
            case IDENTIFICADOR:
                int posicion = aSem.getIdentificador(aLex.getCad(), base, desplazamiento);
                IdentificadorBean identificador = aSem.getIdentificador(posicion);
                if (identificador.getTipo().equals(Terminal.PROCEDURE)) {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, "Se esperaba CONST o VAR");
                } else {
                    if (identificador.getTipo().equals(Terminal.CONST)) {
                        genCod.cargarByte(0xB8);
                        genCod.cargarEntero(identificador.getValor());
                        genCod.cargarByte(0x50); // PUSH EAX
                    } else { // Es una variable
                        genCod.cargarBytes(0x8B, 0x87);
                        genCod.cargarEntero(identificador.getValor());
                        genCod.cargarByte(0x50); // PUSH EAX
                    }
                }
                aLex.escanear();

                break;
            case NUMERO:
                genCod.cargarByte(0xB8);
                genCod.cargarEntero(Integer.valueOf(aLex.getCad()));
                genCod.cargarByte(0x50);
                aLex.escanear();
                //TODO: Cargar bytes igual que en CONST.
                break;
            case ABRE_PARENTESIS:
                aLex.escanear();
                expresion(base, desplazamiento);
                if (aLex.getS().equals(Terminal.CIERRA_PARENTESIS)) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.CIERRE_PARENTESIS_ESPERADO, aLex.getCad());
                }
                break;
            default:
                indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_ESPERADO, aLex.getCad() + "\n Se esperaba IDENTIFICADOR, NUMERO O ABRE_PARENTESIS");
        }
    }

    private void cargarBytes(int... bytes) {
        genCod.cargarBytes(bytes);
    }

}

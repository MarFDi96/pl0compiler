package compilador;

import java.io.IOException;

public class AnalizadorSintactico {

    private final AnalizadorLexico aLex;
    private final AnalizadorSemantico aSem;
    private final GeneradorDeCodigo genCod;
    private final IndicadorDeErrores indicadorDeErrores;

    public AnalizadorSintactico(AnalizadorLexico aLex, AnalizadorSemantico aSem, GeneradorDeCodigo genCod, IndicadorDeErrores indicadorDeErrores) {
        this.aLex = aLex;
        this.aSem = aSem;
        this.genCod = genCod;
        this.indicadorDeErrores = indicadorDeErrores;
    }

    public void analizar() throws IOException {
        aLex.escanear();
        programa();
        genCod.volcar();
        System.out.println("Compilación sin problemas");
    }

    private void programa() throws IOException {
        genCod.cargarByte(0xBF);
        genCod.cargarEntero(0);
        bloque(0);
        if (aLex.getS() == Terminal.PUNTO) {
            aLex.escanear();
            genCod.cargarByte(0xE9);
            int aux = genCod.getTopeMemoria() + 4;
            genCod.cargarEntero(0x588 - aux);
            genCod.cargarEntero(0x701, genCod.getCampo(212) + genCod.getCampo(200) - 0x700 + genCod.getTopeMemoria());
            for (int i = 0; i < aSem.getValorVar() / 4; i++) {
                genCod.cargarEntero(0);
            }
            int tamSeccionText = genCod.getTopeMemoria() - 0x200;
            genCod.cargarEntero(0x1A0, tamSeccionText);

            int fileAlignment = genCod.getCampo(0xDC);
            while (genCod.getTopeMemoria() % fileAlignment != 0) {
                genCod.cargarByte(0);
            }
            tamSeccionText = genCod.getTopeMemoria() - 0x200;
            genCod.cargarEntero(0xBC, tamSeccionText);
            genCod.cargarEntero(0x1A8, tamSeccionText);

            int sectionAlignment = genCod.getCampo(0xD8);
            int cuenta = (2 + tamSeccionText / sectionAlignment) * sectionAlignment;
            genCod.cargarEntero(0xF0, cuenta);
            genCod.cargarEntero(0xD0, cuenta);
        } else {
            indicadorDeErrores.mostrar(Errores.PUNTO_ESPERADO, aLex.getCad() + "\n Se esperaba un .)");
        }
    }

    private void bloque(int base) throws IOException {
        genCod.cargarByte(0xE9);
        genCod.cargarEntero(0);
        String nombre = "";
        int valor = 0;
        Terminal tipo;
        int desplazamiento = 0;
        int posicion1 = genCod.getTopeMemoria();
        if (aLex.getS() == Terminal.CONST) {
            tipo = aLex.getS();
            do {
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR) {
                    nombre = aLex.getCad();
                    aLex.escanear();

                    if (aLex.getS() == Terminal.IGUAL) {
                        aLex.escanear();
                        //TODO: meter un IF para aLex.getS().equals(Terminal.MENOS)
                        if (aLex.getS() == Terminal.MENOS) {
                            aLex.escanear();
                            if (aLex.getS() == Terminal.NUMERO) {
                                valor = Integer.valueOf(aLex.getCad());
                                aSem.cargarTabla(base + desplazamiento, nombre, tipo, -valor);
                                aSem.mostrarTabla(base + desplazamiento);
                                desplazamiento++;
                                aSem.buscarDuplicado(nombre, base, base + desplazamiento - 1);
                                aLex.escanear();
                            } else {
                                indicadorDeErrores.mostrar(Errores.NUMERO_ESPERADO, aLex.getCad());
                            }
                        } else {
                            if (aLex.getS() == Terminal.NUMERO) {
                                valor = Integer.valueOf(aLex.getCad());
                                aSem.cargarTabla(base + desplazamiento, nombre, tipo, valor);
                                aSem.mostrarTabla(base + desplazamiento);
                                desplazamiento++;
                                aSem.buscarDuplicado(nombre, base, base + desplazamiento - 1);
                                aLex.escanear();
                            } else {
                                indicadorDeErrores.mostrar(Errores.NUMERO_ESPERADO, aLex.getCad());
                            }
                        }
                    } else {
                        indicadorDeErrores.mostrar(Errores.IGUAL_ESPERADO, aLex.getCad());
                    }
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_ESPERADO, aLex.getCad());
                }
            } while (aLex.getS() == Terminal.COMA);

            if (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(Errores.PUNTO_Y_COMA_ESPERADO, aLex.getCad());
            }
        }
        if (aLex.getS() == Terminal.VAR) {
            tipo = aLex.getS();
            do {
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR) {
                    nombre = aLex.getCad();
                    aSem.cargarTabla(base + desplazamiento, nombre, tipo, aSem.proximoValor());
                    aSem.mostrarTabla(base + desplazamiento);
                    desplazamiento++;
                    aSem.buscarDuplicado(nombre, base, base + desplazamiento - 1);
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_ESPERADO, aLex.getCad());
                }
            } while (aLex.getS() == Terminal.COMA);

            if (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(Errores.PUNTO_Y_COMA_ESPERADO, aLex.getCad());
            }
        }
        if (aLex.getS() == Terminal.PROCEDURE) {
            while (aLex.getS() == Terminal.PROCEDURE) {
                tipo = aLex.getS();
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR) {
                    nombre = aLex.getCad();
                    aSem.cargarTabla(base + desplazamiento, nombre, tipo, genCod.getTopeMemoria());
                    aSem.mostrarTabla(base + desplazamiento);
                    desplazamiento++;
                    aSem.buscarDuplicado(nombre, base, base + desplazamiento - 1);
                    aLex.escanear();
                    if (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                        aLex.escanear();
                        bloque(base + desplazamiento);
                        genCod.cargarByte(0xC3);
                        if (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                            aLex.escanear();
                        } else {
                            indicadorDeErrores.mostrar(Errores.PUNTO_Y_COMA_ESPERADO, aLex.getCad());
                        }
                    } else {
                        indicadorDeErrores.mostrar(Errores.PUNTO_Y_COMA_ESPERADO, aLex.getCad());
                    }
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_ESPERADO, aLex.getCad());
                }
            }
        }
        int distancia = genCod.getTopeMemoria() - posicion1;
        if (distancia != 0) {
            genCod.cargarEntero(posicion1 - 4, distancia);
        } else {
            genCod.setTopeMemoria(genCod.getTopeMemoria() - 5);
        }
        proposicion(base + desplazamiento - 1);
    }

    private void proposicion(int posTabla) throws IOException {
        IdentificadorBean aux;
        int libreAnt, librePost;
        switch (aLex.getS()) {
            case IDENTIFICADOR:
                aSem.buscarDeclaracion(aLex.getCad(), posTabla);
                aux = aSem.buscar(aLex.getCad(), posTabla);

                if (aux == null) {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_DECLARADO, aLex.getCad());
                } else if (aux.getTipo() != Terminal.VAR) {
                    if (aux.getTipo() == Terminal.PROCEDURE) {
                        System.out.println(" Se esperaba un CALL");
                        indicadorDeErrores.mostrar(Errores.ERROR_GENERICO, aLex.getCad());
                    } else {
                        System.out.println(" Error en el tipo de identificador");
                        indicadorDeErrores.mostrar(Errores.ERROR_GENERICO, aLex.getCad());
                    }
                }

                aLex.escanear();
                if (aLex.getS() == Terminal.ASIGNACION) {
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x89);
                    genCod.cargarByte(0x87);
                    genCod.cargarEntero(aux.getValor());
                } else {
                    indicadorDeErrores.mostrar(Errores.ASIGNACION_ESPERADA, aLex.getCad());
                }
                break;
            case CALL:
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR) {
                    aSem.buscarDeclaracion(aLex.getCad(), posTabla);
                    aux = aSem.buscar(aLex.getCad(), posTabla);

                    if (aux == null) {
                        indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_DECLARADO, aLex.getCad());
                    } else if (aux.getTipo() != Terminal.PROCEDURE) {
                        System.out.println("Error en el tipo de identificador");
                        indicadorDeErrores.mostrar(Errores.ERROR_GENERICO, aLex.getCad());
                    }

                    genCod.cargarByte(0xE8);
                    genCod.cargarEntero(aux.getValor() - (genCod.getTopeMemoria() + 4));
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_ESPERADO, aLex.getCad());
                }
                break;
            case BEGIN:
                aLex.escanear();
                proposicion(posTabla);
                while (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                    aLex.escanear();
                    proposicion(posTabla);
                }
                if (aLex.getS() == Terminal.END) {
                    System.out.println("END");
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(Errores.PUNTO_Y_COMA_ESPERADO, aLex.getCad());
                }
                break;
            case IF:
                aLex.escanear();
                condicion(posTabla);
                libreAnt = genCod.getTopeMemoria();
                if (aLex.getS() == Terminal.THEN) {
                    aLex.escanear();
                    proposicion(posTabla);
                    genCod.cargarBytes(0xE9,0x00,0x00,0x00,0x00); //genero un salto si la condición es false

                    librePost = genCod.getTopeMemoria();
                    genCod.cargarEntero(libreAnt - 4, (genCod.getTopeMemoria() - libreAnt)); //salto del else     
                    
                    if (aLex.getS() == Terminal.ELSE) {
                        aLex.escanear();
                        proposicion(posTabla);
                        //librePost = genCod.getTopeMemoria();
                    }
                    
                    genCod.cargarEntero(libreAnt - 4, librePost - libreAnt); //salto del then
                } else {
                    indicadorDeErrores.mostrar(Errores.THEN_ESPERADO, aLex.getCad());
                }

                break;
            case WHILE:
                aLex.escanear();
                int direccion1 = genCod.getTopeMemoria();
                condicion(posTabla);
                int direccion2 = genCod.getTopeMemoria();
                if (aLex.getS() == Terminal.DO) {
                    aLex.escanear();
                    proposicion(posTabla);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(direccion1 - (genCod.getTopeMemoria() + 4));
                    genCod.cargarEntero(direccion2 - 4, genCod.getTopeMemoria() - direccion2);
                } else {
                    indicadorDeErrores.mostrar(Errores.DO_ESPERADO, aLex.getCad());
                }
                break;

            case READLN:
                aLex.escanear();
                if (aLex.getS() == Terminal.ABRE_PARENTESIS) {
                    do {
                        aLex.escanear();
                        if (aLex.getS() == Terminal.IDENTIFICADOR) {
                            aSem.buscarDeclaracion(aLex.getCad(), posTabla);
                            aux = aSem.buscar(aLex.getCad(), posTabla);

                            if (aux == null) {
                                indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_DECLARADO, aLex.getCad());
                            } else if (aux.getTipo() != Terminal.VAR) {
                                indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_NO_DECLARADO, aLex.getCad());
                            }
                            genCod.cargarByte(0xE8);
                            genCod.cargarEntero(0x590 - (genCod.getTopeMemoria() + 4));
                            genCod.cargarByte(0x89);
                            genCod.cargarByte(0x87);
                            genCod.cargarEntero(aux.getValor());
                            aLex.escanear();

                        } else {
                            indicadorDeErrores.mostrar(Errores.IDENTIFICADOR_ESPERADO, aLex.getCad());
                        }
                    } while (aLex.getS() == Terminal.COMA);
                } else {
                    indicadorDeErrores.mostrar(26, aLex.getCad());
                }
                if (aLex.getS() == Terminal.CIERRA_PARENTESIS) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(2, "");
                }
                break;
            case WRITE:
                aLex.escanear();
                if (aLex.getS() == Terminal.ABRE_PARENTESIS) {
                    do {
                        aLex.escanear();
                        if (aLex.getS() == Terminal.CADENA_LITERAL) {
                            int tamCadena = aLex.getCad().length();
                            int baseOfCode = genCod.getCampo(0xCC);
                            int imageBase = genCod.getCampo(0xD4);
                            int posCadena = baseOfCode + imageBase - 0x200 + genCod.getTopeMemoria() + 15;
                            genCod.cargarByte(0xB8);
                            genCod.cargarEntero(posCadena);
                            genCod.cargarByte(0xE8);
                            genCod.cargarEntero(0x3E0 - (genCod.getTopeMemoria() + 4));
                            genCod.cargarByte(0xE9);
                            genCod.cargarEntero(tamCadena - 1);
                            for (int i = 1; i < (tamCadena - 1); i++) {
                                genCod.cargarByte(aLex.getCad().charAt(i));
                            }
                            genCod.cargarByte(0);
                            aLex.escanear();
                        } else {

                            expresion(posTabla);
                            genCod.cargarPopEax();
                            genCod.cargarByte(0xE8);
                            genCod.cargarEntero(0x420 - (genCod.getTopeMemoria() + 4));
                        }
                    } while (aLex.getS() == Terminal.COMA);
                } else {
                    indicadorDeErrores.mostrar(26, aLex.getCad());
                }
                if (aLex.getS() == Terminal.CIERRA_PARENTESIS) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(2, "");
                }

                break;
            case WRITELN:
                aLex.escanear();
                if (aLex.getS() == Terminal.ABRE_PARENTESIS) {
                    do {
                        aLex.escanear();
                        if (aLex.getS() == Terminal.CADENA_LITERAL) {
                            int tamCadena = aLex.getCad().length();
                            int baseOfCode = genCod.getCampo(0xCC);
                            int imageBase = genCod.getCampo(0xD4);
                            int posCadena = baseOfCode + imageBase - 0x200 + genCod.getTopeMemoria() + 15;
                            genCod.cargarByte(0xB8);
                            genCod.cargarEntero(posCadena);
                            genCod.cargarByte(0xE8);
                            genCod.cargarEntero(0x3E0 - (genCod.getTopeMemoria() + 4));
                            genCod.cargarByte(0xE9);
                            genCod.cargarEntero(tamCadena - 1);
                            for (int i = 1; i < (tamCadena - 1); i++) {
                                genCod.cargarByte(aLex.getCad().charAt(i));
                            }
                            genCod.cargarByte(0);
                            aLex.escanear();
                        } else {

                            expresion(posTabla);
                            genCod.cargarPopEax();
                            genCod.cargarByte(0xE8);
                            genCod.cargarEntero(0x420 - (genCod.getTopeMemoria() + 4));
                        }
                    } while (aLex.getS() == Terminal.COMA);

                    if (aLex.getS() == Terminal.CIERRA_PARENTESIS) {
                        aLex.escanear();
                    } else {
                        indicadorDeErrores.mostrar(2, "");
                    }

                }
                genCod.cargarByte(0xE8);
                genCod.cargarEntero(0x410 - (genCod.getTopeMemoria() + 4));
                break;
            case HALT: // lo mismo que cargar el fin del programa en programa()
                genCod.cargarByte(0xE9);
                int aux2 = genCod.getTopeMemoria() + 4;
                genCod.cargarEntero(0x588 - aux2);
                genCod.cargarEntero(0x701, genCod.getCampo(212) + genCod.getCampo(200) - 0x700 + genCod.getTopeMemoria());
                for (int i = 0; i < aSem.getValorVar() / 4; i++) {
                    genCod.cargarEntero(0);
                }
                int tamSeccionText = genCod.getTopeMemoria() - 0x200;
                genCod.cargarEntero(0x1A0, tamSeccionText);
                int fileAlignment = genCod.getCampo(0xDC);
                while (genCod.getTopeMemoria() % fileAlignment != 0) {
                    genCod.cargarByte(0);
                }
                tamSeccionText = genCod.getTopeMemoria() - 0x200;
                genCod.cargarEntero(0xBC, tamSeccionText);
                genCod.cargarEntero(0x1A8, tamSeccionText);
                int sectionAlignment = genCod.getCampo(0xD8);
                int cuenta = (2 + tamSeccionText / sectionAlignment) * sectionAlignment;
                genCod.cargarEntero(0xF0, cuenta);
                genCod.cargarEntero(0xD0, cuenta);
                aLex.escanear();
                break;
            default:
                // proposicion puede no tener nada? si.
                break;
        }

    }

    private void condicion(int posTabla) throws IOException {
        if (aLex.getS() == Terminal.ODD) {
            aLex.escanear();
            System.out.println("CONDICION: Escaneo = " + aLex.getCad());
            expresion(posTabla);
            genCod.cargarPopEax();
            genCod.cargarByte(0xA8);
            genCod.cargarByte(0x01);
            genCod.cargarByte(0x7B);
            genCod.cargarByte(0x05);
            genCod.cargarByte(0xE9);
            genCod.cargarEntero(0);
        } else {
            expresion(posTabla);

            switch (aLex.getS()) {
                case IGUAL:
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x39);
                    genCod.cargarByte(0xC3);
                    genCod.cargarByte(0x74);
                    genCod.cargarByte(0x05);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(0);
                    break;
                case DISTINTO:
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x39);
                    genCod.cargarByte(0xC3);
                    genCod.cargarByte(0x75);
                    genCod.cargarByte(0x05);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(0);
                    break;
                case MENOR:
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x39);
                    genCod.cargarByte(0xC3);
                    genCod.cargarByte(0x7C);
                    genCod.cargarByte(0x05);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(0);
                    break;
                case MENOR_IGUAL:
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x39);
                    genCod.cargarByte(0xC3);
                    genCod.cargarByte(0x7E);
                    genCod.cargarByte(0x05);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(0);
                    break;
                case MAYOR:
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x39);
                    genCod.cargarByte(0xC3);
                    genCod.cargarByte(0x7F);
                    genCod.cargarByte(0x05);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(0);
                    break;
                case MAYOR_IGUAL:
                    aLex.escanear();
                    expresion(posTabla);
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x39);
                    genCod.cargarByte(0xC3);
                    genCod.cargarByte(0x7D);
                    genCod.cargarByte(0x05);
                    genCod.cargarByte(0xE9);
                    genCod.cargarEntero(0);
                    break;
                default:
                    break;
            }

        }
    }

    private void expresion(int posTabla) throws IOException {
        switch (aLex.getS()) {
            case MAS:
                aLex.escanear();
                termino(posTabla);
                break;
            case MENOS:
                aLex.escanear();
                termino(posTabla);
                genCod.cargarPopEax();
                genCod.cargarByte(0xF7);
                genCod.cargarByte(0xD8);
                genCod.cargarByte(0x50);
                break;
            default:
                termino(posTabla);
                break;

        }

        while (aLex.getS() == Terminal.MAS || aLex.getS() == Terminal.MENOS) {
            String operacion = aLex.getS().toString();
            aLex.escanear();
            termino(posTabla);
            if (operacion.equals("MAS")) {
                genCod.cargarPopEax();
                genCod.cargarByte(0x5B);
                genCod.cargarByte(0x01);
                genCod.cargarByte(0xD8);
                genCod.cargarByte(0x50);
            }
            if (operacion.equals("MENOS")) {
                genCod.cargarPopEax();
                genCod.cargarByte(0x5B);
                genCod.cargarByte(0x93);
                genCod.cargarByte(0x29);
                genCod.cargarByte(0xD8);
                genCod.cargarByte(0x50);
            }
        }
    }

    private void termino(int posTabla) throws IOException {
        factor(posTabla);
        Terminal operacion;
        while (aLex.getS() == Terminal.POR || aLex.getS() == Terminal.DIVIDIDO) {
            operacion = aLex.getS();
            aLex.escanear();
            factor(posTabla);
            switch (operacion) {
                case POR:
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0xF7);
                    genCod.cargarByte(0xEB);
                    genCod.cargarByte(0x50);
                    break;
                case DIVIDIDO:
                    genCod.cargarPopEax();
                    genCod.cargarByte(0x5B);
                    genCod.cargarByte(0x93);
                    genCod.cargarByte(0x99);
                    genCod.cargarByte(0xF7);
                    genCod.cargarByte(0xFB);
                    genCod.cargarByte(0x50);
                    break;
                default:
                    break;
            }
        }
    }

    private void factor(int posTabla) throws IOException {
        IdentificadorBean aux;
        switch (aLex.getS()) {
            case IDENTIFICADOR:
                aSem.buscarDeclaracion(aLex.getCad(), posTabla);
                aux = aSem.buscar(aLex.getCad(), posTabla);
                if (aux.getTipo() == Terminal.VAR) {
                    genCod.cargarByte(0x8B);
                    genCod.cargarByte(0x87);
                    genCod.cargarEntero(aux.getValor());
                    genCod.cargarByte(0x50);
                    aLex.escanear();
                } else if (aux.getTipo() == Terminal.CONST) {
                    genCod.cargarByte(0xB8);
                    genCod.cargarEntero(aux.getValor());
                    genCod.cargarByte(0x50);
                    aLex.escanear();
                }
                break;
            case NUMERO:

                genCod.cargarByte(0xB8);
                genCod.cargarEntero(Integer.parseInt(aLex.getCad()));
                genCod.cargarByte(0x50);
                aLex.escanear();
                break;
            case ABRE_PARENTESIS:
                aLex.escanear();
                expresion(posTabla);
                if (aLex.getS() == Terminal.CIERRA_PARENTESIS) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(2, "");
                }
                break;
            default:
                indicadorDeErrores.mostrar(26, aLex.getCad());
                break;
        }
    }
}

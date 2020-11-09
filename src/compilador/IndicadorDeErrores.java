package compilador;

public class IndicadorDeErrores {

    public void mostrar(int cod, String cad) {
        System.err.print("ERROR: ");
        switch (cod) {
            case 2:
                System.err.println("Se esperaba un parentesis de cierre - ) -");
                break;
            case 16:
                System.err.println("Identificador duplicado: " + cad);
                break;
            case 17:
                System.err.println("Identificador no declarado: " + cad);
                break;
            case 21:
                System.err.println("Numero " + cad + " fuera de rango");
                break;
            case 22:
                System.err.println("Identificador " + cad + " demasiado largo");
                break;
            case 23:
                System.err.println("Cadena " + cad + " demasiado larga");
                break;
            case 25:
                System.err.println("Excepcion de E/S! (" + cad + ")");
                break;
            case 26:
                System.err.println("Se esperaba identificador, numero "
                        + "o apertura de parentesis y se recibio " + cad);
                break;
            case 32:
                System.err.println("Se esperaba igual y se recibio " + cad);
                break;
            case 99:
                System.err.println("Ocurrio algun ERROR, se recibio " + cad);
                break;
        }
        System.exit(1);
    }

    public void mostrar(Errores error, String cad) {
        System.err.print("ERROR: ");
        switch (error) {
            case CADENA_DEMASIADA_LARGA:
                System.err.println("Cadena " + cad + " demasiado larga");
                break;
            case EXCEPCION_DE_ENTRADA_SALIDA:
                System.err.println("Excepcion de E/S (" + cad + ")");
                break;
            case ASIGNACION_ESPERADA:
                System.err.println("Se esperaba asignacion y se recibio " + cad);
                break;
            case IDENTIFICADOR_ESPERADO:
                System.err.println("Se esperaba identificador y se recibio " + cad);
                break;
            case PUNTO_Y_COMA_ESPERADO:
                System.err.println("Se esperaba punto y coma y se recibio " + cad);
                break;
            case ABRE_PARENTESIS_ESPERADO:
                System.out.println("Se esperaba apertura de parentesis");
                break;
            case CIERRE_PARENTESIS_ESPERADO:
                System.out.println("Se esperaba cierre de parentesis");
                break;
            case IDENTIFICADOR_NO_ESPERADO:
                System.out.println("Identificador no esperado: " + cad);
                break;
            case DO_ESPERADO:
                System.err.println("Se esperaba do y se recibio " + cad);
                break;
            case THEN_ESPERADO:
                System.err.println("Se esperaba then y se recibio " + cad);
                break;
            case ELSE_ESPERADO:
                System.err.println("Se esperaba else y se recibio " + cad);
                break;
            case NUMERO_ESPERADO:
                System.out.println("Se esperaba un número y se recibió" + cad);
                break;
            case IGUAL_ESPERADO:
                System.err.println("Se esperaba igual y se recibio " + cad);
                break;
            case COMA_ESPERADA:
                System.out.println("Se esperaba una coma");
                break;
            case CONSTANTE_DUPLICADA:
                System.out.println("Constante duplicada: " + cad);
                break;
            case PUNTO_ESPERADO:
                System.err.println("Se esperaba un punto (.)");
                break;
            case ERROR_GENERICO:
                System.err.println("Ocurrió un error relacionado a: " + cad);
                break;
            case IDENTIFICADOR_DUPLICADO:
                System.err.println("Identificador duplicado: " + cad);
                break;
            case IDENTIFICADOR_NO_DECLARADO:
                System.err.println("Identificador no declarado: " + cad);
                break;
            case NUMERO_FUERA_DE_RANGO:
                System.err.println("Numero " + cad + " fuera de rango");
                break;
            case IDENTIFICADOR_DEMASIADO_LARGO:
                System.err.println("Identificador " + cad + " demasiado largo");
                break;

        }

        System.exit(1);
    }
}

enum Errores {
    PUNTO_ESPERADO,
    PUNTO_Y_COMA_ESPERADO,
    IDENTIFICADOR_ESPERADO,
    DO_ESPERADO,
    THEN_ESPERADO,
    ELSE_ESPERADO,
    ASIGNACION_ESPERADA,
    IDENTIFICADOR_DUPLICADO,
    IDENTIFICADOR_NO_DECLARADO,
    NUMERO_FUERA_DE_RANGO,
    IDENTIFICADOR_DEMASIADO_LARGO,
    CADENA_DEMASIADA_LARGA,
    NUMERO_ESPERADO,
    ERROR_GENERICO,
    EXCEPCION_DE_ENTRADA_SALIDA,
    IGUAL_ESPERADO,
    ABRE_PARENTESIS_ESPERADO,
    CIERRE_PARENTESIS_ESPERADO,
    IDENTIFICADOR_NO_ESPERADO,
    COMA_ESPERADA,
    CONSTANTE_DUPLICADA;

}

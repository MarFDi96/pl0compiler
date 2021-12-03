package compilador;

public class IndicadorDeErrores {

    public void mostrar(Errores error, String cad) {
        System.err.print("ERROR: ");
        switch (error) {
            case PUNTO_ESPERADO:
                System.err.println("Se esperaba un punto (.)");
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
            case CADENA_DEMASIADA_LARGA:
                System.err.println("Cadena " + cad + " demasiado larga");
                break;
            case EXCEPCTION_DE_ENTRADA_SALIDA:
                System.err.println("Excepcion de E/S (" + cad + ")");
                break;
            case ABRE_PARENTESIS_ESPERADO:
                System.out.println("Se esperaba apertura de parentesis");
                break;
            case CIERRE_PARENTESIS_ESPERADO:
                System.out.println("Se esperaba cierre de parentesis");
                break;
            case IDENTIFICADOR_NO_ESPERADO:
                System.out.println("Identificador no esperado: " +cad);
                break;
            case COMA_ESPERADA:
                System.out.println("Se esperaba una coma");
                break;
            case CONSTANTE_DUPLICADA:
                System.out.println("Constante duplicada: " + cad);
                break;
        }
        
        System.exit(1);
    }

    public void mostrar(int cod, String cad) {
        System.err.print("ERROR: ");
        switch (cod) {
            case 1:
                System.err.println("Se esperaba un punto (.)");
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
                System.err.println("Excepcion de E/S (" + cad + ")");
                break;
        }
        System.exit(1);
    }
}

enum Errores {

    PUNTO_ESPERADO,
    IDENTIFICADOR_DUPLICADO,
    IDENTIFICADOR_NO_DECLARADO,
    NUMERO_FUERA_DE_RANGO,
    IDENTIFICADOR_DEMASIADO_LARGO,
    CADENA_DEMASIADA_LARGA,
    EXCEPCTION_DE_ENTRADA_SALIDA,
    ABRE_PARENTESIS_ESPERADO,
    CIERRE_PARENTESIS_ESPERADO,
    IDENTIFICADOR_NO_ESPERADO,
    COMA_ESPERADA,
    CONSTANTE_DUPLICADA;

}

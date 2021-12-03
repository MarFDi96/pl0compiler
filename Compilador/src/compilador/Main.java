package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String nomArch = null;
        while(nomArch == null || !nomArch.equalsIgnoreCase("salir")){
            if (args.length == 0) {
                System.out.print("Ingrese el nombre del archivo PL/0: ");
                nomArch = new Scanner(System.in).nextLine();
            } else {
                nomArch = args[0];
            }
            if (nomArch.isEmpty()) {
                System.out.println("Error!\n");
                System.out.println("Uso: java -jar \"Compilador.jar\" <archivo>\n");
            } else {
                Reader archFuente = null;
                try {
                    archFuente = new BufferedReader(new FileReader(nomArch));
                    AnalizadorLexico aLex = new AnalizadorLexico(archFuente);
                    IndicadorDeErrores indicadorDeErrores = new IndicadorDeErrores();
                    AnalizadorSemantico aSem = new AnalizadorSemantico(indicadorDeErrores);
                    GeneradorDeCodigo genCod = new GeneradorDeCodigo(nomArch, indicadorDeErrores);
                    AnalizadorSintactico aSint = new AnalizadorSintactico(aLex, aSem, genCod, indicadorDeErrores);
                    try {
                        aSint.analizar();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                    try {
                        archFuente.close();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                } catch (FileNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}

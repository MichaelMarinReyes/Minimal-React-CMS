package principal;

import conexion.Servidor;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        System.out.println("Iniciando servidor...");
        servidor.iniciarServer();
        /*
        System.out.println("Ingrese texto:");
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();
        Lexer lexer = new Lexer(new StringReader(texto));
        Parser parser = new Parser(lexer);
        try {
            parser.parse();
            System.out.println(parser.getTexto());
        } catch (Exception e) {
            System.out.println("error de parseo: " + e.getMessage());
        }*/
    }
}
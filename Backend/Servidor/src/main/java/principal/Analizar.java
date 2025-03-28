package principal;

import analizadores.Lexer;
import analizadores.Parser;

import java.io.StringReader;

public class Analizar {
    private String texto;

    public Analizar(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String analizar() {
        try {
            Lexer lexer = new Lexer(new StringReader(texto));
            Parser parser = new Parser(lexer);
            parser.parse();

            //ENVIAR AL CLIENTE
            System.out.println(parser.getTexto());
            return parser.getTexto();
        } catch (Exception e) {
            System.err.println("Error al parsear: " + e.getMessage());
        }
        return "";
    }
}

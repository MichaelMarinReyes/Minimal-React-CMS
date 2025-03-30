package principal;

import analizadores.Lexer;
import analizadores.Parser;
import analizadores.antlr4.AnalizadorBaseListener;
import analizadores.antlr4.AnalizadorLexer;
import analizadores.antlr4.AnalizadorListener;
import analizadores.antlr4.AnalizadorParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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

    public String analizarAntlr4() {
        try {
            CharStream entrada = CharStreams.fromString(texto);

            AnalizadorLexer lexer = new AnalizadorLexer(entrada);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            AnalizadorParser parser = new AnalizadorParser(tokens);
            ParseTree tree = parser.request();

            ParseTreeWalker walker = new ParseTreeWalker();
            AnalizadorListener listener = new AnalizadorBaseListener();
            walker.walk(listener, tree);

            System.out.println(tree.toStringTree(parser));
            return tree.toStringTree(parser);
        } catch (Exception e) {
            System.err.println("Error al parsear: " + e.getMessage());
        }
        return "";
    }
}

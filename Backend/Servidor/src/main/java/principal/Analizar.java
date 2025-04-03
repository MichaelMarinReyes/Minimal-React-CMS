package principal;

import analizadores.ErrorLexico;
import analizadores.Lexer;
import analizadores.Parser;
import analizadores.antlr4.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.StringReader;

import static analizadores.Lexer.errores;
import static analizadores.Parser.erroresSintactico;

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
            return e.getMessage();
        }
    }

    public String analizarAntlr4() {
        try {
            errores.clear();
            erroresSintactico.clear();

            CharStream entrada = CharStreams.fromString(texto);

            AnalizadorLexer lexer = new AnalizadorLexer(entrada);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            AnalizadorParser parser = new AnalizadorParser(tokens);

            lexer.removeErrorListeners();
            lexer.addErrorListener(new ErrorLexicoAntlr());

            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorSintacticoAntlr());

            ParseTree tree = parser.inicial();

            if (!errores.isEmpty()) {
                for (ErrorLexico error : errores) {
                    System.out.println(error.getTipo());
                }
            }

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

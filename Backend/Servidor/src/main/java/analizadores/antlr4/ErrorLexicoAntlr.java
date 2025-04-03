package analizadores.antlr4;

import analizadores.ErrorLexico;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import static analizadores.Lexer.errores;

public class ErrorLexicoAntlr extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String lexema = offendingSymbol != null ? offendingSymbol.toString() : "Desconocido";
        errores.add(new ErrorLexico(lexema, line, charPositionInLine, "Léxico", msg));
    }
}

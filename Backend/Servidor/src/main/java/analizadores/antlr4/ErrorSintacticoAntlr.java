package analizadores.antlr4;

import analizadores.ErrorSintactico;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import static analizadores.Parser.erroresSintactico;

public class ErrorSintacticoAntlr extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String lexema = offendingSymbol instanceof Token ? ((Token) offendingSymbol).getText() : "Desconocido";
        erroresSintactico.add(new ErrorSintactico(lexema, line, charPositionInLine, "Sintactico", msg));
    }
}

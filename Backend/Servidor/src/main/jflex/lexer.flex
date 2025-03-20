package analizadores;

import java_cup.runtime.*;
import analizadores.ErrorLexico;
import analizadores.Token;
import java.util.ArrayList;

%%
%public
%class Lexer
%unicode
%line
%column
%cup

id = [a-zZ-Z_][a-zA-Z0-9]*

%{
    public static ArrayList<ErrorLexico> errores = new ArrayList<>();
    public static ArrayList<Token> tokens = new ArrayList<>();
    public ArrayList<ErrorLexico> getErrores() {
        return errores;
    }
%}

%%

"GET"             { return new Symbol(ParserSym.GET, yyline+1, yycolumn+1, yytext()); }
"POST"            { return new Symbol(ParserSym.POST, yyline+1, yycolumn+1, yytext()); }
"PATCH"           { return new Symbol(ParserSym.PATCH, yyline+1, yycolumn+1, yytext()); }
"DELETE"          { return new Symbol(ParserSym.DELETE, yyline+1, yycolumn+1, yytext()); }
"SITIO"           { return new Symbol(ParserSym.SITIO, yyline+1, yycolumn+1, yytext()); }
"PAGINA"          { return new Symbol(ParserSym.PAGINA, yyline+1, yycolumn+1, yytext()); }
"abrir sitio"     { return new Symbol(ParserSym.ABRIR_SITIO, yyline+1, yycolumn+1, yytext()); }
"crear pagina"    { return new Symbol(ParserSym.CREAR_PAGINA, yyline+1, yycolumn+1, yytext()); }
id                { return new Symbol(ParserSym.ID, yyline+1, yycolumn+1, yytext()); }
"<main>"([^<]|<[^/]|<\/[^m]|<\/m[^a]|<\/ma[^i]|<\/mai[^n]|<\/main[^>])*"</main>" { return new Symbol(sym.BODY, yyline+1, yycolumn+1, yytext()); }
[ \t\n\r]+        {}
"\/\/".*          {}
"/*"([^*]|"*"[^/])*"*/" { /* Ignorar comentarios multilínea */ }
<<EOF>>           { return new Symbol(ParserSym.EOF); }
[^]               { errores.add(new ErrorLexico(yytext(), yyline + 1, yycolumn + 1, "Léxico", "Caracter desconocido: " + yytext()));
          System.err.println("Error léxico: " + yytext() + " linea: " + String.valueOf(yyline + 1) + " columna: " + String.valueOf(yycolumn + 1));}
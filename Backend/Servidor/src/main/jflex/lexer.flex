package analizadores;

import java_cup.runtime.*;
import analizadores.ErrorLexico;
import analizadores.Token;
import javax.swing.text.html.parser.Parser;import java.util.ArrayList;

%%
%public
%class Lexer
%unicode
%line
%column
%cup

DIGITO = [0-9]
TEXTO = [a-zA-Z]
NUMERO = [0-9]+|-[0-9]+
CADENA = \"([a-zA-Z]+|[a-zA-Z0-9]+)\"
CARACTER = \'([a-z]|[A-Z])\'

%{
    public static ArrayList<ErrorLexico> errores = new ArrayList<>();
    public static ArrayList<Token> tokens = new ArrayList<>();
    public ArrayList<ErrorLexico> getErrores() {
        return errores;
    }
%}

%%

"GET"                                  { return new Symbol(ParserSym.GET, yyline+1, yycolumn+1, yytext()); }
"POST"                                 { return new Symbol(ParserSym.POST, yyline+1, yycolumn+1, yytext()); }
"PATCH"                                { return new Symbol(ParserSym.PATCH, yyline+1, yycolumn+1, yytext()); }
"DELETE"                               { return new Symbol(ParserSym.DELETE, yyline+1, yycolumn+1, yytext()); }
"SUCCESS"                              { return new Symbol(ParserSym.SUCCESS, yyline+1, yycolumn+1, yytext()); }
"NOT_FOUND"                            { return new Symbol(ParserSym.NOT_FOUND, yyline+1, yycolumn+1, yytext()); }
"INTERNAL_SERVER_ERROR"                { return new Symbol(ParserSym.INTERNAL_SERVER_ERROR, yyline+1, yycolumn+1, yytext()); }
"SITIO"                                { return new Symbol(ParserSym.SITIO, yyline+1, yycolumn+1, yytext()); }
"PAGINA"                               { return new Symbol(ParserSym.PAGINA, yyline+1, yycolumn+1, yytext()); }
"crear"                                { return new Symbol(ParserSym.CREAR, yyline+1, yycolumn+1, yytext()); }
"agregar"                              { return new Symbol(ParserSym.AGREGAR, yyline+1, yycolumn+1, yytext()); }
"eliminar"                             { return new Symbol(ParserSym.ELIMINAR, yyline+1, yycolumn+1, yytext()); }
"modificar"                            { return new Symbol(ParserSym.MODIFICAR, yyline+1, yycolumn+1, yytext()); }
"nombre"                               { return new Symbol(ParserSym.NOMBRE, yyline+1, yycolumn+1, yytext()); }
"path"                                 { return new Symbol(ParserSym.PATH, yyline+1, yycolumn+1, yytext()); }
"true"                                 { return new Symbol(ParserSym.TRUE, yyline+1, yycolumn+1, yytext()); }
"false"                                { return new Symbol(ParserSym.FALSE, yyline+1, yycolumn+1, yytext()); }
"void"                                 { return new Symbol(ParserSym.VOID, yyline+1, yycolumn+1, yytext()); }
"["                                    { return new Symbol(ParserSym.CORCHETE_ABRE, yyline+1, yycolumn+1, yytext()); }
"]"                                    { return new Symbol(ParserSym.CORCHETE_CIERRA, yyline+1, yycolumn+1, yytext()); }
"="                                    { return new Symbol(ParserSym.IGUAL, yyline+1, yycolumn+1, yytext()); }
\"({CADENA}\/{CADENA})+                { return new Symbol(ParserSym.ATRIBUTO_PATH, yyline+1, yycolumn+1, yytext()); }
"#"{CADENA}                            { return new Symbol(ParserSym.COMENTARIO_TOML, yyline+1, yycolumn+1, yytext()); }
{NUMERO}                               { return new Symbol(ParserSym.NUMERO, yyline+1, yycolumn+1, yytext()); }
{CADENA}                               { return new Symbol(ParserSym.CADENA, yyline+1, yycolumn+1, yytext()); }
{CARACTER}                             { return new Symbol(ParserSym.CARACTER, yyline+1, yycolumn+1, yytext()); }
"<" .*? ">"                            { return new Symbol(ParserSym.BODY, yytext()); }
{TEXTO}({TEXTO}|{DIGITO}|_)*           { return new Symbol(ParserSym.ID, yytext()); }
[ \t\n\r]+        {}
"\/\/".*          {}
"/*"([^*]|"*"[^/])*"*/" { /* Ignorar comentarios multilínea */ }
<<EOF>>           { return new Symbol(ParserSym.EOF); }
[^]               { errores.add(new ErrorLexico(yytext(), yyline + 1, yycolumn + 1, "Léxico", "Caracter desconocido: " + yytext()));
          System.err.println("Error léxico: " + yytext() + " linea: " + String.valueOf(yyline + 1) + " columna: " + String.valueOf(yycolumn + 1));}
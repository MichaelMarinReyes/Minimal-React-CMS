package analizadores;

import java_cup.runtime.*;
import analizadores.ErrorLexico;
import analizadores.Token;import org.w3c.dom.ls.LSOutput;
import javax.swing.text.html.parser.Parser;import java.util.ArrayList;

%%
%public
%class Lexer
%unicode
%line
%column
%cup

DIGITO = [0-9]
ID = [a-zA-Z0-9]+
NUMERO = [0-9]+ | [0-9]+\.[0-9]+
CADENA = \" [a-zA-Z0-9 ]+ \"
CARACTER = \'([a-z]|[A-Z])\'
PATH_TOML = \"([a-zA-Z0-9_]+([/][a-zA-Z0-9_]+)* '.mtsx')\"

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
"SITIO"                                { return new Symbol(ParserSym.SITIO_SHTTP, yyline+1, yycolumn+1, yytext()); }
"PAGINA"                               { return new Symbol(ParserSym.PAGINA_SHTTP, yyline+1, yycolumn+1, yytext()); }
"sitio"                                { return new Symbol(ParserSym.SITIO_SCL, yyline+1, yycolumn+1, yytext()); }
"pagina"                               { return new Symbol(ParserSym.PAGINA_SCL, yyline+1, yycolumn+1, yytext()); }
"crear"                                { return new Symbol(ParserSym.CREAR, yyline+1, yycolumn+1, yytext()); }
"agregar"                              { return new Symbol(ParserSym.AGREGAR, yyline+1, yycolumn+1, yytext()); }
"eliminar"                             { return new Symbol(ParserSym.ELIMINAR, yyline+1, yycolumn+1, yytext()); }
"modificar"                            { return new Symbol(ParserSym.MODIFICAR, yyline+1, yycolumn+1, yytext()); }
"nombre"                               { return new Symbol(ParserSym.NOMBRE_TOML, yyline+1, yycolumn+1, yytext()); }
"path"                                 { return new Symbol(ParserSym.PATH, yyline+1, yycolumn+1, yytext()); }
"true"                                 { return new Symbol(ParserSym.TRUE, yyline+1, yycolumn+1, yytext()); }
"false"                                { return new Symbol(ParserSym.FALSE, yyline+1, yycolumn+1, yytext()); }
"void"                                 { return new Symbol(ParserSym.VOID, yyline+1, yycolumn+1, yytext()); }
"main"                                 { return new Symbol(ParserSym.MAIN, yyline+1, yycolumn+1, yytext()); }
"const"                                { return new Symbol(ParserSym.CONST, yyline+1, yycolumn+1, yytext()); }
"var"                                  { return new Symbol(ParserSym.VAR, yyline+1, yycolumn+1, yytext()); }
"string"                               { return new Symbol(ParserSym.STRING, yyline+1, yycolumn+1, yytext()); }
"number"                               { return new Symbol(ParserSym.NUMBER, yyline+1, yycolumn+1, yytext()); }
"return"                               { return new Symbol(ParserSym.RETURN, yyline+1, yycolumn+1, yytext()); }
"function"                             { return new Symbol(ParserSym.FUNCTION, yyline+1, yycolumn+1, yytext()); }
"h1"                                   { return new Symbol(ParserSym.H1, yyline+1, yycolumn+1, yytext()); }
"h2"                                   { return new Symbol(ParserSym.H2, yyline+1, yycolumn+1, yytext()); }
"h3"                                   { return new Symbol(ParserSym.H3, yyline+1, yycolumn+1, yytext()); }
"h4"                                   { return new Symbol(ParserSym.H4, yyline+1, yycolumn+1, yytext()); }
"h5"                                   { return new Symbol(ParserSym.H5, yyline+1, yycolumn+1, yytext()); }
"h6"                                   { return new Symbol(ParserSym.H6, yyline+1, yycolumn+1, yytext()); }
"<p>"                                  { return new Symbol(ParserSym.PARRAFO_ABRIR, yyline+1, yycolumn+1, yytext()); }
"</p>"                                 { return new Symbol(ParserSym.PARRAFO_CERRAR, yyline+1, yycolumn+1, yytext()); }
"input"                                { return new Symbol(ParserSym.INPUT, yyline+1, yycolumn+1, yytext()); }
"value"                                { return new Symbol(ParserSym.VALUE, yyline+1, yycolumn+1, yytext()); }
"button"                               { return new Symbol(ParserSym.BUTTON, yyline+1, yycolumn+1, yytext()); }
"onClick"                              { return new Symbol(ParserSym.ONCLICK, yyline+1, yycolumn+1, yytext()); }
"["                                    { return new Symbol(ParserSym.CORCHETE_ABRE, yyline+1, yycolumn+1, yytext()); }
"]"                                    { return new Symbol(ParserSym.CORCHETE_CIERRA, yyline+1, yycolumn+1, yytext()); }
"{"                                    { return new Symbol(ParserSym.LLAVE_ABRE, yyline+1, yycolumn+1, yytext()); }
"}"                                    { return new Symbol(ParserSym.LLAVE_CIERRA, yyline+1, yycolumn+1, yytext()); }
"("                                    { return new Symbol(ParserSym.PAREN_ABRE, yyline+1, yycolumn+1, yytext()); }
")"                                    { return new Symbol(ParserSym.PAREN_CIERRA, yyline+1, yycolumn+1, yytext()); }
"\""                                   { return new Symbol(ParserSym.COMILLAS, yyline+1, yycolumn+1, yytext()); }
"+"                                    { return new Symbol(ParserSym.SUMA, yyline+1, yycolumn+1, yytext()); }
"-"                                    { return new Symbol(ParserSym.RESTA, yyline+1, yycolumn+1, yytext()); }
"*"                                    { return new Symbol(ParserSym.MULTIPLICACION, yyline+1, yycolumn+1, yytext()); }
"/"                                    { return new Symbol(ParserSym.DIVISION, yyline+1, yycolumn+1, yytext()); }
"^"                                    { return new Symbol(ParserSym.POTENCIA, yyline+1, yycolumn+1, yytext()); }
"="                                    { return new Symbol(ParserSym.IGUAL, yyline+1, yycolumn+1, yytext()); }
"<"                                    { return new Symbol(ParserSym.MENOR_QUE, yyline+1, yycolumn+1, yytext()); }
">"                                    { return new Symbol(ParserSym.MAYOR_QUE, yyline+1, yycolumn+1, yytext()); }
">="                                   { return new Symbol(ParserSym.MAYOR_IGUAL, yyline+1, yycolumn+1, yytext()); }
"<="                                   { return new Symbol(ParserSym.MENOR_IGUAL, yyline+1, yycolumn+1, yytext()); }
"=>"                                   { return new Symbol(ParserSym.FLECHA, yyline+1, yycolumn+1, yytext()); }
"||"                                   { return new Symbol(ParserSym.OR, yyline+1, yycolumn+1, yytext()); }
"&&"                                   { return new Symbol(ParserSym.AND, yyline+1, yycolumn+1, yytext()); }
"!"                                    { return new Symbol(ParserSym.NEGACION, yyline+1, yycolumn+1, yytext()); }
"."                                    { return new Symbol(ParserSym.PUNTO, yyline+1, yycolumn+1, yytext()); }
";"                                    { return new Symbol(ParserSym.PUNTO_COMA, yyline+1, yycolumn+1, yytext()); }
{DIGITO}                               { return new Symbol(ParserSym.DIGITO, yyline+1, yycolumn+1, yytext()); }
{ID}                                   { return new Symbol(ParserSym.ID, yyline+1, yycolumn+1, yytext()); }
{NUMERO}                               { return new Symbol(ParserSym.NUMERO, yyline+1, yycolumn+1, yytext()); }
{CADENA}                               { return new Symbol(ParserSym.CADENA, yyline+1, yycolumn+1, yytext()); }
{CARACTER}                             { return new Symbol(ParserSym.CARACTER, yyline+1, yycolumn+1, yytext()); }
{PATH_TOML}                            { return new Symbol(ParserSym.PATH_TOML, yyline+1, yycolumn+1, yytext()); }
"#"[^\n]*                              { /* ignorar comentario TOML */ }
"//"[^\n]*                             { /* ignorar comentario de línea */ }
"/*".*?"*/"                            { /* ignorar comentario de bloque */ }
<<EOF>>                                { return new Symbol(ParserSym.EOF); }
[^]                                    { errores.add(new ErrorLexico(yytext(), yyline + 1, yycolumn + 1, "Léxico", "Caracter desconocido: " + yytext()));
                                         System.err.println("Error léxico: " + yytext() + " linea: " + String.valueOf(yyline + 1) + " columna: " + String.valueOf(yycolumn + 1));}
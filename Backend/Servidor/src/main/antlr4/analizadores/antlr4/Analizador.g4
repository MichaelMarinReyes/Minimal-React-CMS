grammar Analizador;

//Gramática
inicial         : request
                | response
                | toml
                ;

request         : metodos objetivo_shttp sCL_instruccion body_html;

metodos         : GET
                | POST
                | PATCH
                | DELETE
                ;

objetivo_shttp : SITIO_SHTTP
               | PAGINA_SHTTP
               ;

sCL_instruccion : accion objetivo_scl parametros;

accion          : CREAR
                | AGREGAR
                | ELIMINAR
                | MODIFICAR
                ;

objetivo_scl : SITIO_SCL
             | PAGINA_SCL
             ;

parametros : ID
           | ID parametros
           ;

response : codigo body_html;

codigo : SUCCESS
       | NOT_FOUND
       | INTERNAL_SERVER_ERROR
       ;

body_html : '<' MAIN '>' contenido '</' MAIN '>'
          | '<' MAIN '>' contenido (contenido)* '</' MAIN '>';

contenido : header
          | parrafos
          | inputs
          | buttons
          ;

header : ('<' H1 '>' texto '</' H1 '>' | '<' H1 '>' texto LLAVE_ABRE ID LLAVE_CIERRA '</' H1 '>') //CORREGIR TEXTO
       | ('<' H2 '>' texto '</' H2 '>' | '<' H2 '>' texto LLAVE_ABRE ID LLAVE_CIERRA '</' H2 '>')
       | ('<' H3 '>' texto '</' H3 '>' | '<' H3 '>' texto LLAVE_ABRE ID LLAVE_CIERRA '</' H3 '>')
       | ('<' H4 '>' texto '</' H4 '>' | '<' H4 '>' texto LLAVE_ABRE ID LLAVE_CIERRA '</' H4 '>')
       | ('<' H5 '>' texto '</' H5 '>' | '<' H5 '>' texto LLAVE_ABRE ID LLAVE_CIERRA '</' H5 '>')
       | ('<' H6 '>' texto '</' H6 '>' | '<' H6 '>' texto LLAVE_ABRE ID LLAVE_CIERRA '</' H6 '>');

parrafos : PARRAFO_ABRIR texto PARRAFO_CERRAR
         ;

inputs : '<' INPUT VALUE '=' LLAVE_ABRE ID LLAVE_CIERRA '/' '>'; //SI SIRVE

buttons : '<' BUTTON ONCLICK '=' LLAVE_ABRE ID LLAVE_CIERRA '>' texto '</' BUTTON '>'
            ;
texto : ID
      | ID texto
      | texto '{' ID '}'
      | texto '{' ID '}' texto
      ;

calculadora : '-' calculadora
            | '(' calculadora ')'
            | calculadora '^' calculadora
            | calculadora ('*' | '/') calculadora
            | calculadora ('+' | '-') calculadora
            | NUMERO;

toml : etiqueta_toml atributo_toml (toml)*;

etiqueta_toml : '[' ID ']'
              | '[' ID (PUNTO ID)+ ']'
              ;

atributo_toml : NOMBRE_TOML '=' CADENA
              | PATH '=' PATH_TOML
              | NOMBRE_TOML '=' CADENA PATH '=' PATH_TOML
              ;

//Lexer
WS: [ \t\n\r]+ -> skip;
GET: 'GET';
POST: 'POST';
PATCH: 'PATCH';
DELETE: 'DELETE';
SUCCESS: 'SUCCESS';
NOT_FOUND: 'NOT_FOUND';
INTERNAL_SERVER_ERROR: 'INTERNAL_SERVER_ERROR';
SITIO_SHTTP: 'SITIO';
PAGINA_SHTTP: 'PAGINA';
SITIO_SCL: 'sitio';
PAGINA_SCL: 'pagina';
CREAR: 'crear';
AGREGAR: 'agregar';
ELIMINAR: 'eliminar';
MODIFICAR: 'modificar';
NOMBRE_TOML: 'nombre';
PATH: 'path';
TRUE: 'true';
FALSE: 'false';
VOID: 'void';
MAIN: 'main';
CONST: 'const';
VAR: 'var';
STRING: 'string';
NUMBER: 'number';
FUNCTION: 'function';
RETURN: 'return';

H1: 'h1';
H2: 'h2';
H3: 'h3';
H4: 'h4';
H5: 'h5';
H6: 'h6';
PARRAFO_ABRIR: '<p>';
PARRAFO_CERRAR: '</p>';
INPUT: 'input';
VALUE: 'value';
BUTTON: 'button';
ONCLICK: 'onClick';
CORCHETE_ABRE: '[';
CORCHETE_CIERRA: ']';
LLAVE_ABRE: '{';
LLAVE_CIERRA: '}';
PAREN_ABRE: '(';
PAREN_CIERRA: ')';
COMILLAS: '"';
SUMA: '+';
RESTA: '-';
MULTIPLICACION: '*';
DIVISION: '/';
POTENCIA: '^';
IGUAL: '=';
MENOR_QUE: '<';
MAYOR_QUE: '>';
MAYOR_IGUAL: '>=';
MENOR_IGUAL: '<=';
FLECHA: '=>';
OR: '||';
AND: '&&';
NEGACION: '!';
PUNTO: '.';
PUNTO_COMA: ';';

DIGITO: [0-9];
ID: [a-zA-Z0-9]+;
NUMERO: [0-9]+;
CADENA: '"' [a-zA-Z0-9 ]+ '"';
CARACTER: '\'' [a-zA-Z] '\'';
PATH_TOML: '"' [a-zA-Z0-9_]+([/][a-zA-Z0-9_]+)* '.mtsx' '"';

COMENTARIO_TOML: '#'~[\n]*;
COMENTARIO_LINEA: '//' ~[\n]* -> skip;
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;
grammar Analizador;

//Gramática
inicial         : request
                | response
                | lenguajeToml
                ;

request         : metodos objetivo sCL_instruccion body_html;

metodos         : GET
                | POST
                | PATCH
                | DELETE
                ;

objetivo        : SITIO
                | PAGINA
                ;

sCL_instruccion : accion objetivo parametros;

accion          : CREAR
                | AGREGAR
                | ELIMINAR
                | MODIFICAR
                ;

parametros      : ID
                | ID parametros
                ;

response        : codigo body_html;

codigo          : SUCCESS
                | NOT_FOUND
                | INTERNAL_SERVER_ERROR
                ;

body_html       : '<' MAIN '>' contenido '<' '/' MAIN '>'
                | '<' MAIN '>' DIVISION MAIN '>';
contenido       : header
                | parrafos
                | inputs
                | buttons;

header          : '<' H1 texto '</' H1 '>'
                | '<' H2 texto '</' H2 '>'
                | '<' H3 texto '</' H3 '>'
                | '<' H4 texto '</' H4 '>'
                | '<' H5 texto '</' H5 '>'
                | '<' H6 texto '</' H6 '>';

parrafos        : PARRAFO_ETIQUETA texto PARRAFO_CIERRE;

inputs          : '<' INPUT VALUE '=' LLAVE_ABRE ID LLAVE_CIERRA '/' '>';

texto           : TEXTO
                | TEXTO LLAVE_ABRE ID LLAVE_CIERRA
                | TEXTO LLAVE_ABRE ID LLAVE_CIERRA texto
                ;

buttons         : '<' BUTTON ONCLICK '=' LLAVE_ABRE ID LLAVE_CIERRA '>' TEXTO '<' '/' BUTTON '>';

lenguajeToml    : etiquetas atributos;

etiquetas       : CORCHETE_ABRE ID CORCHETE_CIERRA
                | CORCHETE_ABRE id_etiqueta CORCHETE_CIERRA;

id_etiqueta     : ID PUNTO ID
                | ID PUNTO id_etiqueta;

atributos       : NOMBRE '=' CADENA
                | PATH '=' PATH_TOML;

minimal_react   : CONST ID '=' PAREN_ABRE PAREN_CIERRA FLECHA LLAVE_ABRE cuerpo_minimal RETURN PAREN_ABRE PAREN_CIERRA PUNTO_COMA LLAVE_CIERRA;

cuerpo_minimal  : VAR;

calculadora     : '-' calculadora
                | '(' calculadora ')'
                | calculadora '^' calculadora
                | calculadora ('*' | '/') calculadora
                | calculadora ('+' | '-') calculadora
                | NUMERO;

//Lexer
DIGITO: [0-9];
TEXTO: [a-zA-Z0-9 ]+;
ID: [a-zA-Z0-9]+;
NUMERO: [0-9]+;
CADENA: '"' [a-zA-Z0-9]+ '"';
CARACTER: '\'' [a-zA-Z] '\'';
PATH_TOML: '"' [a-zA-Z0-9_/]+ '.mtsx' '"';
PARRAFO: [a-zA-Z0-9 ]+ ('\n' [a-zA-Z0-9 ]+)?;

GET: 'GET';
POST: 'POST';
PATCH: 'PATCH';
DELETE: 'DELETE';
SUCCESS: 'SUCCESS';
NOT_FOUND: 'NOT_FOUND';
INTERNAL_SERVER_ERROR: 'INTERNAL_SERVER_ERROR';
SITIO: 'SITIO';
PAGINA: 'PAGINA';
CREAR: 'crear';
AGREGAR: 'agregar';
ELIMINAR: 'eliminar';
MODIFICAR: 'modificar';
NOMBRE: 'nombre';
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

MENOR_QUE: '<';
MAYOR_QUE: '>';
H1: 'h1';
H2: 'h2';
H3: 'h3';
H4: 'h4';
H5: 'h5';
H6: 'h6';
PARRAFO_ETIQUETA: '<p>';
PARRAFO_CIERRE: '</p>';
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
MAYOR_IGUAL: '>=';
MENOR_IGUAL: '<=';
FLECHA: '=>';
OR: '||';
AND: '&&';
NEGACION: '!';
PUNTO: '.';
PUNTO_COMA: ';';

COMENTARIO_TOML: '#' CADENA;
COMENTARIO_LINEA: '//' ~[\n]* -> skip;
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;
ESPACIOS: [ \t\n\r]+ -> skip;
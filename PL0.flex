package compilador;

enum Terminal {

    IF, CALL, CADENA_LITERAL, NUMERO, ASIGNACION, NULO, IDENTIFICADOR, EOF, MAS,
    CONST, VAR, PROCEDURE, BEGIN, END, THEN, WHILE, DO, ODD, MENOS, POR,
    DIVIDIDO, IGUAL, COMA, PUNTO_Y_COMA, MAYOR, MENOR, MENOR_IGUAL, MAYOR_IGUAL,
    DISTINTO, PUNTO, READLN, WRITELN, WRITE, ABRE_PARENTESIS, CIERRA_PARENTESIS
}

%%
/* Modificadores */
%public
%class AnalizadorLexico
%type Terminal
%function escanear
%line
%column
%ignorecase
%unicode

/* Expresiones regulares para la tercera seccion */
identificador = [A-Za-z] ([A-Za-z] | [0-9])*
numero        = 0 | [1-9][0-9]*
cadenaLiteral = \' [^']* \'

lineTerminator = \r|\n|\r\n
whiteSpace = {lineTerminator} | [ \t\f]

%{
    private Terminal s;

    public String getCad() {
        return yytext();
    }

    public Terminal getS() {
        return s;
    }
%}
	
%%

/* caracteres que deben saltearse */
{whiteSpace}	{}

/* separadores y terminales */
"("				{s = Terminal.ABRE_PARENTESIS; return s;}
")"				{s = Terminal.CIERRA_PARENTESIS; return s;}
","				{s = Terminal.COMA; return s;}
";"				{s = Terminal.PUNTO_Y_COMA; return s;}
"."				{s = Terminal.PUNTO; return s;}

/* asignacion */
":="			{s = Terminal.ASIGNACION; return s;}

/* operadores */
/* "sqr"			{s = Terminal.RAIZCUADRADA; return s;}*/
/* "^"				{s = Terminal.CUADRADO; return s;}*/
"+"				{s = Terminal.MAS; return s;}
"-"				{s = Terminal.MENOS; return s;}
"*"				{s = Terminal.POR; return s;}
"/"				{s = Terminal.DIVIDIDO; return s;}

/* operadores relacionales */
"<"				{s = Terminal.MENOR; return s;}
"<="			{s = Terminal.MENOR_IGUAL; return s;}
">"				{s = Terminal.MAYOR; return s;}
">="			{s = Terminal.MAYOR_IGUAL; return s;}
"="				{s = Terminal.IGUAL; return s;}
"<>"			{s = Terminal.DISTINTO; return s;}

/* palabras reservadas */
begin			{s = Terminal.BEGIN; return s;}
call			{s = Terminal.CALL; return s;}
const			{s = Terminal.CONST; return s;}
do				{s = Terminal.DO; return s;}
end				{s = Terminal.END; return s;}
if				{s = Terminal.IF; return s;}
odd				{s = Terminal.ODD; return s;}
procedure		{s = Terminal.PROCEDURE; return s;}
then			{s = Terminal.THEN; return s;}
var				{s = Terminal.VAR; return s;}
while			{s = Terminal.WHILE; return s;}
readln			{s = Terminal.READLN; return s;}
write			{s = Terminal.WRITE; return s;}
writeln			{s = Terminal.WRITELN; return s;}

/* otros simbolos terminales */
{numero}		{s = Terminal.NUMERO; return s;}
{identificador}	{s = Terminal.IDENTIFICADOR; return s;}
{cadenaLiteral}	{s = Terminal.CADENA_LITERAL; return s;}

/* si no es ninguno de los simbolos anteriores */
[^]				{s = Terminal.NULO; return s;}

/* si se ha llegado al final del archivo */
<<EOF>>			{s = Terminal.EOF; return s;}

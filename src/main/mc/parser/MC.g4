/**
 * Student name:
 * Student ID:
 */
grammar MC;

@lexer::header{
	package mc.parser;
}



@parser::header{
	package mc.parser;
}

options{
	language=Java;
}

prog  : stmt+ ;

stmt  : assign | ifstmt ;

assign: ID ASSIGN exp SEMI ;

ifstmt: IF exp THEN stmt ELSE stmt ;

exp   : exp ADDOP term | term ;

//term  : ID | INTLIT | LP exp RP ;
term : factor (MULOP factor)*;
factor : ID | INTLIT | LP exp RP;
MULOP : '*' | '/';

IF: 'if';

THEN : 'then' ;

ELSE: 'else' ;

ASSIGN: '=' ;

ADDOP: '+' | '-' ;

ID: [a-zA-Z]+ ;

INTLIT: [0-9]+;

LP: '(';

RP: ')';

SEMI: ';' ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines


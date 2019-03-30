//LET OP:
//Dit is de bijgewerkte versie met handigere LOWER_IDENT en andere volgorde/prioriteit

grammar ICSS;

//--- LEXER: ---

OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//Literals
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;

//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//--- PARSER: ---
stylesheet:  variableAssignment* stylerule* EOF;
stylerule: selector OPEN_BRACE declaration+ CLOSE_BRACE;


classSelector: CLASS_IDENT;
idSelector: ID_IDENT;
tagSelector: LOWER_IDENT;

selector: classSelector|idSelector|tagSelector;

declaration: declarationWithOperations| declarationWithoutOperations | variableAssignment |stylerule;
declarationWithoutOperations: propertyName COLON value SEMICOLON;
declarationWithOperations: propertyName COLON value operation+ SEMICOLON;

pixelLiteral: PIXELSIZE;
colorLiteral: COLOR;
percentageLiteral: PERCENTAGE;
scalarLiteral: SCALAR;

value: pixelLiteral | colorLiteral | percentageLiteral | scalarLiteral | variableName;

propertyName: background_color | width | color | variableName;

background_color: 'background-color';
width: 'width';
color: 'color';

variableAssignment: variableName ASSIGNMENT_OPERATOR value SEMICOLON;
variableName:ID_IDENT|CLASS_IDENT|LOWER_IDENT|CAPITAL_IDENT;
operation: operator value;

addOperation: PLUS;
subtractOperation: MIN;
multiplyOperation: MUL;

operator: subtractOperation | addOperation | multiplyOperation;


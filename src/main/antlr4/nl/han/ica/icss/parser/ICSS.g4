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
stylerule: selector OPEN_BRACE declaration* variableAssignment* stylerule* CLOSE_BRACE; //may contain multiple delcarations, variableassignments and stylerules

classSelector: CLASS_IDENT;
idSelector: ID_IDENT;
tagSelector: LOWER_IDENT;
capital_ident: CAPITAL_IDENT;

selector: classSelector|idSelector|tagSelector;

declaration: propertyName COLON operation* SEMICOLON;

pixelLiteral: PIXELSIZE;
colorLiteral: COLOR;
percentageLiteral: PERCENTAGE;
scalarLiteral: SCALAR;

value: pixelLiteral | colorLiteral | percentageLiteral | scalarLiteral | variableName;

propertyName: background_color | width | color | height| variableName;

background_color: 'background-color';
width: 'width';
color: 'color';
height: 'height';

variableAssignment: variableName ASSIGNMENT_OPERATOR operation* SEMICOLON;
variableName: capital_ident;
operation: value #inputvalue | operation MUL operation #multiplyOperation | operation (PLUS|MIN) operation  #plusOrMinOperation; //gives the right operation tree



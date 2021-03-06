grammar Pathy;

r	:	(ds|fs)+ DEND (qs|fs)* ;

ds	:	op=(NODE|JUNCT|ACT) idpar PSTART PEND ';'					#SimpleDec
	|	ENT idpar PSTART idpar PEND ';'							#EntDecDef
	|	ENT idpar PSTART idpar ',' intpar PEND ';'					#EntDecEnergy
	|	LINK idpar PSTART idpar ',' idpar PEND ';'					#LinkDecDef
	|	LINK idpar PSTART idpar ',' idpar ',' intpar PEND ';'				#LinkDecWeight
	|	LINK idpar PSTART idpar ',' idpar ',' dirpar=(DIRTWOW|DIRATOB|DIRBTOA|DIRBLOCKED) PEND ';'		#LinkDecDir
	|	LINK idpar PSTART idpar ',' idpar ',' intpar ',' dirpar=(DIRTWOW|DIRATOB|DIRBTOA|DIRBLOCKED) PEND ';'		#LinkDecBoth
	|	'AssignAction' PSTART idpar ',' idpar PEND ';'					#AssignAct
	|	'DeleteItem' PSTART idpar PEND ';'								#DelItem
	;

fs	:	op=(SL2|SLB) PSTART idpar PEND ';'					#SetLink2B
	|	op=(SE|SW|ME) PSTART idpar ',' intpar PEND ';'				#SetVals
	|	'MoveEntity' PSTART idpar ',' idpar PEND ';'					#MoveEnt
	|	'SetLinkOneWay' PSTART idpar ',' idpar ',' idpar PEND ';'		#SetLink1W
	|	'SetJunctDirection' PSTART idpar ',' idpar ',' idpar ',' dirpar=(DIRTWOW|DIRATOB|DIRBTOA|DIRBLOCKED) PEND ';'	#SetJunctDir
	;

qs	:	op=(FPN|FPL|FPJ|FPA|FPE) PSTART PEND ';'		 	#NoParamQuery
	|	op=(F1L|F1D|F1W|F1A|F1C|F1I|F1T|F1B|F1V|F1O|F1E) PSTART idpar PEND ';'		#OneParamQuery
	|	op=(F2A|F2B|F2C|F2D|F2E) PSTART idpar ',' idpar PEND ';'		#TwoParamQuery
	;

idpar	:	ID		#idparam ;
intpar	:	INT		#intparam ;

SE	:	'SetEnergy';
ME	:	'ModEnergy';
SW	:	'SetWeight';
SL2	:	'SetLinkTwoWay';
SLB	:	'SetLinkBlocked';

FPN	:	'PrintNodes';
FPL	:	'PrintLinks';
FPJ	:	'PrintJunctions';
FPA	:	'PrintActions';
FPE	:	'PrintEntities';

F1L	:	'Links';
F1D	:	'Direction';
F1W	:	'Weight';
F1A	:	'ActionsThere';
F1C	:	'Connectivity';
F1I	:	'WhereIs';
F1E	:	'EnergyLevel';
F1T	:	'LinkedTo';
F1B	:	'ConnectedBy';
F1O	:	'TypeOf';
F1V	:	'Availability';

F2A	:	'PathTo';
F2B	:	'CanMove';
F2C	:	'CanMoveWhy';
F2D	:	'EnergyReq';
F2E	:	'SharedActions';

DEND	:	'DecEnd;' ;

DIRTWOW	:	'TWOWAY';
DIRATOB	:	'ATOB';
DIRBTOA	:	'BTOA';
DIRBLOCKED	:	'BLOCKED';
ID	:	[A-Za-z_] [A-Za-z0-9_]* ;
INT	:	[\+\-]*[0-9]+ ;

PSTART	:	'(' ;
PEND	:	')' ;

NODE	:	'@' ;
LINK	:	'^' ;
JUNCT	:	'%' ;
ACT	:	'$' ;
ENT	:	'£' ;

WS	:	[ \t\r\n]+ -> skip ;

grammar Barnemat;

 /** Barnemat Mk4*/

@header{package lexparse; }

fil: 'Program' ID hoveddel ;

hoveddel : 'Start{' argument+ '}Slutt';

argument:       variabelErklæring				// declaration
    | 	        verdiTildeling			  		// assignment	
    |		entenEller					// If else
    |		løkke1						// do-while loop
    |		løkke2						// while-loop
    |  	utskrift					// print
    |		les						// read
    | 		testMetode
    ;
    
testMetode: 'Test;' ;        
variabelErklæring
    :  variabel (':=' (uttrykk))? ';'
    ;
variabel : variabeltype ID ;    
    
variabeltype: ('Heltall' | 'Desimaltall' | 'Setning' | 'Sannhet') ;      

verdiTildeling: 'Angi' ID ':=' ( uttrykk) ';' ;

utskrift : 'Skriv' 'ut' ':' (ID | Setning) ';';

les   : 'Skriv' 'inn' ':' ID ';';

uttrykk:       uttrykk Ganging uttrykk			# Ganging
	| 	uttrykk Deling uttrykk				# Deling
	| 	uttrykk Pluss uttrykk				# Pluss
	|	uttrykk Minus uttrykk 				# Minus
    	|   	ID                      			# ID
    	|   	Heltall					# Heltall
    	|   	Desimaltall					# Desimaltall		
    	| 	Setning					# Setning
   	|   	'('uttrykk')'					# Parentes

    ;
    
    
entenEller: 'Dersom' '(' (sammenlikningEnten | setningsSammenlikningEnten) ')' 'så' '{' argument* '}' ('ellers' '{' argument* '}')? ;

sammenlikningEnten :     (sammenliknbar sammenlikn sammenliknbar) 
			| (ID ('!='|'=')  Sannhet) 
			| (('!')? ID) 
			;
setningsSammenlikningEnten: ((ID|Setning) ('!='|'=') (ID|Setning)) ;

løkke1 : ('Gjenta'|'gjenta') '{' argument* '}' ('Medan'|'Mens'|'medan'|'mens') '(' sammenlikningLøkke1 ')' ;
løkke2 : ('Medan'|'Mens'|'medan'|'mens') '(' sammenlikningLøkke2 ')' ('Gjenta'|'gjenta') '{' argument* '}'  ;

sammenlikningLøkke1 : (sammenliknbar sammenlikn sammenliknbar) | (ID ('!='|'=')  Sannhet) | (('!')? ID);
sammenlikningLøkke2 : (sammenliknbar sammenlikn sammenliknbar) | (ID ('!='|'=')  Sannhet) | (('!')? ID);

sammenliknbar:		ID			# SammenliknbarID
		|	Desimaltall		# SammenliknbarDesi
		| 	Heltall 		# SammenliknbarHel
		;	
			
			    
sammenlikn:      StørreEnn 		
		| MindreEnn 		
		| Lik 			
		| Ulik			 
		;    
	

ID  :   Bokstav (Bokstav | [0-9])* ;
Bokstav : [a-zA-ZæøåÆØÅ] ;
fragment Siffer : ('0'..'9') ;
fragment Ikke_Null_Siffer : ('1'..'9') ;


Setning : '"' (ESC|.)*? '"' ;
Heltall : Siffer | (Ikke_Null_Siffer Siffer+);
Desimaltall : Heltall ',' Siffer+ ;

Sannhet: ('sant' | 'usant') ;

Ganging : '*';
Deling : '/';
Pluss : '+';
Minus : '-';


			
StørreEnn : '>' ;
MindreEnn : '<' ;
Lik : '=' ;
Ulik : '!=' ;
ESC : ('\\"' | '\\\\') ;
Intet : 'Null';


WS  :   [ \t\n\r]+ -> skip ;

SL_COMMENT
    :   '//' .*? '\n' -> skip
    ;
ML_COMMENT  
    :   '/*' .*? '*/' '\n' -> skip
    ;  
    


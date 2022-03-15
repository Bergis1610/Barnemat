package compiler;

import org.antlr.v4.runtime.ParserRuleContext; // need to debug every rule
import org.objectweb.asm.*;  //classes for generating bytecode
import org.objectweb.asm.Opcodes; //Explicit import for ASM bytecode constants
import org.objectweb.asm.Label;
import static org.objectweb.asm.Opcodes.*;
import lexparse.*; //classes for lexer parser
import java.util.*;

/*
package compiler;

import org.antlr.v4.runtime.ParserRuleContext; // need to debug every rule

import org.objectweb.asm.*;  		//classes for generating bytecode
import org.objectweb.asm.Opcodes; 	//Explicit import for ASM bytecode constants
import org.objectweb.asm.Label;
import static org.objectweb.asm.Opcodes.*;

import lexparse.*; //classes for lexer parser
import java.util.*;


//Tillegg
//import java.util.regex.*;
//import java.io.*;
import java.lang.*;

*/

public class BMlistener extends BarnematBaseListener{


	private ClassWriter cw; 		//class level ClassWriter 
	private MethodVisitor mainVisitor; 	//class level MethodVisitor
	private String programName; 		//name of the class and the output file (used by ASM)
	private boolean debug; 		//flag to indicate debug status
	
	
	public BMlistener(String programName){
	       
		this.programName = programName;
		debug = false;

	}//end constructor


	public void setupClass(){
		

		//Set up the classwriter
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        	cw.visit(V11, Opcodes.ACC_PUBLIC,this.programName, null, "java/lang/Object",null);
	
		//Use local MethodVisitor to create the constructor for the object
		MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
       	mv.visitCode();
        	mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
        	mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(1,1);
        	mv.visitEnd();
       	
		//Use global MethodVisitor to write bytecode according to entries in the parsetree	
	 	mainVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,  "main", "([Ljava/lang/String;)V", null, null);
        	mainVisitor.visitCode();

	}//end setupClass
	
	Label sjekker = new Label();

	public void closeClass(){
		//Use global MethodVisitor to finish writing the bytecode and write the binary file.
		if(exit)
			return;
		
		System.out.println("Kommer hit i hvertfall");
		
		mainVisitor.visitInsn(Opcodes.RETURN);
		mainVisitor.visitMaxs(0, 0);
		mainVisitor.visitEnd();

		cw.visitEnd();

        	byte[] b = cw.toByteArray();



        	Utilities.writeFile(b,this.programName+".class");

        	System.out.println("Ei fil har blitt laga i output-mappa");

	}//end closeClass
	
//-----------------------------------------------------------------------------------------------------------------------------------------------	
// Byrjing av vanlig program
//-----------------------------------------------------------------------------------------------------------------------------------------------
public class variabel{

	public String variabelType = "";
	public String verdi = "";
	public int minnePlassering = -1;
	public boolean verdiSatt = false;

	public variabel(String variabelType, String verdi){
		this.variabelType = variabelType;
		this.verdi = verdi;
	
	}
	
	public variabel(){
		variabelType = "";
		verdi = "";
	}

}//Slutt variabel-objekt

public static final int StackMax = 30;

public class stacker{

	public int[]stack = new int[StackMax];
	public int head = 0;


	public stacker(){
		
	}
}//Slutt stacker-objekt


	public void printStack(stacker s){
	
		for(int i = s.head;i>= 0;i--){
			System.out.print(s.stack[i]+",");
		}	
	
	}//slutt printStack
	
	public int push(stacker s, int value){
	
		if(s.head == StackMax-1){
			return -1;
		} else {	
			s.head++;
			s.stack[s.head] = value;
			return value;
		}
	
	}//slutt push
	
	public int pop(stacker s){
	
		int value = 0;
		if(s.head == 0){
			return value;
		} else {	
			
			value = s.stack[s.head];
			s.stack[s.head] = 0;
			s.head--;
			return value;
		}
	
	}//slutt pop
	
	
	public int peek(stacker s){
	
		return s.stack[s.head];
		
	}//slutt peek



	public HashMap<String, variabel> SymbolTable = new HashMap<String, variabel>();
	
	
	
	public boolean exit = false;
	public int minneTeller = 1;
	
	
	
	
	
	
	@Override 
	public void enterFil(BarnematParser.FilContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i fila for første gang");
		
		setupClass();
	
	}
	@Override 
	public void exitFil(BarnematParser.FilContext ctx){
		if(exit)
			return;
		//System.out.println("Prøve");	
		
		if(!besøktArgument){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Tom hoveddel");
			exit = true;
			return;
		
		}

		
		closeClass();
		System.out.println("forlater fila. . .");
	
	}
	
		
	@Override 
	public void enterHoveddel(BarnematParser.HoveddelContext ctx) {
		if(exit)
			return;
			
			//Påminner
			
			/*
			System.out.println("\n\n------------------------------------------");
			System.out.println("PÅMINNELSE TIL DEG EMIL, JA DEG!");
			System.out.println("------------------------------------------");
			
			System.out.println("NESTE OPPGAVE ER UTSKRIFT, OG UTSKRIFT KAN NÅ SKRIVE UT UTTRYKK SÅ VI MÅ FJERNE ARITMETIKK");
			System.out.println("\n\n\n Du finner forresten denne påminninga i \"enterHoveddel\", ha en god dag videre!\n\n\n");
			exit = true;
			return;
			*/
			
	}
	@Override 
	public void exitHoveddel(BarnematParser.HoveddelContext ctx) {
		if(exit)
			return;
		//System.out.println("Går ut or hoveddel");	
	}
	
	public boolean besøktArgument = false;
	@Override 
	public void enterArgument(BarnematParser.ArgumentContext ctx) {
		if(exit)
			return;
		besøktArgument = true;	
		System.out.println("Går inn i argument");	
			
	}
	@Override 
	public void exitArgument(BarnematParser.ArgumentContext ctx) { 
		if(exit)
			return;
	
		System.out.println("Går ut or argument");
	}
	
	//public String varType = "";
	
	public void printHashMap(HashMap<String,variabel> map){
	
		Object[]keys = map.keySet().toArray();
		String val;
		int mem;
		boolean set;
		
		for(int i = 0; i < keys.length; i++){
			System.out.print(keys[i]);
			System.out.print(": " + map.get(keys[i]).variabelType); 
			val = map.get(keys[i]).verdi;
			mem = map.get(keys[i]).minnePlassering;
			set = map.get(keys[i]).verdiSatt;
			System.out.println(", " + val + ", " + mem + ", " + set);
			
		} 	
		
	}// end printHashMap
	
	
	public void compilerError(String Output){
		
	}
	
	//Kanskje fjerne ignore case
	public boolean reserved(String s){
		boolean ret;
		if(s.equalsIgnoreCase("sant")){
			ret = true;
		} else if(s.equalsIgnoreCase("usant")){
			ret = true;
		} else{
		
			ret = false;	

		}
		return ret;
		
	}
	
	public int variabelType(String s){
		
		int ret;
		if(s.equalsIgnoreCase("Heltall")){
		
			ret = 1;
			
		} else if(s.equalsIgnoreCase("Desimaltall")){
		
			ret = 2;
		
		} else if(s.equalsIgnoreCase("Setning")){
		
		
			ret = 3;
		
		} else if(s.equalsIgnoreCase("Sannhet")){
		
			ret = 4;
			
		} else {
		
			ret = -1;
			
		}
		
		return ret;
		
	}
	
	//public stacker varErk = new stacker();
	//public String kretsID = "";
	public String kretsVarType = "";
	public String kretsID = "";
	
	@Override 
	public void enterVariabelErklæring(BarnematParser.VariabelErklæringContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i variabelErklæring");
		
		//System.out.println(ctx.getChildCount());
		
			
		
		
		
		if(ctx.getChildCount() != 2 && ctx.getChildCount() != 4){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabelErklæring, sjekk at alt er skrevet riktig");
			exit = true;
			return;
		
		}	
			
		kretsVarType = ctx.getChild(0).getChild(0).getText();
		String lokalID = ctx.getChild(0).getChild(1).getText();
		
		if(reserved(lokalID)){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("ID: \""+ lokalID + "\" er reservert og kan ikke brukes, vennligst kall den noe annet.");
			exit = true;
			return;
		}
		
		
		if(SymbolTable.containsKey(lokalID)){
		 
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("ID " + lokalID + " er allerede i bruk");
			exit = true;
			return;
		
		} else {
			
			variabel lokalVar = new variabel();
			lokalVar.variabelType = kretsVarType;
			lokalVar.minnePlassering = minneTeller;
			
			SymbolTable.put(lokalID, lokalVar);
			minneTeller++;
			
			kretsID = lokalID;
			
		}
		
		
		/*
		
			Legge til reserverte ord!
			Stacker for variabeltype, sant og usant er reserverte ord!
		
		
		*/
	
	}
	
	public boolean setningsAlias = false;
	
	@Override 
	public void exitVariabelErklæring(BarnematParser.VariabelErklæringContext ctx){ 
		if(exit)
			return;
		
		if(ctx.getChildCount() == 2 && !ctx.getChild(1).getText().equals(";")){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabel erklæring.");
			exit = true;
			return;
		
		
		}
			
		if(ctx.getChildCount() == 4){
		
			System.out.println(ctx.getChild(1).getText());
		/*
			if(ctx.getChild(1).getText().equals(":=")){
			
			
			}
		*/	
			String lokalID = ctx.getChild(0).getChild(1).getText();
			//System.out.println(lokalID);
				
			variabel lokalVar = SymbolTable.get(lokalID);
			
			//	1. Hente minnePlasseringa
			int lagre  = lokalVar.minnePlassering;
			System.out.println(lokalVar.verdiSatt);
			
			//	2. Finne ut hvilken type variabelen er 
			String lokalType = lokalVar.variabelType;
			
			String setningsSjekk = ctx.getChild(2).getText();
			int varTypeTall = variabelType(lokalType);
			
			//System.out.println("test :"+setningsSjekk.contains("\""));
		
			
			switch(varTypeTall){
				case 1:{
				//Heltall
					System.out.println("Dette har type heltall");
					mainVisitor.visitVarInsn(ISTORE, lagre);
					lokalVar.verdiSatt = true;
					break;
				
				}
				case 2:{
				//Desimaltall
					System.out.println("Dette har type desimaltall");
					mainVisitor.visitVarInsn(DSTORE, lagre);
					lokalVar.verdiSatt = true;
					break;
				}
				case 3:{
				//Setning
					System.out.println("Dette har type setning");
					
					mainVisitor.visitVarInsn(ASTORE, lagre);
					lokalVar.verdiSatt = true;
					break;

				}
				case 4:{
				//Sannhet
					System.out.println("Dette har type sannhet");
					
					//boolean sant/true = 1
					//boolean usant/false = 0
					
					
					mainVisitor.visitVarInsn(ISTORE, lagre);
					lokalVar.verdiSatt = true;
					
					break;
					
					
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
			
					System.out.println("Variabeltypen du oppga finns ikke.");
					exit = true;
					return;
				
				
				}
			
			
			}
			System.out.println(lokalVar.verdiSatt);
			
			//	3. lagre den i minneplasseringa ifølge riktig type
		
		} 
		
		//setningsAlias = false;	
		kretsID = "IKKE DEFINERT";
		kretsVarType = "IKKE DEFINERT";
		System.out.println("Går ut or variabel erklæring\n\n");	
	}
	
	@Override 
	public void enterVerdiTildeling(BarnematParser.VerdiTildelingContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i variabel tildeling");
		
		//System.out.println("ctx.getChildCount() = " + ctx.getChildCount()); 
		if(ctx.getChildCount() != 5){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabel tildeling, sjekk at alt er skrevet riktig");
			exit = true;
			return;
		}
		
		
		String lokalID = ctx.getChild(1).getText();
		System.out.println("ID: " + lokalID);
		
		
		if(!SymbolTable.containsKey(lokalID)){
		 
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("ID " + lokalID + " er ikke erklært");
			exit = true;
			return;
		
		} else {
			
			kretsID = lokalID;
			kretsVarType  = SymbolTable.get(lokalID).variabelType;
		
			
		}
					
	}

	@Override 
	public void exitVerdiTildeling(BarnematParser.VerdiTildelingContext ctx){ 
		if(exit)
			return;
				
			
				
			variabel lokalVar = SymbolTable.get(kretsID);
			System.out.println("testing:  " + lokalVar.verdiSatt + "\n\n\n");
			int lagre  = lokalVar.minnePlassering;
				
			switch(variabelType(lokalVar.variabelType)){
				case 1:{
				//Heltall
					System.out.println("Dette har type heltall");
					mainVisitor.visitVarInsn(ISTORE, lagre);
					lokalVar.verdiSatt = true;
					break;
				
				}
				case 2:{
				//Desimaltall
					System.out.println("Dette har type desimaltall");
					mainVisitor.visitVarInsn(DSTORE, lagre);
					lokalVar.verdiSatt = true;
					break;
				}
				case 3:{
				//Setning
					System.out.println("Dette har type setning");
					
					mainVisitor.visitVarInsn(ASTORE, lagre);
					lokalVar.verdiSatt = true;
					break;

				}
				case 4:{
				//Sannhet
					System.out.println("Dette har type sannhet");
					
					//boolean sant/true = 1
					//boolean usant/false = 0
					
					
					mainVisitor.visitVarInsn(ISTORE, lagre);
					lokalVar.verdiSatt = true;
					
					break;
					
					
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
			
					System.out.println("Variabeltypen du oppga finns ikke.");
					exit = true;
					return;
				
				
				}
			
			
			}
			System.out.println(lokalVar.verdiSatt);
			
			
		System.out.println("Går ut or variabel tildeling");	
	}
	
	@Override 
	public void enterVariabel(BarnematParser.VariabelContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i variabel");	
			
		if(ctx.getChild(1).getText().equalsIgnoreCase("<missing ID>")){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabel. Mangler ID");
			exit = true;
			return;
		
		}	
		
	}
	
	@Override 
	public void exitVariabel(BarnematParser.VariabelContext ctx){ 
		if(exit)
			return;
			
		System.out.println("Går ut or variabel");	
	}
	
	@Override 
	public void enterVariabeltype(BarnematParser.VariabeltypeContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i variabelType");
		
		int sjekk = variabelType(ctx.getText());
		if(sjekk != 1 && sjekk != 2 && sjekk != 3 && sjekk != 4){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Variabeltypen er feil, velg enten Heltall, Desimaltall, Setning eller Sannhet");
			exit = true;
			return;
		
		
		}
		
		/*
		System.out.println("---------------------------------------------------");
		System.out.println(ctx.getText());
		System.out.println("---------------------------------------------------");	
		*/	
	}
	@Override 
	public void exitVariabeltype(BarnematParser.VariabeltypeContext ctx){ 
		if(exit)
			return;
			
		System.out.println("Går ut or variabelType");
	}
	
	
	@Override 
	public void enterHeltall(BarnematParser.HeltallContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i Heltall");
		
		if(variabelType(kretsVarType) != 1){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil med variabeltypen du oppga og verdien som angis.");
			System.out.println("Variabeltypen du oppga: \"" + kretsVarType + "\" og verdien som angis: \"Heltall\"");
			exit = true;
			return;
		
		
		
		}
		
		
		
		int tall = Integer.valueOf(ctx.getText());
		mainVisitor.visitIntInsn(SIPUSH, tall);
		
		System.out.println(tall + "\n");
		
	}
	
	@Override 
	public void exitHeltall(BarnematParser.HeltallContext ctx){ 
		if(exit)
			return;
			
		System.out.println("Går ut or Heltall");
	}
	
	
	
	@Override 
	public void enterSetning(BarnematParser.SetningContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i Setning");
		
		//Variabelerklæring
		//utskrift
		
		
		if(variabelType(kretsVarType) != 3){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil med variabeltypen du oppga og verdien som angis.");
			System.out.println("Variabeltypen du oppga: \"" + kretsVarType + "\" og verdien som angis: \"Setning\"");
			exit = true;
			return;
		}
		
		String Setninga = ctx.getText();
		//String Setninga = ctx.getChild(2).getText();
		String subSetninga = Setninga.substring(1,Setninga.length()-1);
						
		System.out.println(Setninga);
		System.out.println(subSetninga);
						
		mainVisitor.visitLdcInsn(subSetninga);		

	}

	@Override 
	public void exitSetning(BarnematParser.SetningContext ctx){ 
		if(exit)
			return;
			
		System.out.println("Går ut or Setning");
		
	}
	
	  
	@Override 
	public void enterDesimaltall(BarnematParser.DesimaltallContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i Desimaltall");
		
		if(variabelType(kretsVarType) != 2){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil med variabeltypen du oppga og verdien som angis.");
			System.out.println("Variabeltypen du oppga: \"" + kretsVarType + "\" og verdien som angis: \"Desimaltall\"");
			exit = true;
			return;
		
		
		
		}
		

		String dub = ctx.getText().replace(",",".");
		
		System.out.println(dub + "\n\n");
		double tall = Double.valueOf(dub);
		mainVisitor.visitLdcInsn((double)tall);
		
	}
	
	@Override 
	public void exitDesimaltall(BarnematParser.DesimaltallContext ctx){ 
		if(exit)
			return;
			
		System.out.println("Går ut or Desimaltall");
	}
	
	@Override 
	public void enterID(BarnematParser.IDContext ctx){ 
		if(exit)
			return;	
		System.out.println("Går inn i ID");
		
		String lokalID = ctx.getText();
	
		
		// Hvis kretsTypen er sannhet, så må ID'en være sant eller usant
		if(variabelType(kretsVarType) == 4){
			//Dette er sannhet
			if(lokalID.equalsIgnoreCase("sant")){
			
				mainVisitor.visitInsn(ICONST_1);
				return;
			} else if(lokalID.equalsIgnoreCase("usant")){
			
				mainVisitor.visitInsn(ICONST_0);
				return;
			} 
		}
		
		System.out.println(lokalID);
		if(!SymbolTable.containsKey(lokalID)){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("IDen du oppga: \"" + lokalID + "\" finns ikke.");
			exit = true;
			return;
		
		} else {
		
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
				
				System.out.println("IDen du oppga: \"" + lokalID + "\" har ikke blitt tildelt en verdi enda.");
				exit = true;
				return;
			
			}
			 else {
			
				if(variabelType(kretsVarType) != variabelType(lokalVar.variabelType)){
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR FEIL");
					System.out.println("------------------------------------------");
			
					System.out.println("Feil med variabeltypen du oppga og verdien som angis.");
				System.out.println("Variabeltypene \""+kretsVarType +"\" og \""+lokalVar.variabelType+"\" samstemmer ikke");
					exit = true;
					return;
					
				} else {
					
					int lokalLagring = lokalVar.minnePlassering;
					
					if(variabelType(lokalVar.variabelType) == 1){
						//Heltall
						mainVisitor.visitVarInsn(ILOAD, lokalLagring);
					
					} else if(variabelType(lokalVar.variabelType) == 2){
						//Desimaltall
						mainVisitor.visitVarInsn(DLOAD, lokalLagring);
					} else if(variabelType(lokalVar.variabelType) == 3){
						//Setning
						mainVisitor.visitVarInsn(ALOAD, lokalLagring);
						setningsAlias = true;
					} else if(variabelType(lokalVar.variabelType) == 4){
						//Sannhet
						mainVisitor.visitVarInsn(ILOAD, lokalLagring);
					} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR FEIL");
					System.out.println("------------------------------------------");
			
					System.out.println("Feil i variabelErklæring i ID");
					exit = true;
					return;
					
					}
				
				}//IDen som erklæres og som tilkalles er av samme type
					
			}//IDen har fått tildelt verdi	
			
		}//IDen finns og er i SymbolTable
		
	
	}


	
	@Override 
	public void exitID(BarnematParser.IDContext ctx){
	
		if(exit)
			return;
			
		System.out.println("Går ut or ID");
	}
	
	//Fjerna
	/*
	boolean sammenlikningSkjer = false;
	@Override 
	public void enterSanningslikning(BarnematParser.SanningslikningContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i sannings likning");
		sammenlikningSkjer = true;
		
		
	}
	
	@Override 
	public void exitSanningslikning(BarnematParser.SanningslikningContext ctx){ 
		if(exit)
			return;
			
			
		sammenlikningSkjer = false;	
		System.out.println("Går ut or sannings likning");
	}
	*/
//----------------------------------------------------------------------------------
//	-- ARITMETIKK --	
//----------------------------------------------------------------------------------

	@Override 
	public void enterPluss(BarnematParser.PlussContext ctx){ 
		if(exit)
			return;	
		System.out.println("Går inn i Pluss");
		
		if(variabelType(kretsVarType) == 3){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan ikke utføre aritmetikk, dvs. Pluss, Minus, Ganging og Deling, på setninger.");
			exit = true;
			return;
		
		}
	
	}
	
	@Override 
	public void exitPluss(BarnematParser.PlussContext ctx){ 
		if(exit)
			return;
			
		//Passe på at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(IADD);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DADD);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i pluss, de to talla som skal legges til må være av samme og riktig angitt type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("Går ut or pluss");
		
	}
	
	
	@Override 
	public void enterMinus(BarnematParser.MinusContext ctx){ 
		if(exit)
			return;	
		System.out.println("Går inn i Minus");
		
		if(variabelType(kretsVarType) == 3){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan ikke utføre aritmetikk, dvs. Pluss, Minus, Ganging og Deling, på setninger.");
			exit = true;
			return;
		
		}
	
	}
	
	@Override 
	public void exitMinus(BarnematParser.MinusContext ctx){ 
		if(exit)
			return;
			
		//Passe på at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(ISUB);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DSUB);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i minus, de to talla som skal legges til må være av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("Går ut or minus");
		
	}
	
	@Override 
	public void enterGanging(BarnematParser.GangingContext ctx){ 
		if(exit)
			return;	
		System.out.println("Går inn i Ganging");
		
		if(variabelType(kretsVarType) == 3){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan ikke utføre aritmetikk, dvs. Pluss, Minus, Ganging og Deling, på setninger.");
			exit = true;
			return;
		
		}
	
	}
	
	@Override 
	public void exitGanging(BarnematParser.GangingContext ctx){ 
		if(exit)
			return;
			
		//Passe på at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(IMUL);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DMUL);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i ganging, de to talla som skal legges til må være av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("Går ut or ganging");
		
	}
	
	@Override 
	public void enterDeling(BarnematParser.DelingContext ctx){ 
		if(exit)
			return;	
		System.out.println("Går inn i Deling");
		
		if(variabelType(kretsVarType) == 3){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan ikke utføre aritmetikk, dvs. Pluss, Minus, Ganging og Deling, på setninger.");
			exit = true;
			return;
		
		}
	
	}
	
	@Override 
	public void exitDeling(BarnematParser.DelingContext ctx){ 
		if(exit)
			return;
			
		//Passe på at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(IDIV);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DDIV);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i deling, de to talla som skal legges til må være av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("Går ut or deling");
		
	}
	
	
	public String utskriftsSetninga = "IKKE DEFINERT";
	public String utskriftsID = "IKKE DEFINERT";
	public int utskriftsType = -1;
	// 0 = setning
	// 1 = ID
	
	public boolean definert(String sjekk){
		if(sjekk.equalsIgnoreCase("IKKE DEFINERT"))
			return false;
		return true;	
	}

	@Override 
	public void enterUtskrift(BarnematParser.UtskriftContext ctx){ 
		if(exit)
			return;
		System.out.println("Går inn i utskrift");
		
		System.out.println("child count: " + ctx.getChildCount());
		if(ctx.getChildCount() != 3){
		
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil utskrift, pass på at alt er skrevet riktig.");
			exit = true;
			return;
		
		}
		
		
		
		//Bytecode
		
	
		
	}



	@Override 
	public void exitUtskrift(BarnematParser.UtskriftContext ctx){ 
		if(exit)
			return;
		
		String nøkkel = ctx.getChild(1).getText();
		
		if(nøkkel.contains("\"")){
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mainVisitor.visitLdcInsn(nøkkel.substring(1,nøkkel.length()-1));				
			mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
			
		} else {

			if(!SymbolTable.containsKey(nøkkel)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i syntaks, dette er verken ei setning eller en ID som er erklært.");
				exit = true;
				return;
			
			} 
			
			variabel lokalVar = SymbolTable.get(utskriftsID);
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("ID'en du prøver å skrive ut er ikke definert enda.");
				exit = true;
				return;
			
			}
			
			int lagre = lokalVar.minnePlassering;
			
			switch(variabelType(lokalVar.variabelType)){
				case 1:{
				//Heltall
					System.out.println("Dette har type heltall");
			
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");		
			mainVisitor.visitVarInsn(ILOAD, lagre);
			mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);		
			
		
					break;
				
				}
				case 2:{
				//Desimaltall
					System.out.println("Dette har type desimaltall");
			
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            		mainVisitor.visitVarInsn(DLOAD, lagre);
           		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);		
			
					break;
				}
				case 3:{
				//Setning
					System.out.println("Dette har type setning");
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mainVisitor.visitVarInsn(ALOAD, lagre);
			mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
					
					break;

				} 
				case 4:{
				//Sannhet
					System.out.println("Dette har type sannhet");
			
			
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            		mainVisitor.visitVarInsn(ILOAD, lagre);
           		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Z)V", false);
			
					
				}
				
				 
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
			
					System.out.println("Variabeltypen du oppga finns ikke.");
					exit = true;
					return;
				
				}
				
			}
			
		
		}
		
		
				
		System.out.println("Går ut or utskrift");
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Override
	public void enterUtskrift(BarnematParser.UtskriftContext ctx){
		if(exit)
			return;
		System.out.println("Går inn i utskrift");
		
		// 1. Sjekke om det er en ID som skal skrives ut eller om det er ei setning (SymbolTable.containsKey(id))
		
		// 2. Sjekke om verdien er satt eller ei
		
		// 3. Sjekke hvilken variabeltype slik at rikit utskriftsmetode blir brukt 
			
		
	}
	
	public String utskrift = "";
	*/
	/*
	@Override
	public void exitUtskrift(BarnematParser.UtskriftContext ctx){
		if(exit)
			return;
		
		utskrift = ctx.getChild(1).getText();
	
		//if(SymbolTable.containsKey(utskrift)){
	
		
		//mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		//mainVisitor.visitLdcInsn(ctx.getChild(1).getText());
		//mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		
		System.out.println("Går ut or utskrift");
	}
	*/
	/*
	@Override
	public void exitUtskrift(BarnematParser.UtskriftContext ctx){
		if(exit)
			return;
	
	
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn(ctx.getChild(1).getText());
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		
	}
	*/
	
	Label label1 = new Label();
	Label label2 = new Label();
	
	public boolean velgTestPlass = false;
	Scanner les = new Scanner(System.in);
	@Override 
	public void enterTestMetode(BarnematParser.TestMetodeContext ctx){ 
		
		if(exit)
			return;
		
		int testPlass = 1;
		if(velgTestPlass){
			System.out.print("Vennligst velg testPlass som skal skrives ut fra: ");
			testPlass = les.nextInt();
			les.nextLine();
			
		}
		System.out.println("Testplass: " + testPlass + "\n\n");
		
		
		
		//Skriver ei setning ut fra constantpool
		/*
		System.out.println("Gjeldende test: Skriver ut ei setning fra constantpool, (getChild(1)));
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn(ctx.getChild(1).getText());
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		*/

		//Skriver ei setning ut fra lagringsplass testplass		
		/*
		System.out.println("Gjeldende test: Skriver ut ei setning fra lagringsplass " + testPlass);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitVarInsn(ALOAD, testPlass);
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		*/
	
		//Skriver ut double fra lagringsplass testplass
		/*
		System.out.println("Gjeldende test: Skriver ut et desimaltall fra lagringsplass " + testPlass);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            	mainVisitor.visitVarInsn(Opcodes.DLOAD, testPlass);
            	mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
		*/
		
		
		
		//Skriver ut et heltall fra lagringsplass testplass
		/*
		System.out.println("Gjeldende test: Skriver ut et heltall fra lagringsplass " + testPlass);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            	mainVisitor.visitVarInsn(Opcodes.ILOAD, testPlass);
            	mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
		*/
	
		//
		/*
		mainVisitor.visitIntInsn(SIPUSH, 5);
		mainVisitor.visitIntInsn(ISTORE, 1);
		mainVisitor.visitIntInsn(SIPUSH, 6);
		mainVisitor.visitIntInsn(ISTORE, 1);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            	mainVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            	mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
		*/
		
		
		/*
		mainVisitor.visitInsn(ICONST_1);
		mainVisitor.visitJumpInsn(IFEQ, label1);
			
			
			
		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		mainVisitor.visitLdcInsn("sant");
		mainVisitor.visitJumpInsn(GOTO, label2);
			
		mainVisitor.visitLabel(label1);
		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		mainVisitor.visitLdcInsn("usant");
			
		mainVisitor.visitLabel(label2);
		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		*/
		
	Label skip;
		
	skip = label1;
		
		//mainVisitor.visitJumpInsn(IFNE, skip);
		
		System.out.println(label1);
		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		mainVisitor.visitLdcInsn("DET FUNKA JO!?");
		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		
		//mainVisitor.visitLabel(skip);
		
		
		
		
		
		/*	
	    Label skip = label1;
            Label end = label2;	
		
		mainVisitor.visitTypeInsn(NEW, "java/util/Scanner");
            mainVisitor.visitInsn(DUP);
            mainVisitor.visitFieldInsn(GETSTATIC,"java/lang/System", "in", "Ljava/io/InputStream;");
            mainVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V" , false);
            mainVisitor.visitVarInsn(ASTORE,1);

		

            mainVisitor.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mainVisitor.visitLdcInsn("Please type an integer, (Will it be equal to 0?): ");
            mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mainVisitor.visitVarInsn(ALOAD,1);
            mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
            mainVisitor.visitVarInsn(ISTORE,2);


            //mv.visitInsn(ICONST_0);
            //mv.visitVarInsn(ISTORE, 1);
            mainVisitor.visitVarInsn(ILOAD, 2);

            mainVisitor.visitJumpInsn(IFNE, skip);             //Label = 17
            mainVisitor.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mainVisitor.visitLdcInsn("Yes, input equals 0");
            mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mainVisitor.visitJumpInsn(GOTO, end);             //Label = 25

            mainVisitor.visitLabel(skip);
            mainVisitor.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mainVisitor.visitLdcInsn("No, input does not equal 0");
            mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);		
		
		*/
		
	
	}
	

	@Override public void exitTestMetode(BarnematParser.TestMetodeContext ctx){ 
		System.out.println("Går ut or test");
	}


//-----------------------------------------------------------------------------------------------------------------------------------------------	



}//end class

package compiler;

import org.antlr.v4.runtime.ParserRuleContext; // need to debug every rule
import org.objectweb.asm.*;  //classes for generating bytecode
import org.objectweb.asm.Opcodes; //Explicit import for ASM bytecode constants
import org.objectweb.asm.Label;
import static org.objectweb.asm.Opcodes.*;
import lexparse.*; //classes for lexer parser
import java.util.*;


import java.lang.*;



public class BMlistener2 extends BarnematBaseListener{

	private ClassWriter cw;  //class level ClassWriter 
	private MethodVisitor mainVisitor; //class level MethodVisitor
	private String programName; //name of the class and the output file (used by ASM)
	private boolean debug; //flag to indicate debug status


	
	public BMlistener2(String programName){
	       
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
	
	

	public void closeClass(){
		//Use global MethodVisitor to finish writing the bytecode and write the binary file.
		if(exit)
			return;
		

		
		mainVisitor.visitInsn(Opcodes.RETURN);
		mainVisitor.visitMaxs(0, 0);
		mainVisitor.visitEnd();

		cw.visitEnd();

        	byte[] b = cw.toByteArray();



        	Utilities.writeFile(b,this.programName+".class");

		System.out.println("\n\n\nKompilering fullf??rt!");
        	System.out.println("Ei fil har blitt laga i output-mappa");

	}//end closeClass
	
	
//-----------------------------------------------------------------------------------------------------------------------------------------------	
// Byrjing av tilleggsprogram
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
}//Slutt stacker-class


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
	
		int value = -1;
		if(s.head == 0){
			return value;
		} else {	
			
			value = s.stack[s.head];
			s.stack[s.head] = -1;
			s.head--;
			return value;
		}
	
	}//slutt pop
	
	
	public int peek(stacker s){
	
		if(s.head != 0)
			return s.stack[s.head];
		return -1;
	}//slutt peek
	

//-----------------------------------------------------------------------------------------------------------------------------------------------	
// Byrjing av vanlig program
//-----------------------------------------------------------------------------------------------------------------------------------------------



	public HashMap<String, variabel> SymbolTable = new HashMap<String, variabel>();
	
	
	
	public boolean exit = false;
	public int minneTeller = 2;
	
		@Override 
	public void enterFil(BarnematParser.FilContext ctx){ 
		if(exit)
			return;
		System.out.println("\nBMlistener version 2\n");
		System.out.println("G??r inn i fila for f??rste gang");
		
		setupClass();
	
	}
	@Override 
	public void exitFil(BarnematParser.FilContext ctx){
		if(exit)
			return;
					
		closeClass();
		System.out.println("forlater fila. . .");
	
	}
	
	@Override 
	public void enterHoveddel(BarnematParser.HoveddelContext ctx) {
		if(exit)
			return;

			
			//P??minner
			
			/*
			System.out.println("\n\n------------------------------------------");
			System.out.println("P??MINNELSE TIL DEG EMIL, JA DEG!");
			System.out.println("------------------------------------------");
			
			System.out.println("NESTE OPPGAVE ER UTSKRIFT, OG UTSKRIFT KAN N?? SKRIVE UT UTTRYKK S?? VI M?? FJERNE ARITMETIKK");
			System.out.println("\n\n\n Du finner forresten denne p??minninga i \"enterHoveddel\", ha en god dag videre!\n\n\n");
			exit = true;
			return;
			*/
			
	}
	@Override 
	public void exitHoveddel(BarnematParser.HoveddelContext ctx) {
		if(exit)
			return;
		//System.out.println("G??r ut or hoveddel");	
	}
	
	public boolean bes??ktArgument = false;
	public int argumentDybde = 0;
	@Override 
	public void enterArgument(BarnematParser.ArgumentContext ctx) {
		if(exit)
			return;
		bes??ktArgument = true;	
		System.out.println("G??r inn i argument");
		argumentDybde++;
		System.out.println("\n\n\nN??v??rende arg djupn: " + argumentDybde+ "\n\n\n");	
		
		
	}
	@Override 
	public void exitArgument(BarnematParser.ArgumentContext ctx) { 

		if(exit)
			return;
		
		
		if(entenDybde > 0){
				
			if(peek(entenTellerStack)> 0){
				
				if(argumentDybde == peek(entenArgDybdeStack)+1){
				
					if(peek(entenTellerStack) == 1){
						
						System.out.println("Her byrjer Ellers");	
						Label lokalLabStart;
						Label lokalLabSlutt;
						int lokaltUtfall = peek(entenDybdeStack);

							
		
							switch(lokaltUtfall){
								case 1: {
									lokalLabStart = entenStart1;
									lokalLabSlutt = entenSlutt1;
									break;	
								}
								case 2: {
									
									lokalLabStart = entenStart2;
									lokalLabSlutt = entenSlutt2;
									break;
								}
								case 3: {
									
									lokalLabStart = entenStart3;
									lokalLabSlutt = entenSlutt3;
									break;
								}
								case 4: {
				
									lokalLabStart = entenStart4;
									lokalLabSlutt = entenSlutt4;
									break;
								}
								case 5: {
								
									lokalLabStart = entenStart5;	
									lokalLabSlutt = entenSlutt5;
									break;
								}
								case 6: {
							
									lokalLabStart = entenStart6;
									lokalLabSlutt = entenSlutt6;
									break;
								}
								case 7: { 
				
									lokalLabStart = entenStart7;	
									lokalLabSlutt = entenSlutt7;
									break;
							
								}
								case 8: {
								
									lokalLabStart = entenStart8;
									lokalLabSlutt = entenSlutt8;
									break;
				
								}
								case 9: { 
					
									lokalLabStart = entenStart9;
									lokalLabSlutt = entenSlutt9;
									break;
				
								}
								case 10: { 
						
									lokalLabStart = entenStart10;
									lokalLabSlutt = entenSlutt10;
									break;
						
								}
								case 11: { 
 						
 									lokalLabStart = entenStart11;
									lokalLabSlutt = entenSlutt11;
									break;
					
								}
								case 12: { 

									lokalLabStart = entenStart12;
									lokalLabSlutt = entenSlutt12;
									break;
				
								}
								case 13: { 
					
									lokalLabStart = entenStart13;
									lokalLabSlutt = entenSlutt13;
									break;
				
								}
								case 14: { 
					
									lokalLabStart = entenStart14;
									lokalLabSlutt = entenSlutt14;
									break;
					
								}
								case 15: { 

									lokalLabStart = entenStart15;
									lokalLabSlutt = entenSlutt15;
									break;
				
								}
								case 16: { 

									lokalLabStart = entenStart16;
									lokalLabSlutt = entenSlutt16;
									break;
				
								}
								case 17: { 

									lokalLabStart = entenStart17;
									lokalLabSlutt = entenSlutt17;
									break;
				
								}
								case 18: { 

									lokalLabStart = entenStart18;
									lokalLabSlutt = entenSlutt18;
									break;
				
								}
								case 19: { 

									lokalLabStart = entenStart19;
									lokalLabSlutt = entenSlutt19;
									break;
					
								}
								case 20: { 

									lokalLabStart = entenStart20;
									lokalLabSlutt = entenSlutt20;
									break;
					
								}
								default: {
				
									System.out.println("\n\n------------------------------------------");
									System.out.println("KOMPILATOR ERROR");
									System.out.println("------------------------------------------");
				
							System.out.println("Merkelapp feil ved utgang av sammenlikning for entenEller.");
							
									exit = true;
									return;
								}		
						}
							
							
						mainVisitor.visitJumpInsn(GOTO, lokalLabSlutt);
						mainVisitor.visitLabel(lokalLabStart);
							
					}	
		
						int lokaltNyttTall = pop(entenTellerStack)-1;
						push(entenTellerStack, lokaltNyttTall);	
				}
			
			}
			
		}
		
		
		argumentDybde--;
		System.out.println("G??r ut or argument");
		
	}
	
	
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
	

	public String kretsVarType = "";
	public String kretsID = "";
	
//---------------------------------- 
//Variabel erkl??ring	
	@Override 
	public void enterVariabelErkl??ring(BarnematParser.VariabelErkl??ringContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i variabelErkl??ring");
		
	
		
		if(ctx.getChildCount() != 2 && ctx.getChildCount() != 4){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabelErkl??ring, sjekk at alt er skrevet riktig");
			exit = true;
			return;
		
		}
		if(ctx.getChildCount() == 4 && !ctx.getChild(1).getText().equals(":=")){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabelErkl??ring, sjekk at alt er skrevet riktig");
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
			
			if(variabelType(lokalVar.variabelType)== 2)
				minneTeller++;
			
			kretsID = lokalID;
			
		}
		
	}	
	@Override 
	public void exitVariabelErkl??ring(BarnematParser.VariabelErkl??ringContext ctx){ 
		if(exit)
			return;
		
		if(ctx.getChildCount() == 2 && !ctx.getChild(1).getText().equals(";")){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i variabel erkl??ring.");
			exit = true;
			return;
		
		
		}
			
		if(ctx.getChildCount() == 4){
		
		
			if(ctx.getChild(2).getText().equalsIgnoreCase("")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("Syntaksfeil i variabel erkl??ring. Mangler verdi eller ID.");
				exit = true;
				return;
			}
		
		//-----------------------------------------------------------------------------------
		// Avlusing
		//-----------------------------------------------------------------------------------
		/*
			System.out.println("child count: " + ctx.getChildCount()+ "\n");
			System.out.println("child number 2: "+ctx.getChild(2).getText());
			System.out.println("test, is ctx.getChild(2).getText() equal to \"\"?: " +ctx.getChild(2).getText().equalsIgnoreCase(""));
		
		
			System.out.println("child 1: " + ctx.getChild(1).getText());
			System.out.println("\n\n\n");
		*/	
		//-----------------------------------------------------------------------------------
		
			
			String lokalID = ctx.getChild(0).getChild(1).getText();
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
			
			//	3. lagre den i minneplasseringa if??lge riktig type
		
		} 
		

		kretsID = "IKKE DEFINERT";
		kretsVarType = "IKKE DEFINERT";
		System.out.println("G??r ut or variabel erkl??ring\n\n");	
	}
//---------------------------------- 
//Verdi tildeling	
	@Override 
	public void enterVerdiTildeling(BarnematParser.VerdiTildelingContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i variabel tildeling");
		
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
			
			System.out.println("ID " + lokalID + " er ikke erkl??rt");
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
			
			//Avlusing
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
			
			
		System.out.println("G??r ut or variabel tildeling");	
	}

//---------------------------------- 
//Variabel	
	@Override 
	public void enterVariabel(BarnematParser.VariabelContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i variabel");	
			
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
			
		System.out.println("G??r ut or variabel");	
	}
	
	
//---------------------------------- 
//Variabel type
	@Override 
	public void enterVariabeltype(BarnematParser.VariabeltypeContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i variabelType");
		
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
			
		System.out.println("G??r ut or variabelType");
	}
	
//--------------------------------------------------------------------------------------------------------------------
// 				Forskjellige variabel typer 	
//--------------------------------------------------------------------------------------------------------------------	
	
	
//Type 1 		Heltall		
	@Override 
	public void enterHeltall(BarnematParser.HeltallContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i Heltall");
		
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
			
		System.out.println("G??r ut or Heltall");
	}
	
//Type 2 		Desimaltall
	@Override 
	public void enterDesimaltall(BarnematParser.DesimaltallContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i Desimaltall");
		
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
			
		System.out.println("G??r ut or Desimaltall");
	}


//Type 3		Setning
	@Override 
	public void enterSetning(BarnematParser.SetningContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i Setning");

		
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
		String subSetninga = Setninga.substring(1,Setninga.length()-1);
		
		//Avlusing				
		System.out.println(Setninga);
		System.out.println(subSetninga);
		
		//ASM				
		mainVisitor.visitLdcInsn(subSetninga);		

	}
	@Override 
	public void exitSetning(BarnematParser.SetningContext ctx){ 
		if(exit)
			return;
			
		System.out.println("G??r ut or Setning");
		
	}	
	


//Type 4 		ID og sannhet/sanning
	
	@Override 
	public void enterID(BarnematParser.IDContext ctx){ 
		if(exit)
			return;	
		System.out.println("G??r inn i ID");
		
		String lokalID = ctx.getText();
	
		
		//Hvis kretsTypen er sannhet, s?? m?? ID'en v??re sant eller usant
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
		
		//Avlusing
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
						//Avlusing
						//setningsAlias = true;
						
					} else if(variabelType(lokalVar.variabelType) == 4){
						//Sannhet
						mainVisitor.visitVarInsn(ILOAD, lokalLagring);
						
					} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR FEIL");
					System.out.println("------------------------------------------");
			
					System.out.println("Feil i variabelErkl??ring i ID");
					exit = true;
					return;
					
					}
				
				}//IDen som erkl??res og som tilkalles er av samme type
					
			}//IDen har f??tt tildelt verdi	
			
		}//IDen finns og er i SymbolTable
		
	
	}


	
	@Override 
	public void exitID(BarnematParser.IDContext ctx){
	
		if(exit)
			return;
			
		System.out.println("G??r ut or ID");
	}	
	
//----------------------------------------------------------------------------------------	
//	         -- Aritmetikk --	
//----------------------------------------------------------------------------------------		
	
	@Override 
	public void enterPluss(BarnematParser.PlussContext ctx){ 
		if(exit)
			return;	
		System.out.println("G??r inn i Pluss");
		
		if(variabelType(kretsVarType) != 1 && variabelType(kretsVarType) != 2){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan kun utf??re aritmetikk, dvs. Pluss, Minus, Ganging og Deling, p?? heltall eller desimaltall.");
			exit = true;
			return;
		}
	}
	
	@Override 
	public void exitPluss(BarnematParser.PlussContext ctx){ 
		if(exit)
			return;
			
		//Passe p?? at riktig tall legges til
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(IADD);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DADD);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i pluss, de to talla som skal legges til m?? v??re av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
	
		System.out.println("G??r ut or pluss");
		
	}
	
	@Override 
	public void enterMinus(BarnematParser.MinusContext ctx){ 
		if(exit)
			return;	
		System.out.println("G??r inn i Minus");
		
		if(variabelType(kretsVarType) != 1 && variabelType(kretsVarType) != 2){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan kun utf??re aritmetikk, dvs. Pluss, Minus, Ganging og Deling, p?? heltall eller desimaltall.");
			exit = true;
			return;
		}
	
	}
	
	@Override 
	public void exitMinus(BarnematParser.MinusContext ctx){ 
		if(exit)
			return;
			
		//Passe p?? at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(ISUB);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DSUB);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i minus, de to talla som skal legges til m?? v??re av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("G??r ut or minus");
		
	}
	
	@Override 
	public void enterGanging(BarnematParser.GangingContext ctx){ 
		if(exit)
			return;	
		System.out.println("G??r inn i Ganging");
		
		if(variabelType(kretsVarType) != 1 && variabelType(kretsVarType) != 2){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan kun utf??re aritmetikk, dvs. Pluss, Minus, Ganging og Deling, p?? heltall eller desimaltall.");
			exit = true;
			return;
		}
	
	}
	
	@Override 
	public void exitGanging(BarnematParser.GangingContext ctx){ 
		if(exit)
			return;
			
		//Passe p?? at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(IMUL);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DMUL);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i ganging, de to talla som skal legges til m?? v??re av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("G??r ut or ganging");
		
	}
	
	@Override 
	public void enterDeling(BarnematParser.DelingContext ctx){ 
		if(exit)
			return;	
		System.out.println("G??r inn i Deling");
		
		if(variabelType(kretsVarType) != 1 && variabelType(kretsVarType) != 2){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Kan kun utf??re aritmetikk, dvs. Pluss, Minus, Ganging og Deling, p?? heltall eller desimaltall.");
			exit = true;
			return;
		}
	
	}
	
	@Override 
	public void exitDeling(BarnematParser.DelingContext ctx){ 
		if(exit)
			return;
			
		//Passe p?? at riktig tall legges til
		
		if(variabelType(kretsVarType) == 1) {
		
			mainVisitor.visitInsn(IDIV);	
		
		
		} else if(variabelType(kretsVarType) == 2){ 
		
			mainVisitor.visitInsn(DDIV);
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i deling, de to talla som skal legges til m?? v??re av samme og riktig angitt type.");
			System.out.println("Dvs. 2 heltall eller 2 desimaltall, ikke en av hver type.");
			exit = true;
			return;
		
		
		
		}	
		
		
			
		System.out.println("G??r ut or deling");
		
	}
	
//------------------------------------------------------------------------------------------------------------	
//		Uskrift
//------------------------------------------------------------------------------------------------------------

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
		System.out.println("G??r inn i utskrift");
		
		//avlusing
		System.out.println("child count: " + ctx.getChildCount());
		if(ctx.getChildCount() != 5){
		
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil utskrift, pass p?? at alt er skrevet riktig.");
			exit = true;
			return;
		
		}
		//Bytecode
	}
	


	@Override 
	public void exitUtskrift(BarnematParser.UtskriftContext ctx){ 
		if(exit)
			return;
		
		String n??kkel = ctx.getChild(3).getText();
		
		//Avlusing
		System.out.println("n??kkel: " + n??kkel);
		
		
		
		if(n??kkel.contains("\"")){
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mainVisitor.visitLdcInsn(n??kkel.substring(1,n??kkel.length()-1));				
			mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
			
		} else {

			if(!SymbolTable.containsKey(n??kkel)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i syntaks i utskrift, " +n??kkel + " er verken ei setning eller en ID som er erkl??rt.");
				exit = true;
				return;
			
			} 
			
			variabel lokalVar = SymbolTable.get(n??kkel);
			//System.out.println("verdiSatt " + lokalVar.verdiSatt);
				
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("ID'en du pr??ver ?? skrive ut er ikke definert enda.");
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
						
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            		mainVisitor.visitVarInsn(Opcodes.DLOAD, lagre);
            		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
			
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
					break;
					
				}
				
				 
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
			
					System.out.println("Variabeltypen: "+ lokalVar.variabelType +  ", som du oppga finns ikke.");
					exit = true;
					return;
				
				}
				
			}
			
		
		}
				
		System.out.println("G??r ut or utskrift");
	}	
	
	
	
	@Override public void enterSammenliknbarID(BarnematParser.SammenliknbarIDContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i ID for sammenlikning");
		
		String lokalID = ctx.getText();
		
		
		if(kretsTallTypeSammenlikn == 4){
	
			if(lokalID.equals("sant")){
				mainVisitor.visitInsn(ICONST_0);
			
			} else if(lokalID.equals("usant")){
				mainVisitor.visitInsn(ICONST_1);
			
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, for ?? sjekke sannhet m?? den andre" 
				+ " Id'en enten v??re \"sant\" eller \"usant\"");		
				exit = true;
			
			}
			
			return;
		}
			
		if(!SymbolTable.containsKey(lokalID)){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i sammenlikning, IDen du oppga: \"" + lokalID + "\" finns ikke.");
			exit = true;
			return;
		
		}
		variabel lokalVar = SymbolTable.get(lokalID);
		int lagre = lokalVar.minnePlassering;
		
		if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning, IDen du oppga: \"" 
				+ lokalID + "\" har ikke blitt tildelt en verdi enda.");
				exit = true;
				return;
			
		}
		
		if(kretsTallTypeSammenlikn == -1){
			kretsTallTypeSammenlikn = variabelType(lokalVar.variabelType);
			
		}
		if(kretsTallTypeSammenlikn == 1){
		
			if(variabelType(lokalVar.variabelType) != 1){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning, pr??ver ?? sammenlikne et desimaltall med et heltall");
				exit = true;
				return;	
			
			
			}
			mainVisitor.visitVarInsn(ILOAD, lagre);
		
		}
		if(kretsTallTypeSammenlikn == 2){
		
			if(variabelType(lokalVar.variabelType) != 2){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning, pr??ver ?? sammenlikne et heltall med et desimaltall");
				exit = true;
				return;	
			
			
			}
			mainVisitor.visitVarInsn(Opcodes.DLOAD, lagre);
			//mainVisitor.visitVarInsn(Opcodes.DLOAD, testPlass);
		
		}
		if(kretsTallTypeSammenlikn == 4){
			
			mainVisitor.visitVarInsn(ILOAD, lagre);
		
		}
		
		//System.out.println("kretstalltype: " + kretsTallType);
		
		
	}
	@Override public void exitSammenliknbarID(BarnematParser.SammenliknbarIDContext ctx){ 
		if(exit)
			return;
			
		System.out.println("G??r ut or ID for sammenlikning");	
	}

	@Override public void enterSammenliknbarDesi(BarnematParser.SammenliknbarDesiContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i Desimaltall for sammenlikning");
		
		double lokalDesimalTall;
		
		if(kretsTallTypeSammenlikn == -1){
			//Udefinert forel??pig, dvs dette er f??rst sammenliknings objekt
			
			kretsTallTypeSammenlikn = 2;
			System.out.println("\n\nctx.getText(): "+ctx.getText() + " \n\n");
		
		} else if(kretsTallTypeSammenlikn == 2){
			//Det f??rste sammenlikningsobjektet er av samme type
			System.out.println("\n\nctx.getText(): "+ctx.getText() + " \n\n");
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i sammenlikning, begge tall m?? v??re av samme type for ?? kunne sammenliknes.");
			exit = true;
			return;
		}
		
		String dub = ctx.getText().replace(",",".");
		System.out.println(dub + "\n\n");
		
		try{
            			
            				
			
			double tall = Double.valueOf(dub);
			mainVisitor.visitLdcInsn((double)tall);
			
            			
          	
       	}catch(NumberFormatException e){
       	
       		System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i sammenlikning, det som ble oppgitt er ikke et desimaltall, sjekk at formatet er riktig.");
			exit = true;
			return;

        	}
        	
		
	}
	@Override public void exitSammenliknbarDesi(BarnematParser.SammenliknbarDesiContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r ut or Desimaltall for sammenlikning");
	}

	@Override public void enterSammenliknbarHel(BarnematParser.SammenliknbarHelContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i Heltall for sammenlikning");
		
		int lokalHeltall;
		
		if(kretsTallTypeSammenlikn == -1){
			//Udefinert forel??pig, dvs dette er f??rst sammenliknings objekt
			
			kretsTallTypeSammenlikn = 1;
			System.out.println("\n\nctx.getText(): "+ctx.getText() + " \n\n");
		
		} else if(kretsTallTypeSammenlikn == 1){
			//Det f??rste sammenlikningsobjektet er av samme type
		
			System.out.println("\n\nctx.getText(): "+ctx.getText() + " \n\n");
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i sammenlikning, begge tall m?? v??re av samme type for ?? kunne sammenliknes.");
			exit = true;
			return;
		
		
		}
		try{
            			
            		lokalHeltall = Integer.valueOf(ctx.getText());
            		mainVisitor.visitIntInsn(SIPUSH, lokalHeltall);
            			
            			
       	}catch(NumberFormatException e){
       	
       		System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i sammenlikning, det som ble oppgitt er ikke et heltall, sjekk at formatet er riktig.");
			exit = true;
			return;

        	}
		
		
	}
	@Override public void exitSammenliknbarHel(BarnematParser.SammenliknbarHelContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r ut or Heltall for sammenlikning");	
	}
	

	
	


//L??kker
//----------------------------------------------------------------------------------------------------------------------------------------------


	
	//L??kke merkelapper
	Label l??kkeF??rste1 = new Label();	//1
	Label l??kkeF??rste2 = new Label();	
	Label l??kkeF??rste3 = new Label();	
	Label l??kkeF??rste4 = new Label();
	Label l??kkeF??rste5 = new Label();	//5
	Label l??kkeF??rste6 = new Label();
	Label l??kkeF??rste7 = new Label();
	Label l??kkeF??rste8 = new Label();
	Label l??kkeF??rste9 = new Label();			
	Label l??kkeF??rste10 = new Label();	//10
	Label l??kkeF??rste11 = new Label();
	Label l??kkeF??rste12 = new Label();
	Label l??kkeF??rste13 = new Label();
	Label l??kkeF??rste14 = new Label();
	Label l??kkeF??rste15 = new Label();	//15
	Label l??kkeF??rste16 = new Label();
	Label l??kkeF??rste17 = new Label();
	Label l??kkeF??rste18 = new Label();
	Label l??kkeF??rste19 = new Label();			
	Label l??kkeF??rste20 = new Label();	//20

					
	//L??kke (do-while-loop)
	stacker l??kke1Stack = new stacker();
	public final int MAKS_ANTALL_MERKELAPPER = 20;
	int l??kke1Dybde = 0;
	int l??kke1Teller = 0;
	
	@Override public void enterL??kke1(BarnematParser.L??kke1Context ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i l??kke type 1");
		
		//Sjekker at maksen ikke er n??dd
		if(l??kke1Teller >= MAKS_ANTALL_MERKELAPPER){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Du har n??dd maks antall merkelapper i l??kke type 1, kompilatoren takler ikke mer enn 20.");
				exit = true;
				return;
		
		} else {
			l??kke1Dybde++;
			l??kke1Teller++;
			push(l??kke1Stack, l??kke1Teller);
		}
		
		
		
		
		int lokalBarneOpptelling = ctx.getChildCount();
		System.out.println("F??rste barn i l??kke: "+ctx.getChild(0).getText());
		if(!ctx.getChild(0).getText().equals("Gjenta")&&!ctx.getChild(0).getText().equals("gjenta")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, l??kka burde byrje med \"Gjenta\".");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(1).getText().equals("{")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, \"Gjenta\" burde etterf??lges av \"{\".");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(lokalBarneOpptelling-5).getText().equals("}")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"{\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(lokalBarneOpptelling-4).getText().equalsIgnoreCase("Mens")&&!ctx.getChild(lokalBarneOpptelling-4).getText().equalsIgnoreCase("Medan")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"mens\" eller \"medan\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(lokalBarneOpptelling-3).getText().equals("(")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"(\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(lokalBarneOpptelling-1).getText().equals(")")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"(\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		
		//1. Riktig syntaks f??rst og fremst
		
		//2. Dybde og merkelapp forsikring
		
		Label lokalLab;
		int lokaltUtfall = peek(l??kke1Stack);
		
		switch(lokaltUtfall){
				case 1: {
					lokalLab = l??kkeF??rste1;
					break;	
				}
				case 2: {
					
					lokalLab = l??kkeF??rste2;
					break;
				}
				case 3: {
					
					lokalLab = l??kkeF??rste3;
					break;
				}
				case 4: {
					
					lokalLab = l??kkeF??rste4;
					break;
				}
				case 5: {
					
					lokalLab = l??kkeF??rste5;
					break;
				}
				case 6: {
					
					lokalLab = l??kkeF??rste6;
					break;
				}
				case 7: { 
					
					lokalLab = l??kkeF??rste7;
					break;
				
				}
				case 8: {
				
					lokalLab = l??kkeF??rste8;
					break;
				
				}
				case 9: { 
					lokalLab = l??kkeF??rste9;
					break;
				
				}
				case 10: { 
					lokalLab = l??kkeF??rste10;
					break;
				
				}
				case 11: { 
					lokalLab = l??kkeF??rste11;
					break;
				
				}
				case 12: { 
					lokalLab = l??kkeF??rste12;
					break;
				
				}
				case 13: { 
					lokalLab = l??kkeF??rste13;
					break;
				
				}
				case 14: { 
					lokalLab = l??kkeF??rste14;
					break;
				
				}
				case 15: { 
					lokalLab = l??kkeF??rste15;
					break;
				
				}
				case 16: { 
					lokalLab = l??kkeF??rste16;
					break;
				
				}
				case 17: { 
					lokalLab = l??kkeF??rste17;
					break;
				
				}
				case 18: { 
					lokalLab = l??kkeF??rste18;
					break;
				
				}
				case 19: { 
					lokalLab = l??kkeF??rste19;
					break;
				
				}
				case 20: { 
					lokalLab = l??kkeF??rste20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved inngang av l??kke type 1.");
					
					exit = true;
					return;
				}		
			}
		
		//ASM stuff
		mainVisitor.visitLabel(lokalLab);
		
		
		
		
	}
	@Override public void exitL??kke1(BarnematParser.L??kke1Context ctx){ 
		if(exit)
			return;
			
			
			
		l??kke1Dybde--;	
		pop(l??kke1Stack);
		System.out.println("G??r ut or l??kke type 1");
	}

	@Override public void enterSammenlikningL??kke1(BarnematParser.SammenlikningL??kke1Context ctx){ 
	
		if(exit)
			return;
		System.out.println("G??r inn i sammenlikning for l??kke type 1");
		
			
		kretsTallTypeSammenlikn = -1;
		int lokalBarneOpptelling = ctx.getChildCount();
		
		//Avlusing
		//System.out.println("\n\n\n barneopptelling: "+lokalBarneOpptelling+ "\n\n\n");
		
		if(lokalBarneOpptelling == 1 || lokalBarneOpptelling == 2){
			
			//Avlusing
			//System.out.println("\nkun ett barn her\n");
			String lokalID;
			if(lokalBarneOpptelling == 1){
				lokalID = ctx.getText();
			} else if(lokalBarneOpptelling == 2){
				lokalID = ctx.getChild(1).getText();
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, antall barn stemmer ikke.");
				exit = true;
				return; 
			}
			
			if(!SymbolTable.containsKey(lokalID)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga finns ikke");
				exit = true;
				return; 
			
			}
			
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(variabelType(lokalVar.variabelType) != 4){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga er av type: " 
				+ lokalVar.variabelType + ", og m?? v??re av typen Sannhet");
				exit = true;
				return; 
			}
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			//Avlusing
			//System.out.println("\n\nOk, da burde dette funke for ett enkelt barn\n\n");
			mainVisitor.visitVarInsn(ILOAD, lokalVar.minnePlassering);
					

		
		} else if(lokalBarneOpptelling == 3){
		
		
			//
			System.out.println("\nHer finns det 3 born\n");
			
			
			
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i sammenlikning, pass p?? at det enten er en ID, " 
			+ "eller ei sammenliking av to tall av samme type");
			exit = true;
			return;
		}
		
		
		
		
	
	}

	@Override public void exitSammenlikningL??kke1(BarnematParser.SammenlikningL??kke1Context ctx){ 
		if(exit)
			return;
		
		Label lokalLab;
		int lokaltUtfall = peek(l??kke1Stack);
		
		switch(lokaltUtfall){
				case 1: {
					lokalLab = l??kkeF??rste1;
					break;	
				}
				case 2: {
					
					lokalLab = l??kkeF??rste2;
					break;
				}
				case 3: {
					
					lokalLab = l??kkeF??rste3;
					break;
				}
				case 4: {
					
					lokalLab = l??kkeF??rste4;
					break;
				}
				case 5: {
					
					lokalLab = l??kkeF??rste5;
					break;
				}
				case 6: {
					
					lokalLab = l??kkeF??rste6;
					break;
				}
				case 7: { 
					
					lokalLab = l??kkeF??rste7;
					break;
				
				}
				case 8: {
				
					lokalLab = l??kkeF??rste8;
					break;
				
				}
				case 9: { 
					lokalLab = l??kkeF??rste9;
					break;
				
				}
				case 10: { 
					lokalLab = l??kkeF??rste10;
					break;
				
				}
				case 11: { 
					lokalLab = l??kkeF??rste11;
					break;
				
				}
				case 12: { 
					lokalLab = l??kkeF??rste12;
					break;
				
				}
				case 13: { 
					lokalLab = l??kkeF??rste13;
					break;
				
				}
				case 14: { 
					lokalLab = l??kkeF??rste14;
					break;
				
				}
				case 15: { 
					lokalLab = l??kkeF??rste15;
					break;
				
				}
				case 16: { 
					lokalLab = l??kkeF??rste16;
					break;
				
				}
				case 17: { 
					lokalLab = l??kkeF??rste17;
					break;
				
				}
				case 18: { 
					lokalLab = l??kkeF??rste18;
					break;
				
				}
				case 19: { 
					lokalLab = l??kkeF??rste19;
					break;
				
				}
				case 20: { 
					lokalLab = l??kkeF??rste20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved inngang av l??kke type 1.");
					
					exit = true;
					return;
				}		
			}
	
		


		int lokalBarneOpptelling = ctx.getChildCount();
		
		if(lokalBarneOpptelling == 2){	
				
          		mainVisitor.visitJumpInsn(IFEQ, lokalLab);	
          	} 
          	if(lokalBarneOpptelling == 1){
          	
          		mainVisitor.visitJumpInsn(IFNE, lokalLab);
          	
          	}
          	if(lokalBarneOpptelling == 3){
          		
          		
          		String lokalSammenlikningsTeikn = ctx.getChild(1).getText();
          		System.out.println("\n\nctx.getText(): "+ lokalSammenlikningsTeikn + " \n\n");
          		
          		
          		System.out.println("kretsTallType i utgangSammenliking: " + kretsTallTypeSammenlikn);
          		
          		if(kretsTallTypeSammenlikn == 1){
          			if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IF_ICMPLE, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IF_ICMPGE, lokalLab);
			
				} else if(lokalSammenlikningsTeikn.equals("=")){
			
					mainVisitor.visitJumpInsn(IF_ICMPEQ, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("!=")){
				
					mainVisitor.visitJumpInsn(IF_ICMPNE, lokalLab);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			} else if(kretsTallTypeSammenlikn == 2){
			
			
			
				mainVisitor.visitInsn(DCMPG);
				
				if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IFLE, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IFGE, lokalLab);
			
				} else if(lokalSammenlikningsTeikn.equals("=")){
			
					mainVisitor.visitJumpInsn(IFEQ, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("!=")){
				
					mainVisitor.visitJumpInsn(IFNE, lokalLab);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			
			
			} else if(kretsTallTypeSammenlikn == 4){
			
				if(lokalSammenlikningsTeikn.equals("=")){
			
					mainVisitor.visitJumpInsn(IFEQ, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("!=")){
				
					mainVisitor.visitJumpInsn(IFNE, lokalLab);
					
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, for ?? sammenlikne sannheter " 
					+"s?? m?? det enten v??re likhetstegn eller ulikhetstegn!");
					exit = true;
					return;
				}  	
			
			
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning ved utgang, "
				+"noe stemmer ikke helt med typene som skal sammenliknes.");
				exit = true;
				return;
			
			
			}	        		          	          
          	
          	}
			 
		System.out.println("G??r ut or sammenlikning for l??kke type 1");
	
	}

	//L??kke merkelapper
	//Start
	Label l??kkeAndreStart1 = new Label();		//1
	Label l??kkeAndreStart2 = new Label();	
	Label l??kkeAndreStart3 = new Label();	
	Label l??kkeAndreStart4 = new Label();
	Label l??kkeAndreStart5 = new Label();		//5
	Label l??kkeAndreStart6 = new Label();
	Label l??kkeAndreStart7 = new Label();
	Label l??kkeAndreStart8 = new Label();
	Label l??kkeAndreStart9 = new Label();			
	Label l??kkeAndreStart10 = new Label();	//10
	Label l??kkeAndreStart11 = new Label();
	Label l??kkeAndreStart12 = new Label();
	Label l??kkeAndreStart13 = new Label();
	Label l??kkeAndreStart14 = new Label();
	Label l??kkeAndreStart15 = new Label();	//15
	Label l??kkeAndreStart16 = new Label();
	Label l??kkeAndreStart17 = new Label();
	Label l??kkeAndreStart18 = new Label();
	Label l??kkeAndreStart19 = new Label();			
	Label l??kkeAndreStart20 = new Label();	//20
	
	//Slutt
	Label l??kkeAndreSlutt1 = new Label();		//1
	Label l??kkeAndreSlutt2 = new Label();	
	Label l??kkeAndreSlutt3 = new Label();	
	Label l??kkeAndreSlutt4 = new Label();
	Label l??kkeAndreSlutt5 = new Label();		//5
	Label l??kkeAndreSlutt6 = new Label();
	Label l??kkeAndreSlutt7 = new Label();
	Label l??kkeAndreSlutt8 = new Label();
	Label l??kkeAndreSlutt9 = new Label();			
	Label l??kkeAndreSlutt10 = new Label();	//10
	Label l??kkeAndreSlutt11 = new Label();
	Label l??kkeAndreSlutt12 = new Label();
	Label l??kkeAndreSlutt13 = new Label();
	Label l??kkeAndreSlutt14 = new Label();
	Label l??kkeAndreSlutt15 = new Label();	//15
	Label l??kkeAndreSlutt16 = new Label();
	Label l??kkeAndreSlutt17 = new Label();
	Label l??kkeAndreSlutt18 = new Label();
	Label l??kkeAndreSlutt19 = new Label();			
	Label l??kkeAndreSlutt20 = new Label();	//20

					
	//L??kke (do-while-loop)
	stacker l??kke2Stack = new stacker();
	int l??kke2Dybde = 0;
	int l??kke2Teller = 0;
	
	@Override 
	public void enterL??kke2(BarnematParser.L??kke2Context ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i l??kke type 2");
		
		//Sjekker at maksen ikke er n??dd
		if(l??kke2Teller >= MAKS_ANTALL_MERKELAPPER){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Du har n??dd maks antall merkelapper i l??kke type 2, kompilatoren takler ikke mer enn 20.");
				exit = true;
				return;
		
		} else {
			l??kke2Dybde++;
			l??kke2Teller++;
			push(l??kke2Stack, l??kke2Teller);
		}
		
		
		
		int lokalBarneOpptelling = ctx.getChildCount();
		System.out.println("F??rste barn i l??kke: "+ctx.getChild(0).getText());
		if(!ctx.getChild(1).getText().equals("(")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
			System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, \"(\" burde etterf??lge \"Medan\" eller \"Mens\".");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(3).getText().equals(")")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, \"Gjenta\" burde etterf??lges av \"{\".");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(4).getText().equalsIgnoreCase("Gjenta")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"{\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		
		if(!ctx.getChild(5).getText().equals("{")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"(\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(lokalBarneOpptelling-1).getText().equals("}")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i l??kka. Pass p?? at syntaksen er riktig, pass p?? at \"(\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		
		
		Label lokalLab;
		int lokaltUtfall = peek(l??kke2Stack);
		
		switch(lokaltUtfall){
				case 1: {
					lokalLab = l??kkeAndreStart1;
					break;	
				}
				case 2: {
					
					lokalLab = l??kkeAndreStart2;
					break;
				}
				case 3: {
					
					lokalLab = l??kkeAndreStart3;
					break;
				}
				case 4: {
					
					lokalLab = l??kkeAndreStart4;
					break;
				}
				case 5: {
					
					lokalLab = l??kkeAndreStart5;
					break;
				}
				case 6: {
					
					lokalLab = l??kkeAndreStart6;
					break;
				}
				case 7: { 
					
					lokalLab = l??kkeAndreStart7;
					break;
				
				}
				case 8: {
				
					lokalLab = l??kkeAndreStart8;
					break;
				
				}
				case 9: { 
					lokalLab = l??kkeAndreStart9;
					break;
				
				}
				case 10: { 
					lokalLab = l??kkeAndreStart10;
					break;
				
				}
				case 11: { 
					lokalLab = l??kkeAndreStart11;
					break;
				
				}
				case 12: { 
					lokalLab = l??kkeAndreStart12;
					break;
				
				}
				case 13: { 
					lokalLab = l??kkeAndreStart13;
					break;
				
				}
				case 14: { 
					lokalLab = l??kkeAndreStart14;
					break;
				
				}
				case 15: { 
					lokalLab = l??kkeAndreStart15;
					break;
				
				}
				case 16: { 
					lokalLab = l??kkeAndreStart16;
					break;
				
				}
				case 17: { 
					lokalLab = l??kkeAndreStart17;
					break;
				
				}
				case 18: { 
					lokalLab = l??kkeAndreStart18;
					break;
				
				}
				case 19: { 
					lokalLab = l??kkeAndreStart19;
					break;
				
				}
				case 20: { 
					lokalLab = l??kkeAndreStart20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved inngang av l??kke type 2.");
					
					exit = true;
					return;
				}		
		}
		
		mainVisitor.visitLabel(lokalLab);
		
	}

	@Override 
	public void exitL??kke2(BarnematParser.L??kke2Context ctx){ 
		if(exit)
			return;
			
		Label lokalLabStart;
		Label lokalLabSlutt;
		
		int lokaltUtfall = peek(l??kke2Stack);
		//Avlusing
		//System.out.println("lokaltutfall: " + lokaltUtfall);
		
		switch(lokaltUtfall){
				case 1: {
					lokalLabStart = l??kkeAndreStart1;
					lokalLabSlutt = l??kkeAndreSlutt1;
					break;	
				}
				case 2: {
					
					lokalLabStart = l??kkeAndreStart2;
					lokalLabSlutt = l??kkeAndreSlutt2;
					break;
				}
				case 3: {
					
					lokalLabStart = l??kkeAndreStart3;
					lokalLabSlutt = l??kkeAndreSlutt3;
					break;
				}
				case 4: {
					
					lokalLabStart = l??kkeAndreStart4;
					lokalLabSlutt = l??kkeAndreSlutt4;
					break;
				}
				case 5: {
					
					lokalLabStart = l??kkeAndreStart5;
					lokalLabSlutt = l??kkeAndreSlutt5;
					break;
				}
				case 6: {
					
					lokalLabStart = l??kkeAndreStart6;
					lokalLabSlutt = l??kkeAndreSlutt6;
					break;
				}
				case 7: { 
					
					lokalLabStart = l??kkeAndreStart7;
					lokalLabSlutt = l??kkeAndreSlutt7;
					break;
				
				}
				case 8: {
				
					lokalLabStart = l??kkeAndreStart8;
					lokalLabSlutt = l??kkeAndreSlutt8;
					break;
				
				}
				case 9: { 
					lokalLabStart = l??kkeAndreStart9;
					lokalLabSlutt = l??kkeAndreSlutt9;
					break;
				
				}
				case 10: { 
					lokalLabStart = l??kkeAndreStart10;
					lokalLabSlutt = l??kkeAndreSlutt10;
					break;
				
				}
				case 11: { 
					lokalLabStart = l??kkeAndreStart11;
					lokalLabSlutt = l??kkeAndreSlutt11;
					break;
				
				}
				case 12: { 
					lokalLabStart = l??kkeAndreStart12;
					lokalLabSlutt = l??kkeAndreSlutt12;
					break;
				
				}
				case 13: { 
					lokalLabStart = l??kkeAndreStart13;
					lokalLabSlutt = l??kkeAndreSlutt13;
					break;
				
				}
				case 14: { 
					lokalLabStart = l??kkeAndreStart14;
					lokalLabSlutt = l??kkeAndreSlutt14;
					break;
				
				}
				case 15: { 
					lokalLabStart = l??kkeAndreStart15;
					lokalLabSlutt = l??kkeAndreSlutt15;
					break;
				
				}
				case 16: { 
					lokalLabStart = l??kkeAndreStart16;
					lokalLabSlutt = l??kkeAndreSlutt16;
					break;
				
				}
				case 17: { 
					lokalLabStart = l??kkeAndreStart17;
					lokalLabSlutt = l??kkeAndreSlutt17;
					break;
				
				}
				case 18: { 
					lokalLabStart = l??kkeAndreStart18;
					lokalLabSlutt = l??kkeAndreSlutt18;
					break;
				
				}
				case 19: { 
					lokalLabStart = l??kkeAndreStart19;
					lokalLabSlutt = l??kkeAndreSlutt19;
					break;
				
				}
				case 20: { 
					lokalLabStart = l??kkeAndreStart20;
					lokalLabSlutt = l??kkeAndreSlutt20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved inngang av l??kke type 2.");
					
					exit = true;
					return;
				}		
		}
		
		mainVisitor.visitJumpInsn(GOTO, lokalLabStart);
		mainVisitor.visitLabel(lokalLabSlutt);	
			
		l??kke2Dybde--;	
		pop(l??kke2Stack);	
		System.out.println("G??r ut or l??kke type 2");
	}
	
	
	@Override 
	public void enterSammenlikningL??kke2(BarnematParser.SammenlikningL??kke2Context ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i sammenlikning for l??kke type 2");
		
			
		kretsTallTypeSammenlikn = -1;
		int lokalBarneOpptelling = ctx.getChildCount();
		
		
		if(lokalBarneOpptelling == 1 || lokalBarneOpptelling == 2){
		
			String lokalID;
			if(lokalBarneOpptelling == 1){
				lokalID = ctx.getText();
			} else if(lokalBarneOpptelling == 2){
				lokalID = ctx.getChild(1).getText();
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, antall barn stemmer ikke.");
				exit = true;
				return; 
			}
			
			if(!SymbolTable.containsKey(lokalID)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga finns ikke");
				exit = true;
				return; 
			
			}
			
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(variabelType(lokalVar.variabelType) != 4){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga er av type: " 
				+ lokalVar.variabelType + ", og m?? v??re av typen Sannhet");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			//Avlusing
			//System.out.println("\n\nOk, da burde dette funke for ett enkelt barn\n\n");
			mainVisitor.visitVarInsn(ILOAD, lokalVar.minnePlassering);
					

		
		} else if(lokalBarneOpptelling == 3){
		
		
			//
			System.out.println("\nHer finns det 3 born\n");
			
			
			
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i sammenlikning, pass p?? at det enten er en ID, " 
			+ "eller ei sammenliking av to tall av samme type");
			exit = true;
			return;
		}
		
		
		
		
	
	}	
		
		
	@Override 
	public void exitSammenlikningL??kke2(BarnematParser.SammenlikningL??kke2Context ctx){ 
	
		if(exit)
			return;
		

		Label lokalLabSlutt;
		int lokaltUtfall = peek(l??kke2Stack);

		
		switch(lokaltUtfall){
				case 1: {
					lokalLabSlutt = l??kkeAndreSlutt1;
					break;	
				}
				case 2: {
					

					lokalLabSlutt = l??kkeAndreSlutt2;
					break;
				}
				case 3: {
					

					lokalLabSlutt = l??kkeAndreSlutt3;
					break;
				}
				case 4: {
					
					lokalLabSlutt = l??kkeAndreSlutt4;
					break;
				}
				case 5: {
					
					lokalLabSlutt = l??kkeAndreSlutt5;
					break;
				}
				case 6: {
				
					lokalLabSlutt = l??kkeAndreSlutt6;
					break;
				}
				case 7: { 
					
					lokalLabSlutt = l??kkeAndreSlutt7;
					break;
				
				}
				case 8: {
				
					lokalLabSlutt = l??kkeAndreSlutt8;
					break;
				
				}
				case 9: { 
					lokalLabSlutt = l??kkeAndreSlutt9;
					break;
				
				}
				case 10: { 

					lokalLabSlutt = l??kkeAndreSlutt10;
					break;
				
				}
				case 11: { 

					lokalLabSlutt = l??kkeAndreSlutt11;
					break;
				
				}
				case 12: { 

					lokalLabSlutt = l??kkeAndreSlutt12;
					break;
				
				}
				case 13: { 

					lokalLabSlutt = l??kkeAndreSlutt13;
					break;
				
				}
				case 14: { 

					lokalLabSlutt = l??kkeAndreSlutt14;
					break;
				
				}
				case 15: { 

					lokalLabSlutt = l??kkeAndreSlutt15;
					break;
				
				}
				case 16: { 

					lokalLabSlutt = l??kkeAndreSlutt16;
					break;
				
				}
				case 17: { 

					lokalLabSlutt = l??kkeAndreSlutt17;
					break;
				
				}
				case 18: { 

					lokalLabSlutt = l??kkeAndreSlutt18;
					break;
				
				}
				case 19: { 

					lokalLabSlutt = l??kkeAndreSlutt19;
					break;
				
				}
				case 20: { 

					lokalLabSlutt = l??kkeAndreSlutt20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved inngang av l??kke type 2.");
					
					exit = true;
					return;
				}		
		}
		
		
		
		int lokalBarneOpptelling = ctx.getChildCount();
		
		if(lokalBarneOpptelling == 1){	
				
          		mainVisitor.visitJumpInsn(IFEQ, lokalLabSlutt);	
          	} 
          	if(lokalBarneOpptelling == 2){
          	
          		mainVisitor.visitJumpInsn(IFNE, lokalLabSlutt);
          	
          	}
          	if(lokalBarneOpptelling == 3){
          		
          		
          		String lokalSammenlikningsTeikn = ctx.getChild(1).getText();
          		System.out.println("\n\nctx.getText(): "+ lokalSammenlikningsTeikn + " \n\n");
          		
          		
          		System.out.println("kretsTallType i utgangSammenliking: " + kretsTallTypeSammenlikn);
          		
          		if(kretsTallTypeSammenlikn == 1){
          			if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IF_ICMPLE, lokalLabSlutt);
				
				} else if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IF_ICMPGE, lokalLabSlutt);
			
				} else if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IF_ICMPEQ, lokalLabSlutt);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IF_ICMPNE, lokalLabSlutt);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			} else if(kretsTallTypeSammenlikn == 2){
			
			
			
				mainVisitor.visitInsn(DCMPG);
				
				if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IFLE, lokalLabSlutt);
				
				} else if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IFGE, lokalLabSlutt);
			
				} else if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IFEQ, lokalLabSlutt);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IFNE, lokalLabSlutt);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			
			
			} else if(kretsTallTypeSammenlikn == 4){
			
				if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IFEQ, lokalLabSlutt);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IFNE, lokalLabSlutt);
					
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, for ?? sammenlikne sannheter " 
					+"s?? m?? det enten v??re likhetstegn eller ulikhetstegn!");
					exit = true;
					return;
				}  	
			
			
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning ved utgang, "
				+"noe stemmer ikke helt med typene som skal sammenliknes.");
				exit = true;
				return;
			
			
			}	        		          	          
          	
          	}
			 
		System.out.println("G??r ut or sammenlikning for l??kke type 2");
	
	}

//----------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------







//----------------------------------------------------------------------------------------
//		-- uferdig --
//----------------------------------------------------------------------------------------

	//Enten merkelapper
	//Start
	Label entenStart1 = new Label();	//1
	Label entenStart2 = new Label();	
	Label entenStart3 = new Label();	
	Label entenStart4 = new Label();
	Label entenStart5 = new Label();	//5
	Label entenStart6 = new Label();
	Label entenStart7 = new Label();
	Label entenStart8 = new Label();
	Label entenStart9 = new Label();			
	Label entenStart10 = new Label();	//10
	Label entenStart11 = new Label();
	Label entenStart12 = new Label();
	Label entenStart13 = new Label();
	Label entenStart14 = new Label();
	Label entenStart15 = new Label();	//15
	Label entenStart16 = new Label();
	Label entenStart17 = new Label();
	Label entenStart18 = new Label();
	Label entenStart19 = new Label();			
	Label entenStart20 = new Label();	//20
	
	//Slutt
	Label entenSlutt1 = new Label();	//1
	Label entenSlutt2 = new Label();	
	Label entenSlutt3 = new Label();	
	Label entenSlutt4 = new Label();
	Label entenSlutt5 = new Label();	//5
	Label entenSlutt6 = new Label();
	Label entenSlutt7 = new Label();
	Label entenSlutt8 = new Label();
	Label entenSlutt9 = new Label();			
	Label entenSlutt10 = new Label();	//10
	Label entenSlutt11 = new Label();
	Label entenSlutt12 = new Label();
	Label entenSlutt13 = new Label();
	Label entenSlutt14 = new Label();
	Label entenSlutt15 = new Label();	//15
	Label entenSlutt16 = new Label();
	Label entenSlutt17 = new Label();
	Label entenSlutt18 = new Label();
	Label entenSlutt19 = new Label();			
	Label entenSlutt20 = new Label();	//20

					
	//L??kke (do-while-loop)
	stacker entenDybdeStack = new stacker();
	stacker entenTellerStack = new stacker();
	stacker entenArgDybdeStack = new stacker();
	int entenDybde = 0;
	int entenTeller = 0;
	
	//Test label
	Label testStempelHvis = new Label();
	
	//int kretsTilfelleSammenlikning = -1;
	int kretsTallTypeSammenlikn = -1;
	//M?? muligens endre denne til stacker!
	
	
	@Override 
	public void enterSetningsSammenlikningEnten(BarnematParser.SetningsSammenlikningEntenContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i setningsSammenlikning for enten");	
				
		/*
		 	1. Sjekke syntaks
		 	
		 	2. Switch
		 	
		 	3. peek entenTellerStack (enten slutt eller midt)
			
			4. sjekke barn 1
				Id eller setning
				erkl??rt
				Angitt verdi
			
			5. sjekke barn 2
				Id eller setning
				erkl??rt
				Angitt verdi
			
			6. sjekke sammenlikninga
		
		*/
		
		int lokalBarneOpptelling = ctx.getChildCount();
		
		if(lokalBarneOpptelling != 3){
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");		
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i setingssammenlikning for enten eller, syntaksen er feil.");
			exit = true;
			return; 

		}
		//Avlusing
		System.out.println("Barn 1: " + ctx.getChild(0).getText());
		System.out.println("Barn 2: "+ ctx.getChild(1).getText());
		System.out.println("Barn 3: "+ ctx.getChild(2).getText());
		
	
		String lokaltBarn1 = ctx.getChild(0).getText();
		String lokaltBarn2 = ctx.getChild(2).getText();
		//String lokalSammenlikn = ctx.getChild(1).getText();
		
		if(lokaltBarn1.contains("\"")){
			
			String subSetninga1 = lokaltBarn1.substring(1,lokaltBarn1.length()-1);
			mainVisitor.visitLdcInsn(subSetninga1);
			
		} else {
			if(!SymbolTable.containsKey(lokaltBarn1)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i setningssammenlikning, \""+ lokaltBarn1 + "\" finns ikke");
				exit = true;
				return;
			
			}
			
			variabel lokalVar1 = SymbolTable.get(lokaltBarn1);
		
			if(variabelType(lokalVar1.variabelType) != 3){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i setningssammenlikning, ID'en du oppga er av type: " 
				+ lokalVar1.variabelType + ", og m?? v??re av typen Setning");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar1.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i setningssammenlikning, \"" 
				+ lokalVar1 + "\" har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
				
			mainVisitor.visitVarInsn(ALOAD, lokalVar1.minnePlassering);	
		}
		
		if(lokaltBarn2.contains("\"")){
			
			String subSetninga2 = lokaltBarn2.substring(1,lokaltBarn2.length()-1);
			mainVisitor.visitLdcInsn(subSetninga2);
			
		} else {
			if(!SymbolTable.containsKey(lokaltBarn2)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i setningssammenlikning, \""+ lokaltBarn2 +"\" finns ikke");
				exit = true;
				return;
			
			}
			variabel lokalVar2 = SymbolTable.get(lokaltBarn2);
			
			if(variabelType(lokalVar2.variabelType) != 3){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i setningssammenlikning, ID'en du oppga er av type: " 
				+ lokalVar2.variabelType + ", og m?? v??re av typen Setning");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar2.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i setningssammenlikning, \"" 
				+ lokalVar2 + "\" har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			mainVisitor.visitVarInsn(ALOAD, lokalVar2.minnePlassering);	
		}
		
	}

	@Override 
	public void exitSetningsSammenlikningEnten(BarnematParser.SetningsSammenlikningEntenContext ctx){ 
		if(exit) 	
			return;
	
		Label lokalLabStart;
		Label lokalLabSlutt;
		Label lokalLab;
		int lokaltUtfall = peek(entenDybdeStack);

		
		switch(lokaltUtfall){
				case 1: {
					lokalLabStart = entenStart1;
					lokalLabSlutt = entenSlutt1;
					break;	
				}
				case 2: {
					
					lokalLabStart = entenStart2;
					lokalLabSlutt = entenSlutt2;
					break;
				}
				case 3: {
					
					lokalLabStart = entenStart3;
					lokalLabSlutt = entenSlutt3;
					break;
				}
				case 4: {
				
					lokalLabStart = entenStart4;
					lokalLabSlutt = entenSlutt4;
					break;
				}
				case 5: {
				
					lokalLabStart = entenStart5;	
					lokalLabSlutt = entenSlutt5;
					break;
				}
				case 6: {
				
					lokalLabStart = entenStart6;
					lokalLabSlutt = entenSlutt6;
					break;
				}
				case 7: { 
				
					lokalLabStart = entenStart7;	
					lokalLabSlutt = entenSlutt7;
					break;
				
				}
				case 8: {
				
					lokalLabStart = entenStart8;
					lokalLabSlutt = entenSlutt8;
					break;
				
				}
				case 9: { 
				
					lokalLabStart = entenStart9;
					lokalLabSlutt = entenSlutt9;
					break;
				
				}
				case 10: { 
					
					lokalLabStart = entenStart10;
					lokalLabSlutt = entenSlutt10;
					break;
				
				}
				case 11: { 
 					
 					lokalLabStart = entenStart11;
					lokalLabSlutt = entenSlutt11;
					break;
				
				}
				case 12: { 

					lokalLabStart = entenStart12;
					lokalLabSlutt = entenSlutt12;
					break;
				
				}
				case 13: { 
				
					lokalLabStart = entenStart13;
					lokalLabSlutt = entenSlutt13;
					break;
				
				}
				case 14: { 
					
					lokalLabStart = entenStart14;
					lokalLabSlutt = entenSlutt14;
					break;
				
				}
				case 15: { 

					lokalLabStart = entenStart15;
					lokalLabSlutt = entenSlutt15;
					break;
				
				}
				case 16: { 

					lokalLabStart = entenStart16;
					lokalLabSlutt = entenSlutt16;
					break;
				
				}
				case 17: { 

					lokalLabStart = entenStart17;
					lokalLabSlutt = entenSlutt17;
					break;
				
				}
				case 18: { 

					lokalLabStart = entenStart18;
					lokalLabSlutt = entenSlutt18;
					break;
				
				}
				case 19: { 

					lokalLabStart = entenStart19;
					lokalLabSlutt = entenSlutt19;
					break;
				
				}
				case 20: { 

					lokalLabStart = entenStart20;
					lokalLabSlutt = entenSlutt20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved utgang av sammenlikning for entenEller.");
					
					exit = true;
					return;
				}		
		}
		
		
		
		
		System.out.println("peek : " + peek(entenTellerStack));
		
		if(peek(entenTellerStack)> 0){
			lokalLab = lokalLabStart;
			System.out.println("\n\nHer m?? vi ta h??gd for ellers!!\n\n");
		
		} else {
			lokalLab = lokalLabSlutt;
			System.out.println("\n\nHer finns det ikke ellers\n\n");
		}
	
		String lokalSammenlikn = ctx.getChild(1).getText();
	
	//INVOKE VIRTUAL
	//Avlusing
	/*
	System.out.println("Trenger invokeVirtual");
	exit = true;
	return;
	*/
	
	
	//invokevirtual #3                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
	mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String",  "equals", "(Ljava/lang/Object;)Z", false);
	
	
	//invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
	//mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
	
		if(lokalSammenlikn.equals("!=")){	
	
          		mainVisitor.visitJumpInsn(IFEQ, lokalLab);	
          	} else if(lokalSammenlikn.equals("=")){
          	
          		mainVisitor.visitJumpInsn(IFNE, lokalLab);
          	
          	} else {
          		
          		System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil ved sammenliknings uttrykk i setningssammenlikning.");
					
			exit = true;
			return;
          	
          	}
		
	
	
		System.out.println("G??r ut or setningsSammenlikning for enten");	
	}
	
	
	//Sammenlikning
	@Override public void enterSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx){
		if(exit)
			return;
			
		System.out.println("G??r inn i sammenlikning for enten eller");
	
			
		kretsTallTypeSammenlikn = -1;
		int lokalBarneOpptelling = ctx.getChildCount();
		
		//Avlusing
		System.out.println("\n\n\n barneopptelling: "+lokalBarneOpptelling+ "\n\n\n");
		
		if(lokalBarneOpptelling == 1 ){
			
			System.out.println("\nkun ett barn her\n");
			String lokalID = ctx.getText();
	
			if(!SymbolTable.containsKey(lokalID)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga finns ikke");
				exit = true;
				return; 
			
			}
			
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(variabelType(lokalVar.variabelType) != 4){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga er av type: " 
				+ lokalVar.variabelType + ", og m?? v??re av typen Sannhet");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			//Mangler ?? hente sanninga
			System.out.println("\n\nOk, da burde dette funke for ett enkelt barn\n\n");
			mainVisitor.visitVarInsn(ILOAD, lokalVar.minnePlassering);
					

		
		} else if(lokalBarneOpptelling == 2){
		
		
			//
			System.out.println("\nHer finns det 2 born\n");
			String lokalID = ctx.getChild(1).getText();

			if(!SymbolTable.containsKey(lokalID)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga finns ikke");
				exit = true;
				return; 
			
			}
			
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(variabelType(lokalVar.variabelType) != 4){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga er av type: " 
				+ lokalVar.variabelType + ", og m?? v??re av typen Sannhet");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			//Mangler ?? hente sanninga
			System.out.println("\n\nOk, da burde dette funke for ett enkelt barn\n\n");
			mainVisitor.visitVarInsn(ILOAD, lokalVar.minnePlassering);
			
			
			
		
		} else if(lokalBarneOpptelling == 3){
		
		
			//
			System.out.println("\nHer finns det 3 born\n");
			
			
			
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i sammenlikning, pass p?? at det enten er en ID, " 
			+ "eller ei sammenliking av to tall av samme type");
			exit = true;
			return;
		}
		
		
		
		
		
		
	}
	@Override public void exitSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx){
		if(exit)
			return;
		
		//Avlusing	
		//System.out.println("FIKS SAMMENLIKNING!!!!!!");
		//exit = true;	
			
		int lokalBarneOpptelling = ctx.getChildCount();
		
		Label lokalLabStart;
		Label lokalLabSlutt;
		Label lokalLab;
		int lokaltUtfall = peek(entenDybdeStack);

		
		switch(lokaltUtfall){
				case 1: {
					lokalLabStart = entenStart1;
					lokalLabSlutt = entenSlutt1;
					break;	
				}
				case 2: {
					
					lokalLabStart = entenStart2;
					lokalLabSlutt = entenSlutt2;
					break;
				}
				case 3: {
					
					lokalLabStart = entenStart3;
					lokalLabSlutt = entenSlutt3;
					break;
				}
				case 4: {
				
					lokalLabStart = entenStart4;
					lokalLabSlutt = entenSlutt4;
					break;
				}
				case 5: {
				
					lokalLabStart = entenStart5;	
					lokalLabSlutt = entenSlutt5;
					break;
				}
				case 6: {
				
					lokalLabStart = entenStart6;
					lokalLabSlutt = entenSlutt6;
					break;
				}
				case 7: { 
				
					lokalLabStart = entenStart7;	
					lokalLabSlutt = entenSlutt7;
					break;
				
				}
				case 8: {
				
					lokalLabStart = entenStart8;
					lokalLabSlutt = entenSlutt8;
					break;
				
				}
				case 9: { 
				
					lokalLabStart = entenStart9;
					lokalLabSlutt = entenSlutt9;
					break;
				
				}
				case 10: { 
					
					lokalLabStart = entenStart10;
					lokalLabSlutt = entenSlutt10;
					break;
				
				}
				case 11: { 
 					
 					lokalLabStart = entenStart11;
					lokalLabSlutt = entenSlutt11;
					break;
				
				}
				case 12: { 

					lokalLabStart = entenStart12;
					lokalLabSlutt = entenSlutt12;
					break;
				
				}
				case 13: { 
				
					lokalLabStart = entenStart13;
					lokalLabSlutt = entenSlutt13;
					break;
				
				}
				case 14: { 
					
					lokalLabStart = entenStart14;
					lokalLabSlutt = entenSlutt14;
					break;
				
				}
				case 15: { 

					lokalLabStart = entenStart15;
					lokalLabSlutt = entenSlutt15;
					break;
				
				}
				case 16: { 

					lokalLabStart = entenStart16;
					lokalLabSlutt = entenSlutt16;
					break;
				
				}
				case 17: { 

					lokalLabStart = entenStart17;
					lokalLabSlutt = entenSlutt17;
					break;
				
				}
				case 18: { 

					lokalLabStart = entenStart18;
					lokalLabSlutt = entenSlutt18;
					break;
				
				}
				case 19: { 

					lokalLabStart = entenStart19;
					lokalLabSlutt = entenSlutt19;
					break;
				
				}
				case 20: { 

					lokalLabStart = entenStart20;
					lokalLabSlutt = entenSlutt20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved utgang av sammenlikning for entenEller.");
					
					exit = true;
					return;
				}		
		}
		
		
		
		
		System.out.println("peek : " + peek(entenTellerStack));
		
		if(peek(entenTellerStack)> 0){
		
			lokalLab = lokalLabStart;
			System.out.println("\n\nHer m?? vi ta h??gd for ellers!!\n\n");
		
		} else {
			lokalLab = lokalLabSlutt;
			System.out.println("\n\nHer finns det ikke ellers\n\n");
		}
		
		
		//M?? FIKSES HER!
		//lokalLab = lokalLabSlutt;
		
		
		
		
		if(lokalBarneOpptelling == 1){	
			
				
          		mainVisitor.visitJumpInsn(IFEQ, lokalLab);	
          	} 
          	if(lokalBarneOpptelling == 2){
          	
          		mainVisitor.visitJumpInsn(IFNE, lokalLab);
          	
          	}
          	if(lokalBarneOpptelling == 3){
          		
          		
          		String lokalSammenlikningsTeikn = ctx.getChild(1).getText();
          		System.out.println("\n\nctx.getText(): "+ lokalSammenlikningsTeikn + " \n\n");
          		
          		
          		System.out.println("kretsTallType i utgangSammenliking: " + kretsTallTypeSammenlikn);
          		
          		if(kretsTallTypeSammenlikn == 1){
          			if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IF_ICMPLE, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IF_ICMPGE, lokalLab);
			
				} else if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IF_ICMPEQ, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IF_ICMPNE, lokalLab);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			} else if(kretsTallTypeSammenlikn == 2){
			
			
			
				mainVisitor.visitInsn(DCMPG);
				
				if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IFLE, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IFGE, lokalLab);
			
				} else if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IFEQ, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IFNE, lokalLab);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			
			
			} else if(kretsTallTypeSammenlikn == 4){
			
				if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IFEQ, lokalLab);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IFNE, lokalLab);
					
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, for ?? sammenlikne sannheter " 
					+"s?? m?? det enten v??re likhetstegn eller ulikhetstegn!");
					exit = true;
					return;
				}  	
			
			
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning ved utgang, "
				+"noe stemmer ikke helt med typene som skal sammenliknes.");
				exit = true;
				return;
			
			
			}	        		          	          
          	
          	}
			 
		System.out.println("G??r ut or sammenlikning for enten eller");
	}

		
//-------------------------------------------------------------------------------------------------------------------------------------------		
		//Kommenter ut alt under
/*
		//System.out.println("\t- ");
		System.out.println("------------------------------------------------------------------");
		System.out.println("\t- Finne ut om en mer effektiv m??te ?? bruke merkelapper(label) p??, "
		+ "til d??mes: \n\t\t* objekt med innhold, \n\t\t* array, \n\t\t* eller bare lage en haug med merkelapper og switch ");
		System.out.println("\t- Legge til argument telling for dersom alts?? if1, if2, osv.");
		System.out.println("\t- Legge til merkelapper i argument utgang og telling i argument, muligens ha alt i argument?");
		System.out.println("\t- Legge til djupn/dybde for ?? kunne ha leira-l??kker og entenEller uttrykk. ");
		
		exit = true;
		System.out.println("\n\n\t dette kan fjernes i \"enterEntenEller\"");
		System.out.println("------------------------------------------------------------------");
		return;

		//Sjekke at syntaksen stemmer
		
		//telle antall if-argument + dypere entenEller og l??kker
*/		
//-------------------------------------------------------------------------------------------------------------------------------------------
	
	//EntenEller (if-else)
	@Override public void enterEntenEller(BarnematParser.EntenEllerContext ctx){
		if(exit)
			return;
		System.out.println("G??r inn i EntenEller");
		
		if(entenTeller >= MAKS_ANTALL_MERKELAPPER){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Du har n??dd maks antall merkelapper i l??kke type 2, kompilatoren takler ikke mer enn 20.");
				exit = true;
				return;
		
		} else {
			entenDybde++;
			entenTeller++;
			push(entenDybdeStack, entenTeller);
			push(entenArgDybdeStack, argumentDybde);
		}
		
		/*
			D??me: 
			entenEller greier i argument
			if(entenDybde > 0){
				
				if(peek(entenTellerStack)> 0){
				
					if(argumentDybde == peek(entenArgDybdeStack)+1){
				
						if(peek(entenTellerStack) == 1){
							
							switch(){
							
							
							
							}
							
							
							mainVisitor.visitJumpInsn(GOTO, lokalLabSlutt);
							mainVisitor.visitLabel(lokalLabStart);
							
						
						
						}
						int lokalNyttTall = pop(entenTellerStack)-1;
						push(entenTellerStack, lokalNyttTall);	
				
				
			
					}
			
				}
			
			}
		
		
		*/
		
		
		int lokalBarneOpptelling = ctx.getChildCount();
		
		
		if(!ctx.getChild(1).getText().equals("(")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
			System.out.println("Feil i Enten/Eller. Pass p?? at syntaksen er riktig, \"(\" burde etterf??lge \"Dersom\".");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(3).getText().equals(")")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i Enten/Eller. Pass p?? at syntaksen er riktig, \")\" m?? v??re p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(4).getText().equals("s??")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i Enten/Eller. Sjekk om syntaksen er riktig, pass p?? at \")\" etterf??lges av \"s??\"");
				exit = true;
				return;
			
		
		}
		
		if(!ctx.getChild(5).getText().equals("{")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i Enten/Eller. Sjekk om syntaksen er riktig, pass p?? at \"{\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		if(ctx.getChild(6).getText().equals("}")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i Enten/eller. Tomt enten/eller-uttrykk, legg til et argument i uttrykket.");
				exit = true;
				return;
			
		
		}
		if(!ctx.getChild(lokalBarneOpptelling-1).getText().equals("}")){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i Enten/eller. Sjekk om syntaksen er riktig, pass p?? at \"}\" er p?? riktig plass.");
				exit = true;
				return;
			
		
		}
		
		//Hvis-teller'n
		int hvisTellern = 6;
		int antallHvis = 0;
		boolean lokalFortsett = true;
		boolean lokalEllersFinns = false;
		boolean lokalForrige = false;
		String lokalStreng = "";
		
		while(hvisTellern < (lokalBarneOpptelling -1)&&lokalFortsett){
		
			lokalStreng = ctx.getChild(hvisTellern).getText();
			
			if(lokalStreng.equalsIgnoreCase("{")){
				lokalForrige = true;
			
			} else {
				lokalForrige = false;
			}
			
			if(lokalStreng.equalsIgnoreCase("}")){
				
				if(lokalForrige){
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i Enten/eller. Tomt Enten/eller uttrykk, pass p?? at det er et argument i begge utfall.");	
					exit = true;
					return;
				}
				lokalFortsett = false;
				
				if((hvisTellern!=lokalBarneOpptelling-1)){
					
					System.out.println("Inneholder ellers!!!!!!");
					System.out.println("antall gjenv??rende born, burde v??re >=4: " + (lokalBarneOpptelling-1-hvisTellern));
					lokalEllersFinns = true;
				}
				if((lokalBarneOpptelling-1-hvisTellern)< 4){
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
			System.out.println("Feil i Enten/eller. Tomt Ellers uttrykk, pass p?? at det er et argument i begge utfall.");	
					exit = true;
					return;
				
				}
			
			
			} else {
			
			
				antallHvis++;
			
			}
			hvisTellern++;
			//Avlusing
			/*
			System.out.println("barn " + (hvisTellern-5) + " : " +lokalStreng); 
			System.out.println("barn = } : " + lokalStreng.equalsIgnoreCase("}"));
			System.out.println("\n\n");	
			*/
		}
		
		if(lokalEllersFinns){
			push(entenTellerStack, antallHvis);
		} else {
		
			push(entenTellerStack, 0);
		}
		
		//Avlusing
		System.out.println("\n\n");
		System.out.println("Her har vi: " + peek(entenTellerStack) +" hvis uttrykk f??r vi m?? gj??re greier i argument");
		System.out.println("Her har vi ellers: " + lokalEllersFinns);
				
		
		
		System.out.println("\n\n");
		
		
		
		
		
		
		
	
		
		
	}
	@Override public void exitEntenEller(BarnematParser.EntenEllerContext ctx){ 
		if(exit)
			return;
		
		Label lokalLabSlutt;
		int lokaltUtfall = peek(entenDybdeStack);

		
		switch(lokaltUtfall){
				case 1: {
					lokalLabSlutt = entenSlutt1;
					break;	
				}
				case 2: {
					

					lokalLabSlutt = entenSlutt2;
					break;
				}
				case 3: {
					

					lokalLabSlutt = entenSlutt3;
					break;
				}
				case 4: {
					
					lokalLabSlutt = entenSlutt4;
					break;
				}
				case 5: {
					
					lokalLabSlutt = entenSlutt5;
					break;
				}
				case 6: {
				
					lokalLabSlutt = entenSlutt6;
					break;
				}
				case 7: { 
					
					lokalLabSlutt = entenSlutt7;
					break;
				
				}
				case 8: {
				
					lokalLabSlutt = entenSlutt8;
					break;
				
				}
				case 9: { 
					lokalLabSlutt = entenSlutt9;
					break;
				
				}
				case 10: { 

					lokalLabSlutt = entenSlutt10;
					break;
				
				}
				case 11: { 

					lokalLabSlutt = entenSlutt11;
					break;
				
				}
				case 12: { 

					lokalLabSlutt = entenSlutt12;
					break;
				
				}
				case 13: { 

					lokalLabSlutt = entenSlutt13;
					break;
				
				}
				case 14: { 

					lokalLabSlutt = entenSlutt14;
					break;
				
				}
				case 15: { 

					lokalLabSlutt = entenSlutt15;
					break;
				
				}
				case 16: { 

					lokalLabSlutt = entenSlutt16;
					break;
				
				}
				case 17: { 

					lokalLabSlutt = entenSlutt17;
					break;
				
				}
				case 18: { 

					lokalLabSlutt = entenSlutt18;
					break;
				
				}
				case 19: { 

					lokalLabSlutt = entenSlutt19;
					break;
				
				}
				case 20: { 

					lokalLabSlutt = entenSlutt20;
					break;
				
				}
				default: {
				
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Merkelapp feil ved utgang av entenEller.");
					
					exit = true;
					return;
				}		
		}
		
	
		mainVisitor.visitLabel(lokalLabSlutt);
			
			
			
		entenDybde--;	
		pop(entenDybdeStack);	
		pop(entenArgDybdeStack);
		System.out.println("G??r ut or EntenEller");
	}


	public boolean alleredeLest = false;
	public int lesMinneplassering = 1;
	//Les
	@Override public void enterLes(BarnematParser.LesContext ctx){ 
		if(exit)
			return;
		System.out.println("G??r inn i les");	
		
		System.out.println("child count: " + ctx.getChildCount());
		if(ctx.getChildCount() != 5){
		
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil les, pass p?? at alt er skrevet riktig.");
			exit = true;
			return;
		
		}
		
		
		
		if(!alleredeLest){
			alleredeLest = true;
			
			mainVisitor.visitTypeInsn(NEW, "java/util/Scanner");
            		mainVisitor.visitInsn(DUP);
            		mainVisitor.visitFieldInsn(GETSTATIC,"java/lang/System", "in", "Ljava/io/InputStream;");
            		mainVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V" , false);
            		mainVisitor.visitVarInsn(ASTORE,lesMinneplassering);
			
		}
		
		
		
	
	}
	@Override public void exitLes(BarnematParser.LesContext ctx){ 
		if(exit)
			return;
			
		String lokalN??kkel = "IKKE DEFINERT";	
		
		if(ctx.getChild(3) != null)
			lokalN??kkel = ctx.getChild(3).getText();
			
		variabel lokalVar;

		if(SymbolTable.containsKey(lokalN??kkel)){
			lokalVar = SymbolTable.get(lokalN??kkel);
		} else {
		
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR FEIL");
			System.out.println("------------------------------------------");
			
			System.out.println("Feil i les. ID: " + lokalN??kkel + " er ikke erkl??rt.");
			exit = true;
			return;
		}
		
		
		
		mainVisitor.visitVarInsn(ALOAD,lesMinneplassering);	
			
		switch(variabelType(lokalVar.variabelType)){//Switch
			case 1:{
			//Heltall
			 
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false); 
				mainVisitor.visitVarInsn(ISTORE,lokalVar.minnePlassering);
			
				mainVisitor.visitVarInsn(ALOAD,lesMinneplassering);
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
				mainVisitor.visitInsn(POP);
				break;
			}//slutt utfall 1
				
			case 2:{ //Desimaltall
					 
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextDouble", "()D", false); 
				mainVisitor.visitVarInsn(DSTORE,lokalVar.minnePlassering);
			
				mainVisitor.visitVarInsn(ALOAD,lesMinneplassering);
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
				mainVisitor.visitInsn(POP);
				break;
			}//Slutt utfall 2
			
			case 3:{ //Setning
				
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
				mainVisitor.visitVarInsn(ASTORE,lokalVar.minnePlassering);
				break;
				
			}//slutt utfall 3
			case 4:{
			//Sanning
				
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextBoolean", "()Z", false);
				mainVisitor.visitVarInsn(ISTORE,lokalVar.minnePlassering);
			
		
				mainVisitor.visitVarInsn(ALOAD,lesMinneplassering);
				mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
				mainVisitor.visitInsn(POP);
				break;
	
			}//Slutt utfall 4
			
			default: {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR FEIL");
				System.out.println("------------------------------------------");
			
				System.out.println("Variabeltypen som blei oppgitt i \"enterLes\" st??ttes ikke.");
				exit = true;
				return;	
			
			}//Slutt tilbakefall
		
		}//Slutt switch
		
		lokalVar.verdiSatt = true;
		System.out.println("G??r ut or les");		

	}//Slutt les



	
//----------------------------------------------------------------------------------------	
	
//----------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------	
	
		
//----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------
//		-- ikke p??byrja --
//----------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------



//Prototype	
/*	
	//Test label
	Label testStempelHvis = new Label();
	
	//int kretsTilfelleSammenlikning = -1;
	int kretsTallTypeSammenlikn = -1;
	//M?? muligens endre denne til stacker!
	
	
	//Sammenlikning
	@Override public void enterSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx){
		if(exit)
			return;
		///*	
		//------------------------------------------------------
		//FJERN ALT DETTE VED AVLUSING	
		exit = true;
		System.out.println("ENDRING M?? TIL FOR KRETSTALLTYPE");
		return;
		//------------------------------------------------------	
		//	
		System.out.println("G??r inn i sammenlikning for enten eller");
			
		kretsTallTypeSammenlikn = -1;
		int lokalBarneOpptelling = ctx.getChildCount();
		
		//Avlusing
		System.out.println("\n\n\n barneopptelling: "+lokalBarneOpptelling+ "\n\n\n");
		
		if(lokalBarneOpptelling == 1 ){
			
			System.out.println("\nkun ett barn her\n");
			String lokalID = ctx.getText();

			if(!SymbolTable.containsKey(lokalID)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga finns ikke");
				exit = true;
				return; 
			
			}
			
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(variabelType(lokalVar.variabelType) != 4){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga er av type: " 
				+ lokalVar.variabelType + ", og m?? v??re av typen Sannhet");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			//Mangler ?? hente sanninga
			System.out.println("\n\nOk, da burde dette funke for ett enkelt barn\n\n");
			mainVisitor.visitVarInsn(ILOAD, lokalVar.minnePlassering);
					

		
		} else if(lokalBarneOpptelling == 2){
		
		
			//
			System.out.println("\nHer finns det 2 born\n");
			String lokalID = ctx.getChild(1).getText();

			if(!SymbolTable.containsKey(lokalID)){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga finns ikke");
				exit = true;
				return; 
			
			}
			
			variabel lokalVar = SymbolTable.get(lokalID);
			
			if(variabelType(lokalVar.variabelType) != 4){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga er av type: " 
				+ lokalVar.variabelType + ", og m?? v??re av typen Sannhet");
				exit = true;
				return; 
			
			}
			
			if(!lokalVar.verdiSatt){
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
			
				System.out.println("Feil i sammenlikning, ID'en du oppga har ikke blitt angitt en verdi enda.");
				exit = true;
				return;
			
			}
			
			//Mangler ?? hente sanninga
			System.out.println("\n\nOk, da burde dette funke for ett enkelt barn\n\n");
			mainVisitor.visitVarInsn(ILOAD, lokalVar.minnePlassering);
			
			
			
		
		} else if(lokalBarneOpptelling == 3){
		
		
			//
			System.out.println("\nHer finns det 3 born\n");
			
			
			
		
		} else {
			System.out.println("\n\n------------------------------------------");
			System.out.println("KOMPILATOR ERROR");
			System.out.println("------------------------------------------");
			
			System.out.println("Syntaksfeil i sammenlikning, pass p?? at det enten er en ID, " 
			+ "eller ei sammenliking av to tall av samme type");
			exit = true;
			return;
		}
		
		
		
		
		
		
	}
	@Override public void exitSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx){
		if(exit)
			return;
		
		//Avlusing	
		//System.out.println("FIKS SAMMENLIKNING!!!!!!");
		//exit = true;	
			
		int lokalBarneOpptelling = ctx.getChildCount();
		
		
		//M?? ENDRES
		System.out.println("\t\tM?? ENDRES I EXITSAMMENLIKNING, alts?? labels m?? endres, forel??pig er bare en prototype.");
		
		
		if(lokalBarneOpptelling == 1){	
			
				
          		mainVisitor.visitJumpInsn(IFEQ, testStempelHvis);	
          	} 
          	if(lokalBarneOpptelling == 2){
          	
          		mainVisitor.visitJumpInsn(IFNE, testStempelHvis);
          	
          	}
          	if(lokalBarneOpptelling == 3){
          		
          		
          		String lokalSammenlikningsTeikn = ctx.getChild(1).getText();
          		System.out.println("\n\nctx.getText(): "+ lokalSammenlikningsTeikn + " \n\n");
          		
          		
          		System.out.println("kretsTallType i utgangSammenliking: " + kretsTallTypeSammenlikn);
          		
          		if(kretsTallTypeSammenlikn == 1){
          			if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IF_ICMPLE, testStempelHvis);
				
				} else if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IF_ICMPGE, testStempelHvis);
			
				} else if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IF_ICMPEQ, testStempelHvis);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IF_ICMPNE, testStempelHvis);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			} else if(kretsTallTypeSammenlikn == 2){
			
			
			
				mainVisitor.visitInsn(DCMPG);
				
				if(lokalSammenlikningsTeikn.equals(">")){
				
					mainVisitor.visitJumpInsn(IFLE, testStempelHvis);
				
				} else if(lokalSammenlikningsTeikn.equals("<")){
				
					mainVisitor.visitJumpInsn(IFGE, testStempelHvis);
			
				} else if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IFEQ, testStempelHvis);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IFNE, testStempelHvis);
				
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, sammenlikningsteiknet gjenkjennes ikke.");
					exit = true;
					return;
				}  
			
			
			} else if(kretsTallTypeSammenlikn == 4){
			
				if(lokalSammenlikningsTeikn.equals("!=")){
			
					mainVisitor.visitJumpInsn(IFEQ, testStempelHvis);
				
				} else if(lokalSammenlikningsTeikn.equals("=")){
				
					mainVisitor.visitJumpInsn(IFNE, testStempelHvis);
					
				} else {
					System.out.println("\n\n------------------------------------------");
					System.out.println("KOMPILATOR ERROR");
					System.out.println("------------------------------------------");
				
					System.out.println("Feil i sammenlikning, for ?? sammenlikne sannheter " 
					+"s?? m?? det enten v??re likhetstegn eller ulikhetstegn!");
					exit = true;
					return;
				}  	
			
			
			} else {
				System.out.println("\n\n------------------------------------------");
				System.out.println("KOMPILATOR ERROR");
				System.out.println("------------------------------------------");
				
				System.out.println("Feil i sammenlikning ved utgang, "
				+"noe stemmer ikke helt med typene som skal sammenliknes.");
				exit = true;
				return;
			
			
			}	        		          	          
          	
          	}
			 
		System.out.println("G??r ut or sammenlikning");
	}
	
//----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------
//		-- ikke p??byrja --
//----------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------
	
	//EntenEller (if-else)
	@Override public void enterEntenEller(BarnematParser.EntenEllerContext ctx){
		if(exit)
			return;
		System.out.println("G??r inn i EntenEller");
		
		
//-------------------------------------------------------------------------------------------------------------------------------------------		
		//Kommenter ut alt under
///*
		//System.out.println("\t- ");
		System.out.println("------------------------------------------------------------------");
		System.out.println("\t- Finne ut om en mer effektiv m??te ?? bruke merkelapper(label) p??, "
		+ "til d??mes: \n\t\t* objekt med innhold, \n\t\t* array, \n\t\t* eller bare lage en haug med merkelapper og switch ");
		System.out.println("\t- Legge til argument telling for dersom alts?? if1, if2, osv.");
		System.out.println("\t- Legge til merkelapper i argument utgang og telling i argument, muligens ha alt i argument?");
		System.out.println("\t- Legge til djupn/dybde for ?? kunne ha leira-l??kker og entenEller uttrykk. ");
		
		exit = true;
		System.out.println("\n\n\t dette kan fjernes i \"enterEntenEller\"");
		System.out.println("------------------------------------------------------------------");
		return;

		//Sjekke at syntaksen stemmer
		
		//telle antall if-argument + dypere entenEller og l??kker
//		
//-------------------------------------------------------------------------------------------------------------------------------------------
	
		
		
	}
	@Override public void exitEntenEller(BarnematParser.EntenEllerContext ctx){ 
		if(exit)
			return;
			
		mainVisitor.visitLabel(testStempelHvis);
			
		System.out.println("G??r ut or EntenEller");
	}
	
	*/
	
	
	
	
	
	
	//Metode erkl??ring
	
	






//---------------------------------------------------------------------------------------------------------
// 	Test metode
//---------------------------------------------------------------------------------------------------------
	Label label1 = new Label();
	Label label2 = new Label();
	
	public boolean velgTestPlass = false;
	Scanner les = new Scanner(System.in);
	
/*	
	public Label labelMaker(){
	
		Label labelMade = new Label();
		return labelMade;	
	
	}
*/	

	
	
	
	@Override 
	public void enterTestMetode(BarnematParser.TestMetodeContext ctx){ 
		
		if(exit)
			return;
		
		int testPlass = 2;
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
		
	
	//Desimaltall sammenlikningstest
	/*
		
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn((double)2.9);
		mainVisitor.visitLdcInsn((double)3.0);
		mainVisitor.visitInsn(DCMPG);
            	mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	
	
	
	
	
	
	
	
	*/
	
	
	
	//Label test	
	/*	
	Label halla;
	Label slutt;
		
	halla = label1;
	slutt = label2;
			
			
		mainVisitor.visitInsn(ICONST_0);
		mainVisitor.visitJumpInsn(IFEQ, halla);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn("FEIL");
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		mainVisitor.visitJumpInsn(GOTO, slutt);
        	mainVisitor.visitLabel(halla);
        	mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn("SANT SANT SANT");
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		//Slutt label er i utgangsmetoden
	*/	
	
	//Test for sammenlikning
	/*
	Label halla;
	Label slutt;
	
	halla = label1;
	slutt = label2;
	
	//mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	//mainVisitor.visitLdcInsn((double)1.0);
	//mainVisitor.visitVarInsn(Opcodes.DLOAD, testPlass);
	
	mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mainVisitor.visitVarInsn(Opcodes.DLOAD, testPlass);
        mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
        
        
        //mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mainVisitor.visitVarInsn(Opcodes.DLOAD, testPlass);
        mainVisitor.visitLdcInsn((double)10.0);
        //mainVisitor.visitVarInsn(Opcodes.DLOAD, testPlass);
	mainVisitor.visitInsn(DCMPG);
	mainVisitor.visitJumpInsn(IFEQ, halla);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn("DE ER IKKE LIKE!");
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
		mainVisitor.visitJumpInsn(GOTO, slutt);
        	mainVisitor.visitLabel(halla);
        	mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn("VELDIG LIKE!");
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
	
	//mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	
	
	//mainVisitor.visitLdcInsn((double)1.0);


	*/
	
	
	//Utskrift for sannhet
	/*
			mainVisitor.visitInsn(ICONST_1);
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            		mainVisitor.visitVarInsn(ILOAD, lagre);
           		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Z)V", false);
	*/
	/*
			mainVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            		mainVisitor.visitInsn(ICONST_1);
           		mainVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Z)V", false);	
	*/
	
	
	
	}
	

	@Override public void exitTestMetode(BarnematParser.TestMetodeContext ctx){ 
		
		
		
		
			Label slutt;	
			slutt = label2;
			mainVisitor.visitLabel(slutt);	
			
		System.out.println("G??r ut or test");
		
		
	}




}//end class

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.*;

public class testing123{

	public static void main(String[]args){
	
		boolean bool = false;
		int i = 0;
		do{
			if(i == 3)
				bool = false;
			//System.out.println(i);
			i++;
			
		}while(!bool);
	
	
	
	}


// 	ASM greier
/* 
	public static void main(String[] args){		
		
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        	cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"TESTER", null, "java/lang/Object",null);
	
	{
		//Use local MethodVisitor to create the constructor for the object
		MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
       	mv.visitCode();
        	mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
        	mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(1,1);
        	mv.visitEnd();
       }	
	
	Label halla = new Label();
	
	
	
	{	
		//Use global MethodVisitor to write bytecode according to entries in the parsetree	
	 MethodVisitor mainVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,  "main", "([Ljava/lang/String;)V", null, null);
        	mainVisitor.visitCode(); 
        	
		mainVisitor.visitInsn(ICONST_1);
		mainVisitor.visitJumpInsn(IFEQ, halla);
		mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn("HALLA, riktig metode");
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
        	mainVisitor.visitLabel(halla);
        	mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mainVisitor.visitLdcInsn("HALLA, riktig metode");
		mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
        	
        	
        	
        	mainVisitor.visitInsn(Opcodes.RETURN);
		mainVisitor.visitMaxs(0, 0);
		mainVisitor.visitEnd();

		cw.visitEnd();
	}
	
	
	
      	byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("TESTER.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        
         
         
        }
       */	
       
        	
}//Slutt klasse	
        	

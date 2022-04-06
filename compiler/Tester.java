import java.util.*;
public class Tester{

	public static void main(String[]args){


		String s = "ja";
		if(s.equals("ja")){ //=
			System.out.println("SANT");
		} else {
			System.out.println("NEI NEI NEI NEI");
		}
		
		String s2 = "ja";
		if(!s2.equals("ja")){ // !=
			System.out.println("SANT");
		} else {
			System.out.println("NEI NEI NEI NEI");
		}

		/*
		Scanner scan = new Scanner(System.in);
		System.out.print("please enter a boolean: ");
		boolean bool = scan.nextBoolean();
	
		scan.nextLine();
		
		System.out.print("Please enter a double: ");
		String st1 = scan.nextLine();
		String dub = st1.replace(",",".");
		double tall = Double.valueOf(dub); 
		
		//double dub = scan.nextDouble();
		//scan.nextLine();
		
		System.out.print("Please enter a string: ");
		String st = scan.nextLine();
		//scan.nextLine();
*/
		

	}

}

import java.util.Scanner;
import java.util.Random;
import java.lang.Math; 
public class Main {
	
	
	public static void main (String []args) {
		int choice;

		
		while (true) {
			System.out.println("****************PLEASE ENTER YOUR CHOICE***************");
			System.out.println("****************1-SEND                ***************");
			System.out.println("****************2-EXIT              ***************");
		

			Scanner scan = new Scanner(System.in);
			choice= scan.nextInt();
		    if (choice ==1) {
		    	System.out.println("Enter the sequence of bits you want");
		    	Scanner input= new Scanner(System.in);
		    	Integer seq= input.nextInt();
		    	String seq1= seq.toString();
		    	int length= seq1.length(); 
		    	boolean flag= true;
		    	int index=0; 
		    	char arr[]= seq1.toCharArray();
		    	/*for (int i=0; i<length; i++) {
		    		System.out.println(arr[i]);
		    	}*/
		    	for (int i=0; flag; i++) {
		    		if (length+1+i <= Math.pow(2, i)) {
		    			flag= false; 
		    		    index=i;
		    		}
		    	}
		    	int array []= new int [length+index];
		    	boolean [] flags= new boolean [length+index];
		    	for (int n=0; n< length+index; n++) {
		    		flags [n]=false; 
		    	}
		        
		    	for (int i=0; i< index; i++) {
		         double d= Math.pow(2, i);
		         double d1= d-1;
		         int myIndex= (int)d1; 
		         flags[myIndex]= true; 
		          }
		    	int h=length-1;
		    	for (int n=0; n< length+index ; n++) {
		    	  if (flags[n]==false) {
		    	    array[n]= (int)arr[h]-48;
		    		h--;
		    	  }
		    		
		    	}
		    	int myInt=0;
		    	for (int i=0;i< index; i++) {
		    		for (int j=0; j<(length+index); j++) {
		    			if((("0000000000"+Integer.toBinaryString(j+1)).toCharArray())[("0000000000"+Integer.toBinaryString(j+1)).length()-1-i]=='1') {
		    				myInt= myInt^array[j];
		    			}
		    			array[(int)Math.pow(2, i)-1]=myInt;
		    		}
		    	}
		    	
		    	/*array[0]= array[2]^ array[4] ^array[6];
		    	array[1]=array[2]^ array[5]^array[6];
		    	array[3]=array[4]^ array[5]^array[6];*/
		    	
		    	System.out.println("The sequence will be sent as the follwing:");
		    	for (int i=length+index-1; i>=0; i--) {
			    	System.out.print(array[i]);
                    }
		    	System.out.println();
		    	System.out.println("Choose the recieving: 1- without error 2- with error.");
		    	Scanner in1= new Scanner(System.in);
		    	int l= in1.nextInt();
		    	int[] recieved= new int[length+index];
		    	if (l==1) {
		    		recieved= array;
		    		System.out.println("THE RECIEVED SIGNAL IS:");
		    		for (int i=length+index-1; i>=0; i--) {
				    	System.out.print(recieved[i]);
	                    }
		    		System.out.println();
		    	}
		    	else if (l==2) {
		    		recieved= array;
		    		Random n= new Random();
		    		Random m=new Random();

		    	
		    		int numberOfErrors= n.nextInt(2)+1;

		    		/*while(x==0){
		    		   x=m.nextInt(7); 
		    		}*/
		    		for (int i=0; i<numberOfErrors; i++) { 
		    			int x=m.nextInt(length+index);
			    		if(recieved[x]==0)recieved[x]=1;
			    		else recieved[x]=0;
		    		}
		    		
		    		System.out.println("THE RECIEVED SIGNAL IS:");
		    		for (int i=length+index-1; i>=0; i--) {
				    	System.out.print(recieved[i]);
	                    }
		    		System.out.println();
		    		
		    	}
		    	else {
		    		System.out.println("INVALID CHOICE");
		    		System.exit(0);
		    	}
		    	
		    
		  //  else if (choice==2) {
		    	System.out.println("DETECTING THE ERROR:");
		    	/*System.out.println("Enter the sequence of bits you recieved");
		    	Scanner input= new Scanner(System.in);
		    	Integer seq= input.nextInt();
		    	String seq1= seq.toString();
		    	char arr[]= seq1.toCharArray();*/
		    	 
		    	int detecting[];
		    	int parities[]=new int [index];
		    	detecting=recieved;
		    	
		    	for (int i=0;i< index; i++) {
		    		int myInt1=0;
		    		for (int j=0; j<(length+index); j++) {
		    			if((("0000000000"+Integer.toBinaryString(j+1)).toCharArray())[("0000000000"+Integer.toBinaryString(j+1)).length()-1-i]=='1') {
		    				myInt1= myInt1^detecting[j];
		    			}
		    		
		    		}
		    		
		    		parities[i]=myInt1;
		    	}
		    /*	for (int i=0; i<index;i++) {
		    		System.out.println(parities[i]);
		    	}*/
		    
		       /* int p1= detecting[0]^detecting[2]^ detecting[4] ^detecting[6];
		        int p2=  detecting[1]^ detecting[2]^  detecting[5] ^ detecting[6];
		        int p4=  detecting[3]^ detecting[4]^  detecting[5] ^ detecting[6];*/
		    	int x=0;
		       for (int i=0; i<(length+index); i++) {
		    	   x=x^ detecting[i];
		       }
		       int errorPlace=0;
		       for (int j=0; j<index; j++) {
		    	   errorPlace+= ((int) Math.pow(2, j)*parities[j]);
		       }
		    	

		    	if (errorPlace ==0 && x==0)
		    		System.out.println("NO ERRORS");
		    	
		    	else if (errorPlace==0 && x==1)
		    		System.out.println("NO ERRORS IN DATA");
		    	
		    	else if(x==1 && errorPlace!=0) {
		    		//convert to decimal
		    	
		    		System.out.println("SINGLE ERROR AT BIT NUMBER  "+ errorPlace);
		    		if (detecting[errorPlace-1]==0)detecting[errorPlace-1]=1;
		    		else detecting[errorPlace-1]=0;
		    		System.out.println("THE CORRECT SEQUENCE SHOULD BE:");
		    		for(int i=detecting.length-1; i>=0; i--) System.out.print(detecting[i]);
		    	}
		    	else if(x==0 && errorPlace!=0) {
		    		System.out.println("DOUBLE ERRORS");
		    	}
		    	System.out.println();
		    	
		    }
		  //  }
		    else if (choice==2) System.exit(0);
		    
		    else {
		    	System.out.println("INVALID CHOICE PLEASE TRY AGAIN!!");
		    }



		}
		
}}

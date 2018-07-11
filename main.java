import java.util.Scanner;

public class main {
    
	public static void main(String args[])
    {
    	Scanner input = new Scanner(System.in);
    	String userString;
    	int userInt;
    	
        System.out.println("==============================");
        System.out.println("======Brainstorm Helper!======");
        System.out.println("==============================");
        
        System.out.println("Please enter a string for the start");
        userString = input.nextLine();
        
        BPlusTree tree = new BPlusTree(userString);
        
        System.out.println("Enter another string");
        userString = input.nextLine();
        System.out.println("Enter a child number");
        userInt = input.nextInt();
        
        Node u1 = new Node(userString);
        
        tree.add(u1, userInt);
        
        input.close();
    	
    }
}

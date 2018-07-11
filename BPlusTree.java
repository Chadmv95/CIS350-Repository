import java.util.Scanner;

public class BPlusTree {
	private static Node root;

    public BPlusTree(String data)
    {
        root = new Node(data);
    }

    public void add(Node parent, Node child, int childNum)
    {
    	
        parent.setChildren(child, childNum);
        
    }
    
    /*
     * This function needs work and may need to be reconsidered
     * if we decide to use the website for the GUI
     */
    public void printTree() {
    	if(root == null) return;
    	
    	return;
    }

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
        tree.add(root, u1, userInt);
    	
    }


}



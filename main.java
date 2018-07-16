import java.util.Scanner;

public class main {
    
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in, "utf-8");
        String userString;
        int userInt = 0;
        
        System.out.println("==============================");
        System.out.println("======Brainstorm Helper!======");
        System.out.println("==============================");
        
        System.out.println("Please enter a string for the starting node");
        userString = input.nextLine();
        
        BPlusTree tree = new BPlusTree(userString);
        
        while (userInt != 4) {
        	
        	System.out.println("");
	        System.out.println("What would you like to do?");
	        System.out.println("1) Add a node");
	        System.out.println("2) Delete a node");
	        System.out.println("3) Print the tree");
	        System.out.println("4) Exit the program");
	        
	        userInt = input.nextInt();
	        input.nextLine();
	        
	        switch (userInt) {
	        case 1: //add a node to the tree
	        	System.out.println("Where would you like to add the new node?");
	        	System.out.println("1) Under root node");
	        	System.out.println("2) Under another node");
	        	
	        	userInt = input.nextInt();
	        	input.nextLine();
	        	
	        	if (userInt == 2) {
	        		System.out.println("Enter node title");
	        		userString = input.nextLine();
	        		Node addToChild = new Node(userString);
	        		
	        		System.out.println("Enter title of parent to add node to");
	        		userString = input.nextLine();
	        		
	        		//add new node as a child to searched node
	        		if (tree.add(tree.search(userString), addToChild))
	        			System.out.println("Node added to tree!");
	        		
	        	}
	        	else {
	        		System.out.println("Enter node title");
	        		userString = input.nextLine();
	        		Node addToRoot = new Node(userString);
	        		
	        		if (tree.add(addToRoot))
	        			System.out.println("Node added to tree!");
	        		else System.out.println("Could not find node with that name");
	        	}
	        	
	        	break;
	        case 2: //delete a node from the tree
	        	System.out.println("Enter a node name to delete");
	        	userString = input.nextLine();
        		Node deleteFromTree = tree.search(userString);
	        	
        		if (tree.delete(deleteFromTree)) //delete from tree
        			System.out.println("Node deleted from tree!");
        		else System.out.println("Could not find node with that name");
	        	
	        	break;
	        case 3: //print the tree
	        	tree.printTree();
	        	break;
	        case 4: //exit the program
	        	System.out.close();
	        	break;
	        default:
	        	System.out.close();
	        }
	        
        }
        
        input.close();
        
    }
}

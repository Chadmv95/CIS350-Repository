import java.util.Scanner;

public class BPlusTree {
	private static Node root;

	/*
	 * creates root node with given data
	 */
    public BPlusTree(String data)
    {
        root = new Node(data);
    }
    
    /*
     * creates root node with no data
     */
    public BPlusTree()
    {
    	root = new Node("");
    }

    /*
     * Add a node to the tree underneath the selected parent
     * at the selected position
     * 
     * returns true on successful add
     * returns false if child is null
     */
    public boolean add(Node parent, Node child, int childNum)
    {
        if(child == null) 
        	return false;
        
    	parent.setChildren(child, childNum);
        return true;
    }
    
    /*
     * Add node to the soonest available spot
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     */
    public boolean add(Node parent, Node child)
    {
    	if(child != null)
	    	for(int i=0; i<5; i++) 
	    		if(parent.getChildren(i) == null) {
	    			parent.setChildren(child, i);
	    			return true;
	    		}
	    	
	    
    	return false;
	    	
    }
    
    /*
     * Delete child from the tree
     * Note: children under the deleted node will also be removed
     * 
     * returns true upon deletion
     * returns false if parent is null
     */
    public boolean delete(Node parent, int childNum)
    {    	
    	if(parent == null) 
    		return false;
    	
    	parent.setChildren(null, childNum);
    	return true;
    }
    
    /*
     * This function needs work and may need to be reconsidered
     * if we decide to use the website for the GUI
     * 
     * returns true upon successful print
     * returns false if tree is empty
     */
    public boolean printTree() {
    	if(root == null)
    		return false;
    	
    	return true;
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
        
        input.close();
    	
    }


}



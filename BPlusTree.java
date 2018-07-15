import java.io.PrintStream;

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
     * Creates a new tree using a pre-existing node as the root node
     */
    public BPlusTree(Node rootNode)
    {
    	root = rootNode;
    }
    
    /*
     * Returns the tree's root node
     */
    public Node getRoot()
    {
    	return root;
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
        
    	return parent.setChildren(child, childNum);
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
	    	return parent.setChildren(child);
	    
    	return false;
    }
    
    /*
     * Add node to the root at the soonest available spot
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     */
    public boolean add(Node child)
    {
    	if(child != null)
	    	return root.setChildren(child);
    	
    	return false;
    }
    
    /*
     * Add a node to the tree underneath the root
     * at the selected position
     * 
     * returns true on successful add
     * returns false if child is null
     */
    public boolean add(Node child, int childNum)
    {
        if(child == null) 
        	return false;
        
    	root.setChildren(child, childNum);
        return true;
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
    	
//    	System.out.println(root.getContent());
//    	System.out.print("|--> ");
//    	for(int i=0; i<root.getNumChildren(); i++) {
//    		if(root.getChildren(i) != null) {
//    			System.out.print(root.getChildren(i).getContent());
//    			System.out.print(" ");
//    		}
//    	}
//    	System.out.println("");
    	
    	printBranch(root, "", "     ", System.out);
    	
    	return true;
    }
    
    private void printBranch(Node parent, String treeStructure, String next, PrintStream stream)
    {
    	// Print the parent of this branch
    	stream.println(treeStructure + "|--> " + parent.getContent());
    	
    	
    	// Then print all of its babies at one more depth than this
    	int i;
    	// The first children all have trailing "sticks" for all descendants to carry
    	for(i=0; i<parent.getNumChildren()-1; i++) {
    		printBranch(parent.getChildren(i), treeStructure + next, "|    ", stream);
    	}
    	// The last child doesn't display a "stick"
    	if(i<parent.getNumChildren())
    		printBranch(parent.getChildren(i), treeStructure + next, "     ", stream);
    }
    
}



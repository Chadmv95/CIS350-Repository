import java.io.PrintStream;

public class BPlusTree {
    private Node root;

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
        if(child == null || child.getContent().equals("")) 
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
        if(parent == null || child == null || child.getContent().equals(""))
            return false;
        
        return parent.setChildren(child);
    }
    
    /*
     * Add node to the root at the soonest available spot
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     */
    public boolean add(Node child)
    {
        if(child == null || child.getContent().equals("")) 
            return false;
        
        return root.setChildren(child);
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
        if(child == null || child.getContent().equals("")) 
            return false;
        
        return root.setChildren(child, childNum);
    }
    
    /*
     * Delete child from the tree
     * Note: children under the deleted node will also be removed.
     * Note: don't use this, it is bad and terrible and bad
     * 
     * returns true upon deletion
     * returns false if parent is null
     */
    public boolean delete(Node parent, int childNum)
    {        
        if(parent == null) 
            return false;
        
        parent.getChildren(childNum).setParent(null);
        parent.setChildren(null, childNum);
        return true;
    }
    
    public boolean delete(Node tbd) {
    	
    	if(tbd == null)
    		return false;
    	else {
    		//remove the children and set parent to null
	    	tbd.getParent().removeChildren(tbd);
	    	tbd.setParent(null);
	    	
	    	//delete the children
	    	for(int i=0; i<tbd.getNumChildren(); i++) {
	    		delete(tbd.getChildren(i));
	    	}
	    	
	    	return true;
    	}
    	
    }
    
    /*
     * calls helper function which uses root
     * this keeps the root private from other classes
     */
    public Node search(String title) {
    	return searchHelper(root, title);
    }
    
    /*
     * this helper function searches the tree from the root
     * to find the matching node
     * 
     * if there is no matching node, null is returned
     * 
     * if there are two matching nodes, the last found node
     * is the node which is returned
     */
    private Node searchHelper(Node it, String title) {
    	Node tmp = new Node();
    	
    	if(it.getContent().equals(title)) {
    		return it;
    	}
    	else {
    		for(int i=0; i< it.getNumChildren(); i++)
    			if(it.getChildren(i) != null) {
    				 tmp = searchHelper(it.getChildren(i), title);
    				 if(tmp != null)
    					 return tmp;
    			}
    	}
    	
    	
    	return null;
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
        
//        System.out.println(root.getContent());
//        System.out.print("|--> ");
//        for(int i=0; i<root.getNumChildren(); i++) {
//            if(root.getChildren(i) != null) {
//                System.out.print(root.getChildren(i).getContent());
//                System.out.print(" ");
//            }
//        }
//        System.out.println("");
        
        printBranch(root, "", "    ", System.out);
        
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



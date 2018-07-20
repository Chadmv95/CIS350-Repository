import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

public class BPlusTree {
    private Node root;

    /*
     * creates root node with given data
     */
    public BPlusTree(final String name, final String data) {
        root = new Node(name, data);
    }
    
    /*
     * creates root node with no data
     */
    public BPlusTree() {
        root = new Node();
    }
    
    /*
     * Creates a new tree using a pre-existing node as the root node
     */
    public BPlusTree(final Node rootNode) {
        root = rootNode;
    }
    
    /*
     * Returns the tree's root node
     */
    public Node getRoot() {
        return root;
    }

    /*
     * Add a node to the tree underneath the selected parent
     * at the selected position
     * 
     * returns true on successful add
     * returns false if child is null
     */
    public boolean add(final Node parent, final Node child, final int childNum) {
        if (child == null || child.getContent().equals("")) 
            return false;
        
        return parent.setChildren(child, childNum);
    }
    
    /*
     * Add node to the soonest available spot
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     */
    public boolean add(final Node parent, final Node child) {
        if (parent == null || child == null || child.getContent().equals(""))
            return false;
        
        return parent.setChildren(child);
    }
    
    /*
     * Add node to the root at the soonest available spot
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     */
    public boolean add(final Node child) {
        if (child == null || child.getContent().equals("")) 
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
    public boolean add(final Node child, final int childNum) {
        if (child == null || child.getContent().equals("")) 
            return false;
        
        return root.setChildren(child, childNum);
    }
    
    public List<Node> getAllNodes() {
        return getAllDescendants(root);
    }
    
    /*
     * Helper function for getAllNodes().
     * 
     * Returns all of the children of parent, and
     * grandchildren, great grandchildren, etc.
     */
    private List<Node> getAllDescendants(final Node parent) {
        List<Node> retVal = new ArrayList<Node>();
        for (Node n: parent.getChildren()) {
            retVal.add(n);
            retVal.addAll(getAllDescendants(n));
        }
        return retVal;
    }
    
    /*
     * Delete child from the tree
     * Note: children under the deleted node will also be removed.
     * Note: don't use this, it is bad and terrible and bad
     * 
     * returns true upon deletion
     * returns false if parent is null
     */
    public boolean delete(final Node parent, final int childNum) {        
        if (parent == null) 
            return false;
        
        parent.getChildren(childNum).setParent(null);
        parent.setChildren(null, childNum);
        return true;
    }
    
    public boolean delete(final Node tbd) {
    	
    	if (tbd == null)
    		return false;
    	else {
    		//remove all links between the node and its parent
	    	tbd.getParent().removeChildren(tbd);
	    	tbd.setParent(null);
	    	
	    	//delete the node's children
	    	for (int i = 0; i < tbd.getNumChildren(); i++) {
	    		delete(tbd.getChildren(i));
	    	}
	    	
	    	return true;
    	}
    	
    }
    
    /*
     * calls helper function which uses root
     * this keeps the root private from other classes
     */
    public Node search(final String title) {
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
    private Node searchHelper(final Node it, final String title) {
    	Node tmp;
    	
    	if (it.getContent().equals(title)) {
    		return it;
    	} else {
    		for (int i = 0; i < it.getNumChildren(); i++) {
    			if (it.getChildren(i) != null) {
    				 tmp = searchHelper(it.getChildren(i), title);
    				 if (tmp != null)
    					 return tmp;
    			}
    		}
    	}
    	
    	return null;
    }
    
    /*
     * This function prints an ASCII representation of the
     * tree to StdOut
     * 
     * returns true upon successful print
     * returns false if tree is empty
     */
    public boolean printTree() {
        if (root == null)
            return false;
        
        printBranch(root, "", "    ", System.out);
        
        return true;
    }
    
    private void printBranch(final Node parent, final String treeStructure,
                             final String next, final PrintStream stream) {
        // Print the parent of this branch
        stream.println(treeStructure + "|--> " + parent.toString());
        
        
        // Then print all of its babies at one more depth than this
        int i;
        // The first children all have trailing "sticks"
        // for all descendants to carry
        for (i = 0; i < parent.getNumChildren() - 1; i++) {
            printBranch(parent.getChildren(i), treeStructure + next, "|    ", stream);
        }
        // The last child doesn't display a "stick"
        if (i < parent.getNumChildren())
            printBranch(parent.getChildren(i), treeStructure + next, "     ", stream);
    }
    
}

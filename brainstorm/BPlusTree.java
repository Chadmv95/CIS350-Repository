package brainstorm;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a basic tree container that holds the nodes of
 * a tree. It has a number of methods for manipulating the
 * nodes of the tree in a more convenient way than the 
 * nodes themselves present.
 * 
 * @author Brian Gilbert
 * @author Chad Vredvald
 *
 */
public class BPlusTree {
    
    /**
     * Holds a reference to the root node of the tree.
     */
    private Node root;

    /**
     * A constructor that allows the user to build a tree by providing
     * the name and data of the first Node as Strings.
     * 
     * @param name The desired name of the root node.
     * @param data The desired data content of the root nod.
     */
    public BPlusTree(final String name, final String data) {
        root = new Node(name, data);
    }
    
    /**
     * Constructor which creates a root node with default values
     * as determined by the Node class.
     */
    public BPlusTree() {
        root = new Node();
    }
    
    /**
     * Constructor which creates a new tree using a pre-existing
     * node as the root node.
     * 
     * @param rootNode The Node which becomes the root of the new tree.
     */
    public BPlusTree(final Node rootNode) {
        root = rootNode;
    }
    
    /**
     * Access method for the root node of the tree.
     * 
     * @return The Node which is a the root of the tree
     */
    public Node getRoot() {
        return root;
    }
    
    /**
     * Adds node to the first available slot among parent's children.
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     * 
     * @param parent The parent Node which will receive the child Node.
     * @param child The Node to add.
     * 
     * @return Whether or not the add was successful.
     */
    public boolean add(final Node parent, final Node child) {
        if (parent == null || child == null) {
            return false;
        }
        
        return parent.addChild(child);
    }
    
    /**
     * Adds node as a child of the root node.
     * 
     * returns true upon successful add
     * returns false upon unsuccessful add & child is not added
     * 
     * @param child The Node to add to the tree
     * @return Whether or not the add was successful.
     */
    public boolean add(final Node child) {
        if (child == null) { 
            return false;
        }
        
        return root.addChild(child);
    }
    
    /**
     * Returns all of the Nodes in the tree EXCEPT the root node.
     * 
     * Returns all of the children, grandchildren, great grandchildren, etc
     * of the requested Node.
     * 
     * @return A List of all descendants of the argument
     */
    public List<Node> getAllNodes() {
        return getAllDescendants(root);
    }
    
    /**
     * Helper function for getAllNodes().
     * 
     * Returns all of the children, grandchildren, great grandchildren, etc
     * of the requested Node.
     * 
     * @param parent The node whose descendants are to be returned.
     * 
     * @return A List of all descendants of the argument
     */
    private List<Node> getAllDescendants(final Node parent) {
        List<Node> retVal = new ArrayList<Node>();
        for (Node n: parent.getChildren()) {
            retVal.add(n);
            retVal.addAll(getAllDescendants(n));
        }
        return retVal;
    }
    
    /**
     * Delete child and its descendants from the tree.
     * 
     * returns true upon deletion
     * returns false if parent is null
     * 
     * @param tbd The Node to be deleted.
     * 
     * @return Whether the deletion was successful.
     */
    public boolean deleteBranch(final Node tbd) {
    	
    	if (tbd == null) {
    		return false;
    	} else {
    		//remove all links between the node and its parent
	    	tbd.getParent().removeChild(tbd);
	    	tbd.setParent(null);
	    	
	    	//delete the node's children
	    	for (Node child: tbd.getChildren()) {
	    		deleteBranch(child);
	    	}
	    	
	    	return true;
    	}
    	
    }
    
    /**
     * Searches the tree for the a Node whose Title matches the argument.
     * 
     * It calls a helper function which uses root
     * to help keep the root private from other classes.
     * 
     * @param title The title of the desired Node
     * 
     * @return The first Node with the desired title. If no Node has that
     * title, then null is returned.
     */
    public Node search(final String title) {
    	return searchHelper(root, title);
    }
    
    /**
     * This helper function searches the branch of the tree starting from
     * the supplied Node it to find the matching node.
     * 
     * If there is no matching node, null is returned
     * 
     * If there are two matching nodes, the first found node
     * is the node which is returned
     * 
     * @param ancestor The root of the branch to be searched.
     * @param title The title of the node being searched for.
     * 
     * @return The first Node with the desired title. If no Node has that
     * title among ancestor's descendants, then null is returned.
     */
    private Node searchHelper(final Node ancestor, final String title) {
    	Node tmp;
    	// First, check if this is our match
    	if (ancestor.getContent().equals(title)) {
    		return ancestor;
    	} else {
    		// Now let's check all of the ancestor's children
    	    for (Node child: ancestor.getChildren()) {
    		    tmp = searchHelper(child, title);
    		    // If the searchHelper found a match, return that.
    		    if (tmp != null) {
    		        return tmp;
				}
    		    // Otherwise, keep looking
    		}
    	}
    	// If we got here, then neither ancestor, nor any of its
    	// descendant were a match for the string, so return null
    	return null;
    }
    
    /**
     * This function prints an ASCII representation of the
     * tree to StdOut.
     * 
     * returns true upon successful print
     * returns false if tree is empty
     * 
     * @return Whether or not the print was successful
     */
    public boolean printTree() {
        if (root == null) {
            return false;
        }
        
        printBranch(root, "", "    ", System.out);
        
        return true;
    }
    
    /**
     * A helper function which helps print all of the Nodes in the tree
     * in a format that can be easily understood. The information is 
     * printed to a PrintStream (typically Standard Out).
     * 
     * @param parent The current position of printing.
     * @param treeStructure The structure of the tree up to this point.
     * @param next The next part of the treeStructure to add before
     * recursively calling this function on all of parent's children.
     * @param stream The output stream where the tree is being printed.
     */
    private void printBranch(final Node parent, final String treeStructure,
                             final String next, final PrintStream stream) {
        // Print the parent of this branch
        stream.println(treeStructure + "|--> " + parent.toString());
        
        
        // Then print all of its babies at one more depth than this
        int i;
        // The first children all have trailing "sticks"
        // for all descendants to carry
        for (i = 0; i < parent.getNumChildren() - 1; i++) {
            printBranch(parent.getChild(i), treeStructure
                        + next, "|    ", stream);
        }
        // The last child doesn't display a "stick"
        if (i < parent.getNumChildren()) {
            printBranch(parent.getChild(i), treeStructure
                        + next, "     ", stream);
        }
    }
}

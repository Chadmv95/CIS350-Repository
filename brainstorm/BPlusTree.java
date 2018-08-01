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
     * A private list of all nodes in this tree. This allows quick lookup
     * of information within the tree without needing to traverse the tree
     * recursively.
     */
    private List<Node> childrenOfRoot;

    /**
     * A constructor that allows the user to build a tree by providing
     * the name and data of the first Node as Strings.
     * 
     * @param name The desired name of the root node.
     * @param data The desired data content of the root nod.
     */
    public BPlusTree(final String name, final String data) {
        root = new Node(name, data);
        childrenOfRoot = new ArrayList<Node>();
    }
    
    /**
     * Constructor which creates a root node with default values
     * as determined by the Node class.
     */
    public BPlusTree() {
        root = new Node("Root", "Brainstorming Tree");
        childrenOfRoot = new ArrayList<Node>();
    }
    
    /**
     * Constructor which creates a new tree using a pre-existing
     * node as the root node.
     * 
     * @param rootNode The Node which becomes the root of the new tree.
     */
    public BPlusTree(final Node rootNode) {
        root = rootNode;
        childrenOfRoot = new ArrayList<Node>();
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
     * <br>
     * returns true upon successful add
     * <br>
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
        
        if (this.contains(parent)) {
            boolean successful = parent.addChild(child);
            if (successful) {
                // addChild was successful. Add the child to our private list of
                // Nodes for quick reference in the future.
                childrenOfRoot.add(child);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Adds node as a child of the root node. This function calls the
     * add(parent, child) method to perform the addition.
     * <br>
     * returns true upon successful add
     * <br>
     * returns false upon unsuccessful add & child is not added
     * 
     * @param child The Node to add to the tree
     * @return Whether or not the add was successful.
     */
    public boolean add(final Node child) {
        return this.add(root, child);
    }
    
    /**
     * Moves a node to a new parent if both nodes are already in the tree.
     * 
     * @param newParent The child's new parent.
     * @param child The node to be moved.
     * @return true if the move was successful, false otherwise.
     */
    public boolean move(final Node newParent, final Node child) {
        if (newParent == null || child == null) {
            return false;
        }
        
        if (this.contains(newParent) && this.contains(child)) {
            Node oldParent = child.getParent();
            if (oldParent.removeChild(child)) {
                // Success!
                if (newParent.addChild(child)) {
                    // Success!
                    return true;
                } else {
                    // Failure! ... Let's give the child back so it doesn't
                    // become an orphan.
                    oldParent.addChild(child);
                    return false;
                }
            } else {
                // Failure!
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Returns a List all of the Nodes in the tree EXCEPT the root node. This
     * list is in the order that the nodes were added to the tree. Such a list
     * should have all of its nodes created before creating parent-child
     * relationships between nodes.
     * <br>
     * Returns all of the children, grandchildren, great grandchildren, etc
     * of the requested Node.
     * 
     * @return A List of all descendants of the argument
     */
    public List<Node> getAllNodes() {
        return new ArrayList<Node>(childrenOfRoot);
    }

    /**
     * Returns a List all of the Nodes in the tree EXCEPT the root node. These
     * nodes are ordered by listing all of a node's children before that node's
     * siblings. This means that no Node is listed before its parent. Such a
     * list will be safe for reconstruction of the tree as the tree is being
     * read from the list.
     * <br>
     * Returns all of the children, grandchildren, great grandchildren, etc
     * of the requested Node.
     * 
     * @return A List of all descendants of the argument
     */
    public List<Node> getAllNodesInOrder() {
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
     * Returns true if the node is anywhere to be found in this tree. This
     * routine is frequently used to make sure a parent node is part of this
     * tree before adding a child node.
     * 
     * @param n The Node to be searched for in the tree.
     * @return true if the node is in the tree, false otherwise.
     */
    public boolean contains(final Node n) {
        if (root == null) {
            // No root means tree is empty, so it can't possibly contain n
            return false;
        } else if (root.equals(n)) {
            return true;
        } else {
            return childrenOfRoot.contains(n);
        }
    }
    
    /**
     * <p>Delete child and its descendants from the tree.</p>
     * <p><b>WARNING!</b> This method does not delete associated controller
     * and view classes. Should only be used in a system that contains only
     * model classes.<p>
     * 
     * returns true upon deletion
     * <br>
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
     * Removes this selected node from the tree, which assigns all children
     * of the node to the node's parent.
     * 
     * @param n The Node to be removed from the tree.
     * @return Whether or not the method was successful.
     */
    public boolean remove(final Node n) {
        if (n == null || n == root || !this.contains(n)) {
            return false;
        }
        
        for (Node child: n.getChildren()) {
            if (!this.move(n.getParent(), child)) {
                // We had a problem moving a node. Abort!
                return false;
            }
        }
        n.setParent(null);
        this.childrenOfRoot.remove(n);
        return true;
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
     * This helper function searches the branch of the tree, starting from
     * the passed Node <i>it</i>, to find the matching node.
     * <br>
     * If there is no matching node, null is returned.
     * <br>
     * If there are two matching nodes, the first found node
     * is the node which is returned.
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
     * <p>This function prints an ASCII representation of the
     * tree to StdOut.</p>
     * 
     * returns true upon successful print
     * <br>
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
        if (stream != null) {
            stream.println(treeStructure + "|--> " + parent.toString());
        } else {
            System.out.println(treeStructure + "|--> " + parent.toString());
        }
        
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

    /**
     * This will update the childrenOfRoot list to include all nodes in the
     * tree.
     */
    public void refreshNodeList() {
        childrenOfRoot = this.getAllNodesInOrder();
    }
}

package brainstorm;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;

/**
 * Class for Nodes in the B+ tree.
 * 
 * @author Brian Gilbert
 * @author Chad Vredvald
 */
public class Node {
    
    /**
     * The name/title of this Node.
     */
    private String name;
    
    /**
     * The content/data associated with this Node.
     */
    private String content;

    /**
     * A List of all of this Node's children.
     */
    private ArrayList<Node> children;
    
    /**
     * A reference to this Node's parent. This is important when deleting
     * this Node so that it can tell it's parent not to count this Node
     * among the parent's children.
     */
    private Node parent;
    
    /**
     * This variable contains information on the position of the node in
     * the GUI and document. This is important when saving or loading a
     * brainstorming document so that the document remains the same
     * between sessions.
     */
    private Rectangle bounds;

    /**
     * Constructor that builds a new Node using the Strings given.
     * 
     * @param name The desired name/title for the node.
     * @param content the desired data/content for the node.
     */
    Node(final String name, final String content) {
        this.name = name;
        this.content = content;
        children = new ArrayList<Node>(5);
        
        bounds = new Rectangle(10, 10, 100, 100);
    }
    
    /**
     * Constructor that builds a new Node using default values.
     * 
     * Name becomes "Insert Name"
     * Content becomes "Insert Content"
     */
    Node() {
        this.name = "Insert Name";
        this.content = "Insert Content";
        children = new ArrayList<Node>(5);
        
        bounds = new Rectangle(0, 0, 100, 100);
    }

    /**
     * Sets the name/title of the node.
     * 
     * @param newName The desired name/title.
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Sets the content/data of the node.
     * 
     * @param newContent The desired content/data.
     */
    public void setContent(final String newContent) {
        this.content = newContent;
    }
    
    /**
     * Sets the location and size of the Node as seen in 
     * the GUI and in the document.
     * 
     * @param bounds The desired location and size.
     */
    public void setBounds(final Rectangle bounds) {
        this.bounds = bounds;
    }
    
    /**
     * Retrieves the name/title of the node.
     * 
     * @return The name/title of the node.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Retrieves the content/data of the node.
     * 
     * @return The content/data of the node.
     */
    public String getContent() {
        return this.content;
    }
    
    /**
     * Retrieves the location and size of the node as seen 
     * in the GUI and in the document.
     * 
     * @return The location and size of the node.
     */
    public Rectangle getBounds() {
        return bounds;
    }
    
    @Override
    public String toString() {
        String retVal = getName();
        if (getContent() != null && !getContent().equals("")) {
            retVal += ": " + getContent();
        }
        return retVal;
    }
    
    /**
     * Adds child to the list of this node's children.
     * 
     * returns true upon success
     * returns false if child is null
     * 
     * @param child The node to be added
     * @return Whether or not the add was successful
     */
    public boolean addChild(final Node child) {
        if (child == null || child == this) {
            return false;
        }
        
        this.children.add(child);
        
        // Set the child's parent to this
        child.setParent(this);
        
        return true;
    }
    
    /**
     * Removes the requested node from among this node's children.
     * 
     * @param child The child node that is desired to be removed from this
     * node's list of children.
     * @return <code>true</code> if this node had child among its children
     */
    public boolean removeChild(final Node child) {
    	
    	if (child == null) {
    		return false;
    	}
    	
    	return this.children.remove(child);
    }
    
    /**
     * Retrieves the list of this node's children.
     * @return A List containing the children of this node.
     */
    public List<Node> getChildren() {
        return new ArrayList<Node>(this.children);
    }

    /**
     * Retrieves the child at the requested index.
     * 
     * @param index The index of the desired child node.
     * @return The requested child. If the index is out of range,
     * this function returns null instead
     */
    public Node getChild(final int index) {
        if (index < 0 || index >= children.size()) {
            return null;
        }
        
        return this.children.get(index);
    }
    
    /**
     * Retrieves the number of children of this node.
     * @return The number of children as an integer.
     */
    public int getNumChildren() {
        return this.children.size();
    }
    
    /**
     * Retrieves the parent of this node.
     * @return This node's parent
     */
    public Node getParent() {
    	return this.parent;
    }
    
    /**
     * Returns a list containing all of this node's descendants.
     * 
     * @return A list containing all of this node's descendants.
     */
    public List<Node> getAllDescendants() {
        ArrayList<Node> retVal = new ArrayList<Node>();
        for (Node n: this.children) {
            retVal.add(n);
            retVal.addAll(n.getAllDescendants());
        }
        return retVal;
    }
    
    /**
     * Sets the argument as this node's parent.
     * @param parent The new parent of this node.
     * @return true if this the parent-child relationship was properly created,
     * false otherwise.
     */
    public boolean setParent(final Node parent) {
        if (parent == this.parent) {
            // Already done, so return true.
            return true;
        }
        // Break ties with the old parent
        if (this.parent != null) {
            this.parent.removeChild(this);
        }
        if (parent == null) {
            this.parent = null;
            return true;
        }
        // We don't want any Futurama Fry incidents...
    	if (parent != this && !this.getAllDescendants().contains(parent)) {
    	    this.parent = parent;
        	if (!this.parent.getChildren().contains(this)) {
        	    return this.parent.addChild(this);
        	}
    	}
    	return false;
    }

}

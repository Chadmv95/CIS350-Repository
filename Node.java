import java.util.ArrayList;

/*
 * class for Nodes in the B+ tree
 */
public class Node {
    private String content;

    private ArrayList<Node> children;
    private Node parent;

    Node(final String content) {
        this.content = content;
        children = new ArrayList<Node>(5);
    }
    
    Node() {
        this.content = " ";
        children = new ArrayList<Node>(5);
    }

    public void setContent(final String content) {
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }

    /*
     * sets child to given parameter at given location
     * don't use this
     * 
     * returns true upon success
     * returns false if child is null
     */
    public boolean setChildren(final Node child, final int i) {
    	
    	if(child == null)
    	    return false;
    	
        // If we REALLY want to put the child at a specific index,
        // then we're going to have to add some null children to the
        // ArrayList. Otherwise, we will get an error from ArrayList
		while(this.children.size() <= i)
            this.children.add(null);
        
		//Add the child at location i
        this.children.set(i, child);
        
        // Now set the child's parent to this
        child.setParent(this);
        
        return true;
    }
    
    /*
     * sets child to parameter given at lowest possible location
     * 
     * returns true upon success
     * returns false if child is null
     */
    public boolean setChildren(final Node child) {
        if(child == null) 
            return false;
        
        // Find the first empty location, and put the child there
        int firstNullPosition = this.children.indexOf(null);
        if(firstNullPosition == -1)
            this.children.add(child);
        else
            this.children.set(firstNullPosition, child);
        
        // Set the child's parent to this
        child.setParent(this);
        
        return true;
    }
    
    public boolean removeChildren(final Node child) {
    	
    	if (child == null)
    		return false;
    	
    	this.children.remove(child);
    	return true;
    }

    public Node getChildren(final int i) {
        return this.children.get(i);
    }
    
    public int getNumChildren() {
        return this.children.size();
    }
    
    public Node getParent() {
    	return this.parent;
    }
    
    public void setParent(final Node p) {
    	this.parent = p;
    }

}

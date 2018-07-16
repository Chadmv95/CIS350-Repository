import java.util.ArrayList;

/*
 * class for Nodes in the B+ tree
 */
public class Node {
    private String content;

    private ArrayList<Node> children;
    private Node parent;

    Node (String content) {
        this.content = content;
        children = new ArrayList<Node>(5);
    }
    
    Node () {
        this.content = " ";
        children = new ArrayList<Node>(5);
    }

    public void setContent(String content) {
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
    public boolean setChildren(Node child, int i) {
    	
    	if(child != null) {
            // If we REALLY want to put the child at a specific index,
            // then we're going to have to add some null children to
            // the ArrayList
    		child.setParent(this);
    		
            while(this.children.size() <= i)
                this.children.add(null);
            
            this.children.set(i, child);
            return true;
        }
        else
            return false;
    }
    
    /*
     * sets child to parameter given at lowest possible location
     * 
     * returns true upon success
     * returns false if child is null
     */
    public boolean setChildren(Node child) {
        if(child != null) {
            int firstNullPosition = this.children.indexOf(null);
            
            child.setParent(this);
            
            if(firstNullPosition == -1)
                this.children.add(child);
            else
                this.children.set(firstNullPosition, child);
            
            return true;
        }
        else
            return false;
    }
    
    public boolean removeChildren(Node child){
    	
    	if(child == null)
    		return false;
    	else {
    		this.children.remove(child);
    		return true;
    	}
    	
    }

    public Node getChildren(int i) {
        return this.children.get(i);
    }
    
    public int getNumChildren()
    {
        return this.children.size();
    }
    
    public Node getParent() {
    	return this.parent;
    }
    
    public void setParent(Node p) {
    	this.parent = p;
    }

}
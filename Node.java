/*
 * class for Nodes in the B+ tree
 */
public class Node {
    private String content;

    private Node[] children;

    Node (String content) {
        this.content = content;
        children = new Node[] {null, null, null, null, null};
    }
    
    Node () {
        this.content = "";
        children = new Node[] {null, null, null, null, null};
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }

    /*
     * sets child to given parameter at given location
     * 
     * returns true upon success
     * returns false if child is null
     */
    public boolean setChildren(Node child, int i) {
        if(child != null) {
        	this.children[i] = child;
        	return true;
        }
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
	    	for(int i=0; i<5; i++) {
	    		if(this.children[i] != null) {
	    			this.children[i] = child;
	    			return true;
	    		}	
	    	}
    	}
    	return false;
    }

    public Node getChildren(int i) {
        return this.children[i];
    }

}
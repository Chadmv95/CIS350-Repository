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
    
    

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setChildren(Node child, int i) {
        this.children[i] = child;
    }

    public Node getChildren(int i) {
        return this.children[i];
    }

}
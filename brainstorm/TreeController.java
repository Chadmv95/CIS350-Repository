package brainstorm;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TreeController implements ComponentListener {

    /**
     * This is the global instance of the singleton tree controller
     */
    private static TreeController instance = new TreeController();
    
    private BPlusTree tree;
    
    private TreeView view;
    
    /**
     * Private constructor. This is a Singleton class.
     */
    private TreeController() { }
    
    public static TreeController getInstance() {
        return instance;
    }
    
    public void associateTree(final BPlusTree tree) {
        if (tree != null) {
            this.tree = tree;
            buildTreeInGUI();
        }
    }
    
    public void associateView(final TreeView view) {
        if (view != null) {
            this.view = view;
            buildTreeInGUI();
        }
    }
    
    private void buildTreeInGUI() {
        if(tree != null && view != null) {
            // Remove everything from the 
            view.removeAll();
            
            // Now, take the data from the tree and build it in the view
            // TODO Build the tree
        }
    }
    
    public void createNewNodeInTree(String name, String content) {
        NodeController nc = new NodeController(new Node(name, content),
                                               new NodeView());
    }
    
    public void addNodeToTree(Node node) {
        NodeController nc = new NodeController(node,
                                               new NodeView());
    }
    
    // ******************* ComponentListener
    @Override
    public void componentHidden(final ComponentEvent e) { /*Do Nothing*/ }

    @Override
    public void componentMoved(final ComponentEvent e) { /*Do Nothing*/ }

    @Override
    public void componentResized(final ComponentEvent e) {
        if (e.getComponent() == view.getTopLevelAncestor()) {
            
        }
    }

    @Override
    public void componentShown(final ComponentEvent e) { /*Do Nothing*/ }
}

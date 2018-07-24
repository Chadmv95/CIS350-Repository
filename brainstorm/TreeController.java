package brainstorm;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public final class TreeController implements ComponentListener {

    /**
     * This is the global instance of the singleton tree controller.
     */
    private static TreeController instance = new TreeController();
    
    /**
     * The model of the tree.
     */
    private BPlusTree tree;
    
    /**
     * The view of the tree.
     */
    private TreeView view;
    
    /**
     * A list of all of the NodeControllers so we have
     * references we can use to remove them if needed.
     */
    private List<NodeController> nodeControllers;
    
    /**
     * Private constructor. This is a Singleton class.
     */
    private TreeController() {
        nodeControllers = new ArrayList<NodeController>();
    }
    
    public static TreeController getInstance() {
        return instance;
    }
    
    public void associateTree(final BPlusTree tree) {
        if (tree != null) {
//            removeNodes();
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
//        if (tree != null && view != null) {
//            // Remove everything from the tree 
//            view.removeAll();
//            
//            // Now, take the data from the tree and build it in the view
//            // TODO Build the tree
//        }
    }
    
    public void createNodeAtRootOfTree(final String name,
                                       final String content) {
        addNode(tree.getRoot(), new Node(name, content));
    }
    
    public void addNodeAtRootOfTree(final Node node) {
        addNode(tree.getRoot(), node);
    }
    
    public void addNode(final Node parent, final Node child) {
        tree.add(parent, child);
        
        NodeController nc = new NodeController(child, new NodeView());
        nodeControllers.add(nc);
        
        view.addToDocumentPanel(nc.getView());
    }
    
    // ******************* ComponentListener
    @Override
    public void componentHidden(final ComponentEvent e) { /*Do Nothing*/ }

    @Override
    public void componentMoved(final ComponentEvent e) { /*Do Nothing*/ }

    @Override
    public void componentResized(final ComponentEvent e) {
//        if (e.getComponent() == view.getTopLevelAncestor()) {
//            // TODO Either implement the resizing, or change layout
//            // so that it happens automatically
//        }
    }

    @Override
    public void componentShown(final ComponentEvent e) { /*Do Nothing*/ }
}

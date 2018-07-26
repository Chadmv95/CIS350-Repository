package brainstorm;

import java.util.ArrayList;
import java.util.List;

/**
 * A controller class for the tree module. This class works with the TreeView
 * class which acts as the viewing class and the BPlusTree class which acts
 * as the model class.
 * 
 * @author Brian Gilbert
 *
 */
public final class TreeController {

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
    
    /**
     * This method returns the singleton instance of the TreeController class.
     * 
     * @return The singleton TreeController instance.
     */
    public static TreeController getInstance() {
        return instance;
    }

    /**
     * This associates a BPlusTree model with this controller object. This
     * method then calls the buildTreeInGUI() method to display the tree model
     * in the GUI.
     * 
     * @param tree The BPlusTree model class to associate.
     */
    public void associateTree(final BPlusTree tree) {
        if (tree != null) {
//            removeNodes();
            this.tree = tree;
            buildTreeInGUI();
        }
    }

    /**
     * Associates a TreeView class with this controller class. The nodes of
     * tree are displayed within the document panel of the TreeView class.
     * 
     * @param view The TreeView object to be associated with this controller.
     */
    public void associateView(final TreeView view) {
        if (view != null) {
            this.view = view;
            buildTreeInGUI();
        }
    }

    /**
     * This method creates the necessary NodeController and NodeView objects
     * and inserts them into the GUI so that all of the Nodes of the tree
     * model are displayed in the GUI in their proper locations.
     */
    private void buildTreeInGUI() {
//        if (tree != null && view != null) {
//            // Destroy all viewers and controllers for all old nodes
//            for (NodeController nc: nodeControllers) {
//                nc.getView().destroy(); // These methods don't exist yet...
//                nc.destroy();
//            }
//            
//            // Now, take the data from the tree and build it in the view,
//            // creating new controllers and viewers for each node in the tree.
//            
//            // TODO Build the tree
//        }
    }

    /**
     * Returns the BPlusTree model object.
     * 
     * @return The BPlusTree model.
     */
    public BPlusTree getTree() {
        return tree;
    }

    /**
     * Returns the TreeView view object.
     * 
     * @return The TreeView view.
     */
    public TreeView getView() {
        return view;
    }

    /**
     * Creates a new Node with the given Strings as values for the new Node.
     * This function calls the addNode function.
     * 
     * @param name A String containing the name of the new Node.
     * @param content A String containing the content of the new Node.
     */
    public void createNodeAtRootOfTree(final String name,
                                       final String content) {
        addNode(tree.getRoot(), new Node(name, content));
    }

    /**
     * Adds a pre-existing Node to the tree at the root of the tree.
     * This method calls the addNode method.
     * 
     * @param node The Node to add at the root of the tree.
     */
    public void addNodeAtRootOfTree(final Node node) {
        addNode(tree.getRoot(), node);
    }

    /**
     * Adds a pre-existing Node to the tree. This method also creates the
     * needed controller and view classes and adds the view to the TreeView
     * document so that it is displayed in the GUI.
     * 
     * @param parent The parent of the node to be added.
     * @param child The node to be added as a child of <i>parent</i>
     */
    public void addNode(final Node parent, final Node child) {
        if (!tree.contains(parent)) {
            addNodeAtRootOfTree(parent);
        }
        
        if (tree.add(parent, child)) {
            // Child was successfully added to parent in the tree
            // Now, let's build the rest
            NodeController nc = new NodeController(child, new NodeView());
            nodeControllers.add(nc);
            view.addToDocumentFront(nc.getView());
        }
    }
}

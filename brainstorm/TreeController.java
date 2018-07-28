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
     * The controller of the root node.
     */
    private NodeController root;
    
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
            this.tree = tree;
            root = new NodeController(this.tree.getRoot());
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
        if (tree != null && view != null) {
            // Remove all viewers from the GUI
            for (NodeController nc: nodeControllers) {
                view.removeFromDocument(nc.getView());
            }
            
            // Now, take the data from the tree and build it in the view,
            // creating new controllers and viewers for each node in the tree.
            
            // TODO Build the tree
        }
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
        if (root != null) {
            addNode(root, new NodeController(new Node(name, content)));
        }
    }

    /**
     * Adds a pre-existing Node to the tree at the root of the tree.
     * This method calls the addNode method.
     * 
     * @param node The Node to add at the root of the tree.
     */
    public void addNodeAtRootOfTree(final Node node) {
        if (root != null) {
            addNode(root, new NodeController(node, new NodeView()));
        }
    }

    /**
     * Adds a pre-existing Node to the tree at the root of the tree.
     * This method calls the addNode method.
     * 
     * @param nc The controller of the node to add at the root of the tree.
     */
    public void addNodeAtRootOfTree(final NodeController nc) {
        if (root != null) {
            addNode(root, nc);
        }
    }

    /**
     * Adds a pre-existing Node to the tree. This method also creates the
     * needed controller and view classes and adds the view to the TreeView
     * document so that it is displayed in the GUI.
     * 
     * @param parent The parent of the node to be added.
     * @param child The node to be added as a child of <i>parent</i>
     */
    public void addNode(final NodeController parent,
                        final NodeController child) {
        if (tree.contains(child.getNode())) {
            moveNode(parent, child);
        }
        
        if(tree != null && parent != null && 
        		child != null && parent != child){
            if (!tree.contains(parent.getNode())) {
                addNodeAtRootOfTree(parent);
            }
            
            if (tree.add(parent.getNode(), child.getNode())) {
                // Child was successfully added to parent in the tree
                nodeControllers.add(child);
                view.addToDocumentFront(child.getView());
                if (parent != root) {
                    view.addToDocumentRear(child.getLineToParent().getView());
                }
            }
        }
    }
    
    /**
     * Moves a node from its current parent to a new parent.
     * 
     * @param parent New parent.
     * @param child The child node to move.
     */
    public void moveNode(final NodeController parent,
                         final NodeController child) {
        if (tree == null || parent == null
                || child == null || parent == child) {
            return;
        }
        if (tree.contains(parent.getNode())
                && tree.contains(child.getNode())) {
            if (tree.move(parent.getNode(), child.getNode())) {
                // Child was successfully added to parent in the tree.
                // Now, let's reflect that in the GUI.
                
                child.setParent(parent);
                
                // We only display the line if the parent is not root
                if (parent == root) {
                    view.removeFromDocument(child.getLineToParent().getView());
                } else {
                    view.addToDocumentRear(child.getLineToParent().getView());
                }
            }
        }
    }
}

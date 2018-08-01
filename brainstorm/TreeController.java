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
            root = new NodeController(this.tree.getRoot(), null);
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
            Object[] ncArray = nodeControllers.toArray();
            for (Object o: ncArray) {
                NodeController nc = (NodeController) o;
                view.removeFromDocument(nc.getView());
                view.removeFromDocument(nc.getLineToParent().getView());
                nodeControllers.remove(nc);
            }
            
            for (Node n: tree.getAllNodesInOrder()) {
                buildNodeInGUI(n);
            }
        }
    }
    
    /**
     * This method creates the necessary controllers and views, and properly
     * associates them with one another.
     * 
     * @param n The node to "build".
     */
    private void buildNodeInGUI(final Node n) {
        NodeController nc = new NodeController(n, new NodeView());
        nodeControllers.add(nc);
        view.addToDocumentFront(nc.getView());
        
        NodeController parent = findController(n.getParent());
        nc.setParent(parent);
        if (parent != root) {
            view.addToDocumentRear(nc.getLineToParent().getView());
        }
    }
    
    /**
     * Finds the NodeController associated with the argument Node.
     * 
     * @param n The node model object
     * @return The controller associated with the node model object.
     */
    public NodeController findController(final Node n) {
        if (n == root.getNode()) {
            return root;
        }
        
        for (NodeController nc: nodeControllers) {
            if (nc.getNode().equals(n)) {
                return nc;
            }
        }
        return null;
    }
    
    /**
     * Finds the NodeController associated with the argument NodeView.
     * 
     * @param nv The node view object
     * @return The controller associated with the node model object.
     */
    public NodeController findController(final NodeView nv) {
        if (nv == null) {
            return null;
        }
        
        for (NodeController nc: nodeControllers) {
            if (nc.getView().equals(nv)) {
                return nc;
            }
        }
        return null;
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
            this.addNode(root, new NodeController(new Node(name, content),
                                             new NodeView()));
        }
    }
    
    /**
     * Creates a child node for the parent NodeController provided.
     * @param parent The controller of the parent node.
     */
    public void createChildOf(final NodeController parent) {
        this.addNode(parent, new NodeController(new Node(), new NodeView()));
    }

    /**
     * Adds a pre-existing Node to the tree at the root of the tree.
     * This method calls the addNode method.
     * 
     * @param node The Node to add at the root of the tree.
     */
    public void addNodeAtRootOfTree(final Node node) {
        if (root != null) {
            this.addNode(root, new NodeController(node, new NodeView()));
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
            this.addNode(root, nc);
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
        if (tree == null || parent == null || child == null) {
            return;
        }
        
        if (tree.contains(child.getNode())) {
            this.moveNode(parent, child);
            return;
        }
        
        if (parent != child) {
            if (!tree.contains(parent.getNode())) {
                this.addNodeAtRootOfTree(parent);
            }
            
            if (tree.add(parent.getNode(), child.getNode())) {
                // Child was successfully added to parent in the tree
                nodeControllers.add(child);
                child.setParent(parent);
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
                view.removeFromDocument(child.getLineToParent().getView());
                if (parent != root) {
                    view.addToDocumentRear(child.getLineToParent().getView());
                }
            }
        }
    }
    
    /**
     * Removes a node and moves all of its children to this node's parents. 
     * @param nc The controller of the node to be removed.
     */
    public void removeNode(final NodeController nc) {
        List<Node> children = nc.getNode().getChildren();
        if (tree.remove(nc.getNode())) {
            view.removeFromDocument(nc.getView());
            view.removeFromDocument(nc.getLineToParent().getView());
            nodeControllers.remove(nc);
            for (Node nChild: children) {
                moveNode(findController(nChild.getParent()),
                         findController(nChild));
            }
        }
    }
}

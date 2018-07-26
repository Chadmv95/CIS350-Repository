package brainstorm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * A controller class for the Node and NodeView classes. Uses the
 * Model-View-Presenter framework.
 * 
 * @author Brian Gilbert
 *
 */
public class NodeController implements MouseListener,
                                       MouseMotionListener,
                                       DocumentListener {
    /**
     * The node that this class controls.
     */
    private Node node;
    
    /**
     * The view that this class controls.
     */
    private NodeView view;
    
    /**
     * Variables used during dragging operation.
     */
    private int mouseStartingX = 0, mouseStartingY = 0,
                nodeOldX = 0, nodeOldY = 0;
    
    /**
     * Creates a "blank" NodeController.
     * 
     * You will want to create a new view and a new node
     * 
     */
    public NodeController() { }
    
    /**
     * Creates a NodeController that is associated with the supplied
     * Node model object.
     * 
     * @param node The Node model object that will be associated with this
     * controller.
     */
    public NodeController(final Node node) {
        this.node = node;
    }
    
    /**
     * Creates a NodeController that is associated with the supplied
     * NodeView view object.
     * 
     * @param view The NodeView view object that will be associated with
     * this controller.
     */
    public NodeController(final NodeView view) {
        associateView(view);
    }
    
    /**
     * Creates a NodeController that is associated with the supplied
     * Node model object and NodeView view object.
     * 
     * @param node The Node model object.
     * @param view The NodeView view object.
     */
    public NodeController(final Node node, final NodeView view) {
        this.node = node;
        associateView(view);
    }
    
    /**
     * Creates a NodeView object and associates it with this controller. The
     * created NodeView object is then returned so it can be used, if desired.
     * 
     * @return The created NodeView object.
     */
    public NodeView createNewView() {
        associateView(new NodeView());
        return this.view;
    }
    
    /**
     * Creates a new Node model object and associates it with this controller.
     * The created Node is returned so it can be used, if desired.
     * 
     * @return The created Node object.
     */
    public Node createNewNode() {
        associateNode(new Node());
        return this.node;
    }

    /**
     * Creates a new Node model object and immediately associates that Node
     * to this controller class. The created Node is returned so it can be
     * used elsewhere, if desired.
     * 
     * @param name A String containing the name of the new Node.
     * @param content A String containing the content of the new Node.
     * @return The new Node that was created.
     */
    public Node createNewNode(final String name, final String content) {
        this.node = new Node(name, content);
        return this.node;
    }

    /**
     * Associates a NodeView object with this controller. This controller
     * begins listening for events from the NodeView view object.
     * 
     * @param view The NodeView to be associated.
     */
    public void associateView(final NodeView view) {
        this.view = view;
        if (this.view != null) {
            this.view.addMouseListener(this);
            this.view.addMouseMotionListener(this);
            this.view.setNameFieldListener(this);
            this.view.setContentFieldListener(this);
            if (this.node != null) {
                this.view.setName(this.node.getName());
                this.view.setContent(this.node.getContent());
                this.view.setBounds(this.node.getBounds());
            }
        }
    }

    /**
     * Associates a Node model object with this controller. Updates
     * from the NodeView view object will then cause the controller
     * to reflect those changes in the Node model object.
     * 
     * @param node The Node model object to associate with this controller.
     */
    public void associateNode(final Node node) {
        this.node = node;
        if (this.node != null && this.view != null) {
            this.view.setName(this.node.getName());
            this.view.setContent(this.node.getContent());
            this.view.setBounds(this.node.getBounds());
        }
    }

    /**
     * Returns the NodeView view object that is associated with this
     * controller.
     * 
     * @return The NodeView view object associated with this controller.
     */
    public NodeView getView() {
        return view;
    }

    /**
     * Returns the Node model object associated with this controller.
     * 
     * @return The Node model object associated with this controller.
     */
    public Node getNode() {
        return node;
    }
    
    
    // ******************* MouseListener Methods ***************************
    @Override
    public void mouseClicked(final MouseEvent e) {
//        if (arg0.isPopupTrigger()) {
//            // TODO Implement pop-up menu for editing node
//        }
    }

    @Override
    public void mouseEntered(final MouseEvent e) { /*Do nothing*/ }

    @Override
    public void mouseExited(final MouseEvent e) { /*Do nothing*/ }

    @Override
    public void mousePressed(final MouseEvent e) {
        // Store the location information so that we can reference it
        // as we drag the mouse around on the screen
        nodeOldX = view.getX();
        nodeOldY = view.getY();
        mouseStartingX = e.getXOnScreen();
        mouseStartingY = e.getYOnScreen();
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (e.getComponent() == this.view) { 
            // Update the value stored in the Node so it can be saved
            this.node.setBounds(this.view.getBounds());
        }
    }

    
    // ******************* MouseMotionListener Methods ************************
    @Override
    public void mouseDragged(final MouseEvent e) {
        // Update the location on the screen based on where the mouse is
        // now compared to where it was when we first clicked.
        view.setLocation(nodeOldX + e.getXOnScreen() - mouseStartingX,
                         nodeOldY + e.getYOnScreen() - mouseStartingY);
    }

    @Override
    public void mouseMoved(final MouseEvent e) { /*Do nothing*/ }

    // ******************* DocumentListener Methods ***************************
    @Override
    public void changedUpdate(final DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertUpdate(final DocumentEvent e) {
        updateNode(e.getDocument());
    }

    @Override
    public void removeUpdate(final DocumentEvent e) {
        updateNode(e.getDocument());
    }
    
    /**
     * Checks a Document object to see which parts of the Node object
     * should be updated, then updates those fields in the Node object.
     * 
     * @param doc The document containing the updates to the Node fields.
     */
    private void updateNode(final Document doc) {
        try {
            if (view.isNameFieldDocument(doc)) {
                node.setName(doc.getText(0, doc.getLength()));
            }
            if (view.isContentFieldDocument(doc)) {
                node.setContent(doc.getText(0, doc.getLength()));
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

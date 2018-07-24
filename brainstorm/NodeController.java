package brainstorm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NodeController implements MouseListener, MouseMotionListener,
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
    
    public NodeController(final Node node) {
        this.node = node;
    }
    
    public NodeController(final NodeView view) {
        associateView(view);
    }
    
    public NodeController(final Node node, final NodeView view) {
        this.node = node;
        associateView(view);
    }
    
    public NodeView createNewView() {
        associateView(new NodeView());
        return this.view;
    }
    
    public Node createNewNode() {
        associateNode(new Node());
        return this.node;
    }
    
    public Node createNewNode(final String name, final String content) {
        this.node = new Node(name, content);
        return this.node;
    }
    
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
            }
        }
    }
    
    public void associateNode(final Node node) {
        this.node = node;
        if (this.node != null && view != null) {
            view.setName(node.getName());
            view.setContent(node.getContent());
        }
    }
    
    public NodeView getView() {
        return view;
    }
    
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
        nodeOldX = view.getX();
        nodeOldY = view.getY();
        
        mouseStartingX = e.getXOnScreen();
        mouseStartingY = e.getYOnScreen();
    }

    @Override
    public void mouseReleased(final MouseEvent e) { /*Do nothing*/ }

    
    // ******************* MouseMotionListener Methods ************************
    @Override
    public void mouseDragged(final MouseEvent e) {
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeUpdate(final DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }

}

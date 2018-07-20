package brainstorm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentListener;

/**
 * 
 * @author brian
 *
 */
@SuppressWarnings("serial")
public class NodeView extends JPanel  implements MouseListener,
                                                 MouseMotionListener {

    /**
     * Container where the user can input the name/title.
     */
    private JTextField nameField;

    /**
     * Container where the user can input the content/data.
     */
    private JTextArea contentField;

    /**
     * Color used when drawing the border of the node.
     */
    private Color borderColor = Color.DARK_GRAY; //Default Color

    /**
     * Variables used during dragging operation.
     */
    private int mouseStartingX = 0, mouseStartingY = 0,
                nodeOldX = 0, nodeOldY = 0;

    /**
     * Constructor that creates a viewer with default text. 
     */
    public NodeView() {
        nameField = new JTextField("Insert Name");
        contentField = new JTextArea("Insert Content");
        
        buildNodeView();
    }

    /**
     * Constructor that creates a viewer using information from a node. 
     * 
     * @param node The node whose information will be used to initialize
     * this NodeView object.
     */
    public NodeView(final Node node) {
        nameField = new JTextField(node.getName());
        contentField = new JTextArea(node.getContent());
        
        buildNodeView();
    }

    /**
     * A helper function for the constructors. This does all of the
     * work of making a viewer that displays the interactive content
     * of the GUI. Since this is common among all constructors, it
     * is broken out into its own function to avoid redundancy.
     */
    private void buildNodeView() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        this.add(nameField);
        
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JScrollPane sp = new JScrollPane(contentField);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.
                                        HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.
                                      VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(200, 100));
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        this.add(sp);
        
        setBorderColor(this.borderColor);
        
        this.setVisible(true);
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Sets the text in the name/title component.
     * 
     * @param name The desired text to place in the name/title field.
     */
    public void setName(final String name) {
        this.nameField.setText(name);
    }

    /**
     * Retrieves the text from the name/title component.
     * 
     * @return A String containing the text in the name/title component.
     */
    public String getName() {
        return this.nameField.getText();
    }

    /**
     * Sets the text in the content/data field.
     * 
     * @param content The desired text to place in the content/data field.
     */
    public void setContent(final String content) {
        this.contentField.setText(content);
    }

    /**
     * Retrieves the text from the content/data field.
     * 
     * @return A String containing the text in the content/data field.
     */
    public String getContent() {
        return this.contentField.getText();
    }

    /**
     * Sets the color of the border.
     * 
     * @param c The desired color for the border of this node.
     */
    public void setBorderColor(final Color c) {
        this.borderColor = c;
        this.setBorder(BorderFactory.createLineBorder(c, 3, true));
    }
    
    /**
     * Associates a DocumentListener with the text in the name/title field.
     * 
     * @param listener The listener that will be associated with this
     * NodeView's name/title field.
     */
    public void setNameFieldListener(final DocumentListener listener) {
        this.nameField.getDocument().addDocumentListener(listener);
    }
    
    /**
     * Associates a DocumentListener with the text in the content/data field.
     * 
     * @param listener The listener that will be associated with this
     * NodeView's content/data field.
     */
    public void setContentFieldListener(final DocumentListener listener) {
        this.contentField.getDocument().addDocumentListener(listener);
    }

    @Override
    public void mouseClicked(final MouseEvent arg0) {
//        if (arg0.isPopupTrigger()) {
//            // TODO Implement pop-up menu for editing node
//        }
    }

    @Override
    public void mouseEntered(final MouseEvent arg0) { /*Do nothing*/ }

    @Override
    public void mouseExited(final MouseEvent arg0) { /*Do nothing*/ }

    @Override
    public void mousePressed(final MouseEvent arg0) {
        nodeOldX = this.getX();
        nodeOldY = this.getY();
        
        mouseStartingX = arg0.getXOnScreen();
        mouseStartingY = arg0.getYOnScreen();
    }

    @Override
    public void mouseReleased(final MouseEvent arg0) { /*Do nothing*/ }

    @Override
    public void mouseDragged(final MouseEvent arg0) {
        this.setLocation(nodeOldX + arg0.getXOnScreen() - mouseStartingX,
                         nodeOldY + arg0.getYOnScreen() - mouseStartingY);
    }

    @Override
    public void mouseMoved(final MouseEvent arg0) { /*Do nothing*/ }
}

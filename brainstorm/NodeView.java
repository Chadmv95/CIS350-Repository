package brainstorm;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class NodeView extends JPanel  implements MouseListener,
                                                 MouseMotionListener {
    
    private JTextField nameField;
    private JTextArea contentField;
    private Color borderColor = Color.DARK_GRAY; //Default Color
    
    private int mouseStartingX = 0, mouseStartingY = 0,
                nodeOldX = 0, nodeOldY = 0;
    
    public NodeView() {
        nameField = new JTextField("Insert Name");
        contentField = new JTextArea("Insert Content");
        
        buildNodeView();
    }
    
    public NodeView(final Node n) {
        nameField = new JTextField(n.getName());
        contentField = new JTextArea(n.getContent());
        
        buildNodeView();
    }
    
    private void buildNodeView() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        this.add(nameField);
        
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JScrollPane sp = new JScrollPane(contentField);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.
                                        HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.
                                      VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(200,100));
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        this.add(sp);
        
        setBorderColor(this.borderColor);
        
        this.setVisible(true);
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void setName(final String name) {
        this.nameField.setText(name);
    }
    
    public String getName() {
        return this.nameField.getText();
    }
    
    public void setContent(final String content) {
        this.contentField.setText(content);
    }
    
    public String getContent() {
        return this.contentField.getText();
    }
    
    public void setBorderColor(final Color c) {
        this.setBorder(BorderFactory.createLineBorder(c, 3, true));
    }
    
    public void setNameFieldListener(final DocumentListener listener) {
        this.nameField.getDocument().addDocumentListener(listener);
    }
    
    public void setContentFieldListener(final DocumentListener listener) {
        this.contentField.getDocument().addDocumentListener(listener);
    }

    @Override
    public void mouseClicked(final MouseEvent arg0) {
        if (arg0.isPopupTrigger()) {
            // TODO Implement pop-up menu for editing node
        }
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

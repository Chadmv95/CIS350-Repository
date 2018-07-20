import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class NodeView extends JPanel {
    
    private JTextField nameField;
    private JTextArea contentField;
    private Color borderColor = Color.DARK_GRAY; //Default Color
    
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
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(200,100));
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        this.add(sp);
        
        setBorderColor(this.borderColor);
        
        this.setVisible(true);
    }
    
    public void setName(String name) {
        this.nameField.setText(name);
    }
    
    public String getName() {
        return this.nameField.getText();
    }
    
    public void setContent(String content) {
        this.contentField.setText(content);
    }
    
    public String getContent() {
        return this.contentField.getText();
    }
    
    public void setBorderColor(Color c) {
        this.setBorder(BorderFactory.createLineBorder(c, 3, true));
    }
    
    public void setNameFieldListener(final DocumentListener listener) {
        this.nameField.getDocument().addDocumentListener(listener);
    }
    
    public void setContentFieldListener(final DocumentListener listener) {
        this.contentField.getDocument().addDocumentListener(listener);
    }
}

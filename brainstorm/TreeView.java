package brainstorm;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

/**
 * A viewing class for the BPlusTree.
 * 
 * @author Brian Gilbert
 *
 */
@SuppressWarnings("serial")
public class TreeView extends JPanel {

    private JScrollPane scrollPane;
    private DocumentView documentPanel;

    /**
     * Constructor that creates a JPanel for displaying the tree.
     */
    public TreeView() {
        super(new BorderLayout());
        
        this.documentPanel = new DocumentView(new Dimension(1000, 700));
        this.scrollPane = new JScrollPane(documentPanel);
        this.scrollPane.setPreferredSize(new Dimension(500, 500));
        this.add(this.scrollPane, BorderLayout.CENTER);
        
        this.documentPanel.setLayout(null);
        this.documentPanel.setBackground(Color.WHITE);
        this.documentPanel.setVisible(true);
        
        this.setVisible(true);
    }
    
    public void addToDocumentPanel(final Component comp) {
        documentPanel.add(comp, 0);
        documentPanel.revalidate();
        documentPanel.repaint(comp.getBounds());
    }
}

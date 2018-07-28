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
public class TreeView {

    /**
     * The outermost container component.
     */
    private JPanel containerPanel;

    /**
     * The scrolling container component.
     */
    private JScrollPane scrollPane;

    /**
     * The document panel component where the NodeViews are placed.
     */
    private DocumentView documentPanel;

    /**
     * Constructor that creates a JPanel for displaying the tree.
     */
    public TreeView() {
        containerPanel = new JPanel(new BorderLayout());
        
        this.documentPanel = new DocumentView(new Dimension(1000, 700));
        this.scrollPane = new JScrollPane(documentPanel);
        this.scrollPane.setPreferredSize(new Dimension(500, 500));
        containerPanel.add(this.scrollPane, BorderLayout.CENTER);
        
        this.documentPanel.setLayout(null);
        this.documentPanel.setBackground(Color.WHITE);
        this.documentPanel.setVisible(true);
        
        containerPanel.setVisible(true);
    }
    
    /**
     * Returns the component that parent components in the GUI should hold.
     * This is the JPanel that has the JScrollPane and the document panel
     * inside of it.
     * 
     * @return JPanel that holds the viewing object for the tree/document.
     */
    public Component getComponent() {
        return containerPanel;
    }
    
    /**
     * Adds a component to the document panel at index zero. It will
     * appear on top of all other components. Use this to add new
     * NodeView objects to the TreeView.
     * 
     * @param comp Component to add.
     */
    public void addToDocumentFront(final Component comp) {
        documentPanel.add(comp, 0);
        documentPanel.revalidate();
        documentPanel.repaint(comp.getBounds());
    }
    
    /**
     * Adds a component to the document panel at the end of the list.
     * The new component will appear beneath all other components. Use
     * this to add lines which connect the NodeViews.
     * @param comp Component to add.
     */
    public void addToDocumentRear(final Component comp) {
        documentPanel.add(comp);
        documentPanel.revalidate();
        documentPanel.repaint(comp.getBounds());
    }
    
    /**
     * Removes a component from the document panel.
     * 
     * @param comp Component to add.
     */
    public void removeFromDocument(final Component comp) {
        documentPanel.remove(comp);
        documentPanel.revalidate();
        documentPanel.repaint(comp.getBounds());
    }
    
    /**
     * Returns the current dimensions of the "document" where the NodeViews
     * are drawn.
     * 
     * @return A Dimension containing the document dimensions.
     */
    public Dimension getDocumentDimensions() {
        return new Dimension(this.documentPanel.getPreferredSize());
    }
    
    /**
     * Changes the dimensions of the "document" to match the passed values.
     * 
     * @param d The new dimensions for the document.
     */
    public void changeDocumentDimensions(final Dimension d) {
        if (d != null) {
            this.documentPanel.setPreferredSize(d);
        }
    }
}

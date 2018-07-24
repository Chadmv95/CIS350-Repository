package brainstorm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

public abstract class ApplicationController {
    
    /**
     * The starting point of the program. This will create all of the
     * necessary controllers, models, and views so that the program
     * can run.
     * 
     * @param args Arguments passed to the program from the system.
     */
    public static void main(final String[] args) {
        buildWindow();
    }
    
    /**
     * Builds the physical window that the user can then interact with.
     */
    private static void buildWindow() {
        JFrame window = new JFrame("Brainstorm Helper");
        window.setMinimumSize(new Dimension(500, 500));
        
        MenuBarView menuBar = new MenuBarView();
        window.setJMenuBar(menuBar);
        
        TreeView treeView = new TreeView();
        TreeController.getInstance().associateView(treeView);
        
        window.add(treeView, BorderLayout.CENTER);
        
        Node node = new Node();
        NodeView nodeView = new NodeView(node);
        Rectangle bounds = new Rectangle(20, 20, 200, 100);
        nodeView.setBounds(bounds);
        node.setBounds(bounds);
        NodeController nodeController = new NodeController(node, nodeView);
        treeView.addToDocumentPanel(nodeController.getView());
        
        node = new Node("Second One", "Other Cool Stuff");
        nodeView = new NodeView(node);
        bounds = new Rectangle(300, 50, 150, 150);
        nodeView.setBounds(bounds);
        node.setBounds(bounds);
        nodeController = new NodeController(node, nodeView);
        treeView.addToDocumentPanel(nodeController.getView());
        
        window.pack();
        window.setVisible(true);
    }
}

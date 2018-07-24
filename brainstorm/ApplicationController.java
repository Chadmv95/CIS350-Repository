package brainstorm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class ApplicationController {
    
    private static ApplicationController instance = new ApplicationController();
    
    private JFrame window;
    
    private ApplicationController() {
        window = new JFrame("Brainstorm Helper");
    }
    
    public static ApplicationController getInstance() {
        return instance;
    }
    
    public void begin() {
        buildWindow();
    }
    
    /**
     * Builds the physical window that the user can then interact with.
     */
    private void buildWindow() {
        
        this.window.setMinimumSize(new Dimension(500, 500));
        
        MenuBarView menuBar = new MenuBarView();
        this.window.setJMenuBar(menuBar);
        
        TreeView treeView = new TreeView();
        TreeController.getInstance().associateView(treeView);
        
        this.window.add(treeView, BorderLayout.CENTER);
        
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
        
        this.window.pack();
        this.window.setVisible(true);
    }
}

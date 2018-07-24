package brainstorm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * A Singleton class that performs tasks of controlling the application.
 * 
 * @author Brian Gilbert
 *
 */
public final class ApplicationController {
    
    /**
     * Instance of the singleton class.
     */
    private static ApplicationController instance = new ApplicationController();
    
    /**
     * The application window that contains the application.
     */
    private JFrame window;
    
    /**
     * Private constructor for the singleton class.
     */
    private ApplicationController() {
        window = new JFrame("Brainstorm Helper");
    }
    
    /**
     * Returns the instance of this singleton class.
     * 
     * @return The single instance of the ApplicationController class.
     */
    public static ApplicationController getInstance() {
        return instance;
    }
    
    /**
     * Begins the application.
     * Calls buildWindow()
     */
    public void begin() {
        buildWindow();
    }
    
    /**
     * Builds the physical window that the user can then interact with.
     */
    private void buildWindow() {
        this.window.setMinimumSize(new Dimension(200, 200));
        
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
    
    /**
     * Returns the handle of the window that contains the application.
     * 
     * @return The JFrame container that houses the application window.
     */
    public JFrame getWindow() {
        return window;
    }
}

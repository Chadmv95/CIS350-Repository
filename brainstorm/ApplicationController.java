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
    
    private MenuBarController mbc;
    
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
        
        mbc = new MenuBarController(new MenuBarView());
        this.window.setJMenuBar(mbc.getView());
        
        TreeView treeView = new TreeView();
        BPlusTree tree = new BPlusTree();
        TreeController.getInstance().associateTree(tree);
        TreeController.getInstance().associateView(treeView);
        
        this.window.add(treeView, BorderLayout.CENTER);
        
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

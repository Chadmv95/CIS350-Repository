package brainstorm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
 
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

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
     * The controller class for the menu bar.
     */
    private MenuBarController mbc;
    
    /**
     * This creates the dialog boxes for opening and saving files.
     */
    private JFileChooser fileChooser;

    /**
     * The current file that the application is working with.
     */
    private File currentFile;
    
    /**
     * Private constructor for the singleton class.
     */
    private ApplicationController() {
        window = new JFrame("Brainstorm Helper");
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(
                System.getProperty("user.home")));
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
        
        this.window.add(treeView.getComponent(), BorderLayout.CENTER);
        
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
    
    /**
     * This method discards the current workspace and creates a new file.
     */
    public void newFile() {
        int result = JOptionPane.showConfirmDialog(window,
                "Are you sure you want to create a new file?\n"
                + "All changes to the current file will be lost.");
        
        if (result == JOptionPane.YES_OPTION) {
            currentFile = null;
            TreeController.getInstance().associateTree(new BPlusTree());
        }
    }
    
    /**
     * This method discards the current workspace and opens an existing file.
     */
    public void openFile() {
        int result = fileChooser.showOpenDialog(ApplicationController
                .getInstance().getWindow());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                currentFile = selectedFile;
                // TODO Implement functionality to turn a file into a BPlusTree
            }
        }
    }
    
    /**
     * This method saves the current workspace under the current file name.
     * If there is no current file name, saveFileAs is called to save the
     * workspace under a new file name.
     */
    public void saveFile() {
        if (currentFile != null) {
            saveWorkspace(currentFile);
        } else {
            saveFileAs();
        }
    }
    
    /**
     * This method saves the current workspace under a new file name.
     */
    public void saveFileAs() {
        int result = fileChooser.showSaveDialog(ApplicationController
                                            .getInstance().getWindow());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                currentFile = selectedFile;
                saveWorkspace(currentFile);
            }
        }
    }
    
    /**
     * Private helper function that saves the workspace under the file contained
     * in currentFile.
     */
    private void saveWorkspace(File file_path) {
        // TODO Add functionality to turn the current BPlusTree into a file.
    	System.out.println("saveWorkspace Called");
    	System.out.println(file_path);
    	BPlusTree temp = TreeController.getInstance().getTree();
    	
    	
    	try {
    		FileWriter file = new FileWriter(file_path);
    		
    		
			writeToJSON(file, temp);
			
			file.flush();
	    	file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Save Successful");
		}
    }
    
    private void writeToJSON(FileWriter file, BPlusTree tree) throws IOException {
    	
    	file.write("{\"" + tree.getRoot().getName()+ "\"}");
    	
    }
}

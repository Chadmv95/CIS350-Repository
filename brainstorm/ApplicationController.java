package brainstorm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.Iterator;
import java.util.Map;

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
                try {
                	BPlusTree tree = openfile(selectedFile);
                	TreeController.getInstance().associateTree(tree);
                } catch (Exception e) {
                	System.out.println("Caught Error: openFile");
                }
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
     * 
     * @param fileName The File that the the current tree is written into.
     */
    private void saveWorkspace(final File fileName) {
        System.out.println("saveWorkspace Called");
    	System.out.println(fileName);
    	BPlusTree temp = TreeController.getInstance().getTree();
    	
    	
    	try {
    		FileWriter file = new FileWriter(fileName);
    		
			writeToJSON(file, temp);
			
			file.flush();
	    	file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Save Successful");
		}
    }
    
    /**
     * Utility function to help writing saves into JSON.
     * 
     * @param file The file to write the JSON to.
     * @param tree The tree to change into a JSON.
     * 
     * @throws IOException If the 
     */
    private void writeToJSON(final FileWriter file, final BPlusTree tree)
                                                        throws IOException {
    	
    	file.write("{\"" + tree.getRoot().getName() + "\":");
    	file.write("{\"Children\":[");
    	int i = 0;
    	for (Node node : tree.getRoot().getChildren()) {
    		writeChildren(file, node, i, tree.getRoot().getNumChildren());
    		i++;
    	}
    	
    	file.write("]}");
    	file.write("}");
    }
    
    private void writeChildren(final FileWriter file, final Node node,
                        final int index, final int size) throws IOException {
    	//begin writing node
    	
    	file.write("{");
		file.write("\"Name\":\"" + node.getName() + "\",");
		file.write("\"Content\":\"" + node.getContent() + "\",");
		file.write("\"Bounds\":\"" + node.getBounds() + "\",");
		
		//call for printing child
		file.write("\"Children\":[");
		if (node.getNumChildren() > 0) {
			
			int i = 0;
			for (Node childNode : node.getChildren()) {
    			try {
    				writeChildren(file, childNode, i,
    				              node.getNumChildren());
    				} catch (IOException e) {
	    				e.printStackTrace();
	    		}
    			i++;
			}
		}
		file.write("]");
		
		//finish writing node
		file.write("}");
		if (index != size - 1) {
			file.write(",");
		}
    }
    
    /**
     * Open file.
     * 
     * @param selectedFile The file to open.
     * 
     * @return BPlusTree with contents of load file
     * 
     * @throws Exception This throws an exception.
     */
    private BPlusTree openfile(final File selectedFile) throws Exception { 
    	
    	 BPlusTree tree = new BPlusTree();
    	
    	 System.out.println("we are in");
    	 Object obj = new JSONParser().parse(new FileReader(selectedFile));
    	 JSONObject jo = (JSONObject) obj;
    	 
    	 JSONObject root = (JSONObject) jo.get("Root");
    	 JSONArray children = (JSONArray) root.get("Children");
    	 
    	 for (Object joNode : children) {
    		 tree.add(parseNode(joNode));
    	 }
    	 
    	 tree.printTree();
    	 
    	 return tree;
    	 
    }
    
    
    /* Recursive function to open nodes with children 
     * 
     */
    private Node parseNode(Object input) {
    	 Node node = new Node();
		 String domain;
    	 Rectangle rect = new Rectangle();
		 
		 node.setName((String) ((JSONObject) input).get("Name"));
		 node.setContent((String) ((JSONObject) input).get("Content"));
		 domain = ((String) ((JSONObject) input).get("Bounds"));
		// Parse Domain
		 String[] corrd = domain.split("[\\[,\\]=]"); 
		 rect.setBounds(
				 Integer.parseInt(corrd[2]),
				 Integer.parseInt(corrd[4]),
				 Integer.parseInt(corrd[6]),
				 Integer.parseInt(corrd[8]) 
		 ); 
		 node.setBounds(rect);
		 
		 JSONObject listKids = (JSONObject) input;
	   	 JSONArray children = (JSONArray) listKids.get("Children");
	   	 
	   	 if (children.size() > 0) {
	   		for (Object joNode : children) {
	    		 node.addChild(parseNode(joNode));
	    	 }
	   	 }
    	
    	return (Node) node; 
    }
}

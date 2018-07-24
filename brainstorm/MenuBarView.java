package brainstorm;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This is the GUI element for the Menu Bar in the brainstorming application.
 * 
 * @author Brian Gilbert
 *
 */
@SuppressWarnings("serial")
public class MenuBarView extends JMenuBar {
    
    /**
     * The menu items within the menu bar.
     */
    private JMenuItem newFile, openFile, saveFile, printFile,
              exit, createNode, editNode, deleteNode;
    
    /**
     * A constructor for the menu bar. 
     */
    public MenuBarView() {
        super();
        buildMenuBar();
    }
    
    /**
     * Builds the menu bar. Called from the constructor(s).
     */
    private void buildMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        this.add(fileMenu);
        
        newFile = new JMenuItem("New");
        newFile.setMnemonic(KeyEvent.VK_N);
        openFile = new JMenuItem("Open");
        openFile.setMnemonic(KeyEvent.VK_O);
        saveFile = new JMenuItem("Save");
        saveFile.setMnemonic(KeyEvent.VK_S);
        printFile = new JMenuItem("Print");
        printFile.setMnemonic(KeyEvent.VK_P);
        exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(printFile);
        fileMenu.add(exit);
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        this.add(editMenu);
        
        createNode = new JMenuItem("Create Node");
        createNode.setMnemonic(KeyEvent.VK_C);
        editNode = new JMenuItem("Edit Node");
        editNode.setMnemonic(KeyEvent.VK_E);
        deleteNode = new JMenuItem("Delete Node");
        deleteNode.setMnemonic(KeyEvent.VK_D);
        
        editMenu.add(createNode);
        editMenu.add(editNode);
        editMenu.add(deleteNode);
    }
    
    
    /**
     * Adds a single listener to all of the the menu items within the menu bar.
     * 
     * @param al The ActionListener that is to be added as a listener
     * to all of the items within the menu.
     */
    public void addMenuBarListener(final ActionListener al) {
        newFile.addActionListener(al);
        openFile.addActionListener(al);
        saveFile.addActionListener(al);
        printFile.addActionListener(al);
        exit.addActionListener(al);
        createNode.addActionListener(al);
        editNode.addActionListener(al);
        deleteNode.addActionListener(al);
    }
}

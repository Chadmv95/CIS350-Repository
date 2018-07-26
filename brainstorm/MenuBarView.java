package brainstorm;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
    private JMenuItem newFile, openFile, saveFile, saveFileAs, printFile,
              quit, createNode, editNode, deleteNode;
    
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
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                                    InputEvent.CTRL_DOWN_MASK));
        openFile = new JMenuItem("Open");
        openFile.setMnemonic(KeyEvent.VK_O);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                                                    InputEvent.CTRL_DOWN_MASK));
        saveFile = new JMenuItem("Save");
        saveFile.setMnemonic(KeyEvent.VK_S);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                                    InputEvent.CTRL_DOWN_MASK));
        saveFileAs = new JMenuItem("Save As...");
        saveFileAs.setMnemonic(KeyEvent.VK_A);
        
        printFile = new JMenuItem("Print");
        printFile.setMnemonic(KeyEvent.VK_P);
        printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                                                    InputEvent.CTRL_DOWN_MASK));
        quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                                    InputEvent.CTRL_DOWN_MASK));
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveFileAs);
        fileMenu.add(printFile);
        fileMenu.add(quit);
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        this.add(editMenu);
        
        createNode = new JMenuItem("Create Node");
        createNode.setMnemonic(KeyEvent.VK_C);
        createNode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                       InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        editNode = new JMenuItem("Edit Node");
        editNode.setMnemonic(KeyEvent.VK_E);
        editNode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                                                    InputEvent.CTRL_DOWN_MASK));
        deleteNode = new JMenuItem("Delete Node");
        deleteNode.setMnemonic(KeyEvent.VK_D);
        deleteNode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                                                    InputEvent.CTRL_DOWN_MASK));
        
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
        quit.addActionListener(al);
        createNode.addActionListener(al);
        editNode.addActionListener(al);
        deleteNode.addActionListener(al);
    }
}

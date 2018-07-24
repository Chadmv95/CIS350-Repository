package brainstorm;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBarView extends JMenuBar {
    
    public MenuBarView() {
        super();
        buildMenuBar();
    }
    
    private void buildMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        this.add(fileMenu);
        
        JMenuItem newFile = new JMenuItem("New");
        newFile.setMnemonic(KeyEvent.VK_N);
        JMenuItem openFile = new JMenuItem("Open");
        openFile.setMnemonic(KeyEvent.VK_O);
        JMenuItem saveFile = new JMenuItem("Save");
        saveFile.setMnemonic(KeyEvent.VK_S);
        JMenuItem printFile = new JMenuItem("Print");
        printFile.setMnemonic(KeyEvent.VK_P);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(printFile);
        fileMenu.add(exit);
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        this.add(editMenu);
        
        JMenuItem createNode = new JMenuItem("Create Node");
        createNode.setMnemonic(KeyEvent.VK_C);
        JMenuItem editNode = new JMenuItem("Edit Node");
        editNode.setMnemonic(KeyEvent.VK_E);
        JMenuItem deleteNode = new JMenuItem("Delete Node");
        deleteNode.setMnemonic(KeyEvent.VK_D);
        
        editMenu.add(createNode);
        editMenu.add(editNode);
        editMenu.add(deleteNode);
    }
    
    public void addMenuBarListener(final ActionListener al) {
//        for (MenuElement e: this.getSubElements()) {
//            // TODO Implement the means of adding a listener to the menu bar
//        }
    }
}

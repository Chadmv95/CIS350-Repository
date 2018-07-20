import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Main {
    
    public static void main(final String[] args) {
        JFrame window = new JFrame("Brainstorm Helper");
        window.setMinimumSize(new Dimension(500,500));
        
        JMenuBar menuBar = buildMenuBar();
        window.setJMenuBar(menuBar);
        
        JPanel tree = new TreeView();
        
        window.add(tree, BorderLayout.CENTER);
        
        NodeView node = new NodeView(new Node());
        
        tree.add(node, BorderLayout.PAGE_END);
        
        Rectangle bounds = new Rectangle(20, 20, 200, 100);
        
        node.setBounds(bounds);
        
        window.pack();
        window.setVisible(true);
    }
    
    private static JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem printFile = new JMenuItem("Print");
        JMenuItem exit = new JMenuItem("Exit");
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(printFile);
        fileMenu.add(exit);
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);
        
        JMenuItem createNode = new JMenuItem("Create Node");
        JMenuItem editNode = new JMenuItem("Edit Node");
        JMenuItem deleteNode = new JMenuItem("Delete Node");
        
        editMenu.add(createNode);
        editMenu.add(editNode);
        editMenu.add(deleteNode);
        
        return menuBar;
    }
}

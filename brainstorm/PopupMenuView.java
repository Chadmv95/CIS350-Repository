package brainstorm;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * This is a popup menu class for right clicking on a node
 * for easier manipulation and editing of the nodes.
 * 
 * @author Chad Vredeveld
 *
 */
@SuppressWarnings("serial")
public final class PopupMenuView extends JPopupMenu {
	
    /**
     * Menu options.
     */
	private JMenuItem deleteNodeItem,  moveNodeItem, createChildNode;
	
	/**
	 * Constructor. Builds the menu.
	 */
	public PopupMenuView() {
		setVisible(true);
        
        createChildNode = new JMenuItem("Create Child Node");
        add(createChildNode);
		
		addSeparator();
        
        deleteNodeItem = new JMenuItem("Delete This Node");
        add(deleteNodeItem);
        
        moveNodeItem = new JMenuItem("Move This Node");
        add(moveNodeItem);
	}
	
	/**
	 * Adds an action listener to all of the menu items of the pop-up menu.
	 * 
	 * @param al The action listener that will listen for menu selections.
	 */
	public void associateActionListener(final ActionListener al) {
	    deleteNodeItem.addActionListener(al);
        moveNodeItem.addActionListener(al);
        createChildNode.addActionListener(al);
	}
	
}

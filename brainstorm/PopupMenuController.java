package brainstorm;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * This is a popup menu class for right clicking on a node
 * for easier manipulation and editing of the nodes.
 * 
 * @author Chad Vredeveld
 *
 */
public final class PopupMenuController extends JPopupMenu {
	
	/**
	 * I made this thing to make the checkstyle happy.
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuItem deleteNodeItem;
	private JMenuItem moveNodeItem;
	
	
	public PopupMenuController() {
		setVisible(true);
		deleteNodeItem = new JMenuItem("Delete This Node");
		moveNodeItem = new JMenuItem("Move This Node");
		
		add(deleteNodeItem);
		add(moveNodeItem);
	}
	
}

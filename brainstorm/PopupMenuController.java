package brainstorm;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class controls pop-up menu behavior. 
 * 
 * @author Chad Vredeveld
 * @author Brian Gilbert
 *
 */
public final class PopupMenuController {
    
    /**
     * The instance of this singleton class.
     */
    private static PopupMenuController instance = new PopupMenuController();
    
    /**
     * The pop-up menu view class that this controls.
     */
    private PopupMenuView view;
    
    /**
     * We only need one listener which can keep track of all views.
     */
    private PopClickListener clickListener;
    
    /**
     * We only need one action listener to watch all menu items.
     */
    private PopActionListener actionListener;
    
    /**
     * The last watched component to get clicked on.
     */
    private Component lastClicked;
    
    /**
     * Private constructor for the singleton controller.
     */
    private PopupMenuController() {
        this.view = new PopupMenuView();
        this.clickListener = new PopClickListener();
        this.actionListener = new PopActionListener();
        this.view.associateActionListener(this.actionListener);
    }
    
    /**
     * Returns the single instance of the singleton Pop-up controller class.
     * 
     * @return The singleton instance of this class.
     */
    public static PopupMenuController getInstance() {
        return instance;
    }
    
    /**
     * Returns the mouse click listener which listens for pop-up menu clicks.
     * 
     * @return The click listener object this controller owns.
     */
    public PopClickListener getClickListener() {
        return this.clickListener;
    }
    
    /**
     * Returns the action listener for the JMenuItems in the menu.
     * 
     * @return the action listener object this controller owns.
     */
    public PopActionListener getActionListener() {
        return this.actionListener;
    }
    
    /**
     * Performs the actions of the pop-up menu.
     * 
     * @param menuChoice String representation of the chosen menu item.
     */
    private void performPopupAction(final String menuChoice) {
        NodeView nv = null;
        if (lastClicked instanceof NodeView) {
            nv = (NodeView) lastClicked;
        } else if (lastClicked instanceof JTextField) {
            // Name/Title area
            nv = (NodeView) lastClicked.getParent();
        } else if (lastClicked instanceof JTextArea) {
            // Content/Data area
            nv = (NodeView) lastClicked.getParent().getParent().getParent();
        }

        TreeController tc = TreeController.getInstance();
        
        switch (menuChoice) {
        case "Delete This Node":
            System.out.println("Delete This Node Clicked!");
            tc.removeNode(tc.findController(nv));
            break;
        case "Move This Node":
            System.out.println("Move This Node Clicked!");
            break;
        case "Create Child Node":
            System.out.println("Create Child Node Clicked!");
            tc.createChildOf(tc.findController(nv));
            break;
        default:
            // Do nothing
            break;
        }
    }
    
    /**
     * This is a class that listens for popup trigger clicks from the mouse.
     * 
     * @author Chad Vredeveld
     * @author Brian Gilbert
     *
     */
    private class PopClickListener extends MouseAdapter
                                          implements MouseListener {
    	
        @Override
        public void mousePressed(final MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			doPop(e);
    		}
    	}
    
        @Override
    	public void mouseReleased(final MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			doPop(e);
    		}
    	}
    
        /**
         * Helper function that displays a pop-up menu and logs which component
         * was clicked.
         * 
         * @param e The MouseEvent that caused the pop-up to trigger.
         */
    	private void doPop(final MouseEvent e) {
    		lastClicked = e.getComponent();
    	    view.show(e.getComponent(), e.getX(), e.getY());
    	}
    }
    
    /**
     * This listener listens for menu item selection.
     * @author Brian Gilbert
     *
     */
    private class PopActionListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            performPopupAction(e.getActionCommand());
        }
        
    }
}


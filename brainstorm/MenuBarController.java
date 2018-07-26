package brainstorm;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author brian
 *
 */
public class MenuBarController implements ActionListener {

    /**
     * The viewing class instance for the menu bar.
     */
    private MenuBarView view;
    
    /**
     * Default constructor. Will need to have a MenuBarView associated
     * with the controller after creation.
     */
    public MenuBarController() { }
    
    /**
     * Preferred constructor. Immediately associates the provided MenuBarView
     * with this controller.
     * 
     * @param view The MenuBarView this controller is paired with.
     */
    public MenuBarController(final MenuBarView view) {
        associateMenuBarView(view);
    }
    
    /**
     * Links this controller to a MenuBarView object.
     * 
     * @param view The MenuBarView that this class will control.
     */
    public void associateMenuBarView(final MenuBarView view) {
        if (view != null) {
            this.view = view;
            this.view.addMenuBarListener(this);
        }
    }
    
    /**
     * Returns the MenuBarView that was last associated with this
     * controller.
     * 
     * @return The associated MenuBarView object.
     */
    public MenuBarView getView() {
        return view;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        switch (e.getActionCommand()) {
        case "New":
            
            break;
        case "Open":
            
            break;
        case "Save":
            
            break;
        case "Print":
            TreeController.getInstance().getTree().printTree();
            break;
        case "Quit":
            ApplicationController.getInstance().getWindow().dispose();
            break;
        case "Create Node":
            Node n = new Node();
            n.setBounds(new Rectangle(20, 20, 200, 100));
            TreeController.getInstance().addNodeAtRootOfTree(n);
            break;
        case "Edit Node":
            
            break;
        case "Delete Node":
            
            break;
        default:
            
            break;
        }
    }
}

package brainstorm;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarController implements ActionListener {

    private MenuBarView view;
    
    public MenuBarController() { }
    
    public MenuBarController(final MenuBarView view) {
        associateMenuBarView(view);
    }
    
    public void associateMenuBarView(final MenuBarView view) {
        if (view != null) {
            this.view = view;
            this.view.addMenuBarListener(this);
        }
    }
    
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
        case "Exit":
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

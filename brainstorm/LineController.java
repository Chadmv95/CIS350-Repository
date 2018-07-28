package brainstorm;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * This is a controller class for the lines connecting nodes in the
 * brainstorming tree. It works in conjunction with the LineView class
 * which draws the lines, and it watches two NodeView objects so it can
 * draw a line between them.
 * 
 * @author Brian Gilbert
 *
 */
public class LineController {

    private LineView view;
    
    private NodeViewWatcher parentWatcher, childWatcher;
    
    public LineController(final LineView lv) {
        this.parentWatcher = new NodeViewWatcher(NodeViewWatcher.PARENT);
        this.childWatcher = new NodeViewWatcher(NodeViewWatcher.CHILD);
        view = lv;
    }
    
    public LineView getView() {
        return view;
    }
    
    public void setParentNodeView(final NodeView nvParent) {
        parentWatcher.watch(nvParent);
        if (nvParent != null) {
            view.setPointA((int) nvParent.getBounds().getCenterX(),
                           (int) nvParent.getBounds().getCenterY());
        }
    }
    
    public void setChildNodeView(final NodeView nvChild) {
        childWatcher.watch(nvChild);
        if (nvChild != null) {
            view.setPointB((int) nvChild.getBounds().getCenterX(),
                           (int) nvChild.getBounds().getCenterY());
        }
    }
    
    private class NodeViewWatcher implements MouseMotionListener {

        public static final boolean PARENT = true;
        public static final boolean CHILD = false;
        
        private boolean isParent;
        
        private NodeView watchee;
        
        NodeViewWatcher(final boolean isParent) {
            this.isParent = isParent;
        }
        
        public void watch(final NodeView nv) {
            if (watchee != null) {
                // If we're already watching a NodeView, stop.
                watchee.removeMouseMotionListener(this);
            }
            
            watchee = nv;
            
            if (watchee != null) {
                // If we're now watching a NodeView, start listening.
                watchee.addMouseMotionListener(this);
            }
        }
        
        @Override
        public void mouseDragged(final MouseEvent e) {
            Rectangle r = e.getComponent().getBounds();
            if (this.isParent) {
                view.setPointA((int) r.getCenterX(), (int) r.getCenterY());
            } else {
                view.setPointB((int) r.getCenterX(), (int) r.getCenterY());
            }
        }

        @Override
        public void mouseMoved(final MouseEvent e) { /*Do Nothing*/ }
    }
}

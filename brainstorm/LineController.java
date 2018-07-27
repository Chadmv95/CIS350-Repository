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
    
    public LineController(LineView lv) {
        this.parentWatcher = new NodeViewWatcher(NodeViewWatcher.PARENT);
        this.childWatcher = new NodeViewWatcher(NodeViewWatcher.CHILD);
        this.associateLineView(lv);
    }
    
    public void associateLineView(LineView lv) {
        view = lv;
    }
    
    public void setParentNodeView(NodeView nvParent) {
        parentWatcher.watch(nvParent);
    }
    
    public void setChildNodeView(NodeView nvChild) {
        childWatcher.watch(nvChild);
    }
    
    private class NodeViewWatcher implements MouseMotionListener {

        public static final boolean PARENT = true;
        public static final boolean CHILD = false;
        
        private boolean isParent;
        
        private NodeView watchee;
        
        public NodeViewWatcher(final boolean isParent) {
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

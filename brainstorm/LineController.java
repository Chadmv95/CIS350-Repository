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

    /**
     * The view associated with this controller object.
     */
    private LineView view;
    
    /**
     * The views of the nodes attached at each end of this line.
     */
    private NodeViewWatcher parentWatcher, childWatcher;
    
    /**
     * Constructor. A LineView object is mandatory for this class.
     * 
     * @param lv The LineView object that is associated with this class.
     */
    public LineController(final LineView lv) {
        this.parentWatcher = new NodeViewWatcher(NodeViewWatcher.PARENT);
        this.childWatcher = new NodeViewWatcher(NodeViewWatcher.CHILD);
        view = lv;
    }
    
    /**
     * Returns the view object associated with this controller.
     * 
     * @return The LineView object that displays this line.
     */
    public LineView getView() {
        return view;
    }
    
    /**
     * Sets the NodeView that the parent side of the line should follow.
     * 
     * @param nvParent The paretn-side NodeView object.
     */
    public void setParentNodeView(final NodeView nvParent) {
        parentWatcher.watch(nvParent);
        if (nvParent != null) {
            view.setPointA((int) nvParent.getBounds().getCenterX(),
                           (int) nvParent.getBounds().getCenterY());
        }
    }
    
    /**
     * Sets the NodeView that the child side of the line should follow.
     * 
     * @param nvChild The child-side NodeView object.
     */
    public void setChildNodeView(final NodeView nvChild) {
        childWatcher.watch(nvChild);
        if (nvChild != null) {
            view.setPointB((int) nvChild.getBounds().getCenterX(),
                           (int) nvChild.getBounds().getCenterY());
        }
    }
    
    /**
     * Since we need to watch two different sides of the line, this
     * helper class allows for two listeners, one for each NodeView.
     * 
     * @author Brian Gilbert
     *
     */
    private class NodeViewWatcher implements MouseMotionListener {

        /**
         * This value indicates to the watcher that it is watching the parent
         * side of the line.
         */
        public static final boolean PARENT = true;
        
        /**
         * This value indicates to the watcher that it is watching the child
         * side of the line.
         */
        public static final boolean CHILD = false;
        
        /**
         * This flag indicates whether the watcher is watching the parent side
         * of the line or the child side of the line.
         */
        private boolean isParent;
        
        /**
         * This is the NodeView that this watcher is watching.
         */
        private NodeView watchee;
        
        /**
         * Creates a new watcher. The parameter tells this watcher if it is
         * watching the NodeView of the parent or of the child. This affects
         * whether this NodeView updates point A (parent) or point B (child).
         * 
         * @param isParent Set to true if watching the parent. Set to false if
         * watching the child.
         */
        NodeViewWatcher(final boolean isParent) {
            this.isParent = isParent;
        }
        
        /**
         * Tells this NodeViewWatcher to start listening to a NodeView.
         * <br>
         * This watcher will stop watching its current NodeView and start
         * watching the new one.
         * <br>
         * If the new NodeView is null, then this watcher stops watching
         * the current NodeView, and will not watch anything.
         * 
         * @param nv The NodeView to start watching.
         */
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

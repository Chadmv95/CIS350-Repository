package brainstorm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**
 * This class draws the lines which connect nodes in the brainstorming app
 * GUI. It requires a LineController which dynamically changes the LineView
 * associated with it.
 * 
 * @author Brian Gilbert
 *
 */
@SuppressWarnings("serial")
public class LineView extends JLabel {
    
    /**
     * The locations of the points on the parent's graphics object
     * between which the line is drawn.
     */
    private int ax, ay, bx, by;
    
    /**
     * Thickness of the line.
     */
    private int thickness = 3;
    
    /**
     * Padding in pixels that the JLabel should place around the
     * rectangle containing the line. Without this, vertical and
     * horizontal lines disappear because the JLabel would have
     * a width or height of zero.
     */
    private int padding = thickness;
    
    /**
     * Color of the line.
     */
    private Color color = Color.BLACK;
    
    /**
     * Constructor for a line with no points.
     */
    public LineView() {
        super();
        ax = 0;
        ay = 0;
        bx = 0;
        by = 0;
        this.setOpaque(false);
    }
    
    /**
     * Constructor for a line between two points.
     * 
     * @param x1 x position of first point.
     * @param y1 y position of first point.
     * @param x2 x position of second point.
     * @param y2 y position of second point.
     */
    public LineView(final int x1, final int y1, final int x2, final int y2) {
        super();
        this.setOpaque(false);
        this.ax = x1;
        this.ay = y1;
        this.bx = x2;
        this.by = y2;
        this.updateBounds();
    }
    
    /**
     * Constructor for a line between the centers of two Component objects. 
     * Data for the positions of the two points of the line are extracted from
     * the Component objects.
     * 
     * @param comp1 The first component.
     * @param comp2 The second component.
     */
    public LineView(final Component comp1, final Component comp2) {
        super();
        this.setOpaque(false);
        this.ax = (int) comp1.getBounds().getCenterX();
        this.ay = (int) comp1.getBounds().getCenterY();
        this.bx = (int) comp2.getBounds().getCenterX();
        this.by = (int) comp2.getBounds().getCenterY();
    }
    
    /**
     * Changes the values of ax and ay to a new location.
     * 
     * @param x The new x value for point A
     * @param y the new y value for point A
     */
    public void setPointA(final int x, final int y) {
        this.ax = x;
        this.ay = y;
        this.updateBounds();
    }
    
    /**
     * Changes the values of bx and by to a new location.
     * 
     * @param x The new x value for point B
     * @param y the new y value for point B
     */
    public void setPointB(final int x, final int y) {
        this.bx = x;
        this.by = y;
        this.updateBounds();
    }
    
    /**
     * Helper function that updates the bounds of this component object
     * so that the component can fully display the line.
     */
    private void updateBounds() {
        int x = Math.min(ax, bx);
        int y = Math.min(ay, by);
        int w = Math.abs(bx - ax);
        int h = Math.abs(by - ay);
        this.setBounds(x - padding, y - padding,
                       w + 2 * padding, h + 2 * padding);
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setStroke(new BasicStroke(thickness));
        ((Graphics2D) g).setColor(color);
        g.drawLine(ax - this.getBounds().x + padding,
                   ay - this.getBounds().y + padding,
                   bx - this.getBounds().x + padding,
                   by - this.getBounds().y + padding);
    }
}

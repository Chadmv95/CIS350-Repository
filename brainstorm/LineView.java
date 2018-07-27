package brainstorm;

import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LineView extends JLabel {
    
    private int ax, ay, bx, by;
    
    public LineView() {
        super();
        this.setOpaque(false);
    }
    
    public LineView(final int x1, final int y1, final int x2, final int y2) {
        super();
        this.setOpaque(false);
        this.ax = x1;
        this.ay = y1;
        this.bx = x2;
        this.by = y2;
        this.updateBounds();
    }
    
    public void setPointA(final int x, final int y) {
        this.ax = x;
        this.ay = y;
        this.updateBounds();
    }
    
    public void setPointB(final int x, final int y) {
        this.bx = x;
        this.by = y;
        this.updateBounds();
    }
    
    private void updateBounds() {
        int x = Math.min(ax, bx);
        int y = Math.min(ay, by);
        int w = Math.abs(bx - ax);
        int h = Math.abs(by - ay);
        this.setBounds(x, y, w, h); 
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawLine(ax, ay, bx, by);
    }
}

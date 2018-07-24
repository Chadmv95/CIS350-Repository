/**
 * 
 */
package brainstorm;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

/**
 * @author brian
 *
 */
@SuppressWarnings("serial")
public class DocumentView extends JPanel implements Scrollable {

    /**
     * Constructor that creates a viewer for the document of the desired size.
     * @param size Desired size of the document.
     */
    DocumentView(final Dimension size) {
        super();
        this.setPreferredSize(size);
    }
    
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }

    @Override
    public int getScrollableBlockIncrement(final Rectangle visibleRect,
                                           final int orientation,
                                           final int direction) {
        if (direction == SwingConstants.HORIZONTAL) {
            return visibleRect.width;
        } else {
            return visibleRect.height;
        }
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public int getScrollableUnitIncrement(final Rectangle visibleRect,
                                          final int orientation,
                                          final int direction) {
        if (direction == SwingConstants.HORIZONTAL) {
            return visibleRect.width / 10;
        } else {
            return visibleRect.height / 10;
        }
    }

}

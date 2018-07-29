package brainstorm;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopClickListener extends MouseAdapter implements MouseListener{
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger())
			doPop(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger())
			doPop(e);
	}

	private void doPop(MouseEvent e) {
		PopupMenuController popupMenuController = new PopupMenuController();
		popupMenuController.show(e.getComponent(), e.getX(), e.getY());
	}
}


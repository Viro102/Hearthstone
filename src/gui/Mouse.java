
/**
 * Mouse class
 * 
 * Class that handles mouse events
 * 
 * @author Adam Virostek
 */
package gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Method;

public class Mouse implements MouseListener, MouseMotionListener {
    private Point p;
    private Panel panel;

    /**
     * Mouse constructor
     * 
     * @param panel Panel to add the mouseListener to
     */
    public Mouse(Panel panel) {
        this.panel = panel;
    }

    /**
     * Gets the current pointer position
     * 
     * @return Current pointer position
     */
    public Point getPointer() {
        return this.p;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.p = e.getPoint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("KLIK: x:" + e.getX() + " y:" + e.getY());
            this.execute("click");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // not used
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // not used
    }

    private void execute(String select) {
        try {
            if (this.panel != null) {
                Method method = this.panel.getClass().getMethod(select);
                method.invoke(this.panel);
            }
        } catch (Exception e) {
            this.doNothing();
        }
    }

    private void doNothing() {
        // nothing
    }
}

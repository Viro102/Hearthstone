package gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Method;

public class Mouse implements MouseListener, MouseMotionListener {
    private Point p;
    private Panel panel;

    public Mouse(Panel panel) {
        this.panel = panel;
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

    public void execute(String select) {
        try {
            if (this.panel != null) {
                Method sprava = this.panel.getClass().getMethod(select);
                sprava.invoke(this.panel);
            }
        } catch (Exception e) {
            this.doNothing();
        }

    }

    public Point getPointer() {
        return this.p;
    }

    private void doNothing() {
        // nothing
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
}

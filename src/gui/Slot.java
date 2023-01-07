package gui;

import java.awt.Rectangle;

public class Slot {
    private boolean free;
    private boolean glow;
    private int x;
    private int y;
    private int width;
    private int height;

    public Slot(int x, int y) {
        this.free = true;
        this.glow = false;
        this.x = x;
        this.y = y;
        this.width = 150;
        this.height = 200;
    }

    public boolean isGlow() {
        return this.glow;
    }

    public boolean isFree() {
        return this.free;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Rectangle getShape() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}

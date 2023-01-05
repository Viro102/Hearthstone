package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 750;
    private ArrayList<Rectangle> objects;

    public Panel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);

        this.objects = new ArrayList<>();
    }

    public void draw(Rectangle obj) {
        this.objects.add(obj);
        this.repaint();
    }

    public void erase(Rectangle obj) {
        if (this.objects.contains(obj)) {
            this.objects.remove(obj);
            this.repaint();
        } else {
            System.out.println("Object not found");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        var colors = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK,
                Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.WHITE, Color.BLACK };
        var g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        int i = 0;
        for (var obj : this.objects) {
            var rect = obj;
            if (i >= colors.length) {
                i = 0;
            }
            g2d.setColor(colors[i]);
            g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
            i++;
        }

        for (var obj : this.objects) {
            int hpPosY = obj.y + obj.height;
            int hpPosX = obj.x;
            int attPosX = obj.x + obj.width / 2;
            int attPosY = obj.y + obj.height;
            g2d.setColor(Color.BLACK);
            g2d.drawString("HP", hpPosX, hpPosY);
            g2d.drawString("AT", attPosX, attPosY);
        }
    }
}

package gui;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(Panel panel) {
        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Hearthstone");
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

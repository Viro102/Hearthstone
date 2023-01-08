/**
 * Frame class
 * 
 * Main frame of the game, uses javax.swing.JFrame to create a window,
 * to then be used to display the game
 * 
 * @author Adam Virostek
 */

package gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Frame extends JFrame {
    /**
     * Frame constructor
     * 
     * Creates a new frame with the specified panel
     * 
     * @param panel Panel to be displayed in the frame
     */
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

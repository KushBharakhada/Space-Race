package spacerace.gui;

/**
 * GUIFrame.java
 *
 * Sets up the frame for the game.
 *
 * @author Kush Bharakhada and James March
 */

import javax.swing.*;

public class GUIFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public GUIFrame() {
        // Setting up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Space Race");
        GUIPanel panel = new GUIPanel();
        this.add(panel);
        this.setSize(800, 600);
        this.setVisible(true);
    }

}
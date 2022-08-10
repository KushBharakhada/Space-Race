package spacerace.gui;

import java.awt.event.KeyAdapter;

import javax.swing.*;

/**
 * GUIFrame.java
 *
 * Sets up the frame for the game.
 *
 * @author Kush Bharakhada and James March
 */

public class GUIFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Instance Variables
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;	

	public GUIFrame() {
        // Setting up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Space Race");
        GUIPanel panel = new GUIPanel();
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        this.add(panel);  
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setVisible(true);
        panel.launchGame();
    }
	

}
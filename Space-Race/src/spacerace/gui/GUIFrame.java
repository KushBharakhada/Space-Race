package spacerace.gui;

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

	public GUIFrame() {
        // Setting up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Space Race");
        GUIPanel panel = new GUIPanel();
        this.setResizable(false);    
        this.add(panel);  
        this.pack();
        this.setVisible(true);
    }
	

}
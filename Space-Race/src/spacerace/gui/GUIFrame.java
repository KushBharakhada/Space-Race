package spacerace.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import spacerace.media.*;

/**
 * GUIFrame.java
 *
 * Sets up the frame for the game.
 *
 * @author Kush Bharakhada and James March
 */

@SuppressWarnings("serial")
public class GUIFrame extends JFrame implements KeyListener{
	
	// Instance Variables
	private StartScreen startPanel;
	private ImagesLoader images;
	private AudioLoader audio;
	
	// Constructor
	public GUIFrame(ImagesLoader images, AudioLoader audio, FontLoader fonts) {		
		// Used for GUIPanel when enter is pressed
	    this.images = images;
		this.audio = audio;
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Space Race");
        this.setResizable(false);        
        startPanel = new StartScreen(images, fonts);  
        this.add(startPanel); 
        this.pack();
        this.setVisible(true);
        this.addKeyListener(this);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		//when enter is selected, removes start screen and plays game
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			startPanel.setVisible(false);
			this.removeKeyListener(this);
			GUIPanel gamePanel = new GUIPanel(images, audio);
			this.add(gamePanel);
			this.addKeyListener(gamePanel);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
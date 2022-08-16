package spacerace.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * GUIFrame.java
 *
 * Sets up the frame for the game.
 *
 * @author Kush Bharakhada and James March
 */

public class GUIFrame extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;	
	private StartScreen start;
	
	public GUIFrame() {
        // Setting up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Space Race");
        this.setResizable(false);  
        start = new StartScreen();
        this.add(start); 
        this.pack();
        this.setVisible(true);
        this.addKeyListener(this);
        //this.setFocusable(true);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//when enter is selected, removes start screen and plays game
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			start.setVisible(false);
			this.removeKeyListener(this);
			GUIPanel panel = new GUIPanel();
			this.add(panel);
			this.addKeyListener(panel);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
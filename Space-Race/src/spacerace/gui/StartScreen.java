package spacerace.gui;

import java.awt.*;
import javax.swing.JPanel;
import spacerace.media.*;

/**
 * StartScreen.java
 *
 * Displayed to the player before the game starts.
 * Initial screen when game is first launched.
 *
 * @author Kush Bharakhada and James March
 */

@SuppressWarnings("serial")
public class StartScreen extends JPanel{
	
	// Instance Variables
	FontLoader fonts;
	ImagesLoader images;

	// Constructor
	public StartScreen(ImagesLoader images, FontLoader fonts) {
		this.setPreferredSize(new Dimension(GUIPanel.GAME_WIDTH, GUIPanel.GAME_HEIGHT));
		this.fonts = fonts;
		this.images = images;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Background
	    g2d.drawImage(images.getBackgroundImage(), 0, 0, GUIPanel.GAME_WIDTH, GUIPanel.GAME_HEIGHT, this);
	    
	    // Spaceship
	    g2d.drawImage(images.getSpaceshipImage(), (GUIPanel.GAME_WIDTH/2)-25, 275, 50, 50, this);
		
		// Logo
		g2d.setFont(fonts.getTitleFont());
		g2d.setColor(Color.white);
		g2d.drawString("Space Race", 100, 250);
		
		//press to start text
		g2d.setFont(fonts.getTextFont());
		g2d.setColor(Color.yellow);
		g2d.drawString("press  ENTER  to  start", 215, 375);
		
		//credits text
		g2d.setFont(fonts.getCreditsFont());
		g2d.setColor(Color.lightGray);
		g2d.drawString("created  by  Kush  Bharakhada  &  James  March", 175, 575);
	}
	
}
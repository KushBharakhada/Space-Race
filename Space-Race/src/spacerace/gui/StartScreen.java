package spacerace.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StartScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private BufferedImage spaceshipImg;

	private Font titleFont;
	private Font textFont;
	private Font creditsFont;

	public StartScreen() {
		this.setPreferredSize(new Dimension(GUIPanel.GAME_WIDTH, GUIPanel.GAME_HEIGHT));
		loadAssets();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//draw background
		if (background != null) {
			g2d.drawImage(background, 0, 0, GUIPanel.GAME_WIDTH, GUIPanel.GAME_HEIGHT, this);
		}
		
		if (spaceshipImg != null) {
			g2d.drawImage(spaceshipImg, (GUIPanel.GAME_WIDTH/2)-25, 275, 50, 50, this);
		}
		
		//logo
		g2d.setFont(titleFont);
		g2d.setColor(Color.white);
		g2d.drawString("Space Race", 100, 250);
		
		//press to start text
		g2d.setFont(textFont);
		g2d.setColor(Color.yellow);
		g2d.drawString("press  ENTER  to  start", 215, 375);
		
		//credits text
		g2d.setFont(creditsFont);
		g2d.setColor(Color.lightGray);
		g2d.drawString("created  by  Kush  Bharakhada  &  James  March", 175, 575);
	}
	
	//used to load in background image and fonts
	public void loadAssets() {
		try {
			background = ImageIO.read(new File("./src/images/background.jpg"));
			spaceshipImg = ImageIO.read(new File("./src/images/spaceship.png"));
			
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("./src/fonts/AquireBold-8Ma60.otf"));
			titleFont  = titleFont.deriveFont(Font.BOLD, 80);
			textFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("./src/fonts/ConnectionIii-Rj3W.otf"));
			textFont  = textFont.deriveFont(Font.PLAIN, 35);
			creditsFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("./src/fonts/ConnectionIii-Rj3W.otf"));
			creditsFont  = creditsFont.deriveFont(Font.PLAIN, 20);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
	}
}
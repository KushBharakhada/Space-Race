package spacerace.gameobjects;

import java.awt.*;
import java.awt.event.*;
import spacerace.gui.GUIFrame;


/**
 * Player.java
 *
 * The player in the game.
 *
 * @author Kush Bharakhada and James March
 */

//TODO make player movement smooth (use timer for KeyPressed and KeyReleased)
//TODO add diagonal movement (8 directions is all u can get with keyboard)
//TODO Implement Lives
//TODO Implement collision detection
//TODO implement game over when you run out of lives
//TODO Define colour in paintComponent

public class Player {
	
	int x;
	int y;
	int width = 20;
	int height = 20;
	Color color = Color.red;
	
	final int SPEED = 5; //how fast player should move
	
	//constructor, parameters are coordinates of where to spawn player
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getXCoord() {
		return this.x;
	}
	
	public int getYCoord() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	//borders are a bit weird because the player appears to be drawn at a strange position (not border)
	public void keyPressed(KeyEvent e) {
		//up
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			this.y = (getYCoord() > 0) ? this.y -= SPEED : this.y;
		}
		//down
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			this.y = (getYCoord() < GUIFrame.GAME_HEIGHT - getHeight()*3) 
					? this.y += SPEED : this.y;
		}
		//left
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.x = (getXCoord() > 0) ? this.x -= SPEED : this.x;
		}
		//right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.x = (getXCoord() < GUIFrame.GAME_WIDTH - getWidth()*1.8) 
				? this.x += SPEED : this.x;
		}
	}
	
	//runs Pythagorean theorem to calculate speed when going diagonally
	public int calculateDiagonal(int speed) { 
		return (int)Math.floor(Math.sqrt(2 * (speed ^ 2)));
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getXCoord(), getYCoord(), getWidth(), getHeight());	
	}
}

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

//TODO Implement Lives
//TODO Implement collision detection
//TODO implement game over when you run out of lives
//TODO Define colour in paintComponent

public class Player {
	
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;
	private int width = 20;
	private int height = 20;
	private int lives = 1;
	private Color color = Color.red;
	private final int SPEED = 2; //how fast player should move
	
	//constructor, parameters are coordinates of where to spawn player
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getXCoord() {
		return this.x;
	}
	
	public void setXCoord(int x) {
		this.x = x;
	}
	
	public int getYCoord() {
		return this.y;
	}
	
	public void setYCoord(int y) {
		this.y = y;
	}
	
	public int getXSpeed() {
		return this.xSpeed;
	}
	
	public int getYSpeed() {
		return this.ySpeed;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getLives() {
		return this.lives;
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
			this.ySpeed = (getYCoord() - SPEED > 0) ? -SPEED : 0;
		}
		//down
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			this.ySpeed = (getYCoord() + SPEED < GUIFrame.GAME_HEIGHT - getHeight()*3) 
					? SPEED : 0;
		}
		//left
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.xSpeed = (getXCoord() - SPEED > 0) ? -SPEED : 0;
		}
		//right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.xSpeed = (getXCoord() + SPEED < GUIFrame.GAME_WIDTH - getWidth()*1.8) 
				? SPEED : 0;
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

	public void keyReleased(KeyEvent e) {
		//up and down
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W || 
				e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			this.ySpeed = 0;
		}
		//left and right
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A || 
				e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.xSpeed = 0;
		}
	}
	
	//checks if player is about to be out of bounds, if so returns them inbounds
	public void checkOutOfBounds() {
		//left border
		if (getXCoord() - SPEED < 0) {
			setXCoord(0);
		}
		//top border
		if (getYCoord() - SPEED < 0) {
			setYCoord(0);
		}
		//right border
		if (getXCoord() + SPEED > GUIFrame.GAME_WIDTH - getWidth()*1.8) {
			setXCoord((int)(GUIFrame.GAME_WIDTH - getWidth()*1.8));
		}
		//down border
		if (getYCoord() + SPEED > GUIFrame.GAME_HEIGHT - getHeight()*3) {
			setYCoord(GUIFrame.GAME_HEIGHT - getHeight()*3);
		}
		
	}
}

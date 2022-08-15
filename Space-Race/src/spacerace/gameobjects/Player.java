package spacerace.gameobjects;

import java.awt.*;
import java.awt.event.*;
import spacerace.gui.GUIPanel;

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
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	private int lives = 3; //use this to set how many lives player has at start of game
	private Boolean invincible = false;
	
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
		return this.WIDTH;
	}
	
	public int getHeight() {
		return this.HEIGHT;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public Boolean getInvincible() {
		return this.invincible;
	}
	
	public void setInvinclible(Boolean invincible) {
		this.invincible = invincible;
	}
	
	public void keyPressed(KeyEvent e) {
		//up
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			this.ySpeed = (getYCoord() - SPEED > 0) ? -SPEED : 0;
		}
		//down
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			this.ySpeed = (getYCoord() + SPEED < GUIPanel.GAME_HEIGHT - getHeight()) 
					? SPEED : 0;
		}
		//left
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.xSpeed = (getXCoord() - SPEED > 0) ? -SPEED : 0;
		}
		//right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.xSpeed = (getXCoord() + SPEED < GUIPanel.GAME_WIDTH - getWidth()) 
				? SPEED : 0;
		}
	}
	
	//runs Pythagorean theorem to calculate speed when going diagonally
	public int calculateDiagonal(int speed) { 
		return (int)Math.floor(Math.sqrt(2 * (speed ^ 2)));
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
		if (getXCoord() + SPEED > GUIPanel.GAME_WIDTH - getWidth()) {
			setXCoord((int)(GUIPanel.GAME_WIDTH - getWidth()));
		}
		//down border
		if (getYCoord() + SPEED > GUIPanel.GAME_HEIGHT - getHeight()) {
			setYCoord((int)(GUIPanel.GAME_HEIGHT - getHeight()));
		}
		//System.out.println(getXCoord() + "," + getYCoord());
		
	}
	
	public Rectangle drawPlayer() {
		return new Rectangle(getXCoord(), getYCoord(), getWidth(), getHeight());
	}
	
}

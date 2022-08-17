package spacerace.gameobjects;

import java.awt.*;
import java.awt.event.*;
import spacerace.gui.GUIPanel;

/**
 * Player.java
 *
 * The player in the game. Controller by the user.
 *
 * @author Kush Bharakhada and James March
 */

public class Player {
	
	// Instance Variables
	public static final int PLAYER_SIZE = 20;
	private final int PLAYER_SPEED = 1; //how fast player should move
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;
	private int lives = 3; //use this to set how many lives player has at start of game
	private Boolean invincible = false;
	
	//constructor, parameters are coordinates of where to spawn player
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Get and Set Methods
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
	
	// ---
	
	public void keyPressed(KeyEvent e) {
		//up
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			this.ySpeed = (getYCoord() - PLAYER_SPEED > 0) ? -PLAYER_SPEED : 0;
		}
		//down
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			this.ySpeed = (getYCoord() + PLAYER_SPEED < GUIPanel.GAME_HEIGHT - PLAYER_SIZE) 
					? PLAYER_SPEED : 0;
		}
		//left
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			this.xSpeed = (getXCoord() - PLAYER_SPEED > 0) ? -PLAYER_SPEED : 0;
		}
		//right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			this.xSpeed = (getXCoord() + PLAYER_SPEED < GUIPanel.GAME_WIDTH - PLAYER_SIZE) 
				? PLAYER_SPEED : 0;
		}
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
		if (getXCoord() - PLAYER_SPEED < 0) {
			setXCoord(0);
		}
		//top border
		if (getYCoord() - PLAYER_SPEED < 0) {
			setYCoord(0);
		}
		//right border
		if (getXCoord() + PLAYER_SPEED > GUIPanel.GAME_WIDTH - PLAYER_SIZE) {
			setXCoord((int)(GUIPanel.GAME_WIDTH - PLAYER_SIZE));
		}
		//down border
		if (getYCoord() + PLAYER_SPEED > GUIPanel.GAME_HEIGHT - PLAYER_SIZE) {
			setYCoord((int)(GUIPanel.GAME_HEIGHT - PLAYER_SIZE));
		}
	}
	
	public Rectangle drawPlayer() {
		return new Rectangle(getXCoord(), getYCoord(), PLAYER_SIZE, PLAYER_SIZE);
	}
	
}

package spacerace.gameobjects;

import java.awt.*;
import java.awt.event.*;


/**
 * Player.java
 *
 * The player in the game.
 *
 * @author Kush Bharakhada and James March
 */

//TODO make player movement smooth (use timer for KeyPressed and KeyReleased)
//TODO add diagonal movement (8 directions is all u can get with keyboard)
//TODO add borders to player movement (can't go off screen)
//TODO Implement Lives
//TODO Implement collision detection
//TODO implement game over when you run out of lives

public class Player {
	
	int x;
	int y;
	int width = 25;
	int height = 25;
	Color color = Color.red;
	
	final double SPEED = 5; //how fast player should move
	
	//constructor, params are coords of where to spawn player
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.y -= SPEED;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.y += SPEED;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.x -= SPEED;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.x += SPEED;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);	
	}
}

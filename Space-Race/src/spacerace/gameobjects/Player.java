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

public class Player {
	
	int x;
	int y;
	int width = 25;
	int height = 25;
	Color color = Color.red;
	
	final double SPEED = 5; //how fast player should move
	 Object keyPressed;
	
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

package spacerace.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import spacerace.gameobjects.Asteroid;
import spacerace.gameobjects.AsteroidRunnable;
import spacerace.gameobjects.Player;
import spacerace.gameobjects.Target;

/**
 * GUIPanel.java
 *
 * Main panel for the game.
 *
 * @author Kush Bharakhada and James March
 */

public class GUIPanel extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	// Instance Variables
	private List<Asteroid> asteroids = Collections.synchronizedList(new ArrayList<>());
	private ArrayList<Thread> threads = new ArrayList<>();
	private Player player;
	private Target target;
	private Timer asteroidTimer;
	private Timer playerTimer;
	
	private int asteroidSpeed;
	private long asteroidLaunchRate;
	private final long PLAYER_REFRESH_RATE;
	private boolean isGameRunning;
	private int level;

	public GUIPanel() {
		this.setBackground(Color.BLACK);
		// Created player at bottom of screen
	    player = new Player(GUIFrame.GAME_WIDTH/2, GUIFrame.GAME_HEIGHT-100);
		target = new Target(200, 200);
		
		asteroidSpeed = 1;
		asteroidLaunchRate = 200L;
		PLAYER_REFRESH_RATE = 8L;
		isGameRunning = true;
		level = 1;
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.YELLOW);
		g2d.fill(target.drawTarget());
		
		g2d.setColor(Color.RED);
		g2d.fill(player.drawPlayer());
		
		// Control access drawPlayer multiple threads to the array list
		synchronized(asteroids) {
			// Goes through all the asteroids that have been added
			for (Asteroid a : asteroids) {
				g2d.setColor(Color.GRAY);
				g2d.fill(a.drawAsteroid());
			}
		}
		
		// Level and lives display
		g2d.setColor(Color.WHITE);
		g2d.drawString(("Lives: " + player.getLives()), 10, 20);
		g2d.drawString(("Level: " + level), 10, 40);
	}
	
	public void launchAsteroid(int speed) {
		// Creating an asteroid object
		Asteroid a = new Asteroid(speed);
		
		// Reliable communication between threads
		// Control access of multiple threads to the array list
		synchronized(asteroids) {
			asteroids.add(a);
		}
		
		// Starts a new thread for each new asteroid
	    Runnable r = new AsteroidRunnable(a, this);
	    Thread t = new Thread(r);
	    // Add the thread to a list so corresponding asteroid can be
	    // removed from asteroids list when its thread is dead
	    threads.add(t);
	    t.start(); 
	}
	
	public void launchAsteroidsWithDelay() {
		// How often the asteroids are launched
    	TimerTask task = new TimerTask() {
			public void run() {
				launchAsteroid(asteroidSpeed);
				if (!threads.get(0).isAlive()) {
					threads.remove(0);
					asteroids.remove(0);
				}
			}
		};
		asteroidTimer = new Timer();
		asteroidTimer.scheduleAtFixedRate(task, 0, asteroidLaunchRate);
    }
	
	public void movePlayer() {
		// Delay on player, determines player speed
    	TimerTask task = new TimerTask() {
			public void run() {
				player.setXCoord(player.getXCoord() + player.getXSpeed());
				player.setYCoord(player.getYCoord() + player.getYSpeed());
				player.checkOutOfBounds();
				checkCollision();
			}
		};
		playerTimer = new Timer();
		playerTimer.scheduleAtFixedRate(task, 0, PLAYER_REFRESH_RATE);	
    }
	
	//used to check collision between player and all asteroids
	public void checkCollision() {
		Rectangle playerHurtBox = player.drawPlayer();
		
		//check collision with asteroids	
		synchronized(asteroids) {
			for (Asteroid asteroid : asteroids) {
				Ellipse2D asteroidHitBox = asteroid.drawAsteroid();
				if (asteroidHitBox.intersects(playerHurtBox) && !player.getInvincible()) {
					System.out.println("Life lost");
					player.setLives(player.getLives() - 1);
					if (player.getLives() == 0) {
						endGame();
					}
					else {
						System.out.println("remaining lives: " + player.getLives());
						playerInvincibility();
					}
				}
			}
		}
		
		//check collision with target
		if (target.drawTarget().intersects(player.drawPlayer())) {
		    increaseLevelAndDifficulty();
			System.out.println("target hit");
		}
	}
	
	public void increaseLevelAndDifficulty() {
		level++;
		asteroidSpeed++;
		
		// Move the target to a new position
		int[] coords = randomCoords();
		target = new Target(coords[0], coords[1]);
	}
	
	public void playerInvincibility() {
		TimerTask setInvincibility = new TimerTask() {
			public void run() {
				player.setInvinclible(false);
			}
		};
		player.setInvinclible(true);
		Timer invincibleTimer = new Timer();
		invincibleTimer.schedule(setInvincibility, 1000);
	}
	
	//used to stop player and generation of new asteroids during a game over+
    public void endGame() {
    	// Time stops the asteroids and player movement
		System.out.println("Game Over");
		asteroidTimer.cancel();
		playerTimer.cancel();
		isGameRunning = false;
	}
	
	public int[] randomCoords() {
		int[] coords = new int[2];
		// Random x coordinate
	    coords[0] = (int)(Math.random() * GUIFrame.GAME_HEIGHT);	
	    // Random y coordinate
	    coords[1] = (int)(Math.random() * GUIFrame.GAME_WIDTH);
	    return coords;
	}
		
	public void launchGame() {
		// Launches asteroids at a constant rate
        launchAsteroidsWithDelay();
        // Allows the player to move
        movePlayer();
	}

	@Override
	public void keyTyped(KeyEvent e) {
        
    }
	
	@Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
	
	@Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
			
}

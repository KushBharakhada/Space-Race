package spacerace.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import spacerace.gameobjects.*;

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
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;	
	
	private List<Asteroid> asteroids = Collections.synchronizedList(new ArrayList<>());
	private ArrayList<Thread> threads = new ArrayList<>();
	private Player player;
	private Target target;
	private Timer asteroidTimer;
	private Timer playerTimer;
	
	private BufferedImage asteroidImg;
	private BufferedImage targetImg;
	private BufferedImage spaceshipImg;
	private BufferedImage backgroundImg;
	private Image explosionImg;
	
	private int asteroidSpeed = 1;
	private long asteroidLaunchRate = 200L;
	private final long PLAYER_REFRESH_RATE = 8L;
	private boolean isGameRunning = true;
	private int level = 1;
	
	public GUIPanel() {
		this.addKeyListener(this);
        this.setFocusable(true);
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		
		// Initial position of player and target
	    player = new Player(GAME_WIDTH / 2, GAME_HEIGHT - 100);
		target = new Target(GAME_WIDTH / 2, GAME_HEIGHT / 2);
		
		loadImages();
		launchGame();
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
				
		// Background
		g2d.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, this);
		
		// Player
		if (!isGameRunning)
			g2d.drawImage(explosionImg, player.getXCoord(), player.getYCoord(), Player.WIDTH, Player.HEIGHT, this);
		else
			g2d.drawImage(spaceshipImg, player.getXCoord(), player.getYCoord(), this);
		
		// Asteroids
		synchronized(asteroids) {
			// Goes through all the asteroids that have been added
			for (Asteroid a : asteroids) {
				g2d.setClip(a.drawAsteroid());
				g2d.drawImage(asteroidImg, a.getXCoordinate(), a.getYCoordinate(), this);
			}
			// Set the clipper back to original from asteroid shape
			g2d.setClip(null);
		}
		
		if (isGameRunning) {
			final int LEVEL_LIVES_POS_X = 10;
			final int LEVEL_LIVES_POS_Y = 20;
			
			// Target
			g2d.setClip(target.drawTarget());
			g2d.drawImage(targetImg, target.getXCoordinate(), target.getYCoordinate(), this);
			g2d.setClip(null);
						
			// Level and lives display
			g2d.setColor(Color.WHITE);
			g2d.drawString(("Lives: " + player.getLives()), LEVEL_LIVES_POS_X, LEVEL_LIVES_POS_Y);
			g2d.drawString(("Level: " + level), LEVEL_LIVES_POS_X, LEVEL_LIVES_POS_Y + 20);
		}
				
		// End game display
		if (!isGameRunning) {
			final int END_GAME_TITLE_POS_X = 75;
			final int END_GAME_TITLE_POS_Y = GAME_HEIGHT / 2;
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 100));
			g2d.drawString("GAME OVER", END_GAME_TITLE_POS_X, END_GAME_TITLE_POS_Y);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
			g2d.setColor(Color.YELLOW);
			g2d.drawString("LEVEL " + (level-1), END_GAME_TITLE_POS_X + 220, END_GAME_TITLE_POS_Y + 100);
			
		}
	}
	
	public void loadImages() {
		// Set the required images for use in-game
		try {
			asteroidImg = ImageIO.read(new File("./src/images/asteroid.jpg"));
			backgroundImg = ImageIO.read(new File("./src/images/background.jpg"));
			targetImg = ImageIO.read(new File("./src/images/target.jpg"));
			spaceshipImg = ImageIO.read(new File("./src/images/spaceship.png"));
			explosionImg = new ImageIcon("./src/images/explosion.gif").getImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				// Remove dead threads and its corresponding asteroid
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
				checkPlayerGraphic();
				player.setXCoord(player.getXCoord() + player.getXSpeed());
				player.setYCoord(player.getYCoord() + player.getYSpeed());
				player.checkOutOfBounds();
				checkCollision();
				
			}
		};
		playerTimer = new Timer();
		playerTimer.scheduleAtFixedRate(task, 0, PLAYER_REFRESH_RATE);	
    }
	
	// Used to check collision between player and all asteroids
	public void checkCollision() {
		Rectangle playerHurtBox = player.drawPlayer();
		
		//check collision with asteroids	
		synchronized(asteroids) {
			for (Asteroid asteroid : asteroids) {
				Ellipse2D asteroidHitBox = asteroid.drawAsteroid();
				if (asteroidHitBox.intersects(playerHurtBox) && !player.getInvincible()) {
					// Decrease player life and end game if 0 lives
					player.setLives(player.getLives() - 1);
					if (player.getLives() == 0) {
						endGame();
					}
					else {
						// Temporary player timeout
						playerInvincibility();
					}
				}
			}
		}
		
		//check collision with target
		if (target.drawTarget().intersects(player.drawPlayer())) {
		    increaseLevelAndDifficulty();
		}
	}
	
	public void increaseLevelAndDifficulty() {
		// Increase level and speed of asteroids
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
    	// Stop the asteroids and player movement
		asteroidTimer.cancel();
		playerTimer.cancel();
		isGameRunning = false;
	}
	
	public int[] randomCoords() {
		int[] coords = new int[2];
		// Random x coordinate
	    int xCoord = (int)(Math.random() * GAME_WIDTH);	
	    // Random y coordinate
	    int yCoord = (int)(Math.random() * GAME_HEIGHT);
	    
	    // Adjust x and y coordinate to ensure full target is seen on panel
	    if (xCoord >= GAME_WIDTH - Target.TARGET_SIZE_WIDTH)
	    	coords[0] = GAME_WIDTH - Target.TARGET_SIZE_WIDTH;
	    else
	    	coords[0] = xCoord;
	    
	    if (yCoord >= GAME_HEIGHT - Target.TARGET_SIZE_HEIGHT)
	    	coords[1] = GAME_HEIGHT - Target.TARGET_SIZE_HEIGHT;
	    else
	    	coords[1] = yCoord;
	    	
	    return coords;
	}
	
	//used to check the direction being travelled and adjust image accordingly
	public void checkPlayerGraphic() {
		//creates a clone of the original image
		BufferedImage source = null;
		try {
			source = ImageIO.read(new File("./src/images/spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		//steps before rotation (creating a blank image and translating it)
		BufferedImage dest = new BufferedImage(player.getHeight(), player.getWidth(), source.getType());
		Graphics2D graphics2D = dest.createGraphics();
		graphics2D.translate((player.getHeight() - player.getWidth()) / 2, (player.getHeight() - player.getWidth()) / 2);
		
		double xSpeed = player.getXSpeed();
		double ySpeed = player.getYSpeed();
		
		//depending on current direction player is travelling, perform specific rotation
		//right
		if (xSpeed > 0 && ySpeed == 0) {
		    rotateImage(graphics2D, Math.PI / 2);
		}
		//left
		else if (xSpeed < 0 && ySpeed == 0) {
			rotateImage(graphics2D, -Math.PI / 2);
		}
		//up
		else if (ySpeed < 0 && xSpeed == 0) {
			rotateImage(graphics2D, 0);
		}
		//down
		else if (ySpeed > 0 && xSpeed == 0) {
			rotateImage(graphics2D, Math.PI);
		}
		//down-right
		else if (ySpeed > 0 && xSpeed > 0) {
			rotateImage(graphics2D, Math.PI / 1.35);
		}
		//down-left
		else if (ySpeed > 0 && xSpeed < 0) {
			rotateImage(graphics2D, -Math.PI / 1.35);
		}
		//up-right
		else if (ySpeed < 0 && xSpeed > 0) {
			rotateImage(graphics2D, Math.PI / 4);
		}
		//up-left
		else if (ySpeed < 0 && xSpeed < 0) {
			rotateImage(graphics2D, -Math.PI / 4);
		}
		else {
			dest = spaceshipImg;
		}
		graphics2D.drawRenderedImage(source, null);
		spaceshipImg = dest;
	}
	
	public void rotateImage(Graphics2D image, double rotation) {
		image.rotate(rotation, player.getHeight() / 2, player.getWidth() / 2);
	}
		
	public void launchGame() {
		// Launches asteroids at a constant rate
        launchAsteroidsWithDelay();
        // Allows the player to move
        movePlayer();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
	
	@Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
			
}

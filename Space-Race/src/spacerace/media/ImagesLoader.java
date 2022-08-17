package spacerace.media;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * ImagesLoader.java
 *
 * Loads the images for in-game use.
 *
 * @author Kush Bharakhada and James March
 */

public class ImagesLoader {
	
	// Instance Variables
	private BufferedImage asteroidImg;
	private BufferedImage targetImg;
	private BufferedImage spaceshipImg;
	private BufferedImage backgroundImg;
	private BufferedImage redSpaceshipImg;
	private BufferedImage rotatableSpaceshipImg;
	private BufferedImage rotatableRedSpaceshipImg;
	private Image explosionImg;
	
	// Constructor
	public ImagesLoader() {
		loadImages();
	}
	
	private void loadImages() {
		// Set the required images for use in-game
		try {
			asteroidImg = ImageIO.read(getClass().getResource("/images/asteroid.jpg"));
			backgroundImg = ImageIO.read(getClass().getResource("/images/background.jpg"));
			targetImg = ImageIO.read(getClass().getResource("/images/target.jpg"));
			spaceshipImg = ImageIO.read(getClass().getResource("/images/spaceship.png"));
			redSpaceshipImg = ImageIO.read(getClass().getResource("/images/red-spaceship.png"));
			rotatableSpaceshipImg = ImageIO.read(getClass().getResource("/images/spaceship.png"));
			rotatableRedSpaceshipImg = ImageIO.read(getClass().getResource("/images/red-spaceship.png"));
			explosionImg = new ImageIcon(getClass().getResource("/images/explosion.gif")).getImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Get and Set Methods
	public BufferedImage getAsteroidImage() {
		return this.asteroidImg;
	}
	
	public BufferedImage getTargetImage() {
		return this.targetImg;
	}
	
	public BufferedImage getSpaceshipImage() {
		return this.spaceshipImg;
	}
	
	public BufferedImage getBackgroundImage() {
		return this.backgroundImg;
	}
	
	public BufferedImage getRedSpaceshipImage() {
		return this.redSpaceshipImg;
	}
	
	public Image getExplosionImage() {
		return this.explosionImg;
	}
	
	public BufferedImage getRotatableSpaceshipImage() {
		return this.rotatableSpaceshipImg;
	}
	
	public void setRotatableSpaceshipImage(BufferedImage img) {
		this.rotatableSpaceshipImg = img;
	}
	
	public BufferedImage getRotatableRedSpaceshipImage() {
		return this.rotatableRedSpaceshipImg;
	}
	
	public void setRotatableRedSpaceshipImage(BufferedImage img) {
		this.rotatableRedSpaceshipImg = img;
	}
	// ---
}

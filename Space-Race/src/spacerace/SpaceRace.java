package spacerace;

import spacerace.gui.GUIFrame;
import spacerace.media.*;

/**
 * SpaceRace.java
 *
 * Main class for the game. Creates a GUI Frame which launches
 * the game.
 *
 * @author Kush Bharakhada and James March
 */

public class SpaceRace {
		
	public static void main(String[] args) {
		
		// Load media
		ImagesLoader images = new ImagesLoader();
		AudioLoader audio = new AudioLoader();
		FontLoader fonts = new FontLoader();
		
		// Launch window and game
		new GUIFrame(images, audio, fonts);
	}

}

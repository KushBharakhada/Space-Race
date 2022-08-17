package spacerace.media;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * AudioLoader.java
 *
 * Sound effects for use in-game.
 *
 * @author Kush Bharakhada and James March
 */

public class AudioLoader {
	
	// Instance variables
	private Clip explosionSound;
	private Clip startGameSound;
	private Clip levelUpSound;
	private Clip gameOverSound;
	
	// Constructor
	public AudioLoader() {
		loadAllSoundEffects();
	}
	
	private void loadAllSoundEffects() {
		try {
			// Load all the required sounds
			loadGameOverSound();
			loadLevelUpSound();
			loadStartGameSound();
			loadExplosionSound();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadGameOverSound() throws Exception {
		InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/audio/gameover.wav"));
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
		gameOverSound = AudioSystem.getClip();
		gameOverSound.open(audioStream);
	}
		
	private void loadLevelUpSound() throws Exception {
		InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/audio/levelup.wav"));
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
		levelUpSound = AudioSystem.getClip();
		levelUpSound.open(audioStream);
	}
	
	private void loadStartGameSound() throws Exception {
		InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/audio/start.wav"));
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
		startGameSound = AudioSystem.getClip();
		startGameSound.open(audioStream);
	}
	
	private void loadExplosionSound() throws Exception {
		InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/audio/explosion.wav"));
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
		explosionSound = AudioSystem.getClip();
		explosionSound.open(audioStream);
	}
	
	// Get Methods
	public Clip getExplosionSound() {
		// Reset the sound on each call to start from beginning
		explosionSound.drain();
		explosionSound.setFramePosition(0);
		return this.explosionSound;
	}
	
	public Clip getGameOverSound() {
		return this.gameOverSound;
	}
	
	public Clip getStartGameSound() {
		return this.startGameSound;
	}
	
	public Clip getLevelUpSound() {
		// Reset the sound in each call to start from beginning
		levelUpSound.drain();
		levelUpSound.setFramePosition(0);
		return this.levelUpSound;
	}
	// ---
	
	// Method has not been used yet*
	public void closeSoundEffects() {
		explosionSound.close();
		startGameSound.close();
		levelUpSound.close();
		gameOverSound.close();
	}
	
}

package spacerace.soundeffects;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffects {
	
	// Instance variables
	private Clip explosionSound;
	private Clip startGameSound;
	private Clip levelUpSound;
	private Clip gameOverSound;
	
	public SoundEffects() {
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
	
	public void loadGameOverSound() throws Exception {
		File gameOver = new File("./src/audio/gameover.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(gameOver);
		gameOverSound = AudioSystem.getClip();
		gameOverSound.open(audioStream);
	}
		
	public void loadLevelUpSound() throws Exception {
		File levelUp = new File("./src/audio/levelup.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(levelUp);
		levelUpSound = AudioSystem.getClip();
		levelUpSound.open(audioStream);
	}
	
	public void loadStartGameSound() throws Exception {
		File start = new File("./src/audio/start.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(start);
		startGameSound = AudioSystem.getClip();
		startGameSound.open(audioStream);
	}
	
	public void loadExplosionSound() throws Exception {
		File impact = new File("./src/audio/explosion.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(impact);
		explosionSound = AudioSystem.getClip();
		explosionSound.open(audioStream);
	}
	
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
	
	public void closeSoundEffects() {
		explosionSound.close();
		startGameSound.close();
		levelUpSound.close();
		gameOverSound.close();
	}
	
}

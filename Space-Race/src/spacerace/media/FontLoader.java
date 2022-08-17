package spacerace.media;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * FontLoader.java
 *
 * Loads the fonts for in-game use.
 *
 * @author Kush Bharakhada and James March
 */

public class FontLoader {
	
	// Instance variables
	private Font titleFont;
	private Font textFont;
	private Font creditsFont;
	
	// Constructor
	public FontLoader() {
		loadFonts();			
	}
	
	private void loadFonts() {
		try {	
			// Title
			titleFont = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream("./src/fonts/AquireBold-8Ma60.otf"));
			titleFont  = titleFont.deriveFont(Font.BOLD, 80);
			
			// Rest of the Text
			textFont = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream("./src/fonts/ConnectionIii-Rj3W.otf"));
			textFont  = textFont.deriveFont(Font.PLAIN, 35);
			
			// Credits
			creditsFont = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream("./src/fonts/ConnectionIii-Rj3W.otf"));
			creditsFont  = creditsFont.deriveFont(Font.PLAIN, 20);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	// Get Methods
	public Font getTitleFont() {
		return this.titleFont;
	}
	
	public Font getTextFont() {
		return this.textFont;
	}
	
	public Font getCreditsFont() {
		return this.creditsFont;
	}
	
	// ---
}

package figglewatts.slagd.graphics.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Static class to generate and draw fonts
 * @author Figglewatts
 *
 */
public class Font {
	private Font() { }
	
	private static FreeTypeFontGenerator generator;
	private static FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	/**
	 * Generate a font with the given parameters
	 * @param pathToFont The path to the font (in the assets folder)
	 * @param size Size to generate the font at
	 * @param useNearest Should the font use Nearest Neighbour texure filtering
	 * @return A BitmapFont instance
	 */
	public static BitmapFont GenerateFont(String pathToFont, int size, boolean useNearest) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(pathToFont));
		parameter.size = size;
		if (useNearest) {
			parameter.minFilter = TextureFilter.Nearest;
			parameter.magFilter = TextureFilter.Nearest;
		}
		return generator.generateFont(parameter);
	}
	
	/**
	 * Generate a font with the given parameters
	 * @param pathToFont The path to the font (in the assets folder)
	 * @param params The parameters to generate with
	 * @return A BitmapFont instance
	 */
	public static BitmapFont GenerateFont(String pathToFont, FreeTypeFontParameter params) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(pathToFont));
		return generator.generateFont(params);
	}
	
	/**
	 * Draw a font with an outline with of 1 pixel
	 * @param font The font to use
	 * @param color The color of the font
	 * @param outline The color of the outline
	 * @param text The text to draw
	 * @param x The X position to draw at
	 * @param y The Y position to draw at
	 * @param batch The SpriteBatch to use
	 */
	public static void DrawWithOutline(BitmapFont font, Color color, Color outline, String text, int x, int y, SpriteBatch batch) {
		Color colorCache = font.getColor();
		font.setColor(outline);
		font.draw(batch, text, x+1, y);
		font.draw(batch, text, x-1, y);
		font.draw(batch, text, x, y-1);
		font.draw(batch, text, x, y+1);
		font.draw(batch, text, x+1, y-1);
		font.draw(batch, text, x+1, y+1);
		font.draw(batch, text, x-1, y-1);
		font.draw(batch, text, x-1, y-1);
		font.setColor(color);
		font.draw(batch, text, x, y);
		font.setColor(colorCache);
	}
	
	/**
	 * Draw a font with a drop shadow
	 * @param font The font to use
	 * @param color The color of the font
	 * @param shadow The color of the shadow
	 * @param text The text to draw
	 * @param distance The distance from the text to draw the shadow
	 * @param x The X position of the text
	 * @param y The Y position of the text
	 * @param batch The SpriteBatch to use
	 */
	public static void DrawWithShadow(BitmapFont font, Color color, Color shadow, String text, int distance, int x, int y, SpriteBatch batch) {
		Color colorCache = font.getColor();
		font.setColor(shadow);
		font.draw(batch, text, x+distance, y-distance);
		font.setColor(color);
		font.draw(batch, text, x, y);
		font.setColor(colorCache);
	}
}

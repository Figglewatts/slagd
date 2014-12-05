package figglewatts.slagd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Settings {
	private Settings() { }
	
	public static int VIRTUAL_VIEWPORT_WIDTH = Gdx.graphics.getWidth();
	public static int VIRTUAL_VIEWPORT_HEIGHT = Gdx.graphics.getHeight();
	public static TextureFilter MIN_FILTER = TextureFilter.Nearest;
	public static TextureFilter MAG_FILTER = TextureFilter.Nearest;
}

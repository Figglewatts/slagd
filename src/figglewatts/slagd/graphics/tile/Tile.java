package figglewatts.slagd.graphics.tile;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A static class to hold tileset textures, information about tiles, and give the ability to calculate source rectangles
 * @author Figglewatts
 *
 */
public class Tile {
	private Tile() { } // static constructor
	
	public static List<Texture> TILESET_TEXTURES = new ArrayList<Texture>();
	
	public static int TILE_WIDTH = -1;
	public static int TILE_HEIGHT = -1;
	
	public static void addTilesheet(Texture tilesheet) {
		TILESET_TEXTURES.add(tilesheet);
	}
	
	/**
	 * Gets the region of the texture that a given tile occupies
	 * @param tileIndex How far into the sheet in tiles the tile is
	 * @param sheetIndex The sheet to source the tiles from
	 * @return TextureRegion of the tile
	 */
	public static TextureRegion getSourceRectangle(int tileIndex, int sheetIndex) {
		
		if (TILE_WIDTH == -1 || TILE_HEIGHT == -1) {
			System.out.println("Cannot create source rectangle, TILE_WIDTH or TILE_HEIGHT have not been set.");
			return null;
		}
		int tileY = 0; // y position of the tile in tiles
		int tileX = 0; // x position of the tile in tiles
		
		if (TILESET_TEXTURES.get(sheetIndex) != null) {
			tileY = tileIndex / (TILESET_TEXTURES.get(sheetIndex).getWidth() / TILE_HEIGHT);
			tileX = tileIndex % (TILESET_TEXTURES.get(sheetIndex).getWidth() / TILE_WIDTH);
		}

		return new TextureRegion(TILESET_TEXTURES.get(sheetIndex), tileX * TILE_WIDTH, tileY * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
	}
}

package figglewatts.slagd.graphics.tile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * MapCell is a class to represent each 'cell' on a tile map. It contains an array of tiles (one for each layer) and the X and Y position of the tile.
 * @author Figglewatts
 *
 */
public class MapCell {
	public Array<BaseTile> BaseTiles = new Array<BaseTile>();
	
	private int tilePosX;
	private int tilePosY;
	public int getTilePosX() {
		return tilePosX;
	}
	public void setTilePosX(int tilePosX) {
		this.tilePosX = tilePosX;
	}
	public int getTilePosY() {
		return tilePosY;
	}
	public void setTilePosY(int tilePosY) {
		this.tilePosY = tilePosY;
	}
	
	public Vector2 getPosAsVector() {
		return new Vector2(tilePosX, tilePosY);
	}
	public void setTilePos(Vector2 pos) {
		this.tilePosX = (int) pos.x;
		this.tilePosY = (int) pos.y;
	}
	public void setTilePos(int x, int y) {
		this.tilePosX = x;
		this.tilePosY = y;
	}
	
	/**
	 * Gets the tile ID of the tile
	 * @param layer The layer to get the tile from
	 * @return The Tile ID of the tile
	 */
	public int getTileID(int layer) {
		return hasLayer(layer) ? BaseTiles.get(layer).getTileID() : -1;
	}
	public void setTileID(int tileID) {
		setTileInList(0, new BaseTile(tileID, BaseTiles.get(0).getSheetID()));
	}
	/**
	 * Sets the tile's tile ID
	 * @param tileID ID to set
	 * @param layer Layer to set ID on
	 */
	public void setTileID(int tileID, int layer) {
		setTileInList(layer, new BaseTile(tileID, BaseTiles.get(layer).getSheetID()));
	}
	/**
	 * Gets which tile sheet a tile is on/
	 * @param layer The layer to get the tile from
	 * @return The index of the tile sheet
	 */
	public int getSheetID(int layer) {
		return hasLayer(layer) ? BaseTiles.get(layer).getSheetID() : -1;
	}
	/**
	 * Sets the sheet ID of the tile on layer 0
	 * @param sheetID The sheet ID
	 */
	public void setSheetID(int sheetID) {
		setTileInList(0, new BaseTile(BaseTiles.get(0).getSheetID(), sheetID));
	}
	/**
	 * Sets the sheet ID of the tile on a given layer
	 * @param sheetID The sheet ID
	 * @param layer Layer to set sheet ID
	 */
	public void setSheetID(int sheetID, int layer) {
		setTileInList(layer, new BaseTile(BaseTiles.get(layer).getSheetID(), sheetID));
	}
	
	/**
	 * Gets the tile on a given layer
	 * @param layer The layer to get the tile from
	 * @return BaseTile instance on the layer
	 */
	public BaseTile getTile(int layer) {
		if (hasLayer(layer)) {
			return BaseTiles.get(layer);
		} else {
			return null;
		}
	}
	
	/**
	 * Sets the tile on layer 0
	 * @param tile BaseTile instance
	 */
	public void setTile(BaseTile tile) {
		setTileInList(0, tile);
	}
	/**
	 * Sets the tile on layer 0
	 * @param tileID
	 * @param sheetID
	 */
	public void setTile(int tileID, int sheetID) {
		setTileInList(0, new BaseTile(tileID, sheetID));
	}
	/**
	 * Sets the tile on a given layer
	 * @param tile BaseTile instance
	 * @param layer layer to set
	 */
	public void setTile(BaseTile tile, int layer) {
		setTileInList(layer, tile);
	}
	/**
	 * Sets the tile on a given layer
	 * @param tileID
	 * @param sheetID
	 * @param layer
	 */
	public void setTile(int tileID, int sheetID, int layer) {
		setTileInList(layer, new BaseTile(tileID, sheetID));
	}
	
	/**
	 * Adds a tile to this cell
	 * @param tile Tile to add
	 */
	public void addTile(BaseTile tile) {
		BaseTiles.add(tile);
	}
	/**
	 * Adds a tile to this cell
	 * @param tileID Tile ID
	 * @param sheetID Sheet ID
	 */
	public void addTile(int tileID, int sheetID) {
		BaseTiles.add(new BaseTile(tileID, sheetID));
	}
	
	/**
	 * Removes an entire layer from this tile
	 * @param layer Layer to remove
	 */
	public void removeLayer(int layer) {
		if (BaseTiles.size > layer) {
			BaseTiles.removeIndex(layer);	
		}
	}
	
	private boolean hasLayer(int layer) {
		try {
			BaseTiles.get(layer);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	private void setTileInList(int layer, BaseTile tile) {
		if (BaseTiles.size <= layer) {
			BaseTiles.add(tile);
		} else {
			BaseTiles.set(layer, tile);
		}
	}
	
	/**
	 * Create a Map Cell at a given position
	 * @param x
	 * @param y
	 */
	public MapCell(int x, int y) {
		this.setTilePos(x, y);
	}
	/**
	 * Create a map cell at a given position with a given tile
	 * @param x
	 * @param y
	 * @param tile
	 */
	public MapCell(int x, int y, BaseTile tile) {
		this.setTilePos(x, y);
		this.setTile(tile);
	}
	/**
	 * Create a map cell with specified tile and sheet IDs at a given position
	 * @param x
	 * @param y
	 * @param tileID
	 * @param sheetID
	 */
	public MapCell(int x, int y, int tileID, int sheetID) {
		this.setTilePos(x, y);
		this.setTile(new BaseTile(tileID, sheetID));
	}
	/**
	 * Create a map cell with specified tile and sheet IDs at a given position on a given layer
	 * @param x
	 * @param y
	 * @param tileID
	 * @param sheetID
	 * @param layer
	 */
	public MapCell(int x, int y, int tileID, int sheetID, int layer) {
		this.setTilePos(x, y);
		this.setTile(new BaseTile(tileID, sheetID), layer);
	}
}

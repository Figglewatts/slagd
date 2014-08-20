package figglewatts.slagd.graphics.tile;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import figglewatts.slagd.Settings;

public class TileMap {
	public MapCell[][] Cells;
	public BoundingBox[] EdgeBounds = new BoundingBox[4];
	
	private int mapWidth;
	private int mapHeight;
	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	
	/**
	 * Fills the map with a tile of given specifications
	 * @param tile BaseTile instance
	 */
	public void fillWithTile(BaseTile tile) {
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				this.Cells[y][x] = new MapCell(x, y, tile);
				//System.out.println("Cell at: " + x + ", " + y + " is : " + this.Cells[y][x]);
			}
		}
	}
	
	/**
	 * Fills the map with a tile of given specifications
	 * @param tileID
	 * @param sheetID
	 */
	public void fillWithTile(int tileID, int sheetID) {
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				this.Cells[y][x] = new MapCell(x, y, tileID, sheetID);
			}
		}
	}
	
	public MapCell getCell(int x, int y) {
		//System.out.println("Getting cell at: " + x + ", " + y);
		//System.out.println("Cell: " + this.Cells[y][x]);
		return this.Cells[y][x];
	}
	
	public BaseTile getTile(int x, int y) {
		return this.Cells[y][x].getTile(0);
	}
	public BaseTile getTile(int x, int y, int layer) {
		return this.Cells[y][x].getTile(layer);
	}
	
	/**
	 * Sets the tile at (x, y)
	 * @param x
	 * @param y
	 * @param tileID
	 * @param sheetID
	 */
	public void setTile(int x, int y, int tileID, int sheetID) {
		this.Cells[y][x].setTile(tileID, sheetID);
	}
	/**
	 * Sets the tile on given layer at (x, y)
	 * @param x
	 * @param y
	 * @param tileID
	 * @param sheetID
	 * @param layer
	 */
	public void setTile(int x, int y, int tileID, int sheetID, int layer) {
		this.Cells[y][x].setTile(tileID, sheetID, layer);
	}
	/**
	 * Sets the tile at (x, y)
	 * @param x
	 * @param y
	 * @param tile BaseTile instance
	 */
	public void setTile(int x, int y, BaseTile tile) {
		this.Cells[y][x].setTile(tile);
	}
	/**
	 * Sets the tile on given layer at (x, y)
	 * @param x
	 * @param y
	 * @param tile BaseTile instance
	 * @param layer
	 */
	public void setTile(int x, int y, BaseTile tile, int layer) {
		this.Cells[y][x].setTile(tile, layer);
	}
	
	public Vector2 worldPosToTilePos(Vector2 worldPos) {
		return new Vector2((float)Math.floor(worldPos.x / Tile.TILE_WIDTH), (float)Math.floor(worldPos.y / Tile.TILE_HEIGHT)); 
	}
	public Vector2 worldPosToTilePos(int x, int y) {
		return new Vector2((float)Math.floor(x / Tile.TILE_WIDTH), (float)Math.floor(y / Tile.TILE_HEIGHT));
	}
	
	public void render(SpriteBatch batch, OrthographicCamera cam, int squaresDown, int squaresAcross) {
		Vector3 cameraPosition = new Vector3(cam.position.x-((Settings.VIRTUAL_VIEWPORT_WIDTH*cam.zoom)/2), cam.position.y-((Settings.VIRTUAL_VIEWPORT_HEIGHT*cam.zoom)/2), 0);

		Vector2 firstSquare = new Vector2((cameraPosition.x) / Tile.TILE_WIDTH, (cameraPosition.y) / Tile.TILE_HEIGHT);
		int firstX = (int)firstSquare.x;
		int firstY = (int)firstSquare.y;
		
		for (int y = 0; y < squaresDown; y++) {
			for (int x = 0; x < squaresAcross; x++) {
				Vector3 screenCoords = cam.project(new Vector3((x+firstX)*Tile.TILE_WIDTH, (y+firstY)*Tile.TILE_HEIGHT, 0), cameraPosition.x, cameraPosition.y, cam.viewportWidth, cam.viewportHeight);
				MapCell thisCell = this.getCell(x+firstX, y+firstY);
				for (int t = 0; t < thisCell.BaseTiles.size; t++) {
					BaseTile tile = thisCell.BaseTiles.get(t);
					TextureRegion srcRect = Tile.getSourceRectangle(tile.getTileID(), tile.getSheetID());
					batch.draw(Tile.TILESET_TEXTURES.get(tile.getSheetID()),
							screenCoords.x,
							screenCoords.y,
							(float)Tile.TILE_WIDTH,
							(float)Tile.TILE_HEIGHT,
							(int)srcRect.getRegionX(),
							(int)srcRect.getRegionY(),
							(int)srcRect.getRegionWidth(),
							(int)srcRect.getRegionHeight(),
							false, false);
				}
			}
		}
	}
	
	public TileMap(int width, int height) {
		this.Cells = new MapCell[height][width];
		this.mapWidth = width;
		this.mapHeight = height;
		fillWithTile(0, 0);
	}
}

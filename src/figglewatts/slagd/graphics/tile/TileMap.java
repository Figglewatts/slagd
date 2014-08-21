package figglewatts.slagd.graphics.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.*;

import figglewatts.slagd.Settings;

/**
 * A class to represent a map of tiles. Contains methods to manipulate tiles.
 * @author Figglewatts
 *
 */
public class TileMap {
	/**
	 * A 2-dimensional array containing information about each map cell
	 */
	public MapCell[][] Cells;
	
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
	
	/**
	 * Converts a world position (in pixels) to a tile position (in tiles)
	 * @param worldPos
	 * @return A vector2 containing the given point's position in tiles
	 */
	public Vector2 worldPosToTilePos(Vector2 worldPos) {
		return new Vector2((float)Math.floor(worldPos.x / Tile.TILE_WIDTH), (float)Math.floor(worldPos.y / Tile.TILE_HEIGHT)); 
	}
	/**
	 * Converts a world position (in pixels) to a tile position (in tiles)
	 * @param x
	 * @param y
	 * @return A vector2 containing the given point's position in tiles
	 */
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
	
	/**
	 * Create a tile map with given width and height, and fill it with tile 0 from sprite sheet 0
	 * @param width
	 * @param height
	 */
	public TileMap(int width, int height) {
		this.Cells = new MapCell[height][width];
		this.mapWidth = width;
		this.mapHeight = height;
		fillWithTile(0, 0);
	}
	
	/**
	 * Create a tile map from a .tmx file from the program Tiled
	 * @param xmlInput The XML string to deserialize (.tmx file string)
	 * @param pathToTilesheets The path to the folder in /assets where the tilesheets are kept
	 */
	public TileMap(String xmlInput, String pathToTilesheets) {
		if (pathToTilesheets != "") {
			pathToTilesheets += "/";
		}
		XmlReader reader = new XmlReader();
		Element mapData = reader.parse(xmlInput);
		this.mapWidth = Integer.parseInt(mapData.getAttribute("width"));
		this.mapHeight = Integer.parseInt(mapData.getAttribute("height"));
		this.Cells = new MapCell[this.mapHeight][this.mapWidth];
		Tile.TILE_WIDTH = Integer.parseInt(mapData.getAttribute("tilewidth"));
		Tile.TILE_HEIGHT = Integer.parseInt(mapData.getAttribute("tileheight"));
		fillWithTile(0, 0);
		Array<Element> tileSets = mapData.getChildrenByName("tileset");
		Array<Integer> firstGid = new Array<Integer>();
		for (Element tileSet : tileSets) {
			String source = tileSet.getChildByName("image").getAttribute("source");
			Tile.addTilesheet(new Texture(Gdx.files.internal(pathToTilesheets + source)));
			firstGid.add(Integer.parseInt(tileSet.getAttribute("firstgid")));
		}
		
		Array<Element> layers = mapData.getChildrenByName("layer");
		int layerIndex = 0;
		for (Element layer : layers) {
			Array<Element> tiles = layer.getChildByName("data").getChildrenByName("tile");
			int i = 0;
			int tileSheetIndex = 0;
			for (int y = this.mapHeight-1; y >= 0; y--) {
				for (int x = 0; x < this.mapWidth; x++) {
					// get tile id
					int gid = Integer.parseInt(tiles.get(i).getAttribute("gid"));
					
					if (gid == 0) {
						i++;
						continue; // tile is nonexistent
					}
					
					// calculate which tilesheet it's on and normalize gid
					for (int k = 0; k < firstGid.size; k++) { 
						tileSheetIndex = k;
						// if it's not the last tilesheet
						if (k != firstGid.size - 1) {
							// if it's between the start ID of this and the start ID of the next tilesheet
							if (gid >= firstGid.get(k) && gid < firstGid.get(k+1)) {
								gid -= firstGid.get(k); // calculate correct ID
								break;
							} else {
								continue;
							}
						} else {
							gid -= firstGid.get(k);
							break;
						}
					}
					this.Cells[y][x].setTile(new BaseTile(gid, tileSheetIndex), layerIndex);
					i++;
				}
			}
			layerIndex++;
		}
	}
	
	/**
	 * Create a tile map from a .tmx file from the program Tiled
	 * @param tmx The FileHandle of the .tmx file
	 * @param pathToTilesheets The path to the folder in /assets where the tilesheets are kept
	 */
	public TileMap(FileHandle tmx, String pathToTilesheets) {
		this(tmx.readString(), pathToTilesheets);
	}
}

package figglewatts.slagd.graphics.tile;

/**
 * A data structure to hold a tile's tile ID and sheet ID;
 * @author Figglewatts
 *
 */
public class BaseTile {
	/**
	 * the index of the tile on the tile sheet
	 */
	private int tileID;
	/**
	 * the index of the tile sheet
	 */
	private int sheetID;
	public int getTileID() {
		return tileID;
	}
	public void setTileID(int tileID) {
		this.tileID = tileID;
	}
	public int getSheetID() {
		return sheetID;
	}
	public void setSheetID(int sheetID) {
		this.sheetID = sheetID;
	}
	
	/**
	 * Create a BaseTile with given tile and sheet IDs
	 * @param tileID
	 * @param sheetID
	 */
	public BaseTile(int tileID, int sheetID) {
		this.setTileID(tileID);
		this.setSheetID(sheetID);
	}
}

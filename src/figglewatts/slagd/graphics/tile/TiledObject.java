package figglewatts.slagd.graphics.tile;

import java.util.HashMap;
import java.util.Map;


public class TiledObject {
	private String name;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Map<String, String> properties = new HashMap<String, String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void addProperty(String name, String value) {
		properties.put(name, value);
	}
	public String getProperty(String name) {
		return properties.get(name);
	}
}

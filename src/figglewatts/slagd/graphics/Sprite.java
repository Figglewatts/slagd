package figglewatts.slagd.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Sprite {
	private Texture texture;
	private Color drawCol;
	private Vector2 position;
	private Vector2 centre;
	private int width;
	private int height;
	private float rotation;

	public Texture getTexture() {
		return this.texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public Color getDrawCol() {
		return this.drawCol;
	}
	public void setDrawCol(Color drawCol) {
		this.drawCol = drawCol;
	}
	public Vector2 getPosition() {
		return this.position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public void setPosition(int x, int y) {
		this.position = new Vector2(x, y);
	}
	public Vector2 getCentre() {
		this.setCentre(new Vector2(this.position.x + (this.width/2), this.position.y + (this.height/2)));
		return this.centre;
	}
	public void setCentre(Vector2 centre) {
		this.centre = centre;
	}
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getXPos() {
		return (int)this.position.x;
	}
	public void setXPos(int x) {
		this.position.x = x;
	}
	public int getYPos() {
		return (int)this.position.y;
	}
	public void setYPos(int y) {
		this.position.y = y;
	}
	
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	/**
	 * Create a sprite.
	 * @param x X position
	 * @param y Y position
	 * @param w Width
	 * @param h Height
	 * @param texture Texture
	 */
	public Sprite(int x, int y, int w, int h, Texture texture) {
		this.setPosition(new Vector2(x, y));
		this.setXPos(x);
		this.setYPos(y);
		this.setWidth(w);
		this.setHeight(h);
		this.setCentre(new Vector2(x + (w/2), y + (h/2)));
		this.setTexture(texture);
		this.setDrawCol(Color.WHITE);
	}
	
	/**
	 * Move the sprite x pixels along the X axis, and Y pixels along the Y axis. Accepts negative numbers.
	 * @param x
	 * @param y
	 */
	public void moveBy(int x, int y) {
		this.position.x += x;
		this.position.y += y;
	}
	public void moveBy(float x, float y) {
		this.position.x += x;
		this.position.y += y;
	}
	
	public void Draw(SpriteBatch batch) {
		Color cachedColor = batch.getColor();
		batch.setColor(this.drawCol);
		batch.draw(this.texture, this.position.x, this.position.y, this.width, this.height);
		batch.setColor(cachedColor);
	}
}

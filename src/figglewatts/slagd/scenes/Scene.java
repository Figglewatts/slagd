package figglewatts.slagd.scenes;

import com.badlogic.gdx.ApplicationAdapter;

public abstract class Scene<T extends ApplicationAdapter> {
	
	public SceneManager<T> parent;
	public String Identifer = "";
	public T game;
	public boolean	ShouldDraw = true;
	
	public Scene(SceneManager<T> par, T gme){
		parent = par;
		game = gme;
	};
	public abstract void load() throws Exception;
	public abstract void unload();
	public abstract void update(double delta);
	public abstract void draw();
	public abstract void pause();
	public abstract void resume();
}

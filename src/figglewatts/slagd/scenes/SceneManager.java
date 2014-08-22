package figglewatts.slagd.scenes;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;

public class SceneManager<T extends ApplicationAdapter> {
	public T parent;
	public List<Scene<T>> totalScenes = new ArrayList<Scene<T>>();
	private List<Scene<T>> currentScenes = new ArrayList<Scene<T>>();
	
	public SceneManager(T par) {
		parent = par;
	}
	

	public void addScene(Scene<T> scene) {
		try {
			scene.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentScenes.add(scene);
	}
	
	public void removeScene(Scene<T> scene) {
		scene.unload();
		currentScenes.remove(scene);
	}
	
	public void update(double delta) {
		for (int i = 0; i < currentScenes.size(); i++) { 
			Scene<T> item = currentScenes.get(i);
			item.update(delta);
		}
	}
	
	public void draw() {
		for (int i = 0; i < currentScenes.size(); i++) { 
			Scene<T> item = currentScenes.get(i);
			if(item.ShouldDraw)
				item.draw();
		}
	}
	
	public void pause() {
		for (int i = 0; i < currentScenes.size(); i++) { 
			Scene<T> item = currentScenes.get(i);
			item.pause();
		}
	}
	
	public void resume() {
		for (int i = 0; i < currentScenes.size(); i++) { 
			Scene<T> item = currentScenes.get(i);
			item.resume();
		}
	}
	
	public void dispose() {
		for (int i = 0; i < currentScenes.size(); i++) { 
			Scene<T> item = currentScenes.get(i);
			item.unload();
		}
	}
	
	public void switchScene(final Scene<T> scene) {
		removeScene(currentScenes.get(0));
		addScene(scene);
	}
}



package figglewatts.slagd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import figglewatts.slagd.scenes.SceneManager;

public abstract class SlagdAdapter extends ApplicationAdapter {
	
	// RENDERING
	public static FitViewport viewport;
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static float w;
	public static float h;
	
	// FRAMEBUFFER
	private FrameBuffer fbo;
	private SpriteBatch fboBatch;
	
	@Override
	public void create() {
		initialize(); // used for setting static constants, such as virtual viewport width and height, so no logic in here
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Settings.VIRTUAL_VIEWPORT_WIDTH, Settings.VIRTUAL_VIEWPORT_HEIGHT);
		viewport = new FitViewport(Settings.VIRTUAL_VIEWPORT_WIDTH, Settings.VIRTUAL_VIEWPORT_HEIGHT);
		
		batch = new SpriteBatch();
		
		initializeFBO();
		
		start(); // used for any startup logic
	}
	
	@Override
	public void dispose() {
		fbo.dispose();
		fboBatch.dispose();
		batch.dispose();
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void render() {
		camera.update();
		update(Gdx.graphics.getDeltaTime());
		
		// first, draw everything to a framebuffer
		fbo.begin();
		batch.begin();
		Gdx.gl.glClearColor(0.392F, 0.584F, 0.929F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		draw();
		batch.end();
		fbo.end();
		
		// then upscale the framebuffer and draw it on screen
		Gdx.gl.glViewport(viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		fboBatch.begin();
		Gdx.gl.glClearColor(0.392F, 0.584F, 0.929F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fboBatch.draw(fbo.getColorBufferTexture(), 0, 0, w, h, 0, 0, 1, 1);
		fboBatch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	@Override
	public void resume() {
		
	}
	
	private void initializeFBO() {
		if (fbo != null) {
			fbo.dispose();
		}
		fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Settings.VIRTUAL_VIEWPORT_WIDTH, Settings.VIRTUAL_VIEWPORT_HEIGHT, false);
		fbo.getColorBufferTexture().setFilter(Settings.MIN_FILTER, Settings.MAG_FILTER);
		
		if (fboBatch != null) {
			fboBatch.dispose();
		}
		fboBatch = new SpriteBatch();
	}
	
	protected abstract void initialize();
	protected abstract void start();
	protected abstract void draw();
	protected abstract void update(double delta);
}

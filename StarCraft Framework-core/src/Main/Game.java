package Main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import Handlers.GameStateManager;

public class Game implements ApplicationListener {
	public static final int V_WIDTH = 800;
	public static int V_HEIGHT = 400;
	public static final int SCALE = 2;
	public static final float STEP = 1 / 60f;
	private float count = 0;

	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;

	private GameStateManager gsm;
	long count2;

	@Override
	public void create() {
		sb = new SpriteBatch();
		cam = new OrthographicCamera(30, 30 * (this.V_HEIGHT / this.V_WIDTH));
		cam.setToOrtho(true, cam.viewportWidth, cam.viewportHeight);
		
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(true, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
		count2 = System.currentTimeMillis();

	}

	public void render() {
		count += Gdx.graphics.getDeltaTime();
		while (count >= STEP) {
			long delta = System.currentTimeMillis() - count2;
			if (delta >= 1000) {
				count2 = System.currentTimeMillis();
				gsm.tick();

			}
			count -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}

	public void dispose() {

	}

	public SpriteBatch getSb() {
		return sb;
	}

	public void setSb(SpriteBatch sb) {
		this.sb = sb;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}

	public void setHudCam(OrthographicCamera hudCam) {
		this.hudCam = hudCam;
	}

	public static int getvWidth() {
		return V_WIDTH;
	}

	public static int getvHeight() {
		return V_HEIGHT;
	}

	public static int getScale() {
		return SCALE;
	}

	@Override
	public void pause() {

	}

	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.position.set(width / 2f, height / 2f, 0);
	}

	@Override
	public void resume() {

	}

}

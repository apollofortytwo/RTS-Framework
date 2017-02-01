package States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import Handlers.GameStateManager;
import Main.Game;

public abstract class GameStates {
	protected GameStateManager gsm;
	protected Game game;
	protected World world;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	public Vector3 newCamPosition = new Vector3();

	protected GameStates(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.getGame();
		sb = game.getSb();
		cam = game.getCam();
		hudCam = game.getHudCam();

	}
	public abstract void update(Float delta);
	
	public abstract void tick();

	public abstract void render();

	public abstract void dispose();

	public GameStateManager getGsm() {
		return gsm;
	}

	public void setGsm(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
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

}

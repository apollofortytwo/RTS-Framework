package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import Handlers.EntityManager;
import Handlers.GameStateManager;
import Input.InputHandler;
import Input.KeyInput;
import Main.Game;

public class Play extends GameStates {

	BitmapFont font = new BitmapFont();
	private Box2DDebugRenderer b2dr;
	private EntityManager em;
	private KeyInput keyInput;
	TextInput ti;
	TiledMap map;
	IsometricTiledMapRenderer renderer;
	private SpriteBatch batch;
	private ShapeRenderer sr;

	public Play(GameStateManager gsm) {
		super(gsm);
		ti = new TextInput();
		Gdx.input.getTextInput(ti, "how many units do you want to appear?", "", "");
		world = new World(new Vector2(0, 0), true);

		em = new EntityManager(this);
		keyInput = new KeyInput(this, em);

		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("map.tmx");
		renderer = new IsometricTiledMapRenderer(map);

		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		batch = new SpriteBatch();
		b2dr = new Box2DDebugRenderer();
		cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight);
	}

	public void update(Float delta) {
		checkForInput();
		cam.update();
		em.update(delta);
		world.step(delta, 6, 3);
		keyInput.update(delta);
	}

	private void checkForInput() {
		if (ti.input != null) {
			em.spawn(Integer.parseInt(ti.input));
			ti.input = null;
			Gdx.input.setInputProcessor(keyInput);
			System.out.println(em.eList.size());
		}
	}

	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderMap();
		renderSelectionBox();
		renderShapes();
		renderEntities();
		// renderText();
		//cam.translate(this.newCamPosition);

		cam.update();

	}

	private void renderMap() {
		//cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight);
		//cam.translate(this.newCamPosition);
		cam.update();
		renderer.setView(cam);
		renderer.render();
	}

	private void renderEntities() {
		//cam.setToOrtho(true, cam.viewportWidth, cam.viewportHeight / 2);
		//cam.translate(this.newCamPosition);

		cam.update();
		b2dr.render(world, cam.combined);
		//cam.setToOrtho(true, cam.viewportWidth, cam.viewportHeight * 2);

		em.render();

	}

	private void renderText() {
		//cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight);
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		font.setColor(Color.RED);
		batch.enableBlending();
		font.draw(batch, "left Mouse to select || right mouse to move || 'A' to rally || Middle mouse to scatter",
				Game.V_WIDTH / 4, 25);
		font.draw(batch,
				"(L-Ctrl + num) Creates a group that can be reselected with just (num)|(L-Shift + num) merges groups| (0) selects all blocks ",
				10, Game.V_HEIGHT);
		batch.end();
	}

	private void renderShapes() {
		//cam.setToOrtho(true, cam.viewportWidth, cam.viewportHeight / 2);
	//	cam.translate(this.newCamPosition);
		cam.update();

		sr.setProjectionMatrix(cam.combined);
		sr.setColor(Color.BLUE);
		
		sr.begin();
		em.renderShapes(sr);

		sr.end();
		//cam.setToOrtho(true, cam.viewportWidth, cam.viewportHeight * 2);
	}

	private void renderSelectionBox() {
		//cam.setToOrtho(true, cam.viewportWidth, cam.viewportHeight);
		cam.update();
		sr.setProjectionMatrix(cam.combined);
		sr.setColor(Color.BLUE);

		sr.begin();
		if (keyInput.selection != null) {
			sr.rect(keyInput.selection.x, keyInput.selection.y, keyInput.selection.width, keyInput.selection.height);
		}

		sr.end();
	}

	public void tick() {
		System.out.println(InputHandler.getKeyPressed().entrySet());
		System.out.println(cam.position);
	}

	public void dispose() {

	}

	public Box2DDebugRenderer getB2dr() {
		return b2dr;
	}

	public void setB2dr(Box2DDebugRenderer b2dr) {
		this.b2dr = b2dr;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}

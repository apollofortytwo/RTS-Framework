package Input;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Entities.Entity;
import Entities.Grouping;
import Handlers.EntityManager;
import Handlers.GroupManager;
import Main.Game;
import States.GameStates;
import javafx.scene.input.KeyCode;

public class KeyInput implements InputProcessor {
	EntityManager em;
	GameStates gs;
	GroupManager gm;
	public Rectangle selection;
	private OrthographicCamera cam;

	public KeyInput(GameStates gs, EntityManager em) {
		this.gs = gs;
		this.em = em;
		this.gm = new GroupManager(em);
		
		cam = new OrthographicCamera(30, 30 * (Game.V_HEIGHT / Game.V_WIDTH));
		cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
	}

	public void update(float dt) {
		for (int i = 0; i < 10; i++) {
			if (InputHandler.checkKey("L-Ctrl") && InputHandler.checkKey(String.valueOf(i))) {
				if (!em.getSelection().isEmpty()) {
					gm.add(new Grouping((ArrayList<Entity>) em.getSelection().clone()), i);
				}

			} else if (InputHandler.checkKey("L-Shift") && InputHandler.checkKey(String.valueOf(i))) {
				if (!em.getSelection().isEmpty()) {
					if (gm.getgList().containsKey(i)) {
						ArrayList<Entity> list = (ArrayList<Entity>) em.getSelection().clone();
						for (Entity e : list) {
							gm.getgList().get(i).getList().addAll(list);
						}
						System.out.println("mergin");

					} else {
						gm.add(new Grouping((ArrayList<Entity>) em.getSelection().clone()), i);
					}

				}

			} else if (InputHandler.checkKey(String.valueOf(i))) {
				gm.selectGroupEntities(i);
			} else if (InputHandler.checkKey("0")) {
				gm.selectAll();
			}
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		String input = Input.Keys.toString(keycode);
		InputHandler.setKey(input, true);

		if (InputHandler.checkKey("Down")) {
			gs.getCam().position.y -= 5;
		}
		if (InputHandler.checkKey("Up")) {
			gs.getCam().position.y += 5;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		String input = Input.Keys.toString(keycode);
		InputHandler.setKey(input, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if (character == 'a') {
			Vector3 vPosition = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			vPosition.y = Gdx.graphics.getHeight() - 1 - vPosition.y;
			for (Entity en : em.eList) {
				en.moveTo(vPosition.x, vPosition.y / 2);
			}
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 vPosition = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		vPosition.y = (gs.getCam().viewportHeight - screenY) * (Gdx.graphics.getHeight() / gs.getCam().viewportHeight);
		
		for (Entity en : em.eList) {
			if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
				for (Entity select : em.getSelection()) {
					select.moveTo(screenX, screenY / 2);
				}
			} else if (Gdx.input.isButtonPressed(Buttons.MIDDLE)) {
				int x = (int) (Math.random() * Game.V_WIDTH);
				int y = (int) (Math.random() * Game.V_HEIGHT);
				en.moveTo(x, y);
			} else if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
				em.getSelection().clear();
				selection = new Rectangle();
				vPosition.y = Gdx.graphics.getHeight() - 1 - vPosition.y;
				selection.setPosition(new Vector2(screenX, vPosition.y));
				selection.setSize(1, 1);
			}
		}
		return false;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			em.setSelection(containment(selection));
			selection = null;
		}
		return false;
	}

	private ArrayList<Entity> containment(Rectangle sel) {
		ArrayList<Entity> inside = new ArrayList<Entity>();
		Rectangle selection = new Rectangle(sel.x, sel.y / 2, sel.width, sel.height / 2);

		if (0 > selection.width) {
			selection.x += selection.width;
		}
		if (0 > selection.height) {
			selection.y += selection.height;
		}

		selection.width = Math.abs(selection.width);
		selection.height = Math.abs(selection.height);

		for (Entity en : em.eList) {
			if (selection.overlaps(en.getBounds())) {
				inside.add(en);
			}
		}
		return inside;

	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 vPosition = cam.unproject(new Vector3(screenX, screenY, 0), gs.getCam().position.x, gs.getCam().position.y, gs.getCam().viewportWidth, gs.getCam().viewportHeight);
		vPosition.y = (gs.getCam().viewportHeight - screenY) * (Gdx.graphics.getHeight() / gs.getCam().viewportHeight);
		vPosition.y = Gdx.graphics.getHeight() - 1 - vPosition.y;
		if (selection != null) {
			selection.setSize(screenX - selection.x, vPosition.y - selection.y);
			em.setSelection(containment(selection));
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Game.V_HEIGHT += amount;
		return false;
	}

}

package Handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import Entities.Block;
import Entities.Entity;
import Main.Game;
import States.GameStates;

public class EntityManager {
	public ArrayList<Entity> eList = new ArrayList<Entity>();
	private ArrayList<Entity> selection = new ArrayList<Entity>();
	GameStates gs;

	public EntityManager(GameStates gs) {
		this.gs = gs;
	}

	public void render() {
		for (Entity en : eList) {
			en.render();
		}
	}

	public ArrayList<Entity> geteList() {
		return eList;
	}

	public void seteList(ArrayList<Entity> eList) {
		this.eList = eList;
	}

	public ArrayList<Entity> getSelection() {
		return selection;
	}

	public void setSelection(ArrayList<Entity> selection) {
		this.selection = selection;
	}

	public GameStates getGs() {
		return gs;
	}

	public void setGs(GameStates gs) {
		this.gs = gs;
	}

	public void update(float delta) {
		for (Entity en : eList) {
			if (selection.contains(en)) {
				en.setSelected(true);
			} else {
				en.setSelected(false);
			}
			en.update(delta);
		}
	}

	public void tick() {

	}

	public void add(Entity block) {
		eList.add(block);
	}

	public void remove(Entity block) {
		eList.remove(block);
	}

	public void spawn(int num) {
		for (int i = 0; i <= num; i++) {
			int x = (int) (Math.random() * Game.V_WIDTH);
			int y = (int) (Math.random() * Game.V_HEIGHT);
			this.add(new Block(x, y, 5, 5, gs));
		}
	}
	
	public ArrayList<Entity> findHighlighted(){
		ArrayList<Entity> highlighted = new ArrayList<Entity>();
		for(Entity en: eList){
			if(en.isSelected()){
				highlighted.add(en);
			}
		}
		return highlighted;
	}

	public void renderShapes(ShapeRenderer sr) {
		for (Entity en : eList) {
			en.renderShapes(sr);
		}
	}

}

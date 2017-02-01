package Entities;

import java.util.ArrayList;

public class Grouping {
	private ArrayList<Entity> enList;

	public Grouping(ArrayList<Entity> list) {

		enList = list;
	}

	public void add(Entity en) {
		enList.add(en);
	}

	public void remove(Entity en) {
		enList.remove(en);
	}

	public void setList(ArrayList<Entity> eList) {
		this.enList = eList;
	}

	public void clearList() {
		enList.clear();
	}

	public ArrayList<Entity> getList() {
		return enList;
	}

}

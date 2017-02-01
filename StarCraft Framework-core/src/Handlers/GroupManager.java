package Handlers;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.Map;

import Entities.Entity;
import Entities.Grouping;

public class GroupManager {
	private HashMap<Integer, Grouping> gList = new HashMap<Integer, Grouping>();
	private EntityManager em;

	public GroupManager(EntityManager em) {
		this.em = em;
	}

	public void selectGroupEntities(int id) {
		if (!gList.containsKey(id)) {
			System.out.println("no group found for: " + id);
			return;
		} else {
			System.out.println(gList.get(id).getList().size());
			ArrayList<Entity> list = new ArrayList<Entity>();
			for (Entity en : gList.get(id).getList()) {
				list.add(en);
			}
			em.setSelection(list);
		}
	}

	public void add(Grouping group, int id) {
		gList.put(id, group);
	}

	public void remove(Grouping group) {
		gList.remove(group);
	}

	public HashMap<Integer, Grouping> getgList() {
		return gList;
	}

	public void setgList(HashMap<Integer, Grouping> gList) {
		this.gList = gList;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void selectAll() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		for (Entity en : em.eList) {
			list.add(en);
		}
		em.setSelection(list);
	}
}

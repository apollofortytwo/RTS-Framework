package Entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import States.GameStates;

public abstract class Entity {
	protected Vector2 vel = new Vector2(0, 0);
	protected Vector2 pos;
	protected Vector2 size, des;
	protected BodyDef bdef = new BodyDef();
	protected FixtureDef fdef = new FixtureDef();
	protected PolygonShape shape = new PolygonShape();
	protected Body body;
	protected GameStates gs;
	protected Fixture fixture;
	protected Rectangle bounds = new Rectangle();
	protected boolean selected = false;

	CircleShape cShape = new CircleShape();
	Entity(int xPosition, int yPosition, int width, int height, GameStates gs) {
		this.gs = gs;
		pos = new Vector2(xPosition, yPosition);
		size = new Vector2(width, height);

		bdef.position.set(pos);
		cShape.setRadius(width);
		fdef.shape = cShape;
		
		bounds.set(xPosition, yPosition, width, height);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public abstract void update(float delta);

	public abstract void render();

	public abstract void tick();

	public abstract void moveTo(float x, float y);

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	public Vector2 getVel() {
		return vel;
	}

	public void setVel(Vector2 vel) {
		this.vel = vel;
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getDes() {
		return des;
	}

	public void setDes(Vector2 des) {
		this.des = des;
	}

	public BodyDef getBdef() {
		return bdef;
	}

	public void setBdef(BodyDef bdef) {
		this.bdef = bdef;
	}

	public FixtureDef getFdef() {
		return fdef;
	}

	public void setFdef(FixtureDef fdef) {
		this.fdef = fdef;
	}

	public PolygonShape getShape() {
		return shape;
	}

	public void setShape(PolygonShape shape) {
		this.shape = shape;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public GameStates getGs() {
		return gs;
	}

	public void setGs(GameStates gs) {
		this.gs = gs;
	}

	public void renderShapes(ShapeRenderer sr) {
	
	}

}

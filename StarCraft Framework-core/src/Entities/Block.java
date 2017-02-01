package Entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import States.GameStates;

public class Block extends Entity {
	int speed = 10;

	public Block(int xPosition, int yPosition, int width, int height, GameStates gs) {
		super(xPosition, yPosition, width, height, gs);
		bdef.type = BodyType.DynamicBody;
		body = gs.getWorld().createBody(bdef);
		show();
	}

	@Override
	public void update(float delta) {
		if (des != null) {
			moveTo(des.x, des.y);
		}
		pos.x += vel.x;
		pos.y += vel.y;

		vel.set(0, 0);
		
	}

	@Override
	public void render() {
		if (des != null) {
			body.setTransform(pos, 0);
		} else {
			body.applyLinearImpulse(0, 0, pos.x, pos.y, true);

		}
		pos = body.getPosition();
		bounds.setPosition(pos);
	}

	@Override
	public void renderShapes(ShapeRenderer sr) {
		if (selected) {
			sr.circle(pos.x, pos.y, size.x * 1.5f);
		}

	}

	@Override
	public void moveTo(float xPos, float yPos) {
		des = new Vector2(xPos, yPos);
		double distance = Math.sqrt(Math.pow(des.x - pos.x, 2) + Math.pow(des.y - pos.y, 2));
		float amountToMoveX = (float) (((des.x - pos.x) / distance) * speed);
		float amountToMoveY = (float) (((des.y - pos.y) / distance) * speed);
		this.vel.set(amountToMoveX, amountToMoveY);
		if (distance <= speed) {
			vel.set(0, 0);
			des = null;
		}

	}

	@Override
	public void tick() {

	}

	public void show() {
		if (fixture == null) {
			fixture = body.createFixture(fdef);
		}
	}

	public void hide() {
		if (fixture != null) {
			body.destroyFixture(fixture);
			fixture = null;
		}
	}

}

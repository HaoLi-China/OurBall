package game.ourball.physics;

import game.ourball.base.Sprite;
import game.ourball.gameview.BoxScreen;
import game.ourball.gameview.TwoPlayersActivity;
import game.ourball.gui.engine.GameScreen;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Message;

public class Circle extends Physics {

	private CircleDef circleDef;

	/**
	 * @param world
	 *            世界
	 * @param x
	 *            中心坐标x
	 * @param y
	 *            中心坐标y
	 * @param radius
	 *            半径
	 * @param density
	 *            密度
	 * @param friction
	 *            摩擦力
	 * @param restitution
	 *            碰撞后恢复系数
	 */
	protected Circle(World world, float x, float y, float radius,
			float density, float friction, float restitution) {
		// TODO Auto-generated constructor stub
		circleDef = new CircleDef();
		circleDef.radius = radius;
		circleDef.density = density;
		circleDef.friction = friction;
		circleDef.restitution = restitution;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		body = world.createBody(bodyDef);
		body.createShape(circleDef);
		body.setMassFromShapes();
	}

	public int getDiameter() {
		return (int) (circleDef.radius * 2);
	}

	public Vec2 getPosition() {
		return body.getPosition();
	}

	public CircleDef getCircleDef() {
		return circleDef;
	}

	public void setCircleDef(CircleDef circleDef) {
		this.circleDef = circleDef;
	}

	@Override
	public void draw(Canvas canvas) {
		if (BoxScreen.gameMode == 0) {
			if (!BoxScreen.isFailed && !BoxScreen.isSucceed) {
				Vec2 vec2 = getPosition();

				if (sprite != null) {
					sprite.draw(canvas, vec2.x - circleDef.radius, vec2.y
							- circleDef.radius);
				} else if (bitmap != null) {
					canvas.drawBitmap(bitmap, vec2.x - circleDef.radius, vec2.y
							- circleDef.radius, paint);
				} else {
					setColor(drawColor);
					canvas.drawCircle(vec2.x, vec2.y, circleDef.radius, paint);
				}
			}
		} else {
			if (TwoPlayersActivity.isDraw && !BoxScreen.isFailed
					&& !BoxScreen.isSucceed) {
				Vec2 vec2 = getPosition();

				if (sprite != null) {
					sprite.draw(canvas, vec2.x - circleDef.radius, vec2.y
							- circleDef.radius);
				} else if (bitmap != null) {
					canvas.drawBitmap(bitmap, vec2.x - circleDef.radius, vec2.y
							- circleDef.radius, paint);
				} else {
					setColor(drawColor);
					canvas.drawCircle(vec2.x, vec2.y, circleDef.radius, paint);
				}

				if (vec2.x > GameScreen.screenW) {
					TwoPlayersActivity.isDraw = false;
					Message msg = getHandler().obtainMessage(
							TwoPlayersActivity.MESSAGE_SEND);
					Bundle bundle = new Bundle();
					bundle.putString(TwoPlayersActivity.TIP, "left");
					bundle.putString(TwoPlayersActivity.YLOCATION, vec2.y
							/ GameScreen.screenH + "");
					msg.setData(bundle);
					getHandler().sendMessage(msg);
				}
				if (vec2.x < 0) {
					TwoPlayersActivity.isDraw = false;
					Message msg = getHandler().obtainMessage(
							TwoPlayersActivity.MESSAGE_SEND);
					Bundle bundle = new Bundle();
					bundle.putString(TwoPlayersActivity.TIP, "right");
					bundle.putString(TwoPlayersActivity.YLOCATION, vec2.y
							/ GameScreen.screenH + "");
					msg.setData(bundle);
					getHandler().sendMessage(msg);
				}
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (sprite != null)
			sprite.update();
	}

	@Override
	public void bind(Bitmap bitmap, int frameWidth, int frameHeight, int delay) {
		// TODO Auto-generated method stub
		sprite = new Sprite(bitmap, frameWidth, frameHeight);
		sprite.setDelay(delay);
	}

	@Override
	public void bind(Bitmap bitmap) {
		// TODO Auto-generated method stub
		this.bitmap = bitmap;
	}

}

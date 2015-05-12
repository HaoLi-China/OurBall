package game.ourball.physics;

import game.ourball.base.Sprite;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;

public class Rectangle extends Physics {

	private PolygonDef polygonDef;
	private float halfW;
	private float halfH;

	/**
	 * @param world
	 *            世界
	 * @param x
	 *            中心坐标x
	 * @param y
	 *            中心坐标y
	 * @param halfWidth
	 *            宽度一半
	 * @param halfHeight
	 *            高度一半
	 * @param density
	 *            密度
	 * @param friction
	 *            摩擦力
	 * @param restitution
	 *            碰撞后恢复系数
	 */
	protected Rectangle(World world, float x, float y, float halfWidth,
			float halfHeight, float density, float friction, float restitution) {
		// TODO Auto-generated constructor stub
		polygonDef = new PolygonDef();
		path = new Path();
		polygonDef.density = density;
		polygonDef.friction = friction;
		polygonDef.restitution = restitution;
		halfW = halfWidth;
		halfH = halfHeight;
		polygonDef.setAsBox(halfWidth, halfHeight);
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		body = world.createBody(bodyDef);
		body.createShape(polygonDef);
		body.setMassFromShapes();
		vertices = new float[polygonDef.getVertexCount() << 1];
	}

	public PolygonDef getPolygonDef() {
		return polygonDef;
	}

	public void setPolygonDef(PolygonDef polygonDef) {
		this.polygonDef = polygonDef;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Vec2 vec2 = body.getPosition();
		path.reset();
		for (int i = 0, j = 0; i < polygonDef.getVertexCount(); i++) {
			vertices[j++] = polygonDef.vertices.get(i).x + vec2.x;
			vertices[j++] = polygonDef.vertices.get(i).y + vec2.y;
			if (i == 0)
				path.moveTo(vertices[j - 2], vertices[j - 1]);
			else
				path.lineTo(vertices[j - 2], vertices[j - 1]);
		}
		path.close();

		if (sprite != null) {
			sprite.draw(canvas, vec2.x - halfW, vec2.y - halfH);
		} else if (bitmap != null) {
			canvas.drawBitmap(bitmap, vec2.x - halfW, vec2.y - halfH, null);
		} else {
			setColor(drawColor);
			canvas.drawPath(path, paint);
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

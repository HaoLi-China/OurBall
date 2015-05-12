package game.ourball.physics;

import game.ourball.gui.engine.GameScreen;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.content.Context;
import android.graphics.Canvas;

public abstract class PhysicsScreen extends GameScreen {

	private ArrayList<Physics> physicsV;
	private AABB aabb;
	private World world;
	private float dt = 1f / 12f;
	private int iterations = 10;

	public PhysicsScreen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		physicsV = new ArrayList<Physics>();
	}

	public ArrayList<Physics> getPhysics() {
		return physicsV;
	}

	public void setPhysics(ArrayList<Physics> physics) {
		this.physicsV = physics;
	}

	public AABB getAabb() {
		return aabb;
	}

	public void setAabb(AABB aabb) {
		this.aabb = aabb;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public float getDt() {
		return dt;
	}

	public void setDt(float dt) {
		this.dt = dt;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	/*
	 * public Bitmap getBackground() { return background; }
	 * 
	 * public void setBackground(Bitmap background) { this.background =
	 * background; }
	 */

	public void createWorld(Vec2 lower, Vec2 upper, Vec2 gravity,
			boolean doSleep) {
		aabb = new AABB();
		aabb.lowerBound = lower;
		aabb.upperBound = upper;
		world = new World(aabb, gravity, doSleep);
	}

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
	public Circle createCircle(float x, float y, float radius, float density,
			float friction, float restitution) {
		return new Circle(world, x, y, radius, density, friction, restitution);
	}

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
	public Rectangle createRectangle(float x, float y, float halfWidth,
			float halfHeight, float density, float friction, float restitution) {
		return new Rectangle(world, x, y, halfWidth, halfHeight, density,
				friction, restitution);
	}

	/**
	 * 创建一个多边形，顶点添加顺序为顺时针依次添加
	 * 
	 * @param world
	 *            世界
	 * @param x
	 *            中心坐标x
	 * @param y
	 *            中心坐标y
	 * @param vertices
	 *            顶点个数
	 * @param density
	 *            密度
	 * @param friction
	 *            摩擦力
	 * @param restitution
	 *            碰撞后恢复系数
	 */
	public Polygon createPolygon(float x, float y, float density,
			float friction, float restitution) {
		return new Polygon(world, x, y, density, friction, restitution);
	}

	public void add(Physics physics) {
		synchronized (physicsV) {
			if (physics instanceof DistanceJoint_) {
				physicsV.add(physics);
				physicsV.add(((DistanceJoint_) physics).getP1());
				physicsV.add(((DistanceJoint_) physics).getP2());
			} else {
				physicsV.add(physics);
			}
		}
	}

	public void remove(int location) {
		world.destroyBody(physicsV.get(location).getBody());
		physicsV.remove(location);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for (int i = 0; i < physicsV.size(); i++) {
			Physics physics = physicsV.get(i);
			physics.update();
		}
		world.step(dt, iterations);
	}

	@Override
	public void draw(Canvas canvas) {

		for (int i = 0; i < physicsV.size(); i++) {
			Physics physics = physicsV.get(i);
			physics.draw(canvas);
		}
	}

}

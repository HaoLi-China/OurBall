package game.ourball.gameview;

import game.ourball.gui.engine.BaseActivity;
import game.ourball.mainview.MainView;
import game.ourball.mainview.R;
import game.ourball.param.BasicParam;
import game.ourball.param.LevelParam;
import game.ourball.physics.Circle;
import game.ourball.physics.PhysicsScreen;
import game.ourball.physics.Rectangle;
import game.ourball.util.Constant;
import game.ourball.util.VibrationUtil;

import java.util.Random;

import org.jbox2d.common.Vec2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class BoxScreen extends PhysicsScreen implements SensorEventListener {
	// 小球
	private Circle ball;

	// 挑战成功/失败标志
	public static boolean isFailed = false;
	public static boolean isSucceed = false;

	// 模式0：单人模式 1：双人模式
	public static int gameMode = 0;

	// 关卡
	private int level = 0;

	// 背景图片
	private Bitmap background = null;
	private Bitmap background1 = null;
	private Bitmap background2 = null;

	// 终点图片
	private Bitmap finalHolePic = null;

	// 重点坐标
	private float finalHoleX;
	private float finalHoleY;

	// 小洞图片
	private Bitmap holePic = null;

	// 小洞坐标数组
	private float holeParam_x[];
	private float holeParam_y[];

	// 对应小球掉入洞中后的动画帧序号
	private int count = 0;

	// 小球掉入洞中的坐标
	private float failedX = 0;
	private float failedY = 0;

	// SensorManager管理器
	private SensorManager mSensorMgr = null;
	Sensor mSensor = null;

	// 重力感应X轴 Y轴的重力值
	private float mGX = 0;
	private float mGY = 0;

	private Handler handler = null;

	/**
	 * @param context
	 * @param handler
	 * @param mode
	 *            //0：单人模式； 1：双人模式
	 */
	public BoxScreen(Context context, Handler handler, int mode, int level) {
		super(context);

		gameMode = mode;
		this.level = level;
		this.handler = handler;

		background = getBitmap("background.jpg");
		background = Bitmap.createScaledBitmap(background, screenW, screenH,
				true);

		createWorld(new Vec2(-BasicParam.ballRadiusParam * screenW, 0),
				new Vec2(getWidth() + BasicParam.ballRadiusParam * screenW,
						getHeight()), new Vec2(0, 0), false);

		Bitmap ballPic = getBitmap("ball.png");
		ballPic = Bitmap.createScaledBitmap(ballPic,
				(int) (screenW * BasicParam.ballPicWidthParam),
				(int) (screenW * BasicParam.ballPicHeightParam), true);
		ball = createCircle(screenW * BasicParam.ballStartLocationParam,
				getHeight() - screenW * BasicParam.ballStartLocationParam,
				screenW * BasicParam.ballRadiusParam, BasicParam.ballDensity,
				BasicParam.ballFriction, BasicParam.ballRestitution);
		ball.bind(ballPic, (int) (screenW * BasicParam.ballPicWidthParam),
				(int) (screenW * BasicParam.ballPicWidthParam), 30);
		ball.setHandler(handler);

		if (gameMode == 0) {
			init(0);
		} else {
			background1 = getBitmap("background1.jpg");
			background1 = Bitmap.createScaledBitmap(background1, screenW,
					screenH, true);
			background2 = getBitmap("background2.jpg");
			background2 = Bitmap.createScaledBitmap(background2, screenW,
					screenH, true);
		}

		addTop();
		addBottom();

		add(ball);

		// 得到SensorManager对象
		mSensorMgr = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorMgr.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_GAME);

	}

	// 根据不同模式初始化资源
	public void init(int mode) {
		float balataParam_x[];
		float balataParam_y[];
		float balataParam_w[];
		float balataParam_h[];

		float boardParam_x[];
		float boardParam_y[];
		float boardParam_w[];
		float boardParam_h[];

		LevelParam lp = new LevelParam();

		holePic = getBitmap("hole.png");
		holePic = Bitmap.createScaledBitmap(holePic,
				(int) (screenW * BasicParam.holePicWidthParam),
				(int) (screenW * BasicParam.holePicHeightParam), true);

		switch (mode) {
		case 0:
			finalHolePic = getBitmap("final.png");
			finalHolePic = Bitmap.createScaledBitmap(finalHolePic,
					(int) (screenW * BasicParam.finalHolePicWidthParam),
					(int) (screenW * BasicParam.finalHolePicHeightParam), true);

			finalHoleX = lp.getFinalHoleParam_xl(level) * screenW;
			finalHoleY = lp.getFinalHoleParam_yl(level) * screenH;

			holeParam_x = lp.getHoleParam_xl(level);
			holeParam_y = lp.getHoleParam_yl(level);

			// 橡胶
			balataParam_x = lp.getBalataParam_xl(level);
			balataParam_y = lp.getBalataParam_yl(level);
			balataParam_w = lp.getBalataParam_w(level);
			balataParam_h = lp.getBalataParam_h(level);

			// 设置障碍物
			boardParam_x = lp.getBoardParam_xl(level);
			boardParam_y = lp.getBoardParam_yl(level);
			boardParam_w = lp.getBoardParam_w(level);
			boardParam_h = lp.getBoardParam_h(level);

			for (int i = 0; i < boardParam_x.length; i++) {
				Bitmap boardPic = null;

				if (boardParam_w[i] < boardParam_h[i]) {
					boardPic = getBitmap("board1.png");
				} else {
					boardPic = getBitmap("board2.png");
				}

				boardPic = Bitmap.createScaledBitmap(boardPic,
						(int) (screenW * boardParam_w[i]),
						(int) (screenH * boardParam_h[i]), true);
				Rectangle board = createRectangle(screenW * boardParam_x[i]
						+ (int) (screenW * boardParam_w[i]) / 2, screenH
						* boardParam_y[i] + (int) (screenH * boardParam_h[i])
						/ 2, (int) (screenW * boardParam_w[i]) / 2,
						(int) (screenH * boardParam_h[i]) / 2,
						BasicParam.boardDensity, BasicParam.boardFriction,
						BasicParam.boardRestitution);
				board.bind(boardPic);

				add(board);
			}

			for (int i = 0; i < balataParam_x.length; i++) {

				Bitmap balataPic = getBitmap("balata.png");
				balataPic = Bitmap.createScaledBitmap(balataPic,
						(int) (screenW * balataParam_w[i]),
						(int) (screenH * balataParam_h[i]), true);
				Rectangle balata = createRectangle(screenW * balataParam_x[i]
						+ (int) (screenW * balataParam_w[i]) / 2, screenH
						* balataParam_y[i] + (int) (screenH * balataParam_h[i])
						/ 2, (int) (screenW * balataParam_w[i]) / 2,
						(int) (screenH * balataParam_h[i]) / 2,
						BasicParam.balataDensity, BasicParam.balataFriction,
						BasicParam.balataRestitution);
				balata.bind(balataPic);

				add(balata);
			}

			addLeft();
			addRight();
			break;
		case 1:
			if (TwoPlayersActivity.isFirstDevice) {
				background = background1;

				holeParam_x = lp.getHoleParam_xl(0, level);
				holeParam_y = lp.getHoleParam_yl(0, level);

				// 橡胶
				balataParam_x = lp.getBalataParam_xl(0, level);
				balataParam_y = lp.getBalataParam_yl(0, level);
				balataParam_w = lp.getBalataParam_w(0, level);
				balataParam_h = lp.getBalataParam_h(0, level);

				// 设置障碍物
				boardParam_x = lp.getBoardParam_xl(0, level);
				boardParam_y = lp.getBoardParam_yl(0, level);
				boardParam_w = lp.getBoardParam_w(0, level);
				boardParam_h = lp.getBoardParam_h(0, level);
			} else {
				background = background2;

				finalHolePic = getBitmap("final.png");
				finalHolePic = Bitmap.createScaledBitmap(finalHolePic,
						(int) (screenW * BasicParam.finalHolePicWidthParam),
						(int) (screenW * BasicParam.finalHolePicHeightParam),
						true);

				finalHoleX = lp.getFinalHoleParam_xl(level) * screenW;
				finalHoleY = lp.getFinalHoleParam_yl(level) * screenH;

				holeParam_x = lp.getHoleParam_xl(1, level);
				holeParam_y = lp.getHoleParam_yl(1, level);

				// 橡胶
				balataParam_x = lp.getBalataParam_xl(1, level);
				balataParam_y = lp.getBalataParam_yl(1, level);
				balataParam_w = lp.getBalataParam_w(1, level);
				balataParam_h = lp.getBalataParam_h(1, level);

				// 设置障碍物
				boardParam_x = lp.getBoardParam_xl(1, level);
				boardParam_y = lp.getBoardParam_yl(1, level);
				boardParam_w = lp.getBoardParam_w(1, level);
				boardParam_h = lp.getBoardParam_h(1, level);
			}

			for (int i = 0; i < boardParam_x.length; i++) {
				Bitmap boardPic = null;

				if (boardParam_w[i] < boardParam_h[i]) {
					boardPic = getBitmap("board1.png");
				} else {
					boardPic = getBitmap("board2.png");
				}

				boardPic = Bitmap.createScaledBitmap(boardPic,
						(int) (screenW * boardParam_w[i]),
						(int) (screenH * boardParam_h[i]), true);
				Rectangle board = createRectangle(screenW * boardParam_x[i]
						+ (int) (screenW * boardParam_w[i]) / 2, screenH
						* boardParam_y[i] + (int) (screenH * boardParam_h[i])
						/ 2, (int) (screenW * boardParam_w[i]) / 2,
						(int) (screenH * boardParam_h[i]) / 2,
						BasicParam.boardDensity, BasicParam.boardFriction,
						BasicParam.boardRestitution);
				board.bind(boardPic);

				add(board);
			}

			for (int i = 0; i < balataParam_x.length; i++) {

				Bitmap balataPic = getBitmap("balata.png");
				balataPic = Bitmap.createScaledBitmap(balataPic,
						(int) (screenW * balataParam_w[i]),
						(int) (screenH * balataParam_h[i]), true);
				Rectangle balata = createRectangle(screenW * balataParam_x[i]
						+ (int) (screenW * balataParam_w[i]) / 2, screenH
						* balataParam_y[i] + (int) (screenH * balataParam_h[i])
						/ 2, (int) (screenW * balataParam_w[i]) / 2,
						(int) (screenH * balataParam_h[i]) / 2,
						BasicParam.balataDensity, BasicParam.balataFriction,
						BasicParam.balataRestitution);
				balata.bind(balataPic);

				add(balata);
			}
			break;
		}
	}

	// 获得ball对象
	public Circle getBall() {
		return ball;
	}

	// 增加上边界
	public void addTop() {
		Rectangle top = createRectangle(getWidth() >> 1, 0, getWidth() >> 1,
				BasicParam.sideThickness / 2, BasicParam.sideDensity,
				BasicParam.sideFriction, BasicParam.sideRestitution);

		add(top);
	}

	// 增加下边界
	public void addBottom() {
		Rectangle bottom = createRectangle(getWidth() >> 1, getHeight(),
				getWidth() >> 1, BasicParam.sideThickness / 2,
				BasicParam.sideDensity, BasicParam.sideFriction,
				BasicParam.sideRestitution);
		add(bottom);
	}

	// 增加左边界
	public void addLeft() {
		Rectangle left = createRectangle(0, getHeight() >> 1,
				BasicParam.sideThickness / 2, getHeight() >> 1,
				BasicParam.sideDensity, BasicParam.sideFriction,
				BasicParam.sideRestitution);
		add(left);
	}

	// 增加右边界
	public void addRight() {
		Rectangle right = createRectangle(getWidth(), getHeight() >> 1,
				BasicParam.sideThickness / 2, getHeight() >> 1,
				BasicParam.sideDensity, BasicParam.sideFriction,
				BasicParam.sideRestitution);
		add(right);
	}

	// 增加左边界（双人）
	public void addLeftForTwoPlayers() {
		Rectangle left = createRectangle(-BasicParam.ballRadiusParam * screenW,
				getHeight() >> 1, BasicParam.sideThickness / 2,
				getHeight() >> 1, BasicParam.sideDensity,
				BasicParam.sideFriction, BasicParam.sideRestitution);
		add(left);
	}

	// 增加右边界（双人）
	public void addRightForTwoPlayers() {
		Rectangle right = createRectangle(getWidth()
				+ BasicParam.ballRadiusParam * screenW, getHeight() >> 1,
				BasicParam.sideThickness / 2, getHeight() >> 1,
				BasicParam.sideDensity, BasicParam.sideFriction,
				BasicParam.sideRestitution);
		add(right);
	}

	@Override
	public void draw(Canvas canvas) {
		if (background != null) {
			canvas.drawBitmap(background, 0, 0, paint);
		}

		float x2 = this.getBall().getBody().getPosition().x;
		float y2 = this.getBall().getBody().getPosition().y;

		if (holePic != null) {
			for (int i = 0; i < holeParam_x.length; i++) {
				canvas.drawBitmap(holePic, holeParam_x[i] * screenW,
						holeParam_y[i] * screenH, paint);
				float x1 = holeParam_x[i] * screenW + screenW
						* BasicParam.holePicWidthParam / 2;
				float y1 = holeParam_y[i] * screenH + screenW
						* BasicParam.holePicHeightParam / 2;

				if (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) < Math.pow(
						screenW * BasicParam.ballRadiusParam, 2)
						&& count == 0
						&& !isFailed && !isSucceed) {
					if (gameMode == 0) {
						playFailedSound();
						isFailed = true;
						count = 40;
						failedX = holeParam_x[i] * screenW;
						failedY = holeParam_y[i] * screenH;
					} else {
						if (TwoPlayersActivity.isDraw) {
							playFailedSound();
							isFailed = true;
							count = 40;
							failedX = holeParam_x[i] * screenW;
							failedY = holeParam_y[i] * screenH;
						}
					}
				}
			}
		}

		if (finalHolePic != null) {
			canvas.drawBitmap(finalHolePic, finalHoleX, finalHoleY, paint);
			if (Math.pow(finalHoleX + screenW
					* BasicParam.finalHolePicWidthParam / 2 - x2, 2)
					+ Math.pow(finalHoleY + screenW
							* BasicParam.finalHolePicHeightParam / 2 - y2, 2) < Math
						.pow(screenW * BasicParam.ballRadiusParam, 2)
					&& count == 0 && !isFailed && !isSucceed) {
				if (gameMode == 0) {
					playSucceedSound();
					isSucceed = true;
					count = 40;
				} else {
					if (TwoPlayersActivity.isDraw) {
						playSucceedSound();
						isSucceed = true;
						count = 40;
					}
				}
			}
		}

		if (count > 0 && isFailed) {
			Bitmap bitmap = null;
			if ((count + 1) / 2 < 12) {
				bitmap = getBitmap("ball_0" + (21 - (count + 1) / 2) + ".png");
			} else if ((count + 1) / 2 >= 12) {
				bitmap = getBitmap("ball_00" + (21 - (count + 1) / 2) + ".png");
			}
			bitmap = Bitmap.createScaledBitmap(bitmap,
					(int) (screenW * BasicParam.ballPicWidthParam),
					(int) (screenW * BasicParam.ballPicHeightParam), true);
			if (bitmap != null) {
				canvas.drawBitmap(bitmap, failedX, failedY, paint);
			}
			count--;
			if (count == 0) {
				if (gameMode == 0) {
					Message msg = handler
							.obtainMessage(OnePlayerActivity.FAILED);
					handler.sendMessage(msg);
				} else {
					Random rand = new Random();
					int index = rand.nextInt(Constant.punishment.length);
					TwoPlayersActivity.isDraw = false;
					Message msg1 = handler
							.obtainMessage(TwoPlayersActivity.MESSAGE_SEND);
					Bundle bundle1 = new Bundle();
					bundle1.putString(TwoPlayersActivity.TIP, "failed"
							+ Constant.punishment[index]);

					msg1.setData(bundle1);
					handler.sendMessage(msg1);

					Message msg2 = handler
							.obtainMessage(TwoPlayersActivity.FAILED);
					Bundle bundle2 = new Bundle();
					bundle2.putString(TwoPlayersActivity.TIP,
							Constant.punishment[index]);

					msg2.setData(bundle2);
					handler.sendMessage(msg2);
				}
			}
		}

		if (count > 0 && isSucceed) {

			Bitmap bitmap = null;
			if ((count + 1) / 2 < 12) {
				bitmap = getBitmap("ball_0" + (21 - (count + 1) / 2) + ".png");
			} else if ((count + 1) / 2 >= 12) {
				bitmap = getBitmap("ball_00" + (21 - (count + 1) / 2) + ".png");
			}
			bitmap = Bitmap.createScaledBitmap(bitmap,
					(int) (screenW * BasicParam.finalHolePicWidthParam),
					(int) (screenW * BasicParam.finalHolePicHeightParam), true);
			if (bitmap != null) {
				canvas.drawBitmap(bitmap, finalHoleX, finalHoleY, paint);
			}
			count--;
			if (count == 0) {
				if (gameMode == 0) {
					Message msg = handler
							.obtainMessage(OnePlayerActivity.SUCCEED);
					handler.sendMessage(msg);
				} else {
					TwoPlayersActivity.isDraw = false;
					Message msg1 = handler
							.obtainMessage(TwoPlayersActivity.MESSAGE_SEND);
					Bundle bundle = new Bundle();
					bundle.putString(TwoPlayersActivity.TIP, "succeed");

					msg1.setData(bundle);
					handler.sendMessage(msg1);

					Message msg2 = handler
							.obtainMessage(TwoPlayersActivity.SUCCEED);
					handler.sendMessage(msg2);
				}
			}
		}

		super.draw(canvas);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public boolean onKeyDown(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Random rand = new Random();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressLint("NewApi")
	@Override
	public void onSensorChanged(SensorEvent event) {
		mGX = (event.values[SensorManager.DATA_X]) * 2;
		mGY = (event.values[SensorManager.DATA_Y]) * 2;
		// mGZ = event.values[SensorManager.DATA_Z];

		if (gameMode == 0) {
			if (!isFailed && !isSucceed) {
				getWorld().setGravity(new Vec2(mGY * 3, mGX * 3));
			} else {
				getWorld().setGravity(new Vec2(0, 0));
			}
		}

		else {
			if (TwoPlayersActivity.isDraw && !isFailed && !isSucceed) {
				getWorld().setGravity(new Vec2(mGY * 3, mGX * 3));
			} else {
				getWorld().setGravity(new Vec2(0, 0));
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	// 成功事件处理
	public void succeedHandle(final BaseActivity ba) {

		VibrationUtil.Vibrate(ba, 500); // 震动0.5s
		if (gameMode == 0) {
			if (level + 1 < Constant.maxLevelForOne) {
				new AlertDialog.Builder(ba)
						.setMessage("挑战成功!")
						.setNegativeButton("下一关",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent();
										intent.putExtra("level", level + 1);
										intent.setClass(ba,
												OnePlayerActivity.class);
										ba.startActivity(intent);
										System.exit(0);
									}
								})
						.setPositiveButton("返回",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Intent intent = new Intent();
										intent.setClass(ba, MainView.class);
										ba.startActivity(intent);
										System.exit(0);
									}
								}).show();
			} else {
				new AlertDialog.Builder(ba)
						.setMessage("通关啦!")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Intent intent = new Intent();
										intent.setClass(ba, MainView.class);
										ba.startActivity(intent);
										System.exit(0);
									}
								}).show();
			}
		} else {
			if (level + 1 < Constant.maxLevelForTwo) {
				new AlertDialog.Builder(ba)
						.setMessage("挑战成功!")
						.setNegativeButton("下一关",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent();
										intent.putExtra("level", level + 1);
										intent.setClass(ba,
												TwoPlayersActivity.class);
										ba.startActivity(intent);
										System.exit(0);
									}
								})
						.setPositiveButton("返回",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Intent intent = new Intent();
										intent.setClass(ba, MainView.class);
										ba.startActivity(intent);
										System.exit(0);
									}
								}).show();
			} else {
				new AlertDialog.Builder(ba)
						.setMessage("通关啦！")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Intent intent = new Intent();
										intent.setClass(ba, MainView.class);
										ba.startActivity(intent);
										System.exit(0);
									}
								}).show();
			}
		}
	}

	// 失败事件处理
	public void failHandle(final BaseActivity ba, String tip) {
		// Toast.makeText(ba, Constant.failedTip, Toast.LENGTH_SHORT).show();
		VibrationUtil.Vibrate(ba, 100); // 震动0.5s

		if (gameMode == 0) {
			reset();
		}
		if (gameMode == 1) {
			new AlertDialog.Builder(ba)
					.setMessage(tip)
					.setNegativeButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (TwoPlayersActivity.isFirstDevice) {
										TwoPlayersActivity.isDraw = true;
									}
									reset();
								}
							}).show();
		}
	}

	// 重置游戏参数
	private void reset() {
		isFailed = false;
		failedX = 0;
		failedY = 0;
		this.getBall()
				.getBody()
				.setXForm(
						new Vec2(screenW * BasicParam.ballStartLocationParam,
								getHeight() - screenW
										* BasicParam.ballStartLocationParam), 0);
	}

	// 播放闯关成功音效
	public void playSucceedSound() {
		MediaPlayer succeedSound = MediaPlayer.create(context, R.raw.success);
		succeedSound.setLooping(false);
		succeedSound.start();
	}

	// 播放闯关失败音效
	public void playFailedSound() {
		MediaPlayer failedSound = MediaPlayer.create(context, R.raw.failed);
		failedSound.setLooping(false);
		failedSound.start();
	}
}

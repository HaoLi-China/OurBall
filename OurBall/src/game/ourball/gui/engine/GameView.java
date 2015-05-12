package game.ourball.gui.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Paint.Style;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class GameView extends SurfaceView implements Callback, Runnable {

	private SurfaceHolder holder;
	protected static boolean isRun = true;
	public static int GAME_HEART = 33;
	private Thread gameThread;
	private long STARTTIME = 0;
	private long USETIME = 0;
	private Paint paint;
	private GameScreen curScreen;
	private boolean showFPS;
	private boolean showDeclare;
	private int color = 0x000000;
	private static GameView instance;
	private int fpsColor = 0xffffff;
	protected PaintFlagsDrawFilter filter;
	private boolean useFilter;

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		instance = this;
		// 初始化活动音效
		holder = getHolder();
		holder.addCallback(this);
		gameThread = new Thread(this);
	}

	protected static GameView getInstance() {
		return instance;
	}

	protected void setShowFPS(boolean showFPS, int fpsColor) {
		this.showFPS = showFPS;
		this.fpsColor = fpsColor;
	}

	protected void setShowDeclare(boolean showDeclare) {
		this.showDeclare = showDeclare;
	}

	public void setUseFilter(boolean useFilter) {
		this.useFilter = useFilter;
		if (useFilter)
			filter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
					| Paint.FILTER_BITMAP_FLAG);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int w, int h) {
		// TODO Auto-generated method stub
	}

	private static boolean isStarted;

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		if (!isStarted)
			handInit();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		isRun = false;
	}

	private void handInit() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);// 无锯齿
		paint.setStyle(Style.FILL);
		starThread();
	}

	private void starThread() {
		if (gameThread == null)
			gameThread = new Thread(this);
		gameThread.start();
		isStarted = true;
	}

	public void setColor(int color) {
		int red = (color & 0xff0000) >> 16;
		int green = (color & 0x00ff00) >> 8;
		int blue = (color & 0x0000ff);
		paint.setColor(Color.rgb(red, green, blue));
	}

	private void drawFPS(Canvas canvas) {
		setColor(fpsColor);
		if (USETIME > 0)
			canvas.drawText("FPS:" + (1000 / USETIME), 3,
					3 + paint.getTextSize(), paint);
	}

	private void drawDeclare(Canvas canvas) {
		setColor(0x000000);
		canvas.drawText(declare, 0, (int) paint.getTextSize(), paint);
	}

	private String declare = "本软件所有权归山东大学纸纸和筒筒";

	public void draw() {
		Canvas canvas = holder.lockCanvas();
		if (useFilter)
			canvas.setDrawFilter(filter);
		setColor(color);
		canvas.drawRect(0, 0, GameScreen.screenW, GameScreen.screenH, paint);
		curScreen.draw(canvas);
		if (showFPS)
			drawFPS(canvas);
		if (showDeclare)
			drawDeclare(canvas);
		holder.unlockCanvasAndPost(canvas);
	}

	public boolean keyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return curScreen.onKeyDown(keyCode, event);
	}

	public boolean keyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return curScreen.onKeyUp(keyCode, event);
	}

	public boolean touchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return curScreen.onTouchEvent(event);
	}

	private void update() {
		curScreen.update();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRun) {
			STARTTIME = System.currentTimeMillis();
			update();
			draw();
			USETIME = System.currentTimeMillis() - STARTTIME;
			if (USETIME < GAME_HEART) {
				try {
					Thread.sleep(GAME_HEART - USETIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int getBackGroundColor() {
		return color;
	}

	public void setBackgroundColor(int color) {
		this.color = color;
	}

	public void setCurScreen(GameScreen curScreen) {
		if (curScreen == null)
			throw new IllegalArgumentException("screnn is null");
		this.curScreen = curScreen;
	}

	public GameScreen getCurScreen() {
		return curScreen;
	}

	public void stop(int resID) {
	}

	protected static void setGAME_HEART(int gAME_HEART) {
		GAME_HEART = gAME_HEART;
	}

}

package game.ourball.gui.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends Activity {

	private static GameView view;
	private boolean landscape;
	private boolean fullScreen;
	private boolean filter;
	private WakeLock wakeLock = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		// 从Intent当中根据key取得value
		int level = intent.getIntExtra("level", 0);

		init(this, level);
	}

	public void createEngine() {
		if (landscape) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		if (fullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}

		// 设置屏幕锁
		wakeLock = ((PowerManager) getSystemService(POWER_SERVICE))
				.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
						| PowerManager.ON_AFTER_RELEASE, "My Lock");

		GameScreen.screenW = getWindowManager().getDefaultDisplay().getWidth();
		GameScreen.screenH = getWindowManager().getDefaultDisplay().getHeight();
		view = new GameView(this);
		view.setUseFilter(filter);
		setContentView(view);
	}

	public boolean isLandscape() {
		return landscape;
	}

	public void setLandscape(boolean landscape) {
		this.landscape = landscape;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public void showFPS(boolean b, int fpsColor) {
		view.setShowFPS(b, fpsColor);
	}

	public void setBackGround(int color) {
		view.setBackgroundColor(color);
	}

	public void setScreen(GameScreen screen) {
		if (view == null)
			throw new RuntimeException("must be first use create engine");
		view.setCurScreen(screen);
		GameView.isRun = true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return view.touchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return view.keyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return view.keyUp(keyCode, event);
	}

	// 保持屏幕常亮
	@Override
	protected void onResume() {
		super.onResume();
		wakeLock.acquire();
	}

	// 关闭屏幕常亮
	@Override
	protected void onPause() {
		super.onPause();
		if (wakeLock != null) {
			wakeLock.release();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public abstract void init(Context context, int level);

}
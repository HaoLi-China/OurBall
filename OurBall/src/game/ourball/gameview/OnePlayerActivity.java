package game.ourball.gameview;

import game.ourball.gui.engine.BaseActivity;
import game.ourball.mainview.LevelView1;
import game.ourball.util.Constant;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

public class OnePlayerActivity extends BaseActivity {

	public static final int FAILED = 0;
	public static final int SUCCEED = 1;

	private BoxScreen boxScreen = null;

	private OnePlayerActivity opa = null;

	@Override
	public void init(Context context, int level) {
		opa = this;

		setLandscape(true);// 设置横屏
		setFullScreen(true);// 设置全屏
		setFilter(true);// 设置图片无锯齿
		createEngine();// 创建引擎

		boxScreen = new BoxScreen(context, mHandler, 0, level);
		setScreen(boxScreen);// 设置显示屏幕
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(OnePlayerActivity.this, LevelView1.class);
			OnePlayerActivity.this.startActivity(intent);
			System.exit(0);
		}

		return super.onKeyDown(keyCode, event);
	}

	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case FAILED:
				boxScreen.failHandle(opa, Constant.failedTip);
				break;

			case SUCCEED:
				boxScreen.succeedHandle(opa);
				break;
			}
		}
	};
}
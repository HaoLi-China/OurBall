package game.ourball.mainview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class MainView extends Activity {

	private ImageButton imgbt_begin1;
	private ImageButton imgbt_begin2;
	private ImageButton imgbt_help;
	private ImageButton imgbt_about;
	private ImageView img_title;

	private AbsoluteLayout.LayoutParams params = null;

	// 屏幕宽度和高度
	protected int screenWidth = 0;
	protected int screenHeight = 0;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		//无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.main);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();


		// 开始图片按钮，进入开始游戏
		imgbt_begin1 = (ImageButton) findViewById(R.id.bt_begin1);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 4,
				screenHeight / 5, screenWidth * 5 / 8, screenHeight * 1 / 7);
		imgbt_begin1.setLayoutParams(params);
		imgbt_begin1.setBackgroundResource(R.drawable.bt_begin);
		imgbt_begin1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_begin1.setBackgroundResource(R.drawable.bt_begin_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_begin1.setBackgroundResource(R.drawable.bt_begin);
					break;
				}
				return false;
			}
		});
		imgbt_begin1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainView.this, LevelView1.class);
				MainView.this.startActivity(intent);
				System.exit(0);
			}
		});
		
		// 开始图片按钮，进入开始游戏
				imgbt_begin2 = (ImageButton) findViewById(R.id.bt_begin2);
				params = new AbsoluteLayout.LayoutParams( screenWidth / 4,
						screenHeight / 5, screenWidth * 5 / 8, screenHeight * 9 / 28);
				imgbt_begin2.setLayoutParams(params);
				imgbt_begin2.setBackgroundResource(R.drawable.bt_begin2);
				imgbt_begin2.setOnTouchListener(new OnTouchListener() {
					public boolean onTouch(View arg0, MotionEvent arg1) {
						switch (arg1.getAction()) {
						case MotionEvent.ACTION_DOWN:
							imgbt_begin2.setBackgroundResource(R.drawable.bt_begin2_2);
							break;
						case MotionEvent.ACTION_UP:
							imgbt_begin2.setBackgroundResource(R.drawable.bt_begin2);
							break;
						}
						return false;
					}
				});
				imgbt_begin2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(MainView.this, LevelView2.class);
						MainView.this.startActivity(intent);
						System.exit(0);
					}
				});

		// 帮助图片按钮，进入游戏帮助界面
		imgbt_help = (ImageButton) findViewById(R.id.bt_help);
		params = new AbsoluteLayout.LayoutParams( screenWidth / 4,
				screenHeight / 5, screenWidth * 5 / 8, screenHeight * 1 / 2);
		imgbt_help.setLayoutParams(params);
		imgbt_help.setBackgroundResource(R.drawable.bt_help);
		imgbt_help.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_help.setBackgroundResource(R.drawable.bt_help_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_help.setBackgroundResource(R.drawable.bt_help);
					break;
				}
				return false;
			}
		});
		imgbt_help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainView.this, HelpView.class);
				MainView.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 关于我们
		imgbt_about = (ImageButton) findViewById(R.id.bt_about);
		params = new AbsoluteLayout.LayoutParams(screenWidth / 4,
				screenHeight / 5, screenWidth * 5 / 8, screenHeight * 19 / 28);
		imgbt_about.setLayoutParams(params);
		imgbt_about.setBackgroundResource(R.drawable.bt_about);
		imgbt_about.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					imgbt_about.setBackgroundResource(R.drawable.bt_about_2);
					break;
				case MotionEvent.ACTION_UP:
					imgbt_about.setBackgroundResource(R.drawable.bt_about);
					break;
				}
				return false;
			}
		});
		imgbt_about.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainView.this, AboutView.class);
				MainView.this.startActivity(intent);
				System.exit(0);
			}
		});

		// 游戏的名称和logo
		img_title = (ImageView) findViewById(R.id.title);
		params = new AbsoluteLayout.LayoutParams(screenHeight * 7 / 8,
				screenHeight * 7 / 8, screenWidth / 20, screenHeight * 1 / 20);
		img_title.setLayoutParams(params);
		img_title.setBackgroundResource(R.drawable.title);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			new AlertDialog.Builder(this).setMessage("你确定要退出游戏吗？")
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).setPositiveButton("退出",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									System.exit(0);
								}
							}).show();
			return true;
		}
		return true;
	}

}

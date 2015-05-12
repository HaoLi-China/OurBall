package game.ourball.mainview;

import game.ourball.gameview.TwoPlayersActivity;
import game.ourball.util.Constant;
import game.ourball.util.ImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

/**
 * 双人模式的关卡列表
 * 
 */
public class LevelView2 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 无title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// 设置屏幕恒为横向
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.level);

		GridView gridview = (GridView) findViewById(R.id.gridview);// 找到main.xml中定义gridview
		// 的id
		gridview.setAdapter(new ImageAdapter(this));// 调用ImageAdapter.java
		gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));// 选中的时候为透明色

		gridview.setOnItemClickListener(new OnItemClickListener() {// 监听事件
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				handleClick(position);
			}
		});

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK){
			Intent intent = new Intent();
			intent.setClass(LevelView2.this, MainView.class);
			LevelView2.this.startActivity(intent);
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void handleClick(int position){
		if(position<Constant.maxLevelForTwo){
			Intent intent = new Intent();
			intent.putExtra("level", position);
			intent.setClass(LevelView2.this, TwoPlayersActivity.class);
			LevelView2.this.startActivity(intent);
			System.exit(0);
			}
			else{
				Toast.makeText(LevelView2.this, "未解锁...",
						Toast.LENGTH_SHORT).show();	
			}
	}
}
package game.ourball.gameview;

import game.ourball.bluetooth.BluetoothChatService;
import game.ourball.bluetooth.DeviceListActivity;
import game.ourball.gui.engine.BaseActivity;
import game.ourball.gui.engine.GameScreen;
import game.ourball.mainview.LevelView2;
import game.ourball.mainview.MainView;
import game.ourball.mainview.R;
import game.ourball.param.BasicParam;

import org.jbox2d.common.Vec2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * This is the main Activity for two players.
 */
public class TwoPlayersActivity extends BaseActivity {
	// Debugging
	private static final String TAG = "OurBall";
	private static final boolean D = true;

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	public static final int MESSAGE_SEND = 6;
	public static final int FAILED = 7;
	public static final int SUCCEED = 8;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";
	public static final String TIP = "tip";
	public static final String YLOCATION = "yl";

	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;

	public static boolean isDraw = false;
	public static boolean isFirstDevice = false;

	// Name of the connected device
	private String mConnectedDeviceName = null;

	private StringBuffer mOutStringBuffer;

	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the chat services
	private BluetoothChatService mChatService = null;

	private TwoPlayersActivity tpa;

	private BoxScreen boxScreen;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (D)
			Log.e(TAG, "+++ ON CREATE +++");

		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		// If the adapter is null, then Bluetooth is not supported
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available",
					Toast.LENGTH_LONG).show();
			Intent intent = new Intent();
			intent.setClass(TwoPlayersActivity.this, MainView.class);
			TwoPlayersActivity.this.startActivity(intent);
			System.exit(0);
		}
	}

	@Override
	public void init(Context context, int level) {
		// TODO Auto-generated method stub
		tpa = this;

		setLandscape(true);// 设置横屏
		setFullScreen(true);// 设置全屏
		setFilter(true);// 设置图片无锯齿
		createEngine();// 创建引擎

		boxScreen = new BoxScreen(context, mHandler, 1, level);

		setScreen(boxScreen);// 设置显示屏幕
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(TwoPlayersActivity.this, LevelView2.class);
			TwoPlayersActivity.this.startActivity(intent);
			finish();
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (D)
			Log.e(TAG, "++ ON START ++");

		// If BT is not on, request that it be enabled.
		// setupChat() will then be called during onActivityResult
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
			// Otherwise, setup the chat session
		} else {
			if (mChatService == null)
				setupChat();
		}

		new AlertDialog.Builder(this).setMessage("点击菜单键搜索蓝牙设备并配对后进行游戏!")
				.setNegativeButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}

	@Override
	public synchronized void onResume() {
		super.onResume();
		if (D)
			Log.e(TAG, "+ ON RESUME +");

		// Performing this check in onResume() covers the case in which BT was
		// not enabled during onStart(), so we were paused to enable it...
		// onResume() will be called when ACTION_REQUEST_ENABLE activity
		// returns.
		if (mChatService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
				// Start the Bluetooth chat services
				mChatService.start();
			}
		}
	}

	private void setupChat() {
		Log.d(TAG, "setupChat()");

		// Initialize the BluetoothChatService to perform bluetooth connections
		mChatService = new BluetoothChatService(this, mHandler);

		// Initialize the buffer for outgoing messages
		mOutStringBuffer = new StringBuffer("");
	}

	@Override
	public synchronized void onPause() {
		super.onPause();
		if (D)
			Log.e(TAG, "- ON PAUSE -");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (D)
			Log.e(TAG, "-- ON STOP --");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Stop the Bluetooth chat services
		if (mChatService != null)
			mChatService.stop();
		if (D)
			Log.e(TAG, "--- ON DESTROY ---");
	}

	private void ensureDiscoverable() {
		if (D)
			Log.d(TAG, "ensure discoverable");
		if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(
					BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}
	}

	/**
	 * Sends a message.
	 * 
	 * @param message
	 *            A string of text to send.
	 */
	private void sendMessage(String message) {
		// Check that we're actually connected before trying anything
		if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
					.show();
			return;
		}

		// Check that there's actually something to send
		if (message.length() > 0) {
			// Get the message bytes and tell the BluetoothChatService to write
			byte[] send = message.getBytes();
			mChatService.write(send);

			// Reset out string buffer to zero and clear the edit text field
			mOutStringBuffer.setLength(0);
			// mOutEditText.setText(mOutStringBuffer);
		}
	}

	// The Handler that gets information back from the BluetoothChatService
	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:
				if (D)
					Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
				switch (msg.arg1) {
				case BluetoothChatService.STATE_CONNECTED:
					break;
				case BluetoothChatService.STATE_CONNECTING:
					break;
				case BluetoothChatService.STATE_LISTEN:
					break;
				case BluetoothChatService.STATE_NONE:
					break;
				}
				break;
			case MESSAGE_WRITE:
				break;
			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				String readMessage = new String(readBuf, 0, msg.arg1);
				String message[] = readMessage.split("/");

				if (message[0].length() > 6
						&& (message[0].substring(0, 6)).equals("failed")) {
					boxScreen.failHandle(tpa, message[0].substring(6));
					if (isFirstDevice) {
						boxScreen.remove(boxScreen.getPhysics().size() - 1);
					}
				} else if (message[0].equals("succeed")) {
					boxScreen.succeedHandle(tpa);
				} else if (message[0].equals("left")) {
					boxScreen
							.getBall()
							.getBody()
							.setXForm(
									new Vec2(BasicParam.sideThickness+5, Float.parseFloat(message[1])
											* GameScreen.screenH), 0);

					boxScreen.remove(boxScreen.getPhysics().size() - 1);

					isDraw = true;
				} else if (message[0].equals("right")) {
					
					//System.out.println("recieved");
					boxScreen
							.getBall()
							.getBody()
							.setXForm(
									new Vec2(GameScreen.screenW-BasicParam.sideThickness-5,
											Float.parseFloat(message[1])
													* GameScreen.screenH), 0);

					boxScreen.remove(boxScreen.getPhysics().size() - 1);

					isDraw = true;
				}

				break;
			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(getApplicationContext(),
						"Connected to " + mConnectedDeviceName,
						Toast.LENGTH_SHORT).show();
				if (isFirstDevice) {
					boxScreen.init(1);
					boxScreen.addLeft();

					isDraw = true;
				} else {
					boxScreen.init(1);
					boxScreen.addRight();
					boxScreen.addLeftForTwoPlayers();
				}
				break;
			case MESSAGE_TOAST:
				Toast.makeText(getApplicationContext(),
						msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
						.show();
				break;
			case MESSAGE_SEND:
				String str1 = msg.getData().getString(TIP);
				if ((str1.length() > 6 && (str1.substring(0, 6))
						.equals("failed")) || str1.equals("succeed")) {
					tpa.sendMessage(str1);
				} else {
					String str2 = msg.getData().getString(YLOCATION);

					if (str1.equals("right")) {
						boxScreen.addLeftForTwoPlayers();
						boxScreen
								.getBall()
								.getBody()
								.setXForm(
										new Vec2(BasicParam.sideThickness+5,
												Float.parseFloat(str2)
														* GameScreen.screenH),
										0);
					} else if (str1.equals("left")) {
						//System.out.println("send");
						boxScreen.addRightForTwoPlayers();
						boxScreen
						.getBall()
						.getBody()
						.setXForm(
								new Vec2(GameScreen.screenW-BasicParam.sideThickness-5,
										Float.parseFloat(str2)
												* GameScreen.screenH),
								0);
					}
					tpa.sendMessage(str1 + "/" + str2);
				}

				break;
			case FAILED:
				if (!isFirstDevice) {
					boxScreen.addLeftForTwoPlayers();
				}
				String str = msg.getData().getString(TIP);
				boxScreen.failHandle(tpa, str);
				break;

			case SUCCEED:
				boxScreen.succeedHandle(tpa);
				break;
			}
		}
	};

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (D)
			Log.d(TAG, "onActivityResult " + resultCode);
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				// Get the device MAC address
				String address = data.getExtras().getString(
						DeviceListActivity.EXTRA_DEVICE_ADDRESS);
				// Get the BLuetoothDevice object
				BluetoothDevice device = mBluetoothAdapter
						.getRemoteDevice(address);
				// Attempt to connect to the device
				mChatService.connect(device);
				isFirstDevice = true;

			}
			break;
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session
				setupChat();
			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(TAG, "BT not enabled");
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(TwoPlayersActivity.this, MainView.class);
				TwoPlayersActivity.this.startActivity(intent);
				System.exit(0);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.scan:
			// Launch the DeviceListActivity to see devices and do scan
			Intent serverIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			return true;
		case R.id.discoverable:
			// Ensure this device is discoverable by others
			ensureDiscoverable();
			return true;
		}
		return false;
	}

}
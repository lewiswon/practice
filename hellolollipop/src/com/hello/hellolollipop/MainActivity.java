package com.hello.hellolollipop;

import java.io.InputStream;

import com.hello.hellolollipop.util.qrcoderecongnize.CaptureActivity;

import android.R.anim;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;

public class MainActivity extends Activity {
	
	private ContactsInfo contactsInfo;
	private MediaPlayer  mPlayer;
	private int musicPosition;
	class ContactsInfo{
		private String contractsPhone;
		private String contactsName;
		public String getContractsPhone() {
			return contractsPhone;
		}
		public void setContractsPhone(String contractsPhone) {
			this.contractsPhone = contractsPhone;
		}
		public String getContactsName() {
			return contactsName;
		}
		public void setContactsName(String contactsName) {
			this.contactsName = contactsName;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
				.commit();
//			getFragmentManager().beginTransaction().add(R.id.container, new newFragment()).commit();
		}
		mPlayer=MediaPlayer.create(this, R.raw.model);
		mPlayer.start();
	}
	@Override
	protected void onPause() {
		super.onPause();
		if(mPlayer.isPlaying()){
			mPlayer.pause();
			musicPosition=mPlayer.getCurrentPosition();
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		if(mPlayer!=null){
			mPlayer.seekTo(musicPosition);
			mPlayer.start();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		public PlaceholderFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
									Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}
	
	public static class newFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
									Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.activity_new, container,false);
			return rootView;
		}
	}
	public void openContact(View view){
		startActivity(new Intent(MainActivity.this,AnimationActivity.class));
	}
	public void openActivity(View view){
		
		String actiongString="android.intent.action.ADD_SHORTCUT";
		String action="com.hello.hellolollipop.intent.action.IntentDemo";
//		startActivityForResult(new Intent(actiongString),0);
//		startActivity(new Intent(action));
//		sendBroadcast(new Intent(actiongString));
		
		
		addShortcut("hello");
	}
	public void openList(View view)
	{
		startActivity(new Intent(MainActivity.this,ListFooterActivity.class));
	}
	public void qrCode(View view){
		startActivity(new Intent(MainActivity.this,QrCodeActivity.class));
	}
	public void arcodeRecongnize(View view)
	{
		startActivity(new Intent(MainActivity.this,CaptureActivity.class));
	}
	public void openVolley(View view){
		startActivity(new Intent(getApplicationContext(),VolleyDemoActivity.class));
	}
	private void addShortcut(String name) {
        Intent addShortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(MainActivity.this,
                        R.drawable.ic_launcher));
        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(MainActivity.this, MainActivity.class);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);
        // 发送广播
        sendBroadcast(addShortcutIntent);
    }
}

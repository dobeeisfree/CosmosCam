package com.example.cosmoscam;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		startActivity(new Intent(this,cosmosSplash.class));
		
		TabHost mTab=getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		// 첫번째 탭
		Drawable img1 = getResources().getDrawable(R.drawable.gallery_icon22);
		intent = new Intent(this, TabOne.class);
		spec = mTab.newTabSpec("갤러리").setIndicator("", img1).setContent(intent);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //탭 클릭시 갱신
		mTab.addTab(spec);
		
		// 두번째 탭
		Drawable img2 = getResources().getDrawable(R.drawable.addf_icon3);
		intent = new Intent(this, TabTwo.class);
		spec = mTab.newTabSpec("인물").setIndicator("", img2).setContent(intent);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //탭 클릭시 갱신
		mTab.addTab(spec);
		
		// 세번째 탭
		Drawable img3 = getResources().getDrawable(R.drawable.planet_icon3);
		intent = new Intent(this, TabThree.class);
		spec = mTab.newTabSpec("배경").setIndicator("", img3).setContent(intent);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //탭 클릭시 갱신
		mTab.addTab(spec);
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
}

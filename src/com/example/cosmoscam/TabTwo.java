package com.example.cosmoscam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TabTwo extends Activity {
	ImageView btIntroSpace;
	ImageView btIntroSpaceSuit;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab2);
	    // TODO Auto-generated method stub
	    btIntroSpace = (ImageView)findViewById(R.id.imageView1);
	    btIntroSpace.setOnClickListener(new onIntroSpace());
	    btIntroSpaceSuit = (ImageView)findViewById(R.id.imageView2);
	    btIntroSpaceSuit.setOnClickListener(new onIntroSpaceSuit());
	}
	 public class onIntroSpace implements OnClickListener {
	    	public void onClick(View v) {
	    		Intent intent1 = new Intent(TabTwo.this, TabTwoA.class);
	    		startActivity(intent1); 
	    	}
	 }
	 public class onIntroSpaceSuit implements OnClickListener {
	    	public void onClick(View v) {
	    		Intent intent1 = new Intent(TabTwo.this, TabTwoB.class);
	    		startActivity(intent1);
	    	}
	    }
}

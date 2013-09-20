package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button button;
	Button optionsButton;
	String font3 = "Jellyka_Estrya_Handwriting.ttf";
	Typeface face;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		face = Typeface.createFromAsset(getAssets(), font3);
		
		//TextView signature
		TextView signature = (TextView) findViewById(R.id.textView1);
		signature.setTypeface(face);
		
		//add Controllers
		registerControllers();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void registerControllers(){
		//Setting font.
		//Starts Game Button.
		button = (Button) findViewById(R.id.button1);
		
		button.setTypeface(face);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Up on click , trans to new activity.
			   Intent intent=new Intent();
			   intent.setClass(MainActivity.this,SecondaryActivity.class);
			   startActivity(intent);
			   finish();
			}
		});
		//Options Button
		optionsButton = (Button) findViewById(R.id.Button2);
		optionsButton.setTypeface(face);
		optionsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent();
				intent2.setClass(MainActivity.this, gameRulesView.class);
				startActivity(intent2);
			}
		});
	}
}

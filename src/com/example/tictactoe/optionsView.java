package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class optionsView extends Activity implements Observer{
	String font3 = "Jellyka_Estrya_Handwriting.ttf";
	Typeface face;
	TextView optionTitle;
	TextView normalOrTour;
	TextView whofirst;
	TextView oName;
	TextView xName;
	CheckBox modeCheck;
	CheckBox turnCheck;
	EditText oNameInput;
	EditText xNameInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEMO", "OPTIONview: on Creat");
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_options_view);
		face=Typeface.createFromAsset(getAssets(), font3);
		this.registerControllers();
		Game.model.addObserver(this);
	}
	@Override
	protected void onResume(){
		super.onRestart();
		if(Game.model.Xfirst){
			this.turnCheck.setChecked(true);
		}
		if(Game.model.Ofirst){
			this.turnCheck.setChecked(false);
		}
		this.oNameInput.setHint(Game.model.getOname());
		this.xNameInput.setHint(Game.model.getXname());
		this.modeCheck.setChecked(Game.model.getTourModeOn()?true:false);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Game.model.setDoneSetting(true);
		Game.model.setNewGame();
		Game.model.cleanTour();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void registerControllers(){
		optionTitle=(TextView)findViewById(R.id.optiontitleview);
		optionTitle.setTypeface(face);
		normalOrTour=(TextView)findViewById(R.id.normalortour);
		normalOrTour.setTypeface(face);
		
		whofirst=(TextView)findViewById(R.id.OorXfirst);
		whofirst.setTypeface(face);
		
		oName=(TextView)findViewById(R.id.Oname);
		oName.setTypeface(face);
		
		xName=(TextView)findViewById(R.id.Xname);
		xName.setTypeface(face);
	
		oNameInput=(EditText)findViewById(R.id.Oname_text);
		xNameInput=(EditText)findViewById(R.id.Xname_text);

		oNameInput.setOnEditorActionListener(new OnEditorActionListener() {	
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
			    String nameOfO=oNameInput.getText().toString();
			    Game.model.setOname(nameOfO);
				return false;
			}
		});
		
		xNameInput.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				String nameOfX=xNameInput.getText().toString();
				Game.model.setXname(nameOfX);
				return false;
			}
		});
		modeCheck=(CheckBox)findViewById(R.id.modecheck);
		modeCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					Game.model.setTourModeOn(true);
				}
				else if(!isChecked){
					Game.model.setTourModeOn(false);
				}
			}
		});
		turnCheck=(CheckBox)findViewById(R.id.oxcheckbox);
		turnCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked){
					Game.model.Xfirst=true;
					Game.model.Ofirst=false;
					
				}
				else if(!isChecked){
					Game.model.Ofirst=true;
					Game.model.Xfirst=false;
				}
				
			}
		});
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stulll
	}
}

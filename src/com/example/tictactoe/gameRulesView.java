package com.example.tictactoe;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class gameRulesView extends Activity{
	Typeface face;
	TextView gameRuleTitle;
	TextView gameRuleText;
	String font3 = "Jellyka_Estrya_Handwriting.ttf";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_rules_dialog);
		face = Typeface.createFromAsset(getAssets(), font3);
		registerViews();
		//TextView signature
	}
	public void registerViews(){
	   gameRuleTitle=(TextView)findViewById(R.id.textGameRule);
	   gameRuleText=(TextView)findViewById(R.id.textRule);
	   gameRuleTitle.setTypeface(face);
	   gameRuleText.setTypeface(face);
	}
}

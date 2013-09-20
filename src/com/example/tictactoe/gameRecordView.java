package com.example.tictactoe;




import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class gameRecordView extends Activity {
	Typeface face;
	String font3="Jellyka_Estrya_Handwriting.ttf";
	TextView title;
	TextView textList;
	StringBuilder tryout;
	String tmp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.record_view_dialog); 
		face=Typeface.createFromAsset(getAssets(), font3);
		
		textList=(TextView)findViewById(R.id.textList);
		textList.setTypeface(face);
		
		title=(TextView)findViewById(R.id.textHistory);
		title.setTypeface(face);
		
		tryout = new StringBuilder();
		if(Game.model.recordList!=null){
		Log.d("DEBUG!!!!!!",Game.model.recordList);
		tryout.append(Game.model.recordList);
	    textList.setText(tryout);
		}
		else if(Game.model.recordList==null){
			textList.setText("No game has played yet!");
		}
	}
	@Override
	public void onDestroy(){
	   super.onDestroy();
	    if(tryout!=null){
		Game.model.recordList=tryout.toString();
	    }
	}
}

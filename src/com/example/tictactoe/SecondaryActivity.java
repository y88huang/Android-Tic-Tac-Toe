package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;
import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater.Filter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryActivity extends Activity implements Observer {
	//Night buttons.
	Button tic00;
	Button tic01;
	Button tic02;
	Button tic10;
	Button tic11;
	Button tic12;
	Button tic20;
	Button tic21;
	Button tic22;
	Button Record;
	Button Option;
	Button newGame;
	TextView gameStatusView;
	TextView vsSign;
	TextView playerOneName;
	TextView playerTwoName;
	Button[][] Buttons = new Button[3][3];
	String font3 = "Jellyka_Estrya_Handwriting.ttf";
	Typeface face;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEMO", "SecondaryActivity: on Creat");
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_secondary); 
		face=Typeface.createFromAsset(getAssets(), font3);
		registerControllers();
		registerViews();
		Intent intent = new Intent();
		intent.setClass(SecondaryActivity.this, optionsView.class);
		startActivity(intent);
		Game.model.setNewGame();
		Game.model.addObserver(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Log.d("DeBug","UPDATING!!!!!");
		 if(Game.model.isDoneSetting()){
			 StringBuilder whofirst = new StringBuilder(20);
			 if(Game.model.getTourModeOn()){
				   whofirst.append("Game ");
				   whofirst.append(Game.model.tourGameCounter);
				   whofirst.append(" ");
				   
			   }
			 if(Game.model.Ofirst){
			 whofirst.append(Game.model.getOname());
			 whofirst.append(" ");
			 whofirst.append("first!");
			 }
			 else if (Game.model.Xfirst){
				 whofirst.append(Game.model.getXname());
				 whofirst.append(" ");
				 whofirst.append("first!");
			 }
			 gameStatusView.setText(whofirst);
			 StringBuilder sbIsUsingO=new StringBuilder();
			 sbIsUsingO.append(Game.model.getOname());
			 sbIsUsingO.append(" with O");
			 StringBuilder sbIsUsingX=new StringBuilder();
			 sbIsUsingX.append(Game.model.getXname());
			 sbIsUsingX.append(" with X");
			 playerOneName.setText(sbIsUsingO);
			 playerTwoName.setText(sbIsUsingX);
		 }
		 if(Game.model.getTourModeOn()){
			 this.newGame.setText("Next Game");
			 this.newGame.setEnabled(false);
			 this.Record.setEnabled(true);
		 }
		 if(!Game.model.getTourModeOn()){
			 this.newGame.setText("New Game");
			 this.newGame.setEnabled(true);
			 this.Record.setEnabled(false);
		 }
		 if(Game.model.gameOn){
			 if(Game.model.illegalMove){
				 gameStatusView.setText("Illegal Move!");
			 }
			 else{
			 StringBuilder moveStep=new StringBuilder();
			 moveStep.append("Move ");
			 moveStep.append(Game.model.moveCounter);
			 gameStatusView.setText(moveStep);
			 }
		 }
		// TODO Auto-generated method stub
	        if(Game.model.shouldStartAnewGame){
	           System.out.format("REmoving Icon\n");
	           
	           for(int i=0;i<3;i++){
	               for (int j=0;j<3;j++){
	            	   Buttons[i][j].setEnabled(true);
	                   Buttons[i][j].setBackground(null);
	                   Game.model.setMatrix(i,j,0);
	               }
	           }
	        }
	        if (Game.model.Owon){
	        	if(Game.model.getTourModeOn()){
	    
		        	Game.model.oTourWinCounter++;
		        	Game.model.setRecord(Game.model.getOname());
		        	this.newGame.setEnabled(true);
		        	if ((Game.model.oTourWinCounter-Game.model.xTourWinCounter==3)||(Game.model.oTourWinCounter==3&&Game.model.xTourWinCounter==2)){
		        
			        		  
			                  
			        		  this.gameStatusView.setText("O has won the Tournment!!");
			                  this.newGame.setText("New Game");
			                  Game.model.setTourModeOn(false);
			        	}
		        	else {
		                  StringBuilder name = new StringBuilder();
		                  name.append("O has won Game ");
		                  name.append(Game.model.tourGameCounter);
		        	   this.gameStatusView.setText(name);
		        	}
		        	}  	
	        	else{
	        	this.gameStatusView.setText("O has won!");
	        	}
	            if(Game.model.GameWinInfo.columWin){
	                for (int j=0;j<3;j++){
	                    Buttons[j][Game.model.GameWinInfo.col].setBackgroundResource(R.drawable.image_of_o);
	                }
	            }
	            if (Game.model.GameWinInfo.rowWin){
	                for (int j=0;j<3;j++){
	                    Buttons[Game.model.GameWinInfo.row][j].setBackgroundResource(R.drawable.image_of_o);
	                }
	            }
	            if (Game.model.GameWinInfo.obWin){
	                if(Game.model.GameWinInfo.obc==0){
	                    Buttons[0][0].setBackgroundResource(R.drawable.image_of_o);
	                    Buttons[1][1].setBackgroundResource(R.drawable.image_of_o);
	                    Buttons[2][2].setBackgroundResource(R.drawable.image_of_o);
	                }
	                else if (Game.model.GameWinInfo.obc==1){
	                    Buttons[0][2].setBackgroundResource(R.drawable.image_of_o);
	                    Buttons[1][1].setBackgroundResource(R.drawable.image_of_o);
	                    Buttons[2][0].setBackgroundResource(R.drawable.image_of_o);
	                }
	            }
	        }
	        else if (Game.model.Xwon){
	        	this.gameStatusView.setText("X has won");
	        	if(Game.model.getTourModeOn()){
	        		 this.newGame.setEnabled(true);
		        	Game.model.xTourWinCounter++;
		        	Game.model.setRecord(Game.model.getXname());
		        	if ((Game.model.xTourWinCounter-Game.model.oTourWinCounter==3)||(Game.model.oTourWinCounter==2&&Game.model.xTourWinCounter==3)){
			        		  String playtername = Game.model.oTourWinCounter>Game.model.xTourWinCounter?Game.model.getOname():Game.model.getXname();
			                  this.gameStatusView.setText("X has won the Tournment!!");
			                  this.newGame.setText("New Game");
			                  Game.model.setTourModeOn(false);
//			         
			        	}
		        	else{
		        		StringBuilder name = new StringBuilder();
		                  name.append("X has won Game ");
		                  name.append(Game.model.tourGameCounter);
		        	   this.gameStatusView.setText(name);
		        	}
		        	}  	
	                if(Game.model.GameWinInfo.columWin){
	                    for (int j=0;j<3;j++){
	                        Buttons[j][Game.model.GameWinInfo.col].setBackgroundResource(R.drawable.image_of_x);	                        
	                    }
	                }
	                if (Game.model.GameWinInfo.rowWin){
	                    for (int j=0;j<3;j++){
	                        Buttons[Game.model.GameWinInfo.row][j].setBackgroundResource(R.drawable.image_of_x);
	                    }
	                }
	                if (Game.model.GameWinInfo.obWin){
	                    if(Game.model.GameWinInfo.obc==0){
	                        Buttons[0][0].setBackgroundResource(R.drawable.image_of_x);
	                        Buttons[1][1].setBackgroundResource(R.drawable.image_of_x);
	                        Buttons[2][2].setBackgroundResource(R.drawable.image_of_x);
	                    }
	                    else if (Game.model.GameWinInfo.obc==1){
	                        Buttons[0][2].setBackgroundResource(R.drawable.image_of_x);
	                        Buttons[1][1].setBackgroundResource(R.drawable.image_of_x);
	                        Buttons[2][0].setBackgroundResource(R.drawable.image_of_x);
	                    }
                }
	        }
	        else if (Game.model.moveCounter==9){
	        	if (Game.model.getTourModeOn()){
	        		gameStatusView.setText("Draw Game! Rematch!");
	        		this.newGame.setEnabled(true);
	        	    Game.model.tourGameCounter--;
	        	}
	        	else{
	        	gameStatusView.setText("This game is a draw!");
	        	}
	        }
	}
		
	
	
	public void registerViews(){
		gameStatusView=(TextView)findViewById(R.id.gameStatus);
   	    gameStatusView.setTypeface(face);
   	    vsSign=(TextView)findViewById(R.id.Vs);
   	    vsSign.setTypeface(face);
   	   playerOneName=(TextView)findViewById(R.id.nameOfPlayerOne);
   	   playerOneName.setTypeface(face);
   	   playerTwoName=(TextView)findViewById(R.id.nameOfPlayerTwo);
   	   playerTwoName.setTypeface(face);
   	   
	}
	
     public void registerControllers(){
    	 newGame=(Button)findViewById(R.id.newgamebutton);
    	 Record=(Button)findViewById(R.id.backbutton);
    	 Option=(Button)findViewById(R.id.optionbutton);
    	 tic00=(Button)findViewById(R.id.tictac00);
    	 tic01=(Button)findViewById(R.id.tictac01);
    	 tic02=(Button)findViewById(R.id.tictac02);
    	 tic10=(Button)findViewById(R.id.tictac10);
    	 tic11=(Button)findViewById(R.id.tictac11);
    	 tic12=(Button)findViewById(R.id.tictac12);
    	 tic20=(Button)findViewById(R.id.tictac20);
    	 tic21=(Button)findViewById(R.id.tictac21);
    	 tic22=(Button)findViewById(R.id.tictac22);
         newGame.setTypeface(face);
    	 Record.setTypeface(face);
    	 Option.setTypeface(face);
    	
    	 Option.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SecondaryActivity.this, optionsView.class);
				startActivity(intent);
			}
		});
    	 Record.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SecondaryActivity.this,gameRecordView.class);
				startActivity(intent);
			
			}
		});
    	 
    	newGame.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Game.model.getTourModeOn()){
					Game.model.tourGameCounter++;
					Game.model.Xfirst=!Game.model.Xfirst;
					Game.model.Ofirst=!Game.model.Ofirst;
				}
			    Game.model.setNewGame();
			}
		});
    	 tic00.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				if (Game.model.isGameOn()&&tic00.getBackground()==null){
                    if(Game.model.Oturn){
                        tic00.setBackgroundResource(R.drawable.button_selector_o);            
                        Game.model.currentMove(0, 0, 1);
                    }
                    else if(Game.model.Xturn){
                        tic00.setBackgroundResource(R.drawable.button_selector_x);
                       Game.model.currentMove(0, 0, 2);
                    }
                }
                else if(Game.model.isGameOn()){
                    Log.d("Debug","Making Illegal Move!");
                    Game.model.MakeillegalMove();
                }

            }    
         });
    	 
    	 tic01.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic01.getBackground()==null){
                     if(Game.model.Oturn){
                         tic01.setBackgroundResource(R.drawable.button_selector_o);
                 
                         Game.model.currentMove(0, 1, 1);
                     }
                     else{
                         tic01.setBackgroundResource(R.drawable.button_selector_x);
                         
                        Game.model.currentMove(0, 1, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic02.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic02.getBackground()==null){
                     if(Game.model.Oturn&&tic02.isEnabled()){
                         tic02.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(0, 2, 1);
                     }
                     else{
                         tic02.setBackgroundResource(R.drawable.button_selector_x);
                        Game.model.currentMove(0, 2, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic10.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic10.getBackground()==null){
                     if(Game.model.Oturn){
                         tic10.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(1, 0, 1);
                     }
                     else{
                         tic10.setBackgroundResource(R.drawable.button_selector_x);
                        Game.model.currentMove(1, 0, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic11.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic11.getBackground()==null){
                     if(Game.model.Oturn){
                         tic11.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(1, 1, 1);
                     }
                     else{
                         tic11.setBackgroundResource(R.drawable.button_selector_x);
                        Game.model.currentMove(1, 1, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     System.out.format("Cant believe this");
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic12.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic12.getBackground()==null){
                     if(Game.model.Oturn){
                         tic12.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(1, 2, 1);
                     }
                     else{
                         tic12.setBackgroundResource(R.drawable.button_selector_x);
                        Game.model.currentMove(1, 2, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic20.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic20.getBackground()==null){
                     if(Game.model.Oturn){
                         tic20.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(2, 0, 1);
                     }
                     else{
                         tic20.setBackgroundResource(R.drawable.button_selector_x);
                        Game.model.currentMove(2, 0, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic21.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				Log.d("Test","Shit I am hit!!");
 				if (Game.model.isGameOn()&&tic21.getBackground()==null){
                     if(Game.model.Oturn){
                    	 tic21.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(2, 1, 1);
                     }
                     else{
                    	 tic21.setBackgroundResource(R.drawable.button_selector_x);    
                        Game.model.currentMove(2, 1, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     System.out.format("Cant believe this");
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 tic22.setOnClickListener(new OnClickListener() {	
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				if (Game.model.isGameOn()&&tic22.getBackground()==null){
                     if(Game.model.Oturn){
                         tic22.setBackgroundResource(R.drawable.button_selector_o);
                         Game.model.currentMove(2, 2, 1);
                     }
                     else{
                         tic22.setBackgroundResource(R.drawable.button_selector_x);
                        Game.model.currentMove(2, 2, 2);
                     }
                 }
                 else if(Game.model.isGameOn()){
                     System.out.format("Cant believe this");
                     Game.model.MakeillegalMove();
                 }

             }    
          });
    	 
    	   Buttons[0][0]=tic00;
    	      Buttons[0][1]=tic01;
    	      Buttons[0][2]=tic02;
    	      Buttons[1][0]=tic10;
    	      Buttons[1][1]=tic11;
    	      Buttons[1][2]=tic12;
    	      Buttons[2][0]=tic20;
    	      Buttons[2][1]=tic21;
    	      Buttons[2][2]=tic22;
 		
     }
}

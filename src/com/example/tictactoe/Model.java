package com.example.tictactoe;

import java.util.Observer;
import java.util.Observable;
import android.util.Log;

public class Model extends Observable{
	//The basic size of the window
    public static final int windowWidth=600;
    public static final int windowHeight=600;
    public String recordList;
    public Boolean Xfirst=false;
    public Boolean Ofirst=true;
    public Boolean Xturn=false;
    public Boolean Oturn=false;
    public Boolean Xwon=false;
    public Boolean Owon=false;
    private Boolean tourModeOn=false;
    private Boolean doneSetting=false;
    private String nameOfO="Player O";
    private String nameOfX="Player X";
    public Boolean gameOn=true; //Deal with first move problem.
    public Boolean illegalMove=false;
    public Boolean tourGameWon=false;
    public int moveCounter=0;
    public int tourWinCounter=0;
    public int tourGameCounter=0;
    public int oTourWinCounter=0;
    public int xTourWinCounter=0;
    public final int maxTourGame=5;
    public Boolean shouldStartAnewGame=false;
    public winInfo GameWinInfo = new winInfo();
    public int[][] boardViewMatrix=new int[3][3];
 
    
   public Model(){
       this.initBoard();
   }
   public void cleanTour(){
	   this.moveCounter=0;
	   this.oTourWinCounter=0;
	   this.xTourWinCounter=0;
   }
   public void setTourModeOn(Boolean bool){
	   if(bool){
		   this.tourModeOn=true;
		   if(this.tourGameCounter==0){
		   this.tourGameCounter++;
		   }
	   }
	   else{
		   this.tourModeOn=false;
		   this.tourGameCounter=0;
	   }
   }
   public Boolean getTourModeOn(){
	   return this.tourModeOn;
   }
   public void setDoneSetting(Boolean bool){
	   this.doneSetting=bool;
	   Log.d("TAGse","done setting here!");
   }
   public Boolean isDoneSetting(){
	   return this.doneSetting;
   }
   public void setOname(String name){
	   if(name.length()>0){
	   this.nameOfO=name;
	   }
   }
   public void setXname(String name){
	   if(name.length()>0){
	   this.nameOfX=name;
	   }
   }
   public String getOname(){
	   return nameOfO;
   }
   public String getXname(){
	   return nameOfX;
   }
   
   class winInfo{
       int row;
       int col;
       int obc;
       int ox;
       Boolean rowWin =false;
       Boolean columWin=false;
       Boolean obWin=false;
       winInfo(){
           this.row=-1;
           this.col=-1;
           this.obc=-1;
           this.ox=-1;
       }
       public void reset(){
           this.row=-1;
           this.col=-1;
           this.obc=-1;
           this.ox=-1;
           this.rowWin=false;
           this.columWin=false;
           this.obWin=false;
       }
   }
   public void setWhoFirst(char who){
	   if (who=='o'){
		   this.Ofirst=true;
		   this.notifyObservers();
	   }
	   else if (who=='x'){
		   this.Xfirst=true;
		   this.notifyObservers();
	   }
	   
   }
   
   public void setRecord(String name){
	   StringBuilder tmp = new StringBuilder();
	   if(!(recordList==null)){
	   tmp.append(recordList);
	   }
	   tmp.append("Game ");
	   tmp.append(tourGameCounter);
	   tmp.append(" : ");
	   tmp.append(name);
	   tmp.append(" won!\n");
	   recordList=tmp.toString();
   }
   public void initBoard(){
       for (int i=0;i<3;i++){
           for (int j=0;j<3;j++){
               boardViewMatrix[i][j]=0;
           }
       }
   }

   public void setMatrix(int x,int y,int number){
       this.boardViewMatrix[x][y]=number;
   }
   
   public void MakeillegalMove(){
          this.illegalMove=true;
          Log.d("Debug","Igot here!!");
          setChanged();
          notifyObservers();
         this.illegalMove=false;
   }
   public int gameLogic(){
       //if X win return 2, O win return 1, dead return 0,-1 game still playing.
       for (int i=0;i<3;i++){
           if (boardViewMatrix[i][0]==boardViewMatrix[i][1]&&boardViewMatrix[i][1]==boardViewMatrix[i][2]){
               if (boardViewMatrix[i][0]>0){
                   this.GameWinInfo.row=i;
                   this.GameWinInfo.rowWin=true;
                   return boardViewMatrix[i][0];
               }
           }
       }
       for (int j=0;j<3;j++){
           if (boardViewMatrix[0][j]==boardViewMatrix[1][j]&&boardViewMatrix[1][j]==boardViewMatrix[2][j]){
               if (boardViewMatrix[0][j]>0){
                   this.GameWinInfo.col=j;
                   this.GameWinInfo.columWin=true;
                   return boardViewMatrix[0][j];
               }
           }
       }
       if(boardViewMatrix[0][0]==boardViewMatrix[1][1]&&boardViewMatrix[1][1]==boardViewMatrix[2][2]){
           if(boardViewMatrix[0][0]>0){
           this.GameWinInfo.obc=0;
           this.GameWinInfo.obWin=true;
           return boardViewMatrix[0][0];
           }
       }
       else if (boardViewMatrix[0][2]==boardViewMatrix[1][1]&&boardViewMatrix[1][1]==boardViewMatrix[2][0]){
           if(boardViewMatrix[0][2]>0){
           this.GameWinInfo.obc=1;
           this.GameWinInfo.obWin=true;
           return boardViewMatrix[0][2];
           }
       }
       return 0;
   }
   public void incrementMoveCounter(){
       moveCounter++;
   }

   public Boolean isGameOn(){
       return Oturn||Xturn;
   }
   public void setEndGame(){
       this.Oturn=false;
       this.Xturn=false;
   }
   public void setNewGame(){
       this.shouldStartAnewGame=true;
       this.setDoneSetting(true);
       if(!this.getTourModeOn()){
    	   this.cleanTour();
    	   this.recordList=null;
       }
       if(this.Ofirst==true){
    	   this.Oturn=true;
    	   this.Xturn=!this.Oturn;
       }
       if (this.Xfirst==true){
    	   this.Xturn=true;
    	   this.Oturn=!this.Xturn;
       }
       this.Owon=false;
       this.Xwon=false;
       this.gameOn=false;
       this.moveCounter=0;
       this.GameWinInfo.reset();
       setChanged();
       notifyObservers();
       this.shouldStartAnewGame=false;
       this.setDoneSetting(false);
   }

   public void switchTrun(){
       Oturn=!Oturn;
       Xturn=!Xturn;
   }
   
   public void currentMove(int a,int b, int c){
	   this.setMatrix(a, b, c);
	   this.incrementMoveCounter();
	   this.gameOn=true;
	   this.nextStep();
   }
   public void nextStep(){
       System.out.format("goingNextStep\n");
       System.out.format("line 1 %d %d %d\n",boardViewMatrix[0][0],boardViewMatrix[0][1],boardViewMatrix[0][2]);
       System.out.format("line 2 %d %d %d\n",boardViewMatrix[1][0],boardViewMatrix[1][1],boardViewMatrix[1][2]);
       System.out.format("line 3 %d %d %d\n",boardViewMatrix[2][0],boardViewMatrix[2][1],boardViewMatrix[2][2]);
       this.switchTrun();
       if(gameLogic()==1){
           this.Owon=true;
           setEndGame();
           System.out.format("O wins!, wins at %d\n",GameWinInfo.row);
       }
       if (gameLogic()==2){
           this.Xwon=true;
           setEndGame();
           System.out.format("X wins!");
       }
       if (this.moveCounter==9){
           setEndGame();
       }
       setChanged();
       notifyObservers();
   }
   
   @Override
   public void addObserver(Observer observer){
	   Log.d("DEMO","Model: Oberserver added");
	   super.addObserver(observer);
   }
   @Override
   public void notifyObservers(){
	   Log.d("DEMO","MOdel: Oberservers NOTIFIED!!!");
	   super.notifyObservers();
   }
}

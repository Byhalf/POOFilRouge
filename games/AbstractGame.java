package games;
import players.*;
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractGame{
  public GamePlayer player1;
  public GamePlayer player2;
  public GamePlayer currPlayer;

  public AbstractGame(GamePlayer player1, GamePlayer player2){
    this.player1 = player1;
    this.player2 = player2;
    this.currPlayer = player1;
  }
  
  public void doPlayerTurn(int nbr){
    this.makeMove(nbr);
    this.changeCurrPlayer();
  }
  
  public void changeCurrPlayer() {
	  if (this.currPlayer==this.player1){
	      this.currPlayer = this.player2;
	  }else{
		  this.currPlayer = this.player1;
	}
  }
  
  public void setCurrPlayer(GamePlayer player) {
	  this.currPlayer = player;
  }
  
  public GamePlayer getPlayer1() {
	  return this.player1;
  }
  
  public GamePlayer getPlayer2() {
	  return this.player2;
  }

  public GamePlayer getCurrPlayer(){
    return currPlayer;
  }
  
  public abstract AbstractGame getCopy();
  
  public abstract void makeMove(int nbr);
  
  public abstract List<Integer> getValidMoves();

//Pas Demandé par l'énoncé
  public abstract Boolean isPlayValid(int nbr);


  public abstract String situationToString();

  public abstract String moveToString(Integer move);

  public abstract Boolean isOver();

  public abstract GamePlayer getWinner();


}

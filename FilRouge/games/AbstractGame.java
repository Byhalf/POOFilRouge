package games;
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractGame{
  public GamePlayer player1;
  public GamePlayer player2;
  public GamePlayer currentPlayer;

  public AbstractGame(GamePlayer player1, GamePlayer player2){
    this.player1 = player1;
    this.player2 = player2;
    this.currentPlayer = player1;

  }
  public abstract void makeMove(int nbr);

  public void doPlayerTurn(int nbr){
    this.makeMove(nbr);
    if (this.currentPlayer==this.player1){
      this.currentPlayer = this.player2;
    }
    else{
      this.currentPlayer = this.player1;
    }
  }

  public GamePlayer currentPlayer(){
    return currentPlayer;
  }

  public abstract List<Integer> validMoves();

//Pas Demandé par l'énoncé
  public abstract Boolean isPlayValid(int nbr);


  public abstract String situationToString();

  public abstract String moveToString(Integer move);

  public abstract Boolean isOver();

  public abstract GamePlayer getWinner();


}

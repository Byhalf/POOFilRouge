package games;
import java.util.Scanner;

public class Orchestrator{
  AbstractGame game;


  public Orchestrator(AbstractGame game){
    this.game=game;
  }
  public void playGame(){
    Scanner scanner = new Scanner(System.in);
    AbstractGame game = this.game;
    while(game.isOver()!=true){
      System.out.println(game.situationToString());
      System.out.println("c'est à "+game.currentPlayer.toString());
      int nbr = game.currentPlayer.chooseMove(game);
      game.doPlayerTurn(nbr);
      System.out.println(game.moveToString(nbr));
    }
    if(game.getWinner()==null)
      System.out.println("match null");
    else
      System.out.println(game.getWinner().toString()+" à gagné");
    System.out.println(game.situationToString());

  }
}

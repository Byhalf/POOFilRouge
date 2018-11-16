package games;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Orchestrator{
  AbstractGame game;


  public Orchestrator(AbstractGame game){
    this.game=game;
  }
  public void playGame() throws IllegalArgumentException{
    Scanner scanner = new Scanner(System.in);
    AbstractGame game = this.game;
    while(!game.isOver()){
      System.out.println(game.situationToString());
      System.out.println("c'est à "+game.getCurrPlayer().toString());
      int nbr = game.getCurrPlayer().chooseMove(game);
      List<Integer> possibleMoves = game.getValidMoves();
      if(!possibleMoves.contains(nbr)){
        throw new IllegalArgumentException("le coup n'existe pas");
      }
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

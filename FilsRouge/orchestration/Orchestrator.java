package orchestration;
import games.AbstractGame;

import java.util.List;

/**
*<b>Orchestrator est une classe permettant d'orchestrer une partie quel que
*soit le jeu choisi</b>
*<p>
*<ul>
*<li>Orchestrator contient donc une instance de AbstractGame</li>
*</ul>
*</p>
*@see Main
*
*
*/
public class Orchestrator{
/**
*l'instance de jeu qui peut être n'importe lequel des jeux qui implémentent AbstractGame
*/
  private AbstractGame game;

/**
*Le constructeur de l'Orchestrator
*<p>On donne à l'Orchestrator le jeu choisi (Nim, TicTacToe, ...)</p>
*/
  public Orchestrator(AbstractGame game){
    this.game=game;
  }
  /**
  *Lance le jeu choisi
  *<p>
  *<li>il s'arrete au moment ou la fonction isOver commune à
  *tous les AbstractGame renvoie True </li>
  *<li>donne le gagnant ou match nul selon le cas</li>
  *</p>
  *@see AbstractGame
  *
  */
  public void playGame() throws IllegalArgumentException{
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

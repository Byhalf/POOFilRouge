package players;
import games.AbstractGame;

import java.util.Random;
import java.util.List;

/**
 * Classe représentant un joueur random
 * c'est à dire un ordinateur qui joue des coups aléatoires
 */
public class RandomPlayer implements GamePlayer{
  /**
   * utilisé pour génére un pseudo-aléatoire
   */
  private Random randomGenerator;

  /**
   * Constructeur de la classe Random
   * qui génére une instance de la classe Random
   */
  public RandomPlayer(){
    this.randomGenerator = new Random();
  }

  /**
   * Choisie un coup possible aléatoirement
   * @inheritDoc
   */
  public int chooseMove(AbstractGame game){
    List<Integer> validMoves = game.getValidMoves();
    int size = validMoves.size();
    return validMoves.get(this.randomGenerator.nextInt(size));
  }

  /**
   * Override toString
   * @return
   *        Retourne le type du joueur (ici random)
   *        + le hash code de l'instance de Random
   */
  public String toString(){
    return "RandomPlayer #"+this.randomGenerator.hashCode();
  }
}

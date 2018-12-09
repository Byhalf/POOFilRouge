package players;
import games.*;

/**
 * Interface de tout les types de joueurs
 */
public interface GamePlayer{

  /**
   * Demande aux joueurs de joué
   * @param game
   *        La position de jeu sur laquelle le joueur doit joué
   * @return
   *        Retourne le coup joué
   */
 public int chooseMove(AbstractGame game);


 }
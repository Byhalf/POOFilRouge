package games;
import players.GamePlayer;
import java.util.List;

/**
 * AbstractGame est une classe permettant de factoriser les différents jeux.
 * 
 */

public abstract class AbstractGame{
  private GamePlayer player1;
  private GamePlayer player2;
  private GamePlayer currPlayer;
/**
 * Le constructeur de Abstract games.
 * @param player1 une instance de GamePlayer
 * @param player2 une instance de GamePlayer 
 */
  public AbstractGame(GamePlayer player1, GamePlayer player2){
    this.player1 = player1;
    this.player2 = player2;
    this.currPlayer = player1;
  }
  /**
   * Effectue un tour de jeu; joue un coup et passe au joueur suivant.
   * @param nbr un entier correspondant à un coup possible.
   */
  public void doPlayerTurn(int nbr){
    this.makeMove(nbr);
    this.changeCurrPlayer();
  }
  /**
   * Change le joueur courrant
   */
  public void changeCurrPlayer() {
	  if (this.currPlayer==this.player1){
	      this.currPlayer = this.player2;
	  }else{
		  this.currPlayer = this.player1;
	}
  }
  /**
   * Remplace le joueur courrant par le joueur passé en paramêtre.
   * @param player instance de GamePlayer
   */
  public void setCurrPlayer(GamePlayer player) {
	  this.currPlayer = player;
  }
  /**
   * <b>Getter</b>
   */
  public GamePlayer getPlayer1() {
	  return this.player1;
  }
  /**
   * <b>Getter</b>
   */  
  public GamePlayer getPlayer2() {
	  return this.player2;
  }
  /**
   * <b>Getter</b>
   */
  public GamePlayer getCurrPlayer(){
    return currPlayer;
  }

  /**
   * Calcule en fonction de notation la valeur numérique de la position (utilisé par la classe MinmaxPlayer)
   * @return
   *        Retourne la valeur numérique de la position
   */
  public int evaluate(){
    if(this.getWinner() == null){
      return 0;
    }else if(this.getWinner() == this.getCurrPlayer()){
      return 1;
    }else{
      return -1;
    }
  }
  /**
   * Permet de faire une copie du jeu, utilisé par la classe MinMaxPlayer
   */
  public abstract AbstractGame getCopy();
  /**
   * Permet de jouer un coup
   * @param nbr entier qui correspond à un coup possible
   */
  public abstract void makeMove(int nbr);
  /**
   * Permet d'obtenir la liste des coups possibles
   * @return list d'entier
   */
  public abstract List<Integer> getValidMoves();
/**
 * Vérifie qu'un coup est valide
 * @param nbr entier correspondant à un coup
 * @return un booléen, vraie si le coup est possible sinon faux.
 */
  public abstract Boolean isPlayValid(int nbr);

/**
 * Génère un string qui illustre la situation du jeu
 * @return un String
 */
  public abstract String situationToString();
/**
 * Génère un string qui illustre un coup du jeu
 * @param move une instance d'Integer qui est un coup valide
 * @return un String
 */
  public abstract String moveToString(Integer move);
/** Permet de vérifier si la partie est fini
 * @return un booléens, vraie si la partie est fini faux sinon
*/
  public abstract Boolean isOver();
/**
 * Fournis le gagnant de la partie
 * @return une instance de GamePlayer
 */
  public abstract GamePlayer getWinner();
}

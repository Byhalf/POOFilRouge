package games;
import players.GamePlayer;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Une classe construisant un jeu de Nim, elle hérite de AbstractGame
 */
public class Nim extends AbstractGame{
  private int initialMatches;
  private int maxTake;
  private int nbrMatches;

/**
 * <b>Constructeur<b>
 * @param initialMatches est le nombre d'allumette disponible au départ.
 * @param maxTake est le nombre maximum d'allumette qu'un joueur peut ramasser lors de son tour
 * @param player1 Une instance de GamePlayer qui représente un joueur
 * @param player2 Une instance de GamePlayer qui représente un joeur
 */
  public Nim(int initialMatches, int maxTake, GamePlayer player1, 
		  GamePlayer player2){
    super(player1,player2);
    this.initialMatches = initialMatches;
    this.maxTake = maxTake;
    this.nbrMatches = initialMatches;
  }

  /**
   * @inheritDoc
   */
  @Override
  public AbstractGame getCopy() {
	  AbstractGame gameCopy = new Nim(this.nbrMatches, this.maxTake,
			  this.getPlayer1(), this.getPlayer2());
	  gameCopy.setCurrPlayer(this.getCurrPlayer());
	  
	  return gameCopy;
  }
/**
 * Getter
 * @return le nombre d'allumette initial.
 */
  public int getinitialMatches(){
    return this.initialMatches;
  }
  /**
   * @return le nombre d'allumette restante
   */
  private int getnbrMatches(){
    return this.nbrMatches;
  }
/**
 * Soustrait le nombre d'allumette joué aux nombres d'allumettes en
 */
  @Override
  public void makeMove(int nbr){
    this.nbrMatches -= nbr;

  }
/**
 * remplie la liste des coups valides en vérifiant si pour chaque coup possible, il reste
 * assez d'allumettes.
 */
  @Override
  public List<Integer> getValidMoves(){
    List<Integer> possibleMoves = new ArrayList<> ();
    for(int i=1;i<=this.maxTake;i++){
      if(this.nbrMatches>=i)
        possibleMoves.add(i);
    }
    return possibleMoves;
  }

  public int hashCode(){
    return Objects.hash(this.getCurrPlayer(),this.nbrMatches,this.maxTake,this.initialMatches);
  }

  public boolean equals(Object o){
    if(o == null || !(o instanceof Nim)){
      return false;
    } else{
      Nim otherNim = (Nim) o;
      return this.getCurrPlayer().equals(otherNim.getCurrPlayer()) && this.nbrMatches == otherNim.nbrMatches &&
              this.maxTake == otherNim.maxTake;
    }
  }


  /**
   * vérifie que le coup est valide c'est à dire inférieur au nombre maximum d'allumette que l'on peut retirer et 
   * supérieur à 0,et que l'on ne peut pas retirer plus d'allumette qu'il y a dans le tas.
   */
  @Override
  public Boolean isPlayValid(int nbr){
    if (nbr <= this.maxTake && nbr>0 && this.getnbrMatches()-nbr>=0){
      return true;
    }
    return false;
    
  }

  /**
   * Si il n'y a plus d'allumette la partie est finie.
   */
  @Override
  public Boolean isOver(){

    if (getnbrMatches()<=0){
      return true;
    }
    return false;
  }

  /**
   * le gagnant est le joeur courrant lorsque la partie est finie
   */
  @Override
  public GamePlayer getWinner(){
    if (this.isOver()){
      return this.getCurrPlayer();
    }
    return null;
  }

  /**
   * boucle sur le nombre d'allumette pour en affichant autant à l'écran
   */
  @Override
  public String situationToString(){
    int nbrMatches =  this.getnbrMatches();
    String situation = "Il y a "+ nbrMatches +" allumette(s)\n";
    for(int i=0;i<nbrMatches;i++)
      situation +="| ";
    return situation;
  }

  @Override
  public String moveToString(Integer move){
    return move + " allumette(s) retiré";
  }
}

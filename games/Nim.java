package games;
import players.*;
import java.util.List;
import java.util.ArrayList;

public class Nim extends AbstractGame{
  public int initialMatches;
  public int maxTake;
  private int nbrMatches;


  public Nim(int initialMatches, int maxTake, GamePlayer player1, 
		  GamePlayer player2){
    super(player1,player2);
    this.initialMatches = initialMatches;
    this.maxTake = maxTake;
    this.nbrMatches = initialMatches;
  }
  
  public AbstractGame getCopy() {
	  AbstractGame gameCopy = new Nim(this.nbrMatches, this.maxTake,
			  this.getPlayer1(), this.getPlayer2());
	  gameCopy.setCurrPlayer(this.getCurrPlayer());
	  
	  return gameCopy;
  }

  public int getinitialMatches(){
    return this.initialMatches;
  }
  
  public int getnbrMatches(){
    return this.nbrMatches;
  }

  @Override
  public void makeMove(int nbr){
    this.nbrMatches -= nbr;

  }

  @Override
  public List<Integer> getValidMoves(){
    List<Integer> possibleMoves = new ArrayList<> ();
    for(int i=1;i<=this.maxTake;i++){
      if(this.nbrMatches>=i)
        possibleMoves.add(i);
    }
    return possibleMoves;
  }

  @Override
  public Boolean isPlayValid(int nbr){
    if (nbr <= this.maxTake && nbr>0){
      return true;
    }
    return false;
    
  }

  @Override
  public Boolean isOver(){
    if (getnbrMatches()<=0){
      return true;
    }
    return false;
  }

  @Override
  public GamePlayer getWinner(){
    if (this.isOver()){
      return this.getCurrPlayer();
    }
    return null;
  }
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
    return move + "allumette(s) retiré";
  }
}

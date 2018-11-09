package games;

import java.util.List;
import java.util.ArrayList;

public class Nim extends AbstractGame{
  public int initialNbMatches;
  public int nbMax;
  private int nbMatches;


  public Nim(int initialNbMatches, int nbMax, GamePlayer player1, GamePlayer player2){
    super(player1,player2);
    this.initialNbMatches = initialNbMatches;
    this.nbMax = nbMax;
    this.nbMatches = initialNbMatches;
  }


  public int getInitialNbMatches(){
    return this.initialNbMatches;
  }
  
  public int getNbMatches(){
    return this.nbMatches;
  }

  @Override
  public void makeMove(int nb){
    this.nbMatches -= nb;

  }

  @Override
  public List<Integer> validMoves(){
    List<Integer> possibleMoves = new ArrayList<> ();
    for(int i=1;i<=this.nbMax;i++){
      if(this.nbMatches>=i)
        possibleMoves.add(i);
    }
    return possibleMoves;
  }

  @Override
  public Boolean isPlayValid(int nb){
    if (nb <= this.nbMax && nb>0){
      return true;
    }
    return false;
    
  }

  @Override
  public Boolean isOver(){
    if (getNbMatches()<=0){
      return true;
    }
    return false;
  }

  @Override
  public GamePlayer getWinner(){
    if (this.isOver()){
      return this.currentPlayer;
    }
    return null;
  }
  @Override
  public String situationToString(){
    int nbMatches =  this.getNbMatches();
    String situation = "Il y a "+ nbMatches +" allumette(s)\n";
    for(int i=0;i<nbMatches;i++)
      situation +="| ";
    return situation;
  }

  @Override
  public String moveToString(Integer move){
    return move + "allumette(s) retiré";
  }
}

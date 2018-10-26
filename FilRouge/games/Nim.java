package games;

import java.util.List;
import java.util.ArrayList;

public class Nim extends AbstractGame{
  public int initialNbMatches;
  public int nbrMax;


//faut il initialiser ici?
  private int nbMatches;


  public Nim(int initialNbMatches, int nbrMax, GamePlayer player1, GamePlayer player2){
    super(player1,player2);
    this.initialNbMatches = initialNbMatches;
    this.nbrMax = nbrMax;
    this.nbMatches = initialNbMatches;
  }


  public int getInitialNbMatches(){
    return this.initialNbMatches;
  }
  public int getNbMatches(){
    return this.nbMatches;
  }

  @Override
  public void makeMove(int nbr){
    this.nbMatches -= nbr;

  }

  @Override
  public List<Integer> validMoves(){
    List<Integer> possibleMoves = new ArrayList<> ();
    for(int i=1;i<=this.nbrMax;i++){
      if(this.nbMatches>=i)
        possibleMoves.add(i);
    }
    return possibleMoves;
  }

  @Override
  public Boolean isPlayValid(int nbr){
    if (nbr <= this.nbrMax && nbr>0 && nbr<=this.nbMatches){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  public Boolean isOver(){
    if (getNbMatches()<=0){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  public GamePlayer getWinner(){
    if (this.isOver()){
      return this.currentPlayer;
    }
    else{
      return null;
    }
  }
  @Override
  public String situationToString(){
    int nbrMatches =  this.getNbMatches();
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

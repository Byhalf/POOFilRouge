package games;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RandomPlayer implements GamePlayer{
  private String name;
  private Random randomGenerator = new Random();

  public int chooseMove(AbstractGame game){
    List<Integer> validMoves = game.validMoves();
    int size = validMoves.size();
    return validMoves.get(this.randomGenerator.nextInt(size));
  }

  public String toString(){
    return "RandomPlayer #"+this.randomGenerator.hashCode();
  }
}

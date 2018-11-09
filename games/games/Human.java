package games;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Human implements GamePlayer{
  String name;

  public Human(String name){
    this.name = name;
  }

  @Override
  public int chooseMove(AbstractGame game){
    Scanner scanner = new Scanner(System.in);

    String possibleMoves="Coup valide: ";
    for(Integer moves: game.validMoves()){
      possibleMoves += Integer.toString(moves) + " ,";
    }
    System.out.println(possibleMoves);
    int choix = scanner.nextInt();
    //isPlayValid ou itérer sur validMoves?
    while(game.isPlayValid(choix)!=true){
      System.out.println("coup non valide");
      System.out.println(possibleMoves);
      choix = scanner.nextInt();
    }
    return choix;
  }

  public String toString(){
    return this.name;
  }
}

package players;
import games.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Human implements GamePlayer{
  public String name;

  public Human(String name){
    this.name = name;
  }

  @Override
  public int chooseMove(AbstractGame game){
    Scanner scanner = new Scanner(System.in);
    int move;

    String possibleMoves="Coup valide: ";
    for(Integer moves: game.getValidMoves()){
      possibleMoves += Integer.toString(moves) + " ,";
    }
    System.out.println(possibleMoves);

    while(true){ //la boucle sera quitté lorsque une bonne valeur sera donner

        try {
            move = scanner.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Vous devez entre un nombre");
            move = -1;
            scanner.next();//permet de supprimé l'exception
        }

        if(game.isPlayValid(move)){
            return move;
        }else {
            System.out.println("coup non valide");
            System.out.println(possibleMoves);
        }

    }
  }

  public String toString(){
    return this.name;
  }
}
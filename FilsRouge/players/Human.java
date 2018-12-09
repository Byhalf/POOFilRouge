package players;
import games.AbstractGame;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe représentant un joueur humain qui demandera
 * donc une interraction avec un humain
 */
public class Human implements GamePlayer{
    /**
     * Le nom du joueur
     */
  public String name;

    /**
     * Constructeur de la classe
     * @param name
     *       Prend un String qui correspondra au nom du joueur
     */
  public Human(String name){
    this.name = name;
  }

    /**
     * Demande au joueur de choisir son coup
     * @inheritDoc
     */
  public int chooseMove(AbstractGame game){
    Scanner scanner = new Scanner(System.in);
    int move;

    String possibleMoves="Coup valide: ";
    for(Integer moves: game.getValidMoves()){
      possibleMoves += (moves) + " ,";
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

    /**
     * Override la fonction toString
     * @return
     *        Le nom du joueur
     */
  public String toString(){
    return this.name;
  }
}

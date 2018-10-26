/*package games;

import java.util.Scanner;

public class ExNim{
  public static void main (String [] args) {
    System.out.println("quels sont le nom des joueurs");
    Scanner scanner = new Scanner(System.in);
    Player player1 = new Player(scanner.next());
    Player player2 = new Player(scanner.next());
    System.out.println("quel est le nombre d'allumettes?");
    int initialNbMatches = scanner.nextInt();
    System.out.println("quel est le nombre max d'allumettes ramassés?");
    int nbrMax = scanner.nextInt();
    Nim theGame = new Nim(initialNbMatches,nbrMax,player1,player2);
    while(theGame.isGameFinished()==false){
      System.out.println("Il y a "+ theGame.nbMatches+" allumette(s)");
      System.out.println("C'est au tour de "+ theGame.currentPlayer.getName());
      System.out.println("Combien d'allumette prendre?");
      int nbr = scanner.nextInt();
      while(theGame.isPlayValid(nbr)!=true){
        System.out.println("Cette réponse n'est pas valide");
        nbr = scanner.nextInt();

      }
      theGame.substractMatches(nbr);

    }
    System.out.println("la partie est finie");
    System.out.println("le joueur "+ theGame.whoWon().getName() + "à gagné");








  }

}
*/

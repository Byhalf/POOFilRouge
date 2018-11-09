package games;
import java.util.Scanner;


public class Main{
  public static void main(String [] args){
    GamePlayer player1;
    GamePlayer player2;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Joueur 1, robot(0) ou humain(1)?");
    int choix = scanner.nextInt();
    if(choix==0)
      player1 = new RandomPlayer();
    else{
      System.out.println("quel est le nom du joueur");
      player1 = new Human(scanner.next());
    }

    System.out.println("Joueur 2, robot(0) ou humain(1)?");
    choix = scanner.nextInt();
    if(choix==0)
      player2 = new RandomPlayer();
    else{
      System.out.println("quel est le nom du joueur");
      player2 = new Human(scanner.next());
    }

    System.out.println("Jouer au nim 0 ou TicTacToe 1?");
    choix = scanner.nextInt();
    if(choix==0){
      System.out.println("quel est le nombre d'allumettes?");
      int initialNbMatches = scanner.nextInt();
      System.out.println("quel est le nombre max d'allumettes ramassés?");
      int nbrMax = scanner.nextInt();
      Nim game = new Nim(initialNbMatches,nbrMax,player1,player2);
      Orchestrator orch = new Orchestrator(game);
      orch.playGame();


    }
    else{
      TicTacToe game = new TicTacToe(player1,player2);
      Orchestrator orch = new Orchestrator(game);
      orch.playGame();


    }
  }
}

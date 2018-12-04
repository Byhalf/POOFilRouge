package games;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main{
  public static void main(String [] args) throws IllegalArgumentException{
    GamePlayer player1;
    GamePlayer player2;
    int choixJeu =0;
    int choixJoueur1=1;
    int choixJoueur2=0;
    Scanner scanner = new Scanner(System.in);
    AbstractGame game;

    try {
      if(args[0].equals("nim")){
        choixJeu=0;
      } else if(args[0].equals("morpion")){
        choixJeu=1;
      } else {
        System.out.println("Il faut donner nim ou morpion en premier argument");
        System.out.println("Nim a été sélectionné par défaut");
      }

      if(args[1].equals("robot")){
        choixJoueur1=0;
      } else if(args[1].equals("humain")){
        choixJoueur1=1;
      } else {
        System.out.println("Il faut donner humain ou robot en deuxième argument");
        System.out.println("on sélectionne humain par défaut");
      }

      if(args[2].equals("humain")){
        choixJoueur2=1;
      } else if(args[2].equals("robot")){
        choixJoueur2=0;
      } else {
        System.out.println("Il faut donner humain ou robot en troisième argument");
        System.out.println("on sélectionne robot par défaut");
      }
    }

    catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Il faut donner des arguments au main jeu/typeJ1/typeJ2");
      System.out.println("Par défaut jeu=nim J1=humain J2=robot");
    }

    if(choixJoueur1==0)
      player1 = new MinMax();
    else{
      System.out.println("quel est le nom du joueur 1?");
      player1 = new Human(scanner.next());
    }
    if(choixJoueur2==0)
      player2 = new MinMax();
    else{
    System.out.println("quel est le nom du joueur 2?");
    player2 = new Human(scanner.next());
    }
    if(choixJeu !=0 && choixJeu !=1){
      throw new IllegalArgumentException("erreur au niveau du choix de jeu");
    }
    if(choixJeu==0){
      System.out.println("quel est le nombre d'allumettes?");
      boolean test = true;
      int initialNbMatches = 10;
      do{
        try{
          test = true;
          initialNbMatches = scanner.nextInt();
        }
        catch(InputMismatchException e){
          test = false;
          System.out.println("il faut saisir un entier");
          scanner.next();
        }
      }while(!test);

      int nbrMax = 1;
      System.out.println("quel est le nombre max d'allumettes ramassés?");
      do{
        try{
          test = true;
          nbrMax = scanner.nextInt();
        }
        catch(InputMismatchException e){
          test = false;
          System.out.println("il faut saisir un entier");
          scanner.next();
        }
      }while(!test);

      game = new Nim(initialNbMatches,nbrMax,player1,player2);

    }
    else {
      game = new TicTacToe(player1,player2);
      Orchestrator orch = new Orchestrator(game);
      orch.playGame();


    }
    Orchestrator orch = new Orchestrator(game);
    orch.playGame();
  }
}

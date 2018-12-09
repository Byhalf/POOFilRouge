package orchestration;
import java.util.InputMismatchException;
import java.util.Scanner;
import players.*;
import games.*;

/**
*<b>La classe main</b>
*<p>
*Elle prend 3 arguments :
*<ul>
*<li>Le premier est le jeu souhaité (nim, morpion, puissance4)</li>
*<li>Les 2 suivants sont les joueurs, ils peuvent etre humain, random ou robot (MinMax)</li>
*</ul>
*</p>
*<p>
*En cas de mauvais arguments on sélectionne le jeu de nim avec un humain
*en joueur 1 et un robot en joueur 2
*</p>
*/
public class Main{
  public static void main(String [] args) throws IllegalArgumentException{
    if(args.length != 3){
      throw new IllegalArgumentException("Pas le bon nombre d'agument");
    }
    GamePlayer player1 = null;
    GamePlayer player2 = null;
    GamePlayer player;
    AbstractGame game;
    String choiceGame = null;
    Scanner scanner = new Scanner(System.in);

    Runtime runtime = Runtime.getRuntime();
    System.out.println(runtime.totalMemory() + " " + runtime.freeMemory() + " " + runtime.maxMemory());


    for(int i = 0;i < 3;i++){
      String arg = args[i];
      player = null;
      if(arg.equals("nim") || arg.equals("tictactoe") || arg.equals("puissance4")){
        choiceGame = arg;
      } else if(arg.equals("humain")){
        System.out.println("quel est le nom du joueur?"+i);
        player = new Human(scanner.next());
      } else if(arg.equals("random")){
        player = new RandomPlayer();
      } else if(arg.equals("robot")){
        System.out.println("Choisissez le nombre de seconde que le robot va réflechir");
        System.out.println("WARNING! Si le robot n'as pas eu le temps de trouver un coup dans le temps impartie," +
                "il prendra quelque seconde de plus pour en choisir un");
        int secondes = -1;
        while(secondes < 0) {
          try {
            secondes = scanner.nextInt();
          } catch (InputMismatchException e) {
            scanner.next();
          }
          if(secondes < 0){
            System.out.println("il faut saisir un entier positif");
          }
        }
        player = new MinMaxPlayer(secondes*1000);//car se sont des millis

      } else{
        throw new IllegalArgumentException("Argument non compris");
      }

      if(player != null){
        if(player1 == null){
          player1 = player;
        }else if(player2 == null){
          player2 = player;
        }else{
          throw new IllegalArgumentException("Trop de joueur donné vous devez donner au moins un jeu");
        }
      }
    }

    if(player1 == null || player2 == null){
      throw new IllegalArgumentException("Manque de joueurs");
    }

    if(choiceGame.equals("nim")){
      System.out.println("Choisissez le nombre d'allumette");
      int nbMatches = -1;
      while(nbMatches < 0) {
        try {
          nbMatches = scanner.nextInt();
        } catch (InputMismatchException e) {
          scanner.next();
        }
        if (nbMatches < 0) {
          System.out.println("Il faut un entier positif");
        }
      }

      System.out.println("Choisissez le nombre d'allumette pris maximun");
      int matchesTake = -1;
      while(matchesTake < 0) {
        try {
          matchesTake = scanner.nextInt();
        } catch (InputMismatchException e) {
          scanner.next();
        }
        if (matchesTake < 0) {
          System.out.println("Il faut un entier positif");
        }
      }

      game = new Nim(nbMatches,matchesTake,player1,player2);
    } else if(choiceGame.equals("morpion")){
      game = new TicTacToe(player1,player2);
    }else{
      game = new ConnectFour(player1,player2);
    }

    Orchestrator orch = new Orchestrator(game);
    orch.playGame();
    scanner.close();

  }
}

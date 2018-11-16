package games;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class MinMax implements GamePlayer{
    private String name;
    private Random randomGenerator = new Random();

    public int chooseMove(AbstractGame game){
        int max = -999;//-inf
        int meilleurCoup = 0;
        int val;

        int depth = 9; //@Todo modifié pour le passé en arg

        for(int coup: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(coup);

            val = -negamax(copyGame, depth-1);

            if(val > max){
                max = val;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }

    public int negamax(AbstractGame game,int depth){
        int max = -999;
        int val = max;

        game.changeCurrPlayer();
        if(game.isOver()){
            return evaluer(game);
        }

        if(depth == 0){
            return 0; //devra évalué le jeu si il est plus complexe
        }

        for(int coup: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(coup);

            val = Math.max(val,-negamax(copyGame, depth-1));
        }
        return val;
    }

    public int evaluer(AbstractGame game){
        if(game.getWinner() == null){
            return 0;
        }else if(game.getWinner() == game.getCurrPlayer()){
            return 1;
        }else{
            return -1;
        }
    }

    public String toString(){
        return "RandomPlayer #"+this.randomGenerator.hashCode();
    }
}

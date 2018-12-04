package players;
import games.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class MinMax implements GamePlayer{
    private Random randomGenerator = new Random();

    public int chooseMove(AbstractGame game){
        int max = -999;//-inf
        int meilleurCoup = 0;
        int val;

        for(int coup: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(coup);

            val = -negamax(copyGame);

            if(val > max){
                max = val;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }

    public int negamax(AbstractGame game){
        int max = -999;
        int val = max;

        game.changeCurrPlayer();
        if(game.isOver()){
            return evaluer(game);
        }

        for(int coup: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(coup);

            val = Math.max(val,-negamax(copyGame));
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
        return "RobotPlayer #"+this.randomGenerator.hashCode();
    }
}

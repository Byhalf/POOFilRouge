package players;
import games.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MinMaxPlayer implements GamePlayer{
    private Random randomGenerator = new Random();
    private Map position;

    public MinMaxPlayer(){
        this.position = new HashMap();
    }

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
        if(this.position.containsKey(game)){
            return (int) this.position.get(game);
        }
        int val = -999;

        game.changeCurrPlayer();
        if(game.isOver()){
            return evaluer(game);
        }

        for(int coup: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(coup);

            val = Math.max(val,-negamax(copyGame));
        }
        this.position.put(game,val);
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
        return "MinMaxPlayer #"+this.randomGenerator.hashCode();
    }
}

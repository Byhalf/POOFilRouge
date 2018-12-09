package players.Negamax;

import games.AbstractGame;

import java.util.HashMap;
import java.util.Map;

public class NegamaxCache implements Runnable{
    private AbstractGame game;
    private int bestMove;

    /**
     * position est une Map qui possède pour clé
     * une instance AbstractGame (c'est à dire une position de jeu)
     * et en valeur: la valeur calculé par l'algorithme MinMax
     * pour la position de jeu
     */
    private Map<AbstractGame, Integer> position;
    private boolean end;

    /**
     * Constructeur de la classe MinMaxPlayer
     * génére une Map basé sur HashMap
     */
    public NegamaxCache(){
        this.position = new HashMap<AbstractGame, Integer>();
    }

    public int getBestMove(){
        return this.bestMove;
    }

    public void setGame(AbstractGame game){
        this.game = game;
    }

    public void setEnd(Boolean end){
        this.end = end;
    }

    public void reset(){
        this.end = false;
        this.position.clear();
    }

    public void run(){
        int max = -999;//-inf
        int bestMove = 0;
        int val;


        for(int move: this.game.getValidMoves()){
            AbstractGame copyGame = this.game.getCopy();
            copyGame.makeMove(move);

            val = -negamax(copyGame);

            if(val > max){
                max = val;
                bestMove = move;
            }
        }
        this.bestMove = bestMove;

        if(this.end){
            this.bestMove = -1;
        }
    }

    public int negamax(AbstractGame game){
        if(this.position.containsKey(game)){
            return this.position.get(game);
        }

        Runtime runtime = Runtime.getRuntime();
        /*runtime.totalMemory() - runtime.freeMemory() = mémoire utilisé
        runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory() = espace libre total
        https://stackoverflow.com/a/18375641 */
        if(runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory()) < 1024*1024*10){//regarde si l'espace libre total est inférieur à 10 mb
            //System.out.println("mémoire pleine");
            this.end = true;//on sait que le résultat sera faussé
            return 0; //trop de récusive call avec les variables créé dedans
        }

        if(end){
            return 0;
        }

        int val = -999;

        game.changeCurrPlayer();
        if(game.isOver()){
            return evaluer(game);
        }

        for(int move: game.getValidMoves()){
            AbstractGame copyGame = null;
            copyGame = game.getCopy();

            copyGame.makeMove(move);


            val = Math.max(val, -negamax(copyGame));

        }
        this.position.put(game, val);

        return val;
    }

    /**
     * Calculer la valeur d'une position de jeu
     * selon un système de notation
     * @param game
     *            Prend une position de jeu
     * @return
     *        Retourne la valeur calculé
     */
    public int evaluer(AbstractGame game){
        if(game.getWinner() == null){
            return 0;
        }else if(game.getWinner() == game.getCurrPlayer()){
            return 1;
        }else{
            return -1;
        }
    }
}

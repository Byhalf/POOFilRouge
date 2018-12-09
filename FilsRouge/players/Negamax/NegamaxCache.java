package players.Negamax;

import games.AbstractGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Class représentent l'agorithme minmax qui utilise une cache pour sauvegarde les valeurs calculés
 */
public class NegamaxCache implements Runnable{
    /**
     * position de départ pour l'algorithme
     */
    private AbstractGame game;
    /**
     * meilleur coup en fonction de la position
     */
    private int bestMove;

    /**
     * position est une Map qui possède pour clé
     * le hashCode d'une instance AbstractGame (c'est à dire une position de jeu)
     * et en valeur: la valeur calculé par l'algorithme MinMax
     * pour la position de jeu
     */
    private Map<Integer , Integer> position;
    /**
     * peut être mis à vrai pour forcer l'arrêt des appeler récursive de negamax
     */
    private boolean end;

    /**
     * Constructeur de la classe MinMaxPlayer
     * génére une Map basé sur HashMap
     */
    public NegamaxCache(){
        this.position = new HashMap<Integer , Integer>();
    }

    /**
     * le meilleur coup pour la position donné
     */
    public int getBestMove(){
        return this.bestMove;
    }

    /**
     * met la position de jeu qui doit être calculé par l'algorithme
     */
    public void setGame(AbstractGame game){
        this.game = game;
    }

    /**
     * setter
     */
    public void setEnd(Boolean end){
        this.end = end;
    }

    /**
     * Par du principe que les appelers récusif seront terminé et vide la map
     */
    public void reset(){
        this.end = false;
        this.position.clear();
    }

    /**
     * overide la méthode run de Runnable
     * se lance dans un seconde thread
     * @see NegamaxDepth start()
     */
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

    /**
     * Algorithme negamax avec un cache qui permet de rétenir les positions déjà calculé
     * @param game
     *       position de jeu
     * @return
     *       retourne la valeur de ce coup
     */
    private int negamax(AbstractGame game){
        int hash = game.hashCode();
        if(this.position.containsKey(hash)){
            return this.position.get(hash);
        }

        Runtime runtime = Runtime.getRuntime();
        /*runtime.totalMemory() - runtime.freeMemory() = mémoire utilisé
        runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory() = espace libre total
        https://stackoverflow.com/a/18375641 */
        if(runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory()) < 1024*1024*1){//regarde si l'espace libre total est inférieur à 1 mb
            System.out.println("mémoire pleine");
            this.end = true;//on sait que le résultat sera faussé
            return 0; //trop de récusive call avec les variables créé dedans
        }

        if(end){
            return 0;
        }

        int val = -999;

        game.changeCurrPlayer();
        if(game.isOver()){
            return game.evaluate();//c'est le jeu qui calcul la valeur de sa position
        }

        for(int move: game.getValidMoves()){
            AbstractGame copyGame;
            copyGame = game.getCopy();

            copyGame.makeMove(move);


            val = Math.max(val, -negamax(copyGame));

        }
        this.position.put(hash, val);

        return val;
    }
}

package players;
import games.AbstractGame;
import players.Negamax.NegamaxCache;
import players.Negamax.NegamaxDepth;

/**
 * Cette classe représente un joueur utilisant un algorithme MinMax
 * c'est à dire un ordinateur qui utilise un algorithme MinMax
 * pour trouver le meilleur move à joué
 */
public class MinMaxPlayer implements GamePlayer{
    private NegamaxCache negamaxCache;
    
    private NegamaxDepth negamaxDepth;

    /**
     * temps (en millis) qui est donner au NegamaxCache pour calculé un coup
     */
    private int wait;

    /**
     * Constructeur de la class MinMaxPlayer
     * @param secondes
     *        correspond au temps qui est donner au NegamaxCache pour calculé un coup
     */
    public MinMaxPlayer(int secondes){
        this.wait = secondes;
        this.negamaxCache = new NegamaxCache();
        this.negamaxDepth = new NegamaxDepth();
    }
    /**
     * retourne la meilleur move possible
     * (selon la notation de l'algorithme)
     */
    public int chooseMove(AbstractGame game){
        negamaxCache.setGame(game);

        Thread thread = new Thread(negamaxCache);
        thread.start();
        /*lance le calcul de l'algorithme negamaxCache dans un second thread
        ce qui permet de le stopper au bout d'un certain temps
         */

        try {
            thread.join(wait);//attend que le thread soit terminé ou que le temps (en millis) se soit écoulé
        }catch(InterruptedException e){
            System.out.println("programme intérropu"+e);
        }
        if(thread.isAlive() || negamaxCache.getBestMove() == -1){//le thread n'as pas pu calculé de bon coup
            thread.interrupt();//interrompt le thread
            negamaxCache.setEnd(true);//ordonner aux appelers récursif de s'arrêter

            int bestMove = negamaxDepth.start(game,8);//lance l'algorithme minmax avec une profodneur max
            //8 étant un bon compromis entre temps de calcul et évite que le coup soit mauvais
            
            negamaxCache.reset();
            /*clear la map position puisqu'elle sera faussé
            fait d'abord l'autre algorithme pour être sûr que les recursive call de negamax du thread NegamaxnegamaxCache
            ne puissent plus changé position
             */
            return bestMove;
        }else {
            return negamaxCache.getBestMove();//va chercher dans negamaxCache le coup qu'il à réussi à calculer
        }

    }

    /**
     * @deprecated
     *          utilisé les negamax de NegamaxCache et NegamaxDepth à la place
     */
    @Deprecated
    public int negamax(AbstractGame game){
        int val = -999;

        game.changeCurrPlayer();
        if(game.isOver()){
            if(game.getWinner() == null){
                return 0;
            }else if(game.getWinner() == game.getCurrPlayer()){
                return 1;
            }else{
                return -1;
            }
        }

        for(int move: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(move);

            val = Math.max(val, -negamax(copyGame));
        }
        return val;
    }

    /**
     * override la fonction toString
     * @return
     *        Le type de joueur
     *        + le hash code de l'instance
     */
    public String toString(){
        return "MinMaxPlayer #"+this.hashCode();
    }
}

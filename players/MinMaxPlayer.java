package players;
import games.*;
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

    private int wait;

    
    public MinMaxPlayer(int secondes){
        this.wait = secondes;
        this.negamaxCache = new NegamaxCache();
        this.negamaxDepth = new NegamaxDepth();
    }
    /**
     * retourne la meilleur move possible
     * (selon la notation de l'algorithme)
     * @inheritDoc
     */
    public int chooseMove(AbstractGame game){
        negamaxCache.setGame(game);

        Thread thread = new Thread(negamaxCache);
        thread.start();

        try {
            thread.join(wait);
        }catch(InterruptedException e){
            System.out.println("programme intérropu"+e);
        }
        if(thread.isAlive() || negamaxCache.getBestMove() == -1){
            thread.interrupt();
            negamaxCache.setEnd(true);

            int bestMove = negamaxDepth.start(game,8);
            
            negamaxCache.reset();
            /*clear la map position puisqu'elle sera faussé
            fait d'abord l'autre algorithme pour être sûr que les recursive call de negamax du thread NegamaxnegamaxCache
            ne puissent plus changé position
             */
            return bestMove;
        }else {
            return negamaxCache.getBestMove();
        }

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

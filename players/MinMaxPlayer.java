package players;
import games.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Cette classe représente un joueur utilisant un algorithme MinMax
 * c'est à dire un ordinateur qui utilise un algorithme MinMax
 * pour trouver le meilleur coup à joué
 */
public class MinMaxPlayer implements GamePlayer{
    /**
     * position est une Map qui possède pour clé
     * une instance AbstractGame (c'est à dire une position de jeu)
     * et en valeur: la valeur calculé par l'algorithme MinMax
     * pour la position de jeu
     */
    private Map position;

    /**
     * Constructeur de la classe MinMaxPlayer
     * génére une Map basé sur HashMap
     */
    public MinMaxPlayer(){
        this.position = new HashMap();
    }

    /**
     * retourne la meilleur coup possible
     * (selon la notation de l'algorithme)
     * @inheritDoc
     */
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

    /**
     * Retourne la valeur de la position de jeu passé en argument
     * calculer par l'algorithme MinMax
     * @param game
     *            La position de jeu qui doit être évalué
     * @return
     *        l'évaluation de la position jeu
     */
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

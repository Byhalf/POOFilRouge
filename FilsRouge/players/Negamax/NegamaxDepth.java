package players.Negamax;

import games.AbstractGame;

/**
 * Class représentent l'algorithme minmax qui peut s'arrête à une certaine profondeur de l'arbre génére par l'algorithme
 */
public class NegamaxDepth {

    /**
     * Lancement de l'algorithme negamax
     * @param game
     *       La position de jeu à calculé
     * @param depth
     *       La profondeur de l'arbre parcourut maximun
     * @return
     *       Retourne le meilleur coup trouvé en conséquence
     */
    public int start(AbstractGame game,int depth){
        int max = -999;//-inf
        int bestMove = 0;
        int val;

        for (int move : game.getValidMoves()) {
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(move);

            val = -negamax(copyGame,depth-1);

            if (val > max) {
                max = val;
                bestMove = move;
            }
        }
        return bestMove;
    }


    /**
     * Retourne la valeur de la position de jeu passé en argument
     * calculer par l'algorithme MinMax
     * @param game
     *            La position de jeu qui doit être évalué
     * @param depth
     *        Arrête de faire des appeler récursive suivant si égale à 0
     * @return
     *        l'évaluation de la position jeu
     */
    public int negamax(AbstractGame game, int depth){
        int val = -999;

        game.changeCurrPlayer();
        if(game.isOver() || depth == 0){
            return game.evaluate();
        }

        for(int move: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(move);

            val = Math.max(val, -negamax(copyGame,depth-1));
        }
        return val;
    }
}

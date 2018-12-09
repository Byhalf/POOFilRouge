package players.Negamax;

import games.AbstractGame;

public class NegamaxDepth {

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
     * @return
     *        l'évaluation de la position jeu
     */
    public int negamax(AbstractGame game, int depth){
        int val = -999;

        game.changeCurrPlayer();
        if(game.isOver() || depth == 0){
            return evaluer(game);
        }

        for(int move: game.getValidMoves()){
            AbstractGame copyGame = game.getCopy();
            copyGame.makeMove(move);

            val = Math.max(val, -negamax(copyGame,depth-1));
        }
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

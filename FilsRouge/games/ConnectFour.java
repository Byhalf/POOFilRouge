package games;

import players.GamePlayer;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ConnectFour extends AbstractGame {
    /**
     * plateau du jeu
     */
    private GamePlayer grid[][];

    /**
     * Constructeur de ConnectFour, génére une grille de 7*6
     * @param player1
     *       Joueur 1
     * @param player2
     *       joueur 2
     */
    public ConnectFour(GamePlayer player1, GamePlayer player2) {
        super(player1, player2);
        this.grid = new GamePlayer[7][6];
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractGame getCopy() {
        ConnectFour gameCopy = new ConnectFour(this.getPlayer1(),
                this.getPlayer2());
        gameCopy.setCurrPlayer(this.getCurrPlayer());

        for(int i = 0;i < 7;i++) {
            for(int j = 0; j < 6;j++) {
                gameCopy.grid[i][j] = this.grid[i][j];
            }
        }

        return gameCopy;
    }

    /**
     * Sotck le joueur dans la première case libre de la colone sélectionne
     * @inheritDoc
     */
    @Override
    public void makeMove(int nbr) {
        for(int i = 0;i < 6;i++){
            if(this.grid[nbr-1][i] == null){
                this.grid[nbr-1][i] = this.getCurrPlayer();
                break;
            }
        }

    }

    /**
     * Retourne les colones jouables
     * @inheritDoc
     */
    @Override
    public List<Integer> getValidMoves() {
        List<Integer> possibleMoves = new ArrayList<>();

        for(int i = 1;i < 8;i++){
            if(this.isPlayValid(i)){
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Boolean isPlayValid(int nbr) {
        if(nbr < 8) {
            if (this.grid[nbr - 1][5] == null) { //colonne non remplie
                return true;
            }
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String situationToString() {
        String situation = "";

        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 7;j++){
                if(this.grid[j][5-i] == null) {
                    situation += "*";
                } else if(this.grid[j][5-i] == this.getPlayer1()){
                    situation += "O";
                } else {
                    situation += "X";
                }
                if(j < 6){
                    situation += "|";
                }
            }
            situation += System.lineSeparator();
        }
        return situation;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String moveToString(Integer move) {
        int height = 0;

        for(int i = 0;i < 6;i++){
            if(this.grid[move-1][i] == null){
                height = i;
                break;
            }
        }
        return "("+move+";"+height+")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public Boolean isOver() {
        if (this.getValidMoves().size() == 0)
            return true;
        else if (this.getWinner() != null)
            return true;
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public GamePlayer getWinner() {
        for(int i = 0;i < 7;i++){
            for(int j = 0;j < 6;j++){
                if(winTest(i,j,0,1,4)){
                    return this.grid[i][j];
                }else if(winTest(i,j,1,0,4)){
                    return this.grid[i][j];
                }else if(winTest(i,j,1,1,4)){
                    return this.grid[i][j];
                }else if(winTest(i,j,1,-1,4)){
                    return this.grid[i][j];
                }
            }
        }
        return null;
    }

    /**
     * test les grilles en fonction d'un vecteur et d'une certaine longueur
     * @param i
     *        Colone de la case de départ
     * @param j
     *        Ligne de la case de départ
     * @param x
     *        Vecteur de la colone
     * @param y
     *        Vecteur de la ligne
     * @param nbr
     *        Nombre de case à tester
     * @return
     *        Boolean vrai si sa donne un gagnant faux sinon
     */
    private Boolean winTest(int i,int j,int x,int y,int nbr){
        if(this.grid[i][j] != null){
            int column = i;
            int row = j;
            for(int k = 1;k < nbr;k++){
                column += x;
                row += y;
                if(column < 0 || column > 6 || row < 0 || row > 5){
                    return false;
                }
                if(this.grid[i][j] != this.grid[column][row]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    public boolean equals(Object o){
        if(o == null || !(o instanceof ConnectFour)){
            return false;
        } else{
            ConnectFour otherConnectFour = (ConnectFour) o;

            boolean gridEqual = true;
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    if (this.grid[i][j] != otherConnectFour.grid[i][j]) {
                        gridEqual = false;
                    }
                }
            }
            return this.getCurrPlayer().equals(otherConnectFour.getCurrPlayer()) && gridEqual;
        }
    }

    /**
     * @inheritDoc
     */
    public int hashCode(){
		return Objects.hash(this.getCurrPlayer(), Arrays.hashCode(this.grid));
	}

    /**
     * Ici le calcul ne sera pas exacte puisqu'il ne regarde pas si les cases sont bloqués ou pas
     * @inheritDoc
     */
    @Override
    public int evaluate() {
        int total = 0;

        for(int i = 0;i < 7;i++){
            for(int j = 0;j < 6;j++){
                for(int nbr = 0;nbr < 4;nbr++){
                    int point = 0;
                    if(winTest(i,j,0,1,nbr)){
                        point = nbr;
                    }else if(winTest(i,j,1,0,nbr)){
                        point = nbr;
                    }else if(winTest(i,j,1,1,nbr)){
                        point = 4;
                    }else if(winTest(i,j,1,-1,nbr)){
                        point = 4;
                    }
                    if(this.grid[i][j] == this.getCurrPlayer()){
                        total += point;
                    }else{
                        total -= point;
                    }
                }
            }
        }

        return super.evaluate()*100+total;
    }
}

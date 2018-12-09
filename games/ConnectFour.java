package games;

import players.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ConnectFour extends AbstractGame {
    public GamePlayer grid[][];

    public ConnectFour(GamePlayer player1, GamePlayer player2) {
        super(player1, player2);
        this.grid = new GamePlayer[7][6];
    }

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

    @Override
    public void makeMove(int nbr) {
        for(int i = 0;i < 6;i++){
            if(this.grid[nbr-1][i] == null){
                this.grid[nbr-1][i] = this.getCurrPlayer();
                break;
            }
        }

    }

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

    @Override
    public Boolean isPlayValid(int nbr) {
        if(nbr < 8) {
            if (this.grid[nbr - 1][5] == null) { //colonne non remplie
                return true;
            }
        }
        return false;
    }

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

    @Override
    public Boolean isOver() {
        if (this.getValidMoves().size() == 0)
            return true;
        else if (this.getWinner() != null)
            return true;
        return false;
    }

    @Override
    public GamePlayer getWinner() {
        GamePlayer winner = null;

        for(int i = 0;i < 7;i++){
            for(int j = 0;j < 6;j++){
                if(checkVertical(i,j) != null){
                    return this.grid[i][j];
                }else if(checkHorizontal(i,j) != null){
                    return this.grid[i][j];
                }else if(checkMountingDiagonal(i,j) != null){
                    return this.grid[i][j];
                }else if(checkDescendingDiagonal(i,j) != null){
                    return this.grid[i][j];
                }
            }
        }
        return null;
    }

    public GamePlayer checkVertical(int i,int j){
        if(i < 4) {
            if (this.grid[i][j] != null &&
                    this.grid[i][j] == this.grid[i + 1][j] &&
                    this.grid[i + 2][j] == this.grid[i + 3][j] &&
                    this.grid[i][j] == this.grid[i + 2][j]) {
                return this.grid[i][j];
            }
        }
        return null;
    }

    public GamePlayer checkHorizontal(int i,int j){
        if(j < 3) {
            if (this.grid[i][j] != null &&
                    this.grid[i][j] == this.grid[i][j + 1] &&
                    this.grid[i][j + 2] == this.grid[i][j + 3] &&
                    this.grid[i][j] == this.grid[i][j + 3]) {
                return this.grid[i][j];
            }
        }
        return null;
    }

    public GamePlayer checkMountingDiagonal(int i,int j){
        if(i < 4 && j < 3) {
            if (this.grid[i][j] != null &&
                    this.grid[i][j] == this.grid[i + 1][j + 1] &&
                    this.grid[i + 2][j + 2] == this.grid[i + 3][j + 3] &&
                    this.grid[i][j] == this.grid[i + 2][j + 2]) {
                return this.grid[i][j];
            }
        }
        return null;
    }

   public GamePlayer checkDescendingDiagonal(int i,int j){
        if(j > 2 && i < 4) {
            if (this.grid[i][j] != null &&
                    this.grid[i][j] == this.grid[i + 1][j - 1] &&
                    this.grid[i + 2][j - 2] == this.grid[i + 3][j - 3] &&
                    this.grid[i][j] == this.grid[i + 2][j - 2]) {
                return this.grid[i][j];
            }
        }
       return null;
 }

    public boolean equals(Object o){
        if(o == null || !(o instanceof ConnectFour)){
            return false;
        } else{
            ConnectFour otherConnectFour = (ConnectFour) o;

            Boolean gridEqual = true;
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

    public int hashCode(){
		return Objects.hash(this.getCurrPlayer(), Arrays.hashCode(this.grid));
	}
}

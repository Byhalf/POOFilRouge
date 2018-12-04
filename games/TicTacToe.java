package games;
import players.*;
import java.util.List;
import java.util.ArrayList;

public class TicTacToe extends AbstractGame {
	public GamePlayer grid[][];

	public TicTacToe(GamePlayer player1, GamePlayer player2) {
		super(player1, player2);
		this.grid = new GamePlayer[3][3];
	}
	
	public AbstractGame getCopy() {
		TicTacToe gameCopy = new TicTacToe(this.getPlayer1(), 
				this.getPlayer2());
		gameCopy.setCurrPlayer(this.getCurrPlayer());
				
		for(int i = 0;i < 3;i++) {
			for(int j = 0; j < 3;j++) {
				gameCopy.grid[i][j] = this.grid[i][j];
			}
		}
		
		return gameCopy;
		
	}
	
	private GamePlayer winTest(int caseJeu, int deltaRow, 
			int deltaColumn) {
		caseJeu--;
		int x1 = caseJeu % 3;
		int y1 = (caseJeu - x1) / 3;
		int i = 0;
		GamePlayer test = this.grid[x1][y1];

		while (i < 2) {
			x1 += deltaRow;
			y1 += deltaColumn;
			if (test == this.grid[x1][y1])
				i++;
			else
				return null;
		}
		return test;
	}

	@Override
	public GamePlayer getWinner() {

		// ADD : CORRECTION DES TESTS DE VICTOIRES
		for (int i = 0; i < 3; i++) {
			// horizontal
			if (this.winTest(1 + i * 3, 1, 0) != null)
				return this.winTest(1 + i * 3, 1, 0);
		}
		for (int j = 1; j < 4; j++) {
			// vertical
			if (this.winTest(j, 0, 1) != null)
				return this.winTest(j, 0, 1);
		}
		// Les 2 diagonales
		if (this.winTest(1, 1, 1) != null)
			return this.winTest(1, 1, 1);
		if (this.winTest(7, 1, -1) != null)
			return this.winTest(7, 1, -1);
		return null;
	}

	@Override
	public Boolean isPlayValid(int nbr) {

		nbr--;
		int x1 = nbr % 3;
		int y1 = (nbr - x1) / 3;

		if (this.grid[x1][y1] == null)
			return true;
		else
			return false;

	}

	@Override
	public List<Integer> getValidMoves() {
		List<Integer> possibleMoves = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			if (this.isPlayValid(i))
				possibleMoves.add(i);
		}
		return possibleMoves;
	}

	public String moveToString(Integer move) {
		move--;
		int i = move % 3;
		int j = (move - i) / 3;
		return "Case(" + Integer.toString(i) + ";" + Integer.toString(j) + ")";
	}

	@Override
	public String situationToString() {
		String situation = "";
		for (int j = 0; j < 3; j++) {
			situation += System.lineSeparator();

			for (int i = 0; i < 3; i++) {
				if (this.grid[i][j] == null) {
					situation += " * ";
				} else if (this.grid[i][j] == this.player1) {
					situation += " X ";
				} else if (this.grid[i][j] == this.player2) {
					situation += " O ";
				}
				if (i < 2)
					situation += "|";
			}

			if (j < 2) {
				situation += System.lineSeparator();
				situation += "............";
			}

		}
		return situation;
	}

	@Override
	public void makeMove(int nbr) {
		nbr--;
		int x1 = nbr % 3;
		int y1 = (nbr - x1) / 3;
		this.setGrid(x1, y1, this.getCurrPlayer());
	}

	@Override
	public Boolean isOver() {
		// ADD : La partie est aussi fini si il y a un gagnant, pas que quand il ne reste
		// plus de cases à jouer
		if (this.getValidMoves().size() == 0)
			return true;
		else if (this.getWinner() != null)
			return true;
		return false;
	}

	public void setGrid(int x, int y, GamePlayer player) {
		this.grid[x][y] = player;
	}

}

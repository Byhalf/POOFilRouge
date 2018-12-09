package games;
import players.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Une class permettant de créé des instances de jeu TicTacToe aussi connu sous 
 * le nom de morpion
 */
public class TicTacToe extends AbstractGame {
	/**
	 * Grille représententy le plateau de jeu
	 */
	private GamePlayer grid[][];

	/**
	 * Constructeur de TicTacToe, créé une grille de jeu et utilise abstract game pour attribuer les joeurs
	 * la grille de jeu sera remplie des instances des joeurs pour savoir qui a joué où.
	 * @param player1 le joueur1 instance de GamePlayer
	 * @param player2 le joueur2 instance de GamePlayer
	 */
	public TicTacToe(GamePlayer player1, GamePlayer player2) {
		super(player1, player2);
		this.grid = new GamePlayer[3][3];
	}

	/**
	 * @inheritDoc
	 */
	@Override
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
	/**
	 * Methode permettant si une partie est gagné en utilisant une case et un vecteur
	 * @param caseJeu La case à partir de laquel l'algorithme compare les valeurs des autres cases
	 * elle doit être la case central
	 * @param deltaRow la coordonné [0] du vecteur (la coordonnée de l'absice)
	 * @param deltaColumn la coordonné [1] du vecteur (la coordonnée de l'ordonnée)
	 * l'algorithme va vérifier que 2 la valeur des cases qui suivent en ligne, grace au vecteur, sont les même
	 * que celle de la valeur de la case jeu
	 * @return null si la parti n'est pas gagné, le joeur gagnant sinon
	 */
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

	public boolean equals(Object o) {
		if (o == null || !(o instanceof TicTacToe)) {
			return false;
		} else {
			TicTacToe otherTicTacToe = (TicTacToe) o;

			boolean gridEqual = true;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (this.grid[i][j] != otherTicTacToe.grid[i][j]) {
						gridEqual = false;
					}
				}
			}
			return this.getCurrPlayer().equals(otherTicTacToe.getCurrPlayer()) && gridEqual;
		}
	}

	public int hashCode(){
		return Objects.hash(this.getCurrPlayer(), Arrays.hashCode(this.grid));
	}


	/**
	 * Applique la méthode winTest sur les 3 case horizontal en haut et les 3 vertical sur le coté
	 * en donnant en paramêtre les vecteurs horizontaux, verticaux et enfin diagonaux.
	 * @return le joueur gagnant (instance de GamePlayer) ou si il n'y en a pas renvoie null.
	 */
	@Override
	public GamePlayer getWinner() {

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

	/**
	 * Le coup est valide que si la case est vide, c'est ce que vérifie cet algorithme
	 * @inheritDoc
	 */
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
	/**
	 * appel la fonction isPlayValid sur toute les cases et si un coup est possible sur cette case
	 * l'ajoute à la liste.
	 * @inheritDoc
	 */
	@Override
	public List<Integer> getValidMoves() {
		List<Integer> possibleMoves = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			if (this.isPlayValid(i))
				possibleMoves.add(i);
		}
		return possibleMoves;
	}

	/**
	 * @inheritDoc
	 */
	public String moveToString(Integer move) {
		move--;
		int i = move % 3;
		int j = (move - i) / 3;
		return "Case(" + i + ";" + j + ")";
	}
	/**
	 * itére sur la grille de jeu et remplace les joeurs et les case vide à l'intérieur du tableau par des
	 * symboles.
	 * @inheritDoc
	 */
	@Override
	public String situationToString() {
		String situation = "";
		for (int j = 0; j < 3; j++) {
			situation += System.lineSeparator();

			for (int i = 0; i < 3; i++) {
				if (this.grid[i][j] == null) {
					situation += " * ";
				} else if (this.grid[i][j] == this.getPlayer1()) {
					situation += " X ";
				} else if (this.grid[i][j] == this.getPlayer2()) {
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


	/**
	 * Remplie une case de la grille
	 * @inheritDoc
	 */
	@Override
	public void makeMove(int nbr) {
		nbr--;
		int x1 = nbr % 3;
		int y1 = (nbr - x1) / 3;
		this.setGrid(x1, y1, this.getCurrPlayer());
	}


	/**
	 * Vérifie si il y a un gagnant ou si la partie est finie.
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

	private void setGrid(int x, int y, GamePlayer player) {
		this.grid[x][y] = player;
	}
}

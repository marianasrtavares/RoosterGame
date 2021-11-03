import java.util.ArrayList;
import java.util.List;

public class Board {
	private String[][] board;
	private List<Position> positions = new ArrayList<>();

	public Board() {

	}

	public Board(String[][] board) {
		this.board = board;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	public void addPosition(Position Position) {
		positions.add(Position);
		board[Position.getLine()][Position.getRow()] = Position.getCard();
	}

	public List<Position> getPositions() {
		return positions;
	}

	public boolean isPossibleSolution(boolean[] possibleSolution) {
		for (int i = 0; i < possibleSolution.length; i++) {
			if (possibleSolution[i] == true)
				return true;
		}
		return false;
	}

	public boolean[] possibleSolution() {
		boolean[] possibleSolution = new boolean[8];
		// vertical solution
		int i = 0;
		int j = 0;
		while (i < board[0].length) {
			possibleSolution[j] = verticalRow(i);
			j++;
			i++;
		}
		while (i < board.length) {
			possibleSolution[j] = horizontalLine(i);
			j++;
			i++;
		}

		possibleSolution[j] = principalDiagonal();
		possibleSolution[j + 1] = secondDiagonal();

		return possibleSolution;

	}

	private boolean secondDiagonal() {
		int center = board.length / 2;
		String value = board[center][center];
		if (value != null) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if ((i == 0 && j == board[i].length - 1 && (board[i][j] == null || !board[i][j].equals(value)))
							|| (i == board.length - 1 && j == 0 && (board[i][j] == null || !board[i][j].equals(value))))
						return false;

				}
			}
			return true;
		}
		return false;
	}

	private boolean principalDiagonal() {
		int center = board.length / 2;
		String value = board[center][center];
		if (value != null) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (i == j && (board[i][j] == null || !board[i][j].equals(value)))
						return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param line { int } line number of the board that we want to verify
	 * @return true if there is a horizontal solution or false
	 */
	private boolean horizontalLine(int line) {
		String value = board[line][0];
		if (value != null) {
			for (int i = 0; i < board[0].length; i++) {
				if (board[line][i] == null || !board[line][i].equals(value))
					return false;
			}
			return true;
		}
		return false;
	}

	// horizontal solution

	public boolean verticalRow(int row) {
		String value = board[0][row];
		if (value != null) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][row] == null || !board[i][row].equals(value))
					return false;
			}
			return true;
		}
		return false;
	}

	public void printBoard() {
		System.out.print("0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.println(" ");
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null)
					System.out.print("-" + " ");
				else
					System.out.print(board[i][j] + " ");

			}
			System.out.print(i);
		}
	}

	public boolean gameisOver() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean pieceExiste(int line, int row) {
		for (Position Position : positions) {
			if (Position.getLine() == line && Position.getRow() == row && Position.getCard() != null)
				return true;
		}
		return false;
	}

	public boolean isOutBoard(int line, int row) {
		if(line >2 || line<0 || row >2 || row<0 && line !=-1)
			return true;
		return false;
	

}
	
	public void printResult (String result, Player player) {
	 if(result == "finished") {
		System.out.println("You won!" + player);
	} else if (result == "standby") {
		System.out.println("Game will continue");
	} else {
		System.out.println("Game is over, Player " + player + " give up");
	}

}
}

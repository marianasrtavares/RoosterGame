import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice03 {

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		// declaration players and board
		Board board = new Board(new String[3][3]);

		Player firstPlayer = new Player();
		firstPlayer.setNumberPlayer(Player.startPlayer());

		int currentPlayer = 1;

		// choose card to play

		System.out.println("Play player:" + firstPlayer);
		System.out.print("Choose X or O");
		String card = sc.nextLine();

		while (!card.equals("X") && !card.equals("O")) {
			System.out.println("Choose just X or O:");
			card = sc.nextLine();
		}

		firstPlayer.setCard(card);
		Player secondPlayer = new Player((firstPlayer.getNumberPlayer() == 1) ? 2 : 1,
				(firstPlayer.getCard().equals("X") ? "O" : "X"));
		System.out.println("\n If you wanna stop the game press -1" + "\n");

		// game
		String result = "finished";
		while (!board.isPossibleSolution(board.possibleSolution())) {
			System.out.println("Play " + Player.getPlayer(firstPlayer, secondPlayer, currentPlayer));
			System.out.print("Choose line and row: ");
			Move m = new Move();
			int line = sc.nextInt();

			// stop the game
			if (line == -1) {
				System.out.println("Do you wanna continue the game later? Y or N");
				sc.nextLine();
				int continueGame = sc.nextLine().charAt(0);
				result = continueGame == 'Y' ? "standby" : "desist";
				break;
			}

			int row = sc.nextInt();
			sc.nextLine();

			// if choose line and row outside of board or if choose wrong place to put the
			// piece

			while (board.isOutBoard(line, row) || board.pieceExiste(line, row)) {
				if (board.isOutBoard(line, row)) {
					System.out.println("Choose between 0 and 2");
				} else {
					System.out.println("Ocupied, choose again!");
				}
				System.out.print("Choose line and row: ");
				line = sc.nextInt();
				row = sc.nextInt();
				sc.nextLine();
			}

			// continue the game
			m = new Move(line, row, Player.getPlayer(firstPlayer, secondPlayer, currentPlayer).getCard());
			board.addMove(m);
			Player.getPlayer(firstPlayer, secondPlayer, currentPlayer).addMoves(m);
			currentPlayer++;
			board.printBoard();
			System.out.println();

			if (board.isPossibleSolution(board.possibleSolution()) == true || board.gameisOver()) {
				break;
			}
		}

		sc.close();
		System.out.println("Print moves of " + firstPlayer);
		firstPlayer.printMoves();
		System.out.println("Print moves of " + secondPlayer);
		secondPlayer.printMoves();

		// result
		board.printResult(result, Player.getPlayer(firstPlayer, secondPlayer, currentPlayer));

		// File write
		String path = "gameResults";
		try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
			br.write("The result of game number " + ": " + result + " : Player " + Player.getPlayer(firstPlayer, secondPlayer, currentPlayer) + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

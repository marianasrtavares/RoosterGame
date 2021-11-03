import java.util.ArrayList;
import java.util.List;

public class Player {
	private Integer numberPlayer;
	private String card;
	private List<Move> moves = new ArrayList<>();

	public Player() {

	}

	public Player(Integer numberPlayer, String card) {
		this.numberPlayer = numberPlayer;
		this.card = card;
	}

	public Integer getNumberPlayer() {
		return numberPlayer;
	}

	public void setNumberPlayer(Integer numberPlayer) {
		this.numberPlayer = numberPlayer;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "Player [numberPlayer=" + numberPlayer + ", card=" + card + "]";
	}

	public void addMoves(Move move) {
		moves.add(move);
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void printMoves() {
		for (Move move : moves) {
			System.out.println(move);
		}

	}

	public static Player getPlayer(Player firstPlayer, Player secondPlayer, int currentPalyer) {
		if (currentPalyer % 2 != 0) {
			return firstPlayer;
		}
		return secondPlayer;
	}

	public static int startPlayer() {
		return (int) (Math.random() * 2 + 1);
	}

	public Player adversarySet(Player firstPlayer) {
		this.setNumberPlayer((firstPlayer.getNumberPlayer() == 1) ? 2 : 1);
		this.setCard(firstPlayer.getCard().equals("X") ? "O" : "X");
		return this;
	}

}

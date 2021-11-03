
public class Position {	
	private Integer line;
	private Integer row;
	private String piece;
	
	
	public Position() {
		
	}
	
	
	public Position(Integer line, Integer row, String piece) {
		this.line = line;
		this.row = row;
		this.piece=piece;
	}
	
	
	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	
	
	public String getCard() {
		return piece;
	}
	


	@Override
	public String toString() {
		return "Moves [line=" + line + ", row=" + row + "]";
	}
	

	
	
	
	

}

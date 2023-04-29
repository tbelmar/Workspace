public class Chessboard {
	public  static  class  Field {
		private char row;
		private byte column;
		private Chesspiece piece = null;
		private boolean marked = false;
		
		public Field(char row, byte column) {
			this.row = row;
			this.column = column;
		}
		
		public void put(Chesspiece piece) {
			this.piece = piece;
			piece.row = row;
			piece.column = column;
		}
		
		public Chesspiece take() {
			Chesspiece prevPiece = this.piece;
			this.piece = null;
			return prevPiece;
		}
		
		public void mark()  {
			marked = true;
		}
		
		public void unmark() {
			marked = false;
		}
		
		public String toString() {
			String s = (marked)? "xx" : "--";
			return (piece == null)? s : piece.toString();
		}
	}
	
	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLUMNS = 8;
	
	public static final int FIRST_ROW = 'a';
	public static final int FIRST_COLUMN = 1;
	
	private Field[][] fields;
	
	public Chessboard() {
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		char row = 0;
		byte column = 0;
		for(int r = 0; r < NUMBER_OF_ROWS; r++) {
			row = (char)(FIRST_ROW + r);
			column = FIRST_COLUMN;
			for(int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				fields[r][c] = new Field(row, column);
				column++;
			}
		}
	}
	
	public String toString() {
		String s = "   ";
		for(int i = FIRST_COLUMN; i <= NUMBER_OF_COLUMNS; i++) {
			s += i + "  ";
		}
		s += "\n";
		for(int i = FIRST_ROW; i < NUMBER_OF_ROWS + FIRST_ROW; i++) {
			s += (char)i + "  ";
			for(int j = FIRST_COLUMN-1; j < NUMBER_OF_COLUMNS; j++) {
				s += fields[i - FIRST_ROW][j] + " ";
			}
			s += "\n";
		}
		
		return s;
	}
	
	public boolean isValidField(char row, byte column) {
		return (row-FIRST_ROW < NUMBER_OF_ROWS && row >= FIRST_ROW && column-FIRST_COLUMN < NUMBER_OF_COLUMNS && column >= FIRST_COLUMN);
	}
	
	public abstract class Chesspiece {
		private char color;
		// w - white, b - black
		
		private char name;
		// K - King, Q - Queen, R - Rook, B - Bishop, N - Knight, P - Pawn
		
		protected char row = 0;
		protected byte column = -1;
		
		protected Chesspiece(char color, char name) {
			this.color = color;
			this.name = name;
		}
		
		public String toString() {
			return "" + color + name;
		}
		
		public boolean isOnBoard() {
			return Chessboard.this.isValidField(row, column);
		}
		
		public void moveTo(char row, byte column) throws NotValidFieldException {
			if(!Chessboard.this.isValidField(row, column))
				throw new NotValidFieldException("bad field: " + row + column);
			
			this.row = row;
			this.column = column;
			
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			Chessboard.this.fields[r][c].put(this);
		}
		
		public void moveOut() {
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			Chessboard.this.fields[r][c].take();
		}
		
		public abstract void markReachableFields();
		
		public abstract void unmarkReachableFields();
	}
	
	public class Pawn extends Chesspiece {
		public Pawn(char color, char name) {
			super(color, name);
		}
		
		public void markReachableFields() {
			byte col = (byte)(column + 1);
			if(Chessboard.this.isValidField(row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark();
			}
		}
		
		public void unmarkReachableFields() {
			byte col = (byte)(column + 1);
			if(Chessboard.this.isValidField(row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark();
			}
		}
	}
	
	public class Rook extends Chesspiece {
		public Rook(char color, char name) {
			super(color, name);
		}
		
		public void markReachableFields() {
			for(int i = row; Chessboard.this.isValidField((char)i, column); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].mark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, column); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i++) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i--) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].mark();
			}
		}
		
		public void unmarkReachableFields() {
			for(int i = row; Chessboard.this.isValidField((char)i, column); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].unmark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, column); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i++) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i--) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
		}
	}
	
	public class Knight extends Chesspiece {
		public Knight(char color, char name) {
			super(color, name);
		}
		
		public void markReachableFields() {
			for(int i = 2 ;; i *= -1) {
				for(int j = 1 ;; j *= -1) {
					if(Chessboard.this.isValidField((char)(i + row), (byte)(j + column))) 
						Chessboard.this.fields[i+(row-FIRST_ROW)][j+(column-FIRST_COLUMN)].mark();
					if(Chessboard.this.isValidField((char)(j + row), (byte)(i + column))) 
						Chessboard.this.fields[j+(row-FIRST_ROW)][i+(column-FIRST_COLUMN)].mark();
					if(j == -1) break;
				}
				if(i == -2) break;
			}
		}
		
		public void unmarkReachableFields() {
			for(int i = 2 ;; i *= -1) {
				for(int j = 1 ;; j *= -1) {
					if(Chessboard.this.isValidField((char)(i + row), (byte)(j + column))) 
						Chessboard.this.fields[i+(row-FIRST_ROW)][j+(column-FIRST_COLUMN)].unmark();
					if(Chessboard.this.isValidField((char)(j + row), (byte)(i + column))) 
						Chessboard.this.fields[j+(row-FIRST_ROW)][i+(column-FIRST_COLUMN)].unmark();
					if(j == -1) break;
				}
				if(i == -1) break;
			}
		}
	}
	
	public class Bishop extends Chesspiece {
		public Bishop(char color, char name) {
			super(color, name);
		}
		
		public void markReachableFields() {
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].mark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i++) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i--) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].mark();
			}
		}
		
		public void unmarkReachableFields() {
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].unmark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i++) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i--) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
		}
	}
	
	public class Queen extends Chesspiece {
		public Queen(char color, char name) {
			super(color, name);
		}
		
		public void markReachableFields() {
			for(int i = row; Chessboard.this.isValidField((char)i, column); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].mark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, column); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i++) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i--) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].mark();
			}
			
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].mark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i++) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].mark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i--) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].mark();
			}
		}
		
		public void unmarkReachableFields() {
			for(int i = row; Chessboard.this.isValidField((char)i, column); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].unmark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, column); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column-FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i++) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField(row, (byte)i); i--) {
				Chessboard.this.fields[row-FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
			
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i++) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].unmark();
			}
			for(int i = row; Chessboard.this.isValidField((char)i, (byte)(column+(i-row))); i--) {
				Chessboard.this.fields[i-FIRST_ROW][column+(i-row) - FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i++) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
			for(int i = column; Chessboard.this.isValidField((char)(row-(i-column)), (byte)i); i--) {
				Chessboard.this.fields[row-(i-column) - FIRST_ROW][i-FIRST_COLUMN].unmark();
			}
		}
	}
	
	public class King extends Chesspiece {
		public King(char color, char name) {
			super(color, name);
		}
		
		public void markReachableFields() {
			for(int i = row-1; i <= row+1; i++) {
				for(int j = column-1; j <= column+1; j++) {
					if(Chessboard.this.isValidField((char)(i), (byte)(j))) Chessboard.this.fields[i-FIRST_ROW][j-FIRST_COLUMN].mark();
				}
			}
		}
		
		public void unmarkReachableFields() {
			for(int i = row-1; i <= row+1; i++) {
				for(int j = column-1; j <= column+1; j++) {
					if(Chessboard.this.isValidField((char)(i), (byte)(j))) Chessboard.this.fields[i-FIRST_ROW][j-FIRST_COLUMN].unmark();
				}
			}
		}
	}
}
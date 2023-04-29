import java.util.Random;

public class ReachableFieldsOnChessboard {
	public static void main(String[] args) throws NotValidFieldException {
		Chessboard chessBoard = new Chessboard();
		System.out.println (chessBoard + "\n");
		
		Chessboard.Chesspiece []   pieces = new  Chessboard.Chesspiece [6];
		pieces [0] = chessBoard.new  Pawn ('w', 'P');
		pieces [1] = chessBoard.new  Rook ('b', 'R');
		pieces [2] = chessBoard.new  Queen ('w', 'Q');
		pieces [3] = chessBoard.new  Bishop ('w', 'B');
		pieces [4] = chessBoard.new  King ('b', 'K');
		pieces [5] = chessBoard.new  Knight ('w', 'N');
		
		for(int i = 0; i < pieces.length; i++) {
			Random r = new Random();
			
			char row = (char)(r.nextInt(8)+Chessboard.FIRST_ROW);
			byte column = (byte)(r.nextInt(8)+Chessboard.FIRST_COLUMN);
			
			pieces[i].moveTo(row, column);
			pieces[i].markReachableFields();
			System.out.println(chessBoard + "\n");
			
			pieces[i].unmarkReachableFields();
			pieces[i].moveOut();
		}
	}
}

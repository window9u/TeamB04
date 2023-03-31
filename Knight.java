
public class Knight extends ChessPiece {
    // Rook을 초기화 한 방식으로 초기화
    int score;

    public Knight(boolean isWhite, ChessBoard board) {
        super(isWhite, board);
        // 점수 초기화
        int score = 3;
    }

    // Rook과 같은 방식으로 canMove를 구현
    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str = "";
        if (Math.abs(fromX - toX) != 1 && Math.abs(fromY - toY) != 2 && Math.abs(fromX - toX) != 2
                && Math.abs(fromY - toY) != 1) {
            str = "wrong";
        } else if (Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 2) {
            if (ChessBoard.board[toX][toY] == null) {
                str = "move";
            } else if (ChessBoard.board[toX][toY].isWhite != ChessBoard.board[fromX][fromY].isWhite) {
                str = "eat";
            } else {
                str = "wrong";
            }
        } else if (Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY) == 1) {
            if (ChessBoard.board[toX][toY] == null) {
                str = "move";
            } else if (ChessBoard.board[toX][toY].isWhite != ChessBoard.board[fromX][fromY].isWhite) {
                str = "eat";
            } else {
                str = "wrong";
            }
        } else {
            str = "wrong";
        }
        return str;
    }

    // toString을 구현
    @Override
    public String toString() {
        if (isWhite) {
            return "N";
        } else {
            return "n";
        }
    }
}

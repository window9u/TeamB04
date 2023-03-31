abstract class ChessPiece {
    boolean isWhite;
    int Score;
    boolean isDie;
    ChessBoard ChessBoard;
    public ChessPiece(boolean isWhite, ChessBoard board) {
        this.isWhite = isWhite;
        this.ChessBoard=board;
    }

    public boolean isWhite() {
        return isWhite;
    }
    //해당 위치로 이동할 수 있는지 true, false를 리턴
    public abstract String canMove(int fromX, int fromY, int toX, int toY);

}

abstract class ChessPiece {
    boolean isWhite;
    int Score;
    boolean isDie;
    ChessBoard ChessBoard;
    public ChessPiece(boolean isWhite, ChessBoard board) {
        this.isWhite = isWhite;
        this.ChessBoard=board;
    }

    public abstract String getFullname();

    public String isWhite() {
        if(isWhite)
            return "White";
        else
            return "Black";
    }
    //해당 위치로 이동할 수 있는지 true, false를 리턴
    public abstract String canMove(int fromX, int fromY, int toX, int toY);
    //체크가 가능한지 true, false를 리턴
    //pieceX, pieceY는 기물의 위치, kingX, kingY는 상대방 킹의 위치
    public abstract boolean canCheck(int pieceX, int pieceY, int kingX, int kingY);

}

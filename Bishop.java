//치수
public class Bishop extends ChessPiece{
    //Rook 과 같은 방식으로 구현
    int score;
    public Bishop(boolean isWhite, ChessBoard board) {
        super(isWhite,board);
        int score=3;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str="";
        //코드 다 짰음
        return str;
    }
    
    @Override
    public String toString() {
        if(isWhite) {
            return "B";
        }
        else {
            return "b";
        }
    }

}

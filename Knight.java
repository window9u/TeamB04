//주혁
public class Knight extends ChessPiece {
    // Rook과 같은 방법 생성자 생성 및 canMove() 메소드 오버라이딩

    public Knight(boolean isWhite, ChessBoard board) {
        super(isWhite, board);
        // 점수 초기화
        this.Score = 3;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str = " ";
        if (Math.abs(toX-fromX) + Math.abs(toY-fromY) != 3) {
            str = "invalid move"; //나이트의 이동거리를 절대값 3으로 고정
            return str;
        }
        else if (toX == fromX || toY == fromY) {
            str = "invalid move"; //한 방향으로 3칸을 못 움직이게 막음
            return str;
        }
        if (ChessBoard.board[toX][toY] == null) str = "move"; //칸에 아무것도 없으면 움직임
        else if (ChessBoard.board[toX][toY].isWhite == !this.isWhite) str = "eat"; //칸에 적의 말이 있으면 먹음
        else if (ChessBoard.board[toX][toY].isWhite == this.isWhite) str = "cannot move to the same color"; //칸에 본인의 말이 있으면 못 감

        return str;
    }

    @Override
    public boolean canCheck(int pieceX, int pieceY, int kingX, int kingY) {
        //인자로 받아온 pieceX, pieceY, kingX, kingY를 canMove에 대입 후 반환값을 비교
        String s=canMove(pieceX,pieceY,kingX,kingY);
        if(s=="eat"){
            return true;
        }
        return false;
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
    
    @Override
    public String getFullname(){
        return "Knight";
    }
  
}

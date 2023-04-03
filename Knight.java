//주혁 나이트 다 짰음
public class Knight extends ChessPiece {
    // Rook과 같은 방법 생성자 생성 및 canMove() 메소드 오버라이딩
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

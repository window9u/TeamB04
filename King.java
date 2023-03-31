
public class King extends ChessPiece{
    //Rook과 같은 방식으로 점수 초기화 및 canMove() 메소드 구현
    int score;
    public King(boolean isWhite, ChessBoard board) {
        super(isWhite,board);
        //점수 초기화
        int score=100;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str="";
        // fromX, fromY, toX, toY를 이용하여 움직일 수 있는지 판단
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, "move" 리턴
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 적인 경우, "eat" 리턴
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 아군인 경우, "wrong" 리턴
        // 움직일 수 없는 경우, "wrong" 리턴
        // 이동하려는 위치가 체스판을 벗어나는 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
        // 이동하려는 위치가 같은 위치인 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
        if((Math.abs(fromX-toX)==1&&fromY==toY)||(Math.abs(fromY-toY)==1&&fromX==toX)||(Math.abs(fromX-toX)==1&&Math.abs(fromY-toY)==1)) {
            if(ChessBoard.board[toX][toY]==null) {//이동 경로에 장애물이 없으면 이동
                str="move";
            }
            else if(ChessBoard.board[toX][toY].isWhite!=ChessBoard.board[fromX][fromY].isWhite) {//이동 경로에 장애물이 있고 적인 기물이 있으면 먹기
                str="eat";
            }
            else {//이동 경로에 장애물이 있고 아군 기물이 있으면 이동 불가
                str="wrong";
            }
        }
        else {//이동 경로에 장애물이 있고 아군 기물이 있으면 이동 불가
            str="wrong";
        }
        return str;
    }
    //toString() 메소드 구현
    @Override
    public String toString() {
        if(isWhite==true) {
            return "K";
        }
        else {
            return "k";
        }
    }

}

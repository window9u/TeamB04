public class Rook extends ChessPiece{
    int score;
    public Rook(boolean isWhite, ChessBoard board) {
        super(isWhite,board);
        //점수 초기화
        int score=5;
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
        if(fromX!=toX&&fromY!=toY) {
            str="wrong";
        }
        else if(fromX==toX&&fromY!=toY) {//세로로 이동
            if(fromY<toY) {//위로 이동
                for(int i=fromY+1;i<toY;i++) {//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[fromX][i]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            else {//아래로 이동
                for(int i=fromY-1;i>toY;i--) {//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[fromX][i]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            if(str!="wrong") {
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
        }
        else if(fromX!=toX&&fromY==toY) {//가로로 이동
            if(fromX<toX) {//오른쪽으로 이동
                for(int i=fromX+1;i<toX;i++) {//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[i][fromY]!=null) {//이동 경로에 장애물이 있으면 이동 불가
                        str="wrong";
                        break;
                    }
                }
            }
            else {
                for(int i=fromX-1;i>toX;i--) {
                    if(ChessBoard.board[i][fromY]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            if(str!="wrong") {//이동 경로에 장애물이 없으면 이동
                if(ChessBoard.board[toX][toY]==null) {
                    str="move";
                }
            }

        }
        return str;//이동 불가
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if(isWhite) {
            return "R";
        }
        else {
            return "r";
        }
    }
}

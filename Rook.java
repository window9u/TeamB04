public class Rook extends ChessPiece {
    boolean isFirstMove=true;//첫 움직임인지 확인
    //만약 이동했을 경우 isFirstMove를 false로 바꿔준다.

    public Rook(boolean isWhite, ChessBoard board) {
        super(isWhite, board);
        //점수 초기화
        this.Score = 5;

    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str = "";
        boolean flag = false;
        // fromX, fromY, toX, toY를 이용하여 움직일 수 있는지 판단
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, "move" 리턴
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 적인 경우, "eat" 리턴
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 아군인 경우, "wrong" 리턴
        // 움직일 수 없는 경우, "wrong" 리턴
        // 이동하려는 위치가 체스판을 벗어나는 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
        // 이동하려는 위치가 같은 위치인 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
        if(fromX == toX && fromY == toY){//자기 자신의 위치를 입력했을 때
            flag=true;
            str="invalid move";
        }else if(fromX == toX && fromY != toY){//세로로 이동
            if(fromY<toY){//위로 이동
                for(int i=fromY+1;i<toY;i++){//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[fromX][i]!=null){
                        flag=true;
                        str="there is a piece in the way";
                        break;
                    }
                }
            }else{//아래로 이동
                for(int i=fromY-1;i>toY;i--){//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[fromX][i]!=null){
                        flag=true;
                        str="there is a piece in the way";
                        break;
                    }
                }
            }
        }else if(fromX != toX && fromY == toY){//가로로 이동
            if(fromX<toX){//오른쪽으로 이동
                for(int i=fromX+1;i<toX;i++){//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[i][fromY]!=null){
                        flag=true;
                        str="there is a piece in the way";
                        flag=true;
                        break;
                    }
                }
            }else{//왼쪽으로 이동
                for(int i=fromX-1;i>toX;i--){//이동 경로에 장애물이 있는지 확인
                    if(ChessBoard.board[i][fromY]!=null){
                        flag=true;
                        str="there is a piece in the way";
                        flag=true;
                        break;
                    }
                }
            }
        }else{//대각선으로 이동
            flag=true;
            str="invalid move";
        }
        if(flag==false){//이동 경로에 장애물이 없을 때
            if(ChessBoard.board[toX][toY]==null){//이동하려는 위치에 기물이 없는 경우
                str="move";
            }else if(ChessBoard.board[toX][toY].isWhite!=this.isWhite){//이동하려는 위치에 기물이 있는 경우
                str="eat";
            }else if(ChessBoard.board[toX][toY].isWhite==this.isWhite){//이동하려는 위치에 기물이 있는 경우
                str="cannot move to the same color";
            }
        }
        return str;
    }

    @Override
    public boolean canCheck(int pieceX, int pieceY, int kingX, int kingY) {
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (isWhite) {
            return "R";
        } else {
            return "r";
        }
    }

    @Override
    public String getFullname() {
        return "Rook";
    }
}

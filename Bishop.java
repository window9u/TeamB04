
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
        if(fromX==toX&&fromY==toY) {
            str="wrong";
        }
        else if(Math.abs(fromX-toX)==Math.abs(fromY-toY)) {
            if(fromX<toX&&fromY<toY) {
                for(int i=1;i<Math.abs(fromX-toX);i++) {
                    if(ChessBoard.board[fromX+i][fromY+i]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            else if(fromX>toX&&fromY>toY) {
                for(int i=1;i<Math.abs(fromX-toX);i++) {
                    if(ChessBoard.board[fromX-i][fromY-i]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            else if(fromX>toX&&fromY<toY) {
                for(int i=1;i<Math.abs(fromX-toX);i++) {
                    if(ChessBoard.board[fromX-i][fromY+i]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            else if(fromX<toX&&fromY>toY) {
                for(int i=1;i<Math.abs(fromX-toX);i++) {
                    if(ChessBoard.board[fromX+i][fromY-i]!=null) {
                        str="wrong";
                        break;
                    }
                }
            }
            if(str!="wrong") {
                if(ChessBoard.board[toX][toY]==null) {
                    str="move";
                }
                else if(ChessBoard.board[toX][toY].isWhite!=ChessBoard.board[fromX][fromY].isWhite) {
                    str="eat";
                }
                else {
                    str="wrong";
                }
            }
        }
        else {
            str="wrong";
        }
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

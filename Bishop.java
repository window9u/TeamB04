//치수 
public class Bishop extends ChessPiece {
    // Rook 과 같은 방식으로 구현

    public Bishop(boolean isWhite, ChessBoard board) {
        super(isWhite, board);
        this.Score = 3;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str="";
        //이동 불가한 경우
            //제자리 이동인 경우
            if(fromX==toX&&fromY==toY){
                str="move to different place";
                return str;
            }
            //대각선 이동이 아닌 경우
            else if(Math.abs(toX-fromX)!=Math.abs(toY-fromY)){
                str="invalid move";
                return str;
            }
            else{ // 대각선 이동은 맞는 경우
                if(toX-fromX==toY-fromY){ //기울기가 1인 상태로 이동하는 경우
                    if(toX-fromX>0){ 
                        for(int i=1;i<Math.abs(toX-fromX);i++){ // 진행 경로 중 장애물에 막힌 경우
                            if(ChessBoard.board[fromX+i][fromY+i]!=null){
                                str="cant go through";
                                return str;
                            }
                        }
                        if(ChessBoard.board[toX][toY]==null){ //비어있으면
                            str="move";
                        }
                        else if(ChessBoard.board[toX][toY].isWhite!=ChessBoard.board[fromX][fromY].isWhite){ //상대편 말이 있는경우
                            str="eat";
                        }
                        else{// 내 말이 이미 위치한 경우
                            str="already placed";
                        }
                    }
                    else{ // 진행 경로 중 장애물에 막힌 경우
                        for(int i=1;i<Math.abs(toX-fromX);i++){
                            if(ChessBoard.board[fromX-i][fromY-i]!=null){
                                str="cant go through";
                                return str;
                            }
                        }
                        if(ChessBoard.board[toX][toY]==null){ //비어있으면
                            str="move";
                        }
                        else if(ChessBoard.board[toX][toY].isWhite!=ChessBoard.board[fromX][fromY].isWhite){ //상대편 말이 있는경우
                            str="eat";
                        }
                        else{// 내 말이 이미 위치한 경우
                            str="already placed";
                        }
                    }
                   
                }
                else{ //기울기가 -1인 상태로 이동하는 경우
                    if(toX-fromX>0){ // 진행 경로 중 장애물에 막힌 경우
                        for(int i=1;i<Math.abs(toX-fromX);i++){
                            if(ChessBoard.board[fromX+i][fromY-i]!=null){
                                str="cant go through";
                                return str;
                            }
                        }
                        if(ChessBoard.board[toX][toY]==null){ //비어있으면
                            str="move";
                        }
                        else if(ChessBoard.board[toX][toY].isWhite!=ChessBoard.board[fromX][fromY].isWhite){ //상대편 말이 있는경우
                            str="eat";
                        }
                        else{// 내 말이 이미 위치한 경우
                            str="already placed";
                        }
                    }
                    else{ // 진행 경로 중 장애물에 막힌 경우
                        for(int i=1;i<Math.abs(toX-fromX);i++){
                            if(ChessBoard.board[fromX-i][fromY+i]!=null){
                                str="cant go through";
                                return str;
                            }
                        }
                        if(ChessBoard.board[toX][toY]==null){ //비어있으면
                            str="move";
                        }
                        else if(ChessBoard.board[toX][toY].isWhite!=ChessBoard.board[fromX][fromY].isWhite){ //상대편 말이 있는경우
                            str="eat";
                        }
                        else{// 내 말이 이미 위치한 경우
                            str="already placed";
                        }
                    }
                }
                
            }

        
        return str;
    }

    @Override
    public String toString() {
        if (isWhite) {
            return "B";
        } else {
            return "b";
        }
    }

}

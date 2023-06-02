//재하
public class King extends ChessPiece{
    //Rook과 같은 방식으로 점수 초기화 및 canMove() 메소드 구현
    boolean isFirstMove=true;//첫 움직임인지 확인
    //만약 이동했을 경우 isFirstMove를 false로 바꿔준다.
    public King(boolean isWhite, ChessBoard board) {
        super(isWhite,board);
        //점수 초기화
        this.Score=100;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        // fromX, fromY, toX, toY를 이용하여 움직일 수 있는지 판단
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, "move" 리턴
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 적인 경우, "eat" 리턴
        // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 아군인 경우, "wrong" 리턴
        // 움직일 수 없는 경우, "wrong" 리턴
        // 이동하려는 위치가 체스판을 벗어나는 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
        // 이동하려는 위치가 같은 위치인 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
        String str = "";
        ChessPiece countpart = ChessBoard.board[toX][toY];
        if (fromY + 1 == toY && fromX == toX) {//위로 한칸 이동
            if (countpart == null){
                str = "move";
                isFirstMove=false;
                //기물이 없는 경우
            } else if (countpart.isWhite == this.isWhite) {
                //같은 색의 기물인 경우
                str = "cannot move to the same color";
            } else {
                //상대방이 있는 경우
                isFirstMove=false;
                str = "eat";
            }
        } else if (fromY - 1 == toY && fromX == toX) {//아래로 한칸 이동
            if (countpart == null){//기물이 없는 경우
                str = "move";
                isFirstMove=false;
            }
            else if (countpart.isWhite == this.isWhite) {
                //같은 색의 기물인 경우
                str = "cannot move to the same color";
            } else {
                //상대방이 있는 경우
                isFirstMove=false;
                str = "eat";
            }
        } else if (fromX - 1 == toX && fromY == toY) {//좌로 한칸 이동
            if (countpart == null){
                //기물이 없는 경우
                str = "move";
                isFirstMove=false;
            }
            else if (countpart.isWhite == this.isWhite) {
                //같은 색의 기물인 경우
                str = "cannot move to the same color";
            } else {
                //상대방이 있는 경우
                isFirstMove=false;
                str = "eat";
            }
        } else if (fromX + 1 == toX && fromY == toY) {//우로 한칸 이동
            if (countpart == null){
                str = "move";
                isFirstMove=false;
                //기물이 없는 경우
            }
            else if (countpart.isWhite == this.isWhite) {
                //같은 색의 기물인 경우
                str = "You can't move to the same color";
            } else {
                //상대방이 있는 경우
                isFirstMove=false;
                str = "eat";
            }
        } else if ((fromX + 1 == toX || fromX - 1 == toX) && (fromY + 1 == toY || fromY - 1 == toY)) {//대각선 이동
            if (countpart == null){
                str = "move";
                isFirstMove=false;
                //기물이 없는 경우
            }
            else if (countpart.isWhite == this.isWhite) {
                //같은 색의 기물인 경우
                str = "cannot move to the same color";
            } else {
                //상대방이 있는 경우
                isFirstMove=false;
                str = "eat";
            }
        }else{
            //예외처리(2칸이동 or 이상한 좌표로 이동)
            str = "invalid move";
        }
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

    @Override
    public String getFullname(){
        return "King";
    }

}

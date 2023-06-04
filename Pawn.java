public class Pawn extends ChessPiece{

    //폰이 첫번째 움직임에는 두칸 갈 수 있다. fristMove를 통하여 판별
    boolean firstMove=true;
    public Pawn(boolean isWhite, ChessBoard board) {
        super(isWhite,board);
        this.Score=1;
        this.isDie=false;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        // Implement the logic for moving a pawn.
        // 해당 말이 해당 위치로 움직 일 수 있는지 계산
        //toX,toY는 전처리 된 보드 내의 좌표.
        String str="";
        ChessPiece countpart= ChessBoard.board[toX][toY];
        if(isWhite){
            //백인 경우
            //전진하는 경우
            if(fromY+1==toY&&toX==fromX){//한칸 이동
                if(countpart==null){
                    //상대방이 없는 경우
                    firstMove=false;
                    str= "move";
                }else if(countpart.isWhite==this.isWhite){
                    //같은 색의 기물이 있는 경우
                    str= "cannot move to the same color";
                }else{
                    //상대방이 있는 경우
                    str= "there is a piece in the way";
                }
            }else if(fromY+2==toY&&toX==fromX){//첫 이동(두칸 이동)
                if(firstMove){//첫번째 이동인 경우
                    if(ChessBoard.board[fromX][fromY+1]==null){//이동경로에 장애물 체크
                        if(countpart==null){//정상
                            //상대방이 없는 경우
                            firstMove=false;
                            str= "move";
                            ChessBoard.apX = toX;
                            ChessBoard.apY = toY;
                            ChessBoard.game.apFlag = 1;
                        }else {//같은편 말이 있는 경우
                            str= "there is a piece in the way";
                        }
                    }else {
                        str= "there is a piece in the way";
                    }
                }else {//첫번째 이동이 아니다.(입력오류)
                    str= "It's not first move";
                }
            }else if(fromY+1==toY&&(toX-1==fromX||toX+1==fromX)){//대각선 이동의 경우
                if(countpart==null){
                    //상대방이 없는 경우
                    str= "invalid move";

                    //앙파상
                    if (canAP(toX, toY)) { str = "move"; }


                }else if(countpart.isWhite!=this.isWhite){
                    //상대방이 있는 경우
                    firstMove=false;
                    str= "eat";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "cannot move to the same color";
                }
                //잘못된 이동
            }else {//전진도 대각선 이동도 아닌, 잘못된 이동
                str= "invalid move";
            }
        }else {
            //흑의 경우
            if(fromY-1==toY&&toX==fromX){//한칸 이동
                if(countpart==null){
                    //상대방이 없는 경우
                    firstMove=false;
                    str= "move";
                }else if(countpart.isWhite!=this.isWhite){
                    //상대방이 있는 경우
                    str= "there is a piece in the way";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "cannot move to the same color";
                }
            }else if(fromY-2==toY&&toX==fromX){//첫 이동(두칸 이동)
                if(firstMove){//첫번째 이동인 경우
                    if(ChessBoard.board[fromX][fromY-1]==null){//이동경로에 장애물 체크
                        if(countpart==null){//정상
                            //상대방이 없는 경우
                            firstMove=false;
                            str= "move";
                            ChessBoard.apX = toX; //앙파상 좌표 설정
                            ChessBoard.apY = toY;
                            ChessBoard.game.apFlag = 1;
                        }else {//같은편 말이 있는 경우
                            str= "there is a piece in the way";
                        }
                    }else {
                        str= "there is a piece in the way";
                    }
                }else {//첫번째 이동이 아니다.(입력오류)
                    str= "It's not first move";
                }
            }else if(fromY-1==toY&&(toX-1==fromX||toX+1==fromX)){//대각선 이동의 경우
                if(countpart==null){
                    //상대방이 없는 경우
                    str= "invalid move";

                    //앙파상
                    if (canAP(toX, toY)) { str = "move"; }

                }else if(countpart.isWhite!=this.isWhite){
                    //상대방이 있는 경우
                    firstMove=false;
                    str= "eat";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "cannot move to the same color";
                }
                //잘못된 이동
            }else {//잘못된 이동
                str= "invalid move";
            }
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

    @Override
    public String toString() {
        // Return the string representation of a pawn.
        // 해당 말의 문자열을 반환
        if(isWhite){
            return "P";
        }else {
            return "p";
        }
    }

    public boolean canAP(int toX, int toY) {
        if (ChessBoard.game.apFlag > -1){
            if (ChessBoard.apY == 4) {//흑 앙파상(흑 점수 +1)
                if ((toX == ChessBoard.apX) && (toY == ChessBoard.apY - 1)) {
                    ChessBoard.board[ChessBoard.apX][ChessBoard.apY] = null;
                    ChessBoard.game.BlackScore++;
                    return true;
                }
            }
            if (ChessBoard.apY == 5) {//백 앙파상(백 점수 +1)
                if ((toX == ChessBoard.apX) && (toY == ChessBoard.apY + 1)) {
                    ChessBoard.board[ChessBoard.apX][ChessBoard.apY] = null;
                    ChessBoard.game.WhiteScore++;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getFullname(){
        return "Pawn";
    }
}

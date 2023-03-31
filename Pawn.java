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
                    //상대방이 있는 경우
                    str= "wrong";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "wrong";
                }
            }else if(fromY+2==toY&&toX==fromX){//첫 이동(두칸 이동)
                if(firstMove){//첫번째 이동인 경우
                    if(ChessBoard.board[toX][toY+1]==null){//이동경로에 장애물 체크
                        if(countpart==null){//정상
                            //상대방이 없는 경우
                            firstMove=false;
                            str= "move";
                        }else if(countpart.isWhite==this.isWhite){//정상
                            //상대방이 있는 경우
                            str= "wrong";
                        }else {//같은편 말이 있는 경우
                            str= "wrong";
                        }
                    }else {
                        str= "wrong";
                    }
                }else {//첫번째 이동이 아니다.(입력오류)
                    str= "wrong";
                }
            }else if(fromY+1==toY&&(toX-1==fromX||toX+1==fromX)){//대각선 이동의 경우
                if(countpart==null){
                    //상대방이 없는 경우
                    str= "wrong";
                }else if(countpart.isWhite==this.isWhite){
                    //상대방이 있는 경우
                    firstMove=false;
                    str= "eat";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "wrong";
                }
                //잘못된 이동
            }else {//전진도 대각선 이동도 아닌, 잘못된 이동
                str= "wrong";
            }
        }else {
            //흑의 경우
            if(fromY-1==toY&&toX==fromX){//한칸 이동
                if(countpart==null){
                    //상대방이 없는 경우
                    firstMove=false;
                    str= "move";
                }else if(countpart.isWhite==this.isWhite){
                    //상대방이 있는 경우
                    str= "wrong";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "wrong";
                }
            }else if(fromY-2==toY&&toX==fromX){//첫 이동(두칸 이동)
                if(firstMove){//첫번째 이동인 경우
                    if(ChessBoard.board[toX][toY+1]==null){//이동경로에 장애물 체크
                        if(countpart==null){//정상
                            //상대방이 없는 경우
                            firstMove=false;
                            str= "move";
                        }else if(countpart.isWhite==this.isWhite){//비정상
                            //상대방이 있는 경우
                            str= "wrong";
                        }else {//같은편 말이 있는 경우
                            str= "wrong";
                        }
                    }
                }else {//첫번째 이동이 아니다.(입력오류)
                    str= "wrong";
                }
            }else if(fromY-1==toY&&(toX-1==fromX||toX+1==fromX)){//대각선 이동의 경우
                if(countpart==null){
                    //상대방이 없는 경우
                    str= "wrong";
                }else if(countpart.isWhite==this.isWhite){
                    //상대방이 있는 경우
                    firstMove=false;
                    str= "eat";
                }else{
                    //같은 색의 기물이 있는 경우
                    str= "wrong";
                }
                //잘못된 이동
            }else {//잘못된 이동
                str= "wrong";
            }
        }
        return str;
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
}

//재하
public class King extends ChessPiece{
    //Rook과 같은 방식으로 점수 초기화 및 canMove() 메소드 구현
    int score;
    public King(boolean isWhite, ChessBoard board) {
        super(isWhite,board);
        //점수 초기화
        int score=100;
    }
//ㄴㅐ가 해야될 기물 Kingㅇㅇ
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

//경식
public class Queen extends ChessPiece {

    //Rook과 같은 방식으로 score를 초기화하고, canMove()를 구현하세요.

    public Queen(boolean isWhite, ChessBoard board) {
        super(isWhite, board);
        //점수 초기화
        this.Score = 9;
    }

    @Override
    public String canMove(int fromX, int fromY, int toX, int toY) {
        String str = "";
        boolean flag = false;

        // fromX, fromY, toX, toY를 이용하여 움직일 수 있는지 판단
        if (fromX == toX && fromY == toY) {//자기 자신의 위치를 입력했을 때
            str = "invalid move";
        } else if (fromX == toX && fromY != toY) {//세로로 이동
            if (fromY < toY) {//위로 이동
                for (int i = fromY + 1; i < toY; i++) {//이동 경로에 장애물이 있는지 확인
                    if (ChessBoard.board[fromX][i] != null) {
                        str = "there is a piece in the way";
                        flag = true;
                        break;
                    }
                }
            } else {//아래로 이동
                for (int i = fromY - 1; i > toY; i--) {//이동 경로에 장애물이 있는지 확인
                    if (ChessBoard.board[fromX][i] != null) {
                        flag = true;
                        str = "there is a piece in the way";
                        break;
                    }
                }
            }
        } else if (fromX != toX && fromY == toY) {//가로로 이동
            if (fromX < toX) {//오른쪽으로 이동
                for (int i = fromX + 1; i < toX; i++) {//이동 경로에 장애물이 있는지 확인
                    if (ChessBoard.board[i][fromY] != null) {//이동 경로에 장애물이 있으면 이동 불가
                        flag = true;
                        str = "there is a piece in the way";
                        break;
                    }
                }
            } else {
                for (int i = fromX - 1; i > toX; i--) {
                    if (ChessBoard.board[i][fromY] != null) {
                        flag = true;
                        str = "there is a piece in the way";
                        break;
                    }
                }
            }
        } else if (fromX != toX && fromY != toY) {//대각선 이동
            if (fromX - toX == fromY - toY) {//오른쪽 대각선 상승 이동
                if (fromX < toX && fromY < toY) {
                    for (int i = 1; i < toX - fromX; i++) {
                        if (ChessBoard.board[fromX + i][fromY + i] != null) {
                            flag = true;
                            str="there is a piece in the way";
                            break;
                        }
                    }
                } else if (fromX > toX && fromY > toY) {
                    for (int i = 1; i < fromX - toX; i++) {
                        if (ChessBoard.board[fromX - i][fromY - i] != null) {
                            str="there is a piece in the way";
                            flag = true;
                            break;
                        }
                    }
                }

            } else if (fromX - toX == -(fromY - toY)) {//오른쪽 대각선 하락 이동
                if (fromX < toX) {
                    for (int i = 1; i < toX - fromX; i++) {
                        if (ChessBoard.board[fromX + i][fromY - i] != null) {
                            str="there is a piece in the way";
                            flag = true;
                            break;
                        }
                    }
                } else {
                    for (int i = 1; i < fromX - toX; i++) {
                        if (ChessBoard.board[fromX - i][fromY + i] != null) {
                            flag = true;
                            str="there is a piece in the way";
                            break;
                        }
                    }
                }
            }else {
                flag= true;
                str = "invalid move";
            }
        }

        if (flag==false) {
            if (ChessBoard.board[toX][toY] == null) {//이동 경로에 장애물이 없으면 이동
                str = "move";
            } else if (ChessBoard.board[toX][toY].isWhite != ChessBoard.board[fromX][fromY].isWhite) {//이동 경로에 장애물이 있고 적인 기물이 있으면 먹기
                str = "eat";
            } else {//이동 경로에 장애물이 있고 아군 기물이 있으면 이동 불가
                str="there is a piece in the way";
            }
        }
        return str;//이동 불가
    }

    @Override
    public boolean canCheck(int pieceX, int pieceY, int kingX, int kingY) {
        return false;
    }
    // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, "move" 리턴
    // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 적인 경우, "eat" 리턴
    // 움직일 수 있는 경우, 이동하려는 위치에 기물이 없는 경우, 이동하려는 위치에 기물이 있는 경우, 아군인 경우, "wrong" 리턴
    // 움직일 수 없는 경우, "wrong" 리턴
    // 이동하려는 위치가 체스판을 벗어나는 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴
    // 이동하려는 위치가 같은 위치인 경우는 입력값이 잘못된 경우이므로, "wrong" 리턴

    @Override
    public String toString() {
        if (isWhite) {
            return "Q";
        } else {
            return "q";
        }
    }

    @Override
    public String getFullname(){
        return "Queen";
    }
}

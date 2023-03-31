public class ChessBoard {
    private ChessGame game;
     ChessPiece[][] board;
     int WhiteScore;
     int BlackScore;

    public ChessBoard(ChessGame game) {
        this.game = game;
        board=new ChessPiece[9][9];//9,9로 한 이유는 코딩할 때 0을 햇갈리지 않도록.
        //흰색 룩 초기화, 이런식으로 16개의 말 모두 초기화
        //폰같이 많은 기물은 for문 또한 사용 가능
        board[1][1]=new Rook(true, this);
        board[1][8]=new Rook(false, this);

        // Create the graphical representation of the chess Chessboard.
    }
    public boolean Move(int i, int i1, int i2, int i3) {
        //해당 좌표로 이동. 해당 움직임은 올바르게 검증되었다고 가정(전처리 후임)
        //i,i1 null로 처리하기
        //case1. 빈 공간으로 이동한 경우board(i2,i3)이 null인 경우
        //case2. 상대방 말이 있는 경우 점수를 더해주는 과정 거침
        ChessPiece piece=board[i][i1];
        return false;
    }

    public void printBoard() {
        //첫 행 옆줄에 점수 표기.
        //다음과 같은 형식으로 출력.
        // 대문자가 White.
        //"    A   B   C   D   E   F   G   H  \n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "8 | r | n | b | q | k | b | n | r | 8  White score: \n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "7 | p | p | p | p | p | p | p | p | 7  Black score: \n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "6 |   | ■ |   | ■ |   | ■ |   | ■ | 6\n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "5 | ■ |   | ■ |   | ■ |   | ■ |   | 5\n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "4 |   | ■ |   | ■ |   | ■ |   | ■ | 4\n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "3 | ■ |   | ■ |   | ■ |   | ■ |   | 3\n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "2 | P | P | P | P | P | P | P | P | 2\n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "1 | R | N | B | Q | K | B | N | R | 1\n" +
        //        "  +---+---+---+---+---+---+---+---+\n" +
        //        "    A   B   C   D   E   F   G   H  \n"
        //위 형식에 맞춰 알파벳과 공백의 위치에 board[i][j]의 정보를 출력.
        //각 자리에는 공백, ■, 대문자, 소문자가 들어감
        //대문자는 White, 소문자는 Black, ■는 비어있는 공간
        //board[i][j]의 정보는 board[i][j].getPiece()로 얻을 수 있음
        //board[i][j].getPiece()가 'p'인 경우, board[i][j].isWhite()가 true이면 대문자 P, false이면 소문자 p
        //board[i][j].getPiece()가 'r'인 경우, board[i][j].isWhite()가 true이면 대문자 R, false이면 소문자 r
        //board[i][j].getPiece()가 null인 경우, ■
        //공백은 공백, ■은 ■, 대문자는 대문자, 소문자는 소문자
        //이런식으로 board[i][j]의 정보를 출력
        //이후 White score와 Black score를 출력
        //White score와 Black score는 각각 WhiteScore와 BlackScore에 저장되어 있음
        //출력
        


    }

}

public class ChessBoard {
    private ChessGame game;
    //9,9로 한 이유는 코딩할 때 0을 햇갈리지 않도록.
    ChessPiece[][] board = new ChessPiece[9][9];
    int WhiteScore;
    int BlackScore;
    int TurnCount;

    public ChessBoard(ChessGame game) {
        this.game = game;
        board=new ChessPiece[9][9];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = null;
            }
        }
    }

    //치수
    public String Move(int i, int i1, int i2, int i3) {
        String str="";
        ChessPiece piece1=board[i][i1];
        ChessPiece piece2=board[i2][i3];
        ChessPiece tmp=null;
        String cstr=String.valueOf((char)(65+i2-1))+i3;
        if(piece1.isWhite()){
            //White 말 이동하는 경우
            str="White move "+piece1.toString()+" to "+cstr; 
            if(piece2!=null){
                //이동하려는 위치에 상대방 말이 있는 경우
                WhiteScore+=piece2.Score;   
            }
        }
        else{
            //Black 말 이동하는 경우
            str="Black move "+piece1.toString()+" to "+cstr;
            if(piece2!=null){
                //이동하려는 위치에 상대방 말이 있는 경우
                BlackScore+=piece2.Score;
            }
        }
        piece2=piece1;

        //해당 좌표로 이동. 해당 움직임은 올바르게 검증되었다고 가정(전처리 후임)
        
        //i,i1 null로 처리하기
        //case1. 빈 공간으로 이동한 경우board(i2,i3)이 null인 경우
        //case2. 상대방 말이 있는 경우 점수를 더해주는 과정 거침
        //리턴 값은 "White(Black) move Knight to A7" 이런식으로.
      
        return str;
    }

    //주혁
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

        System.out.println("    A   B   C   D   E   F   G   H  ");
        for (int i = 1; i < board.length; i++) {
            System.out.println("  +---+---+---+---+---+---+---+---+");
            System.out.print(8 - i + " ");
            for (int j = 1; j < board[i].length; j++) {
                System.out.print("| ");
                if (board[j][i] instanceof ChessPiece) System.out.print(board[j][i].toString());
                else if ((i + j) % 2 != 1) System.out.print("■");
                else System.out.print(" ");
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.print(8 - i + " ");
            if (i == 1) System.out.print(" White score: " + WhiteScore);
            if (i == 2) System.out.print(" Black score: " + BlackScore);
            if (i == 8) System.out.print(" Turn: " + TurnCount);
            System.out.println();
        }
        System.out.println("  +---+---+---+---+---+---+---+---+");
        System.out.println("    A   B   C   D   E   F   G   H  ");




    }

}

import java.util.Scanner;

public class ChessBoard {
    private ChessGame game;
    //9,9로 한 이유는 코딩할 때 0을 햇갈리지 않도록.
    ChessPiece[][] board = new ChessPiece[9][9];

    public ChessBoard(ChessGame game) {
        this.game = game;
        board = new ChessPiece[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    //치수
    public String Move(int i, int i1, int i2, int i3) {
        String str = "";
        ChessPiece piece1 = board[i][i1];
        ChessPiece piece2 = board[i2][i3];
        String cstr1 = String.valueOf((char) (65 + i2 - 1)) + i3;
        String cstr2 = String.valueOf((char) (65 + i - 1)) + i1;
        if (piece1.isWhite) {
            //White 말 이동하는 경우
            str = "White moved " + piece1.getFullname() + " from " + cstr2 + " to "+cstr1;
            if (piece2 != null) {
                //이동하려는 위치에 상대방 말이 있는 경우
                game.WhiteScore += piece2.Score;
            }
        } else {
            //Black 말 이동하는 경우
            str = "Black moved " + piece1.getFullname() + " from " + cstr2 + " to "+cstr1;
            if (piece2 != null) {
                //이동하려는 위치에 상대방 말이 있는 경우
                game.BlackScore += piece2.Score;
            }
        }
        board[i2][i3] = piece1;
        board[i][i1] = null;
        if(piece1.getFullname().equals("Pawn")){//폰 승진
            //str return까지 "White pawn promoted to Queen" 이런식으로 하기.
            //밑의 inputPawnPromoteTo() 함수를 이용해서 폰이 무엇으로 승진할지 입력받기.
            //inputPawnPromoteTo() 함수 완성하기.
            if(piece1.isWhite&&i3==8){//흰색 폰 승진
                if(inputPawnPromoteTo()=='q') {//예시
                    board[i2][i3] = new Queen(true, this);
                    return str+" White pawn promoted to Queen";
                }else if(inputPawnPromoteTo()=='b') {
                    board[i2][i3] = new Bishop(true, this);
                    return str + " White pawn promoted to Bishop";
                }
            }else{//폰이 검은색인 경우
            }
        }
        //해당 좌표로 이동. 해당 움직임은 올바르게 검증되었다고 가정(전처리 후임)

        //i,i1 null로 처리하기
        //case1. 빈 공간으로 이동한 경우board(i2,i3)이 null인 경우
        //case2. 상대방 말이 있는 경우 점수를 더해주는 과정 거침
        //리턴 값은 "White(Black) move Knight to A7" 이런식으로.

        return str;
    }

    public char inputPawnPromoteTo(){
        //폰이 무엇으로 변할지 입력받는 함수
        //제대로 입력받을 때까지 반복
        //Q,B,R,N 중 선택 가능
        char c=' ';
        Scanner sc=new Scanner(System.in);
        String str="";
        while (!(str.length()==1&&str.charAt(0)=='q'||str.charAt(0)=='b'||str.charAt(0)=='r'||str.charAt(0)=='n')){
            System.out.print("promote pawn to: ");
            str=sc.nextLine();
            if(str.length()==1&&str.charAt(0)=='q'||str.charAt(0)=='b'||str.charAt(0)=='r'||str.charAt(0)=='n')
                c=str.charAt(0);
            else
                System.out.println("input error");
        }
        return c;
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
        for (int i = 8; i > 0; i--) {
            System.out.println("  +---+---+---+---+---+---+---+---+");
            System.out.print(i + " ");
            for (int j = 1; j < board[i].length; j++) {
                System.out.print("| ");
                if (board[j][i] instanceof ChessPiece) System.out.print(board[j][i].toString());
                else if ((i + j) % 2 != 1) System.out.print("■");
                else System.out.print(" ");
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.print(i + " ");
            if (i == 8) System.out.print(" White score: " + game.WhiteScore);
            if (i == 7) System.out.print(" Black score: " + game.BlackScore);
            if (i == 1) System.out.print(" Turn: " + game.turn);
            System.out.println();
        }
        System.out.println("  +---+---+---+---+---+---+---+---+");
        System.out.println("    A   B   C   D   E   F   G   H  ");


    }

}

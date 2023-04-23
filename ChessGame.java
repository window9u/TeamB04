import java.util.Scanner;

public class ChessGame {
    public ChessBoard Chessboard;
    boolean isWhiteTurn;
    int WhiteScore;
    int BlackScore;
    int turn=50;// 총 턴
    int fromX, fromY, toX, toY;
    Scanner scan = new Scanner(System.in);

    //찬규
    public ChessGame() {
        // Initialize the chess Chessboard and set up the game.
        // 체스 판 및
        // 기물 초기화
        initBoard();
        // 체스 게임 시작
        // 킹이 죽거나 무승부(턴수 제한이 아니면)
        int flag = 0;
        while (!isKingdie() && !isStalemate()) {
            // 보드 출력
            Chessboard.printBoard();
            // 보드 밑에 출력문 출력.
            printMessage(isWhiteTurn);
            // 사용자 입력 받기
            inputFrom(isWhiteTurn);

            flag = inputTo(isWhiteTurn);
            if (flag == 0) {// 기물을 다시 선택하는 경우
                continue;
            } else if (flag == -1) {// 긴급종료
                break;
            }
            // 이동
            Chessboard.Move(fromX, fromY, toX, toY);
            // 턴 바꾸기
            isWhiteTurn = !isWhiteTurn;
        }
        printEnding();
    }
    //경식
    private void printMessage(boolean isWhiteTurn) {
        // 이전에 했던 움직임 출력ex) White move Night to A7
        // 어려우면 안해도 될 듯.

    }
    //재하
    public int pieceColor(int x, int y) {
        // 해당 좌표의 기물의 색이 무엇인지 출력.
        // 흑이면 -1, 백이면 +1, 비었으면 0 리턴
        // piece를 사용하여 판별

        ChessPiece piece = this.Chessboard.board[x][y];
        return 1;
    }
    //재하
    private void inputFrom(boolean isWhiteTurn) {
        // 선택할 말의 위치를 선택하시오: "A8"에서 문자열 추출 후 toX:1, toY:8 대입
        // 만일 잘못된 위치를 선택 시 오류 메세지(위치가 잘못되었습니다. 다시 입력하세요.) 출력 후 재입력 받음
        // 결국 이 함수에 목적은 움직일 말이 있는 정상 위치를 입력받는 것임.
        // 정상 값을 입력할때까지 이 함수 안에서 다시 입력받을 것
        // pieceColor() 사용하기
        while (true) {

            String str = scan.nextLine();
            // str에 "'A~H"+'1~8'문자열 입력 ex) A8"을 입력받아서 fromX:1, fromY:8 대입 (문자열 처리)
            // 예외처리: 잘못된 문자열 입력 시 오류 메세지 출력 후 재입력 받음
            // str에 fromX, fromY 추출
            // 예외처리: 잘못된 위치 입력 시 오류 메세지 출력 후 재입력 받음
            // 예외처리: 비어있는 위치 입력 시 오류 메세지 출력 후 재입력 받음
            // 예외처리: 흑의 차례에 백의 말을 선택한 경우 오류 메세지 출력 후 재입력 받음
            // 예외처리: 백의 차례에 흑의 말을 선택한 경우 오류 메세지 출력 후 재입력 받음
           
    }
    //찬규
    private int inputTo(boolean isWhiteTurn) {
        // 선택할 말의 위치를 선택하시오: "A8"에서 문자열 추출 후 toX:1, toY:8 대입
        // 만일 잘못된 위치를 선택 시 오류 메세지 출력 후 재입력 받음
        // 사용할 기물 선택으로 돌아가려면 0 리턴, 긴급종료면 -1 리턴, 정상 입력이면 1 리턴
        // 위의 pieceColor()활용하기
        while (true) {

            // toX, toY 입력받기

            String str = this.Chessboard.board[fromX][fromY].canMove(fromX, fromY, toX, toY);
            if (str.equals("wrong")) {// 재입력
                continue;
            } else if (str.equals("quit")) {
                return -1;
            } else if (str.equals("back")) {
                return 0;
            } else {// 정상이동
                return 1;
            }
        }

    }

    //치수
    private void printEnding() {
        // ex) White Win!!
        
    }
    //주혁
    private void initBoard() {
        // 체스 기물 초기화 초기화 ex)Chessboard.board[1][1] = new Rook(false, this.Chessboard);
        // 0,0은 사용하지 않음

    }
    //경식
    public boolean isKingdie() {
            int k = 0;
            for (int i = 1; i < 9; i++) {
                for(int j = 1; j < 9; j++){
                    if(ChassBoard.board[i][j] == null){
                        continue;
                    }
                    if (ChassBoard.board[i][j] instanceof King) {
                        k++;
                    }
                }
            }
            if(k == 2){
                return false;
            }
            else{
                return true;
            }
        }
        // 킹이 죽었는지 확인
        // 킹이 죽었으면 true, 아니면 false
        // 체스판에서 흑과 백의 킹이 죽었는지 확인
        
        return true;
    }
    //치수
    public boolean isStalemate() {
        // Determine whether the game is in stalemate.
        // 턴수가 끝났는지 검사
        return true;
    }

}

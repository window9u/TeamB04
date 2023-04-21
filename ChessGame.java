import java.util.Scanner;

public class ChessGame {
    public ChessBoard Chessboard;
    boolean isWhiteTurn;
    int WhiteScore;
    int BlackScore;
    int turn = 50;// 총 턴
    int fromX, fromY, toX, toY;
    int flag = 0;
    Scanner scan = new Scanner(System.in);
    String printMessage="Game Start!!";

    // 찬규
    public ChessGame() {
        // Initialize the chess Chessboard and set up the game.
        // 체스 판 및
        // 기물 초기화
        initBoard();
        // 체스 게임 시작
        // 킹이 죽거나 무승부(턴수 제한이 아니면)
        Chessboard.printBoard();

        while (!isKingdie() && !isTurnsleft()) {
            // 보드 출력
            Chessboard.printBoard();
            // 보드 밑에 출력문 출력.
            // printMessage 함수에서 전역변수로 변경.
            // 기존의 printMessage()의 기능은 Chessboard.Move()에서 문자열 리턴.
            //초기값은 Game Start!!
            System.out.println(printMessage);
            // 사용자 입력 받기
            inputFrom(isWhiteTurn);
            if (flag == -1) {// 긴급종료
                break;
            }
            inputTo(isWhiteTurn);
            if (flag == 0) {// 기물을 다시 선택하는 경우
                continue;
            } else if (flag == -1) {// 긴급종료
                break;
            }
            // 이동
            printMessage =Chessboard.Move(fromX, fromY, toX, toY);
            // 턴 바꾸기
            isWhiteTurn = !isWhiteTurn;
        }
        printEnding();
    }

    // 재하
    public int pieceColor(int x, int y) {
        // 해당 좌표의 기물의 색이 무엇인지 출력.
        // 흑이면 -1, 백이면 +1, 비었으면 0 리턴
        // piece를 사용하여 판별

        ChessPiece piece = this.Chessboard.board[x][y];
        return 1;
    }

    // 재하
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
    }

    // 찬규
    private void inputTo(boolean isWhiteTurn) {
        // 선택할 말의 위치를 선택하시오: "A8"에서 문자열 추출 후 toX:1, toY:8 대입
        // 만일 잘못된 위치를 선택 시 오류 메세지 출력 후 재입력 받음
        while (true) {
            // toX, toY 입력받기
            String inputstr = scan.nextLine();
            // 예외처리: 잘못된 문자열 입력 시 오류 메세지 출력 후 재입력 받음
            if (inputstr.equals("quit")) {// 긴급종료
                this.flag = -1;
                continue;
            } else if (inputstr.equals("back")) { // 기물을 다시 선택하는 경우
                this.flag = 0;
                continue;
            } else if (inputstr.length() == 2 && inputstr.charAt(0) >= 'A' && inputstr.charAt(0) <= 'H'
                    && inputstr.charAt(1) >= '1' && inputstr.charAt(1) <= '8') {
                // 정상입력
                this.toX = inputstr.charAt(0) - 'A' + 1;
                this.toY = inputstr.charAt(1) - '0';
            } else {
                // 재입력
                System.out.println("input error");
                continue;
            }
            String str = this.Chessboard.board[fromX][fromY].canMove(fromX, fromY, toX, toY);
            if (str.equals("move")) {// 정상이동
                break;
            } else if (str.equals("eat")) {// 정상이동
                break;
            } else {
                // 재입력
                System.out.println(str);
                continue;
            }
        }
    }

    // 치수
    private void printEnding() {
        // ex) White Win!!
        if (isKingdie()) { //king이 죽어서 끝난 경우
            if (isWhiteTurn) { //black 승
                System.out.println("White King die, Black Win !!");
            } else { //white 승
                System.out.println("Black King die, White Win!!");
            }
        } else if (isTurnsleft()) { // 턴이 끝나서 종료된 경우
            if (WhiteScore > BlackScore) {
                System.out.println("White Score : "+WhiteScore+"\nBlack Score : "+BlackScore+"\nWhite Win !!");
            } else if (WhiteScore < BlackScore) {
                System.out.println("White Score : "+WhiteScore+"\nBlack Score : "+BlackScore+"\nBlack Win !!");
            } else {
                System.out.println("White Score : "+WhiteScore+"\nBlack Score : "+BlackScore+"\nDraw !!");
            }

        } else {
            System.out.println("Emergency Termination");
        }

    }

    // 주혁
    private void initBoard() {
        //시작 방법?
        //체스 게임을 시작으로 체스에서 체스 보드를 불러와야 하는데 체스 보드에서는 현재의 체스판을 인자로 받아와야함
        //이때 ChessBoard(ChessGame game)에서 game에 자기 자신을 넣을 수 없으니 충돌이 발생
        //Chessboard를 초기화 시킬 수 없어서 시작이 안됨


        for (int i = 1; i < Chessboard.board.length; i++){
            for (int j = 1; j < Chessboard.board[i].length; j++){
                if (i == 2 || i == 7) {
                    Chessboard.board[j][i] = new Pawn(true, this.Chessboard);
                    if (i == 1) Chessboard.board[j][i] = new Pawn(false, this.Chessboard);
                }

                else if ((i == 1 || i == 8) && (j == 2 || j == 7)) {
                    Chessboard.board[j][i] = new Knight(true, this.Chessboard);
                    if (i == 0) Chessboard.board[j][i] = new Knight(false, this.Chessboard);
                }

                else if ((i == 1 || i == 8) && (j == 1 || j == 8)) {
                    Chessboard.board[j][i] = new Rook(true, this.Chessboard);
                    if (i == 0) Chessboard.board[j][i] = new Rook(false, this.Chessboard);
                }

                else if ((i == 1 || i == 8) && (j == 3 || j == 6)) {
                    Chessboard.board[j][i] = new Bishop(true, this.Chessboard);
                    if (i == 0) Chessboard.board[j][i] = new Bishop(false, this.Chessboard);
                }

                else if ((i == 1 || i == 8) && (j == 4)) {
                    Chessboard.board[j][i] = new Queen(true, this.Chessboard);
                    if (i == 0) Chessboard.board[j][i] = new Queen(false, this.Chessboard);
                }

                else if ((i == 1 || i == 8) && (j == 5)) {
                    Chessboard.board[j][i] = new King(true, this.Chessboard);
                    if (i == 0) Chessboard.board[j][i] = new King(false, this.Chessboard);
                }
            }
        }
    }

    // 경식
    public boolean isKingdie() {
        // 킹이 죽었는지 확인
        // 킹이 죽었으면 true, 아니면 false
        // 체스판에서 흑과 백의 킹이 죽었는지 확인
        // 체스판을 모두 돌면서 킹이 두개 존재하는지 확인하면 될 듯

        return true;
    }

    // 치수
    public boolean isTurnsleft() {
        // Determine whether the game is in stalemate.
        
        // 턴수가 끝났는지 검사
        return true;
    }

}

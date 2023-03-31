import java.util.Scanner;

public class ChessGame {
    public ChessBoard Chessboard;
    boolean isWhiteTurn;
    int WhiteScore;
    int BlackScore;
    int turn;// 총 턴
    int fromX, fromY, toX, toY;
    Scanner scan = new Scanner(System.in);

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

    private void printMessage(boolean isWhiteTurn) {
        // 이전에 했던 움직임 출력ex) White move Night to A7
        // 어려우면 안해도 될 듯.

    }

    public int pieceColor(int x, int y) {
        // 해당 좌표의 기물의 색이 무엇인지 출력.
        // 흑이면 -1, 백이면 +1, 비었으면 0 리턴
        // piece를 사용하여 판별

        ChessPiece piece = this.Chessboard.board[x][y];
        if (piece == null) {
            return 0;
        }
        if (piece.isWhite) {
            return 1;
        } else {
            return -1;
        }
    }

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
            if (str.equals("quit")) {// 긴급종료
                System.out.println("게임을 종료합니다.");
                break;
            } else if (str.equals("back")) {// 기물을 다시 선택하는 경우
                break;
            } else if (str.length() != 2) {// 재입력
                System.out.println("위치가 잘못되었습니다. 다시 입력하세요.");
                continue;
            } else if (str.charAt(0) < 'A' || str.charAt(0) > 'H') {// 재입력
                System.out.println("위치가 잘못되었습니다. 다시 입력하세요.");
                continue;
            } else if (str.charAt(1) < '1' || str.charAt(1) > '8') {// 재입력
                System.out.println("위치가 잘못되었습니다. 다시 입력하세요.");
                continue;
            } else

            if (pieceColor(fromX, fromY) == 0) {// 비어있는 경우
                System.out.println("위치가 잘못되었습니다. 다시 입력하세요.");
                continue;
            } else if (pieceColor(fromX, fromY) == 1 && isWhiteTurn == false) {// 흑의 차례에 백의 말을 선택한 경우
                System.out.println("위치가 잘못되었습니다. 다시 입력하세요.");
                continue;
            } else if (pieceColor(fromX, fromY) == -1 && isWhiteTurn == true) {// 백의 차례에 흑의 말을 선택한 경우
                System.out.println("위치가 잘못되었습니다. 다시 입력하세요.");
                continue;
            } else {
                break;
            }
        }
    }

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

    private void printEnding() {
        // ex) White Win!!
        if (isKingdie()) {
            // 왕이 죽어서 끝난 경우
            if (isWhiteTurn) {
                // 흑이 이긴 경우
                System.out.println("Black Win!!");
            } else {
                // 백이 이긴 경우
                System.out.println("White Win!!");
            }

        } else if (isStalemate()) {
            // 무승부인 경우 WhiteScore 비교해서 출력
            if (WhiteScore > BlackScore) {
                System.out.println("White Win!!");
            } else if (WhiteScore < BlackScore) {
                System.out.println("Black Win!!");
            } else {
                System.out.println("Draw!!");
            }

        } else {
            // 긴급종료인 경우
            System.out.println("Emergency Exit!!");
        }

    }

    private void initBoard() {
        // 체스 기물 초기화 초기화 ex)Chessboard.board[1][1] = new Rook(false, this.Chessboard);
        // 0,0은 사용하지 않음
        Chessboard = new ChessBoard(this);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.Chessboard.board[i][j] = null;
            }
        }
        // 흑
        this.Chessboard.board[1][1] = new Rook(false, this.Chessboard);
        this.Chessboard.board[1][2] = new Knight(false, this.Chessboard);
        this.Chessboard.board[1][3] = new Bishop(false, this.Chessboard);
        this.Chessboard.board[1][4] = new Queen(false, this.Chessboard);
        this.Chessboard.board[1][5] = new King(false, this.Chessboard);
        this.Chessboard.board[1][6] = new Bishop(false, this.Chessboard);
        this.Chessboard.board[1][7] = new Knight(false, this.Chessboard);
        this.Chessboard.board[1][8] = new Rook(false, this.Chessboard);
        for (int i = 1; i < 9; i++) {
            this.Chessboard.board[2][i] = new Pawn(false, this.Chessboard);
        }
        // 백
        this.Chessboard.board[8][1] = new Rook(true, this.Chessboard);
        this.Chessboard.board[8][2] = new Knight(true, this.Chessboard);
        this.Chessboard.board[8][3] = new Bishop(true, this.Chessboard);
        this.Chessboard.board[8][4] = new Queen(true, this.Chessboard);
        this.Chessboard.board[8][5] = new King(true, this.Chessboard);
        this.Chessboard.board[8][6] = new Bishop(true, this.Chessboard);
        this.Chessboard.board[8][7] = new Knight(true, this.Chessboard);
        this.Chessboard.board[8][8] = new Rook(true, this.Chessboard);
        for (int i = 1; i < 9; i++) {
            this.Chessboard.board[7][i] = new Pawn(true, this.Chessboard);
        }

    }

    public boolean isKingdie() {
        // 킹이 죽었는지 확인
        // 킹이 죽었으면 true, 아니면 false
        // 체스판에서 흑과 백의 킹이 죽었는지 확인
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (this.Chessboard.board[i][j] instanceof King) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStalemate() {
        // Determine whether the game is in stalemate.
        // 턴수가 끝났는지 검사
        if (turn == 50) {
            return true;
        }
        return false;
    }

}

import java.util.Scanner;

public class ChessGame {
    public ChessBoard Chessboard;
    boolean isWhiteTurn=true;
    int WhiteScore;
    int BlackScore;
    int turn = 50;// 총 턴
    int fromX, fromY, toX, toY;
    int flag = 999;
    Scanner scan = new Scanner(System.in);
    String printMessage = "Game Start!!";

    public ChessGame() {

    }

    public void StartGame() {

        initBoard();
        // 체스 게임 시작
        // 킹이 죽거나 무승부(턴수 제한이 아니면)
        while (!isKingdie() && !isTurnsleft()) {
            // 보드 출력
            if(flag!=888){
                Chessboard.printBoard();
                System.out.println(printMessage);
            }else{
                flag=999;//flag 초기화
            }
            // 보드 밑에 출력문 출력.
            // printMessage 함수에서 전역변수로 변경.
            // 기존의 printMessage()의 기능은 Chessboard.Move()에서 문자열 리턴.
            // 초기값은 Game Start!!
            // 사용자 입력 받기
            inputFrom(isWhiteTurn);
            if (flag == -1) {// 긴급종료
                break;
            }
            inputTo(isWhiteTurn);
            if (flag == 0) {// 기물을 다시 선택하는 경우
                flag=888;//flag 변경(보드 출력 안함, isTurnleft()
                if(isWhiteTurn) {
                    System.out.println("White Re-selecting");
                }else {
                    System.out.println("Black Re-selecting");
                }
                continue;
            } else if (flag == -1) {// 긴급종료
                break;
            }
            // 이동
            printMessage = Chessboard.Move(fromX, fromY, toX, toY);
            // 턴 바꾸기
            if(isWhiteTurn==true) {
                isWhiteTurn= false;
            }else {
                isWhiteTurn=true;
            }
            turn--;
        }
        printEnding();
    }

    // 재하
    public int pieceColor(int x, int y) {
        // 해당 좌표의 기물의 색이 무엇인지 출력.
        // 흑이면 -1, 백이면 +1, 비었으면 0 리턴
        // piece를 사용하여 판별

        ChessPiece piece = this.Chessboard.board[x][y];
        if(piece==null) {
            return 0;
        }
        else if(piece.isWhite == true) {
            return 1;
        }
        else{
            return -1;
        }
    }

    // 재하
    private void inputFrom(boolean isWhiteTurn) {
        // 선택할 말의 위치를 선택하시오: "A8"에서 문자열 추출 후 toX:1, toY:8 대입
        // 만일 잘못된 위치를 선택 시 오류 메세지(위치가 잘못되었습니다. 다시 입력하세요.) 출력 후 재입력 받음
        // 결국 이 함수에 목적은 움직일 말이 있는 정상 위치를 입력받는 것임.
        // 정상 값을 입력할때까지 이 함수 안에서 다시 입력받을 것
        // pieceColor() 사용하기

        while (true) {
            if(isWhiteTurn)
                System.out.print("Select White piece: ");
            else
                System.out.print("Select Black piece: ");
            //  fromX, fromY 입력받기
            String inputstr = scan.nextLine();
            // 예외처리
            if(inputstr.equals("quit")) {
                this.flag = -1;
                break;
            } else if(inputstr.length() == 2 && inputstr.charAt(0) >= 'A' && inputstr.charAt(0) <= 'H'
                    && inputstr.charAt(1) >= '1' && inputstr.charAt(1) <= '8') {
                // 정상입력
                this.fromX = inputstr.charAt(0) - 'A' + 1;
                this.fromY = inputstr.charAt(1) - '0';
                //디버깅용
                if(pieceColor(fromX,fromY)==0){//비어있는 경우
                    System.out.println("There is no piece");
                } else if(isWhiteTurn == true) {
                    // 백 차례일 때 백의 말을 선택한 경우
                    if(pieceColor(fromX, fromY) == 1){
                        System.out.println("White Selected "+Chessboard.board[fromX][fromY].getFullname());
                        break;
                    }
                    // 백 차례일 때 흑의 말을 선택하거나 선택한 좌표가 비어있는 경우
                    else {
                        System.out.println("Selected opponent's piece");
                        continue;
                    }
                } else {
                    // 흑 차례일 때 백의 말을 선택한 경우
                    if(pieceColor(fromX, fromY) == -1) {
                        System.out.println("Black Selected "+Chessboard.board[fromX][fromY].getFullname());
                        break;
                    }
                    // 흑 차례일 때 백의 말을 선택하거나 선택한 좌표가 비어있는 경우
                    else if(pieceColor(fromX, fromY) == 1 || pieceColor(fromX, fromY) == 0) {
                        System.out.println("Selected opponent's piece");
                        continue;
                    }
                }
            } else {
                //재입력
                System.out.println("input error");
                continue;
            }
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
            System.out.print("Move to:");
            // toX, toY 입력받기
            String inputstr = scan.nextLine();
            // 예외처리: 잘못된 문자열 입력 시 오류 메세지 출력 후 재입력 받음
            if (inputstr.equals("quit")) {// 긴급종료
                this.flag = -1;
                break;
            } else if (inputstr.equals("back")) { // 기물을 다시 선택하는 경우
                this.flag = 0;
                break;
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
                System.out.println("White King dead, Black Win !!");
            } else { //white 승
                System.out.println("Black King dead, White Win!!");
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
            System.out.println("Exiting the game");
        }

    }

    // 주혁
    private void initBoard() {
        // 시작 방법?
        // 체스 게임을 시작으로 체스에서 체스 보드를 불러와야 하는데 체스 보드에서는 현재의 체스판을 인자로 받아와야함
        // 이때 ChessBoard(ChessGame game)에서 game에 자기 자신을 넣을 수 없으니 충돌이 발생
        // Chessboard를 초기화 시킬 수 없어서 시작이 안됨

        this.Chessboard = new ChessBoard(this);

        for(int i=1;i<9;i++){
            Chessboard.board[i][2] = new Pawn(true, this.Chessboard);
        }
        for(int i=1;i<9;i++){
            Chessboard.board[i][7] = new Pawn(false, this.Chessboard);
        }

        Chessboard.board[1][1] = new Rook(true, this.Chessboard);
        Chessboard.board[8][1] = new Rook(true, this.Chessboard);
        Chessboard.board[1][8] = new Rook(false, this.Chessboard);
        Chessboard.board[8][8] = new Rook(false, this.Chessboard);
        Chessboard.board[2][1] = new Knight(true, this.Chessboard);
        Chessboard.board[7][1] = new Knight(true, this.Chessboard);
        Chessboard.board[2][8] = new Knight(false, this.Chessboard);
        Chessboard.board[7][8] = new Knight(false, this.Chessboard);
        Chessboard.board[3][1] = new Bishop(true, this.Chessboard);
        Chessboard.board[6][1] = new Bishop(true, this.Chessboard);
        Chessboard.board[3][8] = new Bishop(false, this.Chessboard);
        Chessboard.board[6][8] = new Bishop(false, this.Chessboard);
        Chessboard.board[4][1] = new Queen(true, this.Chessboard);
        Chessboard.board[5][1] = new King(true, this.Chessboard);
        Chessboard.board[4][8] = new Queen(false, this.Chessboard);
        Chessboard.board[5][8] = new King(false, this.Chessboard);
    }


    // 경식
    public boolean isKingdie() {
            int k = 0;
            for (int i = 1; i < 9; i++) {
                for(int j = 1; j < 9; j++){
                    if(this.Chessboard.board[i][j] == null){
                        continue;
                    }
                    if (Chessboard.board[i][j] instanceof King) {
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
        // 체스판을 모두 돌면서 킹이 두개 존재하는지 확인하면 될 듯


    // 치수
    public boolean isTurnsleft() { //남은 턴수 계산, 0이되면 true 반환
        if(turn>0){
            return false;
        }
        else{
            return true;
        }
    }

}

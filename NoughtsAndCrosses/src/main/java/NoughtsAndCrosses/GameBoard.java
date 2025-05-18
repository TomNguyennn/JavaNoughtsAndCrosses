package NoughtsAndCrosses;

public class GameBoard {
    int size;
    String[][] board;
    String currentPlayer;
    GameBoard(int size) {
        this.size = size;
        this.board = new String[size][size];

        resetBoard();
    }
    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";

            }
        }
        this.currentPlayer = "X";
    }
    public void switchPlayer(){
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";

    }
    public void makeMove(int row, int col) {
        if (board[row][col].isEmpty()) {
            board[row][col] = currentPlayer;
            switchPlayer();
        } else {
            System.out.println("Overlapped");
        }
    }
    public String checkWinner() {
       for (int i = 0; i < size; i++) {
           if(allEquals(getCol(i)) && !board[0][i].isEmpty()) {
               return board[i][0];
           }
           if(allEquals(board[i]) && !board[i][0].isEmpty()) {
               return board[0][i];
           }
       }
       if (allEquals(getDiagonal()) && !board[0][0].isEmpty()) {return board[0][0];}
       if (allEquals(getReverseDiagonal()) && !board[0][size - 1].isEmpty()) {return board[0][size - 1];}
       return "";
    }
    public boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean allEquals(String[] arr){
        String first = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (!arr[i].equals(first)) {
                return false;
            }
        }
        return true;

    }
    private String[] getCol(int c){
        String[] col = new String[size];
        for (int i = 0; i < size; i++) {
            col[i] = board[i][c];
        }
        return col;
    }
    private String[] getDiagonal() {
        String[] diagonal = new String[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = board[i][i];
        }
        return diagonal;
    }
    private String[] getReverseDiagonal() {
        String[] reverseDiagonal = new String[size];
        for (int i = 0; i < size; i++) {
            reverseDiagonal[i] = board[i][size - i - 1];
        }
        return reverseDiagonal;
    }


}

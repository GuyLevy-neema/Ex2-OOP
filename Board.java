package main;

public class Board {

    public static final int SIZE = 3;
    public static final int WIN_STREAK = 3;
    private static final Mark[][] board = new Mark[SIZE][SIZE];
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    private int emptyCells = SIZE * SIZE;

    private final int[][] possibleDirections = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public Board() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Mark.BLANK;
            }

        }
    }


    private int countMarkInDirection(int row, int col, int rowDelta, int colDelta, Mark mark) {
        int count = 0;
        while (row <= SIZE && row > 0 && col <= SIZE && col > 0 && board[row - 1][col - 1] == mark) {
            count++;
            row += rowDelta;
            col += colDelta;
        }
        return count;
    }

    private void updateGameStatus(Mark mark, int row, int col) {
        emptyCells -= 1;
        if (gameStatus != GameStatus.IN_PROGRESS) {
            return;
        }
        int maxSequence = 0;
        for (int[] possibleDirection : possibleDirections) {
            int rowDirection = possibleDirection[0];
            int colDirection = possibleDirection[1];
            int curSequence = countMarkInDirection(row, col, rowDirection, colDirection, mark)
                    + countMarkInDirection(row, col, -rowDirection, -colDirection, mark) - 1;
            if (curSequence > maxSequence)
                maxSequence = curSequence;
        }

        if (maxSequence >= WIN_STREAK) {
            if (mark == Mark.X) {
                gameStatus = GameStatus.X_WIN;
            } else {
                gameStatus = GameStatus.O_WIN;
            }
        } else if (emptyCells <= 0) {
            gameStatus = GameStatus.DRAW;
        }

    }

    public boolean putMark(Mark mark, int row, int col) {

        if (row <= 0 || row > SIZE || col <= 0 || col > SIZE || board[row - 1][col - 1] != Mark.BLANK) {
            return false;
        }
        board[row - 1][col - 1] = mark;
        updateGameStatus(mark, row, col);
        return true;
    }


    public GameStatus getGameStatus() {
        return gameStatus;
    }


    public Mark getMark(int row, int col) {
        if (row < 0 || row > SIZE || col < 0 || col > SIZE) {
            return Mark.BLANK;
        }
        return board[row][col];
    }

}

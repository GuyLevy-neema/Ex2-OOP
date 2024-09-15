package player;

import main.Board;
import main.GameStatus;
import main.Mark;

class CleverPlayer implements Player {
    private int curRow = 1;
    private int curCol = 1;

    private void updateRowCol() {
        if (curCol == Board.SIZE) {
            curRow += 1;
            curCol = 1;
        } else {
            curCol += 1;
        }
    }

    @Override
    public void playTurn(Board board, Mark playerMark) {
        if (curRow > Board.SIZE || curCol > Board.SIZE || board.getGameStatus() != GameStatus.IN_PROGRESS){
            curRow = 1;
            curCol = 1;
        }
        while (!board.putMark(playerMark, curRow, curCol)) {
            updateRowCol();
        }
        updateRowCol();
    }
}

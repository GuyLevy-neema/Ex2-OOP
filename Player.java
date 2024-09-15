package player;

import main.Board;
import main.Mark;

public interface Player {
    public void playTurn(Board board, Mark playerMark);
}

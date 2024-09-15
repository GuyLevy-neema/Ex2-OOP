package player;

import main.Board;
import main.Mark;

import java.util.Random;

class WhateverPlayer implements Player {

    private final Random random = new Random();

    @Override
    public void playTurn(Board board, Mark playerMark) {
        while (true){
            int row = random.nextInt(Board.SIZE) + 1;
            int col = random.nextInt(Board.SIZE) + 1;
            if (board.putMark(playerMark, row, col))
                break;
        }
    }
}

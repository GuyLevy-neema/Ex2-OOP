package main;

import player.Player;
import rendering.Renderer;

class Game {

    private static final String X_WIN_MSG = "The Winner is X!";
    private static final String O_WIN_MSG = "The Winner is O!";
    private static final String DRAW_MSG = "This is a Draw!";
    private final Renderer renderer;
    private final Board board;
    private final Mark[] marks = {Mark.X, Mark.O};
    private int counterPlayerMark;
    private Player[] players = null;

    public Game(Player playerX, Player playerO, Renderer renderer, Board board){
        players = new Player[]{playerX, playerO};
        this.renderer = renderer;
        this.board=board;
    }

    private void printGameStatus(){
        if (board.getGameStatus() == GameStatus.DRAW){
            System.out.println(DRAW_MSG);
        }
        else if (board.getGameStatus() == GameStatus.O_WIN){
            System.out.println(O_WIN_MSG);
        }
        else {
            System.out.println(X_WIN_MSG);
        }
    }
    public void run()
    {
        while(board.getGameStatus() == GameStatus.IN_PROGRESS){
            counterPlayerMark++;
            int playerTurn = counterPlayerMark%2;
            players[playerTurn].playTurn(board, marks[playerTurn]);
            renderer.renderBoard(board);
        }
//        printGameStatus();
    }


}

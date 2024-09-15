package main;

import player.Player;
import player.PlayerFactory;
import rendering.Renderer;
import rendering.RendererFactory;

import java.util.Hashtable;
import java.util.Map;

class Tournament {
    public static final String INVALID_INPUT_MSG = "invalid input";
    public static final int ARGS_LENGTH = 4;
    public static final int RENDERER_INDEX_ARGS = 1;
    public static final int FIRST_PLAYER_INDEX_ARGS = 2;
    public static final int SECOND_PLAYER_INDEX_ARGS = 3;
    public static final int NUM_OF_TOURNAMENT_INDEX = 0;
    private final int rounds;
    private final Renderer renderer;
    private final Player[] players;
    private final Map<Player, Integer> gameResults = new Hashtable<>();
    private int drawCounter = 0;


    //java Tournament 3 console clever human
    public static void main(String[] args) {

        if (args == null || args.length != Tournament.ARGS_LENGTH) {
            System.err.println(Tournament.INVALID_INPUT_MSG);
            return;
        }

        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.build(args[Tournament.RENDERER_INDEX_ARGS]);

        PlayerFactory playerFactory = new PlayerFactory();
        Player[] players = {playerFactory.build(args[Tournament.FIRST_PLAYER_INDEX_ARGS]), playerFactory.build(args[Tournament.SECOND_PLAYER_INDEX_ARGS])};

        if (renderer == null || players[0] == null || players[1] == null || !isPositiveInteger(args[Tournament.NUM_OF_TOURNAMENT_INDEX])) {
            System.err.println(Tournament.INVALID_INPUT_MSG);
            return;
        }
        Tournament tournament = new Tournament(Integer.parseInt(args[Tournament.NUM_OF_TOURNAMENT_INDEX]), renderer, players);
        tournament.playTournament();
    }

    public static boolean isPositiveInteger(String str) {
        try {
            int num = Integer.parseInt(str);
            return num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Tournament(int rounds, Renderer renderer, Player[] players) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
        gameResults.put(players[0], 0);
        gameResults.put(players[1], 0);
    }

    private void updateResults(int iteration, GameStatus gameStatus) {
        if (gameStatus == GameStatus.DRAW) {
            drawCounter++;
        } else if (gameStatus == GameStatus.O_WIN) {
            gameResults.put(players[iteration], gameResults.get(players[iteration]) + 1);
        } else {
            gameResults.put(players[(iteration + 1) % 2], gameResults.get(players[(iteration + 1) % 2]) + 1);
        }
    }

    private void printTournamentResults() {
        System.out.printf("player 1 wins: %d%n", gameResults.get(players[0]));
        System.out.printf("player 2 wins: %d%n", gameResults.get(players[1]));
        System.out.printf("draws: %d%n", drawCounter);
    }

    public void playTournament() {
        for (int i = 0; i < rounds; i++) {
            Board board = new Board();
            renderer.renderBoard(board);
//            printTournamentResults();
            Game game = new Game(players[i % 2], players[(i + 1) % 2], renderer, board);
            game.run();
            updateResults(i % 2, board.getGameStatus());
            printTournamentResults();
        }
    }

}

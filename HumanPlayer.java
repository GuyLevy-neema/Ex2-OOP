package player;

import main.Board;
import main.Mark;

import java.util.Scanner;

class HumanPlayer implements Player {
    private final Scanner scanner = new Scanner(System.in);

    private String[] getCoordsInput() {
        boolean validInput = false;
        String[] coordsArr = null;

        while (!validInput) {
//            printPlayerMark();
            System.out.println("please insert coordinates in that format: x,y \nthan click enter.");
            String coords = scanner.nextLine();
            coordsArr = coords.split(",");
            if (coordsArr.length == 2) validInput = true;
            else {
                System.out.println("Input not valid");
            }
        }
        return coordsArr;
    }

    @Override
    public void playTurn(Board board, Mark playerMark) {
        String[] coordsArr = getCoordsInput();
        int row = Integer.parseInt(coordsArr[0]);
        int col = Integer.parseInt(coordsArr[1]);
        while (!board.putMark(playerMark, row, col)){
            System.out.println("Your coordinates are out of bound or already in use");
            coordsArr = getCoordsInput();
            row = Integer.parseInt(coordsArr[0]);
            col = Integer.parseInt(coordsArr[1]);
        }
        System.out.println(String.format("The coordinates you chose are: %d, %d", row, col));

    }

}

package battleship;

import java.util.Scanner;

public class Battlefield {

    String[][] battlefield = new String[11][11];
    char column = 'A';

    String playerName;

    public Battlefield(String playerName) {
        this.playerName = playerName;
    }

    protected void createBattlefield() {
        for (int i = 0; i < this.battlefield.length; i++) {
            for (int j = 0; j < this.battlefield[i].length; j++) {
                if (i == 0 && j > 0) {
                    this.battlefield[0][j] = Integer.toString(j);
                } else if (j == 0 && i > 0) {
                    this.battlefield[i][0] = Character.toString(this.column++);
                } else {
                    this.battlefield[i][j] = "~";
                }
            }
        }
        this.battlefield[0][0] = " ";
    }

    protected void showBattlefield() {
        for (String[] strings : this.battlefield) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    protected boolean putShip(int[][] coordinates, int size) {
        if (Validations.validateCoordinates(coordinates)) {
            return false;
        }
        if (coordinates[0][0] == coordinates[1][0]) {
            return putShipHorizontally(coordinates, size);
        } else if ((coordinates[0][1] == coordinates[1][1])) {
            return putShipUpright(coordinates, size);
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
    }

    private boolean putShipUpright(int[][] coordinates, int size) {
        int[] swap = Parsing.sortCoordinates(new int[]{coordinates[0][0], coordinates[1][0]});
        if (Validations.validateSizeShip(swap, size) || Validations.validatePositionVertical(this.battlefield, new int[]{swap[0], coordinates[0][1]}, size)) {
            return false;
        }
        for (; swap[0] <= swap[1]; swap[0]++) {
            this.battlefield[swap[0]][coordinates[0][1]] = "O";
        }
        return true;
    }

    private boolean putShipHorizontally(int[][] coordinates, int size) {
        int[] swap = Parsing.sortCoordinates(new int[]{coordinates[0][1], coordinates[1][1]});
        if (Validations.validateSizeShip(swap, size) || Validations.validatePositionHorizontal(this.battlefield, new int[]{coordinates[0][0], swap[0]}, size)) {
            return false;
        }
        for (; swap[0] <= swap[1]; swap[0]++) {
            this.battlefield[coordinates[0][0]][swap[0]] = "O";
        }
        return true;
    }

    protected void takeShot(int[][] shotCoordinates, Battlefield foggedBattlefield) {
        if (!Validations.validateCoordinates(shotCoordinates)) {
            switch (this.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]]) {
                case "O" -> {
                    foggedBattlefield.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]] = "X";
                    this.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]] = "X";
                    if (deathCheck(new int[]{shotCoordinates[0][0], shotCoordinates[0][1]})) {
                        System.out.println("You sank a ship!");
                    } else {
                        System.out.println("You hit a ship!");
                    }
                }
                case "X" -> System.out.println("You hit a ship!");
                case "M" -> System.out.println("You missed.");
                default -> {
                    foggedBattlefield.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]] = "M";
                    this.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]] = "M";
                    System.out.println("You missed.");
                }
            }
        }
    }

    protected boolean checkEndOfGame() {
        for (String[] strings : this.battlefield) {
            for (String string : strings) {
                if (string.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean deathCheck(int[] shotCoordinates) {
        for (int k = 0; k < shotCoordinates.length - 1; k++) {
            for (int i = shotCoordinates[k] - 1; i <= shotCoordinates[k] + 1; i++) {
                for (int j = shotCoordinates[k + 1] - 1; j <= shotCoordinates[k + 1] + 1; j++) {
                    if (j < 1 || i < 1 || i > battlefield.length - 1 || j > battlefield.length - 1) {
                        continue;
                    }
                    if (this.battlefield[i][j].equals("O")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected static void clearConsole(Scanner scanner) {
        do {
            System.out.println("Press Enter and pass the move to another player");
        } while (scanner.nextLine() == null);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

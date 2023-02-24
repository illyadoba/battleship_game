package battleship;

public class Battlefield {

    String[][] battlefield = new String[11][11];
    char column = 'A';

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

    protected boolean takeShot(int[][] shotCoordinates) {
        if (Validations.validateCoordinates(shotCoordinates)) {
            return false;
        }
        if (this.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]].equals("O")) {
            this.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]] = "X";
            this.showBattlefield();
            System.out.println("You hit a ship!");
        } else {
            this.battlefield[shotCoordinates[0][0]][shotCoordinates[0][1]] = "M";
            this.showBattlefield();
            System.out.println("You missed!");
        }
        return true;
    }
}

package battleship;

public class Validations {
    public static boolean validateSizeShip(int[] coordinates, int size) {
        if (coordinates[1] - coordinates[0] + 1 != size) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return true;
        }
        return false;
    }

    public static boolean validateCoordinates(int[][] coordinates) {
        for (int[] coordinate : coordinates) {
            for (int number : coordinate) {
                if (number < 1 || number > 10) {
                    System.out.println("Error! Wrong ship location! Try again:");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validatePositionVertical(String[][] battlefield, int[] coordinates, int size) {
        for (int m = 0; m < size; m++) {
            for (int k = 0; k < coordinates.length - 1; k++) {
                for (int i = coordinates[k] - 1 + m; i <= coordinates[k] + 1 + m; i++) {
                    for (int j = coordinates[k + 1] - 1; j <= coordinates[k + 1] + 1; j++) {
                        if (j < 1 || i < 1 || i > battlefield.length - 1 || j > battlefield.length - 1) {
                            continue;
                        }
                        if (battlefield[i][j].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean validatePositionHorizontal(String[][] battlefield, int[] coordinates, int size) {
        for (int m = 0; m < size; m++) {
            for (int k = 0; k < coordinates.length - 1; k++) {
                for (int i = coordinates[k] - 1; i <= coordinates[k] + 1; i++) {
                    for (int j = coordinates[k + 1] - 1 + m; j <= coordinates[k + 1] + 1 + m; j++) {
                        if (j < 1 || i < 1 || i > battlefield.length - 1 || j > battlefield.length - 1) {
                            continue;
                        }
                        if (battlefield[i][j].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

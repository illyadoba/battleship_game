package battleship;

public class Parsing {
    public static int[][] parseToCoordinates(String coordinates) {
        String[] parts = coordinates.split(" ");
        return convertToInt(parts);
    }

    public static int[] sortCoordinates(int[] coordinates) {
        if (coordinates[0] > coordinates[1]) {
            int m = coordinates[0];
            coordinates[0] = coordinates[1];
            coordinates[1] = m;
            return coordinates;
        }
        return coordinates;
    }

    private static int[][] convertToInt(String[] parts) {
        int[][] coordinates = new int[2][2];
        for (int i = 0; i < parts.length; i++) {
            String upperCasePart = parts[i].toUpperCase();
            char substring1 = upperCasePart.charAt(0);
            coordinates[i][0] = yConvertCharToInt(substring1);
            String substring2 = upperCasePart.substring(1);
            try {
                coordinates[i][1] = Integer.parseInt(substring2);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return coordinates;
    }

    private static int yConvertCharToInt(char y) {
        switch (y) {
            case 'A' -> {
                return 1;
            }
            case 'B' -> {
                return 2;
            }
            case 'C' -> {
                return 3;
            }
            case 'D' -> {
                return 4;
            }
            case 'E' -> {
                return 5;
            }
            case 'F' -> {
                return 6;
            }
            case 'G' -> {
                return 7;
            }
            case 'H' -> {
                return 8;
            }
            case 'I' -> {
                return 9;
            }
            case 'J' -> {
                return 10;
            }
            default -> {
                return 0;
            }
        }
    }
}

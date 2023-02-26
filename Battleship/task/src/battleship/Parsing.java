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
            coordinates[i][0] = upperCasePart.charAt(0) - '@';
            String substring2 = upperCasePart.substring(1);
            try {
                coordinates[i][1] = Integer.parseInt(substring2);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return coordinates;
    }
}

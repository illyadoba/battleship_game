package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        Battlefield player1 = new Battlefield();
        player1.createBattlefield();
        player1.showBattlefield();

        Ship[] ships = new Ship[5];
        ships[0] = new Ship(5, "Aircraft Carrier");
        ships[1] = new Ship(4, "Battleship");
        ships[2] = new Ship(3, "Submarine ");
        ships[3] = new Ship(3, "Cruiser");
        ships[4] = new Ship(2,
                "Destroyer");

        insertCoordinates(scanner, ships, player1);

    }

    public static void insertCoordinates(Scanner scanner, Ship[] ships, Battlefield player) {
        int i = 0;
        while (!ships[4].status) {
            System.out.printf("Enter the coordinates of the %s (%d cells):", ships[i].name, ships[i].cells);
            while (true) {
                if (player.putShip(Parsing.parseToCoordinates(scanner.nextLine()), ships[i].cells)) {
                    break;
                }
            }
            player.showBattlefield();
            ships[i].status = true;
            i++;
        }
    }
}
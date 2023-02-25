package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        Battlefield player1 = new Battlefield("Player 1");
        player1.createBattlefield();

        Battlefield fogPlayer1 = new Battlefield("fog for player 1");
        fogPlayer1.createBattlefield();

        Battlefield player2 = new Battlefield("Player 2");
        player2.createBattlefield();

        Battlefield fogPlayer2 = new Battlefield("for for player 2");
        fogPlayer2.createBattlefield();

        Ship[] ships = new Ship[5];
        ships[0] = new Ship(5, "Aircraft Carrier");
        ships[1] = new Ship(4, "Battleship");
        ships[2] = new Ship(3, "Submarine ");
        ships[3] = new Ship(3, "Cruiser");
        ships[4] = new Ship(2,
                "Destroyer");

        arrangeTheShips(scanner, ships, player1);
        arrangeTheShips(scanner, ships, player2);

        while (true) {
            takeMove(fogPlayer2, player1, player2, scanner);
            if (player2.checkEndOfGame()) {
                break;
            }
            Battlefield.clearConsole(scanner);
            takeMove(fogPlayer1, player2, player1, scanner);
            if (player1.checkEndOfGame()) {
                break;
            }
            Battlefield.clearConsole(scanner);
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static void arrangeTheShips(Scanner scanner, Ship[] ships, Battlefield player) {
        System.out.printf("%s,place your ships on the game field\n", player.playerName);
        player.showBattlefield();
        int i = 0;
        while (i < 5) {
            System.out.printf("Enter the coordinates of the %s (%d cells):", ships[i].name, ships[i].cells);
            while (true) {
                if (player.putShip(Parsing.parseToCoordinates(scanner.nextLine()), ships[i].cells)) {
                    break;
                }
            }
            player.showBattlefield();
            i++;
        }
        Battlefield.clearConsole(scanner);
    }

    private static void takeMove(Battlefield enemyFogBattlefield, Battlefield player, Battlefield enemy, Scanner scanner) {
        enemyFogBattlefield.showBattlefield();
        System.out.println("---------------------");
        player.showBattlefield();
        System.out.printf("%s,it's your turn:\n", player.playerName);
        enemy.takeShot(Parsing.parseToCoordinates(scanner.nextLine()), enemyFogBattlefield);
    }
}
package battleship;

public class Ship extends Battlefield {
    boolean status;
    int cells;
    String name;

    protected Ship(int cells, String name) {
        this.cells = cells;
        this.status = false;
        this.name = name;
    }
}

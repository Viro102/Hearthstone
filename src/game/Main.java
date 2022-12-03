package game;

public class Main {
    public static void main(String[] args) {
        var g = new Game();
        for (int i = 0; i < 23; i++) {
            g.startTurn();
            g.endTurn();
        }
        var p = new Player[2];
        p[0] = g.getPlayer(0);
        p[1] = g.getPlayer(1);

        Logging.printStateTables(p);
        g.playACard(p[0], "Murloc");
        Logging.printStateTables(p);
        g.playACard(p[0], "Commander");
        Logging.printStateTables(p);
    }
}

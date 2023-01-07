package game;

public class Logging {
    private Logging() {
    }

    public static void printStateAll(Player[] players) {
        Logging.printStatePlayers(players);

        System.out.println("\nPlayer 1's hand:");
        players[0].getHand().printHand();

        System.out.println("\nPlayer 2's hand:");
        players[1].getHand().printHand();

        System.out.println("\nPlayer 1's table:");
        players[0].getBoard().printBoard();

        System.out.println("\nPlayer 2's table:");
        players[1].getBoard().printBoard();
    }

    public static void printStatePlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println("\nPlayer " + (i + 1) + ": "
                    + players[i].getHp() + " HP, "
                    + players[i].getMana() + " Mana");
        }
    }

    public static void printStateBoard(Player[] player) {
        System.out.println("\nPlayer 1's table:");
        player[0].getBoard().printBoard();

        System.out.println("\nPlayer 2's table:");
        player[1].getBoard().printBoard();
    }

    public static void printStateHand(Player[] player) {
        System.out.println("\nPlayer 1's hand:");
        player[0].getHand().printHand();

        System.out.println("\nPlayer 2's hand:");
        player[1].getHand().printHand();
    }
}

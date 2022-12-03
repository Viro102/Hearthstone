package game;

public class Logging {
    private Logging() {
    }

    public static void printStateAll(Player[] player) {
        System.out.println("\n\nPlayer 1: " + player[0].getHp() + " HP, " + player[0].getMana() + " Mana");
        System.out.println("Player 2: " + player[1].getHp() + " HP, " + player[1].getMana() + " Mana");

        System.out.println("\nPlayer 1's hand:");
        player[0].getHand().printHand();

        System.out.println("\nPlayer 2's hand:");
        player[1].getHand().printHand();

        System.out.println("\nPlayer 1's table:");
        player[0].getTable().printTable();

        System.out.println("\nPlayer 2's table:");
        player[1].getTable().printTable();
    }

    public static void printStatePlayers(Player[] player) {
        System.out.println("\n\nPlayer 1: " + player[0].getHp() + " HP, " + player[0].getMana() + " Mana");
        System.out.println("Player 2: " + player[1].getHp() + " HP, " + player[1].getMana() + " Mana");
    }

    public static void printStateTables(Player[] player) {
        System.out.println("\nPlayer 1's table:");
        player[0].getTable().printTable();

        System.out.println("\nPlayer 2's table:");
        player[1].getTable().printTable();
    }

    public static void printStateHands(Player[] player) {
        System.out.println("\nPlayer 1's hand:");
        player[0].getHand().printHand();

        System.out.println("\nPlayer 2's hand:");
        player[1].getHand().printHand();
    }
}

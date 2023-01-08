/**
 * Loggging class
 * 
 * Utility class for printing out the state of the game.
 * 
 * @author Adam Virostek
 */

package game;

public class Logging {
    /**
     * Prints the state of the game
     * 
     * @param players Array of players
     */
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

    /**
     * Prints the state of the players (HP and mana)
     * 
     * @param players Array of players
     */
    public static void printStatePlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println("\nPlayer " + (i + 1) + ": "
                    + players[i].getHp() + " HP, "
                    + players[i].getMana() + " Mana");
        }
    }

    /**
     * Prints the state of the board (cards currently in play)
     * 
     * @param players Array of players
     */

    public static void printStateBoard(Player[] players) {
        System.out.println("\nPlayer 1's table:");
        players[0].getBoard().printBoard();

        System.out.println("\nPlayer 2's table:");
        players[1].getBoard().printBoard();
    }

    /**
     * Prints the state of the hand (cards currently in hand)
     * 
     * @param players Array of players
     */
    public static void printStateHand(Player[] players) {
        System.out.println("\nPlayer 1's hand:");
        players[0].getHand().printHand();

        System.out.println("\nPlayer 2's hand:");
        players[1].getHand().printHand();
    }

    private Logging() {
    }
}

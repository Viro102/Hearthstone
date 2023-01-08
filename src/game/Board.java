
/**
 * Board class
 * 
 * Contains the cards that are currently on the board
 * 
 * @author Adam Virostek
 *  
 */
package game;

public class Board {
    private Card[] cards;

    public Board() {
        this.cards = new Card[5];
    }

    /**
     * Adds a card to the board
     * 
     * @param card Card to be added
     */
    public void addCard(Card card) {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] == null) {
                this.cards[i] = card;
                break;
            }
        }
    }

    /**
     * Removes a card from the board
     * 
     * @param card Card to be removed
     */
    public void removeCard(Card card) {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] == card) {
                this.cards[i] = null;
                break;
            }
        }
    }

    /**
     * Removes a card from the board
     * 
     * @param i Index of the card to be removed
     */
    public void removeCard(int i) {
        this.cards[i] = null;
    }

    /**
     * Gets a card from the board
     * 
     * @param i Index of the card to be returned
     * @return Card at the specified index
     */
    public Card getCard(int i) {
        return this.cards[i];
    }

    /**
     * Gets all the cards on the board
     * 
     * @return Array of cards on the board
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Checks if the board is full
     * 
     * @return True if the board is full, false otherwise
     */
    public boolean isFull() {
        for (Card card : this.cards) {
            if (card == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the board
     */
    public void printBoard() {
        for (Card card : this.cards) {
            if (card == null) {
                continue;
            }
            System.out.println(
                    card.getName() + " " + card.getType()
                            + " \nHP: " + card.getHp()
                            + " DMG: " + card.getDamage()
                            + " COST: " + card.getCost());
            System.out.println();
        }
    }
}

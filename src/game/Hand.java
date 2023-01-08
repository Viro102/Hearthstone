/**
 * Hand class
 * 
 * Contains information of cards on the hand of the player
 * 
 * @author Adam Virostek
 */

package game;

public class Hand {
    private static final int MAX_CARDS = 5;
    private Card[] cards;

    public Hand() {
        this.cards = new Card[5];
    }

    /**
     * Adds a card to the hand
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
     * Removes a card from the hand
     * 
     * @param card Card to be removed
     */
    public void removeCard(int i) {
        this.cards[i] = null;
    }

    /**
     * Removes a card from the hand
     * 
     * @param i Index of the card to be removed
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
     * Gets a card from the hand
     * 
     * @param i Index of the card to be returned
     * @return Card at the specified index
     */
    public Card getCard(int i) {
        return this.cards[i];
    }

    /**
     * Gets all cards from the hand
     * 
     * @return Array of cards
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Gets the number of cards in the hand
     * 
     * @return Number of cards in the hand
     */
    public int getNumOfCards() {
        int count = 0;
        for (Card card : this.cards) {
            if (card != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if the hand is full
     * 
     * @return True if the hand is full, false otherwise
     */
    public boolean isFull() {
        return this.getNumOfCards() == MAX_CARDS;
    }

    /**
     * Prints the hand
     */
    public void printHand() {
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

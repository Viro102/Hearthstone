package game;

public class Hand {
    private static final int MAX_CARDS = 5;
    private Card[] cards;

    public Hand() {
        this.cards = new Card[5];
    }

    public void addCard(Card card) {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] == null) {
                this.cards[i] = card;
                break;
            }
        }
    }

    public void removeCard(int i) {
        this.cards[i] = null;
    }

    public void removeCard(Card card) {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] == card) {
                this.cards[i] = null;
                break;
            }
        }
    }

    public Card getCard(int i) {
        return this.cards[i];
    }

    public Card[] getCards() {
        return this.cards;
    }

    public int getNumOfCards() {
        int count = 0;
        for (Card card : this.cards) {
            if (card != null) {
                count++;
            }
        }
        return count;
    }

    public boolean isFull() {
        return this.getNumOfCards() == MAX_CARDS;
    }

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

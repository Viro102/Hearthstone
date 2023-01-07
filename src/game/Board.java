package game;

public class Board {
    private Card[] cards;

    public Board() {
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

    public void removeCard(Card card) {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] == card) {
                this.cards[i] = null;
                break;
            }
        }
    }

    public void removeCard(int i) {
        this.cards[i] = null;
    }

    public Card getCard(int i) {
        return this.cards[i];
    }

    public Card[] getCards() {
        return this.cards;
    }

    public boolean isFull() {
        for (Card card : this.cards) {
            if (card == null) {
                return false;
            }
        }
        return true;
    }

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

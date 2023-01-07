package game;

import gui.Slot;

public class Board {
    private Card[] cards;
    private Slot[] slots;

    public Board(String position) {
        this.cards = new Card[5];
        this.slots = new Slot[5];
        for (int i = 0; i < this.slots.length; i++) {
            if (position.equals("Top")) {
                this.slots[i] = new Slot(i, 30 + (i * 170), 40);
            } else if (position.equals("Bottom")) {
                this.slots[i] = new Slot(i, 30 + (i * 170), 280);
            } else {
                System.out.println("Invalid position");
                break;
            }
        }
    }

    public void addCards(Card card) {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] == null) {
                this.cards[i] = card;
                break;
            }
        }
    }

    public void removeCards(Card card) {
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

    public void setSlotAsFull(int i) {
        this.slots[i].setFree(false);
    }

    public void setSlotAsFree(int i) {
        this.slots[i].setFree(true);
    }

    public Slot[] getSlots() {
        return this.slots;
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

package game;

import gui.Slot;

public class Hand {
    private static final int MAX_CARDS = 5;
    private Card[] cards;
    private Slot[] slots;

    public Hand() {
        this.cards = new Card[5];
        this.slots = new Slot[5];
        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i] = new Slot(i, 30 + (i * 170), 530);
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

    public void removeCards(int i) {
        this.cards[i] = null;
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

    public void setSlotAsFull(int i) {
        this.slots[i].setFree(false);
    }

    public void setSlotAsFree(int i) {
        this.slots[i].setFree(true);
    }

    public Slot[] getSlots() {
        return this.slots;
    }

    public Slot getSlot(int i) {
        return this.slots[i];
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

package game;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private ArrayList<Card> cards;

    public Table() {
        this.cards = new ArrayList<>();
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }

    public void removeCards(Card card) {
        this.cards.remove(card);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public Card getCard(Card card) {
        if (this.cards.contains(card)) {
            return card;
        } else {
            return null;
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void printTable() {
        for (Card card : this.cards) {
            System.out.println(
                    card.getName() + " " + card.getType()
                            + " \nHP: " + card.getHp()
                            + " DMG: " + card.getDamage()
                            + " COST: " + card.getCost());
            System.out.println();
        }
    }
}

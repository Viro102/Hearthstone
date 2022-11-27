import java.util.ArrayList;
import java.util.List;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
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
        return this.cards.get(this.cards.indexOf(card));
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void printHand() {
        for (Card card : this.cards) {
            System.out.println(card.getName());
        }
    }
}

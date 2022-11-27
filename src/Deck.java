import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int MAX_NUMBER_OF_CARDS = 20;
    private int numberOfCards;
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        this.numberOfCards = 0;
        for (int i = 0; i < MAX_NUMBER_OF_CARDS; i++) {
            this.addCards(new Card("Fireball", "Spell", 0, 6, 0));
        }
    }

    public void addCards(Card card) {
        this.numberOfCards++;
        this.cards.add(card);
    }

    public void removeCards(Card card) {
        this.numberOfCards--;
        this.cards.remove(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getNumberOfCards() {
        return this.numberOfCards;
    }
}

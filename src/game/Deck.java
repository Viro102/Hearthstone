package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deck {
    private int numberOfCards;
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        this.numberOfCards = 0;
        this.makeDeck("res/cards.txt");
    }

    public void addCard(Card card) {
        this.numberOfCards++;
        this.cards.add(card);
    }

    public void removeCard(Card card) {
        this.numberOfCards--;
        this.cards.remove(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public int getNumOfCards() {
        return this.numberOfCards;
    }

    public String getNumOfCardsString() {
        return String.valueOf(this.numberOfCards);
    }

    private Deck makeDeck(String filename) {
        var inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        try (var sc = new Scanner(inputStream)) {
            while (sc.hasNext()) {
                int buffType = 0;
                String name = sc.next();
                String type = sc.next();
                if (type.equals("buff")) {
                    buffType = sc.nextInt();
                }
                int hp = sc.nextInt();
                int damage = sc.nextInt();
                int cost = sc.nextInt();
                if (buffType == 0) {
                    this.addCard(new Card(name, type, hp, damage, cost));
                } else {
                    this.addCard(new Card(name, type, buffType, hp, damage, cost));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}

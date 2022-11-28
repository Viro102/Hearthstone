package game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deck {
    // private static final int MAX_NUMBER_OF_CARDS = 20;
    private int numberOfCards;
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        this.numberOfCards = 0;
        this.makeDeck("./src/resources/cards.txt");
    }

    private Deck makeDeck(String filename) {
        try (var sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String name = sc.next();
                String type = sc.next();
                int hp = sc.nextInt();
                int damage = sc.nextInt();
                int cost = sc.nextInt();
                System.out.println(name + " " + type + " " + hp + " " + damage + " " + cost);
                this.addCards(new Card(name, type, hp, damage, cost));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
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

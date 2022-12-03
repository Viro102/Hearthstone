package game;

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
        this.makeDeck("/resources/cards.txt");
    }

    private Deck makeDeck(String filename) {
        var inputStream = this.getClass().getResourceAsStream(filename);
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
                    // System.out.println(name + " " + type + " " + hp + " " + damage + " " + cost);
                    this.addCards(new Card(name, type, hp, damage, cost));
                } else {
                    // System.out.println(name + " " + type + " " + buffType + " " + hp + " " +
                    // damage + " " + cost);
                    this.addCards(new Card(name, type, buffType, hp, damage, cost));
                }
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

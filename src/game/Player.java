package game;

import java.util.List;
import java.util.Random;

public class Player {
    private static final int MAX_CARDS = 5;
    private static final int MAX_MANA = 10;
    private int hp;
    private int mana;
    private boolean turn;
    private Hand hand;
    private Table table;
    private Deck deck;
    private Random random;

    public Player(int hp, int mana, Deck deck, Table table) {
        this.hp = hp;
        this.mana = mana;
        this.turn = false;
        this.hand = new Hand();
        this.deck = deck;
        this.table = table;

        this.random = new Random();
    }

    public Card drawCard() {
        if (this.hand.getCards().size() < MAX_CARDS) {
            if (!this.deck.getCards().isEmpty()) {
                var drawnCard = this.deck.getCards().get(random.nextInt(MAX_CARDS));
                this.hand.addCards(drawnCard);
                this.deck.removeCards(drawnCard);
                return drawnCard;
            } else {
                System.out.println("You have no more cards in your deck");
                return null;
            }
        }
        System.out.println("Your hand is full");
        return null;
    }

    public boolean playCard(Card card) {
        if (this.mana >= card.getCost()) {
            this.table.addCards(card);
            this.hand.removeCards(card);
            this.mana -= card.getCost();
            return true;
        } else {
            System.out.println("Not enough mana");
            return false;
        }
    }

    public void removeCard(Card card) {
        this.hand.removeCards(card);
    }

    public Table getTable() {
        return this.table;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Hand getHand() {
        return this.hand;
    }

    public Card getCard(int i) {
        return this.hand.getCard(i);
    }

    public Card getCard(Card card) {
        return this.hand.getCard(card);
    }

    public int getHp() {
        return this.hp;
    }

    public String getHpString() {
        return String.valueOf(this.hp);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        if (MAX_MANA >= mana) {
            this.mana = mana;
        } else {
            this.mana = MAX_MANA;
            System.out.println("You have reached the maximum mana");
        }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public List<Card> getCards() {
        return this.hand.getCards();
    }

}

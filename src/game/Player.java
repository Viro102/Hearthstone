package game;

import java.util.Random;

public class Player {
    private static final int MAX_MANA = 10;
    private String archetype;
    private int hp;
    private int id;
    private int mana;
    private int currentMaxMana;
    private boolean turn;
    private Hand hand;
    private Board board;
    private Deck deck;
    private Random random;

    public Player(int hp, int id, String archetype) {
        this.archetype = archetype;
        this.hp = hp;
        this.id = id;
        this.mana = 0;
        this.currentMaxMana = 0;
        this.turn = false;
        this.hand = new Hand();
        this.deck = new Deck();
        this.board = new Board();
        this.random = new Random();
    }

    public Card drawCard() {
        if (!this.hand.isFull()) {
            if (!this.deck.getCards().isEmpty()) {
                var drawnCard = this.deck.getCard(this.random.nextInt(this.deck.getNumOfCards()));
                this.hand.addCard(drawnCard);
                this.deck.removeCard(drawnCard);
                return drawnCard;
            } else {
                System.out.println("You have no more cards in your deck");
                return null;
            }
        }
        System.out.println("Your hand is full");
        return null;
    }

    public Card playCard(int i) {
        var card = this.hand.getCard(i);
        if (this.board.isFull()) {
            System.out.println("Your board is full");
            return null;
        }
        if (this.mana >= card.getCost()) {
            this.board.addCard(card);
            this.hand.removeCard(i);
            this.mana -= card.getCost();
            return card;
        } else {
            System.out.println("Not enough mana");
            return null;
        }
    }

    public void removeCard(Card card) {
        this.hand.removeCard(card);
    }

    public Board getBoard() {
        return this.board;
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

    public int getHp() {
        return this.hp;
    }

    public int getId() {
        return this.id;
    }

    public String getHpString() {
        return String.valueOf(this.hp);
    }

    public int getMana() {
        return this.mana;
    }

    public String getManaString() {
        return String.valueOf(this.mana);
    }

    public int getCurrentMaxMana() {
        return this.currentMaxMana;
    }

    public String getArchetype() {
        return this.archetype;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMana(int mana) {
        if (MAX_MANA >= mana) {
            this.mana = mana;
        } else {
            this.mana = MAX_MANA;
            System.out.println("You have reached the maximum mana");
        }
        this.currentMaxMana = this.mana;
    }

    public boolean isTurn() {
        return this.turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}

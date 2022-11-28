package game;

public class Player {
    private int hp;
    private int mana;
    private boolean turn;
    private Hand hand;
    private Table table;
    private Deck deck;

    public Player(int hp, int mana, Deck deck, Table table) {
        this.hp = hp;
        this.mana = mana;
        this.turn = false;
        this.hand = new Hand();
        this.deck = deck;
        this.table = table;
    }

    public void drawCard() {
        if (this.hand.getCards().size() < 10) {
            this.hand.addCards(this.deck.getCards().get(0));
            this.deck.removeCards(this.deck.getCards().get(0));
        }
    }

    public void playCard(Card card) {
        if (this.mana >= card.getCost()) {
            this.table.addCards(card);
            this.hand.removeCards(card);
            this.mana -= card.getCost();
        } else {
            System.out.println("Not enough mana");
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

    public Card getCard(int i) {
        return this.hand.getCard(i);
    }

    public Card getCard(Card card) {
        return this.hand.getCard(card);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

}

public class Player {
    private int hp;
    private int mana;
    private boolean turn;
    private Hand hand;
    private Table table;

    public Player(int hp, int mana) {
        this.hp = hp;
        this.mana = mana;
        this.turn = false;
        this.hand = new Hand();
        this.table = new Table();
    }

    public void drawCard(Deck deck) {
        if (this.hand.getCards().size() < 10) {
            this.hand.addCards(deck.getCards().get(0));
            deck.removeCards(deck.getCards().get(0));
        }
    }

    public Table getTable() {
        return table;
    }

    public Hand getHand() {
        return this.hand;
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

package game;

public class Game {
    private Player[] players;
    private Table table1;
    private Table table2;
    private Deck deck1;
    private Deck deck2;
    private boolean gameOver;

    public Game() {
        this.table1 = new Table();
        this.table2 = new Table();
        this.deck1 = new Deck();
        this.deck2 = new Deck();
        this.players = new Player[2];
        this.players[0] = new Player(30, 0, deck1, table1);
        this.players[1] = new Player(30, 0, deck2, table2);
        this.gameOver = false;
    }

    public Player getPlayer(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Player index must be 0 or 1");
        }
        return this.players[i];
    }

    public void startTurn() {
        if (this.players[0].isTurn()) {
            this.players[0].drawCard();
            this.players[0].setMana(this.players[0].getMana() + 1);
        } else {
            this.players[1].drawCard();
            this.players[1].setMana(this.players[0].getMana() + 1);
            this.players[1].setTurn(true);
        }
    }

    public void endTurn() {
        if (this.players[0].isTurn()) {
            this.players[0].setTurn(false);
            this.players[1].setTurn(true);
        } else {
            this.players[0].setTurn(true);
            this.players[1].setTurn(false);
        }
        // Logging.printStateAll(this.players);
    }

    private void specialCardBuff(Player player, Card card) {
        if (card.getType().equals("buff")) {
            int buffAmout = card.getBuffAmount();
            for (var k : player.getTable().getCards()) {
                k.setHp(k.getHp() + buffAmout);
                k.setDamage(k.getDamage() + buffAmout);
            }
        }
    }

    public Card playACard(Player player, String card) {
        for (var c : player.getCards()) {
            if (c.getName().equals(card)) {
                if (player.playCard(c)) {
                    this.specialCardBuff(player, c);
                    return c;
                } else {
                    return null;
                }
            }

        }
        System.out.println("Card not found");
        return null;
    }

    public Card playACard(Player player, Card card) {
        var success = player.playCard(card);
        if (success) {
            this.specialCardBuff(player, card);
            return card;
        } else {
            return null;
        }
    }

    public Card selectCardInPlay(Player player, Card card) {
        return player.getTable().getCard(card);
    }

    public void attack(Player player, Card source, Card target) {
        target.setHp(target.getHp() - selectCardInPlay(player, source).getDamage());
    }

    public void attackFace(Player player, Card card, Player target) {
        for (var c : player.getTable().getCards()) {
            if (c.getType().equals("taunt")) {
                System.out.println("Taunt card in play, cannot attack face");
                return;
            }
        }
        target.setHp(target.getHp() - selectCardInPlay(player, card).getDamage());
        this.isGameOver();
    }

    public void isGameOver() {
        if (this.players[0].getHp() <= 0) {
            System.out.println("Player 2 wins");
            this.gameOver = true;
        } else if (this.players[1].getHp() <= 0) {
            System.out.println("Player 1 wins");
            this.gameOver = true;
        }
    }
}
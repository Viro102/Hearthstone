package game;

public class Game {
    private Player player1;
    private Player player2;
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
        this.player1 = new Player(30, 0, deck1, table1);
        this.player2 = new Player(30, 0, deck2, table2);
        this.gameOver = false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void startTurn() {
        if (player1.isTurn()) {
            player1.drawCard();
            player1.setMana(player1.getMana() + 1);
        } else {
            player2.drawCard();
            player2.setMana(player1.getMana() + 1);
            player2.setTurn(true);
        }
    }

    public void endTurn() {
        if (player1.isTurn()) {
            player1.setTurn(false);
            player2.setTurn(true);
        } else {
            player1.setTurn(true);
            player2.setTurn(false);
        }
    }

    public Card playACard(Player player, Card card) {
        player.playCard(card);
        return card;
    }

    public Card selectCardInPlay(Player player, Card card) {
        return player.getTable().getCard(card);
    }

    public void attack(Player player, Card source, Card target) {
        target.setHp(target.getHp() - selectCardInPlay(player, source).getDamage());
    }

    public void attackFace(Player player, Card card, Player target) {
        target.setHp(target.getHp() - selectCardInPlay(player, card).getDamage());
        this.isGameOver();
    }

    public void isGameOver() {
        if (player1.getHp() <= 0) {
            System.out.println("Player 2 wins");
            this.gameOver = true;
        } else if (player2.getHp() <= 0) {
            System.out.println("Player 1 wins");
            this.gameOver = true;
        }
    }
}

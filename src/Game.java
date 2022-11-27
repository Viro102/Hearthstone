public class Game {
    private Player player1;
    private Player player2;
    private Deck deck1;
    private Deck deck2;
    private boolean gameOver;

    public Game() {
        this.player1 = new Player(30, 0);
        this.player2 = new Player(30, 0);
        this.deck1 = new Deck();
        this.deck2 = new Deck();
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
            player1.drawCard(deck1);
            player1.setMana(player1.getMana() + 1);
        } else {
            player2.drawCard(deck2);
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
        var playedCard = player.getHand().getCard(card);
        if (player.getMana() >= playedCard.getCost()) {
            player.getHand().removeCards(playedCard);
            player.getTable().addCards(playedCard);
            return playedCard;
        } else {
            System.out.println("Not enough mana");
            return null;
        }
    }

    public Card selectCard(Player player, Card card) {
        return player.getTable().getCard(card);
    }

    public void attack(Player player, Card source, Card target) {
        target.setHp(target.getHp() - selectCard(player, source).getDamage());
    }

    public void attackFace(Player player, Card card, Player target) {
        target.setHp(target.getHp() - selectCard(player, card).getDamage());
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

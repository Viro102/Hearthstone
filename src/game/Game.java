package game;

import gui.Panel;
import gui.Frame;

public class Game {
    private static final int PLAYER_MAX_HP = 20;
    private Player[] players;
    private Panel panel;
    private Card selectedCard;

    public Game() {
        this.players = new Player[2];
        this.panel = new Panel(this);
        new Frame(panel);
    }

    public void startGame(String player1, String player2) {
        this.players[0] = new Player(PLAYER_MAX_HP, 0, player1);
        this.players[1] = new Player(PLAYER_MAX_HP, 1, player2);
        this.players[0].setTurn(true);
        this.players[0].setMana(10);
        this.players[1].setMana(10);

        for (int i = 0; i < 3; i++) {
            this.players[0].drawCard();
            this.players[1].drawCard();
        }
        this.panel.update();
        Logging.printStateAll(players);
    }

    public void endTurn() {
        var currentPlayer = this.getOnTurnPlayer();
        var offPlayer = this.getOffTurnPlayer();

        currentPlayer.setTurn(false);
        offPlayer.setTurn(true);
        offPlayer.setMana(offPlayer.getCurrentMaxMana() + 1);
        offPlayer.drawCard();

        for (var card : currentPlayer.getBoard().getCards()) {
            if (card != null) {
                card.setHasAttacked(false);
            }
        }
        this.panel.update();
        System.out.println("Turn ended");
        Logging.printStateHand(players);
    }

    private void specialCard(Player player, Card card) {
        if (card.getType().equals("buff")) {
            int buffAmout = card.getBuffAmount();
            for (var c : player.getBoard().getCards()) {
                if (c == null) {
                    continue;
                }
                c.setHp(c.getHp() + buffAmout);
                c.setDamage(c.getDamage() + buffAmout);
            }
        }

        if (card.getType().equals("spell")) {
            // TODO
        }

        if (card.getType().equals("aoe")) {
            // TODO
        }
    }

    public void playACard(Player player, int i) {
        var card = player.playCard(i);
        if (card != null) {
            this.specialCard(player, card);
            this.panel.update();
        }
    }

    public void selectCard(Player player, int i) {
        if (selectedCard == player.getBoard().getCard(i)) {
            this.selectedCard = null;
            this.panel.removeGlow(i);
        } else {
            this.selectedCard = player.getBoard().getCard(i);
            this.panel.paintGlow(i);
        }
    }

    public void attack(int i) {
        if (this.selectedCard == null) {
            System.out.println("No card selected");
            return;
        }
        this.panel.removeGlow(i);
        var opponent = this.getOffTurnPlayer();
        var currentPlayer = this.getOnTurnPlayer();
        var targetCard = opponent.getBoard().getCard(i);

        if (!this.selectedCard.hasAttacked()) {
            targetCard.setHp(targetCard.getHp() - this.selectedCard.getDamage());
            this.selectedCard.setHp(this.selectedCard.getHp() - targetCard.getDamage());
            this.selectedCard.setHasAttacked(true);
            if (targetCard.getHp() <= 0) {
                opponent.getBoard().removeCard(i);
            }
            if (this.selectedCard.getHp() <= 0) {
                currentPlayer.getBoard().removeCard(i);
            }
        } else {
            System.out.println("Card has already attacked");
        }

        this.selectedCard = null;
        this.panel.update();
    }

    private int indexOf(Card[] arr, Card card) {

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == card) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    public void attackFace() {
        var target = this.getOffTurnPlayer();
        var attacker = this.getOnTurnPlayer();

        if (this.selectedCard == null) {
            System.out.println("No card selected");
            return;
        }

        this.panel.removeGlow(this.indexOf(attacker.getBoard().getCards(), this.selectedCard));

        for (var c : target.getBoard().getCards()) {
            if (c == null) {
                continue;
            }
            if (c.getType().equals("taunt")) {
                System.out.println("Taunt card in play, cannot attack face");
                return;
            }
        }
        if (!this.selectedCard.hasAttacked()) {
            target.setHp(target.getHp() - this.selectedCard.getDamage());
            this.selectedCard.setHasAttacked(true);
        } else {
            System.out.println("Card has already attacked");
        }

        this.selectedCard = null;
        this.panel.update();
        this.isGameOver();
    }

    public void isGameOver() {
        if (this.players[0].getHp() <= 0) {
            System.out.println("Player 2 wins");
        } else if (this.players[1].getHp() <= 0) {
            System.out.println("Player 1 wins");
        }
    }

    public Player getOnTurnPlayer() {
        for (var player : this.players) {
            if (player.isTurn()) {
                return player;
            }
        }
        System.out.println("No player on turn");
        return null;
    }

    public Player getOffTurnPlayer() {
        for (var player : this.players) {
            if (!player.isTurn()) {
                return player;
            }
        }
        System.out.println("No player off turn");
        return null;
    }

    public boolean isSelected() {
        return this.selectedCard != null;
    }

    public Player[] getPlayers() {
        return this.players;
    }
}

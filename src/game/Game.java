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
        this.players[0] = new Player(PLAYER_MAX_HP, 0);
        this.players[1] = new Player(PLAYER_MAX_HP, 1);

        this.players[0].setTurn(true);
        this.players[0].setMana(1);
        this.players[1].setMana(10);

        this.panel = new Panel(this);
        new Frame(panel);
    }

    public void startGame() {
        for (int i = 0; i < 3; i++) {
            int x = i * 170;
            var card = this.players[0].drawCard();
            card.setPosition(30 + x, 530);
        }
        this.panel.test();
        Logging.printStateAll(players);
    }

    public void endTurn() {
        for (var pl : this.players) {
            if (pl.isTurn()) {
                pl.setTurn(false);
                continue;
            }
            pl.setTurn(true);
            pl.setMana(pl.getMana() + 1);
            pl.drawCard();
            this.panel.test();
        }
        System.out.println("Turn ended");
        Logging.printStatePlayers(players);
    }

    private void specialCardBuff(Player player, Card card) {
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
    }

    public void playACard(Player player, int i) {
        var card = player.playCard(i);
        if (card != null) {
            this.specialCardBuff(player, card);
            this.panel.test();
        }
    }

    public void selectCard(Player player, int i) {
        if (selectedCard == player.getBoard().getCard(i)) {
            this.selectedCard = null;
            this.panel.removeGlow(i);
        } else {
            this.selectedCard = player.getBoard().getCard(i);
            this.panel.renderGlow(i);
        }
    }

    public void attack(int i) {
        if (this.selectedCard == null) {
            System.out.println("No card selected");
            return;
        }
        var player = this.getOffTurnPlayer();
        var target = player.getBoard().getCard(i);
        target.setHp(target.getHp() - this.selectedCard.getDamage());
    }

    public void attackFace() {
        var target = this.getOffTurnPlayer();
        var attacker = this.getOnTurnPlayer();

        if (this.selectedCard == null) {
            System.out.println("No card selected");
            return;
        }

        for (var c : attacker.getBoard().getCards()) {
            if (c == null) {
                continue;
            }
            if (c.getType().equals("taunt")) {
                System.out.println("Taunt card in play, cannot attack face");
                return;
            }
        }
        target.setHp(target.getHp() - this.selectedCard.getDamage());
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

    public Player[] getPlayers() {
        return this.players;
    }
}

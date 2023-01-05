package game;

import gui.*;

public class Game {
    private Player[] players;
    private Mouse mouse;
    private Panel panel;

    public Game() {
        this.panel = new Panel();
        new Frame(panel);

        this.players = new Player[2];
        this.players[0] = new Player(30, 0, new Deck(), new Table());
        this.players[1] = new Player(30, 0, new Deck(), new Table());
        this.players[0].setTurn(true);

        this.mouse = new Mouse(this);
        panel.addMouseListener(mouse);
        panel.addMouseMotionListener(mouse);
    }

    public void click() {
        var p = this.mouse.getPointer();
    }

    public void startTurn() {
        if (this.players[0].isTurn()) {
            this.players[0].drawCard();
            this.players[0].setMana(this.players[0].getMana() + 1);
        } else {
            this.players[1].drawCard();
            this.players[1].setMana(this.players[0].getMana() + 1);
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
                    this.panel.drawCard(c);
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
            this.panel.drawCard(card);
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
        } else if (this.players[1].getHp() <= 0) {
            System.out.println("Player 1 wins");
        }
    }

    public Player getPlayer(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Player index must be 0 or 1");
        }
        return this.players[i];
    }
}

package game;

import gui.*;

public class Game {
    private Player[] players;
    private Panel panel;

    public Game() {
        this.players = new Player[2];
        this.players[0] = new Player(30, 0, new Deck(), new Table());
        this.players[1] = new Player(30, 0, new Deck(), new Table());

        this.players[0].setTurn(true);

        this.panel = new Panel(this);
        new Frame(panel);
    }

    public void startGame() {
        for (int i = 0; i < 3; i++) {
            int x = i * 170;
            var card = this.players[0].drawCard();
            card.setPosition(20 + x, 500);
            this.panel.renderCard(card);
        }
        this.players[0].setMana(1);
        Logging.printStateAll(players);
    }

    public void endTurn() {
        for (var pl : this.players) {
            if (pl.isTurn()) {
                pl.setTurn(false);
                continue;
            }
            var drawnCard = pl.drawCard();
            this.panel.renderCard(drawnCard);
            pl.setMana(pl.getMana() + 1);
            pl.setTurn(true);
        }
        System.out.println("Turn ended");
        Logging.printStatePlayers(players);
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
                    this.panel.renderCard(c);
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
            this.panel.renderCard(card);
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

    public Player getOnTurnPlayer() {
        for (var pl : this.players) {
            if (pl.isTurn()) {
                return pl;
            }
        }
        return null;
    }
}

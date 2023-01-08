/**
 * Game class
 * 
 * Main class of the game, contains all the logic and methods to play the game
 * 
 * @author Adam Virostek
 */

package game;

import gui.Panel;
import gui.Frame;

import javax.swing.JOptionPane;

public class Game {
    private static final int PLAYER_MAX_HP = 20;
    private Player[] players;
    private Panel panel;
    private Card selectedCard;
    private int turnCounter;

    /**
     * Game constructor
     * 
     * Creates a new game with two players and initializes the GUI
     */
    public Game() {
        this.players = new Player[2];
        this.panel = new Panel(this);
        this.selectedCard = null;
        this.turnCounter = 0;
        new Frame(this.panel);
    }

    /**
     * Starts the game
     * 
     * @param player1 Name of the first player
     * @param player2 Name of the second player
     */
    public void startGame(String player1, String player2) {
        this.players[0] = new Player(PLAYER_MAX_HP, 0, player1);
        this.players[1] = new Player(PLAYER_MAX_HP, 1, player2);
        this.players[0].setTurn(true);
        this.players[0].setMana(1);
        for (int i = 0; i < 3; i++) {
            this.players[0].drawCard();
            this.players[1].drawCard();
        }
        this.panel.update();
        Logging.printStateAll(this.players);
    }

    /**
     * Ends the turn
     */
    public void endTurn() {
        if (this.turnCounter >= 30) {
            JOptionPane.showMessageDialog(this.panel, "Game over, no one won");
            System.exit(0);
        }
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
        if (this.selectedCard != null) {
            this.selectedCard = null;
        }
        this.panel.removeGlow();
        this.panel.update();
        this.turnCounter++;
        System.out.println("Turn ended");
        Logging.printStateHand(this.players);
    }

    /**
     * Plays a card from the specified player's hand
     * 
     * @param player Player who plays the card
     * @param i      Index of the card in the player's hand
     */

    public void playACard(Player player, int i) {
        var card = player.playCard(i);
        if (card != null) {
            this.specialCard(player, card);
            this.panel.update();
        }
    }

    /**
     * Selects a card on the board of the specified player
     * 
     * @param player Player whose board is being selected
     * @param i      Index of the card on the board
     */
    public void selectCardBoard(Player player, int i) {
        if (this.selectedCard == player.getBoard().getCard(i)) {
            this.selectedCard = null;
            this.panel.removeGlow();
        } else if (this.selectedCard != null) {
            JOptionPane.showMessageDialog(this.panel, "Can't select two cards at once");
        }
        if (this.selectedCard == null) {
            this.selectedCard = player.getBoard().getCard(i);
            this.panel.addGlow(i, "board");
        }
    }

    /**
     * Selects a card on the hand of the specified player
     * 
     * @param player Player whose hand is being selected
     * @param i      Index of the card on the hand
     */
    public void selectCardHand(Player player, int i) {
        if (this.selectedCard == player.getHand().getCard(i)) {
            this.selectedCard = null;
            this.panel.removeGlow();
        } else {
            this.selectedCard = player.getHand().getCard(i);
            this.panel.addGlow(i, "hand");
        }
    }

    /**
     * The player on turn attacks the specified card on the opponent's board, the
     * card which is attacking must be selected beforehand
     * 
     * @param i Index of the card on the opponent's board
     */
    public void attack(int i) {
        if (this.selectedCard == null) {
            System.out.println("No card selected");
            return;
        }
        this.panel.removeGlow();
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
                currentPlayer.getBoard().removeCard(this.selectedCard);
            }
        } else {
            JOptionPane.showMessageDialog(this.panel, "Card has already attacked!");
        }

        this.selectedCard = null;
        this.panel.update();
    }

    /**
     * The player on turn attacks the opponent's hero, the card which is attacking
     * must be selected beforehand
     */
    public void attackFace() {
        var target = this.getOffTurnPlayer();
        var attacker = this.getOnTurnPlayer();

        if (this.selectedCard == null) {
            System.out.println("No card selected");
            return;
        }

        this.panel.removeGlow();

        for (var c : target.getBoard().getCards()) {
            if (c == null) {
                continue;
            }
            if (c.getType().equals("taunt")) {
                JOptionPane.showMessageDialog(this.panel, "Taunt card in play, cannot attack hero");
                return;
            }
        }
        if (!this.selectedCard.hasAttacked()) {
            target.setHp(target.getHp() - this.selectedCard.getDamage());
            this.selectedCard.setHasAttacked(true);
            if (this.selectedCard.getType().equals("spell")) {
                attacker.getBoard().removeCard(this.selectedCard);
            }
        } else {
            JOptionPane.showMessageDialog(this.panel, "Card has already attacked!");
        }

        this.selectedCard = null;
        this.panel.update();
        this.isGameOver();
    }

    /**
     * Gets the player on turn
     * 
     * @return Player on turn
     */
    public Player getOnTurnPlayer() {
        for (var player : this.players) {
            if (player == null) {
                continue;
            }
            if (player.isTurn()) {
                return player;
            }
        }
        System.out.println("Error");
        return null;
    }

    /**
     * Gets the player off turn
     * 
     * @return Player off turn
     */
    public Player getOffTurnPlayer() {
        for (var player : this.players) {
            if (player == null) {
                continue;
            }
            if (!player.isTurn()) {
                return player;
            }
        }
        System.out.println("Error");
        return null;
    }

    /**
     * Gets the players
     * 
     * @return Players
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * @return is a card selected
     */
    public boolean isSelected() {
        return this.selectedCard != null;
    }

    /**
     * Method to check if the game is over
     */
    public void isGameOver() {
        if (this.players[0].getHp() <= 0) {
            JOptionPane.showMessageDialog(this.panel, "Player 2 (" + this.players[1].getArchetype() + ") wins");
        } else if (this.players[1].getHp() <= 0) {
            JOptionPane.showMessageDialog(this.panel, "Player 1 (" + this.players[0].getArchetype() + ") wins");
        }
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
            return;
        }

        if (card.getType().equals("spell")) {
            this.getOnTurnPlayer().getHand().removeCard(card);
            this.selectedCard = card;
            return;
        }

        if (card.getType().equals("aoe")) {
            this.getOnTurnPlayer().getHand().removeCard(card);
            for (var target : this.getOffTurnPlayer().getBoard().getCards()) {
                if (target == null) {
                    continue;
                }
                target.setHp(target.getHp() - card.getDamage());
                if (target.getHp() <= 0) {
                    this.getOffTurnPlayer().getBoard().removeCard(target);
                }
            }
        }
    }
}

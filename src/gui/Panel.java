package gui;

import game.Card;
import game.Game;
import game.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private static final int END_TURN_BUTTON_POSITION_X = 910;
    private static final int END_TURN_BUTTON_POSITION_Y = 350;
    private static final int HEROES_POSITION_X = 900;
    private static final int FIRST_HERO_POSITION_Y = 450;
    private static final int SECOND_HERO_POSITION_Y = 20;
    private static final int PANEL_WIDTH = 1400;
    private static final int PANEL_HEIGHT = 750;
    private static final String DEFAULT_FONT = "Arial";
    private BufferedImage board;
    private BufferedImage hero;
    private BufferedImage deck;
    private BufferedImage endTurnButton;
    private Rectangle endTurnButtonHitbox;
    private Rectangle heroHitbox;
    private ArrayList<Card> cards;
    private Slot[] slotsHand;
    private Slot[][] slotsBoard;
    private Mouse mouse;
    private Game game;

    public Panel(Game game) {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);
        this.game = game;
        this.init();
    }

    private void init() {
        this.mouse = new Mouse(this);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        this.cards = new ArrayList<>();
        this.slotsHand = new Slot[5];
        for (int i = 0; i < this.slotsHand.length; i++) {
            this.slotsHand[i] = new Slot(30 + (i * 170), 530);
        }

        this.slotsBoard = new Slot[2][5];
        for (int i = 0; i < this.slotsBoard.length; i++) {
            for (int j = 0; j < this.slotsBoard[i].length; j++) {
                int x = Math.abs(i - 1);
                this.slotsBoard[x][j] = new Slot(30 + (j * 170), 40 + (i * 240));
            }
        }

        try {
            this.board = ImageIO.read(new File("./src/resources/table.png"));
            this.deck = ImageIO.read(new File("./src/resources/deck.png"));

            this.hero = ImageIO.read(new File("./src/resources/hero.png"));
            this.heroHitbox = new Rectangle(hero.getWidth(), hero.getHeight());
            this.heroHitbox.setLocation(HEROES_POSITION_X, SECOND_HERO_POSITION_Y);

            this.endTurnButton = ImageIO.read(new File("./src/resources/endTurnButton.png"));
            this.endTurnButtonHitbox = new Rectangle(endTurnButton.getWidth(), endTurnButton.getHeight());
            this.endTurnButtonHitbox.setLocation(END_TURN_BUTTON_POSITION_X, END_TURN_BUTTON_POSITION_Y);
        } catch (Exception e) {
            System.out.println("Images not found");
        }
    }

    public void click() {
        if (this.endTurnButtonHitbox.contains(this.mouse.getPointer())) {
            this.execute("endTurn");
            return;
        }

        if (this.heroHitbox.contains(this.mouse.getPointer())) {
            System.out.println("Hero clicked");
            this.execute("attackFace");
            return;
        }

        this.checkSlots();
    }

    private void checkSlots() {
        for (int i = 0; i < slotsHand.length; i++) {
            if (slotsHand[i].getShape().contains(this.mouse.getPointer()) && !slotsHand[i].isFree()) {
                System.out.println("Slot Hand: " + i + " clicked");
                this.execute("playACard", i);
                return;
            }
        }

        for (int i = 0; i < slotsBoard.length; i++) {
            for (int j = 0; j < slotsBoard[i].length; j++) {
                if (slotsBoard[i][j].getShape().contains(this.mouse.getPointer()) && !slotsBoard[i][j].isFree()) {
                    System.out.println("Slot " + i + " " + j + " clicked");
                    this.execute("selectCard", j);
                    return;
                }
            }
        }
    }

    private void execute(String select) {
        try {
            if (this.game != null) {
                Method method = this.game.getClass().getMethod(select);
                method.invoke(this.game);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
            e.printStackTrace();
        }
    }

    private void execute(String select, int param) {
        try {
            if (this.game != null) {
                Method method = this.game.getClass().getMethod(select, Player.class, Integer.TYPE);
                var player = this.game.getOnTurnPlayer();
                method.invoke(this.game, player, param);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
            e.printStackTrace();
        }
    }

    public void renderGlow(int i) {
        // slotsBoard[i].setGlow(true);
        this.repaint();
    }

    public void removeGlow(int i) {
        // slotsBoard[i].setGlow(false);
        this.repaint();
    }

    public void test() {
        var player = this.game.getOnTurnPlayer();
        int id = player.getId();
        var playerCardsHand = player.getHand().getCards();
        var playerCardsBoard = player.getBoard().getCards();

        for (int i = 0; i < playerCardsHand.length; i++) {
            var cardToPaint = playerCardsHand[i];
            if (cardToPaint != null) {
                this.slotsHand[i].setFree(false);
                int x = this.slotsHand[i].getX();
                int y = this.slotsHand[i].getY();
                cardToPaint.setPosition(x, y);
                this.cards.add(cardToPaint);
            } else {
                this.slotsHand[i].setFree(true);
            }
        }

        for (int i = 0; i < playerCardsBoard.length; i++) {
            if (playerCardsBoard[i] != null) {
                this.slotsBoard[id][i].setFree(false);
                int x = this.slotsBoard[id][i].getX();
                int y = this.slotsBoard[id][i].getY();
                playerCardsBoard[i].setPosition(x, y);
                this.cards.add(playerCardsBoard[i]);
            } else {
                this.slotsBoard[id][i].setFree(true);
            }
        }
        this.repaint();
    }

    private void eraseCardHand(Card card) {
        if (this.cards.contains(card)) {
            this.cards.remove(card);
            this.repaint();
        } else {
            System.out.println("Card not found");
        }
    }

    private void paintUI(Graphics2D g2d) {
        var currentPlayer = this.game.getOnTurnPlayer();
        var opponentPlayer = this.game.getOffTurnPlayer();
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 40));

        g2d.drawImage(this.board, 20, 10, this);
        g2d.drawImage(this.endTurnButton, END_TURN_BUTTON_POSITION_X, END_TURN_BUTTON_POSITION_Y, this);

        if (currentPlayer == null) {
            return;
        }
        g2d.drawImage(this.hero, HEROES_POSITION_X, FIRST_HERO_POSITION_Y, this);
        g2d.drawString("HP: " + currentPlayer.getHpString(), HEROES_POSITION_X + 220, 650);
        g2d.drawString("MANA: " + currentPlayer.getManaString(), HEROES_POSITION_X + 220, 700);

        g2d.drawImage(this.hero, HEROES_POSITION_X, SECOND_HERO_POSITION_Y, this);
        g2d.drawString("HP: " + opponentPlayer.getHpString(), HEROES_POSITION_X + 220, 50);
        g2d.drawString("MANA: " + opponentPlayer.getManaString(), HEROES_POSITION_X + 220, 100);

        g2d.drawImage(this.deck, 1150, 200, this);
        g2d.drawString("Cards: " + currentPlayer.getDeck().getNumOfCardsString(), 1170, 200);

        g2d.drawString("Player: " + currentPlayer.getId(), 600, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        paintUI(g2d);

        for (var card : this.cards) {
            if (card == null) {
                continue;
            }
            int attPosY = card.getY() + card.getHeight();
            int attPosX = card.getX() + 2;

            int hpPosY = card.getY() + card.getHeight();
            int hpPosX = card.getX() + card.getWidth() - 18;

            int costPosY = card.getY() + 25;
            int costPosX = card.getX() + 2;

            int namePosY = card.getY() + 100;
            int namePosX = card.getX() + 40;

            int typePosY = card.getY() + 125;
            int typePosX = card.getX() + 45;

            g2d.setColor(Color.PINK);
            g2d.fillRect(card.getX(), card.getY(), card.getWidth(), card.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(DEFAULT_FONT, Font.BOLD, 30));
            g2d.drawString(String.valueOf(card.getHp()), hpPosX, hpPosY);
            g2d.drawString(String.valueOf(card.getDamage()), attPosX, attPosY);
            g2d.drawString(String.valueOf(card.getCost()), costPosX, costPosY);
            g2d.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 20));
            g2d.drawString(card.getName(), namePosX, namePosY);
            g2d.setFont(new Font(DEFAULT_FONT, Font.ITALIC, 17));
            g2d.drawString(card.getType(), typePosX, typePosY);
        }

        for (int i = 0; i < this.slotsBoard.length; i++) {
            for (int j = 0; j < this.slotsBoard[i].length; j++) {
                if (this.slotsBoard[i][j].isFree()) {
                    continue;
                }

                if (this.slotsBoard[i][j].isGlow()) {
                    g2d.setColor(Color.YELLOW);
                    g2d.fill(this.slotsBoard[i][j].getShape());
                }
            }
        }
    }
}

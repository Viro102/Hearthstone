package gui;

import game.Card;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private static final int WIDTH = 1400;
    private static final int HEIGHT = 750;
    private transient ArrayList<Card> cards;
    private Rectangle[] environment;

    public Panel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);

        this.environment = new Rectangle[4];
        this.cards = new ArrayList<>();

        this.init();
    }

    public void drawCard(Card card) {
        this.cards.add(card);
        this.repaint();
    }

    private void init() {
        GUI gui = new GUI();
        this.environment[0] = gui.createHero();
        this.environment[1] = gui.createDeck();
        this.environment[2] = gui.createTable();
        this.environment[3] = gui.createEndTurnButton();
    }

    public void eraseCard(Card card) {
        if (this.cards.contains(card)) {
            this.cards.remove(card);
            this.repaint();
        } else {
            System.out.println("Card not found");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        for (var obj : this.environment) {
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(obj.x, obj.y, obj.width, obj.height);
        }

        for (var card : this.cards) {
            int attPosY = card.getY() + card.getHeight();
            int attPosX = card.getX();

            int hpPosY = card.getY() + card.getHeight();
            int hpPosX = card.getX() + card.getWidth() - 15;

            int costPosY = card.getY() + 25;
            int costPosX = card.getX();

            int namePosY = card.getY() + 100;
            int namePosX = card.getX() + 40;

            g2d.setColor(Color.PINK);
            g2d.fillRect(card.getX(), card.getY(), card.getWidth(), card.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString(String.valueOf(card.getHp()), hpPosX, hpPosY);
            g2d.drawString(String.valueOf(card.getDamage()), attPosX, attPosY);
            g2d.drawString(String.valueOf(card.getCost()), costPosX, costPosY);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.drawString(card.getName(), namePosX, namePosY);
        }
    }
}

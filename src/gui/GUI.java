package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    private static final int WIDTH_CARD = 150;
    private static final int HEIGHT_CARD = 200;
    private static final int WIDTH_TABLE = 850;
    private static final int HEIGHT_TABLE = 500;
    private static final int WIDTH_HERO = 200;
    private static final int HEIGHT_HERO = 200;
    private static final int WIDTH_BUTTON = 150;
    private static final int HEIGHT_BUTTON = 50;

    private Rectangle endTurnButton;
    private Rectangle card;
    private Rectangle deck;
    private Rectangle table;
    private Rectangle hero;

    public Rectangle createDeck() {
        this.deck = new Rectangle(1200, 200, WIDTH_CARD, HEIGHT_CARD);
        return this.deck;
    }

    public Rectangle createTable() {
        this.table = new Rectangle(70, 100, WIDTH_TABLE, HEIGHT_TABLE);
        return this.table;
    }

    public Rectangle createHero() {
        this.hero = new Rectangle(1000, 500, WIDTH_HERO, HEIGHT_HERO);
        return this.hero;
    }

    public Rectangle createEndTurnButton() {
        this.endTurnButton = new Rectangle(950, 320, WIDTH_BUTTON, HEIGHT_BUTTON);
        return this.endTurnButton;
    }

    public Rectangle createCard() {
        this.card = new Rectangle(0, 0, WIDTH_CARD, HEIGHT_CARD);
        return this.card;
    }

    public void moveCard(Rectangle card, int x, int y) {
        card.setLocation(x, y);
    }
}

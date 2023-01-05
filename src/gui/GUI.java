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

    public Rectangle createDeck() {
        return new Rectangle(1200, 200, WIDTH_CARD, HEIGHT_CARD);
    }

    public Rectangle createTable() {
        return new Rectangle(70, 30, WIDTH_TABLE, HEIGHT_TABLE);
    }

    public Rectangle createHero() {
        return new Rectangle(1000, 500, WIDTH_HERO, HEIGHT_HERO);
    }

    public Rectangle createEndTurnButton() {
        return new Rectangle(950, 250, WIDTH_BUTTON, HEIGHT_BUTTON);
    }

    public Rectangle createCard() {
        return new Rectangle(0, 0, WIDTH_CARD, HEIGHT_CARD);
    }

    public void moveCard(Rectangle card, int x, int y) {
        card.setLocation(x, y);
    }
}

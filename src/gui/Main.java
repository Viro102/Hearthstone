package gui;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        var p = new Panel();
        new Frame(p);
        var gui = new GUI();
        p.draw(gui.createDeck());
        p.draw(gui.createHero());
        p.draw(gui.createEndTurnButton());
        p.draw(gui.createTable());

        Rectangle[] cardsBot = new Rectangle[] { gui.createCard(), gui.createCard(), gui.createCard(),
                gui.createCard(),
                gui.createCard() };

        Rectangle[] cardsTop = new Rectangle[] { gui.createCard(), gui.createCard(), gui.createCard(), gui.createCard(),
                gui.createCard() };

        int x = 0;
        for (int i = 0; i < 5; i++) {
            gui.moveCard(cardsBot[i], 100 + x, 300);
            gui.moveCard(cardsTop[i], 100 + x, 50);
            x += 160;
        }

        for (var card : cardsBot) {
            p.draw(card);
        }

        for (var card : cardsTop) {
            p.draw(card);
        }
    }
}

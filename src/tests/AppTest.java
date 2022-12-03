package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import game.Game;

public class AppTest {
    @Test
    public void test() {
        var g = new Game();
        var p1 = g.getPlayer(0);
        var p2 = g.getPlayer(1);
        g.startTurn();
        assertTrue(p2.isTurn());
        assertFalse(p1.isTurn());
        assertEquals(1, p2.getMana());
        assertEquals(0, p1.getMana());
        var card = g.playACard(p2, p2.getCard(0));
        assertNotEquals(null, card);
        int hpBeforeAttack = p1.getHp();
        g.attackFace(p2, card, p1);
        assertEquals(hpBeforeAttack - card.getDamage(), p1.getHp());
        g.endTurn();
        assertTrue(p1.isTurn());
        assertFalse(p2.isTurn());
        g.startTurn();
        assertEquals(1, p1.getMana());
        assertEquals(1, p2.getMana());
    }

    @Test
    public void startGame() {
        var g = new Game();
        assertNotEquals(null, g.getPlayer(0));
        assertNotEquals(null, g.getPlayer(1));
        var p1 = g.getPlayer(0);
        var p2 = g.getPlayer(1);
        assertNotEquals(null, p1.getDeck());
        assertNotEquals(null, p2.getDeck());
        assertNotEquals(null, p1.getTable());
        assertNotEquals(null, p2.getTable());
        assertEquals(30, p1.getHp());
        assertEquals(30, p2.getHp());
        assertEquals(0, p1.getMana());
        assertEquals(0, p2.getMana());
    }

}

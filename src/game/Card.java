/**
 * Card class
 * 
 * Contains all the information about a card
 * 
 * @author Adam Virostek
 */

package game;

import java.awt.Rectangle;

public class Card {
    private String name;
    private String type;
    private int buffAmount;
    private int hp;
    private int damage;
    private int cost;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean hasAttacked;

    /**
     * Card constructor
     * 
     * Creates a new card with the specified name, type, buff amount, hp, damage,
     * and cost
     * 
     * @param name
     * @param type
     * @param buffAmount
     * @param hp
     * @param damage
     * @param cost
     */
    public Card(String name, String type, int buffAmount, int hp, int damage, int cost) {
        this.name = name;
        this.type = type;
        this.buffAmount = buffAmount;
        this.hp = hp;
        this.damage = damage;
        this.cost = cost;
        this.init();
    }

    /**
     * Card constructor
     * 
     * Creates a new card with the specified name, type, hp, damage, and cost
     * 
     * @param name
     * @param type
     * @param hp
     * @param damage
     * @param cost
     */
    public Card(String name, String type, int hp, int damage, int cost) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.damage = damage;
        this.cost = cost;
        this.init();
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getHp() {
        return this.hp;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getCost() {
        return this.cost;
    }

    public int getBuffAmount() {
        return this.buffAmount;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Rectangle getShape() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public boolean hasAttacked() {
        return this.hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    private void init() {
        this.x = 0;
        this.y = 0;
        this.width = 150;
        this.height = 200;
    }
}

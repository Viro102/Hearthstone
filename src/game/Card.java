package game;

public class Card {
    private String name;
    private String type;
    private int buffAmount;
    private int hp;
    private int damage;
    private int cost;

    public Card(String name, String type, int buffAmount, int hp, int damage, int cost) {
        this.name = name;
        this.type = type;
        this.buffAmount = buffAmount;
        this.hp = hp;
        this.damage = damage;
        this.cost = cost;
    }

    public Card(String name, String type, int hp, int damage, int cost) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.damage = damage;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getBuffAmount() {
        return buffAmount;
    }
}

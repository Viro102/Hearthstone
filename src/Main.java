public class Main {
    public static void main(String[] args) {
        var g = new Game();
        var p1 = g.getPlayer1();
        var p2 = g.getPlayer2();
        g.startTurn();
        var card = g.playACard(p2, p2.getHand().getCard(0));
        g.attackFace(p2, card, p1);
    }
}

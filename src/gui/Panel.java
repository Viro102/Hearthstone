package gui;

import game.Card;
import game.Game;
import game.Player;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Panel class
 *
 * <p>Main panel of the game, uses javax.swing.JPanel to create a panel,
 * contains all the GUI elements and methods to display the game.
 *
 * @author Adam Virostek
 */
public class Panel extends JPanel {
  private static final int END_TURN_BUTTON_POSITION_X = 910;
  private static final int END_TURN_BUTTON_POSITION_Y = 350;
  private static final int HEROES_POSITION_X = 900;
  private static final int FIRST_HERO_POSITION_Y = 460;
  private static final int SECOND_HERO_POSITION_Y = 20;
  private static final int PANEL_WIDTH = 1400;
  private static final int PANEL_HEIGHT = 750;
  private static final String DEFAULT_FONT = "Arial";
  private final Game game;
  private ImageIcon[] images;
  private Rectangle endTurnButtonHitbox;
  private Rectangle heroHitbox;
  private ArrayList<Card> cardsHand;
  private ArrayList<Card> cardsBoard;
  private Slot[] slotsHand;
  private Slot[][] slotsBoard;
  private Mouse mouse;

  /**
   * Panel constructor
   *
   * <p>Initializes the panel and sets the game to be displayed in it.
   *
   * @param game Game to be displayed in the panel
   */
  public Panel(Game game) {
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.setBackground(Color.LIGHT_GRAY);
    this.setVisible(true);
    this.game = game;
    this.init();
  }

  /**
   * Method which is called when user clicks on the panel,
   * uses the mouse class to get the position of the click.
   */
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

  /**
   * Adds glow to the slot where the card is located.
   *
   * @param i     Index of the card
   * @param where Where the card is located (hand or board)
   */
  public void addGlow(int i, String where) {
    var currentPlayer = this.game.getOnTurnPlayer();
    if (where.equals("hand")) {
      this.slotsHand[i].setGlow(true);
    } else if (where.equals("board")) {
      this.slotsBoard[currentPlayer.getId()][i].setGlow(true);
    }
    this.repaint();
  }

  /**
   * Removes glow from all slots.
   */
  public void removeGlow() {
    for (int i = 0; i < this.slotsBoard.length; i++) {
      for (int j = 0; j < this.slotsBoard[i].length; j++) {
        this.slotsBoard[i][j].setGlow(false);
      }
    }

    for (int i = 0; i < this.slotsHand.length; i++) {
      this.slotsHand[i].setGlow(false);
    }
    this.repaint();
  }

  /**
   * Method which handles correct drawing of the cards,
   * it sets the appropriate position to the cards.
   */
  public void update() {
    this.cardsHand.clear();
    this.cardsBoard.clear();
    var players = this.game.getPlayers();
    var currentPlayer = this.game.getOnTurnPlayer();
    var playerCardsHand = currentPlayer.getHand().getCards();

    for (int i = 0; i < playerCardsHand.length; i++) {
      var cardToPaint = playerCardsHand[i];
      if (cardToPaint != null) {
        this.slotsHand[i].setFree(false);
        int x = this.slotsHand[i].getX();
        int y = this.slotsHand[i].getY();
        cardToPaint.setPosition(x, y);
        this.cardsHand.add(cardToPaint);
      } else {
        this.slotsHand[i].setFree(true);
      }
    }
    for (int j = 0; j < players.length; j++) {
      int id = players[j].getId();
      var playerCardsBoard = players[j].getBoard().getCards();

      for (int i = 0; i < playerCardsBoard.length; i++) {
        if (playerCardsBoard[i] != null) {
          this.slotsBoard[id][i].setFree(false);
          int x = this.slotsBoard[id][i].getX();
          int y = this.slotsBoard[id][i].getY();
          playerCardsBoard[i].setPosition(x, y);
          this.cardsBoard.add(playerCardsBoard[i]);
        } else {
          this.slotsBoard[id][i].setFree(true);
        }
      }
    }
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    var g2d = (Graphics2D) g;
    super.paintComponent(g2d);
    this.paintUI(g2d);
    var cards = new ArrayList<Card>();
    cards.addAll(this.cardsHand);
    cards.addAll(this.cardsBoard);
    this.paintCards(g2d, cards);

    for (int i = 0; i < this.slotsBoard.length; i++) {
      for (int j = 0; j < this.slotsBoard[i].length; j++) {
        if (!this.slotsBoard[i][j].isFree() && this.slotsBoard[i][j].isGlow()) {
          g2d.setColor(Color.YELLOW);
          g2d.draw(this.slotsBoard[i][j].getShape());
        }
      }
    }

    for (int i = 0; i < this.slotsHand.length; i++) {
      if (!this.slotsHand[i].isFree() && this.slotsHand[i].isGlow()) {
        g2d.setColor(Color.YELLOW);
        g2d.draw(this.slotsHand[i].getShape());
      }
    }
  }

  private void init() {
    this.mouse = new Mouse(this);
    this.addMouseListener(this.mouse);
    this.addMouseMotionListener(this.mouse);

    this.images = new ImageIcon[5];
    this.cardsHand = new ArrayList<>();
    this.cardsBoard = new ArrayList<>();
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
      this.images[0] = new ImageIcon(this.getClass().getClassLoader().getResource("res/board.png"));
      this.images[1] = new ImageIcon(this.getClass().getClassLoader().getResource("res/deck.png"));
      this.images[2] = new ImageIcon(this.getClass().getClassLoader().getResource("res/mage.png"));
      this.images[3] = new ImageIcon(this.getClass().getClassLoader().getResource("res/warrior.png"));
      this.images[4] = new ImageIcon(this.getClass().getClassLoader().getResource("res/endTurnButton.png"));

      this.heroHitbox = new Rectangle(this.images[2].getIconWidth(), this.images[2].getIconHeight());
      this.heroHitbox.setLocation(HEROES_POSITION_X, SECOND_HERO_POSITION_Y);

      this.endTurnButtonHitbox = new Rectangle(this.images[4].getIconWidth(), this.images[4].getIconHeight());
      this.endTurnButtonHitbox.setLocation(END_TURN_BUTTON_POSITION_X, END_TURN_BUTTON_POSITION_Y);
    } catch (Exception e) {
      System.out.println("Images not found");
    }
  }

  private void checkSlots() {
    var currentPlayer = this.game.getOnTurnPlayer();

    for (int i = 0; i < this.slotsHand.length; i++) {
      if (this.slotsHand[i].getShape().contains(this.mouse.getPointer()) && !this.slotsHand[i].isFree()) {
        this.execute("playACard", currentPlayer, i);
        return;
      }
    }

    for (int i = 0; i < this.slotsBoard.length; i++) {
      for (int j = 0; j < this.slotsBoard[i].length; j++) {
        if (this.slotsBoard[i][j]
            .getShape()
            .contains(this.mouse.getPointer()) && !this.slotsBoard[i][j].isFree()) {
          if (currentPlayer.getId() == i) {
            this.execute("selectCardBoard", currentPlayer, j);
            return;
          }
          if (this.game.isSelected()) {
            this.execute("attack", j);
            return;
          }
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

  private void execute(String methodName, int param1) {
    try {
      if (this.game != null) {
        Method method = this.game.getClass().getMethod(methodName, Integer.TYPE);
        method.invoke(this.game, param1);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getCause());
      e.printStackTrace();
    }
  }

  private void execute(String select, Player param1, int param2) {
    try {
      if (this.game != null) {
        Method method = this.game.getClass().getMethod(select, Player.class, Integer.TYPE);
        method.invoke(this.game, param1, param2);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getCause());
      e.printStackTrace();
    }
  }

  private void displayHero(int pos, ImageIcon hero, Player player, Graphics2D g2d) {
    if (pos == 0) {
      g2d.drawImage(hero.getImage(), HEROES_POSITION_X, FIRST_HERO_POSITION_Y, this);
      g2d.drawString("HP: " + player.getHpString(), HEROES_POSITION_X + 220, 650);
      g2d.drawString("MANA: " + player.getManaString(), HEROES_POSITION_X + 220, 700);
    } else {
      g2d.drawImage(hero.getImage(), HEROES_POSITION_X, SECOND_HERO_POSITION_Y, this);
      g2d.drawString("HP: " + player.getHpString(), HEROES_POSITION_X + 220, 50);
      g2d.drawString("MANA: " + player.getManaString(), HEROES_POSITION_X + 220, 100);
    }
  }

  private void paintUI(Graphics2D g2d) {
    var currentPlayer = this.game.getOnTurnPlayer();
    var opponentPlayer = this.game.getOffTurnPlayer();
    var board = this.images[0];
    var deck = this.images[1];
    var mage = this.images[2];
    var warrior = this.images[3];
    var endTurnButton = this.images[4];

    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 40));

    g2d.drawImage(board.getImage(), 20, 10, this);
    g2d.drawImage(endTurnButton.getImage(), END_TURN_BUTTON_POSITION_X, END_TURN_BUTTON_POSITION_Y, this);

    if (currentPlayer == null) {
      return;
    }

    if (currentPlayer.getArchetype().equals("mage")) {
      this.displayHero(0, mage, currentPlayer, g2d);
      this.displayHero(1, warrior, opponentPlayer, g2d);
    } else if (currentPlayer.getArchetype().equals("warrior")) {
      this.displayHero(0, warrior, currentPlayer, g2d);
      this.displayHero(1, mage, opponentPlayer, g2d);
    }

    g2d.drawImage(deck.getImage(), 1150, 200, this);
    g2d.drawString("Cards: " + currentPlayer.getDeck().getNumOfCardsString(), 1170, 200);
  }

  private void paintCards(Graphics2D g2d, ArrayList<Card> cards) {
    for (var card : cards) {
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
  }
}

package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deck class
 *
 * <p>Contains a list of cards and methods to manipulate them.
 *
 * @author Adam Virostek
 */
public class Deck {
  private final ArrayList<Card> cards;
  private int numberOfCards;

  /**
   * Deck constructor
   *
   * <p>Creates a new deck from the cards in the specified file.
   *
   * @param filename Name of the file containing the cards
   */
  public Deck(String filename) {
    this.cards = new ArrayList<>();
    this.numberOfCards = 0;
    this.makeDeck(filename);
  }

  /**
   * Adds a card to the deck.
   *
   * @param card Card to be added
   */
  public void addCard(Card card) {
    this.numberOfCards++;
    this.cards.add(card);
  }

  /**
   * Removes a card from the deck.
   *
   * @param card Card to be removed
   */
  public void removeCard(Card card) {
    this.numberOfCards--;
    this.cards.remove(card);
  }

  /**
   * Gets the list of cards in the deck.
   *
   * @return List of cards in the deck
   */
  public List<Card> getCards() {
    return this.cards;
  }

  /**
   * Gets a card from the deck.
   *
   * @param i Index of the card to be returned
   * @return Card at the specified index
   */
  public Card getCard(int i) {
    return this.cards.get(i);
  }

  /**
   * Gets the number of cards in the deck.
   *
   * @return Number of cards in the deck
   */
  public int getNumOfCards() {
    return this.numberOfCards;
  }

  public String getNumOfCardsString() {
    return String.valueOf(this.numberOfCards);
  }

  private Deck makeDeck(String filename) {
    var inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
    try (var sc = new Scanner(inputStream)) {
      while (sc.hasNext()) {
        int buffType = 0;
        String name = sc.next();
        String type = sc.next();
        if (type.equals("buff")) {
          buffType = sc.nextInt();
        }
        int hp = sc.nextInt();
        int damage = sc.nextInt();
        int cost = sc.nextInt();
        if (buffType == 0) {
          this.addCard(new Card(name, type, hp, damage, cost));
        } else {
          this.addCard(new Card(name, type, buffType, hp, damage, cost));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }
}

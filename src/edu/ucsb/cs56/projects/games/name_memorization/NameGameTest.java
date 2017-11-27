package edu.ucsb.cs56.projects.games.name_memorization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class NameGameTest {

    private Card c1;
    private Card c2;
    private DeckList dl1;
    private DeckList dl2;
    private DeckEditor de1;
    private Deck d1;
    private Deck d2;
    private NameGame ng1;

    @Before public void setUp() {
	c1 = new Card("side1", "side2", false);
  c2 = new Card("side1a", "side2b", true);
  d1 = new Deck("d1");
  d1.addCard("side1", "side2", false);
  d2.addCard("side1a", "side2b", true);
  dl1 = new DeckList();
  dl2 = new DeckList();
  dl1.add(d1);
  dl2.add(d1);
  dl2.add(d2);
  de1 = new DeckEditor(dl1);
  ng1 = new NameGame(dl1);
    }

    @Test
    public void test_getDeck() {
	assertEquals(d1, ng1.getDeck());
    }

    @Test
    public void test_getDeckList1() {
	assertEquals(dl1, ng1.getDeckList());
    }

    @Test
    public void test_setDeckList() {
      ng1.setDeckList(dl2);
	    assertEquals(dl2, ng1.getDeckList());
    }

}

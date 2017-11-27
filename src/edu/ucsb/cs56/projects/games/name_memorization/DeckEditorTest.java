package edu.ucsb.cs56.projects.games.name_memorization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class DeckEditorTest {

    private Deck d1;
    private Deck d2;
    private Card c1;
    private Card c2;
    private DeckList dl1;
    private DeckEditor de1;

    @Before public void setUp() {
      d1 = new Deck("d1");
      d2 = new Deck("d2");
	    c1 = new Card("side1", "side2", false);
      c2 = new Card("edit1", "edit2", false);
      dl1 = new DeckList();
      dl1.add(d1);
      dl1.add(d2);
      de1 = new DeckEditor(dl1);
    }

    @Test
    public void test_getDeckList() {
	assertEquals(dl1, de1.getDeckList());
  assertEquals(dl1, de1.getDeckList());
    }

}

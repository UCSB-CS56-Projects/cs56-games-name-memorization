package edu.ucsb.cs56.projects.games.name_memorization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class CardEditorTest {

    private Card c1;
    private Card c2;
    private CardEditor ce1;
    private CardEditor ce2;

    @Before public void setUp() {
	c1 = new Card("side1", "side2", false);
  c2 = new Card("side1", "side2", true);
  ce1 = new CardEditor(c1);
  ce2 = new CardEditor(c2);
    }

    @Test
    public void test_BackText() {
	assertEquals("side2", ce1.getBackText());
    }

    @Test
    public void test_FrontText() {
	assertEquals("side1", ce1.getFrontText());
    }

    @Test
    public void test_isPicFalse() {
	assertEquals(false, ce1.isPic());
    }

    @Test
    public void test_isPicTrue() {
	assertEquals(true, ce2.isPic());
    }

}

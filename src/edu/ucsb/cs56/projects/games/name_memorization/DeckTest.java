package edu.ucsb.cs56.projects.games.name_memorization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class DeckTest {

    private Deck d1;
    private Card c1;
    private Card c2;

    @Before public void setUp() {
        d1 = new Deck("d1");
        c1 = new Card("side1", "side2", false);
        c2 = new Card("edit1", "edit2", false);
    }

    @Test
    public void test_getName() {
	assertEquals("d1", d1.getName());
    }

    @Test
    public void test_addCard() {
        d1.addCard("side1", "side2", false);
        assertEquals("side1", d1.get(0).getSide1());
        assertEquals("side2", d1.get(0).getSide2());
        assertEquals(false, d1.get(0).isPic());
    }

    @Test
    public void test_editCard() {
        d1.add(0,c1);
        d1.editCard(d1.get(0), "edit1", "edit2");
        assertEquals("edit1", d1.get(0).getSide1());
        assertEquals("edit2", d1.get(0).getSide2());
        assertEquals(false, d1.get(0).isPic());
    }

}

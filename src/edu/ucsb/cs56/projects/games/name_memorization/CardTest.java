package edu.ucsb.cs56.projects.games.name_memorization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

public class CardTest {

    private Card c1;
    private Card c2;

    @Before public void setUp() {
	c1 = new Card("side1", "side2", false);
  c2 = new Card("side1", "side2", true);
    }

    @Test
    public void test_getSide1() {
	assertEquals("side1", c1.getSide1());
    }

    @Test
    public void test_getSide2() {
	assertEquals("side2", c1.getSide2());
    }

    @Test
    public void test_isPicFalse() {
	assertEquals(false, c1.isPic());
    }

    @Test
    public void test_isPicTrue() {
	assertEquals(true, c2.isPic());
    }

    @Test
    public void test_setSide1() {
      c1.setSide1("newSide1");
	    assertEquals("newSide1", c1.getSide1());
    }

    @Test
    public void test_setSide2() {
      c1.setSide2("newSide2");
	    assertEquals("newSide2", c1.getSide2());
    }
}

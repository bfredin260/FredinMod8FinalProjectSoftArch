package edu.wctc.JUnitTests;

import edu.wctc.PureFabrication.Determiner;
import edu.wctc.StrategyPattern.KeyStrategy;
import edu.wctc.StrategyPattern.KickDownStrategy;
import edu.wctc.StrategyPattern.LockpickStrategy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DeterminerTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void convertCharNToActionTest() {
        assertEquals("MOVE NORTH", Determiner.convertCharToAction('n'), "Action should be MOVE NORTH");
    }

    @Test
    void convertCharEToActionTest() {
        assertEquals("MOVE EAST", Determiner.convertCharToAction('e'), "Action should be MOVE EAST");
    }

    @Test
    void convertCharSToActionTest() {
        assertEquals("MOVE SOUTH", Determiner.convertCharToAction('s'), "Action should be MOVE SOUTH");
    }

    @Test
    void convertCharWToActionTest() {
        assertEquals("MOVE WEST", Determiner.convertCharToAction('w'), "Action should be MOVE WEST");
    }

    @Test
    void convertCharIToActionTest() {
        assertEquals("INTERACT", Determiner.convertCharToAction('i'), "Action should be INTERACT");
    }

    @Test
    void convertCharXToActionTest() {
        assertEquals("EXIT", Determiner.convertCharToAction('x'), "Action should be EXIT");
    }

    @Test
    void convertCharVToActionTest() {
        assertEquals("VIEW INVENTORY", Determiner.convertCharToAction('v'), "Action should be VIEW INVENTORY");
    }

    @Test
    void convertCharCToActionTest() {
        assertEquals("VALID ENTRIES", Determiner.convertCharToAction('c'), "Action should be VALID ENTRIES");
    }

    @Test
    void convertCharInvalidToActionTest() {
        assertNull(Determiner.convertCharToAction('q'), "Invalid action char should return null");
    }

    @Test
    void convertCharKToOpenStrategyTest() {
        assertEquals(KeyStrategy.class, Determiner.convertCharToOpenStrategy('k').getClass(), "Should have created a KeyStrategy class");
    }

    @Test
    void convertCharLToOpenStrategyTest() {
        assertEquals(LockpickStrategy.class, Determiner.convertCharToOpenStrategy('l').getClass(), "Should have created a LockpickStrategy class");
    }

    @Test
    void convertCharXToOpenStrategyTest() {
        assertEquals(KickDownStrategy.class, Determiner.convertCharToOpenStrategy('x').getClass(), "Should have created a KickDownStrategy class");
    }

    @Test
    void convertCharInvalidToOpenStrategyTest() {
        assertNull(Determiner.convertCharToOpenStrategy('q'), "Invalid open strategy char should return null");
    }
}
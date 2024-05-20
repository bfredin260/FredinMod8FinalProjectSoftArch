package edu.wctc.JUnitTests;

import edu.wctc.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
        for(int i = 0; i < 5; i++) player.addToInventory("testItem");
    }

    @Test
    public void amountOfItemInInventoryTest() {
        assertEquals(5, player.amountOfItemInInventory("testItem"), "Should be 5 items in the inventory");
    }
}
package edu.wctc.JUnitTests;

import edu.wctc.Maze;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    Maze testMaze;

    @BeforeEach
    void setUp() {
        testMaze = Maze.getInstance();
    }

    @Test
    void canMoveInvalidTest() {
        // currentRoom has no connection on its south side
        assertFalse(testMaze.canMove('s'));
    }

    @Test
    void canMoveValidTest() {
        // currentRoom DOES have a connection on its north side
        assertFalse(testMaze.canMove('n'));
    }
}
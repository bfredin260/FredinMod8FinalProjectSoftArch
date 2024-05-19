package edu.wctc.JUnitTests;

import edu.wctc.FactoryPattern.RoomFactory;
import edu.wctc.rooms.roomTypes.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RoomFactoryTest {
    static String name;
    static String description;
    static String exitString;
    static String interactString;

    @BeforeEach
    void setUp() {
        name = "Test Room";
        description = "Description";
        exitString = "You have exited the room";
        interactString = "You have interacted with the room";
    }

    @Test
    public void createBothRoomTest() {
        Assertions.assertEquals(BothRoom.class, RoomFactory.createRoom(name, description, exitString, interactString, true, true).getClass(), "Should have created a BothRoom");
    }

    @Test
    public void createNoneRoomTest() {
        assertEquals(NoneRoom.class, RoomFactory.createRoom(name, description, exitString, interactString, false, false).getClass(), "Should have created a NoneRoom");
    }

    @Test
    public void createInteractableRoomTest() {
        assertEquals(InteractableRoom.class, RoomFactory.createRoom(name, description, exitString, interactString, false, true).getClass(), "Should have created an InteractableRoom");
    }

    @Test
    public void createExitableRoomTest() {
        assertEquals(ExitableRoom.class, RoomFactory.createRoom(name, description, exitString, interactString, true, false).getClass(), "Should have created an ExitableRoom");
    }
}
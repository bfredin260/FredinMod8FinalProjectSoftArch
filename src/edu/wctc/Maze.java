package edu.wctc;

import edu.wctc.FactoryPattern.RoomFactory;
import edu.wctc.PureFabrication.Determiner;
import edu.wctc.rooms.interfaces.Room;
import edu.wctc.rooms.roomTypes.InteractableRoom;

public class Maze {
    // singleton
    public static Maze INSTANCE = null;

    private Room currentRoom;
    private Player player;

    private boolean isFinished = false;

    public static Maze getInstance() {
        if(INSTANCE == null) INSTANCE = new Maze();
        return INSTANCE;
    }

    private Maze() {
        this.player = new Player();

        // start player with 5 lockpicks
        for(int i = 0; i < 5; i++) this.player.addToInventory("lockpick");

        // ROOMS

        Room room1 = RoomFactory.createRoom(
            "STARTING ROOM", // name
            "Your journey starts here.", // description
            null, // exit string
            "You notice that whoever left you in here left their keys nearby.", // interact string
            false, // exitable
            true // interactable
        );

        // set item to pick up when interacting
        putItem("key", (InteractableRoom)room1, 5);

        // starting room needs to be selected
        this.currentRoom = room1;

        Room room2 = RoomFactory.createRoom(
            "SECOND ROOM",
            "You made it out of the first room.",
            null,
            "You notice there is more than one path to go down.\nYou find some lockpicks on a shelf nearby.",
            false,
            true
        );
        putItem("lockpick", (InteractableRoom)room2, 3);


        // SETTING ROOM CONNECTIONS
        room1.setNorth(room2);

        room2.setSouth(room1);
    }

    public boolean canMove(char direction) {
        return this.currentRoom.isValidDirection(direction);
    }

    // information expert pattern - this method was originally in the Determiner class, but it fits in this one
    //  better, since this class contians the information necessary.
    public String tryToMove(char userInput, String direction) {
        if(canMove(userInput)) return move(userInput);
        else return "You can't go " + direction + ".\n";
    }

    public String move(char direction) {
        if(!currentRoom.isDoorLocked(direction)) {
            this.currentRoom = this.currentRoom.convertCharToDir(direction);
            return "You have moved to " + currentRoom.getName() + "\n";
        } else {
            return player.openDoor(direction, Determiner.determineOpenStrategyFromInput());
        }
    }

    public int getPlayerScore() {
        return this.player.getScore();
    }

    public String getPlayerInventory() {
        return this.player.displayInventory();
    }

    public String getCurrentRoomDescription() {
        return this.currentRoom.getDescription();
    }

    public String getCurrentRoomExits() {
        return this.currentRoom.getExits();
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void finish() {
        this.isFinished = true;
    }

    private void putItem(String item, InteractableRoom room, int amountOfItem) {
        room.setHeldItem(item);
        room.setAmountOfItem(amountOfItem);
    }
}

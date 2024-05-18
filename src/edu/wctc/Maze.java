package edu.wctc;

import edu.wctc.rooms.interfaces.*;
import edu.wctc.rooms.*;
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

        // ROOMS

        // starting room needs to be selected
//        Room strangePlace = new StrangePlace("STRANGE PLACE");
        this.currentRoom = null; //  strangePlace;

//        Room ominousHallway = new OminousHallway("OMINOUS HALLWAY");
//        Room infestedCrook = new InfestedCrook("INFESTED CROOK");
//        Room melancholySkylight = new MelancholySkylight("MELANCHOLY SKYLIGHT");
//        Room collapsedTomb = new CollapsedTomb("COLLAPSED TOMB", melancholySkylight);
//        Room finalRestingPlace = new FinalRestingPlace("FINAL RESTING PLACE");

        // SETTING ROOM CONNECTIONS
//        strangePlace.setNorth(ominousHallway);
//
//        ominousHallway.setSouth(strangePlace);
//        ominousHallway.setWest(infestedCrook);
//        ominousHallway.setEast(collapsedTomb);
//
//        infestedCrook.setEast(ominousHallway);
//
//        collapsedTomb.setWest(ominousHallway);
//        collapsedTomb.setNorth(finalRestingPlace);
//
//        melancholySkylight.setDown(collapsedTomb);
//
//        finalRestingPlace.setSouth(collapsedTomb);
    }

    public String exitCurrentRoom() {
        if(this.currentRoom instanceof Exitable exitable) {
            this.isFinished = true;
            this.player.addScore(450);
            return exitable.exit(this.player);
        } else return "\nCan not exit the labyrinth from this room.\n";
    }

    public String interactWithCurrentRoom() {
        if(this.currentRoom instanceof Interactable interact) return interact.interact(this.player);
        else return "\nNo interactions with this room are possible.\n";
    }

    public boolean canMove(char direction) {
        return this.currentRoom.isValidDirection(direction);
    }

    public int getPlayerScore() {
        return this.player.getScore();
    }

    public String getPlayerInventory() {
        return this.player.getInventory();
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

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void putItem(String item, InteractableRoom room) {
        room.setHeldItem(item);
    }
}

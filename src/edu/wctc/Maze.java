package edu.wctc;

import edu.wctc.FactoryPattern.RoomFactory;
import edu.wctc.PureFabrication.Determiner;
import edu.wctc.rooms.interfaces.Room;
import edu.wctc.rooms.roomTypes.InteractableRoom;

public class Maze {
    // singleton
    public static Maze INSTANCE = null;

    private Room currentRoom;
    private final Player player;

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

        Room containmentChamber = RoomFactory.createRoom(
            "CONTAINMENT CHAMBER", // name
            "You can't stay here any longer.", // description
            null, // exit string
            "You notice that whoever left you in here left their keys nearby.", // interact string
            false, // exitable
            true // interactable
        );

        // set item to pick up when interacting
        putItem("key", (InteractableRoom)containmentChamber, 5);

        // starting room needs to be selected
        this.currentRoom = containmentChamber;

        Room facilityHallwaySW = RoomFactory.createRoom(
            "FACILITY HALLWAY - SOUTHWEST",
            "You look around you and recognize nothing.",
            null,
            "You notice there is more than one path to go down.\nYou find some lockpicks on a shelf nearby.",
            false,
            true // interactable
        );
        putItem("lockpick", (InteractableRoom)facilityHallwaySW, 3);

        Room serverRoom = RoomFactory.createRoom(
            "SERVER ROOM",
            "This must be where they store their files.",
            null,
            "You look through the master computer and find out the exit door requires 5 keycards to unlock during the night.",
            false,
            true // interactable
        );

        Room masterControl = RoomFactory.createRoom(
            "SERVER ROOM - MASTER CONTROL",
            "Looks like this is where they control the building's electronics.",
            null,
            "You notice whoever was here last left their keycard sitting next to the cotrol panel.",
            false,
            true // interactable
        );
        putItem("keycard", (InteractableRoom) masterControl, 1);

        Room employeeOffices = RoomFactory.createRoom(
            "EMPLOYEE OFFICES",
            "The empty cubicles make you uneasy.\nThey are WAY too clean, as if nobody was ever here.",
            null,
            null,
            false,
            false
        );

        Room managersOffice = RoomFactory.createRoom(
            "MANAGER'S OFFICE",
            "The manager's chair stands elegantly amidst the room, resembling a throne.",
            null,
            "A quick search through the file cabinets reveals the manager's backup keycard.",
            false,
            true // interactable
        );
        putItem("keycard", (InteractableRoom) managersOffice, 1);

        Room facilityHallwaySE = RoomFactory.createRoom(
            "FACILITY HALLWAY - SOUTHEAST",
            "All these long hallways make it difficult to orient your location within the facility.",
            null,
            "For once, you can see the outside world.\nThe moonlight shines faintly through the foggy windows.",
            false,
            true // interactable
        );

        Room meetingRoom = RoomFactory.createRoom(
            "MEETING ROOM",
            "You are surrounded by white boards, the large table in the middle of the room stands out.",
            null,
            "One of the chairs has a set of keys hanging off of it.\nSomeone must have been in a hurry.",
            false,
            true // interactable
        );
        putItem("key", (InteractableRoom) meetingRoom, 3);

        Room employeeBreakRoom = RoomFactory.createRoom(
            "EMPLOYEE BREAK ROOM",
            "These working conditions are outrageous!",
            null,
            "Apart from the boring walls and maggot-infested fabric chairs, you find a keycard in an employee's coat pocket hanging on the wall",
            false,
            true // interactable
        );
        putItem("keycard", (InteractableRoom) employeeBreakRoom, 1);

        Room facilityHallwayW = RoomFactory.createRoom(
            "FACILITY HALLWAY - WEST",
            "This hallway seems to never end.",
            null,
            null,
            false,
            false
        );

        Room employeeBathroom = RoomFactory.createRoom(
            "EMPLOYEE BATHROOM",
            "Thank God. You've been holding this one in forEVER.",
            null,
            "While taking a piss, you notice a keycard in a puddle on the floor.\nLooks like someone doesn't want that back.",
            false,
            true // interactable
        );
        putItem("keycard", (InteractableRoom) employeeBathroom, 1);

        Room facilityHallwayNW = RoomFactory.createRoom(
            "FACILITY HALLWAY - NORTH WEST",
            "If only by a miracle, you reach a turning point in this hallway.",
            null,
            "You inspect each tile on the floor in this corner of the hallway.\nThere seems to be nothing of interest here.",
            false,
            true // interactable
        );

        Room waitingRoom = RoomFactory.createRoom(
            "WAITING ROOM",
            "What kind of facility is this? Where are you?",
            null,
            "Out of the corner of your eye, you notice the receptionist's keys amongst the files and folders on their desk.",
            false,
            true // interactable
        );
        putItem("key", (InteractableRoom) waitingRoom, 2);

        Room waitingRoomBathroom = RoomFactory.createRoom(
            "WAITING ROOM - BATHROOM",
            "This bathroom is 10x worse than the employee's bathroom.",
            null,
            "What is with keycards being attracted to bathroom puddles?",
            false,
            true // interactable
        );
        putItem("keycard", (InteractableRoom) waitingRoomBathroom, 1);

        Room facilityEntrance = RoomFactory.createRoom(
            "FACILITY ENTRANCE",
            "The outside is so close, but the doors are locked!",
            "You scan all 5 keycards and the doors automatically unlock.\nYou are free.",
            "You see a terminal for keycards to be scanned.",
            true, // exitable
            true // interactable
        );

        // SETTING ROOM CONNECTIONS
        containmentChamber.setNorth(facilityHallwaySW);

        facilityHallwaySW.setNorth(facilityHallwayW);
        facilityHallwaySW.setEast(employeeOffices);
        facilityHallwaySW.setSouth(containmentChamber);
        facilityHallwaySW.setWest(serverRoom);

        serverRoom.setNorth(masterControl);
        serverRoom.setEast(facilityHallwaySW);

        masterControl.setSouth(serverRoom);

        employeeOffices.setEast(facilityHallwaySE);
        employeeOffices.setSouth(managersOffice);
        employeeOffices.setWest(facilityHallwaySW);

        managersOffice.setNorth(employeeOffices);

        facilityHallwaySE.setNorth(meetingRoom);
        facilityHallwaySE.setEast(employeeBreakRoom);
        facilityHallwaySE.setWest(employeeOffices);

        meetingRoom.setSouth(facilityHallwaySE);

        employeeBreakRoom.setWest(facilityHallwaySE);

        facilityHallwayW.setNorth(facilityHallwayNW);
        facilityHallwayW.setEast(employeeBathroom);
        facilityHallwayW.setSouth(facilityHallwaySW);

        employeeBathroom.setWest(facilityHallwayW);

        facilityHallwayNW.setEast(waitingRoom);
        facilityHallwayNW.setSouth(facilityHallwayW);

        waitingRoom.setNorth(facilityEntrance);
        waitingRoom.setEast(waitingRoomBathroom);
        waitingRoom.setWest(facilityHallwayNW);

        waitingRoomBathroom.setWest(waitingRoom);

        facilityEntrance.setSouth(waitingRoom);
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
        room.setHeldItemAmount(amountOfItem);
    }
}

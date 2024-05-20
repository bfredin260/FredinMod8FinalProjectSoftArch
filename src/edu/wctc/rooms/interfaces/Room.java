package edu.wctc.rooms.interfaces;

import java.util.ArrayList;

import static java.util.Objects.isNull;

public abstract class Room {
    private final String name;
    private final String description;

    // Stores the room in the given direction
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    // Stores if the door in each direction is locked
    private boolean northLocked = true;
    private boolean eastLocked = true;
    private boolean southLocked = true;
    private boolean westLocked = true;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getExits() {
        ArrayList<String> exits = new ArrayList<>();
        if(isValidDirection('n')) exits.add("North");
        if(isValidDirection('e')) exits.add("East");
        if(isValidDirection('s')) exits.add("South");
        if(isValidDirection('w')) exits.add("West");
        return exits.toString();
    }

    public String getName() {
        return this.name;
    }

    public boolean isValidDirection(char direction) {
        return !isNull(convertCharToDir(direction));
    }

    public void setNorth(Room room) {
        this.north = room;
    }

    public void setEast(Room room) {
        this.east = room;
    }

    public void setSouth(Room room) {
        this.south = room;
    }

    public void setWest(Room room) {
        this.west = room;
    }

    public void unlock(char direction) {
        switch(direction) {
            case 'n':
                this.northLocked = false;
                this.north.southLocked = false;
                break;
            case 'e':
                this.eastLocked = false;
                this.east.westLocked = false;
                break;
            case 's':
                this.southLocked = false;
                this.south.northLocked = false;
                break;
            case 'w':
                this.westLocked = false;
                this.west.eastLocked = false;
                break;
        }
    }

    public boolean isDoorLocked(char direction) {
        return switch(direction) {
            case 'n' -> this.northLocked;
            case 'e' -> this.eastLocked;
            case 's' -> this.southLocked;
            case 'w' -> this.westLocked;
            default -> true;
        };
    }

    // added this so that I can easily convert the char ('n', 'e', 's', 'w', 'u', or 'd') into their corresponding
    //  cardinal directions so that they can be used.

    // Information Expert - this could be in the Determiner class, but this class knows the information necessary and
    //  the other class does not.
    public Room convertCharToDir(char direction) {
        return switch (direction) {
            case 'n' -> this.north;
            case 'e' -> this.east;
            case 's' -> this.south;
            case 'w' -> this.west;
            default -> null;
        };
    }
}

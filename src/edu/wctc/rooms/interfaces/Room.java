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
    private Room up;
    private Room down;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Room getAdjoiningRoom(char direction) {
        if(isValidDirection(direction)) return convertCharToDir(direction);
        else return null;
    }

    public String getExits() {
        ArrayList<String> exits = new ArrayList<>();
        if(isValidDirection('n')) exits.add("North");
        if(isValidDirection('e')) exits.add("East");
        if(isValidDirection('s')) exits.add("South");
        if(isValidDirection('w')) exits.add("West");
        if(isValidDirection('u')) exits.add("Up");
        if(isValidDirection('d')) exits.add("Down");
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

    public void setUp(Room room) {
        this.up = room;
    }

    public void setDown(Room room) {
        this.down = room;
    }

    // added this so that I can easily convert the char ('n', 'e', 's', 'w', 'u', or 'd') into their corresponding
    // cardinal directions so that they can be used.
    Room convertCharToDir(char direction) {
        return switch (direction) {
            case 'n' -> this.north;
            case 'e' -> this.east;
            case 's' -> this.south;
            case 'w' -> this.west;
            case 'u' -> this.up;
            case 'd' -> this.down;
            default -> null;
        };
    }
}

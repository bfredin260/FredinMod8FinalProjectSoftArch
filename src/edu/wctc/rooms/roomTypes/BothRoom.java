package edu.wctc.rooms.roomTypes;

import edu.wctc.Player;
import edu.wctc.rooms.interfaces.*;

public class BothRoom extends InteractableRoom implements Exitable, Interactable {
    String exitString;

    public BothRoom(String name, String description, String exitString, String interactString) {
        super(name, description, exitString);
        setInteractString(interactString);
        setExitString(exitString);
    }

    @Override
    public String exit(Player player) {
        return getExitString();
    }

    @Override
    public void setExitString(String string) {
        this.exitString = string;
    }

    @Override
    public String getExitString() {
        return this.exitString;
    }
}

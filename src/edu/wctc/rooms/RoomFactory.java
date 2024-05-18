package edu.wctc.rooms;

import edu.wctc.rooms.interfaces.*;
import edu.wctc.rooms.roomTypes.*;

// Factory method pattern
public class RoomFactory {
    public Room createRoom(String name, String description, String exitString, String interactString, boolean exitable, boolean interactable) {
        if(exitable)
            if(interactable) return new BothRoom(name, description, exitString, interactString);
            else return new ExitableRoom(name, description, exitString);
        else if(interactable) return new InteractableRoom(name, description, interactString);
        return new NoneRoom(name, description);
    }
}

package edu.wctc.FactoryPattern;

import edu.wctc.rooms.interfaces.*;
import edu.wctc.rooms.roomTypes.*;

// Factory method pattern

// Every google search, Stack Overflow page, and other person I have consulted with has told me that a factory is used
//  to solve an existing problem, not to create a new one. I did not have a need for a Factory, even so, I came up with
//  this.
public class RoomFactory {
    public static Room createRoom(String name, String description, String exitString, String interactString, boolean exitable, boolean interactable) {
        if(exitable)
            if(interactable) return new BothRoom(name, description, exitString, interactString);
            else return new ExitableRoom(name, description, exitString);
        else if(interactable) return new InteractableRoom(name, description, interactString);
        return new NoneRoom(name, description);
    }
}

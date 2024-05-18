package edu.wctc.rooms.roomTypes;

import edu.wctc.Player;
import edu.wctc.rooms.interfaces.Exitable;
import edu.wctc.rooms.interfaces.Room;

public class ExitableRoom extends Room implements Exitable {
    String exitString;

    public ExitableRoom(String name, String description, String exitString) {
        super(name, description);
        this.exitString = exitString;
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

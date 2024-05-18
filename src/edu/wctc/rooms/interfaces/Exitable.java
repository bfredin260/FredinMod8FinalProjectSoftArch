package edu.wctc.rooms.interfaces;

import edu.wctc.Player;

public interface Exitable {
    String exit(Player player);

    void setExitString(String string);
    String getExitString();
}

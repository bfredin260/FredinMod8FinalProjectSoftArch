package edu.wctc.rooms.interfaces;

import edu.wctc.Player;

public interface Exitable {
    String exit(Player player);

    void setRequiredItem(String item);
    void setRequiredItemAmount(int amount);

    void setExitString(String string);
    String getExitString();
}

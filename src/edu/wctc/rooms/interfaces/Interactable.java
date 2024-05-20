package edu.wctc.rooms.interfaces;

import edu.wctc.Player;

public interface Interactable {
    String interact(Player player);

    void setHeldItem(String item);
    void setHeldItemAmount(int amount);

    void setInteractString(String string);
    String getInteractString();
}

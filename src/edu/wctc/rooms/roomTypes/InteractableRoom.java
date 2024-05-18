package edu.wctc.rooms.roomTypes;

import edu.wctc.Player;
import edu.wctc.rooms.interfaces.*;

public class InteractableRoom extends Room implements Interactable {
    private boolean interacted = false;

    private String heldItem = null;
    private String interactString;

    public InteractableRoom(String name, String description, String interactString) {
        super(name, description);
        this.interactString = interactString;
    }

    @Override
    public String interact(Player player) {
        StringBuilder sb = new StringBuilder();
        if(!interacted) {
            this.interacted = true;
            sb.append(getInteractString());
            if(heldItem != null) {
                player.addToInventory(heldItem);
                sb.append(String.format("\n%s added to your inventory!", heldItem.toUpperCase()));
            }
            return sb.toString();
        } else return "Nothing to interact with.";
    }

    @Override
    public void setHeldItem(String item) {
        this.heldItem = item;
    }

    @Override
    public void setInteractString(String string) {
        this.interactString = string;
    }

    @Override
    public String getInteractString() {
        return this.interactString;
    }
}
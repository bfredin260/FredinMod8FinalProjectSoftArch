package edu.wctc.rooms.roomTypes;

import edu.wctc.Player;
import edu.wctc.rooms.interfaces.*;

import static edu.wctc.Main.maze;

public class InteractableRoom extends Room implements Interactable {
    private boolean interacted = false;

    private String heldItem = null;
    private int heldItemAmount = 0;
    private String interactString;

    public InteractableRoom(String name, String description, String interactString) {
        super(name, description);
        this.interactString = interactString;
    }

    @Override
    public String interact(Player player) {
        StringBuilder sb = new StringBuilder();
        if(!interacted) {
            interacted = true;
            maze.getPlayer().addScore(50);
            sb.append(getInteractString());
            if(heldItem != null) {
                for(int i = 0; i < heldItemAmount; i++) player.addToInventory(heldItem);
                sb.append(String.format("\n\n%d %s(s) added to your inventory!\n", heldItemAmount, heldItem.toUpperCase()));
                heldItemAmount = 0;
            }
            return sb.toString();
        } else return "Nothing to interact with.";
    }

    @Override
    public void setHeldItem(String item) {
        this.heldItem = item;
    }

    @Override
    public void setHeldItemAmount(int amount) {
        this.heldItemAmount = amount;
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

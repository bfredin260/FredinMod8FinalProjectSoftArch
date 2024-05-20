package edu.wctc.rooms.roomTypes;

import edu.wctc.Player;
import edu.wctc.rooms.interfaces.*;

import static edu.wctc.Main.maze;

public class BothRoom extends InteractableRoom implements Exitable, Interactable {
    private String exitString;

    private String requiredItem;
    private int requiredItemAmount;

    public BothRoom(String name, String description, String exitString, String interactString) {
        super(name, description, exitString);
        setInteractString(interactString);
        setExitString(exitString);
    }

    @Override
    public String exit(Player player) {
        int amountOfItemHeld = maze.getPlayer().amountOfItemInInventory(requiredItem);
        if(amountOfItemHeld >= requiredItemAmount) {
            maze.finish();
            maze.getPlayer().addScore(450);
            return getExitString();
        } else return String.format(
            "%d %s(s) needed to exit! (You only hold %d)",
            requiredItemAmount,
            requiredItem,
            amountOfItemHeld
        );
    }

    @Override
    public void setRequiredItem(String item) {
        this.requiredItem = item;
    }

    @Override
    public void setRequiredItemAmount(int amount) {
        this.requiredItemAmount = amount;
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

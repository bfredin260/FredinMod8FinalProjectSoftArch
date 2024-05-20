package edu.wctc.rooms.roomTypes;

import edu.wctc.Player;
import edu.wctc.rooms.interfaces.Exitable;
import edu.wctc.rooms.interfaces.Room;

import static edu.wctc.Main.maze;

public class ExitableRoom extends Room implements Exitable {
    private String exitString;

    private String requiredItem;
    private int requiredItemAmount;

    public ExitableRoom(String name, String description, String exitString) {
        super(name, description);
        this.exitString = exitString;
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

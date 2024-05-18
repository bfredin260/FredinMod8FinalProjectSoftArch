package edu.wctc;

import edu.wctc.rooms.interfaces.Exitable;
import edu.wctc.rooms.interfaces.Interactable;

import java.util.ArrayList;
import java.util.List;

import static edu.wctc.Main.maze;

public class Player {
    private int score;
    private List<String> inventory = new ArrayList<>();

    public void addToInventory(String item) {
        this.inventory.add(item);
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    public String getInventory() {
        if(this.inventory.isEmpty()) return "Your inventory is empty.";
        else return this.inventory.toString();
    }

    public int getScore() {
        return this.score;
    }

    public boolean removeFromInventory(String item) {
        return inventory.remove(item);
    }

    public boolean interact() {
        if (maze.getCurrentRoom() instanceof Interactable interactable) {
            interactable.interact(this);
            return true;
        }
        return false;
    }

    public boolean exit() {
        if(maze.getCurrentRoom() instanceof Exitable exitable) {
            exitable.exit(this);
            return true;
        }
        return false;
    }
}

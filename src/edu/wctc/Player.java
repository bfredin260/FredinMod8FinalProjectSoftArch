package edu.wctc;

import edu.wctc.StrategyPattern.OpenStrategy;
import edu.wctc.rooms.interfaces.Interactable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.wctc.Main.maze;

public class Player {
    private int score;
    private List<String> inventory = new ArrayList<>();

    public int getScore() {
        return this.score;
    }

    public void addScore(int amount) {
        this.score += amount;
    }


    public void addToInventory(String item) {
        this.inventory.add(item);
    }

    public boolean removeFromInventory(String item) {
        return inventory.remove(item);
    }

    public String displayInventory() {
        if(this.inventory.isEmpty()) return "Your inventory is empty.";
        else return this.inventory.toString();
    }

    public boolean itemInInventory(String item) {
        return inventory.contains(item);
    }


    public String openDoor(char direction, OpenStrategy openStrategy) {
        // if the strategy succeeds or not
        boolean success = new Random().nextInt(100) < openStrategy.successRate() * 100;

        if(success) {
            addScore(openStrategy.successRewardAmount());
            maze.getCurrentRoom().unlock(direction);

            // removes item from player's inventory if necessary
            if(openStrategy.successUsesItem()) removeFromInventory(openStrategy.itemToUse());

            return "Successfully opened door using " +
                openStrategy.getStrategyName() +
                " (+" +
                openStrategy.successRewardAmount() +
                " points)\n\n" +
                maze.goToRoom(direction)
            ;
        }
        else {
            addScore(openStrategy.failPunishmentAmount());

            // removes item from player's inventory if necessary
            if(openStrategy.failUsesItem()) removeFromInventory(openStrategy.itemToUse());

            return "Failed to open door using " +
                openStrategy.getStrategyName() +
                " (" +
                openStrategy.failPunishmentAmount() +
                " points)\n\n";
        }
    }


    public String interact() {
        if(maze.getCurrentRoom() instanceof Interactable interactable) return interactable.interact(this);
        else return "\nNo interactions with this room are possible.\n";
    }
}

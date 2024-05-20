package edu.wctc;

import edu.wctc.StrategyPattern.OpenStrategy;
import edu.wctc.rooms.interfaces.Exitable;
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

    public int amountOfItemInInventory(String item) {
        int amount = 0;
        for(String i : inventory) if(i.equals(item)) amount++;
        return amount;
    }


    public String openDoor(char direction, OpenStrategy openStrategy) {
        // if the strategy succeeds or not
        boolean success = new Random().nextInt(100) < openStrategy.successRate() * 100;

        if(success) {
            addScore(openStrategy.successPointReward());
            maze.getCurrentRoom().unlock(direction);

            // removes item from player's inventory if necessary
            if(openStrategy.successConsumesItem()) removeFromInventory(openStrategy.itemToConsume());

            return "Successfully opened door using " +
                openStrategy.strategyName() +
                " (+" +
                openStrategy.successPointReward() +
                " points)\n\n" +
                maze.move(direction)
            ;
        }
        else {
            addScore(openStrategy.failurePointDeduction());

            // removes item from player's inventory if necessary
            if(openStrategy.failureConsumesItem()) removeFromInventory(openStrategy.itemToConsume());

            return "Failed to open door using " +
                openStrategy.strategyName() +
                " (" +
                openStrategy.failurePointDeduction() +
                " points)\n\n";
        }
    }

    public String interact() {
        if(maze.getCurrentRoom() instanceof Interactable interactable) return interactable.interact(this);
        else return "\nNo interactions with this room are possible.\n";
    }

    public String exit() {
        if(maze.getCurrentRoom() instanceof Exitable exitable) return exitable.exit(this);
        else return "\nCan not exit the labyrinth from this room.\n";
    }
}

package edu.wctc;

import edu.wctc.PureFabrication.Determiner;
import edu.wctc.PureFabrication.ConsoleOutput;
import edu.wctc.PureFabrication.UserInput;

public class Main {

    // !! READ THE README.MD FILE PLEASE

    // singleton so that only one Maze can exist
    public static Maze maze = Maze.getInstance();

    public static void main(String[] args) {
        ConsoleOutput.outputLine("---------------------------------------------\nESCAPE THE FACILITY - BY: BRAEDON FREDIN\n---------------------------------------------\n");

        // this code loops after the user's input until they complete the maze
        do {
            ConsoleOutput.outputLine("You are in: " + maze.getCurrentRoom().getName());
            ConsoleOutput.outputLine(maze.getCurrentRoomDescription());
            ConsoleOutput.outputLine("\nAvailable room exits: " + maze.getCurrentRoomExits());
            ConsoleOutput.outputLine("\nPlease enter a command (enter 'c' for valid entries).");
            ConsoleOutput.outputOpen(">> ");
            ConsoleOutput.outputLine("\n---------------------------------------------" +
                    Determiner.determineAction(UserInput.getInput()));
            ConsoleOutput.outputLine("---------------------------------------------\n");
        } while(!maze.isFinished());

        ConsoleOutput.outputLine("\nTotal Score: " + maze.getPlayerScore() + "\n");
    }
}

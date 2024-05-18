package edu.wctc;

import java.util.Scanner;

public class Main {
    static Scanner keyboard = new Scanner(System.in);

    // singleton so that only one Maze can exist
    static Maze maze = Maze.getInstance();

    public static void main(String[] args) {
        System.out.println("---------------------------------------------\nINFILTRATION - BY: BRAEDON FREDIN\n---------------------------------------------\n");

        // this code loops after the user's input until they complete the maze
        do {
            System.out.println("You are in: " + maze.getCurrentRoom().getName());
            System.out.println(maze.getCurrentRoomDescription());
            System.out.println("\nAvailable room exits: " + maze.getCurrentRoomExits());
            System.out.println("\nPlease enter a command (enter 'c' for valid entries).");
            System.out.print(">> ");
            System.out.println("\n---------------------------------------------" +
                    ActionDeterminer.determineAction(keyboard.nextLine().toLowerCase().charAt(0)));
            System.out.println("---------------------------------------------\n");
    } while(!maze.isFinished());

        System.out.println("\nTotal Score: " + maze.getPlayerScore() + "\n");
    }
}

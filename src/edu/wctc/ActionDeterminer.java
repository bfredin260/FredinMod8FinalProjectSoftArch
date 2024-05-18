package edu.wctc;
import static edu.wctc.Main.maze;

// Pure Abstraction
public class ActionDeterminer {

    /**
     *  determines the action that the user wants to perform
     */
    public static String determineAction(char userInput) {
        return switch (userInput) {
            // 'n', 'e', 's', 'w', 'u', and 'd' are "movement" inputs
            case 'n' -> "\n\nMOVE:\n\n" + getDirWalked(userInput, "North");
            case 'e' -> "\n\nMOVE:\n\n" + getDirWalked(userInput, "East");
            case 's' -> "\n\nMOVE:\n\n" + getDirWalked(userInput, "South");
            case 'w' -> "\n\nMOVE:\n\n" + getDirWalked(userInput, "West");
            case 'u' -> "\n\nMOVE:\n\n" + getDirWalked(userInput, "Up");
            case 'd' -> "\n\nMOVE:\n\n" + getDirWalked(userInput, "Down");

            // 'i' is the "interact" input
            case 'i' -> "\n\nINTERACT:\n" + maze.interactWithCurrentRoom();

            // 'x' is the "exit" input
            case 'x' -> "\n\nEXIT:\n" + maze.exitCurrentRoom();

            // 'v' is the "inventory" input
            case 'v' -> "\n\nINVENTORY:\n\n" + maze.getPlayerInventory() + "\n";

            // I added a way for the user to see a list of all valid entries, since they might not know the controls
            case 'c' -> """


                    VALID ENTRIES:

                    'n' -> Move north.
                    'e' -> Move east.
                    's' -> Move south.
                    'w' -> Move west.
                    'u' -> Move up.
                    'd' -> Move down.
                    'i' -> Interact with current room.
                    'x' -> Exit labyrinth through current room
                    'v' -> Display inventory.
                    'c' -> Display valid input entries.
                    Anything else does nothing
                    """;

            // any other input will be thrown away, and the program will loop
            default -> "\n\nPlease enter a valid entry ('"+ userInput +"' is not a valid entry).\n";
        };
    }

    /**
     *  determines the direction the user is trying to move
     */
    public static String getDirWalked(char userInput, String direction) {
        if(maze.canMove(userInput))
            if(maze.getPlayer().getInventory().contains("Dim Torch")) {
                maze.setCurrentRoom(maze.getCurrentRoom().getAdjoiningRoom(userInput));
                return "You went " + direction + ".\n";
            } else return "The path ahead lies in darkness..\nYou did not canMove " + direction + ".\n";
        else return "You can't canMove " + direction + ".\n";
    }
}

package edu.wctc.PureFabrication;

import edu.wctc.Player;
import edu.wctc.StrategyPattern.KeyStrategy;
import edu.wctc.StrategyPattern.KickDownStrategy;
import edu.wctc.StrategyPattern.LockpickStrategy;
import edu.wctc.StrategyPattern.OpenStrategy;

import java.util.ArrayList;
import java.util.List;

import static edu.wctc.Main.maze;

// Pure Fabrication
public class ActionDeterminer {

    /**
     *  determines the action that the user wants to perform
     * @param userInput user's input for which action they want to perform
     * @return String for output where this method is called
     */
    public static String determineAction(String userInput) {
        char inputChar;
        if(!userInput.isEmpty() && userInput != null) {
            inputChar = userInput.toLowerCase().charAt(0);
            return switch (inputChar) {
                // 'n', 'e', 's', 'w', 'u', and 'd' are "movement" inputs
                case 'n' -> "\n\nMOVE:\n" + getDirWalked(inputChar, "North");
                case 'e' -> "\n\nMOVE:\n" + getDirWalked(inputChar, "East");
                case 's' -> "\n\nMOVE:\n" + getDirWalked(inputChar, "South");
                case 'w' -> "\n\nMOVE:\n" + getDirWalked(inputChar, "West");
                case 'u' -> "\n\nMOVE:\n" + getDirWalked(inputChar, "Up");
                case 'd' -> "\n\nMOVE:\n" + getDirWalked(inputChar, "Down");

                // 'i' is the "interact" input
                case 'i' -> "\n\nINTERACT:\n" + maze.getPlayer().interact();

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
                        'i' -> Interact with current room.
                        'x' -> Exit labyrinth through current room
                        'v' -> Display inventory.
                        'c' -> Display valid input entries.
                        Anything else does nothing
                        """;

                // any other input will be thrown away, and the program will loop
                default -> "\n\nPlease enter a valid entry ('"+ userInput +"' is not a valid entry).\n";
            };
        } else return "\n\nPlease enter a valid entry ('"+ userInput +"' is not a valid entry).\n";
    }

    /**
     *  determines the direction the user is trying to move
     * @param userInput directional input as a char
     * @param direction direction string for display purposes
     * @return String for output where this method is called
     */
    private static String getDirWalked(char userInput, String direction) {
        if(maze.canMove(userInput)) return maze.goToRoom(userInput);
        else return "You can't go " + direction + ".\n";
    }

    /**
     * Gets input from the user to decide which strategy they want to use
     * */
    public static OpenStrategy getOpenStrategyFromInput() {
        // for querying inventory
        Player player = maze.getPlayer();

        // we need to get the successRate for each of the strategies anyway, so returning them will be easier if we
        //  initialize them first
        OpenStrategy key = new KeyStrategy();
        OpenStrategy lockpick = new LockpickStrategy();
        OpenStrategy kickdown = new KickDownStrategy();

        // valid options depending on what the user has in their inventory
        List<Character> validOptions = new ArrayList<>();
        validOptions.add('x');
        if(player.itemInInventory("key")) validOptions.add('k');
        if(player.itemInInventory("lockpick")) validOptions.add('l');

        char inputChar = ' ';
        do {
            // prompt
            ConsoleOutput.outputLine("\n---------------------------------------------");
            ConsoleOutput.outputLine("\nDOOR IS LOCKED:\nHow would you like to open the door?");
            if (validOptions.contains('k')) ConsoleOutput.outputLine("k) Key (" + key.successRate() * 100 + "% success)");
            if (validOptions.contains('l')) ConsoleOutput.outputLine("l) Lockpick (" + lockpick.successRate() * 100 + "% success)");
            ConsoleOutput.outputLine("x) Kick the door down!! (" + kickdown.successRate() * 100 + "% success)");
            ConsoleOutput.outputOpen(">> ");

            // user input
            String input = UserInput.getInput();

            // sets input to char
            if(!input.isBlank() && input != null) inputChar = input.toLowerCase().charAt(0);
            else ConsoleOutput.outputLine("\nInvalid option!");

        // makes sure that the user's input is valid, reruns loop if it isn't
        } while (!validOptions.contains(inputChar));

        return determineStrategy(inputChar, key, lockpick, kickdown);
    }

    /**
     * determines the strategy to use based on the user's input
     * @param strategyChar user's option as a char
     * @param key KeyStrategy for return
     * @param lockpick LockpickStrategy for return
     * @param kickdown KickDownStrategy for return
     * @return OpenStrategy based on user's input
     */
    private static OpenStrategy determineStrategy(char strategyChar, OpenStrategy key, OpenStrategy lockpick, OpenStrategy kickdown) {
        return switch(strategyChar) {
            case 'k' -> key;
            case 'l' -> lockpick;
            default -> kickdown;
        };
    }
}

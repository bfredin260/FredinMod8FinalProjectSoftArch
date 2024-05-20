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
public class Determiner {

    /**
     *  determines the action that the user wants to perform
     * @param userInput user's input for which action they want to perform
     * @return String for output where this method is called
     */
    public static String determineAction(String userInput) {
        if(!userInput.isEmpty() && userInput != null) {
            char inputChar = userInput.toLowerCase().charAt(0);
            return switch (inputChar) {
                // 'n', 'e', 's', 'w', 'u', and 'd' are "movement" inputs
                case 'n' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.tryToMove(inputChar, "North");
                case 'e' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.tryToMove(inputChar, "East");
                case 's' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.tryToMove(inputChar, "South");
                case 'w' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.tryToMove(inputChar, "West");
                case 'u' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.tryToMove(inputChar, "Up");
                case 'd' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.tryToMove(inputChar, "Down");

                // 'i' is the "interact" input
                case 'i' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.getPlayer().interact();

                // 'x' is the "exit" input
                case 'x' -> "\n\n" + convertCharToAction(inputChar) + ":\n" + maze.getPlayer().exit();

                // 'v' is the "inventory" input
                case 'v' -> "\n\n" + convertCharToAction(inputChar) + ":\n\n" + maze.getPlayerInventory() + "\n";

                // I added a way for the user to see a list of all valid entries, since they might not know the controls
                case 'c' -> "\n\n" + convertCharToAction(inputChar) +

                        ":" + """
    
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

    // for the test class
    public static String convertCharToAction(char actionChar) {
        return switch(actionChar) {
            case 'n' -> "MOVE NORTH";
            case 'e' -> "MOVE EAST";
            case 's' -> "MOVE SOUTH";
            case 'w' -> "MOVE WEST";
            case 'i' -> "INTERACT";
            case 'x' -> "EXIT";
            case 'v' -> "VIEW INVENTORY";
            case 'c' -> "VALID ENTRIES";
            default -> null;
        };
    }
    /**
     *  determines the Open Strategy to be used
     * */
    public static OpenStrategy determineOpenStrategyFromInput() {
        // for querying inventory
        Player player = maze.getPlayer();

        // we need to get the successRate for each of the strategies
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

        return convertCharToOpenStrategy(inputChar);
    }

    // for the test class
    public static OpenStrategy convertCharToOpenStrategy(char strategyChar) {
        return switch(strategyChar) {
            case 'k' -> new KeyStrategy();
            case 'l' -> new LockpickStrategy();
            case 'x' -> new KickDownStrategy();
            default -> null;
        };
    }
}

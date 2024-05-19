package edu.wctc.PureFabrication;

import java.util.Scanner;

// Pure Fabrication
public class UserInput {
    private static Scanner scanner = new Scanner(System.in);
    public static String getInput() {
        return scanner.nextLine();
    }
}

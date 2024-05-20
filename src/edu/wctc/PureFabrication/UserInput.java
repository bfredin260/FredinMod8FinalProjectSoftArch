package edu.wctc.PureFabrication;

import java.util.Scanner;

// Pure Fabrication / Indirection (Wrapper)
public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }
}

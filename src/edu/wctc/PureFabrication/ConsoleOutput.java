package edu.wctc.PureFabrication;

// Pure Fabrication / Indirection (Wrapper)
public class ConsoleOutput {
    public static void outputLine(String message) {
        System.out.println(message);
    }

    public static void outputOpen(String message) {
        System.out.print(message);
    }

    public static void outputFormat(String message, Object... vars) {
        System.out.printf(message, vars);
    }
}

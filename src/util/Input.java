package util;

import java.util.Scanner;

public class Input {
    private Scanner scanner;



    public Input () {
        this.scanner = new Scanner(System.in);
    }

    public String getString (String s) {
        return this.scanner.nextLine();

    }

    public boolean yesNo (String s) {
//        String userInput = scanner.nextLine();
//        if (userInput.startsWith("y") || userInput.startsWith("Y")) {
//            return true;
//        }
//        return false;
        return this.getString("Enter the username you want more info on:").trim().toLowerCase().startsWith("y");
    }

    public int getInt () {
//        return scanner.nextInt();
        try {
            return Integer.parseInt(this.getString("Enter the username you want more info on:"));
        } catch (NumberFormatException e) {
            System.out.println("You must enter a whole number");
            return this.getInt();
        }
    }

    public int getInt(int min, int max) {
//        String userInput = scanner.nextLine();
//        System.out.println("Enter an integer between 0-25");
//        while (min < 0 || max > 25) {
//            System.out.println("Please try another integer");
//        }
//        System.out.println(userInput);
//        return userInput;
        int userInt = this.getInt();
        if (userInt >= min && userInt <= max) {
            return userInt;
        } else {
            System.out.printf("Must be between %d and %d%n", min, max);
            return getInt(min, max);
        }
    }

    public double getDouble() {
//        System.out.println("Enter an integer between 0-25");
//        String userInput = scanner.nextLine();
//        while (min < 0 || max > 25) {
//            System.out.println("Please try another integer");
//        }
//        System.out.println(userInput);
//        return userInput;
        try {
            return Double.parseDouble(this.getString("Enter the username you want more info on:"));
        } catch (NumberFormatException e) {
            System.out.println("You must enter a number");
            return this.getDouble();

        }
    }

    public double getDouble (String prompt) {
        System.out.println(prompt);
        return this.getDouble();

    }


    public double getDouble(double min, double max) {
//        return scanner.nextDouble();
        double userDouble = this.getDouble();
        if (userDouble >= min && userDouble <= max) {
            return userDouble;
        } else {
            System.out.printf("Must be between %f and %f%n", min, max);
            return getDouble(min, max);
        }
    }

    public static int divideNums (int a, int b) {
        try {
            return a / b;
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot divide by 0!");
        }
        return divideNums(3, 4);
    }


}

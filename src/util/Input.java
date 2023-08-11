package util;

import java.util.Scanner;

import static java.lang.Long.parseLong;

public class Input {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        String string = this.scanner.nextLine();
        return string;
    }

    public boolean yesNo() {
        String userInput = this.scanner.next();
        this.scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    public int getInt() {
        try {
            return Integer.parseInt(this.getString());
        } catch (NumberFormatException e) {
            System.out.println("You must enter a whole number");
            return getInt();
        }
    }

    public long getLong() {
        String userInput = this.getString();
            if (userInput.length() == 7 || userInput.length() == 10) {
                try {
                    return parseLong(userInput);
                }catch (NumberFormatException e){
                }
            }
                System.out.println("You must enter a valid number");
                return getLong();
    }


    public int getInt(int min, int max) {
        int userInt = this.getInt();
        if (userInt >= min && userInt <= max) {
            return userInt;
        } else {
            System.out.printf("The number must be between %d and %d. Please try again.", min, max);
            return getInt(min, max);
        }
    }


}

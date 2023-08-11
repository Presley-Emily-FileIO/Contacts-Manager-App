import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Input;

public class ContactManagerApplication extends Contact {

    static Input userResponse = new Input();

    static ArrayList<Contact> contactInfo = new ArrayList<>();
    static String directory = "Contacts";
    static String fileName = "contacts.txt";

    public ContactManagerApplication(String name, long phoneNumber) {
        super(name, phoneNumber);
    }
// Checks to see if directory and fileName exist if not makes them
    public static void createDirectoryFile() {
        Path contactsDirectory = Paths.get(directory);
        Path contactsFile = Paths.get(fileName);

        if (!Files.exists(contactsDirectory)) {
            try {
                Files.createDirectories(contactsDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!Files.exists(contactsFile)) {
            try {
                Files.createFile(contactsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void addContact() {
        System.out.println("Enter the name!");
        String userInput = userResponse.getString();
        System.out.println("Enter the phone number");
        long userNumber = userResponse.getLong();
        contactInfo.add(new Contact(userInput, userNumber));
        saveContactList();
        System.out.println("Contact added successfully");
    }


    public static void  deleteContact() {
        System.out.println("Who would you like to Delete?");
        renderContactList();
        String userInput = userResponse.getString();
        Contact contactToRemove = null;
        for (Contact contact : contactInfo) {
            if (contact.getName().equalsIgnoreCase(userInput)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            contactInfo.remove(contactToRemove);
            saveContactList();
            System.out.println(userInput + " has been deleted.");
        } else {
            System.out.println(userInput + " doesn't exist.");
        }
    }

    public static void searchContact(){
        System.out.println("Who would you like to search for?");
        String userInput = userResponse.getString();
        boolean contactFound = false;
        for (Contact contact : contactInfo) {
            if (contact.getName().trim().equalsIgnoreCase(userInput.trim())) {
                System.out.println(contact.getName() + " " + contact.getPhoneNumber());
                contactFound = true;
                break;
            }
        }
        if (!contactFound) {
            System.out.println("Sorry, no one with that name!");
        }
    }

    public static void saveContactList() {
        Path contactsFile = Paths.get(directory, fileName);
        try {
            List<String> contactLines = new ArrayList<>();
            for (Contact contact : contactInfo) {
                contactLines.add(contact.getName() + "," + contact.getPhoneNumber());
            }
            Files.write(contactsFile, contactLines);  // Write the contact information to the file
        } catch (IOException e) {
            System.err.println("Error saving contact list: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadContactList() {
        Path contactsFile = Paths.get(directory, fileName);
        try {
            List<String> lines = Files.readAllLines(contactsFile);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    long phoneNumber = Long.parseLong(parts[1]);
                    contactInfo.add(new Contact(name, phoneNumber));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading contact list: " + e.getMessage());
        }
    }


    public static void renderContactList() {
        if (contactInfo.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            System.out.printf("%-20s%-15s\n", "Name", "Phone Number");
            for (Contact contact : contactInfo) {
                System.out.printf("%-20s%-15s\n", contact.getName(), contact.getPhoneNumber());
            }
        }
    }



    public static void mainMenu() {
        System.out.printf("1.View Contacts. \n");
        System.out.printf("2.Add a new contact. \n");
        System.out.printf("3.Search a contact by name. \n");
        System.out.printf("4.Delete a contact. \n");
        System.out.printf("5.Exit. \n");
        System.out.printf("What would you like to do today?(Enter a number 1-5)\n ");
        System.out.printf("- - - - - - - - - - - - - - - - - - - - - - - - - - \n");
        int userInput = userResponse.getInt(1, 5);
            if (userInput == 1) {
                renderContactList();
            } else if (userInput == 2) {
                addContact();
            } else if (userInput == 3) {
                searchContact();
            } else if (userInput == 4) {
                deleteContact();
            } else if (userInput == 5) {
                saveContactList();
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }

    public static void main(String[] args) {
        createDirectoryFile();
        loadContactList();

        boolean quit = false;
        while (!quit) {
            mainMenu();
            System.out.println("Do you want to quit? (yes/no)");
            String userChoice = userResponse.getString().toLowerCase();
            if (userChoice.equals("yes")) {
                saveContactList();
                System.out.println("Goodbye!");
                quit = true;
            }
        }
    }


}

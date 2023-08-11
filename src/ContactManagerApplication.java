import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import util.Input;

import static java.lang.Long.parseLong;

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
    public static String formatNumber (long phoneNumber) {
        String input = Long.toString(phoneNumber);
        if (input.length() >= 10) {
            String output = "(" + input.substring(0, 3) + ") " + input.substring(3, 6) + "-" + input.substring(6, 10);
            return output;
        } else  {
            String output2  =  input.substring(0, 3) + "-" + input.substring(3, 7);
            return output2;
        }
    }


    public static void addContact() {
        System.out.println("Enter the name!");
        String userInput = userResponse.getString();
        System.out.println("Enter the phone number");
        Long userNumber = userResponse.getLong();
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
                    long phoneNumber = parseLong(parts[1]);
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
            System.out.printf("%-20s   |  %-15s    |\n", "Name", "Phone Number");
            System.out.printf("_____________________________________________|\n");
            for (Contact contact : contactInfo) {
                System.out.printf("%-20s   |  %-15s    |\n", contact.getName(), formatNumber(contact.getPhoneNumber()));
            }
            System.out.printf("\n");
        }
    }


    public static void mainMenu() {
        System.out.printf("1.View Contacts. \n");
        System.out.printf("2.Add a new contact. \n");
        System.out.printf("3.Search a contact by name. \n");
        System.out.printf("4.Delete a contact. \n");
        System.out.printf("5.Feeling frisky?\n");
        System.out.printf("6.Exit. \n");
        System.out.printf("What would you like to do today?(Enter a number 1-6)\n ");
        System.out.printf("- - - - - - - - - - - - - - - - - - - - - - - - - - \n");
        int userInput = userResponse.getInt(1, 6);
            if (userInput == 1) {
                renderContactList();
            } else if (userInput == 2) {
                addContact();
            } else if (userInput == 3) {
                searchContact();
            } else if (userInput == 4) {
                deleteContact();
            } else if (userInput == 5) {
                kittyCat();
            } else if (userInput == 6) {
                saveContactList();
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }

        public static void kittyCat () {
            System.out.print("""
                    
                                             \s
                           \\`*-.                   \s
                            )  _`-.                \s
                           .  : `. .               \s
                           : _   '  \\              \s
                           ; *` _.   `*-._         \s
                           `-.-'          `-.      \s
                             ;       `       `.    \s
                             :.       .        \\   \s
                             . \\  .   :   .-'   .  \s
                             '  `+.;  ;  '      :  \s
                             :  '  |    ;       ;-.\s
                             ; '   : :`-:     _.`* ;
                    [bug] .*' /  .*' ; .*`- +'  `*'\s
                          `*-*   `*-*  `*-*'
                    """);
        }

    public static void main(String[] args) {
        createDirectoryFile();
        loadContactList();

        boolean quit = false;
        while (!quit) {
            mainMenu();
            System.out.println("Return to main menu? (yes/no)");
            String userChoice = userResponse.getString().toLowerCase();
            if (userChoice.equals("no")) {
                saveContactList();
                System.out.println("Goodbye!");
                quit = true;
            }
        }
    }


}

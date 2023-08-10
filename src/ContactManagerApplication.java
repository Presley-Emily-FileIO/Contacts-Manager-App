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

    static ArrayList<Contact> contactInfo;

    public ContactManagerApplication(String name, long phoneNumber) {
        super(name, phoneNumber);
    }

    public void createDirectoryFile() {
        String directory = "Contacts";
        String fileName = "contacts.txt";

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
        Path p = Paths.get("Contacts/contacts.txt");
        System.out.println("Enter the name!");
        String userInput = userResponse.getString();
        System.out.println("Enter the phone number");
        long userNumber = userResponse.getLong();
        contactInfo = new ArrayList<>();
        contactInfo.add(new Contact(userInput, userNumber));
        renderContactList();
    }

    public static List<Contact> renderContactList() {
        Path p = Paths.get("Contacts/contacts.txt");
        contactInfo = new ArrayList<>();
        contactInfo.add(new Contact("Emily", 2101234567));
        contactInfo.add(new Contact("Presley", 2101234567));
        contactInfo.add(new Contact("Draco", 2101234567));
        contactInfo.add(new Contact("Hermione", 2101234567));
        contactInfo.add(new Contact("Hagrid", 2101234567));
        contactInfo.add(new Contact("Ron", 2101234567));
        contactInfo.add(new Contact("Luna", 2101234567));

        System.out.printf("%-10s%-10s\n", "name", "phone number");
        for (Contact contact : contactInfo) {
            System.out.printf(String.format("%-10s%-10s\n", contact.getName(), contact.getPhoneNumber()));
        }
        return contactInfo;
    }


    public static void mainMenu(){
        System.out.printf("1.View Contacts. \n");
        System.out.printf("2.Add a new contact. \n");
        System.out.printf("3.Search a contact by name. \n");
        System.out.printf("4.Delete a contact. \n");
        System.out.printf("5.Exit. \n");
        System.out.printf("What would you like to do today?(Enter a number 1-5)\n ");
        System.out.printf("- - - - - - - - - - - - - - - - - - - - - - - - - - \n");
        int userInput = userResponse.getInt(1,5);
        if (userInput == 1){
            renderContactList();
//        } else if (userInput == 2) {
//            addContact();
//        } else if (userInput == 3) {
//            searchContact();
//        } else if (userInput == 4) {
//            deleteContact();
//        } else if (userInput == 5) {
//            exitMenu;
        }
    }

    public static void main(String[] args) {
        mainMenu();
        addContact();
    }


}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import util.Input;

public class ContactManagerApplication extends Contact {

    static Input userResponse = new Input();

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

//    public void addContact (Contact contact) {

//            Contact.add(userResponse.getString());

//    }

    public static List<Contact> renderContactList() {
        Path p = Paths.get("Contacts/contacts.txt");

        Contact contact1 = new Contact("Emily", 2101234567);
        Contact contact2 = new Contact("Presley", 2101234567);
        Contact contact3 = new Contact("Draco", 2101234567);
        Contact contact4 = new Contact("Hermione", 2101234567);
        Contact contact5 = new Contact("Ron", 2101234567);
        Contact contact6 = new Contact("Luna", 2101234567);
        Contact contact7 = new Contact("Hagrid", 2101234567);


        List<Contact> contactInfo = new ArrayList<>();
        contactInfo.add(contact1);
        contactInfo.add(contact2);
        contactInfo.add(contact3);
        contactInfo.add(contact4);
        contactInfo.add(contact5);
        contactInfo.add(contact6);
        contactInfo.add(contact7);

        System.out.println(toString(contactInfo));
        return contactInfo;

    }

    public static String toString(List<Contact> contact) {
        return name + phoneNumber;
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
        } else if (userInput == 2) {
            addContact();
        } else if (userInput == 3) {
            searchContact();
        } else if (userInput == 4) {
            deleteContact();
        } else if (userInput == 5) {
            exitMenu;
        }
    }

    public static void main(String[] args) {
        renderContactList();
        mainMenu();
    }


}

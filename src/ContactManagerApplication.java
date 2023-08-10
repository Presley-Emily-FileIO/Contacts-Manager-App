import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactManagerApplication {
    public void createDirectoryFile(){
        String directory = "Contacts";
        String fileName = "contacts.txt";
        Path contactsDirectory = Paths.get(directory);
        Path contactsFile = Paths.get(fileName);
        if (!Files.exists(contactsDirectory)){
            try {
                Files.createDirectories(contactsDirectory);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        if (!Files.exists(contactsFile)){
            try {
                Files.createFile(contactsFile);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }







}

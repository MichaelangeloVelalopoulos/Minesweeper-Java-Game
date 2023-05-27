import java.io.File;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static void createTextFile(String location, String filename, String content) {
        try {
            File file = new File(location + "/" + filename + ".txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

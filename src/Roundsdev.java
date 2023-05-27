import java.io.*;
/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;*/

public class Roundsdev {
    private final static String filename = "src/mines/rounds.txt";
    public static int[] Roundsreadline(int lineNumber) throws IOException {
        int[] result = new int[4];
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int currentLine = 0;
            while ((line = br.readLine()) != null) {
                currentLine++;
                if (currentLine == lineNumber) {
                    String[] numbers = line.trim().split(" ");
                    for (int i = 0; i < 4; i++) {
                        result[i] = Integer.parseInt(numbers[i]);
                    }
                    break;
                }
            }
        }
        return result;
    }
    public static void Roundsedit(int minenum, int clicks, int timeleft, int winner) throws IOException {
        // Open the file for reading and writing
        String textToWrite = minenum + " " + clicks + " " + timeleft + " " + winner + "\n";
        RandomAccessFile file = new RandomAccessFile(filename, "rw");

        // Write the new string to the beginning of the file
        long fileLength = file.length();
        byte[] fileBytes = new byte[(int) fileLength];
        file.readFully(fileBytes);
        file.seek(0);
        file.writeBytes(textToWrite);
        file.write(fileBytes);
        file.close();
    }

}

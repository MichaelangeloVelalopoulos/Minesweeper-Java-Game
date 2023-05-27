import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TextFileParser {
    private static final int MAX_VARIABLE_1_VALUE = 100; // Maximum value for Variable 1
    private static final int MAX_TIME_VALUE = 3600; // Maximum value for Time in seconds

    public static int[] parseTextFile(String filePath)
            throws IOException, InvalidDescriptionException, InvalidValueException {
        BufferedReader reader = null;
        int[] gamevars = new int[4];
        try {
            reader = new BufferedReader(new FileReader(filePath));
            // Parse first line
            String line = reader.readLine();
            if (line == null) {
                throw new InvalidDescriptionException("Invalid first line");
            } else if (!line.equals("1") && !line.equals("2")) {
                throw new InvalidValueException("Invalid difficulty setting");
            }
            gamevars[0] = Integer.parseInt(line);

            // Parse second line
            line = reader.readLine();
            if (line == null) {
                throw new InvalidDescriptionException("Missing second line");
            }
            gamevars[1] = Integer.parseInt(line);
            if (!(gamevars[0] == 1 && gamevars[1] > 8 && gamevars[1] < 12 || gamevars[0] == 2 && gamevars[1] > 34 && gamevars[1] < 46)) {
                throw new InvalidValueException("Invalid value for number of mines");
            }

            // Parse third line
            line = reader.readLine();
            if (line == null) {
                throw new InvalidDescriptionException("Missing third line");
            }
            gamevars[2] = Integer.parseInt(line);
            if (!(gamevars[0] == 1 && gamevars[2] > 119 && gamevars[2] < 181 || gamevars[0] == 2 && gamevars[2] > 239 && gamevars[2] < 361)) {
                throw new InvalidValueException("Invalid value for Game Time");
            }

            // Parse fourth line
            line = reader.readLine();
            if (line == null || (!line.equals("0") && !line.equals("1"))) {
                throw new InvalidDescriptionException("Invalid fourth line");
            }
            gamevars[3] = Integer.parseInt(line);
            if (gamevars[0] == 1 && gamevars[3] != 0) {
                throw new InvalidValueException("Easy mode can't have supermine");
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Ignore exception
                }
            }
            reader.close();
        }
        return gamevars;
    }
}

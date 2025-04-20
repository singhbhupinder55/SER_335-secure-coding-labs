import java.io.*;

public class Task2_FileTest {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java FileTest <filename>");
            return;
        }

        File file = new File(args[0]);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

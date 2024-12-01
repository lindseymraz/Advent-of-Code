package shared;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Utilities {
    public static int charsPerLine(String path) throws IOException {
        Scanner scanner = new Scanner(Paths.get(path));
        String line = scanner.nextLine();
        scanner.close();
        return line.length();
    }

    public static int linesPerFile(String path) throws IOException {
        Scanner scanner = new Scanner(Paths.get(path));
        int lines = 0;
        while(scanner.hasNextLine()) {
            scanner.nextLine();
            lines++;
        }
        scanner.close();
        return lines;
    }
}

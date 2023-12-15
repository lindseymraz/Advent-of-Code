import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
    private static final String testPath = "src/tests/Day15Part1Test.txt";
    private static final String realPath = "Day15.txt";
    static String Path = realPath;
    static int day15part1() throws IOException {
        int toReturn = 0;
        Scanner scanner = new Scanner(Paths.get(Path));
        Pattern notCommas = Pattern.compile("[^,]*?(?=,)|(?<=,)[^,]*");
        Matcher notCommasMatcher = notCommas.matcher(scanner.nextLine());
        while(notCommasMatcher.find()) {
            toReturn += hashString(notCommasMatcher.group());
        }
        return toReturn;
    }
    static int hashHelper(char aChar, int currentValue) {
        int toReturn = currentValue;
        toReturn += (int) aChar;
        toReturn *= 17;
        toReturn %= 256;
        return toReturn;
    }

    static int hashString(String toHash) {
        int toReturn = 0;
        for(int i = 0; i < toHash.length(); i++) {
            toReturn = hashHelper(toHash.charAt(i), toReturn);
        }
        return toReturn;
    }

    static void setPathToTestPath() {
        Path = testPath;
    }
}

package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

    static final String realPath = "y2023.Day13.txt";
    public static final String testPath1 = "src/y2023.tests/Day13Part1Test.txt";
    public static final String testPath2 = "src/y2023.tests/Day13Part1Test2.txt";

    public static String Path = realPath;
    static int patternsInFile = 0;

    public static int day13part1() throws IOException {
        String[][] patterns = makePatternsArray();
        int toReturn = 0;
        for (String[] pattern : patterns) {
            toReturn += linesLeftOfVerticalLineOfReflection(pattern) + (100 * rowsAboveHorizontalLineOfReflection(pattern));
        }
        return toReturn;
    }
    public static int countPatternsInFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get(Path));
        int patterns = 1;
        while(scanner.hasNextLine()) {
            if(scanner.nextLine().isEmpty()) {
                patterns++;
            }
        }
        scanner.close();
        return patterns;
    }

    public static int[] makeLinesInPatternsArray() throws IOException {
        Scanner scanner = new Scanner(Paths.get(Path));
        setPatternsInFile();
        int[] linesInPatterns = new int[patternsInFile];
        int pattern = 0;
        int lines = 0;
        while(scanner.hasNextLine()) {
            while(scanner.hasNextLine() && !scanner.nextLine().isEmpty()) {
                lines++;
            }
            linesInPatterns[pattern] = lines;
            lines = 0;
            pattern++;
        }
        scanner.close();
        return linesInPatterns;
    }

    public static String[][] makePatternsArray() throws IOException {
        int[] linesInPatterns = makeLinesInPatternsArray();
        String[][] patterns = new String[patternsInFile][];
        Scanner scanner = new Scanner(Paths.get(Path));
        for (int i = 0; i < linesInPatterns.length; i++) {
            String[] pattern = new String[linesInPatterns[i]];
            for (int j = 0; j < linesInPatterns[i]; j++) {
                if(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(line.isEmpty() && scanner.hasNextLine()) {
                        line = scanner.nextLine();
                    }
                    pattern[j] = line;
                }
            } patterns[i] = pattern;
        }
        return patterns;
    }

    static void setPatternsInFile() throws IOException {
        patternsInFile = countPatternsInFile();
    }

    public static boolean linesMirror(String string1, String string2) {
        int length = string1.length();
        for(int i = 0; i < length; i++) {
            if(!string1.substring(i, i + 1).equals(string2.substring(length - i - 1, length - i))) {
                return false;
            }
        } return true;
    }

    public static int linesLeftOfVerticalLineOfReflection(String patternLines[]) {
        String string1 = "";
        String string2 = "";
        String substring = "";
        int lines = patternLines.length;
        int length = patternLines[0].length();
        for (int i = 0; i < length - 1; i++) {
            substring = patternLines[0].substring(i, i + 2);
            if (substring.equals("..") || substring.equals("##")) {
                int lineOfReflectionIndex = i + 1;
                int mirroredLines = 0;
                for (int j = 0; j < lines; j++) {
                    string1 = patternLines[j].substring(0, lineOfReflectionIndex);
                    string2 = patternLines[j].substring(lineOfReflectionIndex, length);
                    if (string1.length() > string2.length()) {
                        string1 = patternLines[j].substring(lineOfReflectionIndex - string2.length(), lineOfReflectionIndex);
                    } else {
                        string2 = patternLines[j].substring(lineOfReflectionIndex, lineOfReflectionIndex + string1.length());
                    }
                    if (linesMirror(string1, string2)) {
                        mirroredLines++;
                    } else {
                        break;
                    }
                }
                if (mirroredLines == lines) {return lineOfReflectionIndex;}
            }
        }
        return 0;
    }

    public static boolean checkReflects(String patternLines[], int line1index, int line2index) {
        int limit = patternLines.length - 1 - line2index;
        if(limit > line1index) { limit = line1index; }
        for(int i = 0; i < limit; i++) {
            if(!patternLines[line1index - (i + 1)].equals(patternLines[line2index + (i + 1)])) {
                return false;
            }
        }
        return true;
    }

    public static int rowsAboveHorizontalLineOfReflection(String[] patternLines) {
        String line1 = patternLines[0];
        String line2 = "";
        for(int i = 1; i < patternLines.length; i++) {
            line2 = patternLines[i];
            if(line1.equals(line2)) {
                if(checkReflects(patternLines, i-1, i)) {
                    return i;
                }
            }
            else { line1 = line2; }
        }
        return 0;
    }

}

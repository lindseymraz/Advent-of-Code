import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day10 {

    private static final String realPath = "Day10.txt";
    private static final String testPath1 = "src/tests/Day10Part1Test.txt";
    private static final String testPath2 = "src/tests/Day10Part1Test2.txt";
    private static final String testPath3 = "src/tests/Day10Part1Test3.txt";
    private static final String testPath4 = "src/tests/Day10Part1Test4.txt";
    private static final String Path = realPath;

    public enum DIR {
        ABOVE, BELOW, LEFT, RIGHT;
    }

    public class forReturn {
        DIR from;

        int xcoord;
        String symbol;

        int ycoord;

        public forReturn(DIR from, String symbol, int xcoord, int ycoord) {
            this.from = from;
            this.symbol = symbol;
            this.xcoord = xcoord;
            this.ycoord = ycoord;
        }
    }

    Day10() {

    }

    public static int day10part1() throws Exception {
        Day10 a = new Day10();
        String[] grid = readFile();
        int acc = 1;
        forReturn returnAcc = a.getNeighbor(grid, getStartLine(grid), grid[getStartLine(grid)].indexOf("S"), DIR.ABOVE);
        while(!returnAcc.symbol.equals("S")) {
            returnAcc = a.getNeighbor(grid, returnAcc.ycoord, returnAcc.xcoord, returnAcc.from);
            acc++;
        }
        return (((acc - 1)/2) + 1);
    }

    forReturn getNeighbor(String[] grid, int currLine, int currStart, DIR cameFrom) throws Exception {
        String curr = grid[currLine].substring(currStart, currStart + 1);
        if((cameFrom != DIR.ABOVE) && hasAbove(currLine)) {
            String above = getAbove(grid, currLine, currStart);
            if(centerConnectsToAbove(curr, above)) {
                return new forReturn(DIR.BELOW, above, currStart, currLine - 1);
            }
        }
        if((cameFrom != DIR.BELOW) && hasBelow(grid, currLine)) {
            String below = getBelow(grid, currLine, currStart);
            if(centerConnectsToBelow(curr, below)) {
                return new forReturn(DIR.ABOVE, below, currStart, currLine + 1);
            }
        }
        if((cameFrom != DIR.LEFT) && hasLeft(currStart)) {
            String left = getLeft(grid, currLine, currStart);
            if(centerConnectsToLeft(curr, left)) {
                return new forReturn(DIR.RIGHT, left, currStart - 1, currLine);
            }
        }
        if((cameFrom != DIR.RIGHT) && hasRight(grid, currStart)) {
            String right = getRight(grid, currLine, currStart);
            if(centerConnectsToRight(curr, right)) {
                return new forReturn(DIR.LEFT, right, currStart + 1, currLine);
            }
        }
        throw new Exception();
    }

    static boolean hasAbove(int currLine) {
        return(currLine != 0);
    }

    static boolean hasBelow(String[] grid, int currLine) {
        return(currLine != (grid.length - 1));
    }

    static boolean hasLeft(int currStart) {
        return(currStart != 0);
    }

    static boolean hasRight(String[] grid, int currStart) {
        return(currStart != grid[0].length() - 1);
    }
    static String getAbove(String[] grid, int currLine, int currStart) {
        return grid[currLine - 1].substring(currStart, currStart + 1);
    }

    static String getBelow(String[] grid, int currLine, int currStart) {
        return grid[currLine + 1].substring(currStart, currStart + 1);
    }

    static String getLeft(String[] grid, int currLine, int currStart) {
        return grid[currLine].substring(currStart - 1, currStart);
    }

    static String getRight(String[] grid, int currLine, int currStart) {
        return grid[currLine].substring(currStart + 1, currStart + 2);
    }

    static int getStartLine(String[] grid) throws Exception {
        for(int i = 0; i < grid.length; i++) {
            if(grid[i].contains("S")) {
                return i;
            }
        }
        throw new Exception();
    }

    static String[] readFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get(Path));
        int lineCount = 0;
        while(scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        Scanner another = new Scanner(Paths.get(Path));
        String[] toReturn = new String[lineCount];
        int i = 0;
        while(another.hasNextLine()) {
            toReturn[i] = another.nextLine();
            i++;
        }
        return toReturn;
    }
    static boolean centerConnectsToAbove(String center, String above) throws Exception {
        switch(center) {
            case "|", "L", "J", "S": switch (above) {
                case "|", "7", "F", "S": return true;
                default: return false;
            }
            case "-", "7", "F", ".": return false;
            default: throw new Exception();
        }
    }

    static boolean centerConnectsToBelow(String center, String below) throws Exception {
        switch(center) {
            case "|", "7", "F", "S": switch (below) {
                case "|", "L", "J", "S": return true;
                default: return false;
            }
            case "-", "L", "J", ".": return false;
            default: throw new Exception();
        }
    }
    static boolean centerConnectsToLeft(String center, String left) throws Exception {
        switch(center) {
            case "-", "J", "7", "S": switch (left) {
                case "-", "L", "F", "S": return true;
                default: return false;
            }
            case "|", "L", "F", ".": return false;
            default: throw new Exception();
        }
    }

    static boolean centerConnectsToRight(String center, String right) throws Exception {
        switch(center) {
            case "-", "L", "F", "S": switch (right) {
                case "-", "J", "7", "S": return true;
                default: return false;
            }
            case "|", "J", "7", ".": return false;
            default: throw new Exception();
        }
    }
}

package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class Day16 {
    private static final String testPath = "src/y2023.tests/Day16Part1Test.txt";

    private static final String realPath = "y2023.Day16.txt";
    static String Path = realPath;

    static int linesPerFile = 0;
    static int charsPerLine = 0;

    static Day16 a = new Day16();

    static LinkedList<State> startList = new LinkedList<State>();

    static LinkedList<State> visited = new LinkedList<State>();
    static class coordinateSet {
        int x;
        int y;
        coordinateSet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean outOfBounds(int xBound, int yBound) {
            return(this.x > xBound || this.y > yBound || this.x < 0 || this.y < 0);
        }

        public boolean equals(Object other) {
            coordinateSet otherSet = (coordinateSet) other;
            return(this.x == otherSet.x && this.y==otherSet.y);
        }
    }

    enum dirs { UP, DOWN, LEFT, RIGHT;
        coordinateSet getNewCoords(int currentX, int currentY) throws Exception {
            switch(this) {
                case UP: return new coordinateSet(currentX, currentY - 1);
                case DOWN: return new coordinateSet(currentX, currentY + 1);
                case LEFT: return new coordinateSet(currentX - 1, currentY);
                case RIGHT: return new coordinateSet(currentX + 1, currentY);
                default: throw new Exception();
            }
        }

        dirs getNewDir(int currentX, int currentY, dirs currDir, chars[][] layoutGrid) throws Exception {
            coordinateSet set = currDir.getNewCoords(currentX, currentY);
            chars foundAtNewCoords = layoutGrid[set.y][set.x];
            switch(foundAtNewCoords) {
                case EMPTY: return currDir;
                case MIRRORLEFT: switch(currDir) {
                    case UP: return LEFT;
                    case LEFT: return UP;
                    case DOWN: return RIGHT;
                    case RIGHT: return DOWN;
                }
                case MIRRORRIGHT: switch(currDir) {
                    case UP: return RIGHT;
                    case LEFT: return DOWN;
                    case DOWN: return LEFT;
                    case RIGHT: return UP;
                }
                case SPLITTERVERT: switch(currDir) {
                    case UP, DOWN: return currDir;
                    case LEFT, RIGHT: Day16.split(DOWN, set.x, set.y); return UP;
                }
                case SPLITTERHORIZ: switch(currDir) {
                    case LEFT, RIGHT: return currDir;
                    case UP, DOWN: Day16.split(RIGHT, set.x, set.y); return LEFT; default:
                }
                default: throw new Exception();
            }
        }
    }

    enum chars { EMPTY, MIRRORLEFT, MIRRORRIGHT, SPLITTERVERT, SPLITTERHORIZ; }

    class State {
        dirs dir;
        int x;
        int y;

        public State(dirs dir, int x, int y) {
            this.dir = dir;
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object other) {
            State otherState = (State) other;
            return(this.dir == otherState.dir && this.x == otherState.x && this.y == otherState.y);
        }
    }

    public static int day16part2() throws Exception {
        linesPerFile = Utilities.linesPerFile(Path);
        charsPerLine = Utilities.charsPerLine(Path);
        chars[][] layoutGrid = createLayoutGrid();
        boolean[][] energizedGrid = createEnergizedGrid();
        int highest = 0;
        highest = day16part2helper(highest, dirs.UP, layoutGrid, energizedGrid);
        highest = day16part2helper(highest, dirs.LEFT, layoutGrid, energizedGrid);
        highest = day16part2helper(highest, dirs.DOWN, layoutGrid, energizedGrid);
        highest = day16part2helper(highest, dirs.RIGHT, layoutGrid, energizedGrid);
        return highest;
    }

    public static int day16part2helper(int highest, dirs dir, chars[][] layoutGrid, boolean[][] energizedGrid) throws Exception {
        int count = 0;
        int limitForLoop = 0;
        switch(dir) {
            case UP, DOWN: limitForLoop = charsPerLine;
            case LEFT, RIGHT: limitForLoop = linesPerFile;
        }
        for(int j = 0; j < limitForLoop; j++) {
            boolean done = false;
            switch(dir) {
                case UP: traverse(a.new State(dirs.UP, j, linesPerFile), layoutGrid, energizedGrid); break;
                case DOWN: traverse(a.new State(dirs.DOWN, j, -1), layoutGrid, energizedGrid); break;
                case LEFT: traverse(a.new State(dirs.LEFT, charsPerLine, j), layoutGrid, energizedGrid); break;
                case RIGHT: traverse(a.new State(dirs.RIGHT, -1, j), layoutGrid, energizedGrid); break;
            }
            while (!done) {
                if (startList.isEmpty()) {
                    done = true;
                }
                int limit = startList.size();
                LinkedList<State> temp = (LinkedList<State>) startList.clone();
                for (int i = 0; i < limit; i++) {
                    traverse(temp.get(i), layoutGrid, energizedGrid);
                }
                for (int i = 0; i < limit; i++) {
                    startList.remove();
                }
            }
            count = countEnergizedTiles(energizedGrid);
            if (count > highest) {
                highest = count;
            }
            resetEnergizedGrid(energizedGrid);
            visited.clear();
        }
        return highest;
    }

    public static int day16part1() throws Exception {
        linesPerFile = Utilities.linesPerFile(Path);
        charsPerLine = Utilities.charsPerLine(Path);
        chars[][] layoutGrid = createLayoutGrid();
        boolean[][] energizedGrid = createEnergizedGrid();
        traverse(a.new State(dirs.RIGHT, -1, 0), layoutGrid, energizedGrid);
        boolean done = false;
        while(!done) {
            if(startList.isEmpty()) {
                done = true;
            }
            int limit = startList.size();
            LinkedList<State> temp = (LinkedList<State>) startList.clone();
            for(int i = 0; i < limit; i++) {
                traverse(temp.get(i), layoutGrid, energizedGrid);
            }
            for(int i = 0; i < limit; i++) {
                startList.remove();
            }
        }
        return countEnergizedTiles(energizedGrid);
    }

    public State traverseHelper(dirs dir, int xCoord, int yCoord, chars[][] layoutGrid, boolean[][] energizedGrid) throws Exception {
        coordinateSet coords = dir.getNewCoords(xCoord, yCoord);
        return new State(dir.getNewDir(xCoord, yCoord, dir, layoutGrid), coords.x, coords.y);
    }

    public static void traverse(State start, chars[][] layoutGrid, boolean[][] energizedGrid) throws Exception {
        coordinateSet coords = start.dir.getNewCoords(start.x, start.y);
        State state = start;
        visited.add(state);
        while(!coords.outOfBounds(charsPerLine - 1, linesPerFile - 1)) {
            state = a.traverseHelper(state.dir, state.x, state.y, layoutGrid, energizedGrid);
            if(visited.contains(state)) {
                break;
            }
            energizedGrid[state.y][state.x] = true;
            visited.add(state);
            coords = state.dir.getNewCoords(state.x, state.y);
        }
    }

    static void split(Day16.dirs newDir, int newX, int newY) {
        startList.add(a.new State(newDir, newX, newY));
    }

    static chars[][] createLayoutGrid() throws IOException {
        chars[][] toReturn = new chars[linesPerFile][charsPerLine];
        Scanner scanner = new Scanner(Paths.get(Path));
        String line = "";
        for(int i = 0; i < linesPerFile; i++) {
            if(scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
            for(int j = 0; j < charsPerLine; j++) {
                switch(line.substring(j, j + 1)) {
                    case ".": toReturn[i][j] = chars.EMPTY; break;
                    case "/": toReturn[i][j] = chars.MIRRORRIGHT; break;
                    case "\\": toReturn[i][j] = chars.MIRRORLEFT; break;
                    case "|": toReturn[i][j] = chars.SPLITTERVERT; break;
                    case "-": toReturn[i][j] = chars.SPLITTERHORIZ; break;
                    default: throw new IOException();
                }
            }
        }
        scanner.close();
        return toReturn;
    }

    static boolean[][] createEnergizedGrid() throws IOException {
        boolean[][] toReturn = new boolean[linesPerFile][charsPerLine];
        Scanner scanner = new Scanner(Paths.get(Path));
        String line = "";
        for(int i = 0; i < linesPerFile; i++) {
            for(int j = 0; j < charsPerLine; j++) {
                toReturn[i][j] = false;
            }
        }
        scanner.close();
        return toReturn;
    }

    static void resetEnergizedGrid(boolean[][] energizedGrid) throws IOException {
        for(int i = 0; i < linesPerFile; i++) {
            for(int j = 0; j < charsPerLine; j++) {
                energizedGrid[i][j] = false;
            }
        }
    }

    static int countEnergizedTiles(boolean[][] energizedGrid) {
        int rows = energizedGrid.length;
        int columns = energizedGrid[0].length;
        int toReturn = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(energizedGrid[i][j]) {
                    toReturn++;
                }
            }
        }
        return toReturn;
    }

    static void setPathToTestPath() throws IOException {
        Path = testPath;
        linesPerFile = Utilities.linesPerFile(Path);
        charsPerLine = Utilities.charsPerLine(Path);
    }
}

package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
    private static final String testPath = "src/y2023.tests/Day15Part1Test.txt";
    private static final String realPath = "y2023.Day15.txt";
    static String Path = realPath;

    public class Lens {
        String label;
        int focalLength;
        public Lens(String label, int focalLength) {
            this.label = label;
            this.focalLength = focalLength;
        }

        public boolean equals(Object other) {
            Lens otherLens = (Lens) other;
            return (this.label.equals(otherLens.label) &&
                    (this.focalLength == otherLens.focalLength));
        }
    }

    public class Box {
        public ArrayList<Lens> lenses;
        public Box() {
            this.lenses = new ArrayList<Lens>();
        }

        //also moves the following lenses up
        public void removeLensWithLabel(String label) {
            for(int i = 0; i < this.lenses.size(); i++) {
                if(lenses.get(i).label.equals(label)) {
                    lenses.remove(lenses.get(i));
                }
            }
        }

        public void addNewLens(String label, int focalLength) {
            if(this.lenses.isEmpty()) {
                this.lenses.add(0, new Lens(label, focalLength));
            } else {
                boolean foundLensWithSameLabel = false;
                for(int i = 0; i < this.lenses.size(); i++) {
                    if(lenses.get(i).label.equals(label)) {
                        lenses.set(i, new Lens(label, focalLength));
                        foundLensWithSameLabel = true;
                        break;
                    }
                }
                if(!foundLensWithSameLabel) {
                    this.lenses.add(new Lens(label, focalLength));
                }
            }
        }

        public boolean equals(Object other) {
            Box otherBox = (Box) other;
            return (this.lenses.equals(otherBox.lenses));
        }
    }

    public static int day15part2() throws IOException {
        Day15 a = new Day15();
        Box[] boxes = new Box[256];
        Scanner scanner = new Scanner(Paths.get(Path));
        Pattern step = Pattern.compile("([a-zA-Z]+)([\\-|=])(\\d+)?");
        Matcher stepMatcher = step.matcher(scanner.nextLine());
        String letters = "";
        while(stepMatcher.find()) {
            letters = stepMatcher.group(1);
            int boxNumWanted = hashString(letters);
            if(boxes[boxNumWanted] == null) {
                boxes[boxNumWanted] = a.new Box();
            }
            if(stepMatcher.group(2).matches("=")) {
                boxes[boxNumWanted].addNewLens(letters, Integer.parseInt(stepMatcher.group(3)));
            } else {
                boxes[boxNumWanted].removeLensWithLabel(letters);
            }
        }
        return getTotalFocusingPower(boxes);
    }
    public static int day15part1() throws IOException {
        int toReturn = 0;
        Scanner scanner = new Scanner(Paths.get(Path));
        Pattern notCommas = Pattern.compile("[^,]*?(?=,)|(?<=,)[^,]*");
        Matcher notCommasMatcher = notCommas.matcher(scanner.nextLine());
        while(notCommasMatcher.find()) {
            toReturn += hashString(notCommasMatcher.group());
        }
        return toReturn;
    }
    public static int hashHelper(char aChar, int currentValue) {
        int toReturn = currentValue;
        toReturn += (int) aChar;
        toReturn *= 17;
        toReturn %= 256;
        return toReturn;
    }

    public static int hashString(String toHash) {
        int toReturn = 0;
        for(int i = 0; i < toHash.length(); i++) {
            toReturn = hashHelper(toHash.charAt(i), toReturn);
        }
        return toReturn;
    }

    public static int getTotalFocusingPower(Box[] boxes) {
        int toReturn = 0;
        int size = boxes.length;
        for(int i = 0; i < size; i++) {
            if(boxes[i] != null) {
                for(int j = 0; j < boxes[i].lenses.size(); j++) {
                    toReturn += ((i + 1) * (j + 1) * boxes[i].lenses.get(j).focalLength);
                }
            }
        }
        return toReturn;
    }

    public static void setPathToTestPath() {
        Path = testPath;
    }
}

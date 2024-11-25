package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.toIntExact;

public class Day8 {

    private static final String realPath = "y2023.Day8.txt";
    private static final String testPath1 = "src/y2023.tests/Day8Part1Test1.txt";
    private static final String testPath2 = "src/y2023.tests/Day8Part1Test2.txt";
    private static final String testPath3 = "src/y2023.tests/Day8Part2Test.txt";
    private static final String Path = realPath;
    private static String directions = "";
    private static Map<String, Node> map;
    static long count = 0;
    static long part2Count = 0; //for when you've moved on all of your As
    static int tempCount = 0;

    static int lastPlaceInString = 0;
    class Node {

        String name;
        String left;
        String right;

        public Node(String name, String left, String right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }
    }
    static public long day8part2() throws IOException {
        try {
            map = makeMap();
            boolean there = false;
            ArrayList<String> starts = makeStarts();
            ArrayList<String> dests = (ArrayList<String>) makeStarts().clone();
            int placeToStart = 0;
            while(!there) {
                long highest = 0;
                int Zs = 0;
                for (int i = 0; i < starts.size(); i++) {//find the one that took the most steps to hit Z
                    lastPlaceInString = placeToStart;
                    traversalPart2(dests.get(i), true); //traverse till you hit a Z, starting from the spot we saved in the next for loop
                    if(highest < count) {
                        highest = count;
                    }
                    count = 0;
                }
                for (int i = 0; i < starts.size(); i++) { //move them all up to that spot, and count how many still end in Z, save spot
                    lastPlaceInString = placeToStart;
                    //start from the place you started the first time, which is usually the same as where you ended here
                    String endedAt = traversalPart2V2(dests.get(i), highest);
                    if(endedAt.matches("..Z")) {
                       Zs++;
                    }
                    dests.set(i, endedAt);
                    count = 0;
                }
                placeToStart = lastPlaceInString;
                part2Count += highest;
                if(Zs == starts.size()) {
                    return part2Count;
                }
            }
        } catch (Exception e) {
            throw new IOException();
        }
        return 0;
    }

    static ArrayList<String> makeStarts() throws IOException {
        ArrayList<String> starts = new ArrayList<>();
        Scanner scanner = new Scanner(Paths.get(Path));
        directions = scanner.nextLine();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.matches("..A.*")) {
                starts.add(line.substring(0, 3));
            }
        }
        return starts;
    }

    static String traversalPart2V2(String dest, long highest) {
        String result = traversalPart2V2Helper(dest, highest);
        while(count != highest) {
            result = traversalPart2V2(result, highest);
        }
        return result;
    }

    static String traversalPart2V2Helper(String dest, long highest) {
        if(lastPlaceInString == directions.length()) {
            lastPlaceInString = 0;
        }
        if(tempCount == 5000 || count == highest) {
            tempCount = 0;
            return dest;
        }
        if(directions.substring((lastPlaceInString % directions.length()), (lastPlaceInString % directions.length() + 1)).equals("L")) {
            count++;
            tempCount++;
            lastPlaceInString++;
            return traversalPart2V2Helper(map.get(dest).left, highest);
        } else {
            count++;
            tempCount++;
            lastPlaceInString++;
            return traversalPart2V2Helper(map.get(dest).right, highest);
        }
    }

    static String traversalPart2(String dest, boolean firstTime) {
        String result = traversalPart2Helper(dest, true);
        while(!result.matches("..Z")) {
            result = traversalPart2(result, false);
        }
        return result;
    }

    static String traversalPart2Helper(String dest, boolean firstTime) {
        if(lastPlaceInString == directions.length()) {
            lastPlaceInString = 0;
        }
        if(!firstTime && dest.matches("..Z")) {
            tempCount = 0;
            return dest;
        }
        if(tempCount == 5000) {
            tempCount = 0;
            return dest;
        }
        if(directions.substring((lastPlaceInString % directions.length()), (lastPlaceInString % directions.length() + 1)).equals("L")) {
            count++;
            tempCount++;
            lastPlaceInString++;
            return traversalPart2Helper(map.get(dest).left, false);
        } else {
            count++;
            tempCount++;
            lastPlaceInString++;
            return traversalPart2Helper(map.get(dest).right, false);
        }
    }

    static String traversal(String dest) {
        if(dest.equals("ZZZ")) {
            return dest;
        }
        if(tempCount == 5000) {
            tempCount = 0;
            return dest;
        }
        if(directions.substring((toIntExact(count) % directions.length()), (toIntExact(count) % directions.length() + 1)).equals("L")) {
            count++;
            tempCount++;
            return traversal(map.get(dest).left);
        } else {
            count++;
            tempCount++;
            return traversal(map.get(dest).right);
        }
    }


    static public long day8part1() throws IOException {
        try {
            map = makeMap();
            boolean there = false;
            String dest = "AAA";
            while(!there) {
                dest = traversal(dest);
                if(dest.equals("ZZZ")) {
                    return count;
                }
            }
        } catch (Exception e) {
            throw new IOException();
        }
        return 0;
    }

    static HashMap<String, Node> makeMap() throws IOException {
        Scanner scanner = new Scanner(Paths.get(Path));
        directions = scanner.nextLine();
        scanner.nextLine();
        HashMap<String, Node> map = new HashMap<>();
        Day8 a = new Day8();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            map.put(line.substring(0, 3), a.new Node(line.substring(0, 3), line.substring(7, 10), line.substring(12, 15)));
        }
        return map;
    }

}

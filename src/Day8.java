import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.toIntExact;

public class Day8 {

    private static final String realPath = "Day8.txt";

    private static final String testPath1 = "src/tests/Day8Part1Test1.txt";

    private static final String testPath2 = "src/tests/Day8Part1Test2.txt";

    private static final String testPath3 = "src/tests/Day8Part2Test.txt";

    private static final String Path = realPath;

    private static String directions = "";

    private static Map<String, Node> map;

    static long count = 0;

    static int part2Count = 0; //for when you've moved on all of your As

    static int tempCount = 0;

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
            ArrayList<String> dests = (ArrayList<String>) starts.clone();
            while(!there) {
                int Zs = 0;
                for (int i = 0; i < starts.size(); i++) {
                    dests.set(i, traversalPart2(dests.get(i)));
                    if(dests.get(i).matches("\\w\\wZ")) {
                        Zs++;
                    }
                }
                part2Count++;
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
            if(line.matches("\\w\\wA.*")) {
                starts.add(line.substring(0, 3));
            }
        }
        return starts;
    }

    static String traversalPart2(String dest) {
        if(tempCount == 1) {
            tempCount = 0;
            return dest;
        }
        if(directions.substring((part2Count % directions.length()), (part2Count % directions.length() + 1)).equals("L")) {
            tempCount++;
            return traversalPart2(map.get(dest).left);
        } else {
            tempCount++;
            return traversalPart2(map.get(dest).right);
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

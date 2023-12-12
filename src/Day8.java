import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Math.toIntExact;

public class Day8 {

    private static final String realPath = "Day8.txt";

    private static final String testPath1 = "src/tests/Day8Test1.txt";

    private static final String testPath2 = "src/tests/Day8Test2.txt";

    private static final String Path = realPath;

    private static String directions = "";

    private static Map<String, Node> map;

    static long count = 0;

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
}

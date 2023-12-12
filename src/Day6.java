import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {

    static final String path = "Day6.txt";

    public static long day6part2() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get(path))) {
            long time = Long.parseLong(scanner.nextLine().replaceAll("[^\\d]", ""));
            long distance = Long.parseLong(scanner.nextLine().replaceAll("[^\\d]", ""));
            return waysToBeat(time, distance);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public static long day6part1() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get(path))) {
            Pattern digits = Pattern.compile("\\d+");
            Matcher time = digits.matcher(scanner.nextLine());
            Matcher distance = digits.matcher(scanner.nextLine());
            long waysToGroup = 1;
            while(time.find()) {
                long thisTime = Long.parseLong(time.group());
                distance.find();
                long thisDistance = Long.parseLong(distance.group());
                waysToGroup = (waysToBeat(thisTime, thisDistance) * waysToGroup);
            }
            return waysToGroup;
        } catch (Exception e) {
            throw new IOException();
        }
    }
    public static long waysToBeat(long time, long record) {
        double root1 = ((-time + Math.sqrt(Math.pow(time, 2) - (4 * record))) / -2);
        double root2 = ((-time - Math.sqrt(Math.pow(time, 2) - (4 * record))) / -2);
        double time1 = Math.ceil(root1);
        double time2 = Math.floor(root2);
        if(-Math.pow(time1, 2) + (time*time1) <= record) {
            time1++;
        }
        if(-Math.pow(time2, 2) + (time*time2) <= record) {
            time2--;
        }
        return((long) (time2 - time1 + 1));
    }
}

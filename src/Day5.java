import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    static final String path = "Day5.txt";
    static long day5part1() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get(path))) {
            LinkedList<Long> locations = new LinkedList<>();
            Pattern seeds = Pattern.compile("\\d+");
            Matcher seedsMatcher = seeds.matcher(scanner.nextLine());
            while(seedsMatcher.find()) {
                long seed = Long.parseLong(seedsMatcher.group());
                locations.add(convert(seed));
            }
            long lowest = locations.getFirst();
            for(long loc : locations) {
                if(loc < lowest) {
                    lowest = loc;
                }
            }
            return lowest;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    static long convert(long toBeConverted) throws IOException {
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            long finalConverted = toBeConverted;
            Pattern map = Pattern.compile("(\\d+) (\\d+) (\\d+)");
            while(scanner.hasNextLine()) {
                if (scanner.nextLine().matches(".*map:")) {
                    long source = 0;
                    long destination = 0;
                    long range = 0;
                    while(scanner.hasNextLine()) {
                        String nextLine = scanner.nextLine();
                        if(nextLine.isEmpty()) {
                            break;
                        }
                        Matcher matcher = map.matcher(nextLine);
                        matcher.matches();
                        String test = matcher.group(2);
                        source = Long.parseLong(test);
                        destination = Long.parseLong(matcher.group(1));
                        range = Long.parseLong(matcher.group(3));
                        if(contains(finalConverted, source, range)) {
                            finalConverted = (finalConverted - source + destination);
                            break;
                        }
                    }
                }
            }
            return finalConverted;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    /**
     *
     * @param i the number that you want to know is in the container or not
     * @param containerStart
     * @param containerRange
     * @return
     */
    static boolean contains(long i, long containerStart, long containerRange) {
        return(i >= containerStart && (i <= (containerStart + containerRange - 1) || i == containerStart));
    }

}

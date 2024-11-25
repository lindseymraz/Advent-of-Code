package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    static final String path = "y2023.Day5.txt";
    static long day5part1() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get(path))) {
            Pattern seeds = Pattern.compile("\\d+");
            Matcher seedsMatcher = seeds.matcher(scanner.nextLine());
            long lowest = Long.MAX_VALUE;
            while(seedsMatcher.find()) {
                long seed = Long.parseLong(seedsMatcher.group());
                long location = convert(seed);
                if(lowest > location) {
                    lowest = location;
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
                        source = Long.parseLong(matcher.group(2));
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

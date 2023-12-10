import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
    static int day2part2() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("Day2.txt"))) {
            int toReturn = 0;
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Pattern gameNum = Pattern.compile("Game (\\d*): ");
                Pattern blue = Pattern.compile("(\\d*)(?: blue)");
                Pattern green = Pattern.compile("(\\d*)(?: green)");
                Pattern red = Pattern.compile("(\\d*)(?: red)");
                Matcher matchBlue = blue.matcher(line);
                Matcher matchGreen = green.matcher(line);
                Matcher matchRed = red.matcher(line);
                toReturn += (getHighest(matchBlue) * getHighest(matchGreen) * getHighest(matchRed));
            }
            return toReturn;
        } catch(Exception e) {
            throw new IOException();
        }
    }

    static int getHighest(Matcher matcher) {
        int highest = 0;
        while(matcher.find()) {
            if(Integer.parseInt(matcher.group(1)) > highest) {
                highest = Integer.parseInt(matcher.group(1));
            }
        }
        System.out.println(highest + " is highest");
        return highest;
    }

    static int day2part1() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("Day2.txt"))) {
            int toReturn = 0;
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Pattern gameNum = Pattern.compile("Game (\\d*): ");
                Pattern blue = Pattern.compile("(\\d*)(?: blue)");
                Pattern green = Pattern.compile("(\\d*)(?: green)");
                Pattern red = Pattern.compile("(\\d*)(?: red)");
                Matcher matchGameNum = gameNum.matcher(line);
                Matcher matchBlue = blue.matcher(line);
                Matcher matchGreen = green.matcher(line);
                Matcher matchRed = red.matcher(line);
                if(countExceedsLimit(matchBlue, 14) && countExceedsLimit(matchGreen, 13) && countExceedsLimit(matchRed, 12)) {
                    matchGameNum.find();
                    toReturn += Integer.parseInt(matchGameNum.group(1));
                    System.out.println(matchGameNum.group(1));
                }
            }
            return toReturn;
        } catch(Exception e) {
            throw new IOException();
        }
    }

    static boolean countExceedsLimit(Matcher matcher, int limit) {
        while(matcher.find()) {
            if(Integer.parseInt(matcher.group(1)) > limit) {
                System.out.println(matcher.group(1) + " > " + limit);
                return false;
            }
        }
        return true;
    }
}

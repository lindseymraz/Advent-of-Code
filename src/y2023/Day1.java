package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    static int day1part2() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("y2023.Day1.txt"))) {
            int toReturn = 0;
            String newNum = "";
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Pattern findFirst = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|\\d)(?:.*)");
                Pattern findLast = Pattern.compile("(?:.*)(one|two|three|four|five|six|seven|eight|nine|\\d)");
                Matcher first = findFirst.matcher(line);
                Matcher last = findLast.matcher(line);
                while(first.find() && last.find()) {
                    newNum = parseNumbers(first.group(1)) + parseNumbers(last.group(1));
                }
                toReturn += Integer.parseInt(newNum);
            }
            return toReturn;
        } catch (Exception e) { throw new IOException(); }
    }

    /**
     *
     * @param input a number from 1-9, either in digit form or written out
     * @return a number from 1-9 in digit form
     * @throws Exception
     */
    static String parseNumbers(String input) throws Exception {
        switch(input) {
            case "one": return "1";
            case "two": return "2";
            case "three": return "3";
            case "four": return "4";
            case "five": return "5";
            case "six": return "6";
            case "seven": return "7";
            case "eight": return "8";
            case "nine": return "9";
            case "1", "2", "3", "4", "5", "6", "7", "8", "9": return input;
            default: throw new Exception();
        }
    }

    static int day1part1() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get("y2023.Day1.txt"))) {
            int toReturn = 0;
            String line = "";
            String newNum = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                for(int i = 0; i < line.length(); i++) {
                    if(line.substring(i, i+1).matches("\\d")) {
                        newNum = (line.substring(i, i+1));
                        break;
                    }
                }
                for(int i = line.length(); i > 0; i--) {
                    if(line.substring(i - 1, i).matches("\\d")) {
                        newNum = newNum + (line.substring(i - 1, i));
                        break;
                    }
                }
                toReturn+=Integer.parseInt(newNum);
            }
            return toReturn;
        } catch (Exception e) { throw new IOException(); }
    }
}

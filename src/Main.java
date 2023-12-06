import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String args[]) throws IOException {
        System.out.println(day2part2());
    }

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
                Matcher matchGameNum = gameNum.matcher(line);
                Matcher matchBlue = blue.matcher(line);
                Matcher matchGreen = green.matcher(line);
                Matcher matchRed = red.matcher(line);
                toReturn += (day2part2helper(matchBlue) * day2part2helper(matchGreen) * day2part2helper(matchRed));
            }
            return toReturn;
        } catch(Exception e) {
            throw new IOException();
        }
    }

    static int day2part2helper(Matcher matcher) {
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
                if(day2part1helper(matchBlue, 14) && day2part1helper(matchGreen, 13) && day2part1helper(matchRed, 12)) {
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

    static boolean day2part1helper(Matcher matcher, int limit) {
        while(matcher.find()) {
            if(Integer.parseInt(matcher.group(1)) > limit) {
                System.out.println(matcher.group(1) + " > " + limit);
                return false;
            }
        }
        return true;
    }

    static int day1part2() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("Day1.txt"))) {
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
                    newNum = day1part2helper(first.group(1)) + day1part2helper(last.group(1));
                }
                toReturn += Integer.parseInt(newNum);
            }
            return toReturn;
        } catch (Exception e) { throw new IOException(); }
    }

    static String day1part2helper(String input) throws Exception {
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
        try(Scanner scanner = new Scanner(Paths.get("Day1.txt"))) {
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

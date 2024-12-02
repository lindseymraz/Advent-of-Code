package y2024;

import shared.Utilities;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    static final String fileName = "src/y2024/Day2.txt";
    public static int part1() throws IOException {
        ArrayList<ArrayList<Integer>> input = inputGetter();
        int safeCount = 0;
        for (ArrayList<Integer> line : input) {
            int firstDigit = 0;
            int lastDigit = 0;
            boolean asc = false;
            boolean firstSet = false;
            boolean ascOrDescSet = false;
            boolean addToSafeCount = true;
            for(Integer integer : line) {
                int currentDigit = integer;
                if (!firstSet) {
                    firstDigit = currentDigit;
                    firstSet = true;
                } else { //this is here because you don't set ascOrDesc till second go-round, don't check unless first is set
                    if (!ascOrDescSet) {
                        if ((currentDigit == firstDigit) || !differsByAtLeastOneAtMostThree(firstDigit, currentDigit)) {
                            addToSafeCount = false;
                            break;
                        } else if (increasing(firstDigit, currentDigit)) {
                            asc = true;
                            ascOrDescSet = true;
                            lastDigit = currentDigit;
                        } else {
                            asc = false;
                            ascOrDescSet = true;
                            lastDigit = currentDigit;
                        }
                    } else { //it is set
                        if (!differsByAtLeastOneAtMostThree(lastDigit, currentDigit)) {
                            addToSafeCount = false;
                            break;
                        }
                        if (asc) {
                            if (!increasing(lastDigit, currentDigit)) {
                                addToSafeCount = false;
                                break;
                            }
                        } else {
                            if (!decreasing(lastDigit, currentDigit)) {
                                addToSafeCount = false;
                                break;
                            }
                        } //if you didn't break yet you're valid
                        lastDigit = currentDigit;
                    }
                }
            }
            if (addToSafeCount) {
                safeCount++;
            }
        }
        return safeCount;
    }

    public static int part2() throws IOException {
        ArrayList<ArrayList<Integer>> input = inputGetter();
        for (ArrayList<Integer> line: input) {

        }
        return 0;
    }
    static ArrayList<ArrayList<Integer>> inputGetter() throws IOException {
        ArrayList<ArrayList<Integer>> toReturn = new ArrayList<ArrayList<Integer>>();
        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Pattern findDigit = Pattern.compile("\\d+");
                Matcher number = findDigit.matcher(line);
                ArrayList<Integer> currentLine = new ArrayList<Integer>();
                while(number.find()) {
                    currentLine.add(Integer.parseInt(number.group()));
                }
                toReturn.add(currentLine);
            }
        }
        return toReturn;
    }



    static boolean differsByAtLeastOneAtMostThree(int first, int second) {
        int difference = Math.abs(second - first);
        return(difference > 0 && difference < 4);
    }

    static boolean increasing(int first, int second) {
        return(second > first);
    }
    static boolean decreasing(int first, int second) {
        return (second < first);
    }



}

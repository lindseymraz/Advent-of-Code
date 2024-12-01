package y2023;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day9 {
    private static final String realPath = "y2023.Day9.txt";
    private static final String testPath1 = "src/y2023.tests/Day9Part1Test.txt";
    private static final String Path = testPath1;

    public static boolean isDay2 = false;

    public static long day9part2() throws Exception {
        isDay2 = true;
        long[][] histories = parseHistories(Path, 200);
        long acc = 0;
        for(int i = 0; i < histories.length; i++) {
            LinkedList<long[]> lines = getAllSequences(histories[i], new LinkedList<long[]>());//first thing in list will be the original history
            acc += getLastFilledValue(0, lines.size() - 2, lines);
        }
        return acc;
    }
    public static long day9part1() throws Exception {
        long[][] histories = parseHistories(Path, 200);
        long acc = 0;
        for(int i = 0; i < histories.length; i++) {
            LinkedList<long[]> lines = getAllSequences(histories[i], new LinkedList<long[]>());//first thing in list will be the original history
            acc += getLastFilledValue(0, lines.size() - 2, lines);
        }
        return acc;
    }

    static long[][] parseHistories(String path, int count) throws Exception {
        Scanner scanner = new Scanner(Paths.get(path));
        long[][] histories = new long[count][];
        int i = 0;
        while (scanner.hasNextLine()) {
            histories[i] = parseHistory(scanner.nextLine());
            i++;
        } return histories;
    }

    public static long[] parseHistory(String history) throws Exception {
        Pattern digit = Pattern.compile("-?\\d+");
        Matcher digitMatcher = digit.matcher(history);
        digitMatcher.matches();
        int groupCount = Math.toIntExact(digitMatcher.results().count());
        long[] longs = new long[groupCount];
        digitMatcher.reset();
        for (int i = 0; i < groupCount; i++) {
            digitMatcher.find();
            longs[i] = Long.parseLong(digitMatcher.group());
        }
        return longs;
    }

    public static LinkedList<long[]> getAllSequences(long[] prevSequence, LinkedList<long[]> list) {
        list.add(prevSequence);
        long[] newSequence = createSequence(prevSequence);
        if(!isAllZero(newSequence)) {
            return getAllSequences(newSequence, list);
        } else {
            list.add(newSequence);
            return list;
        }
    }

    public static boolean isAllZero(long[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static long[] createSequence(long[] prevLine) {
        long[] toReturn = new long[prevLine.length - 1];
        for (int i = 0; (i + 1) < prevLine.length; i++) {
            toReturn[i] = prevLine[i + 1] - prevLine[i];
        }
        return toReturn;
    }

    public static long fillValue(long topVal, long[] lineBelow) {
        if(isDay2) {
            return lineBelow[0] - topVal;
        }
        return lineBelow[lineBelow.length - 1] + topVal;
    }

    public static long getLastFilledValue(long topVal, int currLineIndex, LinkedList<long[]> lines) {
        long newTopVal = fillValue(topVal, lines.get(currLineIndex));
        if(lines.get(currLineIndex).length == (lines.get(0).length)) {
            return newTopVal;
        }
        return getLastFilledValue(newTopVal, (currLineIndex - 1), lines);
    }

    public static long sumFilledValues(long topVal, int currLineIndex, LinkedList<long[]> lines, long acc) {
        long newTopVal = fillValue(topVal, lines.get(currLineIndex));
        acc += newTopVal;
        if(lines.get(currLineIndex).length == (lines.get(0).length)) {
            return acc;
        }
        return sumFilledValues(newTopVal, (currLineIndex - 1), lines, acc);
    }

}

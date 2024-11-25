package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    static String prevLine = "";
    static String line = "";
    static String nextLine = "";
    static final String symbolRegex = "[@#$%&\\*\\-\\+=\\/]+";
    static final String digitRegex = "\\d+";

    static final String gearRegex = "\\*";

    enum Side {
        LEFT, RIGHT
    }

    static int day3part2() throws IOException {
        boolean firstTime = true;
        try (Scanner scanner = new Scanner(Paths.get("y2023.Day3.txt"))) {
            int toReturn = 0;
            if(firstTime) {
                line = scanner.nextLine();
            }
            firstTime = false;
            while(scanner.hasNextLine()) {
                nextLine = scanner.nextLine();
                toReturn += adjGears();
                prevLine = line;
                line = nextLine;
            }
            nextLine = "";
            toReturn += adjGears();
            return toReturn;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    static int setMultiple(int foundVal, int multiple) {
        if(multiple == 0) {
            return foundVal;
        } else {
            return multiple * foundVal;
        }
    }

    static int adjGears() {
        int toReturn = 0;
        int multiple = 0;
        int partsFound = 0;
        Pattern gear = Pattern.compile(gearRegex);
        Matcher gearMatcher = gear.matcher(line);
        Pattern num = Pattern.compile(digitRegex);
        Matcher numMatcherPrev = num.matcher(prevLine);
        Matcher numMatcher = num.matcher(line);
        Matcher numMatcherNext = num.matcher(nextLine);
        while(gearMatcher.find()) {
            boolean doneWithSymbolSearch = false;
            while(!doneWithSymbolSearch) {
                //current line L & R
                if(gearMatcher.start() !=0 && sideOfCharHasAdjacentSymbol(line, gearMatcher, digitRegex, Side.LEFT)) {
                    partsFound++;
                    while(numMatcher.find()) {
                        if(numMatcher.end() == gearMatcher.start()) {
                            multiple = setMultiple(Integer.parseInt(numMatcher.group()), multiple);
                            break;
                        }
                    }
                }
                if(gearMatcher.end() < line.length() && sideOfCharHasAdjacentSymbol(line, gearMatcher, digitRegex, Side.RIGHT)) {
                    partsFound++;
                    while(numMatcher.find()) {
                        if(gearMatcher.end() == numMatcher.start()) {
                            multiple = setMultiple(Integer.parseInt(numMatcher.group()), multiple);
                            break;
                        }
                    }
                }
                //top and bottom line
                if(!prevLine.isEmpty()) {
                    while(numMatcherPrev.find()) {
                        if((numMatcherPrev.end() == gearMatcher.start() || numMatcherPrev.start() == gearMatcher.end()) ||
                                (numMatcherPrev.start() <= gearMatcher.start() && numMatcherPrev.end() >= gearMatcher.end())) {
                            partsFound++;
                            multiple = setMultiple(Integer.parseInt(numMatcherPrev.group()), multiple);
                            //no break, possible to match a 1.1 in which you'll need to run the numMatcherPrev.find() again to get the second num instead of quitting on first match
                        }
                    }
                    numMatcherPrev.reset();
                }
                if(!nextLine.isEmpty()) {
                    while(numMatcherNext.find()) {
                        if((numMatcherNext.end() == gearMatcher.start() || numMatcherNext.start() == gearMatcher.end()) ||
                                (numMatcherNext.start() <= gearMatcher.start() && numMatcherNext.end() >= gearMatcher.end())) {
                            partsFound++;
                            multiple = setMultiple(Integer.parseInt(numMatcherNext.group()), multiple);
                            //no break, possible to match a 1.1 in which you'll need to run the numMatcherNext.find() again to get the second num instead of quitting on first match
                        }
                    }
                    numMatcherNext.reset();
                }
                doneWithSymbolSearch = true;
            }
            if(partsFound == 2) {
                toReturn += multiple;
            }
            multiple = 0;
            partsFound = 0;
        }
        return toReturn;
    }

    static int day3part1() throws IOException {
        boolean firstTime = true;
        try (Scanner scanner = new Scanner(Paths.get("y2023.Day3.txt"))) {
            int toReturn = 0;
            if(firstTime) {
                line = scanner.nextLine();
            }
            firstTime = false;
            while(scanner.hasNextLine()) {
                nextLine = scanner.nextLine();
                toReturn += adjSymbols();
                prevLine = line;
                line = nextLine;
            }
            nextLine = "";
            toReturn += adjSymbols();
            return toReturn;
        } catch (Exception e) {
        throw new IOException();
        }
    }

    /**
     * Looks for adjacent symbols left, right, and above and below of a sequence of characters. Given
     *
     * 123
     * 4.5
     * 678
     *
     * where . has the sequence, left is 146, right is 358, above is 2, and below is 7.
     * @return
     */
    static int adjSymbols() {
        int toReturn = 0;
        Pattern num = Pattern.compile(digitRegex);
        Matcher numMatcher = num.matcher(line);
        while(numMatcher.find()) {
            boolean doneWithSymbolSearch = false;
            while(!doneWithSymbolSearch) {
                if(numMatcher.start() != 0 && sideOfSeqHasAdjacentSymbol(numMatcher, symbolRegex, Side.LEFT)) {
                    toReturn += Integer.parseInt(numMatcher.group());
                    break;
                }
                if(numMatcher.end() < line.length() && sideOfSeqHasAdjacentSymbol(numMatcher, symbolRegex, Side.RIGHT)) {
                    toReturn += Integer.parseInt(numMatcher.group());
                    break;
                }
                for(int i = 0; i < numMatcher.group().length(); i++) {
                    if(topOrBottomOfCharHasAdjacentSymbol(prevLine, i, numMatcher, symbolRegex)) {
                        toReturn += Integer.parseInt(numMatcher.group());
                        break;
                    }
                    if(topOrBottomOfCharHasAdjacentSymbol(nextLine, i, numMatcher, symbolRegex)) {
                        toReturn += Integer.parseInt(numMatcher.group());
                        break;
                    }
                }
                doneWithSymbolSearch = true;
            }
        }
        return toReturn;
    }

    /**
     *
     * @param line Not the line you want the top or bottom of, but the line that IS the top or bottom. Given
     *
     * 123
     * 4.5
     * 678
     *
     * if you are at the ., put in the line that has 123 or the line that has 678.
     * @param i
     * @param matcher
     * @param regex
     * @return Defaults to false if the top or bottom line doesn't exist/is empty.
     */
        static boolean topOrBottomOfCharHasAdjacentSymbol(String line, int i, Matcher matcher, String regex) {
            if(!line.isEmpty()) {
                return(line.substring(matcher.start() + i, matcher.start() + i + 1).matches(regex));
            }
            return false;
        }

        static boolean sideOfCharHasAdjacentSymbol(String line, Matcher matcher, String regex, Side side) {
            if(side==Side.LEFT) {
                return line.substring(matcher.start() - 1, matcher.start()).matches(regex);
            }
            return line.substring(matcher.end(), matcher.end() + 1).matches(regex);
        }

        static boolean sideOfSeqHasAdjacentSymbol(Matcher matcherForSeq, String regex, Side side) {
            if(prevLine.isEmpty()) {
                return(sideOfCharHasAdjacentSymbol(line, matcherForSeq, regex, side) || sideOfCharHasAdjacentSymbol(nextLine, matcherForSeq, regex, side));
            }
            if(nextLine.isEmpty()) {
                return(sideOfCharHasAdjacentSymbol(prevLine, matcherForSeq, regex, side) || sideOfCharHasAdjacentSymbol(line, matcherForSeq, regex, side));
            }
            return(sideOfCharHasAdjacentSymbol(prevLine, matcherForSeq, regex, side) || sideOfCharHasAdjacentSymbol(line, matcherForSeq, regex, side) || sideOfCharHasAdjacentSymbol(nextLine, matcherForSeq, regex, side));
        }
}

package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    static final String realPath = "y2023.Day7.txt";
    static final String testPath = "src/y2023.tests/Day7Test.txt";

    static boolean part2 = true;

    static final String Path = realPath;

    public static int day7part2() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get(Path))) {
            part2 = true;
            int toReturn = 0;
            LinkedList<String> five = new LinkedList<>();
            LinkedList<String> four = new LinkedList<>();
            LinkedList<String> full = new LinkedList<>();
            LinkedList<String> three = new LinkedList<>();
            LinkedList<String> two = new LinkedList<>();
            LinkedList<String> one = new LinkedList<>();
            LinkedList<String> high = new LinkedList<>();
            Map<String, Integer> map = new HashMap<>();
            while(scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                String hand = nextLine.substring(0,5);
                switch(typeHand(hand)) {
                    case 7: five.add(hand); break;
                    case 6: four.add(hand); break;
                    case 5: full.add(hand); break;
                    case 4: three.add(hand); break;
                    case 3: two.add(hand); break;
                    case 2: one.add(hand); break;
                    case 1: high.add(hand); break;
                }
                Pattern digits = Pattern.compile("(?<= )\\d+");
                Matcher matcher = digits.matcher(nextLine);
                matcher.find();
                map.put(hand, Integer.parseInt(matcher.group()));
            }
            Map<String, Integer> orderFive = new HashMap<>();
            Map<String, Integer> orderFour = new HashMap<>();
            Map<String, Integer> orderFull = new HashMap<>();
            Map<String, Integer> orderThree = new HashMap<>();
            Map<String, Integer> orderTwo = new HashMap<>();
            Map<String, Integer> orderOne = new HashMap<>();
            Map<String, Integer> orderHigh = new HashMap<>();
            if(!five.isEmpty()) {
                mapMakers(orderHandsOfType(five), orderFive);
            }
            if(!four.isEmpty()) {
                mapMakers(orderHandsOfType(four), orderFour);
            }
            if(!full.isEmpty()) {
                mapMakers(orderHandsOfType(full), orderFull);
            }
            if(!three.isEmpty()) {
                mapMakers(orderHandsOfType(three), orderThree);
            }
            if(!two.isEmpty()) {
                mapMakers(orderHandsOfType(two), orderTwo);
            }
            if(!one.isEmpty()) {
                mapMakers(orderHandsOfType(one), orderOne);
            }
            if(!high.isEmpty()) {
                mapMakers(orderHandsOfType(high), orderHigh);
            }
            scanner.close();
            Scanner another = new Scanner(Paths.get(Path));
            while(another.hasNextLine()) {
                String nextLine = another.nextLine();
                String hand = nextLine.substring(0,5);
                switch(typeHand(hand)) {
                    case 7: if(!orderFive.isEmpty()) {
                        toReturn += map.get(hand) * (orderFour.size() + orderFull.size() + orderThree.size() + orderTwo.size() + orderOne.size() + orderHigh.size() + orderFive.get(hand)); } break;
                    case 6: if(!orderFour.isEmpty()) {
                        toReturn += map.get(hand) * (orderFull.size() + orderThree.size() + orderTwo.size() + orderOne.size() + orderHigh.size() + orderFour.get(hand)); }break;
                    case 5: if(!orderFull.isEmpty()) {
                        toReturn += map.get(hand) * (orderThree.size() + orderTwo.size() + orderOne.size() + orderHigh.size() + orderFull.get(hand)); } break;
                    case 4: if(!orderThree.isEmpty()) {
                        toReturn += map.get(hand) * (orderTwo.size() + orderOne.size() + orderHigh.size() + orderThree.get(hand)); } break;
                    case 3: if(!orderTwo.isEmpty()) {
                        toReturn += map.get(hand) * (orderOne.size() + orderHigh.size() + orderTwo.get(hand));
                    } break;
                    case 2: if(!orderOne.isEmpty()) {
                        toReturn += map.get(hand) * (orderHigh.size() + orderOne.get(hand));
                    } break;
                    case 1: if(!orderHigh.isEmpty()) {
                        toReturn += map.get(hand) * orderHigh.get(hand);
                    } break;
                }
            }
            return toReturn;
        } catch (Exception e) {
            throw new IOException();
        }
    }
    public static int day7part1() throws IOException {
        try(Scanner scanner = new Scanner(Paths.get(Path))) {
            int toReturn = 0;
            LinkedList<String> five = new LinkedList<>();
            LinkedList<String> four = new LinkedList<>();
            LinkedList<String> full = new LinkedList<>();
            LinkedList<String> three = new LinkedList<>();
            LinkedList<String> two = new LinkedList<>();
            LinkedList<String> one = new LinkedList<>();
            LinkedList<String> high = new LinkedList<>();
            Map<String, Integer> map = new HashMap<>();
            while(scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                String hand = nextLine.substring(0,5);
                switch(typeHand(hand)) {
                    case 7: five.add(hand); break;
                    case 6: four.add(hand); break;
                    case 5: full.add(hand); break;
                    case 4: three.add(hand); break;
                    case 3: two.add(hand); break;
                    case 2: one.add(hand); break;
                    case 1: high.add(hand); break;
                }
                Pattern digits = Pattern.compile("(?<= )\\d+");
                Matcher matcher = digits.matcher(nextLine);
                matcher.find();
                map.put(hand, Integer.parseInt(matcher.group()));
            }
            Map<String, Integer> orderFive = new HashMap<>();
            Map<String, Integer> orderFour = new HashMap<>();
            Map<String, Integer> orderFull = new HashMap<>();
            Map<String, Integer> orderThree = new HashMap<>();
            Map<String, Integer> orderTwo = new HashMap<>();
            Map<String, Integer> orderOne = new HashMap<>();
            Map<String, Integer> orderHigh = new HashMap<>();
            if(!five.isEmpty()) {
                mapMakers(orderHandsOfType(five), orderFive);
            }
            if(!four.isEmpty()) {
                mapMakers(orderHandsOfType(four), orderFour);
            }
            if(!full.isEmpty()) {
                mapMakers(orderHandsOfType(full), orderFull);
            }
            if(!three.isEmpty()) {
                mapMakers(orderHandsOfType(three), orderThree);
            }
            if(!two.isEmpty()) {
                mapMakers(orderHandsOfType(two), orderTwo);
            }
            if(!one.isEmpty()) {
                mapMakers(orderHandsOfType(one), orderOne);
            }
            if(!high.isEmpty()) {
                mapMakers(orderHandsOfType(high), orderHigh);
            }
            scanner.close();
            Scanner another = new Scanner(Paths.get(Path));
            while(another.hasNextLine()) {
                String nextLine = another.nextLine();
                String hand = nextLine.substring(0,5);
                switch(typeHand(hand)) {
                    case 7: if(!orderFive.isEmpty()) {
                        toReturn += map.get(hand) * (orderFour.size() + orderFull.size() + orderThree.size() + orderTwo.size() + orderOne.size() + orderHigh.size() + orderFive.get(hand)); } break;
                    case 6: if(!orderFour.isEmpty()) {
                        toReturn += map.get(hand) * (orderFull.size() + orderThree.size() + orderTwo.size() + orderOne.size() + orderHigh.size() + orderFour.get(hand)); }break;
                    case 5: if(!orderFull.isEmpty()) {
                        toReturn += map.get(hand) * (orderThree.size() + orderTwo.size() + orderOne.size() + orderHigh.size() + orderFull.get(hand)); } break;
                    case 4: if(!orderThree.isEmpty()) {
                        toReturn += map.get(hand) * (orderTwo.size() + orderOne.size() + orderHigh.size() + orderThree.get(hand)); } break;
                    case 3: if(!orderTwo.isEmpty()) {
                        toReturn += map.get(hand) * (orderOne.size() + orderHigh.size() + orderTwo.get(hand));
                    } break;
                    case 2: if(!orderOne.isEmpty()) {
                        toReturn += map.get(hand) * (orderHigh.size() + orderOne.get(hand));
                    } break;
                    case 1: if(!orderHigh.isEmpty()) {
                        toReturn += map.get(hand) * orderHigh.get(hand);
                    } break;
                }
            }
            return toReturn;
        } catch (Exception e) {
            throw new IOException();
        }
    }

    static void mapMakers(String[] array, Map<String, Integer> m) {
        for(int i = 0; i < array.length; i++) {
            m.put(array[i], i + 1);
        }
    }

    public static String weakerHand(String hand1, String hand2, int a) {
        int hand1Strength = 0;
        int hand2Strength = 0;
        for(int i = a; i + 1 < 6; i++) {
            hand1Strength = strengthOfLabel(hand1.substring(i, i + 1));
            hand2Strength = strengthOfLabel(hand2.substring(i, i + 1));
            if(hand1Strength > hand2Strength) {
                return hand2;
            }
            if(hand1Strength < hand2Strength) {
                return hand1;
            }
            if(i==4) {
                return hand1;
            }
        }
        return weakerHand(hand1, hand2, a++);
    }

    public static String weakerHand(String hand1, String hand2) {
        return weakerHand(hand1, hand2, 0);
    }

    public static String findWeakest(LinkedList<String> strings) {
        String weakest = strings.getFirst();
        for(String aString : strings) {
            if(weakerHand(weakest, aString).equals(aString)) {
                weakest = aString;
            }
        }
        return weakest;
    }

    public static String[] orderHandsOfTypeHelper(LinkedList<String> handsOfType, String[] sorted, int acc) {
        String weakest = findWeakest(handsOfType);
        sorted[acc] = weakest;
        acc++;
        handsOfType.remove(weakest);
        if(!handsOfType.isEmpty()) {
            return orderHandsOfTypeHelper(handsOfType, sorted, acc);
        } else {
            return sorted;
        }
    }
    public static String[] orderHandsOfType(LinkedList<String> handsOfType) {
        if(handsOfType.isEmpty()) {
            return null;
        }
        String[] sorted = new String[handsOfType.size()];
        return orderHandsOfTypeHelper(handsOfType, sorted, 0);
    }

    /**
     *
     * @param label should be 1 char long
     * @return
     */
    public static int strengthOfLabel (String label) {
        switch(label) {
            case "2", "3", "4", "5", "6", "7", "8", "9": return Integer.parseInt(label);
            case "T": return 10;
            case "J": if(part2) {return 1;} return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
        }
        return -2;
    }

    public static int typeHand(String hand) {
        Pattern j = Pattern.compile("J");
        Matcher jMatcher = j.matcher(hand);
        jMatcher.matches();
        long jMatches = jMatcher.results().count();
        LinkedList<Integer> matchedIndices = new LinkedList<>();
        for(int i = 1; i + 1 < 6; i++) {
            if(hand.substring(0, 1).equals(hand.substring(i, i+1))) {
                matchedIndices.add(i);
            }
        }
        if (matchedIndices.size() == 4) {
            return 7;
        }
        LinkedList<Integer> matchedIndicesTwo = new LinkedList<>();
        for(int i = 2; i + 1 < 6; i++) {
            if(!matchedIndices.contains(i)) {
                if (hand.substring(1, 2).equals(hand.substring(i, i + 1))) {
                    matchedIndicesTwo.add(i);
                }
            }
        }
        if(matchedIndices.size() == 3 || matchedIndicesTwo.size() == 3) {
            if(part2) {
                if(jMatches == 1 || jMatches == 4) {
                    return 7;
                }
            }
            return 6;
        }
        LinkedList<Integer> matchedIndicesThree = new LinkedList<>();
        for(int i = 3; i + 1 < 6; i++) {
            if (!matchedIndices.contains(i) && !matchedIndicesTwo.contains(i)) {
                if (hand.substring(2, 3).equals(hand.substring(i, i + 1))) {
                    matchedIndicesThree.add(i);
                }
            }
        }
        LinkedList<Integer> matchedIndicesFour = new LinkedList<>();
        if (!matchedIndices.contains(4) && !matchedIndicesTwo.contains(4) && !matchedIndicesThree.contains(4)) {
            if (hand.substring(3, 4).equals(hand.substring(4, 5))) {
                matchedIndicesFour.add(4);
            }
        }
        if(matchedIndices.size() == 2 || matchedIndicesTwo.size() == 2 || matchedIndicesThree.size() == 2) {
            if(matchedIndices.size() == 1 || matchedIndicesTwo.size() == 1 || matchedIndicesThree.size() == 1 || matchedIndicesFour.size() == 1) {
                if(part2) {
                    if(jMatches == 2 || jMatches == 3) {
                        return 7;
                    }
                }
                return 5;
            }
            if(part2) {
                if(jMatches == 1 || jMatches == 3) {
                    return 6;
                }
            }
            return 4;
        }
        int matchedIndicesWithOne = 0;
        if(matchedIndices.size() == 1) {
            matchedIndicesWithOne++;
        }
        if(matchedIndicesTwo.size() == 1) {
            matchedIndicesWithOne++;
        }
        if(matchedIndicesThree.size() == 1) {
            matchedIndicesWithOne++;
        }
        if(matchedIndicesFour.size() == 1) {
            matchedIndicesWithOne++;
        }
        if(matchedIndicesWithOne == 2) {
            if(part2) {
                if(jMatches == 2) {
                    return 6;
                }
                if(jMatches == 1) {
                    return 5;
                }
            }
            return 3;
        }
        if(matchedIndicesWithOne == 1) {
            if(part2) {
                if(jMatches == 1 || jMatches == 2) {
                    return 4;
                }
            }
            return 2;
        }
        if(part2) {
            if(jMatches == 1) {
                return 2;
            }
        }
        return 1;
    }

}

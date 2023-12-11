import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    static int lines = 196;
    static int day4part2() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("Day4.txt"))) {
            String line = "";
            int[] cards = new int[lines]; //index is (card number - 1), value is the amount of that card you have
            Arrays.fill(cards, 1);
            int cardBeingWorkedOnMinusOne = 0;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                LinkedList<Integer> winners = new LinkedList<>();
                LinkedList<Integer> entries = new LinkedList<>();
                String entriesString = line.substring(line.indexOf(":") + 1, line.indexOf("|"));
                String winnersString = line.substring(line.indexOf("|") + 1);
                Pattern nums = Pattern.compile("\\d+");
                Matcher winnersNums = nums.matcher(winnersString);
                Matcher entriesNums = nums.matcher(entriesString);
                while(winnersNums.find()) {
                    winners.add(Integer.parseInt(winnersNums.group().strip()));
                }
                while(entriesNums.find()) {
                    entries.add(Integer.parseInt(entriesNums.group().strip()));
                }
                int wins = 0;
                for(int entry : entries) {
                    for(int winner : winners) {
                        if(entry == winner) {
                            wins++;
                            cards[cardBeingWorkedOnMinusOne + wins] = cards[cardBeingWorkedOnMinusOne + wins] + cards[cardBeingWorkedOnMinusOne]; //if you have x copies of card y, when you win you earn x new cards of y+wincount; we're doing current count of (y+wincount) cards + x new earned cards
                            break;
                        }
                    }
                }
                cardBeingWorkedOnMinusOne++;
            }
            return Arrays.stream(cards).sum();
        } catch (Exception e) { throw new IOException(); }
    }
    static int day4part1() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("Day4.txt"))) {
            int toReturn = 0;
            String line = "";
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                LinkedList<Integer> winners = new LinkedList<>();
                LinkedList<Integer> entries = new LinkedList<>();
                String entriesString = line.substring(line.indexOf(":") + 1, line.indexOf("|"));
                String winnersString = line.substring(line.indexOf("|") + 1);
                Pattern nums = Pattern.compile("\\d+");
                Matcher winnersNums = nums.matcher(winnersString);
                Matcher entriesNums = nums.matcher(entriesString);
                while(winnersNums.find()) {
                    winners.add(Integer.parseInt(winnersNums.group().strip()));
                }
                while(entriesNums.find()) {
                    entries.add(Integer.parseInt(entriesNums.group().strip()));
                }
                int wins = 0;
                for(int entry : entries) {
                    for(int winner : winners) {
                        if(entry == winner) {
                            wins++;
                            break;
                        }
                    }
                }
                toReturn += Math.pow(2, wins - 1);
            }
            return toReturn;
        } catch (Exception e) { throw new IOException(); }
    }
}

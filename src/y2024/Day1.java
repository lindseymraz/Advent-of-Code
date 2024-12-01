package y2024;

import shared.Utilities;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    static final String fileName = "src/y2024/Day1.txt";

    public static int day1part1() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            int[] left = new int[Utilities.linesPerFile(fileName)];
            int[] right = new int[Utilities.linesPerFile(fileName)];
            String line = "";
            int counterOverall = 0;
            int leftCounter = 0;
            int rightCounter = 0;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Pattern findFirst = Pattern.compile("\\d+");
                Matcher number = findFirst.matcher(line);
                while(number.find()) {
                    if(counterOverall % 2 == 0) {
                        left[leftCounter] = Integer.parseInt(number.group());
                        leftCounter++;
                    } else {
                        right[rightCounter] = Integer.parseInt(number.group());
                        rightCounter++;
                    }
                    counterOverall++;
                }
            }
            scanner.close();
            return addDifferences(mergeSort(left), mergeSort(right));
        } catch (Exception e) { throw new IOException(); }
    }

    static int[] mergeSort(int[] array) {
        if(array.length == 1) {
            return array;
        }
        int limit; //midpoint
        if(array.length % 2 == 0) { //even
            limit = array.length / 2;
        } else {
            limit = (array.length / 2) + 1;
        }
        int rightLimit = array.length - limit;
        int[] left = new int[limit];
        for(int i = 0; i < limit; i++) { //left array copies left side
            left[i] = array[i];
        }
        int[] right = new int[rightLimit]; //right array copies right side
        for(int i = 0; i < rightLimit; i++) {
            right[i] = array[limit + i];
        }
        left = mergeSort(left);
        right = mergeSort(right);
        int[] result = new int[array.length];
        int elementsAdded = 0;
        int leftI = 0;
        int rightI = 0;
        while(elementsAdded != array.length) {
            if(right.length == 0 || rightI == right.length) { //no right entries/all right entries used
                for(int i = leftI; i < left.length; i++) {
                    result[elementsAdded] = left[i];
                    elementsAdded++;
                }
                break;
            }
            if(leftI == left.length) { //all left entries used (left entries guaranteed to exist)
                for(int i = rightI; i < right.length; i++) {
                    result[elementsAdded] = right[i];
                    elementsAdded++;
                }
                break;
            }
            else if(left[leftI] > right[rightI]) {
                result[elementsAdded] = right[rightI];
                rightI++;
                elementsAdded++;
            } else {
                result[elementsAdded] = left[leftI];
                leftI++;
                elementsAdded++;
            }
        }
        return result;
    }

    static int addDifferences(int[] left, int[] right) {
        int toReturn = 0;
        for(int i = 0; i < left.length; i++) {
            toReturn += Math.abs(right[i] - left[i]);
        }
        return toReturn;
    }

    public static int day1part2() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            int[] left = new int[Utilities.linesPerFile(fileName)];
            int[] right = new int[Utilities.linesPerFile(fileName)];
            String line = "";
            int counterOverall = 0;
            int leftCounter = 0;
            int rightCounter = 0;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Pattern findFirst = Pattern.compile("\\d+");
                Matcher number = findFirst.matcher(line);
                while(number.find()) {
                    if(counterOverall % 2 == 0) {
                        left[leftCounter] = Integer.parseInt(number.group());
                        leftCounter++;
                    } else {
                        right[rightCounter] = Integer.parseInt(number.group());
                        rightCounter++;
                    }
                    counterOverall++;
                }
            }
            scanner.close();
            return similarityScore(mergeSort(left), mergeSort(right));
        } catch (Exception e) { throw new IOException(); }
    }

    static int similarityScore(int[] left, int[] right) {
        int similarityScore = 0;
        int leftI = 0;
        int rightI = 0;
        while(leftI != left.length && rightI != right.length) {
            int valueToFind = left[leftI];
            int occurrencesInLeft = 0;
            while(leftI != left.length && left[leftI] == valueToFind) {
                occurrencesInLeft++;
                leftI++;
            }
            int occurrencesInRight = 0;
            while(rightI != right.length && right[rightI] <= valueToFind) {
                if(right[rightI] == valueToFind) {
                    occurrencesInRight++;
                }
                rightI++;
            }
            similarityScore += valueToFind * occurrencesInRight * occurrencesInLeft;
        }
        return similarityScore;
    }

}

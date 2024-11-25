package y2023;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day19 {
    static final String testPath = "src/y2023.tests/Day19Part1Test.txt";
    static final String realPath = "y2023.Day19.txt";
    static String Path = realPath;
    static HashMap<String, String> workflows;
    static ArrayList<Integer[]> partsRated;

    static long day19part1() throws Exception {
        Scanner scanner = new Scanner(Paths.get(Path));
        workflows = createWorkflowMap(scanner);
        partsRated = createPartsRatedArray(scanner);
        scanner.close();
        boolean[] partsAccepted = new boolean[partsRated.size()];
        for(int i = 0; i < partsRated.size(); i++) {
            if(accepted(i)) {
                partsAccepted[i] = true;
            }
        }
        return addRatingsForAcceptedParts(partsAccepted);
    }

    static String lineHelper(int partIndex, String cutDownLineString) throws Exception {
        String arr[] = cutDownLineString.split(":");
        String[] LR = arr[1].split(",");
        if(evaluateInequality(partIndex, arr[0])) {
            return categorize(partIndex, LR[0], cutDownLineString);
        } else {
            return categorize(partIndex, LR[1], cutDownLineString);
        }
    }

    static String categorize(int partIndex, String toMatch, String lineString) throws Exception {
        if(toMatch.matches("A")) { return "T"; }
        if(toMatch.matches("R")) { return "F"; }
        if(toMatch.matches("[x|m|a|s][<|>]\\d+")) { return lineHelper(partIndex, lineString.substring(lineString.indexOf(toMatch))); } //this is a spli
        else { return toMatch; } //it's another workflow
    }

    static String acceptedHelper(int partIndex, String toUse) throws Exception {
        String workflow = workflows.get(toUse);
        String arr[] = workflow.split(":");
        String[] LR = arr[1].split(",");
        if(evaluateInequality(partIndex, arr[0])) {
            return categorize(partIndex, LR[0], workflow);
        } else {
            return categorize(partIndex, LR[1], workflow);
        }
    }

    static boolean accepted(int partIndex) throws Exception {
        String in = workflows.get("in");
        String arr[] = in.split(":");
        String arr2[] = arr[1].split(",");
        String helper = "";
        if(evaluateInequality(partIndex, arr[0])) {
            helper = acceptedHelper(partIndex, arr2[0]);
            while(!helper.equals("T") && !helper.equals("F")) {
                helper = acceptedHelper(partIndex, helper);
            }
        } else {
            helper = acceptedHelper(partIndex, arr2[1]);
            while(!helper.equals("T") && !helper.equals("F")) {
                helper = acceptedHelper(partIndex, helper);
            }
        }
        if(helper.equals("T")) { return true; } else { return false; }
    }

    static boolean evaluateInequality(int partIndex, String inequality) throws Exception {
        String arr[] = inequality.split("<|>");
        if(inequality.contains(">")) {
            switch (arr[0]) {
                case "x": return partsRated.get(partIndex)[0] > Integer.parseInt(arr[1]);
                case "m": return partsRated.get(partIndex)[1] > Integer.parseInt(arr[1]);
                case "a": return partsRated.get(partIndex)[2] > Integer.parseInt(arr[1]);
                case "s": return partsRated.get(partIndex)[3] > Integer.parseInt(arr[1]);
            }
        } else {
            switch (arr[0]) {
                case "x": return partsRated.get(partIndex)[0] < Integer.parseInt(arr[1]);
                case "m": return partsRated.get(partIndex)[1] < Integer.parseInt(arr[1]);
                case "a": return partsRated.get(partIndex)[2] < Integer.parseInt(arr[1]);
                case "s": return partsRated.get(partIndex)[3] < Integer.parseInt(arr[1]);
            }
        }
        throw new Exception();
    }

    static long addRatingsForAcceptedParts(boolean[] partsAccepted) {
        long toReturn = 0;
        for(int i = 0; i < partsAccepted.length; i++) {
            if(partsAccepted[i]) {
                toReturn += (partsRated.get(i)[0] + partsRated.get(i)[1] + partsRated.get(i)[2] + partsRated.get(i)[3]);
            }
        }
        return toReturn;
    }

    static ArrayList<Integer[]> createPartsRatedArray(Scanner scanner) {
        ArrayList<Integer[]> toReturn = new ArrayList<Integer[]>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split("\\{x=|,m=|,a=|,s=|}");
            toReturn.add(new Integer[]{Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4])});
        }
        return toReturn;
    }

    static HashMap<String, String> createWorkflowMap(Scanner scanner) {
        HashMap<String, String> toReturn = new HashMap<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.isEmpty()) {
                break;
            }
            String[] arr = line.split("[{|}]");
            toReturn.put(arr[0], arr[1]);
        }
        return toReturn;
    }
}

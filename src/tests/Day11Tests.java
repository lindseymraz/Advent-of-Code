
import org.junit.Test;


import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;
public class Day11Tests {
    public Day11Tests() throws IOException {
    }

    @Test
    public void charsPerLine() throws IOException {
        Day11.setPathToTestPath1();
        assertEquals(10, Utilities.charsPerLine("src/tests/Day11Part1Test.txt"));
        Day11.setPathToTestPath2();
        assertEquals(10, Utilities.charsPerLine("src/tests/Day11Part1Test2.txt"));
        Day11.setPathToRealPath();
        assertEquals(140, Utilities.charsPerLine("Day11.txt"));
    }

    @Test
    public void linesPerFile() throws IOException {
        Day11.setPathToTestPath1();
        assertEquals(10, Utilities.linesPerFile("src/tests/Day11Part1Test.txt"));
        Day11.setPathToTestPath2();
        assertEquals(10, Utilities.linesPerFile("src/tests/Day11Part1Test2.txt"));
        Day11.setPathToRealPath();
        assertEquals(140, Utilities.linesPerFile("Day11.txt"));
    }

    @Test
    public void makeColumnHasGalaxy() throws IOException {
        Day11.setPathToTestPath1();
        boolean[] test = new boolean[]{true, true, false, true, true, false, true, true, false, true};
        assertTrue(Arrays.equals(test, Day11.makeColumnHasGalaxy()));
        Day11.setPathToTestPath2();
        boolean[] test2 = new boolean[]{false, true, false, false, true, false, true, true, false, false};
        assertTrue(Arrays.equals(test2, Day11.makeColumnHasGalaxy()));

    }

    @Test
    public void makeXOffsets() throws IOException {
        Day11.setIsPart2(false);
        Day11.setPathToTestPath1();
        int[] test = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3};
        assertTrue(Arrays.equals(test, Day11.makeXOffsets()));
        Day11.setPathToTestPath2();
        int[] test2 = new int[]{0, 1, 1, 2, 3, 3, 4, 4, 4, 5};
        assertTrue(Arrays.equals(test2, Day11.makeXOffsets()));
    }

    @Test
    public void makeYOffsets() throws IOException {
        Day11.setIsPart2(false);
        Day11.setPathToTestPath1();
        int[] test = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2};
        assertTrue(Arrays.equals(test, Day11.makeYOffsets()));
        Day11.setPathToTestPath2();
        int[] test2 = new int[]{0, 1, 1, 2, 3, 3, 3, 4, 5, 5};
        assertTrue(Arrays.equals(test2, Day11.makeYOffsets()));
    }

    @Test
    public void countGalaxies() throws IOException {
        Day11.setPathToTestPath1();
        assertEquals(9, Day11.countGalaxies());
        Day11.setPathToTestPath2();
        assertEquals(5, Day11.countGalaxies());
        Day11.setPathToRealPath();
        assertEquals(436, Day11.countGalaxies());
    }

    @Test
    public void makeGalaxyArray() throws IOException {
        Day11.setIsPart2(false);
        Day11.setPathToTestPath1();
        int[][] test = new int[][]{new int[]{4,0}, new int[]{9,1}, new int[]{0,2}, new int[]{8,5},
                new int[]{1,6}, new int[]{12,7}, new int[]{9,10}, new int[]{0,11}, new int[]{5,11}};
        assertArrayEquals(test, Day11.makeGalaxyArray());
        Day11.setPathToTestPath2();
        int[][] test2 = new int[][]{new int[]{11, 2}, new int[]{10, 7}, new int[]{2, 8}, new int[]{11, 13},
                new int[]{7, 14}};
        assertArrayEquals(test2, Day11.makeGalaxyArray());
    }

    @Test
    public void distance() {
        assertEquals(9, Day11.distance(1, 6, 5, 11));
        assertEquals(15, Day11.distance(4, 0, 9, 10));
        assertEquals(17, Day11.distance(0, 2, 12, 7));
        assertEquals(5, Day11.distance(0, 11, 5, 11));
    }

    @Test
    public void testWholeProblem() throws IOException {
        Day11.setIsPart2(false);
        Day11.setPathToTestPath1();
        assertEquals(374, Day11.day11part1());
        Day11.setIsPart2(true);
        Day11.setPart2GalaxyExpansionValue(10);
        assertEquals(1030, Day11.day11part2());
        Day11.setPart2GalaxyExpansionValue(100);
        assertEquals(8410, Day11.day11part2());
    }
}

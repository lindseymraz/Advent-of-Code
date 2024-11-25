import org.junit.Test;
import org.junit.Assert;
import y2023.Day13;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day13Tests {
    String[] test1 = new String[] {
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#."
    };
    String[] test2 = new String[] {
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#"
    };
    String[] test3 = new String[] {
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###"
    };
    String[] test4 = new String[] {
            "#######",
            "##.....",
            "##....."
    };
    String[] test5 = new String[] {
            "#.##..",
            "#.##..",
            "######"
    };
    public Day13Tests() {}

    @Test
    public void countPatternsInFile() throws IOException {
        Day13.Path = Day13.testPath1;
        assertEquals(2, Day13.countPatternsInFile());
        Day13.Path = Day13.testPath2;
        assertEquals(3, Day13.countPatternsInFile());
    }

    @Test
    public void makeLinesInPatternsArray() throws IOException {
        Day13.Path = Day13.testPath1;
        int[] test = new int[]{7, 7};
        assertArrayEquals(test, Day13.makeLinesInPatternsArray());
        Day13.Path = Day13.testPath2;
        int[] test2 = new int[]{7, 6, 3};
        assertArrayEquals(test2, Day13.makeLinesInPatternsArray());
    }

    @Test
    public void makePatternsArray() throws IOException {
        Day13.Path = Day13.testPath1;
        String[][] pattern1 = new String[][] {test1, test2};
        assertArrayEquals(pattern1, Day13.makePatternsArray());
        Day13.Path = Day13.testPath2;
        String[][] pattern2 = new String[][] {test1, test3, test4};
        assertArrayEquals(pattern2, Day13.makePatternsArray());
    }

    @Test
    public void linesMirror() {
        assertTrue(Day13.linesMirror("", ""));
        assertTrue(Day13.linesMirror(".", "."));
        assertTrue(Day13.linesMirror("#.", ".#"));
        assertTrue(Day13.linesMirror(".#", "#."));
        assertTrue(Day13.linesMirror("###", "###"));
        assertTrue(Day13.linesMirror("..#", "#.."));
        assertFalse(Day13.linesMirror("##.", "##."));
    }

    @Test
    public void linesLeftOfVerticalLineOfReflection() {
        assertEquals(5, Day13.linesLeftOfVerticalLineOfReflection(test1));
        assertEquals(0, Day13.linesLeftOfVerticalLineOfReflection(test2));
        assertEquals(0, Day13.linesLeftOfVerticalLineOfReflection(test3));
        assertEquals(1, Day13.linesLeftOfVerticalLineOfReflection(test4));
        assertEquals(5, Day13.linesLeftOfVerticalLineOfReflection(test5));
    }

    @Test
    public void checkReflects() {
        assertFalse(Day13.checkReflects(test1, 2, 3));
        assertTrue(Day13.checkReflects(test2, 3, 4));
        assertTrue(Day13.checkReflects(test3, 3, 4));
        assertTrue(Day13.checkReflects(test4, 1, 2));
        assertTrue(Day13.checkReflects(test5, 0, 1));
    }

    @Test
    public void rowsAboveHorizontalLineOfReflection() {
        assertEquals(0, Day13.rowsAboveHorizontalLineOfReflection(test1));
        assertEquals(4, Day13.rowsAboveHorizontalLineOfReflection(test2));
        assertEquals(4, Day13.rowsAboveHorizontalLineOfReflection(test3));
        assertEquals(2, Day13.rowsAboveHorizontalLineOfReflection(test4));
        assertEquals(1, Day13.rowsAboveHorizontalLineOfReflection(test5));
    }

    @Test
    public void day13part1() throws IOException {
        Day13.Path = Day13.testPath1;
        assertEquals(405, Day13.day13part1());
    }

}

package y2023.tests;

import org.junit.Test;
import y2023.Day16;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day16Tests {
    public Day16Tests(){}

    @Test public void createLayoutGrid() throws IOException {
        Day16.setPathToTestPath();
        Day16.chars[] zero = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.SPLITTERVERT, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.MIRRORLEFT, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[] one = new Day16.chars[]{Day16.chars.SPLITTERVERT, Day16.chars.EMPTY, Day16.chars.SPLITTERHORIZ, Day16.chars.EMPTY, Day16.chars.MIRRORLEFT, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[] two = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.SPLITTERVERT, Day16.chars.SPLITTERHORIZ, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[] three = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.SPLITTERVERT, Day16.chars.EMPTY};
        Day16.chars[] four = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[] five = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.MIRRORLEFT};
        Day16.chars[] six = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.MIRRORRIGHT, Day16.chars.EMPTY, Day16.chars.MIRRORLEFT, Day16.chars.MIRRORLEFT, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[] seven = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.SPLITTERHORIZ, Day16.chars.EMPTY, Day16.chars.SPLITTERHORIZ, Day16.chars.MIRRORRIGHT, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.SPLITTERVERT, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[] eight = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.SPLITTERVERT, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.SPLITTERHORIZ, Day16.chars.SPLITTERVERT, Day16.chars.EMPTY, Day16.chars.MIRRORLEFT};
        Day16.chars[] nine = new Day16.chars[]{Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.MIRRORRIGHT, Day16.chars.MIRRORRIGHT, Day16.chars.EMPTY, Day16.chars.SPLITTERVERT, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY, Day16.chars.EMPTY};
        Day16.chars[][] test = new Day16.chars[][]{zero, one, two, three, four, five, six, seven, eight, nine};
        assertArrayEquals(test, Day16.createLayoutGrid());
    }

    @Test public void day16part1() throws Exception {
        Day16.setPathToTestPath();
        assertEquals(46, Day16.day16part1());
    }

    @Test public void day16part2() throws Exception {
        Day16.setPathToTestPath();
        assertEquals(51, Day16.day16part2());
    }
}

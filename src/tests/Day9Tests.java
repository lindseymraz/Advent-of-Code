import org.junit.Test;


import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class Day9Tests {
    public Day9Tests() {}

    long firstExampleOriginal[] = new long[]{0, 3, 6, 9, 12, 15};
    long firstExampleFirstSequence[] = new long[]{3, 3, 3, 3, 3};
    long firstExampleSecondSequence[] = new long[]{0, 0, 0, 0};

    long secondExampleOriginal[] = new long[]{1, 3, 6, 10, 15, 21};
    long secondExampleFirstSequence[] = new long[]{2, 3, 4, 5, 6};
    long secondExampleSecondSequence[] = new long[]{1, 1, 1, 1};
    long secondExampleThirdSequence[] = new long[]{0, 0, 0};

    long thirdExampleOriginal[] = new long[]{10, 13, 16, 21, 30, 45};
    long thirdExampleFirstSequence[] = new long[]{3, 3, 5, 9, 15};
    long thirdExampleSecondSequence[] = new long[]{0, 2, 4, 6,};
    long thirdExampleThirdSequence[] = new long[]{2, 2, 2};
    long thirdExampleFourthSequence[] = new long[]{0, 0};

    long fourthExampleOriginal[] = new long[]{-10, 3, 4, -30};
    long fourthExampleFirstSequence[] = new long[]{13, 1, -34};
    long fourthExampleSecondSequence[] = new long[]{-12, -35};
    long fourthExampleThirdSequence[] = new long[]{-23};

    LinkedList<long[]> testOne = Day9.getAllSequences(firstExampleOriginal, new LinkedList<long[]>());
    LinkedList<long[]> testTwo = Day9.getAllSequences(secondExampleOriginal, new LinkedList<long[]>());
    LinkedList<long[]> testThree = Day9.getAllSequences(thirdExampleOriginal, new LinkedList<long[]>());
    LinkedList<long[]> testFour = Day9.getAllSequences(fourthExampleOriginal, new LinkedList<long[]>());

    @Test
    public void parseHistory() throws Exception {
        assertTrue(Arrays.equals(Day9.parseHistory("0 3 6 9 12 15"), firstExampleOriginal));
        assertTrue(Arrays.equals(Day9.parseHistory("1 3 6 10 15 21"), secondExampleOriginal));
        assertTrue(Arrays.equals(Day9.parseHistory("10 13 16 21 30 45"), thirdExampleOriginal));
        assertTrue(Arrays.equals(Day9.parseHistory("-10 3 4 -30"), fourthExampleOriginal));
    }
    @Test
    public void createSequence() {
        assertTrue(Arrays.equals(Day9.createSequence(firstExampleOriginal), firstExampleFirstSequence));
        assertTrue(Arrays.equals(Day9.createSequence(firstExampleFirstSequence), firstExampleSecondSequence));
        assertTrue(Arrays.equals(Day9.createSequence(secondExampleOriginal), secondExampleFirstSequence));
        assertTrue(Arrays.equals(Day9.createSequence(secondExampleFirstSequence), secondExampleSecondSequence));
        assertTrue(Arrays.equals(Day9.createSequence(secondExampleSecondSequence), secondExampleThirdSequence));
        assertTrue(Arrays.equals(Day9.createSequence(thirdExampleOriginal), thirdExampleFirstSequence));
        assertTrue(Arrays.equals(Day9.createSequence(thirdExampleFirstSequence), thirdExampleSecondSequence));
        assertTrue(Arrays.equals(Day9.createSequence(thirdExampleSecondSequence), thirdExampleThirdSequence));
        assertTrue(Arrays.equals(Day9.createSequence(thirdExampleThirdSequence), thirdExampleFourthSequence));
        assertTrue(Arrays.equals(Day9.createSequence(fourthExampleOriginal), fourthExampleFirstSequence));
        assertTrue(Arrays.equals(Day9.createSequence(fourthExampleFirstSequence), fourthExampleSecondSequence));
        assertTrue(Arrays.equals(Day9.createSequence(fourthExampleSecondSequence), fourthExampleThirdSequence));
    }

    @Test
    public void isAllZero() {

        long allButStart[] = new long[]{1, 0, 0, 0};
        long allButEnd[] = new long[]{0, 0, 0, 1};

        assertFalse(Day9.isAllZero(firstExampleOriginal));
        assertFalse(Day9.isAllZero(firstExampleFirstSequence));
        assertTrue(Day9.isAllZero(firstExampleSecondSequence));

        assertFalse(Day9.isAllZero(secondExampleOriginal));
        assertFalse(Day9.isAllZero(secondExampleFirstSequence));
        assertFalse(Day9.isAllZero(secondExampleSecondSequence));
        assertTrue(Day9.isAllZero(secondExampleThirdSequence));

        assertFalse(Day9.isAllZero(thirdExampleOriginal));
        assertFalse(Day9.isAllZero(thirdExampleFirstSequence));
        assertFalse(Day9.isAllZero(thirdExampleSecondSequence));
        assertFalse(Day9.isAllZero(thirdExampleThirdSequence));
        assertTrue(Day9.isAllZero(thirdExampleFourthSequence));

        assertFalse(Day9.isAllZero(fourthExampleOriginal));
        assertFalse(Day9.isAllZero(fourthExampleFirstSequence));
        assertFalse(Day9.isAllZero(fourthExampleSecondSequence));
        assertFalse(Day9.isAllZero(fourthExampleThirdSequence));

        assertFalse(Day9.isAllZero(allButStart));
        assertFalse(Day9.isAllZero(allButEnd));
    }

    @Test
    public void getAllSequences() {
        assertTrue(Arrays.equals(testOne.get(0), firstExampleOriginal));
        assertTrue(Arrays.equals(testOne.get(1), firstExampleFirstSequence));
        assertTrue(Arrays.equals(testOne.get(2), firstExampleSecondSequence));
        assertTrue(testOne.size() == 3);

        assertTrue(Arrays.equals(testTwo.get(0), secondExampleOriginal));
        assertTrue(Arrays.equals(testTwo.get(1), secondExampleFirstSequence));
        assertTrue(Arrays.equals(testTwo.get(2), secondExampleSecondSequence));
        assertTrue(Arrays.equals(testTwo.get(3), secondExampleThirdSequence));
        assertTrue(testTwo.size() == 4);

        assertTrue(Arrays.equals(testThree.get(0), thirdExampleOriginal));
        assertTrue(Arrays.equals(testThree.get(1), thirdExampleFirstSequence));
        assertTrue(Arrays.equals(testThree.get(2), thirdExampleSecondSequence));
        assertTrue(Arrays.equals(testThree.get(3), thirdExampleThirdSequence));
        assertTrue(Arrays.equals(testThree.get(4), thirdExampleFourthSequence));
        assertTrue(testThree.size() == 5);

        assertTrue(Arrays.equals(testFour.get(0), fourthExampleOriginal));
        assertTrue(Arrays.equals(testFour.get(1), fourthExampleFirstSequence));
        assertTrue(Arrays.equals(testFour.get(2), fourthExampleSecondSequence));
        assertTrue(Arrays.equals(testFour.get(3), fourthExampleThirdSequence));
        assertTrue(testFour.size() == 5);
    }

    @Test
    public void fillValue() {
        assertEquals(3, Day9.fillValue(0, firstExampleFirstSequence));
        assertEquals(18, Day9.fillValue(3, firstExampleOriginal));

        assertEquals(1, Day9.fillValue(0, secondExampleSecondSequence));
        assertEquals(7, Day9.fillValue(1, secondExampleFirstSequence));
        assertEquals(28, Day9.fillValue(7, secondExampleOriginal));

        assertEquals(2, Day9.fillValue(0, thirdExampleThirdSequence));
        assertEquals(8, Day9.fillValue(2, thirdExampleSecondSequence));
        assertEquals(23, Day9.fillValue(8, thirdExampleFirstSequence));
        assertEquals(68, Day9.fillValue(23, thirdExampleOriginal));

        assertEquals(2, Day9.fillValue(0, thirdExampleThirdSequence));
        assertEquals(8, Day9.fillValue(2, thirdExampleSecondSequence));
        assertEquals(23, Day9.fillValue(8, thirdExampleFirstSequence));
        assertEquals(68, Day9.fillValue(23, thirdExampleOriginal));

        assertEquals(-23, Day9.fillValue(0, fourthExampleThirdSequence));
        assertEquals(-58, Day9.fillValue(-23, fourthExampleSecondSequence));
        assertEquals(-92, Day9.fillValue(-58, fourthExampleFirstSequence));
        assertEquals(-122, Day9.fillValue(-92, fourthExampleOriginal));
    }

    @Test
    public void getLastFilledValue() {
        assertEquals(18, Day9.getLastFilledValue(0, testOne.size() - 2, testOne));
        assertEquals(28, Day9.getLastFilledValue(0, testTwo.size() - 2, testTwo));
        assertEquals(68, Day9.getLastFilledValue(0, testThree.size() - 2, testThree));
        assertEquals(-122, Day9.getLastFilledValue(0, testFour.size() - 2, testFour));

    }

    @Test
    public void sumFilledValues() {
        assertEquals(21, Day9.sumFilledValues(0, testOne.size() - 2, testOne, 0));
        assertEquals(36, Day9.sumFilledValues(0, testTwo.size() - 2, testTwo, 0));
        assertEquals(101, Day9.sumFilledValues(0, testThree.size() - 2, testThree, 0));
        assertEquals(-295, Day9.sumFilledValues(0, testFour.size() - 2, testFour, 0));

    }
}

import org.junit.Test;
import y2023.Day5;

import static org.junit.Assert.*;

public class Day5Tests {
    public Day5Tests() {}

    @Test
    public void contains() {
        assertTrue(Day5.contains(3, 3, 1)); //on left bound
        assertTrue(Day5.contains(3, 3, 9)); //on left bound
        assertTrue(Day5.contains(3, 2, 2)); //on right bound
        assertTrue(Day5.contains(3, 2, 4)); //within
        assertFalse(Day5.contains(2, 3, 4)); //outside left
        assertFalse(Day5.contains(7, 3, 4)); //outside right
    }

   /*
    @Test
    public void sourceContains() throws IOException {
        y2023.Day5 e = new y2023.Day5();
        y2023.Day5.Mapping f = e.new Mapping(15, 0, 37);
        assertTrue(f.sourceContains(30, 31)); //within
        assertTrue(f.sourceContains(14, 15)); //left boundary
        assertTrue(f.sourceContains(51, 57)); //right boundary
        assertTrue(f.sourceContains(13, 30)); //cross left boundary
        assertTrue(f.sourceContains(30, 60)); //cross right boundary
        assertFalse(f.sourceContains(0, 9)); //outside left boundary
        assertFalse(f.sourceContains(100, 109)); //outside right boundary
    }

    @Test
    public void mappingContains() throws IOException {
        y2023.Day5 e = new y2023.Day5();
        y2023.Day5.Mapping f = e.new Mapping(30, 10, 11);
        assertTrue(f.destContains(13, 15)); //within
        assertTrue(f.destContains(0, 10)); //left boundary
        assertTrue(f.destContains(20, 23)); //right boundary
        assertTrue(f.destContains(9, 13)); //cross left boundary
        assertTrue(f.destContains(15, 21)); //cross right boundary
        assertFalse(f.destContains(0, 9)); //outside left boundary
        assertFalse(f.destContains(41, 109)); //outside right boundary
        assertTrue(f.destWhollyContains(13, 15)); //within
        assertFalse(f.destWhollyContains(0, 10)); //left boundary
        assertFalse(f.destWhollyContains(20, 23)); //right boundary
        assertFalse(f.destWhollyContains(9, 13)); //cross left boundary
        assertFalse(f.destWhollyContains(15, 21)); //cross right boundary
        assertFalse(f.destWhollyContains(0, 9)); //outside left boundary
        assertFalse(f.destWhollyContains(41, 109)); //outside right boundary
        assertTrue(f.destContainsLeftHalf(13)); //within
        assertFalse(f.destContainsLeftHalf(0)); //left boundary
        assertTrue(f.destContainsLeftHalf(20)); //right boundary
        assertFalse(f.destContainsLeftHalf(9)); //cross left boundary
        assertTrue(f.destContainsLeftHalf(15)); //cross right boundary
        assertFalse(f.destContainsLeftHalf(0)); //outside left boundary
        assertFalse(f.destContainsLeftHalf(41)); //outside right boundary
        assertTrue(f.destContainsRightHalf(13, 15)); //within
        assertTrue(f.destContainsRightHalf(0, 10)); //left boundary
        assertFalse(f.destContainsRightHalf(20, 23)); //right boundary
        assertTrue(f.destContainsRightHalf(9, 13)); //cross left boundary
        assertFalse(f.destContainsRightHalf(15, 21)); //cross right boundary
        assertFalse(f.destContainsRightHalf(0, 9)); //outside left boundary
        assertFalse(f.destContainsRightHalf(41, 109)); //outside right boundary

    }
    */
}
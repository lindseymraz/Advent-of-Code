import org.junit.Test;
import y2023.Day6;

import static org.junit.Assert.*;
public class Day6Tests {
    public Day6Tests() {}

    @Test
    public void waysToBeat() {
        assertEquals(4, Day6.waysToBeat(7, 9));
        assertEquals(8, Day6.waysToBeat(15, 40));
        assertEquals(9, Day6.waysToBeat(30, 200));
        assertEquals(71503, Day6.waysToBeat(71530, 940200));
    }


}

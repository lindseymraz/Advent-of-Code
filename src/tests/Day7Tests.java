import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day7Tests {

    public Day7Tests() {}
    @Test
    public void typeHand() {
        assertEquals(7, Day7.typeHand("AAAAA"));
        assertEquals(6, Day7.typeHand("AA8AA"));
        assertEquals(5, Day7.typeHand("23332"));
        assertEquals(4, Day7.typeHand("TTT98"));
        assertEquals(3, Day7.typeHand("23432"));
        assertEquals(2, Day7.typeHand("A23A4"));
        assertEquals(1, Day7.typeHand("23456"));
        assertEquals(6, Day7.typeHand("33332"));
        assertEquals(6, Day7.typeHand("2AAAA"));
        assertEquals(5, Day7.typeHand("77888"));
        assertEquals(5, Day7.typeHand("77788"));
        assertEquals(2, Day7.typeHand("32T3K"));
        assertEquals(4, Day7.typeHand("T55J5"));
        assertEquals(3, Day7.typeHand("KK677"));
        assertEquals(3, Day7.typeHand("KTJJT"));
        assertEquals(4, Day7.typeHand("QQQJA"));
    }

    @Test
    public void weakerHand() {
        assertEquals("2AAAA", Day7.weakerHand("33332", "2AAAA"));
        assertEquals("77788", Day7.weakerHand("77888", "77788"));
        assertEquals("AAAA4", Day7.weakerHand("AAAA4", "AAAAA"));
    }
}


import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Day15Tests {
    public Day15Tests() {}

    @Test
    public void hashHelper() {
        assertEquals(200, Day15.hashHelper('H', 0));
        assertEquals(153, Day15.hashHelper('A', 200));
        assertEquals(172, Day15.hashHelper('S', 153));
        assertEquals(52, Day15.hashHelper('H', 172));
    }

    @Test
    public void hashString() {
        assertEquals(52, Day15.hashString("HASH"));
        assertEquals(30, Day15.hashString("rn=1"));
        assertEquals(253, Day15.hashString("cm-"));
        assertEquals(97, Day15.hashString("qp=3"));
        assertEquals(47, Day15.hashString("cm=2"));
        assertEquals(14, Day15.hashString("qp-"));
        assertEquals(180, Day15.hashString("pc=4"));
        assertEquals(9, Day15.hashString("ot=9"));
        assertEquals(197, Day15.hashString("ab=5"));
        assertEquals(48, Day15.hashString("pc-"));
        assertEquals(214, Day15.hashString("pc=6"));
        assertEquals(231, Day15.hashString("ot=7"));
    }

    @Test
    public void day15part1() throws IOException {
        Day15.setPathToTestPath();
        assertEquals(1320, Day15.day15part1());
    }
}

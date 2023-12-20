
import org.junit.Assert;
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

    @Test
    public void removeLensWithLabel() {
        Day15 a = new Day15();
        Day15.Box zero = a.new Box();
        Day15.Box one = a.new Box();
        Day15.Box three = a.new Box();
        Day15.Box two = a.new Box();
        Day15.Box testBoxZero = a.new Box();
        Day15.Box testBoxOne = a.new Box();
        Day15.Box testBoxThree = a.new Box();
        Day15.Box testBoxTwo = a.new Box();
        Day15.Lens rn = a.new Lens("rn", 1);
        Day15.Lens qp = a.new Lens("qp", 3);
        Day15.Lens pc = a.new Lens("pc", 4);
        Day15.Lens ot = a.new Lens("ot", 9);
        Day15.Lens ab = a.new Lens("ab", 5);
        testBoxZero.lenses.add(0, rn);
        zero.lenses.add(0, rn);
        zero.removeLensWithLabel("cm");
        assertArrayEquals(testBoxZero.lenses.toArray(), zero.lenses.toArray()); //remove nothing
        testBoxOne.lenses.add(0, qp);
        one.lenses.add(0, qp);
        one.removeLensWithLabel("qp");
        assertArrayEquals(testBoxThree.lenses.toArray(), one.lenses.toArray()); //remove only
        three.lenses.add(0, pc);
        three.lenses.add(1, ot);
        three.lenses.add(2, ab);
        three.removeLensWithLabel("pc");
        testBoxThree.lenses.add(0, ot);
        testBoxThree.lenses.add(1, ab);
        assertArrayEquals(testBoxThree.lenses.toArray(), three.lenses.toArray()); //remove start
        two.lenses.add(0, pc);
        two.lenses.add(1, ot);
        two.lenses.add(2, ab);
        two.removeLensWithLabel("ab");
        testBoxTwo.lenses.add(0, pc);
        testBoxTwo.lenses.add(1, ot);
        assertArrayEquals(testBoxTwo.lenses.toArray(), two.lenses.toArray()); //remove end
    }

    @Test
    public void addNewLens() {
        Day15 a = new Day15();
        Day15.Box zero = a.new Box();
        Day15.Box one = a.new Box();
        Day15.Box three = a.new Box();
        Day15.Box two = a.new Box();
        Day15.Box testBoxZero = a.new Box();
        Day15.Box testBoxOne = a.new Box();
        Day15.Box testBoxThree = a.new Box();
        Day15.Box testBoxTwo = a.new Box();
        Day15.Lens rn = a.new Lens("rn", 1);
        Day15.Lens qp = a.new Lens("qp", 3);
        Day15.Lens cm = a.new Lens("cm", 2);
        Day15.Lens pc = a.new Lens("pc", 4);
        Day15.Lens ot = a.new Lens("ot", 9);
        Day15.Lens ot7 = a.new Lens("ot", 7);
        zero.addNewLens("rn", 1);
        testBoxZero.lenses.add(rn);
        assertArrayEquals(testBoxZero.lenses.toArray(), zero.lenses.toArray()); //from zero
        zero.addNewLens("cm", 2);
        testBoxZero.lenses.add(cm);
        assertArrayEquals(testBoxZero.lenses.toArray(), zero.lenses.toArray()); //from one
        zero.addNewLens("ot", 9);
        testBoxZero.lenses.add(ot);
        assertArrayEquals(testBoxZero.lenses.toArray(), zero.lenses.toArray()); //from two
        zero.addNewLens("ot", 7);
        testBoxZero.lenses.remove(ot);
        testBoxZero.lenses.add(ot7);
        assertArrayEquals(testBoxZero.lenses.toArray(), zero.lenses.toArray()); //replace at end
        one.addNewLens("ot", 9);
        one.addNewLens("ot", 7);
        testBoxOne.lenses.add(ot7);
        assertArrayEquals(testBoxOne.lenses.toArray(), one.lenses.toArray()); //replace at only
        two.addNewLens("ot", 9);
        two.addNewLens("cm", 2);
        two.addNewLens("rn", 1);
        two.addNewLens("ot", 7);
        testBoxTwo.lenses.add(ot7);
        testBoxTwo.lenses.add(cm);
        testBoxTwo.lenses.add(rn);
        assertArrayEquals(testBoxTwo.lenses.toArray(), two.lenses.toArray()); //replace at start
        three.addNewLens("pc", 4);
        three.addNewLens("qp", 3);
        three.addNewLens("ot", 9);
        three.addNewLens("cm", 2);
        three.addNewLens("rn", 1);
        three.addNewLens("ot", 7);
        testBoxThree.lenses.add(pc);
        testBoxThree.lenses.add(qp);
        testBoxThree.lenses.add(ot7);
        testBoxThree.lenses.add(cm);
        testBoxThree.lenses.add(rn);
        assertArrayEquals(testBoxThree.lenses.toArray(), three.lenses.toArray()); //replace at middle
    }

    @Test
    public void addRemoveLenses() {
        Day15 a = new Day15();
        Day15.Box zero = a.new Box();
        Day15.Box one = a.new Box();
        Day15.Box three = a.new Box();
        Day15.Box testBoxZero = a.new Box();
        Day15.Box testBoxOne = a.new Box();
        Day15.Box testBoxThree = a.new Box();
        Day15.Lens rn = a.new Lens("rn", 1);
        Day15.Lens qp = a.new Lens("qp", 3);
        Day15.Lens cm = a.new Lens("cm", 2);
        Day15.Lens pc = a.new Lens("pc", 4);
        Day15.Lens ot = a.new Lens("ot", 9);
        Day15.Lens ab = a.new Lens("ab", 5);
        Day15.Lens pc6 = a.new Lens("pc", 6);
        Day15.Lens ot7 = a.new Lens("ot", 7);
        zero.addNewLens("rn", 1);
        testBoxZero.lenses.add(rn);
        assertEquals(testBoxZero, zero);
        zero.removeLensWithLabel("rn");
        testBoxZero.lenses.remove(rn);
        assertEquals(testBoxZero, zero);
        one.addNewLens("qp", 3);
        testBoxOne.lenses.add(qp);
        assertEquals(testBoxOne, one);
        zero.addNewLens("cm", 2);
        testBoxZero.lenses.add(cm);
        assertEquals(testBoxZero, zero);
        one.removeLensWithLabel("qp");
        testBoxOne.lenses.remove(qp);
        assertEquals(testBoxOne, one);
        three.addNewLens("pc", 4);
        testBoxThree.lenses.add(pc);
        assertEquals(testBoxThree, three);
        three.addNewLens("ot", 9);
        testBoxThree.lenses.add(ot);
        assertEquals(testBoxThree, three);
        three.addNewLens("ab", 5);
        testBoxThree.lenses.add(ab);
        assertEquals(testBoxThree, three);
        three.removeLensWithLabel("pc");
        testBoxThree.lenses.remove(pc);
        assertEquals(testBoxThree, three);
        three.addNewLens("pc", 6);
        testBoxThree.lenses.add(pc6);
        assertEquals(testBoxThree, three);
        three.addNewLens("ot", 7);
        testBoxThree.lenses.remove(ot);
        testBoxThree.lenses.add(0, ot7);
        assertEquals(testBoxThree, three);
    }


    @Test
    public void getTotalFocusingPower() {
        Day15 a = new Day15();
        Day15.Box zero = a.new Box();
        Day15.Box one = a.new Box();
        Day15.Box two = a.new Box();
        Day15.Box three = a.new Box();
        Day15.Lens rn = a.new Lens("rn", 1);
        Day15.Lens cm = a.new Lens("cm", 2);
        Day15.Lens ab = a.new Lens("ab", 5);
        Day15.Lens pc6 = a.new Lens("pc", 6);
        Day15.Lens ot7 = a.new Lens("ot", 7);
        zero.lenses.add(rn);
        zero.lenses.add(cm);
        three.lenses.add(ot7);
        three.lenses.add(ab);
        three.lenses.add(pc6);
        Day15.Box[] boxes = new Day15.Box[]{zero, one, two, three};
        Day15.getTotalFocusingPower(boxes);
    }

    @Test
    public void day15part2() throws IOException {
        Day15.setPathToTestPath();
        assertEquals(145, Day15.day15part2());
    }

}

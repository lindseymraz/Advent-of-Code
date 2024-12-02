package y2024.Tests;

import org.junit.Test;
import y2024.Day2;

import java.io.IOException;

import static org.junit.Assert.*;

public class Tests {
    public Tests() {}


    @Test
    public void Day2Safe() throws IOException {
        assertEquals(257, Day2.part1());
    }
}

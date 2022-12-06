import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedTestsTest {
    private static List<Integer> list;
    @BeforeAll
    static void setup() {
        list = new ArrayList<>();
    }
    @RepeatedTest(value = 5)
    void testRepitition(RepetitionInfo repetitionInfo) {
        list.add(repetitionInfo.getCurrentRepetition());
        assertEquals(repetitionInfo.getCurrentRepetition(), list.size());
    }
}
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertThrowsTest {
    private SUT system = new SUT("SystemUnderTest");

    @Test
    void testExpectedException() {
        assertThrows(NoJobException.class, system::run);
    }

    @Test
    void testCatchException() {
        Throwable throwable = assertThrows(NoJobException.class, () -> system.run(1000));

        assertEquals("No Jobs!", throwable.getMessage());
    }
}


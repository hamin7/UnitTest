import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class AssertTimeoutTest {
    private SUT sut = new SUT("SystemUnderTest");

    @Test
    void testTimeout() throws InterruptedException {
        sut.addJob(new Job("Job1"));
        assertTimeout(ofMillis(500), () -> sut.run(50));
    }

    @Test
    void testTimeoutPreemptively() throws InterruptedException {
        sut.addJob(new Job("Job1"));
        assertTimeoutPreemptively(ofMillis(500), () -> sut.run(50));
    }
}

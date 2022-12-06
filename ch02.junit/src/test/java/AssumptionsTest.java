import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.*;

class AssumptionsTest {
    private static String EXPECTED_JAVA_VERSION = "8";
    private Environment environment = new Environment(
            new JavaSpecification(System.getProperty("java.vm.specification.version")),
            new OperationSystem(System.getProperty("os.name"), System.getProperty("os.arch"))
    );

    private SUT system = new SUT("SystemUnderTest");

    @BeforeEach
    void setUp() {
//        assumeTrue(environment.isWindows());
        assumeTrue(environment.isMacOS());
    }

    @Test
    void testNoJobToRun() {
        assumingThat(
                () -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(system.hasJobToRun()));
    }

    @Test
    void testJobToRun() {
        assumeTrue(environment.isAmd64Architecture());
//        assumeTrue(environment.isArm64Architecture());
        system.run(new Job("Job1"));

        assertTrue(system.hasJobToRun());
    }
}
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestInfoTest {
    TestInfoTest(TestInfo testInfo) {
        assertEquals("TestInfoTest", testInfo.getDisplayName());
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("테스트 메서드") || displayName.equals("testGetNameOfTheMethod(TestInfo)"));
    }

    @Test
    void testGetNameOfTheMethod(TestInfo testInfo) {
        assertEquals("testGetNameOfTheMethod", testInfo.getTestMethod().get().getName());
    }

    @Test
    @DisplayName("테스트 메서드")
    void testGetNameOfTheMethodWithDisplayNameAnnotation(TestInfo testInfo) {
        assertEquals("테스트 메서드", testInfo.getDisplayName());
    }
}
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

class TestReporterTest {

    @Test
    void testReportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("값");
    }

    @Test
    void testReportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("키", "값");
    }

    @Test
    void testReportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user", "kim1");
        values.put("password", "password1");

        testReporter.publishEntry(values);
    }

}
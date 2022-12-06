import org.junit.jupiter.api.*;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestsTest {
    private PositiveNumberPredicate predicate = new PositiveNumberPredicate();

    @TestFactory
    Iterator<DynamicTest> positiveNumberPredicateTestCases() {
        return asList(
                dynamicTest("음수", () -> assertFalse(predicate.check(-1))),
                dynamicTest("0", () -> assertFalse(predicate.check(0))),
                dynamicTest("양수", () -> assertTrue(predicate.check(1)))
        ).iterator();
    }
}

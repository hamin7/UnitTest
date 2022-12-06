import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTestsTest {
    @ParameterizedTest
    @ValueSource(strings = {"김일", "김이", "김삼"})
    void testValueSource(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        assertEquals(name, customer.getName());
    }
    @ParameterizedTest
    @CsvSource({"10, 20, 30", "1, 2, 3", "-10, 20, 10", "0, 0, 0"})
    void testSum(int x, int y, int z) {
        Calculator calc = new Calculator();
        assertEquals(z, calc.sum(x, y));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/demo/values.csv")
    void testSumWithCsvFile(int x, int y, int z) {
        Calculator calc = new Calculator();
        assertEquals(z, calc.sum(x, y));
    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.sum(10, 20) == 30);
        System.out.println(calc.sum(1, 2) == 3);
        System.out.println(calc.sum(-10, 20) == 10);
        System.out.println(calc.sum(0, 0) == 0);
    }

    @Test
    void testSum() {
        Calculator calc = new Calculator();
        assertEquals(30, calc.sum(10, 20));
        assertEquals(3, calc.sum(1, 2));
        assertEquals(10, calc.sum(-10, 20));
        assertEquals(0, calc.sum(0, 0));
    }
}

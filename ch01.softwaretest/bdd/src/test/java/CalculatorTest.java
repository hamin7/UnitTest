import io.cucumber.java.ko.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calc;
    int result;

    @먼저("계산기 객체를 생성한다")
    public void 객체를_생성한다() {
        calc = new Calculator();
    }

    @만약("두 수 {int}과 {int}을 더한다")
    public void addAnd(int x, int y) {
        result = calc.add(x, y);
    }

    @그러면("결과는 {int}이어야 한다")
    public void produce(int z) {
        assertEquals(z, result);
    }
}

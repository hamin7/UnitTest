import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {
    Customer customer;
    static String name;
    static String address;
    static String email;
    long id;
    @BeforeAll
    static void setup() {
        name = "김일";
        address = "서울시";
        email = "kim1@gmail.com";
        System.out.println("@BeforeAll");
    }
    @BeforeEach
    void init() {
        // Given : Customer 객체를 생성한다
        id = 1;
        customer = new Customer();
        System.out.println("@BeforeEach");
    }
    @Test
    void testCustomerInfo() {
        // When : 필드값을 저장한다
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        // Then : 필드값을 읽으면 동일한 값을 반환해야 한다
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getAddress()).isEqualTo(address);
        assertThat(customer.getEmail()).isEqualTo(email);
    }
    @Test
    void testValidInstanceOfCustomer() {
        // Then : Customer 객체를 생성할 수 있어야 한다
        assertThat(customer).isNotNull();
    }
    @Test
    void testNameEmailNotEmpty() {
        // When : 이름과 이메일 필드에 값을 저장하지 않는다
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        // Then : Customer 객체의 이름과 이메일은 비어있지 않아야 한다.
        assertThat(customer.getName()).isNotNull().isNotEmpty();
        assertThat(customer.getEmail()).isNotNull().isNotEmpty();
    }
    @Test
    void testIdChange() {
        // Given : 이전 id 값을 구한다
        long oldid = customer.getId();
        long newid = 10;
        // When : id 필드 값을 변경한다
        // customer.setId(newid);
        // Then : 이전 id 값과 동일해야 한다.
        assertThat(customer.getId()).isEqualTo(oldid);
    }
}

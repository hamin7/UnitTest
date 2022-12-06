import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {
    static String name;
    static String address;
    static String email;
    Customer customer;

    @BeforeAll
    static void setup() {
        name = "kim1";
        address = "seoul";
        email = "ccc@gmail.com";
        System.out.println("@BeforeAll : setup");
    }

    @BeforeEach
    void init() {
        customer = new Customer();
        System.out.printf("@BeforeEach : init");
    }

    @Test
    void testCustomerInfo(){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        assertEquals(name, customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals(email, customer.getEmail());
    }

    @Test void testCustomerInfo2() {
        customer.setName("Test2");
        assertEquals("Test2", customer.getName());
    }

    @Test
    @Tag("important")
    void testValidInstanceOfCustomer() {
        assertNotNull(customer);
    }

    @Test
    @Tag("important")
    void testNameEmailNotEmpty() {
        customer.setAddress(address);
        assertNotNull(customer.getName());
        assertNotNull(customer.getEmail());
        assertFalse(customer.getName().equals(""));
        assertFalse(customer.getEmail().equals(""));
    }

    @Test
    void testIdChange() {
        
    }
}
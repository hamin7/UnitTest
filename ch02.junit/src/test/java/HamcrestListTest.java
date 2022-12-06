import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HamcrestListTest {

    private List<String> values;

    @BeforeEach
    public void init() {
        values = new ArrayList<>();
        values.add("값1");
        values.add("값2");
        values.add("값3");
    }

    @Test
    public void testListWithoutHamcrest() {
        assertEquals(3, values.size());
        assertTrue(values.contains("값1") || values.contains("값2") || values.contains("값3"));
    }

    @Test
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("값1"), equalTo("값2"), equalTo("값3"))));
    }

    @Test
    public void testHamcrestIs() {
        assertThat("값1", is(values.get(0)));
        assertThat("값1", anyOf(is(values.get(0)), is(values.get(1)), is(values.get(2))));
        values.add("값1");
        assertThat("값1", allOf(is(values.get(0)), is(values.get(3))));
    }
    @Test
    public void testCustomerNull() {
        Customer customer = null;
        assertThat(customer, nullValue());
        customer = new Customer();
        assertThat(customer, notNullValue());
    }
    @Test
    public void testCustomerProperty() {
        Customer customer = new Customer();;
        customer.setName("김일");
        customer.setAddress("서울시");
        customer.setEmail("kim1@gmail.com");
        assertThat(customer, allOf(
                hasProperty("name", is("김일")),
                hasProperty("address", is("서울시")),
                hasProperty("email", is("kim1@gmail.com"))
        ));
    }

    @Test
    public void testCustomerContains() {
        assertThat("순서화 객체", values, contains("값1", "값2", "값3"));
        assertThat("모든 객체", values, containsInAnyOrder("값2", "값3", "값1"));
    }
}

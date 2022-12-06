package airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {

        private Flight economyFlight;

        @BeforeEach
        void setUp() {
            economyFlight = new Flight("1", "Economy");
        }

        @Test
        public void testEconomyFlightRegularPassenger() {
            Passenger roger = new Passenger("Roger", false);

            assertEquals("1", economyFlight.getId());
            assertEquals(true, economyFlight.addPassenger(roger));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("Roger", economyFlight.getPassengers().get(0).getName());

            assertEquals(true, economyFlight.removePassenger(roger));
            assertEquals(0, economyFlight.getPassengers().size());
        }

        @Test
        public void testEconomyFlightVipPassenger() {
            Passenger rafael = new Passenger("Rafael", true);

            assertEquals("1", economyFlight.getId());
            assertEquals(true, economyFlight.addPassenger(rafael));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("Rafael", economyFlight.getPassengers().get(0).getName());

            assertEquals(false, economyFlight.removePassenger(rafael));
            assertEquals(1, economyFlight.getPassengers().size());
        }

    }

    @DisplayName("Given there is a business flight")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new Flight("2", "Business");
        }

        @Test
        public void testBusinessFlightRegularPassenger() {
            Passenger roger = new Passenger("Roger", false);

            assertEquals(false, businessFlight.addPassenger(roger));
            assertEquals(0, businessFlight.getPassengers().size());
            assertEquals(false, businessFlight.removePassenger(roger));
            assertEquals(0, businessFlight.getPassengers().size());

        }

        @Test
        public void testBusinessFlightVipPassenger() {
            Passenger rafael = new Passenger("Rafael", true);

            assertEquals(true, businessFlight.addPassenger(rafael));
            assertEquals(1, businessFlight.getPassengers().size());
            assertEquals(false, businessFlight.removePassenger(rafael));
            assertEquals(1, businessFlight.getPassengers().size());

        }

    }
}

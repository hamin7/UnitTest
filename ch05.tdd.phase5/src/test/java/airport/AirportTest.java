package airport;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {

        private Flight economyFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When we have a regular passenger")
        class RegularPassenger {

            @Test
            @DisplayName("Then you can add and remove him from an economy flight")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verify all conditions for a regular passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengers()).get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengers().size())
                );
            }

            @DisplayName("Then you cannot add him to an economy flight more than once")
            @RepeatedTest(5)
            public void testEconomyFlightRegularPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }
                assertAll("Verify a regular passenger can be added to an economy flight only once",
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertTrue(economyFlight.getPassengers().contains(mike)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengers()).get(0).getName().equals("Mike"))
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassenger {
            @Test
            @DisplayName("Then you can add him but cannot remove him from an economy flight")
            public void testEconomyFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("James", new ArrayList<>(economyFlight.getPassengers()).get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengers().size())
                );
            }

            @DisplayName("Then you cannot add him to an economy flight more than once")
            @RepeatedTest(5)
            public void testEconomyFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(james);
                }
                assertAll("Verify a VIP passenger can be added to an economy flight only once",
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertTrue(economyFlight.getPassengers().contains(james)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengers()).get(0).getName().equals("James"))
                );
            }
        }
    }

    @DisplayName("Given there is a business flight")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When we have a regular passenger")
        class RegularPassenger {

            @Test
            @DisplayName("Then you cannot add or remove him from a business flight")
            public void testBusinessFlightRegularPassenger() {
                assertAll("Verify all conditions for a regular passenger and a business flight",
                        () -> assertEquals(false, businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengers().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassenger {

            @Test
            @DisplayName("Then you can add him but cannot remove him from a business flight")
            public void testBusinessFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and a business flight",
                        () -> assertEquals(true, businessFlight.addPassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengers().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengers().size())
                );
            }

            @DisplayName("Then you cannot add him to a business flight more than once")
            @RepeatedTest(5)
            public void testBusinessFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    businessFlight.addPassenger(james);
                }
                assertAll("Verify a VIP passenger can be added to a business flight only once",
                        () -> assertEquals(1, businessFlight.getPassengers().size()),
                        () -> assertTrue(businessFlight.getPassengers().contains(james)),
                        () -> assertTrue(new ArrayList<>(businessFlight.getPassengers()).get(0).getName().equals("James"))
                );
            }
        }
    }

    @DisplayName("Given there is a premium flight")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When we have a regular passenger")
        class RegularPassenger {

            @Test
            @DisplayName("Then you cannot add or remove him from a premium flight")
            public void testPremiumFlightRegularPassenger() {
                assertAll("Verify all conditions for a regular passenger and a premium flight",
                        () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassenger {

            @Test
            @DisplayName("Then you can add and remove him from a premium flight")
            public void testPremiumFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and a premium flight",
                        () -> assertEquals(true, premiumFlight.addPassenger(james)),
                        () -> assertEquals(1, premiumFlight.getPassengers().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(james)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }

            @DisplayName("Then you cannot add him to a premium flight more than once")
            @RepeatedTest(5)
            public void testPremiumFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    premiumFlight.addPassenger(james);
                }
                assertAll("Verify a VIP passenger can be added to a premium flight only once",
                        () -> assertEquals(1, premiumFlight.getPassengers().size()),
                        () -> assertTrue(premiumFlight.getPassengers().contains(james)),
                        () -> assertTrue(new ArrayList<>(premiumFlight.getPassengers()).get(0).getName().equals("James"))
                );
            }
        }
    }
}


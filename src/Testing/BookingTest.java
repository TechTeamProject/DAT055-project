package src.Testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Model.Booking;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BookingTest {
    Booking booking1;
    Booking booking2;
    LocalDateTime localDateTime;
    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.now();
        booking1 = new Booking(localDateTime, localDateTime, "test1", "testplace1");
        localDateTime = localDateTime.plusHours(2);
        booking2 = new Booking(localDateTime, localDateTime, "test2", "testplace2");
    }

    @Test
    void getDescription() {
        assertNotEquals(booking1.getDescription(), booking2.getDescription());
    }

    @Test
    void getLocation() {
        assertNotEquals(booking1.getLocation(), booking2.getLocation());
    }

    @Test
    void getStartTime() {
        assertNotEquals(booking1.getStartTime(), booking2.getStartTime());
    }

    @Test
    void getEndTime() {
        assertNotEquals(booking1.getEndTime(), booking2.getEndTime());
    }

    @Test
    void compareTo() {
        assertNotEquals(booking1.compareTo(booking2), booking2.compareTo(booking1));
        assertNotEquals(booking1.compareTo(booking1), booking1.compareTo(booking2));
    }
}
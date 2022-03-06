package src.Testing;

import org.junit.jupiter.api.Test;
import src.Model.Weather;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    void getTemperature() {
        String temp = Weather.getTemperature();
        assertNotEquals(temp, "Input/Output error");
        assertNotEquals(temp, "Connection Interrupted");
    }
}
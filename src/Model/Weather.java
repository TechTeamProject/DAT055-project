package src.Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * This class is used to request the weather from visualcrossing.com.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-02
 */
public class Weather {
    private static String temperature = requestWeather();

    /**
     * Getter method for the temperature variable.
     * @return String The temperate in Gothenburg.
     */
    public static String getTemperature() {
        return temperature;
    }

    /**
     * A method used to request and return the current temperature in Gothenburg in Celsius.
     * @return String The temperature in Gothenburg.
     */
    private static String requestWeather() {
        HttpResponse<String> response;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Gothenburg?unitGroup=metric&elements=temp&include=current&key=P7UUKZV2W3724RGBC4A3MUWVQ&contentType=csv"))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (java.io.IOException e) {
            return "Input/Output error";
        } catch (InterruptedException e) {
            return "Connection Interrupted";
        }
        return response.body().replace("temp", "").replace("\n", "") + " °C";
    }
}
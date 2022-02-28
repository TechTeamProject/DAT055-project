package src;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Weather {
    public String getWeather() {
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
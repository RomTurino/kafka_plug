package com.dummy.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

public class JokeParser {
    public static String parseJoke() {
        try {
            URI uri = new URIBuilder("https://v2.jokeapi.dev/joke/Programming")
                    .addParameter("blacklistFlags", "political,racist,sexist")
                    .addParameter("type", "single")
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.body());

            return jsonResponse.get("joke").asText();

        } catch (Exception e) {
            return "This is a joke";
        }
    }
}

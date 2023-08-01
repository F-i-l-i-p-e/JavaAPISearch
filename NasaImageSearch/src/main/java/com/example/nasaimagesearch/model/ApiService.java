package com.example.nasaimagesearch.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// Class to handle API calls for image search
public class ApiService {
    // API key and base URL for NASA API
    private static final String API_KEY = "BddipxDRKp0CCyYwDuxa7PUIBMjrAxs5ltyWesg2";
    private static final String BASE_URL = "https://images-api.nasa.gov";

    // Method to search for images using the NASA API
    public List<Image> searchImages(String query) throws IOException {
        // Encode the search query to handle spaces and special characters
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        String urlString = BASE_URL + "/search?q=" + encodedQuery + "&media_type=image";

        // Make the API call and get the JSON response
        JsonObject json = makeApiCall(urlString);

        // Parse the JSON response and extract the image data
        List<Image> images = new ArrayList<>();
        JsonArray items = json.getAsJsonObject("collection").getAsJsonArray("items");
        for (JsonElement item : items) {
            JsonObject data = item.getAsJsonObject().getAsJsonArray("data").get(0).getAsJsonObject();
            String href = item.getAsJsonObject().getAsJsonArray("links").get(0).getAsJsonObject().get("href").getAsString();
            images.add(new Image(data.get("nasa_id").getAsString(), data.get("title").getAsString(), data.get("description").getAsString(), href));
        }

        return images;
    }

    // Method to make the API call and get the JSON response
    private JsonObject makeApiCall(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error response code: " + connection.getResponseCode());
        }

        try (InputStream responseStream = connection.getInputStream()) {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(new InputStreamReader(responseStream)).getAsJsonObject();
            return jsonObject;
        }
    }
}

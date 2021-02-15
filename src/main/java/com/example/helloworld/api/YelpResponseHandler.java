package com.example.helloworld.api;

import com.example.dataobjects.YelpRestaurantResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// https://stackoverflow.com/questions/43055027/retrieve-just-one-field-from-json-string-in-java
public class YelpResponseHandler {

    public static final String RESTAURANT_NAME = "name";
    public static final String YELP_URL = "yelpURL";
    public static final String LOCATION = "location";
    public static final String DISPLAY_ADDRESS = "display_address";
    public static final String RATING = "rating";
    public static final String BUSINESSES = "businesses";

    // TODO: Return response status with result object inside it
    public static List<YelpRestaurantResponse> parseYelpResponse(String rawResponse){
        List<YelpRestaurantResponse> restaurantResponsesList = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(rawResponse);
            JSONArray businesses = (JSONArray) jsonObject.get(BUSINESSES);

            for (Object business : businesses){
                YelpRestaurantResponse.YelpRestaurantResponseBuilder builder = YelpRestaurantResponse.builder();
                String name = ((JSONObject) business).get(RESTAURANT_NAME).toString();
                builder.restaurantName(name);

                // yelpURL
                String yelpURL = ((JSONObject) business).get(YELP_URL).toString();
                builder.restaurantURL(yelpURL);

                // display_address
                JSONArray address = ((JSONArray)((JSONObject) ((JSONObject) business)
                        .get(LOCATION))
                        .get(DISPLAY_ADDRESS));
                StringBuilder sb = new StringBuilder();
                for (Object s : address){
                    sb.append(s.toString());
                }
                builder.restaurantAddress(sb.toString());

                double rating = (double) ((JSONObject) business).get(RATING);
                builder.restaurantRating(rating);

                restaurantResponsesList.add(builder.build());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return restaurantResponsesList;
    }

}

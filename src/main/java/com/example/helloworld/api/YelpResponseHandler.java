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

    // TODO: Return response status with result object inside it
    public static List<YelpRestaurantResponse> parseYelpResponse(String rawResponse){
        List<YelpRestaurantResponse> restaurantResponsesList = new ArrayList<>();
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(rawResponse);
            JSONArray businesses = (JSONArray) jsonObject.get("businesses");
            for (Object business : businesses){
                YelpRestaurantResponse.YelpRestaurantResponseBuilder builder = YelpRestaurantResponse.builder();
                String name = ((JSONObject) business).get("name").toString();
                builder.restaurantName(name);
//                System.out.println(name);

                String url = ((JSONObject) business).get("url").toString();
                builder.restaurantURL(url);
//                System.out.println(url);

                // display_address
                JSONArray address = ((JSONArray)((JSONObject) ((JSONObject) business).get("location")).get(
                        "display_address"));
                StringBuilder sb = new StringBuilder();
                for (Object s : address){
                    sb.append(s.toString());
                }
                builder.restaurantAddress(sb.toString());

                double rating = (double) ((JSONObject) business).get("rating");
                builder.restaurantRating(rating);
//                System.out.println(rating);

                restaurantResponsesList.add(builder.build());
            }
//            System.out.println("Business size = " + businesses.size());
//            System.out.println("RestaurantResponsesList size = " + restaurantResponsesList.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return restaurantResponsesList;
    }

}

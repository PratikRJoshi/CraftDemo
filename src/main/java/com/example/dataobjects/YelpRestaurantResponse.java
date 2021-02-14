package com.example.dataobjects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YelpRestaurantResponse {
    public String restaurantName;
    public String restaurantURL;
    public double restaurantRating;
    public String restaurantAddress;

}

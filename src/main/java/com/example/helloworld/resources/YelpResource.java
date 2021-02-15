package com.example.helloworld.resources;

import com.example.dao.UserDAO;
import com.example.dataobjects.YelpRestaurantResponse;
import com.example.helloworld.api.YelpResponseHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/yelp")
@Produces(MediaType.APPLICATION_JSON)
public class YelpResource {
    private static final String API_HOST = "api.yelp.com";
    private static final String BUSINESS_PATH = "/v3/businesses";
    private static final String API_KEY
            = "xjd66Zod9Uj5SxF2dfjfyrineuyqVjgv57qE4AZ3HwMvQlQCrViBGLKJd4q4tl9hEMIGSfZPrVQ7nc5qnoVFEJtSW9_Z1LHhEazReyOdRx4y6YoWISTkYZD3S0IpYHYx";
    private static final int RESULT_LIMIT = 10;

    private UserDAO userDAO;

    public YelpResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // https://stackoverflow.com/questions/62658941/error-write-eproto-34557064error100000f7ssl-routinesopenssl-internalwrong
    @Path("/addNearbyRestaurants")
    @POST
    public Response addNearbyRestaurants(@QueryParam("zipCode" ) Integer zipCode,
                                         @QueryParam("limit") Optional<Integer> limit) {
        // TODO: Validation

        String requestUrl = "https://"
                            + API_HOST
                            + BUSINESS_PATH
                            + "/search?"
                            + "limit=" + limit.orElse(RESULT_LIMIT)
                            + "&location=" + zipCode;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()))){
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    List<YelpRestaurantResponse> restaurantResponses = YelpResponseHandler.parseYelpResponse(inputLine);
                    for (YelpRestaurantResponse restaurantResponse : restaurantResponses){
                        // TODO: Can use batch insert instead of single inserts
                        userDAO.insertIntoRestaurantsTable(restaurantResponse.restaurantName,
                                                           restaurantResponse.restaurantURL,
                                                           restaurantResponse.restaurantRating,
                                                           restaurantResponse.restaurantAddress);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    // TODO: another endpoint to query and get the Yelp results from the db

}

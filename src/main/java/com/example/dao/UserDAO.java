package com.example.dao;

import com.example.helloworld.logging.LogSqlFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

@LogSqlFactory
public interface UserDAO {

    @SqlQuery("select COUNT(*) from MyGuests where id = :id")
    boolean existsBrand( @Bind("id") int id );

    @SqlUpdate("UPDATE MyGuests SET email = :email WHERE id = :id")
    void updateEmail( @Bind("id") int id,
                      @Bind("email") String email );

    @SqlUpdate("INSERT INTO YELP_NEARBY_RESTAURANTS (name,url,rating,address,zipcode) "
               + "VALUES (:name,:url,:rating,:address,:zipcode)")
    void insertIntoRestaurantsTable(@Bind("name") String name,
                                    @Bind("url") String url,
                                    @Bind("rating") double rating,
                                    @Bind("address") String address,
                                    @Bind("zipcode") int zipcode);

    @SqlQuery("SELECT * FROM YELP_NEARBY_RESTAURANTS WHERE ZIPCODE = :zipCode")
    boolean selectByZip( @Bind("zipCode") int zipcode);
}

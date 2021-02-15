package com.example.dao;

import com.example.dataobjects.TableResponseFieldObject;
import com.example.helloworld.logging.LogSqlFactory;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

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

    @SqlQuery("select * from YELP_NEARBY_RESTAURANTS where name = :name")
    boolean selectByZip( @Bind("name") String name);

    @SqlQuery("select * from YELP_NEARBY_RESTAURANTS")
    @Mapper(TableMapper.class)
    List<TableResponseFieldObject> selectAllFromTable();
}

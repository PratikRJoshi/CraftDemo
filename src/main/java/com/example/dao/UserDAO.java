package com.example.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDAO {

    @SqlQuery("select COUNT(*) from MyGuests where id = :id")
    boolean existsBrand( @Bind("id") int id );

    @SqlUpdate("UPDATE MyGuests SET email = :email WHERE id = :id")
    void updateEmail( @Bind("id") int id, @Bind("email") String email );
}

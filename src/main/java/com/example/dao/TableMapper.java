package com.example.dao;

import com.example.dataobjects.TableResponseFieldObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class TableMapper implements ResultSetMapper<TableResponseFieldObject> {
    @Override
    public TableResponseFieldObject map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new TableResponseFieldObject(String.valueOf(r.getString("name")),
                                            String.valueOf(r.getString("address")),
                                            String.valueOf(r.getString("url")),
                                            Double.parseDouble(r.getString("rating")),
                                            Integer.parseInt(r.getString("zipcode")));
    }
}

package org.bonn.se.model.dao;

import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class AbstractDAO {

    protected Statement getStatement(){
        Statement statement;
        try {
            statement = JDBCConnection.getInstance().getStatement();

        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }

    protected static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement;
        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);

        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }
}

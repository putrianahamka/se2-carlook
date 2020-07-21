package org.bonn.se.services.db;

import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Password;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    private Connection conn;

    private static JDBCConnection instance;

    public static JDBCConnection getInstance() throws DatabaseException {
        return instance == null ? instance = new JDBCConnection() : instance;
    }

    private JDBCConnection() throws DatabaseException {
        this.initConnection();
    }

    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);

        }
        this.openConnection();
    }

    public void openConnection() throws DatabaseException {

        try {

            Properties props = new Properties();
            props.setProperty("user", "hbajwa2s");
            props.setProperty("password", Password.DB);


            String url = "jdbc:postgresql://dumbo.inf.h-brs.de/hbajwa2s";
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");

        }
    }


    public Statement getStatement() throws DatabaseException {
        try {
            if(this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.createStatement();
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }

    public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
        try {
            if(this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }

    public void closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }
    }
}

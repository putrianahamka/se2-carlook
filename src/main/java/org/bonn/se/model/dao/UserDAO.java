package org.bonn.se.model.dao;

import org.bonn.se.model.objects.entities.User;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends AbstractDAO {
    private static UserDAO instance;
    public static UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }
    public User getUser(String email) throws DatabaseException, SQLException{
        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try{
            set = statement.executeQuery("SELECT * FROM carlook.tab_user WHERE upper(carlook.tab_user.email) = '" + email.toUpperCase() + "'");
            User user = null;
            while (set.next()){
                user = new User();
                user.setEmail(set.getString(1));
                user.setPasswort(set.getString(2));
                user.setVorname(set.getString(3));
                user.setNachname(set.getString(4));
                user.setType(set.getString(5));
            }
            return user;
        } catch (SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally{
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }

    public boolean getUserbyEmail(String email) throws DatabaseException, SQLException {

        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try {
            set = statement.executeQuery("SELECT * "
                    + "FROM carlook.tab_user "
                    + "WHERE upper(carlook.tab_user.email) = '" + email.toUpperCase() + "'");

            while (set.next()) {
                return set.getString(1).equalsIgnoreCase(email);
            }
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return false;
    }

    public void registerUser(User user) throws  DatabaseException{
        String sql;
        if(user.getType().equals("v")){
            sql = "INSERT INTO carlook.tab_user VALUES(?,?,?,?,?); INSERT INTO carlook.tab_vertriebler (email,kontakt_nr) VALUES(?,?);";
        }else{
            sql = "INSERT INTO carlook.tab_user VALUES(?,?,?,?,?); INSERT INTO carlook.tab_kunde (email) VALUES(?);";
        }
        PreparedStatement statement = AbstractDAO.getPreparedStatement(sql);

        try {
            assert statement != null;
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswort());
            statement.setString(3, user.getVorname());
            statement.setString(4, user.getNachname());
            statement.setString(5, user.getType());
            if(user.getType().equals("v")) {

                //statement.setString(6, user.getCname());
                //statement.setString(7, user.getHauptsitz());
               //statement.setString(8, user.getBundesland());
                statement.setString(6, user.getEmail());
                statement.setString(7, user.getKontaktNr());
            } else {
                statement.setString(6, user.getEmail());
            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
    }

    public String getUserType(String email) throws DatabaseException, SQLException {
        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try {
            set = statement.executeQuery("SELECT * "
                    + "FROM carlook.tab_user "
                    + "WHERE lacasa.tab_user.email = '" + email + "'");

            if( set.next()){
                return set.getString("typ");
            }
        } catch (SQLException throwables) {

            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("UserTyp nicht vorhanden");
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }


}

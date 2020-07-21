package org.bonn.se.control;

import org.bonn.se.model.dao.UserDAO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSearchControl {
    private static UserSearchControl instance;

    public static UserSearchControl getInstance(){
        if (instance == null){
            instance = new UserSearchControl();
        }
        return instance;
    }
    public boolean existUser(String email){
        try{
            return UserDAO.getInstance().getUserbyEmail(email);
        } catch (DatabaseException | SQLException e){
            Logger.getLogger(UserSearchControl.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

}

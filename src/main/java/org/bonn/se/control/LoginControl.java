package org.bonn.se.control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.bonn.se.control.exception.NoSuchUserOrPassword;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.dao.ProfilDAO;
import org.bonn.se.model.objects.entities.User;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {
    private LoginControl(){}

    private static LoginControl instance;
    public static LoginControl getInstance() {
        if (instance == null){
            instance = new LoginControl();
        }
        return instance;
    }
    public void checkAuthentication ( String email , String password) throws NoSuchUserOrPassword, DatabaseException, SQLException {
        ResultSet set;
        Statement statement = null;

        try {
            statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM carlook.tab_user "
                    + "WHERE upper(carlook.tab_user.email) = '" + email.toUpperCase() + "'"
                    + " AND carlook.tab_user.passwort = '" + password + "'");
        } catch(SQLException | DatabaseException throwables){
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE,null,throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        User user;
        try{
            if(set.next()){
                user = new User();
                user.setVorname(set.getString(3));
                user.setNachname(set.getString(4));
                user.setEmail(set.getString(1));
                user.setPasswort(set.getString(2));

                if(set.getString(5).equals("v")){
                    Vertriebler vertriebler = new Vertriebler();
                    MyUI.getCurrent().getSession().setAttribute(Roles.VERTRIEBLER,vertriebler);

                    vertriebler.setEmail(user.getEmail());
                    vertriebler.setPasswort(user.getPasswort());
                    vertriebler.setVorname(user.getVorname());
                    vertriebler.setNachname(user.getNachname());

                    vertriebler = ProfilDAO.getInstance().getVertrieblerProfil(vertriebler);

                    UI.getCurrent().getSession().setAttribute(Roles.VERTRIEBLER,vertriebler);

                }else if(set.getString(5).equals("k")){
                    //Code for Kunde
                }
            }else {
                throw new NoSuchUserOrPassword();
            }
        }catch(SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally{
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
    }

    public static void logoutUser(){
        VaadinSession vaadinSession = UI.getCurrent().getSession();
        vaadinSession.setAttribute(Roles.VERTRIEBLER,null);
        vaadinSession.setAttribute(Roles.KUNDE,null);
        UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
    }
}

package org.bonn.se.model.dao;

import org.bonn.se.model.objects.entities.Kunde;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfilDAO extends AbstractDAO{
    private static ProfilDAO instance;

    public static ProfilDAO getInstance() {
        return instance == null ? instance = new ProfilDAO() : instance;
    }

    public Vertriebler getVertrieblerProfil(Vertriebler vertriebler) throws DatabaseException, SQLException {
        ResultSet set;
        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT personalnummer, kontakt_nr FROM carlook.tab_vertriebler WHERE email='" + vertriebler.getEmail()+"'");
        } catch(SQLException | DatabaseException throwables){
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        try{
            while(set.next()){
                vertriebler.setPersonalNummer(set.getInt(1));
                vertriebler.setKontaktNr(set.getString(2));

                return vertriebler;
            }
        } catch(SQLException  throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally{
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }

    public Kunde getKundenProfil(Kunde kunde) throws DatabaseException, SQLException {
        ResultSet set;
        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT kundennummer FROM carlook.tab_kunde WHERE email='" + kunde.getEmail()+"'");
        } catch(SQLException | DatabaseException throwables){
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        try{
            while(set.next()){
                kunde.setKundenNr(set.getInt(1));
                //kunden.setKontaktNr(set.getString(2));

                return kunde;
            }
        } catch(SQLException  throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally{
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }
}

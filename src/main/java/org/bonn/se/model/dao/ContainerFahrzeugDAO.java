package org.bonn.se.model.dao;

import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerFahrzeugDAO extends AbstractDAO {
    private static ContainerFahrzeugDAO instance;

    public static ContainerFahrzeugDAO getInstance(){
        if (instance == null){
            instance = new ContainerFahrzeugDAO();
        }
        return instance;
    }

    public void setFahrzeug(Vertriebler vertriebler) throws DatabaseException{
        String sql = "INSERT INTO carlook.tab_fahrzeug (fahrzeugzustand,short_description,marke,modell,fahrzeugtyp,erstzulassung," +
                "kaufpreis,kilometer,leistung,kraftstoffart,getriebe,tuev,aussenfarbe,anzahl_tueren,anzahl_sitzplaetze,klimaanlage,fahrzeugart,anzahl_fahrzeughalter," +
                "schadenstoffklasse,umweltplakette,description,garantie,zeitstample,personalnummer)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        PreparedStatement statement = getPreparedStatement(sql);
        try{
            assert statement != null;
            statement.setString(1,vertriebler.getFahrzeugDTO().getFahrzeugZustand());
            statement.setString(2,vertriebler.getFahrzeugDTO().getShortDescription());
            statement.setString(3,vertriebler.getFahrzeugDTO().getMarke());
            statement.setString(4,vertriebler.getFahrzeugDTO().getModell());
            statement.setString(5,vertriebler.getFahrzeugDTO().getFahrzeugTyp());
            statement.setDate(6, Date.valueOf(String.valueOf(vertriebler.getFahrzeugDTO().getErstzulassung())));
            statement.setInt(7,vertriebler.getFahrzeugDTO().getPreis());
            statement.setInt(8,vertriebler.getFahrzeugDTO().getKilometer());
            statement.setInt(9,vertriebler.getFahrzeugDTO().getLeistung());
            statement.setString(10,vertriebler.getFahrzeugDTO().getKraftstoffart());
            statement.setString(11,vertriebler.getFahrzeugDTO().getGetriebe());
            statement.setDate(12, Date.valueOf(String.valueOf(vertriebler.getFahrzeugDTO().getTuev())));
            statement.setString(13,vertriebler.getFahrzeugDTO().getAussenfarbe());
            statement.setString(14,vertriebler.getFahrzeugDTO().getAnzahlTueren());
            statement.setInt(15,vertriebler.getFahrzeugDTO().getAnzahlSitzplaetze());
            statement.setString(16,vertriebler.getFahrzeugDTO().getKlimaanlage());
            statement.setString(17,vertriebler.getFahrzeugDTO().getFahrzeugart());
            statement.setInt(18,vertriebler.getFahrzeugDTO().getAnzahlFahrzeughalter());
            statement.setString(19,vertriebler.getFahrzeugDTO().getSchadenstoffklasse());
            statement.setString(20,vertriebler.getFahrzeugDTO().getUmweltplakette());
            statement.setString(21,vertriebler.getFahrzeugDTO().getDescription());
            statement.setString(22,vertriebler.getFahrzeugDTO().getGarantie());
            statement.setDate(23,Date.valueOf(LocalDate.now()));
            statement.setInt(24,vertriebler.getFahrzeugDTO().getPersonalnummer());


            statement.executeUpdate();






        }catch(SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }finally {
            JDBCConnection.getInstance().closeConnection();
        }
    }
}

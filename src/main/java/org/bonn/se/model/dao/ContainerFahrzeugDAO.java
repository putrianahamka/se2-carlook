package org.bonn.se.model.dao;

import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public void setAutoReservierung (int kundenNummer, int fahrzeugid )throws DatabaseException{
        /*
        statement.setDate(23,Date.valueOf(LocalDate.now()));

         */
        String sql = "INSERT INTO carlook.tab_reservierungsliste (kundennummer, fahrzeug_id, zeitstample)" +
                "VALUES (?,?,?)";
        PreparedStatement statement = getPreparedStatement(sql);
        try{
            assert statement != null;
            statement.setInt(1, kundenNummer);
            statement.setInt(2, fahrzeugid);
            statement.setDate(3,Date.valueOf(LocalDate.now()));
            statement.executeUpdate();

        } catch(SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }finally {
            JDBCConnection.getInstance().closeConnection();
        }
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

    public List<FahrzeugDTO> getFahrzeugByPersonalnummer(int personalNummer){
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * FROM carlook.tab_fahrzeug WHERE carlook.tab_fahrzeug.personalnummer =" +
                    " \'" + personalNummer + "\'");
        }catch(SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rs == null){
            System.out.println("Liste ist leer");
            return null;
        }
        FahrzeugDTO fahrzeugDTO =null;
        List<FahrzeugDTO> liste = new ArrayList<>();

        try{
            while(rs.next()){
                //fahrzeugDTO.setId(rs.getInt(1));
                fahrzeugDTO = new FahrzeugDTO();

                fahrzeugDTO.setId(rs.getInt(1));
                fahrzeugDTO.setFahrzeugZustand(rs.getString(2));
                fahrzeugDTO.setShortDescription(rs.getString(3));
                fahrzeugDTO.setMarke(rs.getString(4));
                fahrzeugDTO.setModell(rs.getString(5));
                fahrzeugDTO.setFahrzeugTyp(rs.getString(6));
                fahrzeugDTO.setErstzulassung(LocalDate.parse(rs.getString(7)));
                fahrzeugDTO.setPreis(rs.getInt(8));
                fahrzeugDTO.setKilometer(rs.getInt(9));
                fahrzeugDTO.setLeistung(rs.getInt(10));
                fahrzeugDTO.setKraftstoffart(rs.getString(11));
                fahrzeugDTO.setGetriebe(rs.getString(12));
                fahrzeugDTO.setTuev(LocalDate.parse(rs.getString(13)));
                fahrzeugDTO.setAussenfarbe(rs.getString(14));
                fahrzeugDTO.setAnzahlTueren(rs.getString(15));
                fahrzeugDTO.setAnzahlSitzplaetze(rs.getInt(16));
                fahrzeugDTO.setKlimaanlage(rs.getString(17));
                fahrzeugDTO.setFahrzeugart(rs.getString(18));
                fahrzeugDTO.setAnzahlFahrzeughalter(rs.getInt(19));
                fahrzeugDTO.setSchadenstoffklasse(rs.getString(20));
                fahrzeugDTO.setUmweltplakette(rs.getString(21));
                fahrzeugDTO.setDescription(rs.getString(22));
                fahrzeugDTO.setGarantie(rs.getString(23));
                fahrzeugDTO.setZeitstempel(Date.valueOf(rs.getString(24)));
                fahrzeugDTO.setPersonalnummer(rs.getInt(25));

                liste.add(fahrzeugDTO);


            }
        }catch(SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return liste;

    }

    public List<FahrzeugDTO> getFahrzeug(){
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * FROM carlook.tab_fahrzeug ORDER BY zeitstample DESC LIMIT 5 ");
        }catch(SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rs == null){
            System.out.println("Liste ist leer");
            return null;
        }
        FahrzeugDTO fahrzeugDTO =null;
        List<FahrzeugDTO> liste = new ArrayList<>();

        try{
            while(rs.next()){
                //fahrzeugDTO.setId(rs.getInt(1));
                fahrzeugDTO = new FahrzeugDTO();

                fahrzeugDTO.setId(rs.getInt(1));
                fahrzeugDTO.setFahrzeugZustand(rs.getString(2));
                fahrzeugDTO.setShortDescription(rs.getString(3));
                fahrzeugDTO.setMarke(rs.getString(4));
                fahrzeugDTO.setModell(rs.getString(5));
                fahrzeugDTO.setFahrzeugTyp(rs.getString(6));
                fahrzeugDTO.setErstzulassung(LocalDate.parse(rs.getString(7)));
                fahrzeugDTO.setPreis(rs.getInt(8));
                fahrzeugDTO.setKilometer(rs.getInt(9));
                fahrzeugDTO.setLeistung(rs.getInt(10));
                fahrzeugDTO.setKraftstoffart(rs.getString(11));
                fahrzeugDTO.setGetriebe(rs.getString(12));
                fahrzeugDTO.setTuev(LocalDate.parse(rs.getString(13)));
                fahrzeugDTO.setAussenfarbe(rs.getString(14));
                fahrzeugDTO.setAnzahlTueren(rs.getString(15));
                fahrzeugDTO.setAnzahlSitzplaetze(rs.getInt(16));
                fahrzeugDTO.setKlimaanlage(rs.getString(17));
                fahrzeugDTO.setFahrzeugart(rs.getString(18));
                fahrzeugDTO.setAnzahlFahrzeughalter(rs.getInt(19));
                fahrzeugDTO.setSchadenstoffklasse(rs.getString(20));
                fahrzeugDTO.setUmweltplakette(rs.getString(21));
                fahrzeugDTO.setDescription(rs.getString(22));
                fahrzeugDTO.setGarantie(rs.getString(23));
                fahrzeugDTO.setZeitstempel(Date.valueOf(rs.getString(24)));
                fahrzeugDTO.setPersonalnummer(rs.getInt(25));

                liste.add(fahrzeugDTO);


            }
        }catch(SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return liste;
    }

    public List<FahrzeugDTO> loadFahrzeuge(String marke, String modell, String typ, LocalDate erstAb, LocalDate erstBis, String preisAb, String preisBis
            ,String kilometerAb, String kilometerBis,String kraftstoffart,String getriebe,String aussenfarbe,String umweltplakette,
                                           String schadenstoffklsse,String klima){
        List<FahrzeugDTO> liste = new ArrayList<>();
        if (marke == null){
            marke = "%";
        }
        if (modell == null){
            modell = "%";
        }else if (modell == ""){
            modell = "%";
        }
        if (typ == null){
            typ = "%";
        }
        if (kraftstoffart == null){
            kraftstoffart = "%";
        }
        if (getriebe == null){
            getriebe = "%";
        }
        if (aussenfarbe == null){
            aussenfarbe = "%";
        }
        if (umweltplakette == null){
            umweltplakette = "%";
        }
        if (schadenstoffklsse == null){
            schadenstoffklsse = "%";
        }
        if (klima == null){
            klima = "%";
        }
        StringBuilder erstzuAb = new StringBuilder(" ");
        StringBuilder erstzuBis = new StringBuilder(" ");

        Date dateAb = erstAb == null ? null: Date.valueOf(erstAb);
        Date dateBis = erstBis == null ? null: Date.valueOf(erstBis);

        erstzuAb = new StringBuilder(dateAb == null ? "1900-01-01" : " " +dateAb);
        erstzuBis = new StringBuilder(dateBis == null ? "9999-01-01" : " " +dateBis);

        if(preisAb == null){
            preisAb = "0";
        }
        if(preisBis == null){
            preisBis = "1000000000";
        }
        if(kilometerAb == null){
            kilometerAb = "0";
        }
        if(kilometerBis == null){
            kilometerBis = "1000000000";
        }

        ResultSet set = null;
        Statement statement = this.getStatement();
        try{
            set = statement.executeQuery("SELECT * FROM carlook.tab_fahrzeug WHERE carlook.tab_fahrzeug.marke LIKE" +
                    "\'" + marke + "\' AND carlook.tab_fahrzeug.kaufpreis >=\'" + preisAb + "\'" +
                    "AND carlook.tab_fahrzeug.kaufpreis <=\'" + preisBis + "\'" +
                    "AND carlook.tab_fahrzeug.modell LIKE \'" + modell + "\'" +
                    "AND carlook.tab_fahrzeug.fahrzeugtyp LIKE \'" + typ + "\'" +
                    "AND carlook.tab_fahrzeug.kraftstoffart LIKE \'" + kraftstoffart + "\'" +
                    "AND carlook.tab_fahrzeug.getriebe LIKE \'" + getriebe + "\'" +
                    "AND carlook.tab_fahrzeug.aussenfarbe LIKE \'" + aussenfarbe + "\'" +
                    "AND carlook.tab_fahrzeug.umweltplakette LIKE \'" + umweltplakette + "\'" +
                    "AND carlook.tab_fahrzeug.schadenstoffklasse LIKE \'" + schadenstoffklsse + "\'" +
                    "AND carlook.tab_fahrzeug.klimaanlage LIKE \'" + klima + "\'" +
                    "AND carlook.tab_fahrzeug.erstzulassung >= \'" + erstzuAb + "\'" +
                    "AND carlook.tab_fahrzeug.erstzulassung <= \'" + erstzuBis + "\'" +
                    "AND carlook.tab_fahrzeug.kilometer >= \'" + kilometerAb + "\'" +
                    "AND carlook.tab_fahrzeug.kilometer <= \'" + kilometerBis + "\'");


        }catch(SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (set == null){
            System.out.println("Liste ist leer");
            return null;
        }
        FahrzeugDTO fahrzeugDTO =null;
        try {
            while (set.next()) {
                //fahrzeugDTO.setId(rs.getInt(1));
                fahrzeugDTO = new FahrzeugDTO();
                fahrzeugDTO.setId(set.getInt(1));
                fahrzeugDTO.setFahrzeugZustand(set.getString(2));
                fahrzeugDTO.setShortDescription(set.getString(3));
                fahrzeugDTO.setMarke(set.getString(4));
                fahrzeugDTO.setModell(set.getString(5));
                fahrzeugDTO.setFahrzeugTyp(set.getString(6));
                fahrzeugDTO.setErstzulassung(LocalDate.parse(set.getString(7)));
                fahrzeugDTO.setPreis(set.getInt(8));
                fahrzeugDTO.setKilometer(set.getInt(9));
                fahrzeugDTO.setLeistung(set.getInt(10));
                fahrzeugDTO.setKraftstoffart(set.getString(11));
                fahrzeugDTO.setGetriebe(set.getString(12));
                fahrzeugDTO.setTuev(LocalDate.parse(set.getString(13)));
                fahrzeugDTO.setAussenfarbe(set.getString(14));
                fahrzeugDTO.setAnzahlTueren(set.getString(15));
                fahrzeugDTO.setAnzahlSitzplaetze(set.getInt(16));
                fahrzeugDTO.setKlimaanlage(set.getString(17));
                fahrzeugDTO.setFahrzeugart(set.getString(18));
                fahrzeugDTO.setAnzahlFahrzeughalter(set.getInt(19));
                fahrzeugDTO.setSchadenstoffklasse(set.getString(20));
                fahrzeugDTO.setUmweltplakette(set.getString(21));
                fahrzeugDTO.setDescription(set.getString(22));
                fahrzeugDTO.setGarantie(set.getString(23));
                fahrzeugDTO.setZeitstempel(Date.valueOf(set.getString(24)));
                fahrzeugDTO.setPersonalnummer(set.getInt(25));

                liste.add(fahrzeugDTO);
            }
        }catch(SQLException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return liste;


    }
}

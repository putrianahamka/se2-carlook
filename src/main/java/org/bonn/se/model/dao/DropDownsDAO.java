package org.bonn.se.model.dao;

import org.bonn.se.gui.views.AutoAnlegenView;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DropDownsDAO extends AbstractDAO{

    private static DropDownsDAO instance;

    public static DropDownsDAO getInstance(){
        if(instance==null){
            instance = new DropDownsDAO();
        }
        return instance;
    }

    public List<String> getMarke()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> markenList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT DISTINCT marke FROM carlook.tab_marke_modell ORDER BY marke");
            while (true){
                assert set != null;
                if(!set.next())break;
                markenList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return markenList;
    }

    public List<String> getModell()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> modellList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT modell FROM carlook.tab_marke_modell ORDER BY modell");

            while (true){
                assert set != null;
                if(!set.next())break;
                modellList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return modellList;
    }

    public List<String> getFahrzeugZustand()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> fahrzeugZustandList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT fahrzeugzustand FROM carlook.tab_fahrzeugzustand");
            while (true){
                assert set != null;
                if(!set.next())break;
                fahrzeugZustandList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return fahrzeugZustandList;
    }

    public List<String> getFahrzeugTyp()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> fahrzeugTypList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT fahrzeugtyp FROM carlook.tab_fahrzeugtyp");
            while (true){
                assert set != null;
                if(!set.next())break;
                fahrzeugTypList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return fahrzeugTypList;
    }

    public List<String> getKraftstoffArt()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> kraftStoffArtList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT kraftstoffart FROM carlook.tab_kraftstoffart");
            while (true){
                assert set != null;
                if(!set.next())break;
                kraftStoffArtList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return kraftStoffArtList;
    }

    public List<String> getGetriebe()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> getriebeList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT getriebe FROM carlook.tab_getriebe");
            while (true){
                assert set != null;
                if(!set.next())break;
                getriebeList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return getriebeList;
    }

    public List<String> getAnzahlTueren()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> anzahlTuerenList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT anzahl_tueren FROM carlook.tab_anzahl_tueren");
            while (true){
                assert set != null;
                if(!set.next())break;
                anzahlTuerenList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return anzahlTuerenList;
    }

    public List<String> getGarantie()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> garantieList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT garantie FROM carlook.tab_garantie");
            while (true){
                assert set != null;
                if(!set.next())break;
                garantieList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return garantieList;
    }

    public List<String> getUmweltplakette()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> umweltplaketteList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT umweltplakette FROM carlook.tab_umweltplakette");
            while (true){
                assert set != null;
                if(!set.next())break;
                umweltplaketteList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return umweltplaketteList;
    }

    public List<String> getSchadenstoffKlasse()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> schadenstoffKlasseList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT schadenstoffklasse FROM carlook.tab_schadenstoffklasse");
            while (true){
                assert set != null;
                if(!set.next())break;
                schadenstoffKlasseList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return schadenstoffKlasseList;
    }

    public List<String> getFahrzeugArt()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> fahrzeugArtList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT fahrzeugart FROM carlook.tab_fahrzeugart");
            while (true){
                assert set != null;
                if(!set.next())break;
                fahrzeugArtList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return fahrzeugArtList;
    }

    public List<String> getKlimaanlage()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> klimaanlageList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT klimaanlage FROM carlook.tab_klimaanlage");
            while (true){
                assert set != null;
                if(!set.next())break;
                klimaanlageList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return klimaanlageList;
    }


    public List<String> getaussenfarbe()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<String> aussenfarbeList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT aussenfarbe FROM carlook.tab_aussenfarbe");
            while (true){
                assert set != null;
                if(!set.next())break;
                aussenfarbeList.add(set.getString(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return aussenfarbeList;
    }

    public List<Integer> getAnzahlFahrzeughalter()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<Integer> anzahlFahrzeughalterList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT anzahl_fahrzeughalter FROM carlook.tab_anzahl_fahrzeughalter");
            while (true){
                assert set != null;
                if(!set.next())break;
                anzahlFahrzeughalterList.add(set.getInt(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return anzahlFahrzeughalterList;
    }

    public List<Integer> getAnzahlSitzplaetze()throws DatabaseException, SQLException {
        ResultSet set = null;
        List<Integer> anzahlSitzplaetzeList = new ArrayList<>();

        try{
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT anzahl_sitzplaetze FROM carlook.tab_anzahl_sitzplaetze");
            while (true){
                assert set != null;
                if(!set.next())break;
                anzahlSitzplaetzeList.add(set.getInt(1));
            }
        }catch(SQLException | DatabaseException throwables){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try{
                set.close();
            }catch(SQLException throwables){
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try{
                JDBCConnection.getInstance().closeConnection();
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        return anzahlSitzplaetzeList;
    }


}

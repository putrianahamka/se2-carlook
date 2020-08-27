package org.bonn.se.control;

import org.bonn.se.model.dao.DropDownsDAO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DropDownsControl {
    private static DropDownsControl instance;
    public static DropDownsControl getInstance(){
        if(instance == null){
            instance = new DropDownsControl();
        }
        return instance;
    }
    public List<String> getMarke()  {
        try {
            return DropDownsDAO.getInstance().getMarke();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getModell(String str)  {
        try {
            return DropDownsDAO.getInstance().getModell(str);
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getFahrzeugZustand()  {
        try {
            return DropDownsDAO.getInstance().getFahrzeugZustand();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }
    public List<String> getFahrzeugTyp()  {
        try {
            return DropDownsDAO.getInstance().getFahrzeugTyp();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getKraftstoffArt()  {
        try {
            return DropDownsDAO.getInstance().getKraftstoffArt();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getGetriebe()  {
        try {
            return DropDownsDAO.getInstance().getGetriebe();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getAussenfarbe()  {
        try {
            return DropDownsDAO.getInstance().getaussenfarbe();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getAnzahlTueren()  {
        try {
            return DropDownsDAO.getInstance().getAnzahlTueren();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getGarantie()  {
        try {
            return DropDownsDAO.getInstance().getGarantie();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getNumbers()  {
        try {
            return DropDownsDAO.getInstance().getNumbers();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }


    public List<String> getUmweltplakette()  {
        try {
            return DropDownsDAO.getInstance().getUmweltplakette();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getSchadenstoffKlasse()  {
        try {
            return DropDownsDAO.getInstance().getSchadenstoffKlasse();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getFahrzeugArt()  {
        try {
            return DropDownsDAO.getInstance().getFahrzeugArt();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<String> getKlimaanlage()  {
        try {
            return DropDownsDAO.getInstance().getKlimaanlage();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<Integer> getAnzahlFahrzeughalter()  {
        try {
            return DropDownsDAO.getInstance().getAnzahlFahrzeughalter();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }

    public List<Integer> getAnzahlSitzplaetze()  {
        try {
            return DropDownsDAO.getInstance().getAnzahlSitzplaetze();
        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(DropDownsControl.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return null;
    }
}

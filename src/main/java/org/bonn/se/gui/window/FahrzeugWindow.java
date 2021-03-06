package org.bonn.se.gui.window;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.ConfirmationWindow;
import org.bonn.se.model.dao.ContainerFahrzeugDAO;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.model.objects.entities.Kunde;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FahrzeugWindow extends Window {

    private int kundenNummer;
    private int fahrzeugId;

    public FahrzeugWindow(FahrzeugDTO fahrzeugDTO)throws  DatabaseException{
        setUp(fahrzeugDTO);
    }

    public void setUp(FahrzeugDTO fahrzeugDTO) throws  DatabaseException{

        center();
        this.setWidth("80%");
        this.setHeight("90%");
        this.setModal(true);
        this.setResizable(false);



        Panel panel = new Panel();
        panel.setWidthFull();

        Button reservierung = new Button("Reservieren");
        Button close = new Button("Zurück");

        if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            this.kundenNummer = ((Kunde) UI.getCurrent().getSession().getAttribute(Roles.KUNDE)).getKundenNr();
            this.fahrzeugId = fahrzeugDTO.getId();
        }

        reservierung.addClickListener((Button.ClickListener) event ->
        //int kundeNummer = ((Kunde) UI.getCurrent().getSession().getAttribute(Roles.KUNDE)).getKundenNr();
        //int fahrzeugId = fahrzeugDTO.getId();
            {
                try {
                    ContainerFahrzeugDAO.getInstance().setAutoReservierung(kundenNummer, fahrzeugId);
                    ConfirmationWindow confirmationWindow = new ConfirmationWindow("Auto wurde erfolgreich reserviert");
                    UI.getCurrent().addWindow(confirmationWindow);
                    reservierung.setEnabled(false);
                    reservierung.setCaption("Reserviert");
                } catch (DatabaseException e) {
                    Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
                    ConfirmationWindow confirmationWindow = new ConfirmationWindow("Sie haben das Auto bereits reserviert");
                    UI.getCurrent().addWindow(confirmationWindow);

                }
            }


        );
        close.addClickListener((Button.ClickListener) event ->
                this.close() );

        GridLayout gridLayout = new GridLayout(5,19);
        gridLayout.setWidthFull();
        gridLayout.setHeightUndefined();
        gridLayout.setMargin(true);




        Label shortDescription = new Label("<b>" + fahrzeugDTO.getShortDescription()+ "</b>",ContentMode.HTML);
        Label fahrzeugzustand = new Label ("<b>Fahrzeugzustand</b>",ContentMode.HTML);
        Label fahrzeugart = new Label ("<b>Fahrzeugart</b>",ContentMode.HTML);
        Label marke = new Label("<b>Marke</b>", ContentMode.HTML);
        Label modell = new Label("<b>Modell</b>",ContentMode.HTML);
        Label fahrzeugtyp = new Label("<b>Fahrzeugtyp</b>",ContentMode.HTML);
        Label preis = new Label("<b>Kaufpreis</b> (€)",ContentMode.HTML);
        Label erstzulassung = new Label("<b>Erstzulassung</b>",ContentMode.HTML);
        Label leistung = new Label("<b>Lesitung</b> (PS)",ContentMode.HTML);
        Label kilometer = new Label("<b>Kilometer</b>",ContentMode.HTML);
        Label aussenfarbe = new Label("<b>Außenfarbe</b>",ContentMode.HTML);
        Label anzahlSitze = new Label("<b>Anzahl Sitzplätze</b>",ContentMode.HTML);
        Label anzahltueren = new Label("<b>Anzahl Türen</b>",ContentMode.HTML);
        Label tuevBis = new Label("<b>Tüv</b>",ContentMode.HTML);
        Label kraftstoff = new Label("<b>Kraftstoffart</b>",ContentMode.HTML);
        Label getriebe =  new Label("<b>Getriebe</b>",ContentMode.HTML);
        Label klimaanlage = new Label("<b>Klimaanlage</b>",ContentMode.HTML);
        Label anzahlFahrzeughalter = new Label("<b>Anzahl Fahrzeughalter</b>",ContentMode.HTML);
        Label garantie = new Label("<b>Garantie</b>",ContentMode.HTML);
        Label umweltplakette = new Label("<b>Umweltplakette</b>",ContentMode.HTML);
        Label schadenstoffklasse = new Label("<b>Schadenstoffklasse</b>",ContentMode.HTML);
        Label published = new Label("<b>Angelegt am</b>",ContentMode.HTML);
        Label description = new Label("<b>Description:</b>",ContentMode.HTML);


        RichTextArea descriptionData = new RichTextArea();
        descriptionData.setSizeFull();
        descriptionData.setValue(fahrzeugDTO.getDescription());
        descriptionData.setReadOnly(true);

        Label fahrzeugzustandData = new Label(fahrzeugDTO.getFahrzeugZustand());
        Label fahrzeugartData = new Label(fahrzeugDTO.getFahrzeugart());
        Label markeData = new Label(fahrzeugDTO.getMarke());
        Label modellData = new Label(fahrzeugDTO.getModell());
        Label fahrzeugtypData = new Label(fahrzeugDTO.getFahrzeugTyp());
        Label preisData = new Label(String.valueOf(fahrzeugDTO.getPreis()));
        Label erstzulassungData = new Label(String.valueOf(fahrzeugDTO.getErstzulassung()));
        Label leistungData = new Label(String.valueOf(fahrzeugDTO.getLeistung()));
        Label kilometerData = new Label(String.valueOf(fahrzeugDTO.getKilometer()));
        Label aussenfarbeData = new Label(fahrzeugDTO.getAussenfarbe());
        Label anzahlsitzeData = new Label(String.valueOf(fahrzeugDTO.getAnzahlSitzplaetze()));
        Label anzahltuerenData = new Label(fahrzeugDTO.getAnzahlTueren()) ;
        Label tuevData = new Label(String.valueOf(fahrzeugDTO.getTuev()));
        Label kraftstoffData = new Label(fahrzeugDTO.getKraftstoffart());
        Label getriebeData = new Label(fahrzeugDTO.getGetriebe());
        Label klimaanlageDate = new Label(fahrzeugDTO.getKlimaanlage());
        Label anzahlFahrzeughalterData = new Label(String.valueOf(fahrzeugDTO.getAnzahlFahrzeughalter()));
        Label garantieData = new Label(fahrzeugDTO.getGarantie());
        Label umweltplaketteData = new Label(fahrzeugDTO.getUmweltplakette());
        Label schadenstoffklasseData = new Label(fahrzeugDTO.getSchadenstoffklasse());
        Label publishedData = new Label(String.valueOf(fahrzeugDTO.getZeitstempel()));

        if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            try {
                if (ContainerFahrzeugDAO.getInstance().isReserviert(kundenNummer, fahrzeugId)) {
                    gridLayout.addComponent(reservierung, 4, 18);
                    reservierung.setEnabled(false);
                    reservierung.setCaption("Reserviert");
                } else {
                    gridLayout.addComponent(reservierung, 4, 18);
                }
            } catch (DatabaseException | SQLException e) {
                e.printStackTrace();
            }
        }




        gridLayout.addComponent(shortDescription,0,0,4,0);
        gridLayout.setComponentAlignment(shortDescription, Alignment.MIDDLE_CENTER);

        gridLayout.addComponent(fahrzeugzustand,0,2);
        gridLayout.addComponent(fahrzeugzustandData,1,2);

        gridLayout.addComponent(fahrzeugart,3,2);
        gridLayout.addComponent(fahrzeugartData,4,2);

        gridLayout.addComponent(marke,0,3);
        gridLayout.addComponent(markeData,1,3);

        gridLayout.addComponent(modell,3,3);
        gridLayout.addComponent(modellData,4,3);

        gridLayout.addComponent(fahrzeugtyp,0,4);
        gridLayout.addComponent(fahrzeugtypData,1,4);

        gridLayout.addComponent(preis,3,4);
        gridLayout.addComponent(preisData,4,4);

        gridLayout.addComponent(erstzulassung,0,5);
        gridLayout.addComponent(erstzulassungData,1,5);

        gridLayout.addComponent(leistung,3,5);
        gridLayout.addComponent(leistungData,4,5);

        gridLayout.addComponent(kilometer,0,6);
        gridLayout.addComponent(kilometerData,1,6);

        gridLayout.addComponent(aussenfarbe,3,6);
        gridLayout.addComponent(aussenfarbeData,4,6);

        gridLayout.addComponent(anzahlSitze,0,7);
        gridLayout.addComponent(anzahlsitzeData,1,7);

        gridLayout.addComponent(anzahltueren,3,7);
        gridLayout.addComponent(anzahltuerenData,4,7);

        gridLayout.addComponent(tuevBis,0,8);
        gridLayout.addComponent(tuevData,1,8);

        gridLayout.addComponent(kraftstoff,3,8);
        gridLayout.addComponent(kraftstoffData,4,8);

        gridLayout.addComponent(getriebe,0,9);
        gridLayout.addComponent(getriebeData,1,9);

        gridLayout.addComponent(klimaanlage,3,9);
        gridLayout.addComponent(klimaanlageDate,4,9);

        gridLayout.addComponent(anzahlFahrzeughalter,0,10);
        gridLayout.addComponent(anzahlFahrzeughalterData,1,10);

        gridLayout.addComponent(garantie,3,10);
        gridLayout.addComponent(garantieData,4,10);

        gridLayout.addComponent(umweltplakette,0,11);
        gridLayout.addComponent(umweltplaketteData,1,11);

        gridLayout.addComponent(schadenstoffklasse,3,11);
        gridLayout.addComponent(schadenstoffklasseData,4,11);

        gridLayout.addComponent(published,0,12);
        gridLayout.addComponent(publishedData,1,12);

        gridLayout.addComponent(description,0,13);
        gridLayout.addComponent(descriptionData,0,14,4,16);

        gridLayout.addComponent(close,3,18);


        panel.setContent(gridLayout);
        this.setContent(panel);
    }



}

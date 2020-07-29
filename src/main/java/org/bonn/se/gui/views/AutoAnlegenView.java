package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.bonn.se.control.DropDownsControl;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoAnlegenView extends GridLayout implements View {
    public void setUp() throws DatabaseException, SQLException {
        this.setRows(15);
        this.setColumns(10);
        this.setSizeFull();

        GridLayout formGrid = new GridLayout(4,8);
        formGrid.setMargin(true);

        TopPanelUser topPanelUser = new TopPanelUser();


        ComboBox<String> fahrzeugZustand = new ComboBox<>("Fahrzeugzustand:", DropDownsControl.getInstance().getFahrzeugZustand());

        ComboBox<String> fahrzeugart = new ComboBox<>("Fahrzeugart:", DropDownsControl.getInstance().getFahrzeugArt());

        TextField shortDescription = new TextField("Short Description:");

        ComboBox<String> marke = new ComboBox<>("Marke:",DropDownsControl.getInstance().getMarke());

        ComboBox<String> modell = new ComboBox<>("Modell:",DropDownsControl.getInstance().getModell());

        ComboBox<String> fahrzeugTyp = new ComboBox<>("Fahrzeugtyp:",DropDownsControl.getInstance().getFahrzeugTyp());

        TextField preis = new TextField("Preis(€):");

        DateField erstzulassung = new DateField("Erstzulassung:");
        erstzulassung.setDateFormat("yyyy-MM-dd");

        TextField leistung = new TextField("Leistung(PS):");

        TextField kilometer = new TextField("Kilometer:");

        ComboBox<String> aussenfarbe = new ComboBox<>("Außenfarbe:",DropDownsControl.getInstance().getAussenfarbe());

        ComboBox<Integer> anzahlSitzplaetze = new ComboBox<>("Anzahl Sitzplätze:",DropDownsControl.getInstance().getAnzahlSitzplaetze());

        ComboBox<String> anzahlTueren = new ComboBox<>("Anzahl Türen:",DropDownsControl.getInstance().getAnzahlTueren());

        DateField tuevBis = new DateField("Tüv:");
        tuevBis.setDateFormat("yyyy-MM-dd");

        ComboBox<String> kraftstoffArt = new ComboBox<>("Kraftstoffart:",DropDownsControl.getInstance().getKraftstoffArt());

        ComboBox<String> getriebe = new ComboBox<>("Getriebe:",DropDownsControl.getInstance().getGetriebe());

        ComboBox<String> klimaanlage = new ComboBox<>("Klimatisierung:",DropDownsControl.getInstance().getKlimaanlage());

        ComboBox<Integer> anzahlFahrzeughalter = new ComboBox<>("Anzahl Fahrzeughalter:",DropDownsControl.getInstance().getAnzahlFahrzeughalter());

        ComboBox<String> garantie = new ComboBox<>("Garantie:",DropDownsControl.getInstance().getGarantie());

        ComboBox<String> umweltplakette = new ComboBox<>("Umweltplakette:",DropDownsControl.getInstance().getUmweltplakette());

        ComboBox<String> schadenstoffklasse = new ComboBox<>("Schadenstoffklasse:",DropDownsControl.getInstance().getSchadenstoffKlasse());

        Button weiter = new Button("Weiter");
        Button abbrechen = new Button("Abbrechen");

        this.addComponent(topPanelUser,1,1,8,1);
        this.addComponent(formGrid,2,2,7,3);

        formGrid.addComponent(fahrzeugZustand,0,0,0,0);
        formGrid.setComponentAlignment(fahrzeugZustand, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(fahrzeugart,1,0,1,0);
        formGrid.setComponentAlignment(fahrzeugart, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(shortDescription,0,1,3,1);
        formGrid.setComponentAlignment(shortDescription, Alignment.MIDDLE_CENTER);
        shortDescription.setWidth("800px");

        formGrid.addComponent(marke,0,2,0,2);
        formGrid.setComponentAlignment(marke, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(modell,1,2,1,2);
        formGrid.setComponentAlignment(modell, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(fahrzeugTyp,2,2,2,2);
        formGrid.setComponentAlignment(fahrzeugTyp, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(preis,0,3,0,3);
        formGrid.setComponentAlignment(preis, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(erstzulassung,1,3,1,3);
        formGrid.setComponentAlignment(erstzulassung, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(leistung,2,3,2,3);
        formGrid.setComponentAlignment(leistung, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(kilometer,3,3,3,3);
        formGrid.setComponentAlignment(kilometer, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(aussenfarbe,0,4,0,4);
        formGrid.setComponentAlignment(aussenfarbe, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(anzahlSitzplaetze,1,4,1,4);
        formGrid.setComponentAlignment(anzahlSitzplaetze, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(anzahlTueren,2,4,2,4);
        formGrid.setComponentAlignment(anzahlTueren, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(tuevBis,3,4,3,4);
        formGrid.setComponentAlignment(tuevBis, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(kraftstoffArt,0,5,0,5);
        formGrid.setComponentAlignment(kraftstoffArt, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(getriebe,1,5,1,5);
        formGrid.setComponentAlignment(getriebe, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(klimaanlage,2,5,2,5);
        formGrid.setComponentAlignment(klimaanlage, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(anzahlFahrzeughalter,3,5,3,5);
        formGrid.setComponentAlignment(anzahlFahrzeughalter, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(garantie,0,6,0,6);
        formGrid.setComponentAlignment(garantie, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(umweltplakette,1,6,1,6);
        formGrid.setComponentAlignment(umweltplakette, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(schadenstoffklasse,2,6,2,6);
        formGrid.setComponentAlignment(schadenstoffklasse, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(weiter,3,7,3,7);
        formGrid.setComponentAlignment(weiter, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(abbrechen,2,7,2,7);
        formGrid.setComponentAlignment(abbrechen, Alignment.MIDDLE_CENTER);

        formGrid.setSpacing(true);

        //this.setComponentAlignment(topPanelUser, Alignment.TOP_LEFT);
        this.setComponentAlignment(formGrid,Alignment.MIDDLE_CENTER);
        this.setMargin(false);
        Panel panel = new Panel("Bitte Daten angeben");
        panel.setContent(formGrid);
        panel.setSizeUndefined();
        this.addComponent(panel,2,2,7,3);
        this.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);

        FahrzeugDTO fahrzeugDTO = new FahrzeugDTO();

        abbrechen.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(Views.VERTRIEBLERHOMEVIEW));
        weiter.addClickListener((Button.ClickListener) event ->{
           if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)instanceof Vertriebler) {
               fahrzeugDTO.setFahrzeugZustand(fahrzeugZustand.getValue());
               fahrzeugDTO.setShortDescription(shortDescription.getValue());
               fahrzeugDTO.setMarke(marke.getValue());
               fahrzeugDTO.setModell(modell.getValue());
               fahrzeugDTO.setFahrzeugTyp(fahrzeugTyp.getValue());
               fahrzeugDTO.setErstzulassung(erstzulassung.getValue());
               fahrzeugDTO.setPreis(Integer.parseInt(preis.getValue()));
               fahrzeugDTO.setKilometer(Integer.parseInt(kilometer.getValue()));
               fahrzeugDTO.setLeistung(Integer.parseInt(leistung.getValue()));
               fahrzeugDTO.setKraftstoffart(kraftstoffArt.getValue());
               fahrzeugDTO.setGetriebe(getriebe.getValue());
               fahrzeugDTO.setTuev(tuevBis.getValue());
               fahrzeugDTO.setAussenfarbe(aussenfarbe.getValue());
               fahrzeugDTO.setAnzahlTueren(anzahlTueren.getValue());
               fahrzeugDTO.setAnzahlSitzplaetze(anzahlSitzplaetze.getValue());
               fahrzeugDTO.setKlimaanlage(klimaanlage.getValue());
               fahrzeugDTO.setFahrzeugart(fahrzeugart.getValue());
               fahrzeugDTO.setAnzahlFahrzeughalter(anzahlFahrzeughalter.getValue());
               fahrzeugDTO.setSchadenstoffklasse(schadenstoffklasse.getValue());
               fahrzeugDTO.setUmweltplakette(umweltplakette.getValue());
               fahrzeugDTO.setGarantie(garantie.getValue());
               //fahrzeugDTO.setZeitstempel(());
               fahrzeugDTO.setPersonalnummer(((Vertriebler)UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)).getPersonalNummer());
               ((Vertriebler) UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)).setFahrzeug(fahrzeugDTO);
           }
           UI.getCurrent().getNavigator().navigateTo(Views.FAHRZEUGDESCRIPTION);
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();
        } else if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            try {
                this.setUp();
            } catch (DatabaseException | SQLException e) {
                Logger.getLogger(AutoAnlegenView.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }
    }
}

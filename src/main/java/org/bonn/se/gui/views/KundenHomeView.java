package org.bonn.se.gui.views;

import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.control.DropDownsControl;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.window.FahrzeugWindow;
import org.bonn.se.model.dao.ContainerFahrzeugDAO;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.util.List;

public class KundenHomeView extends GridLayout  implements View {

    public void setUp(){
        this.setRows(15);
        this.setColumns(10);
        this.setSizeFull();
        GridLayout mainGrid = new GridLayout(5,4);
        mainGrid.setMargin(true);

        TopPanelUser topPanelUser = new TopPanelUser();
        String str = "";
        //**********NEW Suche*************************************
        ComboBox<String> marke = new ComboBox<>("Marke:", DropDownsControl.getInstance().getMarke());

        ComboBox<String> modell = new ComboBox<>("Modell:",DropDownsControl.getInstance().getModell(str));

        marke.addValueChangeListener(event -> {
            modell.setValue("");
            modell.setItems(DropDownsControl.getInstance().getModell(event.getValue()));
        });

        ComboBox<String> fahrzeugTyp = new ComboBox<>("Fahrzeugtyp:",DropDownsControl.getInstance().getFahrzeugTyp());

        ComboBox<String> preisAb = new ComboBox<>("Preis Ab(€):",DropDownsControl.getInstance().getNumbers());

        ComboBox<String> preisBis = new ComboBox<>("Preis Bis(€):",DropDownsControl.getInstance().getNumbers());

        DateField erstzulassungAb = new DateField("Erstzulassung Ab:");
        erstzulassungAb.setDateFormat("yyyy-MM-dd");

        DateField erstzulassungBis = new DateField("Erstzulassung Bis:");
        erstzulassungBis.setDateFormat("yyyy-MM-dd");

        ComboBox<String> kilometerAb = new ComboBox<>("Kilometer Ab:",DropDownsControl.getInstance().getNumbers());

        ComboBox<String> kilometerBis = new ComboBox<>("Kilometer Bis:",DropDownsControl.getInstance().getNumbers());

        ComboBox<String> kraftstoffArt = new ComboBox<>("Kraftstoffart:",DropDownsControl.getInstance().getKraftstoffArt());

        ComboBox<String> getriebe = new ComboBox<>("Getriebe:",DropDownsControl.getInstance().getGetriebe());

        ComboBox<String> aussenfarbe = new ComboBox<>("Außenfarbe:",DropDownsControl.getInstance().getAussenfarbe());

        ComboBox<String> umweltplakette = new ComboBox<>("Umweltplakette:",DropDownsControl.getInstance().getUmweltplakette());

        ComboBox<String> schadenstoffklasse = new ComboBox<>("Schadenstoffklasse:",DropDownsControl.getInstance().getSchadenstoffKlasse());

        ComboBox<String> klimaanlage = new ComboBox<>("Klimatisierung:",DropDownsControl.getInstance().getKlimaanlage());

        Button suchen = new Button("Suchen", VaadinIcons.SEARCH);
        Button filterloeschen = new Button("Filter Zurücksetzen");

        this.addComponent(topPanelUser,1,1,8,1);
        this.addComponent(mainGrid,1,2,7,3);
        this.setComponentAlignment(topPanelUser,Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(marke,0,0,0,0);
        mainGrid.setComponentAlignment(marke, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(modell,1,0,1,0);
        mainGrid.setComponentAlignment(modell, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(fahrzeugTyp,2,0,2,0);
        mainGrid.setComponentAlignment(fahrzeugTyp, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(preisAb,3,0,3,0);
        mainGrid.setComponentAlignment(preisAb, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(preisBis,4,0,4,0);
        mainGrid.setComponentAlignment(preisBis, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(erstzulassungAb,0,1,0,1);
        mainGrid.setComponentAlignment(erstzulassungAb, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(erstzulassungBis,1,1,1,1);
        mainGrid.setComponentAlignment(erstzulassungBis, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(kilometerAb,2,1,2,1);
        mainGrid.setComponentAlignment(kilometerAb, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(kilometerBis,3,1,3,1);
        mainGrid.setComponentAlignment(kilometerBis, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(kraftstoffArt,4,1,4,1);
        mainGrid.setComponentAlignment(kraftstoffArt, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(getriebe,0,2,0,2);
        mainGrid.setComponentAlignment(getriebe, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(aussenfarbe,1,2,1,2);
        mainGrid.setComponentAlignment(aussenfarbe, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(umweltplakette,2,2,2,2);
        mainGrid.setComponentAlignment(umweltplakette, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(schadenstoffklasse,3,2,3,2);
        mainGrid.setComponentAlignment(schadenstoffklasse, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(klimaanlage,4,2,4,2);
        mainGrid.setComponentAlignment(klimaanlage, Alignment.MIDDLE_CENTER);

        mainGrid.addComponent(suchen,2,3,2,3);
        mainGrid.setComponentAlignment(suchen, Alignment.MIDDLE_LEFT);

        mainGrid.addComponent(filterloeschen,3,3,3,3);
        mainGrid.setComponentAlignment(filterloeschen, Alignment.MIDDLE_LEFT);

        Grid<FahrzeugDTO> grid = new Grid<>();
        grid.setSizeUndefined();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.setWidthFull();

        List<FahrzeugDTO> liste = ContainerFahrzeugDAO.getInstance().getFahrzeug();
        grid.setItems(liste);
        grid.setCaption("Neuigkeiten");

        suchen.addClickListener((Button.ClickListener) clickEvent -> {
            List<FahrzeugDTO> listeSuche = ContainerFahrzeugDAO.getInstance().loadFahrzeuge(marke.getValue(),modell.getValue(),fahrzeugTyp.getValue()
                    ,erstzulassungAb.getValue(),erstzulassungBis.getValue(),preisAb.getValue(),preisBis.getValue(),kilometerAb.getValue(),kilometerBis.getValue()
                    ,kraftstoffArt.getValue(),getriebe.getValue(),aussenfarbe.getValue(),umweltplakette.getValue(),schadenstoffklasse.getValue(),klimaanlage.getValue());
            grid.setItems(listeSuche);
            grid.setCaption("Suchergebnisse, Treffer: " + listeSuche.size());
            //grid.setVisible(true);
        });

        grid.asSingleSelect().addSingleSelectionListener((SingleSelectionListener<FahrzeugDTO>) event -> {
            if(event.getValue() != null){

                try {
                    UI.getCurrent().addWindow(new FahrzeugWindow(event.getValue()));
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }
            grid.deselectAll();
        });

        filterloeschen.addClickListener((Button.ClickListener) clickEvent -> {
            marke.clear();
            modell.clear();
            fahrzeugTyp.clear();
            preisAb.clear();
            preisBis.clear();
            erstzulassungAb.clear();
            erstzulassungBis.clear();
            kilometerAb.clear();
            kilometerBis.clear();
            kraftstoffArt.clear();
            getriebe.clear();
            aussenfarbe.clear();
            umweltplakette.clear();
            schadenstoffklasse.clear();
            klimaanlage.clear();

        });
        grid.addColumn(FahrzeugDTO::getFahrzeugZustand).setCaption("Fahrzeugzustand");
        grid.addColumn(FahrzeugDTO::getMarke).setCaption("Marke");
        grid.addColumn(FahrzeugDTO::getModell).setCaption("Modell");
        grid.addColumn(FahrzeugDTO::getErstzulassung).setCaption("Erstzulassung");
        grid.addColumn(FahrzeugDTO::getKilometer ).setCaption("Kilometer");
        grid.addColumn(FahrzeugDTO::getPreis).setCaption("Kaufpreis (€)");
        grid.addColumn(FahrzeugDTO::getShortDescription).setCaption("Short Description");

        //**********End Suche******************************************


        mainGrid.setSpacing(true);

        this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);
        this.addComponent(grid,1,9,8,13);
        this.setMargin(false);

        mainGrid.setSizeFull();
        grid.setSizeFull();
        //grid.setVisible(false);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            this.setUp();
        } else if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();
        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }
    }

}

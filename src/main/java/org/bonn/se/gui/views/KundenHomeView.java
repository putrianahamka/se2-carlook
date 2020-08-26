package org.bonn.se.gui.views;

import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.window.FahrzeugWindow;
import org.bonn.se.model.dao.ContainerFahrzeugDAO;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.util.List;

public class KundenHomeView extends VerticalLayout implements View {

    public void setUp(){
        //TopPanelUser topPanelUser = new TopPanelUser();
        this.addComponent(new TopPanelUser());
        GridLayout mainGrid = new GridLayout(3,3);

        mainGrid.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setMargin(true);

        Grid<FahrzeugDTO> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);


        List<FahrzeugDTO> liste = ContainerFahrzeugDAO.getInstance().getFahrzeug();
        grid.setItems(liste);
        grid.setCaption("Meine Fahrzeuge: " + liste.size());

        grid.addColumn(FahrzeugDTO::getFahrzeugZustand).setCaption("Fahrzeugzustand");
        grid.addColumn(FahrzeugDTO::getMarke).setCaption("Marke");
        grid.addColumn(FahrzeugDTO::getModell).setCaption("Modell");
        grid.addColumn(FahrzeugDTO::getErstzulassung).setCaption("Erstzulassung");
        grid.addColumn(FahrzeugDTO::getKilometer ).setCaption("Kilometer");
        grid.addColumn(FahrzeugDTO::getPreis).setCaption("Kaufpreis (€)");
        grid.addColumn(FahrzeugDTO::getShortDescription).setCaption("Short Description");

        this.addComponent(grid);

        grid.asSingleSelect().addSingleSelectionListener((SingleSelectionListener<FahrzeugDTO>) event -> {
            if(event.getValue() != null){
                //System.out.println("ok " + event.getValue());
                try {
                    UI.getCurrent().addWindow(new FahrzeugWindow(event.getValue()));
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }
            grid.deselectAll();
        });


        mainGrid.addComponent(verticalLayout);

        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);
    }
    //zu bedenken
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

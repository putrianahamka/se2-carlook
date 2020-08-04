package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.model.dao.ContainerFahrzeugDAO;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.util.List;

public class VertrieblerHomeView extends VerticalLayout implements View {

    public void setUp(){
        //TopPanelUser topPanelUser = new TopPanelUser();
        this.addComponent(new TopPanelUser());
        GridLayout mainGrid = new GridLayout(3,3);

        mainGrid.setSizeFull();
        Button autoAnlegen = new Button("Auto anlegen");
        autoAnlegen.addClickListener((Button.ClickListener)event -> UI.getCurrent().getNavigator().navigateTo(Views.AUTOANLEGEN));
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setMargin(true);
        verticalLayout.addComponent(autoAnlegen);
        verticalLayout.setComponentAlignment(autoAnlegen, Alignment.MIDDLE_CENTER);
        mainGrid.addComponent(verticalLayout);
         this.addComponent(mainGrid);
         this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);

         Grid<FahrzeugDTO> grid = new Grid<>();
         grid.setSizeFull();
         grid.setHeightMode(HeightMode.UNDEFINED);
         grid.setCaption("Meine Fahrzeuge");

        int personalNummer = ((Vertriebler)UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)).getPersonalNummer();

        List<FahrzeugDTO> liste = ContainerFahrzeugDAO.getInstance().getFahrzeugByPersonalnummer(personalNummer);
        grid.setItems(liste);

        //grid.addColumn(FahrzeugDTO::getId).setCaption("ID");
        grid.addColumn(FahrzeugDTO::getFahrzeugZustand).setCaption("Fahrzeugzustand");
        grid.addColumn(FahrzeugDTO::getShortDescription).setCaption("Short Description");
        grid.addColumn(FahrzeugDTO::getMarke).setCaption("Marke");
        grid.addColumn(FahrzeugDTO::getModell).setCaption("Modell");

        this.addComponent(grid);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            this.setUp();
        } else if(UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();
        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }
    }

}

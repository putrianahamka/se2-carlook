package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

public class KundenHomeView extends VerticalLayout implements View {

    public void setUp(){
        //TopPanelUser topPanelUser = new TopPanelUser();
        this.addComponent(new TopPanelUser());
        GridLayout mainGrid = new GridLayout(3,3);

        mainGrid.setSizeFull();
        Button autoBuchen = new Button("Auto buchen");
        autoBuchen.addClickListener((Button.ClickListener)event -> UI.getCurrent().getNavigator().navigateTo(Views.AUTOANLEGEN));
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setMargin(true);
        verticalLayout.addComponent(autoBuchen);
        verticalLayout.setComponentAlignment(autoBuchen, Alignment.MIDDLE_CENTER);
        mainGrid.addComponent(verticalLayout);
        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);
    }
    //zu bedenken
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
package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoAnlegenView extends GridLayout implements View {
    public void setUp() throws DatabaseException, SQLException {
        this.setRows(15);
        this.setColumns(10);
        this.setSizeFull();

        GridLayout formGrid = new GridLayout(3,6);
        formGrid.setMargin(true);

        TopPanelUser topPanelUser = new TopPanelUser();


        TextField t1 = new TextField();
        t1.setCaption("test1");

        TextField t2 = new TextField();
        t2.setCaption("test2");

        TextField t3 = new TextField("test3");

        Button ok = new Button("OK");
        Button no = new Button("Cancel");

        this.addComponent(topPanelUser,1,1,8,1);
        this.addComponent(formGrid,2,2,7,3);

        formGrid.addComponent(t1,0,0,0,0);
        formGrid.setComponentAlignment(t1, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(t2,1,0,1,0);
        formGrid.setComponentAlignment(t2, Alignment.MIDDLE_CENTER);

        formGrid.addComponent(t3,0,1,0,1);
        formGrid.setComponentAlignment(t3, Alignment.MIDDLE_CENTER);

        formGrid.setSpacing(true);

        //this.setComponentAlignment(topPanelUser, Alignment.TOP_LEFT);
        this.setComponentAlignment(formGrid,Alignment.MIDDLE_CENTER);
        this.setMargin(false);
        Panel panel = new Panel("Bitte Daten angeben");
        panel.setContent(formGrid);
        panel.setSizeUndefined();
        this.addComponent(panel,2,2,7,3);
        this.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
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

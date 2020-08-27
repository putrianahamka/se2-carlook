package org.bonn.se.gui.component;

//import java.awt.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.LoginControl;
import org.bonn.se.model.objects.entities.Kunde;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;
//import com.vaadin.ui.GridLayout;
//import com.vaadin.ui.MenuBar;


public class TopPanelUser extends HorizontalLayout {
    MenuBar bar;
    MenuBar.MenuItem item1;

    public TopPanelUser(){
        //this.setRows(1);
        //this.setColumns(10);
        this.setSizeFull();

        Label headLabel = new Label("<b>CarLook Ltd.</b>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitel");

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel,Alignment.TOP_LEFT);

        HorizontalLayout horizontalLayout = new HorizontalLayout();



        //this.setMargin(false);
        //this.setWidthFull();
        //this.setHeightUndefined();

        bar = new MenuBar();

        if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)!= null){
            item1 = bar.addItem(
                    ((Vertriebler)UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)).getVorname(),null
            );
        }else{
            item1 = bar.addItem(
                    ((Kunde)UI.getCurrent().getSession().getAttribute(Roles.KUNDE)).getVorname(),null
            );
        }

        if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            item1.addItem("Home",VaadinIcons.HOME,(MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.VERTRIEBLERHOMEVIEW));
            item1.addItem("Mein Profil",VaadinIcons.USER,(MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.VERTRIEBLERPROFIL));
            item1.addSeparator();
        }else{
            item1.addItem("Home",VaadinIcons.HOME,(MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW));
            item1.addItem("Mein Profil",VaadinIcons.USER,(MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.KUNDEPROFIL));
            item1.addItem("Meine Reservierungliste",VaadinIcons.CAR,(MenuBar.Command)menuItem ->UI.getCurrent().getNavigator().navigateTo(Views.AUTORESERVIERUNG));
            item1.addSeparator();
        }

        item1.addItem("Logout", VaadinIcons.SIGN_OUT,(MenuBar.Command) menuItem -> LoginControl.logoutUser());

        horizontalLayout.addComponent(bar);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.TOP_RIGHT);
    }




}

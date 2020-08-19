package org.bonn.se.gui.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.bonn.se.gui.views.*;

import javax.servlet.annotation.WebServlet;

import static org.bonn.se.services.util.Views.*;
@Push
@Title("CarLook Ltd.")
@PreserveOnRefresh
public class MyUI extends UI {


    @Override
    public void init(VaadinRequest vaadinRequest){
        this.setSizeFull();

        Navigator navi = new Navigator(this,this);
        navi.addView(REGISTERVERTRIEBLER, RegisterVertrieblerView.class);
        navi.addView(VERTRIEBLERHOMEVIEW, VertrieblerHomeView.class);
        navi.addView(LOGINVIEW, LoginView.class);
        navi.addView(VERTRIEBLERPROFIL, VertrieblerProfilView.class);
        navi.addView(AUTOANLEGEN, AutoAnlegenView.class);
        navi.addView(FAHRZEUGDESCRIPTION,AutoDescriptionView.class);

        navi.addView(REGISTERKUNDE, RegisterKundenView.class);
        navi.addView(KUNDEHOMEVIEW, KundenHomeView.class);

        navi.addView(KUNDEPROFIL, KundeProfilView.class);


        UI.getCurrent().getNavigator().navigateTo(LOGINVIEW);

    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}

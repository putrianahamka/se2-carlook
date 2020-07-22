package org.bonn.se.gui.ui;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.bonn.se.gui.views.RegisterVertrieblerView;

import javax.servlet.annotation.WebServlet;

import static org.bonn.se.services.util.Views.REGISTERVERTRIEBLER;

@Title("CarLook Ltd.")
public class MyUI extends UI {


    @Override
    public void init(VaadinRequest vaadinRequest){
        this.setSizeFull();

        Navigator navi = new Navigator(this,this);
        navi.addView(REGISTERVERTRIEBLER, RegisterVertrieblerView.class);


        UI.getCurrent().getNavigator().navigateTo(REGISTERVERTRIEBLER);
    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
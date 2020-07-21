package org.bonn.se.gui.views;

import com.vaadin.event.ShortcutAction;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import org.bonn.se.control.UserSearchControl;
import org.bonn.se.model.dao.UserDAO;
import org.bonn.se.model.objects.entities.User;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;
import com.vaadin.navigator.View;

public class RegisterVertrieblerView extends VerticalLayout implements View {

    public void setUp(){
        this.setSizeFull();

        final TextField vorName = new TextField();
        vorName.setCaption("Vorname:");

        final TextField nachName = new TextField();
        nachName.setCaption("Nachname:");

        final TextField email = new TextField();
        email.setCaption("Email:");

        //final TextField kontaktNr = new TextField();
        //kontaktNr.setCaption("Kontakt Nr.:");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort:");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(vorName);
        layout.addComponent(nachName);
        //layout.addComponent(kontaktNr);
        layout.addComponent(email);
        layout.addComponent(passwordField);

        Panel panel = new Panel("Bitte Daten eingeben:");
        panel.addStyleName("registerVertriebler");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);

        Button registerVertrieblerButton = new Button("Registrieren");
        registerVertrieblerButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        layout.addComponent(registerVertrieblerButton);
        panel.setSizeUndefined();

        User user = new User();



        registerVertrieblerButton.addClickListener(
                event -> {
                    try{
                        if (UserSearchControl.getInstance().existUser(email.getValue())){
                            email.setValue("");
                            email.setPlaceholder("Email existiert schon!");
                            email.setComponentError(new UserError("Bitte eine andere Email verwenden"));
                        } else{
                            user.setType("v");
                            user.setVorname(vorName.getValue());
                            user.setNachname(nachName.getValue());
                            user.setEmail(email.getValue());
                            user.setPasswort(passwordField.getValue());

                            UserDAO.getInstance().registerUser(user);

                            Vertriebler vertriebler = new Vertriebler();
                            vertriebler.setEmail(user.getEmail());
                            vertriebler.setVorname(user.getVorname());
                            vertriebler.setNachname(user.getNachname());
                            vertriebler.setPasswort(user.getPasswort());

                            UI.getCurrent().getSession().setAttribute(Roles.VERTRIEBLER,vertriebler);
                        }
                    } catch(DatabaseException e){
                        e.printStackTrace();
                    }
                });


    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.VERTRIEBLERHOMEVIEW);
        } else if(UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW);
        } else {
            this.setUp();
        }
    }


}

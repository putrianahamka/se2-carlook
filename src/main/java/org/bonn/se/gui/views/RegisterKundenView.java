package org.bonn.se.gui.views;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.UserSearchControl;
import org.bonn.se.gui.component.ConfirmationWindow;
import org.bonn.se.gui.component.TopPanel;
import org.bonn.se.model.dao.ProfilDAO;
import org.bonn.se.model.dao.UserDAO;
import org.bonn.se.model.objects.entities.User;
import org.bonn.se.model.objects.entities.Kunde;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;
import com.vaadin.navigator.View;

import java.sql.SQLException;

public class RegisterKundenView extends VerticalLayout implements View {

    public void setUp(){
        //this.setSizeFull();

        Label head = new Label ("Registrieren Sie sich als Kunde", ContentMode.HTML);

        this.addComponent(new TopPanel("kunde"));

        head.setSizeUndefined();
        head.addStyleName("headLabel");
        this.addComponent(head);
        this.setComponentAlignment(head,Alignment.TOP_CENTER);
        //HorizontalLayout hori = new HorizontalLayout();
        // hori.addComponent(head);
        //hori.setHeight("10px");

        final TextField vorName = new TextField();
        vorName.setCaption("Vorname:");

        final TextField nachName = new TextField();
        nachName.setCaption("Nachname:");

        final TextField email = new TextField();
        email.setCaption("Email:");
        /*
        final TextField kontaktNr = new TextField();
        kontaktNr.setCaption("Tel. Nummer:");
        */
        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort:");

        Binder<User> binder = new Binder<>(User.class);

        binder.forField(passwordField)
                .asRequired("Passwort ist muss!")
                .withValidator(new StringLengthValidator("Passwort muss 8 Zeichen lang sein!",8,null))
                .bind(User::getPasswort,User::setPasswort);

        binder.forField(vorName)
                .asRequired("Vorname ist muss!")
                .bind(User::getNachname,User::setVorname);
        binder.forField(nachName)
                .asRequired("Vorname ist muss!")
                .bind(User::getNachname,User::setNachname);
        binder.forField(email)
                .asRequired("Email ist muss")

                .withValidator(new EmailValidator("Keine gültige Email!"))

                .bind(User::getEmail,User::setEmail);



        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(vorName);
        layout.addComponent(nachName);
        //layout.addComponent(kontaktNr);
        layout.addComponent(email);
        layout.addComponent(passwordField);

        Panel panel = new Panel("Bitte Daten eingeben:");
        panel.addStyleName("registerVertriebler");

        layout.setWidth("300px");
        vorName.setWidth("270px");
        nachName.setWidth("270px");
        //kontaktNr.setWidth("270px");
        email.setWidth("270px");
        passwordField.setWidth("270px");


        //this.addComponent(hori);
        this.addComponent(panel);
        //this.setComponentAlignment(hori, Alignment.TOP_CENTER);
        this.setComponentAlignment(panel, Alignment.TOP_CENTER);



        panel.setContent(layout);

        Button registerKundenButton = new Button("Registrieren");
        registerKundenButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        registerKundenButton.setEnabled(false);
        layout.addComponent(registerKundenButton);
        panel.setSizeUndefined();

        User user = new User();
        binder.setBean(user);


        registerKundenButton.addClickListener(
                event -> {
                    try{
                        if (UserSearchControl.getInstance().existUser(email.getValue())){
                            email.setValue("");
                            email.setPlaceholder("Email existiert schon!");
                            email.setComponentError(new UserError("Bitte eine andere Email verwenden"));
                        } else{
                            user.setType("k");
                            registerKundenButton.setEnabled(false);
                            user.setVorname(vorName.getValue());
                            user.setNachname(nachName.getValue());
                            user.setEmail(email.getValue());
                            user.setPasswort(passwordField.getValue());
                           // user.setKontaktNr(kontaktNr.getValue());

                            Kunde kunde = new Kunde();
                            kunde.setEmail(user.getEmail());
                            kunde.setVorname(user.getVorname());
                            kunde.setNachname(user.getNachname());
                            kunde.setPasswort(user.getPasswort());
                            //kunden.setKontaktNr(user.getKontaktNr());


                            UserDAO.getInstance().registerUser(user);

                            UI.getCurrent().getSession().setAttribute(Roles.KUNDE, kunde);

                            kunde = ProfilDAO.getInstance().getKundenProfil(kunde);

                            UI.getCurrent().getSession().setAttribute(Roles.KUNDE, kunde);
                            ConfirmationWindow confirmationWindow = new ConfirmationWindow("Sie haben sich erfolgreich registriert");
                            UI.getCurrent().addWindow(confirmationWindow);
                            UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW);
                        }
                    } catch(DatabaseException | SQLException e){
                        e.printStackTrace();
                    }
                });
//noch anpassen
        binder.addStatusChangeListener(
                event -> registerKundenButton.setEnabled(binder.isValid())
        );
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

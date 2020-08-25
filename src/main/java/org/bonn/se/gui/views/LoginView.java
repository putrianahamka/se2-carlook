package org.bonn.se.gui.views;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.LoginControl;
import org.bonn.se.control.exception.NoSuchUserOrPassword;
import org.bonn.se.gui.component.TopPanel;
import org.bonn.se.model.objects.entities.User;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginView extends VerticalLayout implements View {
    public void setUp(){
        Label head = new Label ("Welcome to CarLook Ltd. </n> Wenn Sie noch keine Account haben, bitte registrieren Sie sich.", ContentMode.HTML);

        this.addComponent(new TopPanel("login"));
        head.setSizeUndefined();
        head.addStyleName("headLabel");
        this.addComponent(head);
        this.setComponentAlignment(head, Alignment.TOP_CENTER);
        //HorizontalLayout hori = new HorizontalLayout();
        // hori.addComponent(head);
        //hori.setHeight("10px");

        final TextField email = new TextField();
        email.setCaption("E-Mail:");
        email.setWidth("300px");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort:");
        passwordField.setWidth("300px");

       VerticalLayout verticalLayout = new VerticalLayout();
       verticalLayout.addComponent(email);
       verticalLayout.addComponent(passwordField);
        verticalLayout.setComponentAlignment(email,Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(passwordField,Alignment.MIDDLE_CENTER);

       Panel panel = new Panel("Bitte Login-Daten eingeben:");

       this.addComponent(panel);
       this.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);

       panel.setContent(verticalLayout);

       Button loginButton = new Button("Login", VaadinIcons.SIGN_IN);
       loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
       verticalLayout.addComponent(loginButton);
       verticalLayout.setComponentAlignment(loginButton,Alignment.MIDDLE_CENTER);

       panel.setSizeUndefined();
       panel.setWidth("350px");

       loginButton.addClickListener( (Button.ClickListener) clickEvent -> {
           String userEmail = email.getValue();
           String passwort = passwordField.getValue();
           try{
               LoginControl.getInstance().checkAuthentication(userEmail,passwort);
           } catch (NoSuchUserOrPassword ex){
               Notification.show("Fehler: Email oder Passwort ist falsch!",Notification.Type.ERROR_MESSAGE);
               email.clear();
               passwordField.clear();
           }catch(DatabaseException ex){
               Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
               email.clear();
               passwordField.clear();
           }catch(SQLException throwables){
               Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
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

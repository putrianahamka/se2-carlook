package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.util.Roles;


public class VertrieblerProfilView extends VerticalLayout implements View {

    public void setUp() {

        this.addComponent(new TopPanelUser());

        Label header = new Label ("Hier können Sie Ihre Profil ansehen, aber keine Änderungen vornehmen", ContentMode.HTML);
        header.setSizeUndefined();

        this.addComponent(header);

        this.setComponentAlignment(header, Alignment.TOP_CENTER);

        final TextField vorName = new TextField();
        vorName.setCaption("Vorname:");

        final TextField nachName = new TextField();
        nachName.setCaption("Nachname:");

        final TextField email = new TextField();
        email.setCaption("Email:");

        final TextField kontaktNr = new TextField();
        kontaktNr.setCaption("Tel. Nummer:");

        final TextField personalNummer = new TextField();
        personalNummer.setCaption("Personalnummer:");

        Vertriebler vertriebler = (Vertriebler) UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) ;

        personalNummer.setValue(vertriebler.toString());
        personalNummer.setReadOnly(true);

        vorName.setValue(vertriebler.getVorname());
        vorName.setReadOnly(true);

        nachName.setValue(vertriebler.getNachname());
        nachName.setReadOnly(true);

        email.setValue(vertriebler.getEmail());
        email.setReadOnly(true);

        kontaktNr.setValue(vertriebler.getKontaktNr());
        kontaktNr.setReadOnly(true);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(personalNummer,vorName,nachName,email,kontaktNr);

        Panel panel = new Panel("Profil-Daten:");

        this.addComponent(panel);
        this.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);

        panel.setContent(verticalLayout);
        panel.setSizeUndefined();


    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setUp();
    }
}

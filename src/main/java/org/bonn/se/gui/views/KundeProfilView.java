package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.model.objects.entities.Kunde;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.util.Roles;


public class KundeProfilView extends VerticalLayout implements View {

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

        final TextField kundenNummer = new TextField();
        kundenNummer.setCaption("Kundennummer:");

        Kunde kunde = (Kunde) UI.getCurrent().getSession().getAttribute(Roles.KUNDE) ;

        kundenNummer.setValue(kunde.toString());
        kundenNummer.setReadOnly(true);

        vorName.setValue(kunde.getVorname());
        vorName.setReadOnly(true);

        nachName.setValue(kunde.getNachname());
        nachName.setReadOnly(true);

        email.setValue(kunde.getEmail());
        email.setReadOnly(true);



        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(kundenNummer,vorName,nachName,email);

        verticalLayout.setWidth("300px");
        vorName.setWidth("270px");
        nachName.setWidth("270px");
        email.setWidth("270px");
        kundenNummer.setWidth("270px");
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

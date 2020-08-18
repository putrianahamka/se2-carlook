package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.ConfirmationWindow;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.model.objects.entities.ContainerFahrzeuge;
import org.bonn.se.model.objects.entities.Vertriebler;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

public class AutoDescriptionView extends GridLayout implements View {
    public void setUp(){
        this.setMargin(false);
        this.setSizeFull();
        this.setColumns(10);
        this.setRows(10);

        TopPanelUser topPanelUser = new TopPanelUser();

        final RichTextArea richTextArea = new RichTextArea();
        richTextArea.setWidth("940px");
        richTextArea.setHeightFull();
        richTextArea.setValue("<h1>Hallo</h1>\n" + "<p>Hier können Sie Fahrzeug beschreiben.</p>");

        Button abbrechen = new Button("Abbrechen");

        abbrechen.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(Views.VERTRIEBLERHOMEVIEW));

        Button speichern = new Button("Speichern");


        speichern.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ConfirmDialog.Factory df = new DefaultConfirmDialogFactory() {

                    @Override
                    public ConfirmDialog create(String caption, String message, String okCaption, String cancelCaption, String notOkCaption) {
                        return super.create("Bestätigung", message, "Ja", "Nein", notOkCaption);
                    }
                };
                ConfirmDialog.setFactory(df);
                ConfirmDialog.show(MyUI.getCurrent(), "Möchten Sie das Auto spreichern?",
                        (ConfirmDialog.Listener) dialog -> {
                            FahrzeugDTO fahrzeugDTO = ((Vertriebler) UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER)).getFahrzeugDTO();
                            fahrzeugDTO.setDescription(richTextArea.getValue());
                            if (dialog.isConfirmed()) {
                                ContainerFahrzeuge.getInstance().setFahrzeug((Vertriebler) UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER));
                                ConfirmationWindow confirmationWindow = new ConfirmationWindow("Auto wurde erfolgreich angelegt");
                                UI.getCurrent().addWindow(confirmationWindow);
                            }

                            UI.getCurrent().getNavigator().navigateTo(Views.VERTRIEBLERHOMEVIEW);

                        });
            }
        });








        this.addComponent(topPanelUser,1,1,8,1);
        this.addComponent(richTextArea,0,2,9,7);
        this.addComponent(abbrechen,3,8,3,8);
        this.addComponent(speichern,6,8,6,8);

        this.setComponentAlignment(topPanelUser, Alignment.TOP_LEFT);
        this.setComponentAlignment(richTextArea,Alignment.TOP_CENTER);
        this.setComponentAlignment(abbrechen,Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(speichern,Alignment.MIDDLE_CENTER);
        this.setMargin(false);

    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            this.setUp();
        } else if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();

        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }
    }
}

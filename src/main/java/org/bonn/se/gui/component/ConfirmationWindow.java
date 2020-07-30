package org.bonn.se.gui.component;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;
// Sie haben sie erfolgreich Reserviert.. nimmt, RegisterVertrieblerView

public class ConfirmationWindow extends Window {
    public ConfirmationWindow(String label){
        super("Confirmation");
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label(label));
        verticalLayout.setMargin(true);
        setContent(verticalLayout);

        Button closeWindow = new Button("OK");

        closeWindow.addClickListener(e ->{
            close();
        });
        verticalLayout.addComponent(closeWindow);
        verticalLayout.setComponentAlignment(closeWindow,Alignment.MIDDLE_CENTER);

    }
}

package org.bonn.se.gui.component;


import com.vaadin.ui.*;

import static org.bonn.se.services.util.Views.*;


public class TopPanel extends HorizontalLayout {
    public TopPanel(String usertype){
        this.setSizeFull();
        this.setMargin(false);

        String buttonOne;
        String buttonTwo;
        String navigateToOne;
        String navigateToTwo;
        if (usertype.equals("vertriebler")){
            buttonOne = "Registrierung Kunde";
            buttonTwo = "Login";
            navigateToOne= REGISTERKUNDE;
            navigateToTwo = LOGINVIEW;
        }else if(usertype.equals("kunde")){
            buttonOne="Registrierung Vertriebler";
            buttonTwo="Login";
            navigateToOne= REGISTERVERTRIEBLER;
            navigateToTwo = LOGINVIEW;
        } else{
            buttonOne = "Registrierung Vertriebler";
            buttonTwo = "Registrierung Kunde";
            navigateToOne = REGISTERVERTRIEBLER;
            navigateToTwo = REGISTERKUNDE;
        }
        //this.setRows(1);
        //this.setColumns(10);
        this.setSizeFull();

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button switchUser = new Button(buttonOne);
        Button loginButton = new Button (buttonTwo);

        horizontalLayout.addComponents(switchUser,loginButton);
        horizontalLayout.setMargin(true);
        switchUser.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(navigateToOne));

        loginButton.addClickListener((Button.ClickListener) event -> {
            if (loginButton.getCaption().equals("Registrierung Kunde")){
                UI.getCurrent().getNavigator().navigateTo(navigateToTwo);
            } else{
                UI.getCurrent().getNavigator().navigateTo(navigateToTwo);
            }
        });

        //this.addComponent(horizontalLayout,8,0,9,0);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_RIGHT);

    }
}

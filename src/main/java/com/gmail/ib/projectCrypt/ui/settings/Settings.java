package com.gmail.ib.projectCrypt.ui.settings;

import com.gmail.ib.projectCrypt.ui.MainLayout;
import com.gmail.ib.projectCrypt.ui.profile.profileContent;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.SQLException;


@Route(value = "Settings", layout = MainLayout.class)
@PageTitle("Settings")
public class Settings extends VerticalLayout {

    public Settings() throws SQLException {
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setWidthFull();
        setHeightFull();

        H1 title = new H1("Settings");
        title.getElement().getStyle().set("color", "rgb(18, 202, 214)");
        add(title);

        subscriptionInfo subscriptionInformation = new subscriptionInfo();
        add(subscriptionInformation);

        profileContent profileInformation = new profileContent();
        profileInformation.setWidth("70%");
        add(profileInformation);

        settingsMenu buttons = new settingsMenu();
        add(buttons);

    }

}

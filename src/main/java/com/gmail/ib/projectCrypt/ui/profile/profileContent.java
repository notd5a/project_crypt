package com.gmail.ib.projectCrypt.ui.profile;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.sql.SQLException;

public class profileContent extends HorizontalLayout {

    public profileContent() throws SQLException {

        Image avatarImage = new Image("img/avatar.png", "");
        add(avatarImage);

        profileInformation information = new profileInformation();
        add(information);

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setMargin(true);
        setPadding(true);
        setHeight("70%");
        setWidth("70%");
        setMaxHeight("100%");
        setMaxWidth("100%");
    }


}

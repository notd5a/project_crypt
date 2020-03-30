package com.gmail.ib.projectCrypt.ui.profile;

import com.gmail.ib.projectCrypt.authentication.User;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.sql.SQLException;


public class profileInformation extends VerticalLayout implements HasSize {

    public profileInformation() throws SQLException {
        H2 title = new H2();
        title.getElement().getStyle().set("color", "rgb(18, 202, 214)");

        Label nameLabel = new Label();
        TextField name = new TextField();
        Label lastNameLabel = new Label();
        TextField lastName = new TextField();
        Label usernameLabel = new Label();
        TextField username = new TextField();
        Label emailLabel = new Label();
        TextField email = new TextField();

        //user details
        User user = new User();
        user.loadData();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.STRETCH);
        setWidth("100%");

        title.setText("Profile");

        nameLabel.setText("First Name: ");
        name.setPlaceholder("name...");
        name.setValue(user.getFirstName());

        lastNameLabel.setText("Last Name:");
        lastName.setPlaceholder("last name...");
        lastName.setValue(user.getLastName());

        usernameLabel.setText("Username: ");
        username.setPlaceholder("username...");
        username.setValue(user.getUsername());

        emailLabel.setText("Email: ");
        email.setPlaceholder("email...");
        email.setValue(user.getEmail());

        //Adding all components in the correct order
        add(title);
        add(nameLabel);
        add(name);
        add(lastNameLabel);
        add(lastName);
        add(usernameLabel);
        add(username);
        add(emailLabel);
        add(email);

    }

}

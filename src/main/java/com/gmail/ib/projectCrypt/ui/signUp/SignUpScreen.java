package com.gmail.ib.projectCrypt.ui.signUp;

import com.gmail.ib.projectCrypt.authentication.AccessControl;

import com.gmail.ib.projectCrypt.authentication.User;
import com.gmail.ib.projectCrypt.backend.cryptData.SQLConnector;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

@Route("Sign-Up")
@PageTitle("Sign Up")
@CssImport("./styles/shared-styles.css")


//This class is largely unfinished due to time constraints, most work needs to be done on how it looks and changing that is going to require some more time. Sadly, im forced to upload this with base functionality and with it looking terrible.
public class SignUpScreen extends VerticalLayout {

    private AccessControl accessControl;

    public SignUpScreen() {

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setAlignSelf(Alignment.CENTER);
        add(buildUI());

    }

    private VerticalLayout buildUI() {

        VerticalLayout layout = new VerticalLayout();

        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.STRETCH);
        layout.setMaxHeight("50%");
        layout.setMaxWidth("50%");

        H1 title = new H1("Sign Up");
        H3 subtitle = new H3("To sign up, please fill out the form below :)");
        layout.add(title);
        title.getElement().getStyle().set("color", "rgb(18, 202, 214)");
        layout.add(subtitle);
        FormLayout form = form();
        layout.add(form);
        layout.setAlignSelf(Alignment.CENTER);

        return layout;
    }

    public FormLayout form() {

        FormLayout signUpForm = new FormLayout();

        signUpForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));

        //name fields
        TextField firstName = new TextField();
        firstName.setPlaceholder("John");
        firstName.setValueChangeMode(ValueChangeMode.EAGER);
        firstName.setRequired(true);
        TextField lastName = new TextField();
        lastName.setValueChangeMode(ValueChangeMode.EAGER);
        lastName.setPlaceholder("Doe");
        TextField username = new TextField();
        username.setPlaceholder("Username");
        username.setRequired(true);
        username.setValueChangeMode(ValueChangeMode.EAGER);

        //email and bday fields
        TextField email = new TextField();
        email.setPlaceholder("JohnDoe@mail.com");
        email.setValueChangeMode(ValueChangeMode.EAGER);

        //password field
        PasswordField password = new PasswordField();
        password.setRequired(true);
        password.setPlaceholder("Make it Strong :)");

        //adding buttons for confirm and cancel here as well
        userControl USERCONTROL = new userControl();

        Button confirmButton = new Button("Sign up!");
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        Button cancelButton = new Button("Back to Login");
        cancelButton.addClickListener(e ->
                getUI().ifPresent(ui ->
                        ui.navigate("Login")));
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);


        //adding them to the form layout
        signUpForm.addFormItem(firstName, "First Name");
        signUpForm.addFormItem(lastName, "Last Name");
        signUpForm.addFormItem(username, "Username");
        signUpForm.addFormItem(email, "Email");
        signUpForm.addFormItem(password, "Password");
        signUpForm.add(cancelButton);
        signUpForm.add(confirmButton);

        signUpForm.setColspan(email, 2);
        signUpForm.setColspan(username, 3);
        signUpForm.setColspan(confirmButton, 2);
        signUpForm.setColspan(cancelButton, 1);
        signUpForm.setColspan(password, 3);

        confirmButton.addClickListener(e ->
        {
            try {
                USERCONTROL.createUser(firstName.getValue(), lastName.getValue(), username.getValue(), email.getValue(), password.getValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //returning a form
        return signUpForm;
    }
}

class userControl {

    public void createUser(String firstName, String lastName, String username, String email, String password) throws SQLException {

        Dialog success = new Dialog();
        Div content = new Div();
        Text message = new Text("You have successfully created an Account!");

        //loading the user details in here so they can be accessed by Settings view
        User user = new User(firstName, lastName, username, email, password);
        SQLConnector connector = new SQLConnector();
        Statement statement = connector.SQLConnection();
        String sql = "INSERT INTO users" +
                "(username, password, lastName, email, firstName)" + "VALUES(" +
                "'" + username + "'" + "," +
                "'" + password + "'" + "," +
                "'" + lastName + "'" + "," +
                "'" + email + "'" + "," +
                "'" + firstName + "'" + ")";

        statement.executeUpdate(sql);

        content.add(message);
        success.add(content);
        success.open();
        message.getUI().ifPresent(ui -> ui.navigate("Login"));

    }

}

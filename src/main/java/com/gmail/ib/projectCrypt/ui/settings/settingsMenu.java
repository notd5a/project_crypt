package com.gmail.ib.projectCrypt.ui.settings;

import com.gmail.ib.projectCrypt.authentication.AccessControlFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

public class settingsMenu extends VerticalLayout {

    public settingsMenu() {
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.STRETCH);
        setWidth("70%");
        setHeightFull();

        //Below button changes the theme from dark to light mode throughout the entire program :)

        Button toggleButton = new Button("Toggle dark theme", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // (1)

            if (themeList.contains(Lumo.DARK)) { // (2)
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        //add more buttons here for more settings
        //Todo: Add support for multiple currencies using a dropbox menu?

        add(toggleButton);

        //here we want to add the delete account option

        H4 dangerZone = new H4("DANGER ZONE");
        add(dangerZone);

        FormLayout deleteAccount = new FormLayout();
        Paragraph warningInformation = new Paragraph("Once you delete your account there is no going back! Please be careful!");
        Paragraph finalWarningInformation = new Paragraph("Once you delete your account there is no going back! Please be careful!");
        deleteAccount.add(warningInformation);

        Button deleteAccountButton = new Button("Delete Account!");
        Button finalConfirmationButton = new Button("Delete Account!!!???");
        Dialog confirmation = new Dialog(finalWarningInformation, finalConfirmationButton);

        deleteAccountButton.addClickListener(e ->
                confirmation.open());

        finalConfirmationButton.addClickListener(e ->
                logout());

        deleteAccount.add(deleteAccountButton);

        add(deleteAccount);

    }

    private void logout() {
        AccessControlFactory.getInstance().createAccessControl().signOut();
    }

    private void deleteUser() {

        //code to delete account from the database


        logout();
    }
}

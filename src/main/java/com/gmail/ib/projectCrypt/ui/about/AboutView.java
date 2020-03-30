package com.gmail.ib.projectCrypt.ui.about;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;
import com.gmail.ib.projectCrypt.ui.MainLayout;

@Route(value = "About", layout = MainLayout.class)
@PageTitle("About")
public class AboutView extends VerticalLayout {

    public static final String VIEW_NAME = "About";

    public AboutView() {
        //Adding logo
        Image logo = new Image("http://i.imgur.com/q7YE4Ob.png", "");

        //styling height and width
        logo.setMaxHeight("100px");
        logo.setMaxWidth("100px");
        logo.getElement().getStyle().set("align", "middle");

        add(logo);
        //Icon
        add(VaadinIcon.INFO_CIRCLE.create());
        Span versionDesc = new Span(" This application is using Vaadin version "
                + Version.getFullVersion() + ".");

        //Adding desc and versionDesc
        Span desc = new Span("Project Crypt is a web based application designed to help users make Crypto Currency investment decisions");
        add(desc);
        add(versionDesc);
        add(logo);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

    }
}

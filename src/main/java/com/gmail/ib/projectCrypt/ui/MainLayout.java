package com.gmail.ib.projectCrypt.ui;

import com.gmail.ib.projectCrypt.backend.cryptData.API;
import com.gmail.ib.projectCrypt.newsPage.newsView;
import com.gmail.ib.projectCrypt.ui.dashboard.Dashboard;
import com.gmail.ib.projectCrypt.ui.settings.Settings;
import com.gmail.ib.projectCrypt.authentication.AccessControlFactory;
import com.gmail.ib.projectCrypt.ui.about.AboutView;
import com.gmail.ib.projectCrypt.ui.hubs.HubView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


/**
 * The main layout. Contains the navigation menu.
 */

    @Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Project Crypt", shortName = "Project Crypt")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/menu-buttons.css", themeFor = "vaadin-button")
public class MainLayout extends AppLayout implements RouterLayout {

    private final Button logoutButton;

    public MainLayout() {

        //running the API on a backend thread so that it updates prices while the program is running.
        Runnable r = new Runnable() {
            @Override
            public void run() {
                API.main();
            }
        };

        new Thread(r).start();

        // Header of the menu (the navbar)

        // menu toggle
        final DrawerToggle drawerToggle = new DrawerToggle();
        drawerToggle.addClassName("menu-toggle");
        addToNavbar(drawerToggle);

        // image, logo
        final HorizontalLayout top = new HorizontalLayout();
        top.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        top.setClassName("menu-header");

        // Note! Image resource url is resolved here as it is dependent on the
        // execution mode (development or production) and browser ES level
        // support
        final String resolvedImage = VaadinService.getCurrent().resolveResource(
                "http://i.imgur.com/q7YE4Ob.png", VaadinSession.getCurrent().getBrowser());

        final Image image = new Image(resolvedImage, "");
        //Styling the image so it doesnt take up the whole screen, check css for the rest.
        image.setMaxWidth("30px");
        image.setMaxHeight("30px");

        //adding the images into the navbar
        final Label title = new Label("Project Crypt");
        top.add(image, title);
        top.add(title);
        addToNavbar(top);

        //create the LUMO ICONS to use in the drawer navigation system
        Icon dashboardIcon = new Icon("lumo", "bar-chart");
        Icon hubsIcon = new Icon("lumo", "menu");
        Icon settingsIcon = new Icon("lumo", "cog");
        Icon newsIcon = new Icon(VaadinIcon.NEWSPAPER);

        // Navigation items
        addToDrawer(createMenuLink(Dashboard.class, "Dashboard",
                dashboardIcon));

        addToDrawer(createMenuLink(HubView.class, "Hubs",
                hubsIcon));

        addToDrawer(createMenuLink(newsView.class, "News",
                newsIcon));

        addToDrawer(createMenuLink(Settings.class, "Settings",
                settingsIcon));

        addToDrawer(createMenuLink(AboutView.class, AboutView.VIEW_NAME,
                VaadinIcon.INFO_CIRCLE.create()));

        // Create logout button but don't add it yet; admin view might be added
        // in between (see #onAttach())
        logoutButton = createMenuButton("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> logout());
        logoutButton.getElement().setAttribute("title", "Logout (Ctrl+L)");

    }

    private void logout() {
        AccessControlFactory.getInstance().createAccessControl().signOut();
    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass,
            String caption, Icon icon) {
        final RouterLink routerLink = new RouterLink(null, viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon);
        routerLink.add(new Span(caption));
        icon.setSize("24px");
        return routerLink;
    }

    private Button createMenuButton(String caption, Icon icon) {
        final Button routerButton = new Button(caption);
        routerButton.setClassName("menu-button");
        routerButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        routerButton.setIcon(icon);
        routerButton.getElement().getStyle().set("margin", "5px");
        icon.setSize("20px");
        return routerButton;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // User can quickly activate logout with Ctrl+L
        attachEvent.getUI().addShortcutListener(() -> logout(), Key.KEY_L,
                KeyModifier.CONTROL);


        // Finally, add logout button for all users
        addToDrawer(logoutButton);
    }

}

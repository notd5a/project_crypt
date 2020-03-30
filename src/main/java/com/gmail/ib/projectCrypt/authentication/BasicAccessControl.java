package com.gmail.ib.projectCrypt.authentication;

import com.gmail.ib.projectCrypt.backend.cryptData.SQLConnector;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a user if the password is the same string, and
 * considers the user "admin" as the only administrator.
 */
public class BasicAccessControl implements AccessControl {

    @Override
    public boolean signIn(String username, String password) throws SQLException {
        String querpass = "";
        if (username == null || username.isEmpty()) {
            return false;
        }
        SQLConnector connect = new SQLConnector();
        Statement stmt = connect.SQLConnection();
        String query = "SELECT password FROM users WHERE username = '"+username+"'";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            querpass = rs.getString("password");
        }

        if (!password.equals(querpass)){
            return false;
        }

        CurrentUser.set(username);
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

    @Override
    public void signOut() {
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().navigate("");
    }
}

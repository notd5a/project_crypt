package com.gmail.ib.projectCrypt.authentication;

import com.gmail.ib.projectCrypt.backend.cryptData.SQLConnector;
import jdk.vm.ci.meta.Local;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
        //empty for when we dont want to initialize in the constructor
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loadData() throws SQLException {

        SQLConnector connector = new SQLConnector();
        Statement statement = connector.SQLConnection();
        String query = "SELECT * FROM users WHERE username = " + "'" + CurrentUser.get() + "'";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            setFirstName(rs.getString("firstName"));
            setLastName(rs.getString("lastName"));
            setEmail(rs.getString("email"));
            setUsername(rs.getString("username"));
            setPassword(rs.getString("password"));
        }
    }
}

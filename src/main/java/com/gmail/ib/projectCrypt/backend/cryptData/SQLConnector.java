package com.gmail.ib.projectCrypt.backend.cryptData;

import java.sql.*;

public class SQLConnector {
    public Statement SQLConnection() throws SQLException {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cryptocurrencies?user=root&password=toor");
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        return statement;

    }
}

package com.bullet.sqlite3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLITEDB {
    private static final String DB_URL = "jdbc:sqlite:bullet.db";

    public SQLITEDB() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }


}

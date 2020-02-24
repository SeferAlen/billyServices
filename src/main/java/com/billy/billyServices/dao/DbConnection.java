package com.billy.billyServices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Abstract class containing shared values and method's for Db related actions
 */
public abstract class DbConnection {
    protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    protected static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    protected static final String USER = "alen";
    protected static final String PASSWORD = "alen";
    protected static final String DB_EXCEPTION = "Db exception: ";
    protected static final String NAME_COLUMN = "name";
    protected static final int ONE = 1;

    protected Connection conn;
    protected PreparedStatement preparedStatement;
}

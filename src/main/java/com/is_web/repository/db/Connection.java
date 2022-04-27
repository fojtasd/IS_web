package com.is_web.repository.db;

import java.sql.*;

/**
 * MySql connection singleton.
 */
public class Connection {

    private static Connection instance = null;

    public java.sql.Connection getConnection() {
        return connection;
    }

    private java.sql.Connection connection = null;

    private Connection() {
        connect();
    }

    public static Connection getInstance() {
        if (instance == null)
            instance = new Connection();

        return instance;
    }


//    private void connect() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(
//                    "jdbc:sqlserver://localhost\\DS2MSSQL;databaseName=ds2;user=david2;password=david;"
//            );
//        } catch (Exception e) {
//
//            System.err.println("Could not establish connection to the database!");
////            System.err.println(e.getStackTrace());
//            System.err.println(e.toString());
//            System.exit(-1); // TODO handle this better
//        }
//    }

    private void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://dbsys.cs.vsb.cz\\STUDENT;databaseName=foj0105;user=FOJ0105;password=aOCE4O39xj5eV7t1;"
            );
        } catch (Exception e) {

            System.err.println("Could not establish connection to the database!");
//            System.err.println(e.getStackTrace());
            System.err.println(e.toString());
            System.exit(-1); // TODO handle this better
        }
    }

}
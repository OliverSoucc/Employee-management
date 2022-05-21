package com.oliversouc.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DBConnector {
        private SQLServerDataSource dataSource;

    public DBConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("oliversouc_JDBCTest");
        dataSource.setUser("CSe21B_26");
        dataSource.setPassword("CSe21B_26");
        dataSource.setPortNumber(1433);

    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

//    public static void main(String[] args) throws SQLException {
//
//        DBConnector databaseConnector = new DBConnector();
//        Connection connection = databaseConnector.getConnection();
//
//        System.out.println("IS it open?" + connection.isClosed());
//
//        connection.close();
//    }
}

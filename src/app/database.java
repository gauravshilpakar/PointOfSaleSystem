package app;

import java.sql.*;

class database {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "demo";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "mypasswd";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
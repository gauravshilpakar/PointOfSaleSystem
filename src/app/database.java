package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class Database {
    private static Connection connect() throws Exception {
        Connection conn = null;
        String url = "jdbc:sqlite:data/store.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS INVENTORY (\n" + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n" + "	stock integer,\n" + "	price double(4,2)\n" + ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table Has been Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addItems(String name, int stock, double price) throws Exception {
        ResultSet result = checkItems(name);
        String checkName = null;
        while (result.next()) {
            checkName = result.getString("name");
        }
        if (checkName == name) {
            System.out.println("Updating...");
            updateItems(name, stock, price, result);
        } else {
            System.out.println("Adding...");
            String sql = "INSERT INTO INVENTORY (name,stock,price) VALUES(?,?,?);";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, stock);
                pstmt.setDouble(3, price);
                pstmt.executeUpdate();
                System.out.println(name + "(" + stock + ")" + " at price $" + price + " added to inventory.");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updateItems(String name, int stock, double price, ResultSet result) throws Exception {

        int oldId = 0;
        String oldName = null;
        int oldStock = 0;
        double oldPrice = 0;
        while (result.next()) {
            oldId = result.getInt("id");
            oldName = result.getString("name");
            oldStock = result.getInt("stock");
            oldPrice = result.getDouble("price");
        }
        String sql = String.format("UPDATE INVENTORY SET name = '%s' , stock = %d, price = %f WHERE id = %d;", oldName,
                stock + oldStock, price, oldId);
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            // pstmt.setString(1, name);
            // pstmt.setInt(2, stock + oldStock);
            // pstmt.setDouble(3, price);
            // pstmt.setInt(3, oldId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static ResultSet checkItems(String name) throws Exception {
        String sql = "SELECT * FROM INVENTORY WHERE NAME= '" + name + "';";
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // return null;
        // }
    }

    public static void deleteAll() {
        String sql = "DELETE FROM INVENTORY;";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        deleteAll();
        createTable();
        addItems("fanta", 10, 2.5);
        addItems("coke", 2, 3.5);
        // ResultSet rs = checkItems("coke");
        // while (rs.next()) {
        // System.out.println(rs.getInt("id"));
        // System.out.println(rs.getString("name"));
        // System.out.println(rs.getInt("stock"));
        // System.out.println(rs.getDouble("price"));
        // }
        updateItems("coke", 10, 5, checkItems("coke"));
        // updateItems("fanta", 10, 3, checkItems("fanta"));
    }
}

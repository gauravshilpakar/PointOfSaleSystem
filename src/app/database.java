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
    // Database() throws Exception {
    // Connection conn = connect();
    // }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS INVENTORY (\n" + "	id integer PRIMARY KEY,\n"
                + "	name  text NOT NULL,\n" + "	stock integer,\n" + "	price double(4,2)\n" + ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.close();
            conn.close();
            System.out.println("Table Has been Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sqlSales = "CREATE TABLE IF NOT EXISTS SALES (\n" + "	id integer PRIMARY KEY,\n"
                + "	name  text NOT NULL,\n" + "	stock integer,\n" + "	price double(4,2)\n" + ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlSales);
            stmt.close();
            conn.close();
            System.out.println("Table Has been Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addItems(String name, int stock, double price, String tableName) throws Exception {
        ResultSet result = checkItems(name);
        String checkName = "";
        while (result.next()) {
            checkName = result.getString("name");
        }
        if (checkName != "") {
            System.out.println("\nUpdating...");
            updateItems(name, stock, price, checkItems(name), tableName, true);
        } else {
            System.out.println("\nAdding...");
            String sql = String.format("INSERT INTO %s(name,stock, price) VALUES(?,?,?);", tableName);
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, stock);
                pstmt.setDouble(3, price);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
                System.out.println(name + " (" + stock + ")" + " at price $" + price + " added to inventory.");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updateItems(String name, int stock, double price, ResultSet result, String tableName,
            boolean addition) throws Exception {

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
        if (addition) {
            stock += oldStock;
        }
        String sql = String.format("UPDATE %s SET name = '%s' , stock = %d, price = %f WHERE id = %d;", tableName,
                oldName, stock, price, oldId);
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            System.out.println("DB Updated");
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
    }

    public static void deleteAll(String tableName) {
        String sql = String.format("DELETE FROM %s;", tableName);
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet checkAll(String tableName) throws Exception {
        String sql = String.format("SELECT * FROM '%s';", tableName);
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    public static void startup() throws Exception {
        deleteAll("INVENTORY");
        deleteAll("SALES");
        createTable();
        addItems("fanta", 10, 2.89, "INVENTORY");
        addItems("coke", 2, 2.89, "INVENTORY");
        addItems("coke", 2, 3.5, "INVENTORY");
        addItems("lays", 40, 1.89, "INVENTORY");
        addItems("cheetos", 40, 1.89, "INVENTORY");
        addItems("budweiser", 10, 18.99, "INVENTORY");
        ResultSet rs = checkItems("budweiser");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }

    public static void main(String[] args) throws Exception {
        startup();
    }
}

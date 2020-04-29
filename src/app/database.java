package app;

import java.io.OutputStream;
import java.io.PrintStream;
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
        String sql = "CREATE TABLE IF NOT EXISTS INVENTORY (id integer PRIMARY KEY,\nname text NOT NULL,\nstock integer,\nprice double(4,2)\n);";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.close();
            conn.close();
            System.out.println("Table Has been Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sqlSales = "CREATE TABLE IF NOT EXISTS SALES (id integer PRIMARY KEY,\nname text NOT NULL,\nstock integer,\nprice double(4,2),\ntransactionType text\n);";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlSales);
            stmt.close();
            conn.close();
            System.out.println("Table Has been Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String sqlCredit = "CREATE TABLE IF NOT EXISTS CREDITSALES (\n " + "	id integer PRIMARY KEY,\n"
                + "	card_no integer NOT NULL,\n" + " expiry_date text NOT NULL,\n" + "	zipcode integer,\n"
                + "  totalPrice double(4,2)\n" + ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlCredit);
            stmt.close();
            conn.close();
            System.out.println("Table Has been Created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addItems(String name, int stock, double price, String tableName) throws Exception {
        ResultSet result = checkItems(name, tableName);
        String checkName = "";
        while (result.next()) {
            checkName = result.getString("name");
        }
        if (checkName != "") {
            System.out.println("\nUpdating...");
            updateItems(name, stock, price, checkItems(name, tableName), tableName, true);
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
                if (tableName == "INVENTORY") {
                    System.out.println(name + " (" + stock + ")" + " at price $" + price + " added to inventory.");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addItemsSales(String name, int stock, double price, String tableName, String transactionType)
            throws Exception {
        // System.out.println("\nAdding...");
        String sql = String.format("INSERT INTO %s(name,stock, price, transactionType) VALUES(?,?,?,?);", tableName);
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, stock);
            pstmt.setDouble(3, price);
            pstmt.setString(4, transactionType);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            // if (tableName == "INVENTORY") {
            // System.out.println(name + " (" + stock + ")" + " at price $" + price + "
            // added to inventory.");
            // }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateItems(String name, int stock, double price, ResultSet result, String tableName,
            boolean addition) throws Exception {

        int oldId = 0;
        String oldName = null;
        int oldStock = 0;
        while (result.next()) {
            oldId = result.getInt("id");
            oldName = result.getString("name");
            oldStock = result.getInt("stock");
            result.getDouble("price");
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

    public static ResultSet checkItems(String name, String tableName) throws Exception {
        String sql = String.format("SELECT * FROM %s WHERE NAME= '%s';", tableName, name);
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

    public static void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
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

    public static void creditTransaction(int card_no, String expiry_date, int zipcode, double totalPrice,
            String tableName) throws Exception {

        System.out.println("\nAdding...");
        String sql = String.format("INSERT INTO %s(card_no,expiry_date,zipcode,totalPrice) VALUES(?,?,?,?);",
                tableName);
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, card_no);
            pstmt.setString(2, expiry_date);
            pstmt.setInt(3, zipcode);
            pstmt.setDouble(4, totalPrice);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            System.out.println("Credit Card Transaction Successful!!");
            System.out.println("Thank You for Shopping with Us.");

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

    private static PrintStream realSystemOut = System.out;

    private static class NullOutputStream extends OutputStream {
        @Override
        public void write(int b) {
            return;
        }

        @Override
        public void write(byte[] b) {
            return;
        }

        @Override
        public void write(byte[] b, int off, int len) {
            return;
        }

        public NullOutputStream() {
        }
    }

    public static void startup() throws Exception {
        System.setOut(new PrintStream(new NullOutputStream()));
        dropTable("INVENTORY");
        dropTable("SALES");
        dropTable("CREDITSALES");
        createTable();
        addItems("fanta", 10, 2.89, "INVENTORY");
        addItems("coke", 2, 2.89, "INVENTORY");
        addItems("coke", 2, 3.5, "INVENTORY");
        addItems("lays", 40, 1.89, "INVENTORY");
        addItems("cheetos", 40, 1.89, "INVENTORY");
        addItems("budweiser", 10, 18.99, "INVENTORY");
        System.setOut(realSystemOut);
    }

    public static void main(String[] args) throws Exception {
        startup();
    }
}

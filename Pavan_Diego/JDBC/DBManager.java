// DBManager.java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager implements AutoCloseable {
    private static final String DB_URL = "jdbc:mysql://localhost/alunno";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection conn;

    public DBManager() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public int insertElement(String name, String surname, int age) throws SQLException {
        String sql = "INSERT INTO data (ID, Name, Surname, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String ID = generateID(name, surname, age);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setInt(4, age);
            return pstmt.executeUpdate();
        }
    }

    public List<String> viewAll() throws SQLException {
        List<String> results = new ArrayList<>();
        String sql = "SELECT * FROM data";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                results.add(rs.getString("ID") + ", " + rs.getString("Name") + ", " + rs.getString("Surname") + ", " + rs.getInt("age"));
            }
        }
        return results;
    }

    public int updateElement(String ID, String name, String surname, int age) throws SQLException {
        String sql = "UPDATE data SET Name=?, Surname=?, age=? WHERE ID=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setInt(3, age);
            pstmt.setString(4, ID);
            return pstmt.executeUpdate();
        }
    }

    public int deleteElement(String ID) throws SQLException {
        String sql = "DELETE FROM data WHERE ID=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ID);
            return pstmt.executeUpdate();
        }
    }

    public int countStudentsAboveAge(int age) throws SQLException {
        String sql = "SELECT COUNT(*) FROM data WHERE age > ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, age);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public double averageAge() throws SQLException {
        String sql = "SELECT AVG(age) FROM data";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0;
    }
    private String generateID(String name, String surname, int age) {
        String namePart = name.length() >= 2 ? name.substring(0, 2) : name;
        String surnamePart = surname.length() >= 2 ? surname.substring(0, 2) : surname;
    
        return namePart + surnamePart + age;
    }

    // ... Other methods for the remaining functionalities ...

    @Override
    public void close() throws SQLException {
       if (conn != null) {
          conn.close();
       }
    }
}

package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSqlUtils {

    public static void insertAuthor(Connection connection, String name, String bio) throws SQLException {
        String sql = "INSERT INTO Authors (Name, Bio) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, bio);
            pstmt.executeUpdate();
        }
    }

    public static boolean isAuthorPresent(Connection connection, String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Authors WHERE Name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        }
    }

    public static void deleteAuthor(Connection connection, String name) throws SQLException {
        String sql = "DELETE FROM Authors WHERE Name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

}
package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSqlUtils {
//TODO de explicat  try resurs
    public static List<String> getBooksByAuthor(Connection connection, String authorName) throws SQLException {
        String sql = "SELECT Books.Title FROM Books " +
                "JOIN Authors ON Books.Author_ID = Authors.Author_ID " +
                "WHERE Authors.Name = ?";
        List<String> bookTitles = new ArrayList<>();
        //TODO  de afalt de ce 2 try
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, authorName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookTitles.add(rs.getString("Title"));
                }
            }
        }
        return bookTitles;
    }
}

import java.sql.*;

public class EmployeeLoginAuthenticator {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/sjuhealthservices";
    private static final String USER = "root";
    private static final String PASS = "umargul";

    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM employee WHERE username = ? AND password = ? AND role = 'employee'";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

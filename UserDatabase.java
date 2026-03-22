import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class UserDatabase {

    static void registerUser(User u) {

        String query = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, u.getUsername());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getRole());

            pst.executeUpdate();
            System.out.println("\nRegistration successful");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static User login(String username, String password) {

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return null;
    }

    static boolean userExists(String username) {

        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return false;
    }

    static void addAddress(String username, String address) {

        String query = "INSERT INTO user_addresses(username, address) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, address);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static String[] getAddresses(String username) {

        String query = "SELECT address FROM user_addresses WHERE username = ?";
        String[] addresses = new String[20];
        int count = 0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next() && count < addresses.length) {
                addresses[count++] = rs.getString("address");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = addresses[i];
        }

        return result;
    }
}
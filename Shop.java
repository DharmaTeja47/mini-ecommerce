import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Shop {

    static void addProduct(Product p) {

        String query = "INSERT INTO products(name, category, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, p.getName());
            pst.setString(2, p.getCategory());
            pst.setDouble(3, p.getPrice());
            pst.setInt(4, p.getStock());

            pst.executeUpdate();
            System.out.println("\nProduct added successfully");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void displayProducts() {

        String query = "SELECT * FROM products";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            boolean found = false;

            System.out.println("\nID    Name                 Category        Price     Stock");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                found = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                String status = stock == 0 ? "Out Of Stock" : String.valueOf(stock);

                System.out.printf("%-5d %-20s %-15s Rs.%-8.2f %-10s%n",
                        id, name, category, price, status);
            }

            if (!found) {
                System.out.println("No products available");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void searchProduct(String keyword) {

        String query = "SELECT * FROM products WHERE LOWER(name) LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = pst.executeQuery();

            boolean found = false;

            System.out.println("\nSearch Results:");
            System.out.println("ID    Name                 Category        Price     Stock");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                found = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                String status = stock == 0 ? "Out Of Stock" : String.valueOf(stock);

                System.out.printf("%-5d %-20s %-15s Rs.%-8.2f %-10s%n",
                        id, name, category, price, status);
            }

            if (!found) {
                System.out.println("No matching products found");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static Product getProductById(int productId) {

        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return null;
    }

    static void updateStock(int productId, int purchasedQty) {

        String query = "UPDATE products SET stock = stock - ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, purchasedQty);
            pst.setInt(2, productId);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
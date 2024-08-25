package in.co.hsbc.ecommerceApp.dao.impl;

import in.co.hsbc.ecommerceApp.dao.AdminDao;
import in.co.hsbc.ecommerceApp.entity.Admin;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.util.DBConnection;
import in.co.hsbc.ecommerceApp.exception.AdminNotFoundException;
import in.co.hsbc.ecommerceApp.exception.DatabaseException;
import in.co.hsbc.ecommerceApp.exception.ProductNotFoundException;
import in.co.hsbc.ecommerceApp.exception.SubscriptionNotFoundException;
import in.co.hsbc.ecommerceApp.exception.OrderNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public void addSubscription(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (customer_id, product_id, frequency, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, subscription.getCustomerId());
            ps.setInt(2, subscription.getProductId());
            ps.setString(3, subscription.getFrequency());
            ps.setString(4, subscription.getStartDate());
            ps.setString(5, subscription.getEndDate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding subscription: " + e.getMessage());
        }
    }

    @Override
    public Admin getAdminById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM admins WHERE admin_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                return admin;
            } else {
                throw new AdminNotFoundException("Admin with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while fetching admin data.", e);
        }
    }

    @Override
    public void addProduct(Product product) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO products (name, price, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error while adding product.", e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE products SET name = ?, price = ?, description = ? WHERE product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getProductId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ProductNotFoundException("Product with ID " + product.getProductId() + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while updating product.", e);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ProductNotFoundException("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while deleting product.", e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM products";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DatabaseException("Error while fetching products.", e);
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM subscriptions";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setSubscriptionId(rs.getInt("subscription_id"));
                subscription.setCustomerId(rs.getInt("customer_id"));
                subscription.setProductId(rs.getInt("product_id"));
                subscription.setFrequency(rs.getString("frequency"));
                subscription.setStartDate(rs.getString("start_date"));
                subscription.setEndDate(rs.getString("end_date"));
                subscriptions.add(subscription);
            }
            return subscriptions;
        } catch (SQLException e) {
            throw new DatabaseException("Error while fetching subscriptions.", e);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM orders";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setSubscriptionId(rs.getInt("subscription_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setDeliveryDate(rs.getString("delivery_date"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DatabaseException("Error while fetching orders.", e);
        }
    }
}

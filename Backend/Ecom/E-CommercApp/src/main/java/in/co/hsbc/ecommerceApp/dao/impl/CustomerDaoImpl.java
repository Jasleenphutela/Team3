package in.co.hsbc.ecommerceApp.dao.impl;

import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.util.DBConnection;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import in.co.hsbc.ecommerceApp.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {



    @Override
    public void cancelSubscription(int subscriptionId) {
        String sql = "UPDATE subscriptions SET status = 'Cancelled' WHERE subscription_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, subscriptionId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error cancelling subscription: " + e.getMessage());
        }
    }


    @Override
    public Customer getCustomerById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                return customer;
            } else {
                throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while fetching customer data.", e);
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO customers (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error while adding customer.", e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE customers SET name = ?, email = ?, password = ? WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setInt(4, customer.getCustomerId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new CustomerNotFoundException("Customer with ID " + customer.getCustomerId() + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while updating customer.", e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while deleting customer.", e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM customers";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new DatabaseException("Error while fetching customers.", e);
        }
    }

    @Override
    public void placeOrder(Order order) {
        String sql = "INSERT INTO orders (subscription_id, delivery_date, status) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order.getSubscriptionId());
            ps.setString(2, order.getDeliveryDate());
            ps.setString(3, order.getStatus());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error placing order: " + e.getMessage());
        }
    }
}

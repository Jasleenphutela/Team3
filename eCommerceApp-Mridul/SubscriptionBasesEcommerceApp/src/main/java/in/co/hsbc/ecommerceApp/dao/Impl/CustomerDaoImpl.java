package in.co.hsbc.ecommerceApp.dao.Impl;

import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import in.co.hsbc.ecommerceApp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customers (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.executeUpdate();
        }
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException, CustomerNotFoundException {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCustomer(rs);
                } else {
                    throw new CustomerNotFoundException("no customer found with given id"); // **Changed**
                }
            }
        }
    }
    @Override
    public Customer getCustomerByEmail(String email) throws SQLException, CustomerNotFoundException {
        String query = "SELECT * FROM customers WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCustomer(rs);
                } else {
                    throw new CustomerNotFoundException("Customer not found with email: " + email); // **Changed**
                }
            }
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE customers SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setInt(4, customer.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            getCustomerById(id);
            stmt.setInt(1, id);
           stmt.executeUpdate();

        } catch (CustomerNotFoundException e) {
           e.printStackTrace();
        }
    }

    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        return customer;
    }
}


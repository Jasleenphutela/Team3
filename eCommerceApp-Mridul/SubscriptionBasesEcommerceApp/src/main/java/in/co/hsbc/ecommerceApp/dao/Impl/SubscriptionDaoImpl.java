package in.co.hsbc.ecommerceApp.dao.Impl;
import in.co.hsbc.ecommerceApp.dao.SubscriptionDao;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import in.co.hsbc.ecommerceApp.exception.SubscriptionNotFoundException;
import in.co.hsbc.ecommerceApp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    @Override
    public void addSubscription(Subscription subscription) throws SQLException {
        String query = "INSERT INTO subscriptions (customer_id, product_id, plan, start_date, end_date, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, subscription.getPlan());
            stmt.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setBoolean(6, subscription.isActive());
            stmt.executeUpdate();
        }
    }


    @Override
    public Subscription getSubscriptionById(int id) throws SQLException, SubscriptionNotFoundException {
        String query = "SELECT * FROM subscriptions WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToSubscription(rs);
                } else {
                    throw new SubscriptionNotFoundException("Subscription not found with ID: " + id); // **Changed**
                }
            }
        }
    }


    @Override
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException, CustomerNotFoundException {
        String query = "SELECT * FROM subscriptions WHERE customer_id = ?";
        List<Subscription> subscriptions = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if(!rs.next()) {
                    throw new CustomerNotFoundException("Customer not found with ID: " + customerId); // **Changed**
                }
                while (rs.next()) {
                    subscriptions.add(mapRowToSubscription(rs));
                }
            }

        }
        return subscriptions;
    }

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        String query = "UPDATE subscriptions SET customer_id = ?, product_id = ?, plan = ?, start_date = ?, end_date = ?, is_active = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, subscription.getPlan());
            stmt.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setBoolean(6, subscription.isActive());
            stmt.setInt(7, subscription.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void cancelSubscription(int id) throws SQLException, SubscriptionNotFoundException {
        String query = "UPDATE subscriptions SET is_active = false WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            getSubscriptionById(id);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Subscription mapRowToSubscription(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getInt("id"));
        subscription.setCustomerId(rs.getInt("customer_id"));
        subscription.setProductId(rs.getInt("product_id"));
        subscription.setPlan(rs.getString("plan"));
        subscription.setStartDate(rs.getDate("start_date"));
        subscription.setEndDate(rs.getDate("end_date"));
        subscription.setActive(rs.getBoolean("is_active"));
        return subscription;
    }
}

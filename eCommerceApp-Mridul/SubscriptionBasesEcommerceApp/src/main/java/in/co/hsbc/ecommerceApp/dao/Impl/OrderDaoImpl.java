package in.co.hsbc.ecommerceApp.dao.Impl;

import in.co.hsbc.ecommerceApp.dao.OrderDao;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.exception.OrderNotFoundException;
import in.co.hsbc.ecommerceApp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (subscription_id, order_date, delivery_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getSubscriptionId());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getDeliveryDate().getTime()));
            stmt.setString(4, order.getStatus());
            stmt.executeUpdate();
        }
    }

    @Override
    public Order getOrderById(int id) throws SQLException, OrderNotFoundException {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToOrder(rs);
                } else {
                    throw new OrderNotFoundException("Order not found with ID: " + id); // **Changed**
                }
            }
        }
    }

    @Override
    public List<Order> getOrdersBySubscriptionId(int subscriptionId) throws SQLException, OrderNotFoundException {
        String query = "SELECT * FROM orders WHERE subscription_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subscriptionId);
            try (ResultSet rs = stmt.executeQuery()) {
               if(!rs.next())
                {
                    throw new OrderNotFoundException("Order not found with ID: " + subscriptionId);
                }
                while (rs.next()) {
                    orders.add(mapRowToOrder(rs));
                }
            }

        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        String query = "UPDATE orders SET subscription_id = ?, order_date = ?, delivery_date = ?, status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getSubscriptionId());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getDeliveryDate().getTime()));
            stmt.setString(4, order.getStatus());
            stmt.setInt(5, order.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException, OrderNotFoundException {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            getOrderById(id);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Order mapRowToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setSubscriptionId(rs.getInt("subscription_id"));
        order.setOrderDate(rs.getDate("order_date"));
        order.setDeliveryDate(rs.getDate("delivery_date"));
        order.setStatus(rs.getString("status"));
        return order;
    }
}

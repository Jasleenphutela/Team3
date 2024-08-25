package in.co.hsbc.ecommerceApp.dao.impl;

import in.co.hsbc.ecommerceApp.dao.AdminDao;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.util.DBConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class AdminDaoImplTest {

    private AdminDao adminDao = new AdminDaoImpl();

    @BeforeEach
    public void setUp() {
        adminDao = new AdminDaoImpl();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Clean up tables before each test
            stmt.executeUpdate("DELETE FROM products");
            stmt.executeUpdate("DELETE FROM subscriptions");
            stmt.executeUpdate("DELETE FROM orders");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setCustomerId(1);
        subscription.setProductId(1);
        subscription.setFrequency("weekly");
        subscription.setStartDate("2024-01-01");
        subscription.setEndDate("2024-12-31");

        adminDao.addSubscription(subscription);

        List<Subscription> subscriptions = adminDao.getAllSubscriptions();
        Assertions.assertFalse(subscriptions.isEmpty());
    }

    @Test
    public void testAddOrder() {
        Order order = new Order();
        order.setSubscriptionId(1);
        order.setDeliveryDate("2024-01-08");
        order.setStatus("pending");

        adminDao.addOrder(order);

        List<Order> orders = adminDao.getAllOrders();
        Assertions.assertFalse(orders.isEmpty());
    }
}
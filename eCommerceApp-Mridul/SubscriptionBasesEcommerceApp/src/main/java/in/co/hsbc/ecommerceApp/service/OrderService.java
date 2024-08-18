package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void addOrder(Order order) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getOrdersBySubscriptionId(int subscriptionId) throws SQLException;
    void updateOrder(Order order) throws SQLException;
    void deleteOrder(int id) throws SQLException;
}

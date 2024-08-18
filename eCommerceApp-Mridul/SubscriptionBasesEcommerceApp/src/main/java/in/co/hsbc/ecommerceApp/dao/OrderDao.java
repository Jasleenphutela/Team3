package in.co.hsbc.ecommerceApp.dao;

import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.exception.OrderNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void addOrder(Order order) throws SQLException;
    Order getOrderById(int id) throws SQLException, OrderNotFoundException;
    List<Order> getOrdersBySubscriptionId(int subscriptionId) throws SQLException, OrderNotFoundException;
    void updateOrder(Order order) throws SQLException;
    void deleteOrder(int id) throws SQLException, OrderNotFoundException;
}

package in.co.hsbc.ecommerceApp.service.Impl;

import in.co.hsbc.ecommerceApp.dao.OrderDao;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.exception.OrderNotFoundException;
import in.co.hsbc.ecommerceApp.service.OrderService;
import in.co.hsbc.ecommerceApp.util.BeanFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDAO = BeanFactory.getOrderDAO();

    @Override
    public void addOrder(Order order) throws SQLException {
        orderDAO.addOrder(order);
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        try {
            return orderDAO.getOrderById(id);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
            return new Order();
        }
    }

    @Override
    public List<Order> getOrdersBySubscriptionId(int subscriptionId) throws SQLException {
        try {
            return orderDAO.getOrdersBySubscriptionId(subscriptionId);
        }
        catch (OrderNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        try {
            orderDAO.deleteOrder(id);
        }
        catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
    }
}

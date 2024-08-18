package test;

import in.co.hsbc.ecommerceApp.dao.Impl.OrderDaoImpl;
import in.co.hsbc.ecommerceApp.exception.OrderNotFoundException;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import in.co.hsbc.ecommerceApp.entity.Order;

public class OrderDaoImplTest {

    private OrderDaoImpl dao = new OrderDaoImpl();

    @Test
    public void addOrderTest() throws SQLException {
        dao.addOrder(new Order(1, 1,new Date(), new Date(), "Pending"));
    }

    @Test
    public void getOrderByIdTest() throws SQLException, OrderNotFoundException {
        Order order = dao.getOrderById(1);
        System.out.println(order);
    }

    @Test
    public void getOrdersBySubscriptionIdTest() throws SQLException, OrderNotFoundException {
        List<Order> orders = dao.getOrdersBySubscriptionId(1);
        orders.forEach(System.out::println);
    }

    @Test
    public void updateOrderTest() throws SQLException, OrderNotFoundException {
        Order order = dao.getOrderById(1);
        order.setStatus("Shipped");
        dao.updateOrder(order);
    }

    @Test
    public void deleteOrderTest() throws SQLException, OrderNotFoundException {
        dao.deleteOrder(1);
    }
}

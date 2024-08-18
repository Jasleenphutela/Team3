package test;

import in.co.hsbc.ecommerceApp.service.Impl.OrderServiceImpl;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import in.co.hsbc.ecommerceApp.entity.Order;

public class OrderServiceImplTest {

    private OrderServiceImpl service = new OrderServiceImpl();

    @Test
    public void addOrderTest() throws SQLException {
        service.addOrder(new Order(1, 1,new Date(), new Date(), "Pending"));
    }

    @Test
    public void getOrderByIdTest() throws SQLException {
        Order order = service.getOrderById(1);
        System.out.println(order);
    }

    @Test
    public void getOrdersBySubscriptionIdTest() throws SQLException {
        List<Order> orders = service.getOrdersBySubscriptionId(1);
        orders.forEach(System.out::println);
    }

    @Test
    public void updateOrderTest() throws SQLException {
        Order order = service.getOrderById(1);
        order.setStatus("Shipped");
        service.updateOrder(order);
    }

    @Test
    public void deleteOrderTest() throws SQLException {
        service.deleteOrder(1);
    }
}

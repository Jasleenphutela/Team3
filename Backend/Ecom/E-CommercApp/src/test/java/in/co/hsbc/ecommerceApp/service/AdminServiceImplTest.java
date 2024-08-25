package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.dao.AdminDao;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    private AdminService adminService;
    private AdminDao adminDao;

    @BeforeEach
    public void setUp() {
        adminDao = mock(AdminDao.class);
        adminService = new AdminServiceImpl(adminDao);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Organic Apples");
        product.setPrice(3.99);
        product.setDescription("Fresh organic apples.");

        adminService.addProduct(product);
        verify(adminDao).addProduct(product);
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setCustomerId(1);
        subscription.setProductId(1);
        subscription.setFrequency("weekly");
        subscription.setStartDate("2024-01-01");
        subscription.setEndDate("2024-12-31");

        adminService.addSubscription(subscription);
        verify(adminDao).addSubscription(subscription);
    }

    @Test
    public void testGetAllSubscriptions() {
        // Implement a mock test for getAllSubscriptions
    }

    @Test
    public void testGetAllOrders() {
        // Implement a mock test for getAllOrders
    }
}
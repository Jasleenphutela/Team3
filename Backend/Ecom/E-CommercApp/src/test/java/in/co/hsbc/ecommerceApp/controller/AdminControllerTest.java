package in.co.hsbc.ecommerceApp.controller;


import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.service.AdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminControllerTest {

    private AdminController adminController;
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        adminService = Mockito.mock(AdminService.class);
        adminController = new AdminController(adminService);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Organic Apples");
        product.setPrice(3.99);
        product.setDescription("Fresh organic apples.");

        adminController.addProduct(product);
        Mockito.verify(adminService).addProduct(product);
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setCustomerId(1);
        subscription.setProductId(1);
        subscription.setFrequency("weekly");
        subscription.setStartDate("2024-01-01");
        subscription.setEndDate("2024-12-31");

        adminController.addSubscription(subscription);
        Mockito.verify(adminService).addSubscription(subscription);
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
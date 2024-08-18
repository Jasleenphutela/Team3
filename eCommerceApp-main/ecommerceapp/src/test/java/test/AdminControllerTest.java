package test;

import in.co.hsbc.ecommerceApp.adminController.AdminController;
import in.co.hsbc.ecommerceApp.beanfactory.BeanFactory;
import in.co.hsbc.ecommerceApp.entity.Admin;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.RecurringSubscription;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;
import in.co.hsbc.ecommerceApp.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AdminControllerTest {

    private BeanFactory factory;
    private AdminController controller;
    private AdminService adminService;
    private PrintStream originalOut;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        factory = new BeanFactory();
        controller = factory.getController();
        adminService = factory.getService();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testInsertProductSuccess() {
        Admin admin = Admin.getInstance(1);
        Product product = new Product(1, "Product A", 100.0);
        controller.insertProduct(admin, product.getId(), product.getName(), product.getPrice());
        assertEquals("Product Added Successfully !\n", outputStream.toString().trim());
    }

    @Test
    void testInsertProductUnauthorizedAccess() {
        Object nonAdmin = new Object();
        Product product = new Product(1, "Product A", 100.0);
        controller.insertProduct(nonAdmin, product.getId(), product.getName(), product.getPrice());
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }

    @Test
    void testUpdateProductSuccess() {
        Admin admin = Admin.getInstance(1)
        ;
        Product product = new Product(1, "Product B", 150.0);
        controller.updateProduct(admin, product.getId(), product.getName(), product.getPrice());
        assertEquals("Updation Successful\n", outputStream.toString().trim());
    }

    @Test
    void testUpdateProductUnauthorizedAccess() {
        Object nonAdmin = new Object();
        Product product = new Product(1, "Product B", 150.0);
        controller.updateProduct(nonAdmin, product.getId(), product.getName(), product.getPrice());
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }

    @Test
    void testDeleteProductSuccess() {
        Admin admin = Admin.getInstance(1);
        int productId = 1;
        controller.deleteProduct(admin, productId);
        assertEquals("Deletion Successful\n", outputStream.toString().trim());
    }

    @Test
    void testDeleteProductUnauthorizedAccess() {
        Object nonAdmin = new Object();
        int productId = 1;
        controller.deleteProduct(nonAdmin, productId);
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }

    @Test
    void testInsertSubscriptionSuccess() {
        Admin admin = Admin.getInstance(1)
;
        Subscriptions subscription = new RecurringSubscription(30);
        int productId = 1;
        controller.insertSubscription(admin, productId, 30, 1);
        assertEquals("Subscription Added Successfully\n", outputStream.toString().trim());
    }

    @Test
    void testInsertSubscriptionUnauthorizedAccess() {
        Object nonAdmin = new Object();
        Subscriptions subscription = new RecurringSubscription(30);
        int productId = 1;
        controller.insertSubscription(nonAdmin, productId, 30, 1);
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }

    @Test
    void testSubChangeSubscriptionStatusSuccess() {
        Admin admin = Admin.getInstance(1)
;
        int productId = 1;
        String type = "Type1";
        controller.subChangeSubscriptionStatus(admin, productId, type);
        assertEquals("Subscription Status Changed Successfully !\n", outputStream.toString().trim());
    }

    @Test
    void testSubChangeSubscriptionStatusUnauthorizedAccess() {
        Object nonAdmin = new Object();
        int productId = 1;
        String type = "Type1";
        controller.subChangeSubscriptionStatus(nonAdmin, productId, type);
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }

    @Test
    void testAddChangeSubscriptionStatusSuccess() {
        Admin admin = Admin.getInstance(1)
;
        int productId = 1;
        String type = "Type1";
        controller.addChangeSubscriptionStatus(admin, productId, type);
        assertEquals("Subscription Status Changed Successfully !\n", outputStream.toString().trim());
    }

    @Test
    void testAddChangeSubscriptionStatusUnauthorizedAccess() {
        Object nonAdmin = new Object();
        int productId = 1;
        String type = "Type1";
        controller.addChangeSubscriptionStatus(nonAdmin, productId, type);
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }

    @Test
    void testViewAllSubscriptionsSuccess() {
        Admin admin = Admin.getInstance(1);
        controller.viewAllSubscriptions(admin);
        assertTrue(outputStream.toString().startsWith("Displaying All Subscriptions"));
    }

    @Test
    void testViewAllSubscriptionsUnauthorizedAccess() {
        Object nonAdmin = new Object();
        controller.viewAllSubscriptions(nonAdmin);
        assertEquals("Unauthorized Access\n", outputStream.toString().trim());
    }
}

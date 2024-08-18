package test;

import in.co.hsbc.ecommerceApp.adminDao.AdminDao;
import in.co.hsbc.ecommerceApp.adminDao.Impl.AdminJdbcDaoImpl;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;
import in.co.hsbc.ecommerceApp.model.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminJdbcDaoTest {

    private AdminDao adminDao;

    @BeforeEach
    void setUp() {
        adminDao = new AdminJdbcDaoImpl();
    }

    @Test
    void testAddProductSuccess() throws ClassNotFoundException, ProductAlreadyPresentException, ProductNotFoundException {
        Product product = new Product(1, "Product A", 100.0);
        int rowsAffected = adminDao.addProduct(product);
        assertEquals(1, rowsAffected);
    }

    @Test
    void testAddProductFailure() throws ClassNotFoundException {
        Product product = new Product(1, "Product A", 100.0);
        assertThrows(ProductAlreadyPresentException.class, () -> {
            adminDao.addProduct(product);
        });
    }

    @Test
    void testAlterProductSuccess() throws ClassNotFoundException, ProductNotFoundException {
        Product product = new Product(1, "Product B", 150.0);
        int rowsAffected = adminDao.alterProduct(product);
        assertEquals(1, rowsAffected);
    }

    @Test
    void testAlterProductFailure() throws ClassNotFoundException {
        Product product = new Product(999, "Non-existent Product", 150.0);
        assertThrows(ProductNotFoundException.class, () -> {
            adminDao.alterProduct(product);
        });
    }

    @Test
    void testEradicateProductSuccess() throws ClassNotFoundException, ProductNotFoundException {
        int productId = 1;
        int rowsAffected = adminDao.eradicateProduct(productId);
        assertEquals(1, rowsAffected);
    }

    @Test
    void testEradicateProductFailure() throws ClassNotFoundException {
        int productId = 999;
        assertThrows(ProductNotFoundException.class, () -> {
            adminDao.eradicateProduct(productId);
        });
    }

    @Test
    void testInsertSubscriptionSuccess() throws ClassNotFoundException, SubscriptionAlreadyExistsException, SubscriptionNotFoundException {
        Subscriptions subscription = new Subscriptions("Type1", "active", java.time.LocalDate.now(), 1);
        int rowsAffected = adminDao.insertSubscription(subscription, 1);
        assertEquals(1, rowsAffected);
    }

    @Test
    void testInsertSubscriptionFailure() throws ClassNotFoundException {
        Subscriptions subscription = new Subscriptions("Type1", "active", java.time.LocalDate.now(), 1);
        assertThrows(SubscriptionAlreadyExistsException.class, () -> {
            adminDao.insertSubscription(subscription, 1);
        });
    }

    @Test
    void testDeactivateSubscriptionSuccess() throws ClassNotFoundException, SubscriptionNotFoundException, SubscriptionAlreadyInactiveException {
        String subscriptionType = "Type1";
        int productId = 1;
        int rowsAffected = adminDao.deactivateSubscription(subscriptionType, productId);
        assertEquals(1, rowsAffected);
    }

    @Test
    void testDeactivateSubscriptionFailure() throws ClassNotFoundException {
        String subscriptionType = "Non-existent Type";
        int productId = 999;
        assertThrows(SubscriptionNotFoundException.class, () -> {
            adminDao.deactivateSubscription(subscriptionType, productId);
        });
    }

    @Test
    void testActivateSubscriptionSuccess() throws ClassNotFoundException, SubscriptionNotFoundException, SubscriptionAlreadyActiveException {
        String subscriptionType = "Type1";
        int productId = 1;
        int rowsAffected = adminDao.activateSubscription(subscriptionType, productId);
        assertEquals(1, rowsAffected);
    }

    @Test
    void testActivateSubscriptionFailure() throws ClassNotFoundException {
        String subscriptionType = "Non-existent Type";
        int productId = 999;
        assertThrows(SubscriptionNotFoundException.class, () -> {
            adminDao.activateSubscription(subscriptionType, productId);
        });
    }

    @Test
    void testListAllSubscriptionsSuccess() throws ClassNotFoundException, SubscriptionNotFoundException, SQLException {
        List<Subscriptions> subscriptionsList = adminDao.listAllSubscriptions();
        assertNotNull(subscriptionsList);
        assertFalse(subscriptionsList.isEmpty());
    }

    @Test
    void testListAllSubscriptionsFailure() throws ClassNotFoundException {
        assertThrows(SubscriptionNotFoundException.class, () -> {
            adminDao.listAllSubscriptions();
        });
    }
}

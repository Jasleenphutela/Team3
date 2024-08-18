package test;

import in.co.hsbc.ecommerceApp.adminController.AdminController;
import in.co.hsbc.ecommerceApp.adminDao.AdminDao;
import in.co.hsbc.ecommerceApp.adminDao.Impl.AdminJdbcDaoImpl;
import in.co.hsbc.ecommerceApp.beanfactory.BeanFactory;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;
import in.co.hsbc.ecommerceApp.model.exceptions.*;
import in.co.hsbc.ecommerceApp.service.AdminService;
import in.co.hsbc.ecommerceApp.service.Impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceTest {

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
    void testRegisterNewProductSuccess() {
        Product product = new Product(1, "Product A", 100.0);
        assertTrue(adminService.registerNewProduct(product));
        assertEquals("Product Added Successfully !\n", outputStream.toString().trim());
    }

    @Test
    void testRegisterNewProductFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()
 {
            @Override
            public int addProduct(Product p) throws ClassNotFoundException, ProductNotFoundException, ProductAlreadyPresentException {
                throw new ProductAlreadyPresentException();
            }

        };
        adminService = new AdminServiceImpl(adminDao);
        Product product = new Product(1, "Product A", 100.0);
        assertFalse(adminService.registerNewProduct(product));
        assertEquals("Product Already Exists With The Given Id !\n", outputStream.toString().trim());
    }

    @Test
    void testModifyProductSuccess() {
        Product product = new Product(1, "Product B", 150.0);
        assertTrue(adminService.modifyProduct(product));
        assertEquals("Updation Successful\n", outputStream.toString().trim());
    }

    @Test
    void testModifyProductFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()
 {
            @Override
            public int alterProduct(Product p) throws ClassNotFoundException, ProductNotFoundException {
                throw new ProductNotFoundException();
            }

        };
        adminService = new AdminServiceImpl(adminDao);
        Product product = new Product(1, "Product B", 150.0);
        assertFalse(adminService.modifyProduct(product));
        assertEquals("Product With The Given Id Does Not Exist In The Database !\n", outputStream.toString().trim());
    }

    @Test
    void testRemoveProductSuccess() {
        int productId = 1;
        assertTrue(adminService.removeProduct(productId));
        assertEquals("Deletion Successful\n", outputStream.toString().trim());
    }

    @Test
    void testRemoveProductFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()

{
            @Override
            public int eradicateProduct(int id) throws ProductNotFoundException, ClassNotFoundException {
                throw new ProductNotFoundException();
            }
        };
        adminService = new AdminServiceImpl(adminDao);
        int productId = 1;
        assertFalse(adminService.removeProduct(productId));
        assertEquals("Product Couldn't Be Found In The Database !\n", outputStream.toString().trim());
    }

    @Test
    void testRegisterNewSubscriptionSuccess() {
        Subscriptions subscription = new Subscriptions("Type1", "active", java.time.LocalDate.now(), 1);
        int productId = 1;
        assertTrue(adminService.registerNewSubscription(subscription, productId));
        assertEquals("Subscription Added Successfully\n", outputStream.toString().trim());
    }

    @Test
    void testRegisterNewSubscriptionFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()
        {         @Override
            public int insertSubscription(Subscriptions subscriptions, int id) throws ClassNotFoundException, SubscriptionAlreadyExistsException, SubscriptionNotFoundException {
                throw new SubscriptionAlreadyExistsException();
            }

        };
        adminService = new AdminServiceImpl(adminDao);
        Subscriptions subscription = new Subscriptions("Type1", "active", java.time.LocalDate.now(), 1);
        int productId = 1;
        assertFalse(adminService.registerNewSubscription(subscription, productId));
        assertEquals("Same Subscription Already Exists For The Given Product ID !\n", outputStream.toString().trim());
    }

    @Test
    void testRemoveSubscriptionSuccess() {
        String subscriptionType = "Type1";
        int productId = 1;
        assertTrue(adminService.removeSubscription(subscriptionType, productId));
        assertEquals("Subscription Status Changed Successfully !\n", outputStream.toString().trim());
    }

    @Test
    void testRemoveSubscriptionFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()
{
            @Override
            public int deactivateSubscription(String subscriptionType, int id) throws ClassNotFoundException, SubscriptionAlreadyInactiveException, SubscriptionNotFoundException {
                throw new SubscriptionAlreadyInactiveException();
            }

        };
        adminService = new AdminServiceImpl(adminDao);
        String subscriptionType = "Type1";
        int productId = 1;
        assertFalse(adminService.removeSubscription(subscriptionType, productId));
        assertEquals("Subscription Is Already In Inactive State !\n", outputStream.toString().trim());
    }

    @Test
    void testAddSubscriptionSuccess() {
        String subscriptionType = "Type1";
        int productId = 1;
        assertTrue(adminService.addSubscription(subscriptionType, productId));
        assertEquals("Subscription Status Changed Successfully !\n", outputStream.toString().trim());
    }

    @Test
    void testAddSubscriptionFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()
 {
            @Override
            public int activateSubscription(String subscriptionType, int id) throws SubscriptionAlreadyActiveException, SubscriptionNotFoundException, ClassNotFoundException {
                throw new SubscriptionAlreadyActiveException();
            }

        };
        adminService = new AdminServiceImpl(adminDao);
        String subscriptionType = "Type1";
        int productId = 1;
        assertFalse(adminService.addSubscription(subscriptionType, productId));
        assertEquals("Subscription Is Already In Active State !\n", outputStream.toString().trim());
    }

    @Test
    void testDisplayAllSubscriptionsSuccess() {
        assertTrue(adminService.displayAllSubscriptions());
        assertTrue(outputStream.toString().startsWith("Displaying All Subscriptions"));
    }

    @Test
    void testDisplayAllSubscriptionsFailure() {
        AdminDao adminDao = new AdminJdbcDaoImpl()
 {
            @Override
            public List<Subscriptions> listAllSubscriptions() throws ClassNotFoundException, SubscriptionNotFoundException {
                throw new SubscriptionNotFoundException();
            }

        };
        adminService = new AdminServiceImpl(adminDao);
        assertFalse(adminService.displayAllSubscriptions());
        assertEquals("Subscriptions Can't Be Found !\n", outputStream.toString().trim());
    }
}

package test;

import in.co.hsbc.ecommerceApp.controller.New_CustomerController;
import org.junit.Test;

public class New_CustomerControllerTest {

    private New_CustomerController controller = new New_CustomerController();

    @Test
    public void registerCustomerTest() {
        controller.registerCustomer("John Doe", "john.doe@example.com", "password123");
    }

    @Test
    public void browseProductsTest() {
        controller.browseProducts();
    }

    @Test
    public void subscribeToProductTest() {
        controller.subscribeToProduct(1, 2, "Basic", "2024-08-01", "2025-08-01");
    }

    @Test
    public void viewSubscriptionsTest() {
        controller.viewSubscriptions(1);
    }

    @Test
    public void cancelSubscriptionTest() {
        controller.cancelSubscription(1);
    }
}

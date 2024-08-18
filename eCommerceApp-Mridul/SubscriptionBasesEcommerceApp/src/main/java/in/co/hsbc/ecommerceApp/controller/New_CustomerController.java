package in.co.hsbc.ecommerceApp.controller;

import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.service.CustomerService;
import in.co.hsbc.ecommerceApp.service.ProductService;
import in.co.hsbc.ecommerceApp.service.SubscriptionService;
import in.co.hsbc.ecommerceApp.util.BeanFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class New_CustomerController {
    private CustomerService customerService = BeanFactory.getCustomerService();
    private ProductService productService = BeanFactory.getProductService();
    private SubscriptionService subscriptionService = BeanFactory.getSubscriptionService();

    public void registerCustomer(String name, String email, String password) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);

        try {
            customerService.registerCustomer(customer);
            System.out.println("Customer registered successfully.");
        } catch (SQLException e) {
            System.err.println("Error registering customer: " + e.getMessage());
        }
    }

    public void browseProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
    }

    public void subscribeToProduct(int customerId, int productId, String plan, String startDate, String endDate) {
        Subscription subscription = new Subscription();
        subscription.setCustomerId(customerId);
        subscription.setProductId(productId);
        subscription.setPlan(plan);
        subscription.setStartDate(java.sql.Date.valueOf(startDate));
        subscription.setEndDate(java.sql.Date.valueOf(endDate));
        subscription.setActive(true);

        try {
            subscriptionService.addSubscription(subscription);
            System.out.println("Subscribed successfully.");
        } catch (SQLException e) {
            System.err.println("Error subscribing to product: " + e.getMessage());
        }
    }

    public void viewSubscriptions(int customerId) {
        try {
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);
            for (Subscription subscription : subscriptions) {
                System.out.println(subscription);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching subscriptions: " + e.getMessage());
        }
    }

    public void cancelSubscription(int subscriptionId) {
        try {
            subscriptionService.cancelSubscription(subscriptionId);
            System.out.println("Subscription cancelled successfully.");
        } catch (SQLException e) {
            System.err.println("Error cancelling subscription: " + e.getMessage());
        }
    }
}



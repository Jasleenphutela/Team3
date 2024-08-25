package in.co.hsbc.ecommerceApp.controller;

import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerService.getCustomerById(customerId);
    }

    public void placeOrder(int subscriptionId) {
        Order order = new Order();
        order.setSubscriptionId(subscriptionId);
        order.setDeliveryDate("2024-12-31"); // example delivery date
        order.setStatus("Pending");
        customerService.placeOrder(order);
    }

    public void cancelSubscription(int subscriptionId) {
        customerService.cancelSubscription(subscriptionId);
    }
}

package in.co.hsbc.ecommerceApp.service.impl;

import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.service.CustomerService;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    // Constructor Injection for better testability and loose coupling
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }
    @Override
    public void placeOrder(Order order) {
        customerDao.placeOrder(order);
    }

    @Override
    public void cancelSubscription(int subscriptionId) {
        customerDao.cancelSubscription(subscriptionId);
    }
}

package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    void registerCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int id) throws SQLException, CustomerNotFoundException;
    Customer getCustomerByEmail(String email) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;
    void deleteCustomer(int id) throws SQLException;
}

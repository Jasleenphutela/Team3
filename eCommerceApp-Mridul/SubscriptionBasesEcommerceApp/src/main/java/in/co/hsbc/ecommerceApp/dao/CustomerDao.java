package in.co.hsbc.ecommerceApp.dao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    void addCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int id) throws SQLException, CustomerNotFoundException; // **Changed**
    Customer getCustomerByEmail(String email) throws SQLException, CustomerNotFoundException; // **Changed**
    List<Customer> getAllCustomers() throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;
    void deleteCustomer(int id) throws SQLException;
}
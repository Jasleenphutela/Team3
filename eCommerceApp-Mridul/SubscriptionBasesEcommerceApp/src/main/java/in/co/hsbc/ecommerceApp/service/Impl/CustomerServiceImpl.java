package in.co.hsbc.ecommerceApp.service.Impl;

import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import in.co.hsbc.ecommerceApp.service.CustomerService;
import in.co.hsbc.ecommerceApp.util.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDAO = BeanFactory.getCustomerDAO();

    @Override
    public void registerCustomer(Customer customer) throws SQLException {
        customerDAO.addCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        try {
            return customerDAO.getCustomerById(id);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return new Customer();
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) throws SQLException {
        try {
            return customerDAO.getCustomerByEmail(email);
        } catch (CustomerNotFoundException e) {
           e.printStackTrace();
           return new Customer();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        customerDAO.deleteCustomer(id);
    }
}

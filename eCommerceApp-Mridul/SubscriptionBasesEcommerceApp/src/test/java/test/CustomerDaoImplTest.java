package test;

import in.co.hsbc.ecommerceApp.exception.CustomerNotFoundException;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.dao.Impl.CustomerDaoImpl;

public class CustomerDaoImplTest {

     CustomerDao dao = new CustomerDaoImpl();

    @Test
    public void addCustomerTest() throws SQLException {
        dao.addCustomer(new Customer("John Doe", 1,"john.doe@example.com", "password123"));
    }

    @Test
    public void getCustomerByIdTest() throws SQLException, CustomerNotFoundException {
        Customer customer = dao.getCustomerById(1);
        System.out.println(customer);
    }

    @Test
    public void getCustomerByEmailTest() throws SQLException, CustomerNotFoundException {
        Customer customer = dao.getCustomerByEmail("john.doe@example.com");
        System.out.println(customer);
    }

    @Test
    public void getAllCustomersTest() throws SQLException {
        List<Customer> customers = dao.getAllCustomers();
        customers.forEach(System.out::println);
    }

    @Test
    public void updateCustomerTest() throws SQLException, CustomerNotFoundException {
        Customer customer = dao.getCustomerById(1);
        customer.setName("Jane Doe");
        dao.updateCustomer(customer);
    }

    @Test
    public void deleteCustomerTest() throws SQLException {
        dao.deleteCustomer(1);
    }
}

package test;

import in.co.hsbc.ecommerceApp.service.Impl.CustomerServiceImpl;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import in.co.hsbc.ecommerceApp.entity.Customer;

public class CustomerServiceImplTest {

    private CustomerServiceImpl service = new CustomerServiceImpl();

    @Test
    public void registerCustomerTest() throws SQLException {
        service.registerCustomer(new Customer("John Doe", 1,"john.doe@example.com", "password123"));
    }

    @Test
    public void getCustomerByIdTest() throws SQLException {
        Customer customer = service.getCustomerById(1);
        System.out.println(customer);
    }

    @Test
    public void getCustomerByEmailTest() throws SQLException {
        Customer customer = service.getCustomerByEmail("john.doe@example.com");
        System.out.println(customer);
    }

    @Test
    public void getAllCustomersTest() throws SQLException {
        List<Customer> customers = service.getAllCustomers();
        customers.forEach(System.out::println);
    }

    @Test
    public void updateCustomerTest() throws SQLException {
        Customer customer = service.getCustomerById(1);
        customer.setName("Jane Doe");
        service.updateCustomer(customer);
    }

    @Test
    public void deleteCustomerTest() throws SQLException {
        service.deleteCustomer(1);
    }
}

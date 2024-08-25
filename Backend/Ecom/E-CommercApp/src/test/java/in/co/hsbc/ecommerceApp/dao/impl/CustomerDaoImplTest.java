package in.co.hsbc.ecommerceApp.dao.impl;

import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.util.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoImplTest {

    private CustomerDao customerDao;

    @BeforeEach
    public void setUp() {
        customerDao = new CustomerDaoImpl();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Clean up tables before each test
            stmt.executeUpdate("DELETE FROM customers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("password123");

        customerDao.addCustomer(customer);

        Customer fetchedCustomer = customerDao.getCustomerById(1);
        assertNotNull(fetchedCustomer);
        assertEquals("Jane Doe", fetchedCustomer.getName());
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("Jane Doe");
        customer1.setEmail("jane.doe@example.com");
        customer1.setPassword("password123");
        customerDao.addCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setName("John Smith");
        customer2.setEmail("john.smith@example.com");
        customer2.setPassword("password456");
        customerDao.addCustomer(customer2);

        List<Customer> customers = customerDao.getAllCustomers();
        assertEquals(2, customers.size());
    }
}
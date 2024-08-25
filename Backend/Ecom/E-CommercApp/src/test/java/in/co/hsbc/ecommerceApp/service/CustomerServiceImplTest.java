package in.co.hsbc.ecommerceApp.service;


import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceImplTest {

    private CustomerService customerService;
    private CustomerDao customerDao;

    @BeforeEach
    public void setUp() {
        customerDao = mock(CustomerDao.class);
        customerService = new CustomerServiceImpl(customerDao);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("password123");

        customerService.addCustomer(customer);
        verify(customerDao).addCustomer(customer);
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("password123");

        when(customerDao.getCustomerById(1)).thenReturn(customer);

        Customer fetchedCustomer = customerService.getCustomerById(1);
        assertNotNull(fetchedCustomer);
        assertEquals("Jane Doe", fetchedCustomer.getName());
    }

    @Test
    public void testPlaceOrder() {
        // Implement a similar mock test for placeOrder
    }
}
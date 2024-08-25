package in.co.hsbc.ecommerceApp.controller;


import in.co.hsbc.ecommerceApp.entity.Customer;
import in.co.hsbc.ecommerceApp.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerControllerTest {

    private CustomerController customerController;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = Mockito.mock(CustomerService.class);
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("password123");

        customerController.addCustomer(customer);
        Mockito.verify(customerService).addCustomer(customer);
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("password123");

        Mockito.when(customerService.getCustomerById(1)).thenReturn(customer);

        Customer fetchedCustomer = customerController.getCustomerById(1);
        Assertions.assertNotNull(fetchedCustomer);
        Assertions.assertEquals("Jane Doe", fetchedCustomer.getName());
    }
}
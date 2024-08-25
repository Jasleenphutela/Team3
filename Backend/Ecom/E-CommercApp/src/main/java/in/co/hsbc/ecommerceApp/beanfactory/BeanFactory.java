package in.co.hsbc.ecommerceApp.beanfactory;

import in.co.hsbc.ecommerceApp.dao.AdminDao;
import in.co.hsbc.ecommerceApp.dao.CustomerDao;
import in.co.hsbc.ecommerceApp.dao.impl.AdminDaoImpl;
import in.co.hsbc.ecommerceApp.dao.impl.CustomerDaoImpl;
import in.co.hsbc.ecommerceApp.service.AdminService;
import in.co.hsbc.ecommerceApp.service.CustomerService;
import in.co.hsbc.ecommerceApp.service.impl.AdminServiceImpl;
import in.co.hsbc.ecommerceApp.service.impl.CustomerServiceImpl;
import in.co.hsbc.ecommerceApp.controller.AdminController;
import in.co.hsbc.ecommerceApp.controller.CustomerController;

public class BeanFactory {

    private static AdminDao adminDao;
    private static CustomerDao customerDao;
    private static AdminService adminService;
    private static CustomerService customerService;
    private static AdminController adminController;
    private static CustomerController customerController;

    static {
        // Initialize DAOs
        adminDao = new AdminDaoImpl();
        customerDao = new CustomerDaoImpl();

        // Initialize Services
        adminService = new AdminServiceImpl(adminDao);
        customerService = new CustomerServiceImpl(customerDao);

        // Initialize Controllers
        adminController = new AdminController(adminService);
        customerController = new CustomerController(customerService);
    }

    public static AdminController getAdminController() {
        return adminController;
    }

    public static CustomerController getCustomerController() {
        return customerController;
    }
}

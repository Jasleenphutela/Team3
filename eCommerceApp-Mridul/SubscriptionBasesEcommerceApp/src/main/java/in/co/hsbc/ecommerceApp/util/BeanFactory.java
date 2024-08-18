package in.co.hsbc.ecommerceApp.util;

import in.co.hsbc.ecommerceApp.dao.*;
import in.co.hsbc.ecommerceApp.dao.Impl.*;
import in.co.hsbc.ecommerceApp.service.*;
import in.co.hsbc.ecommerceApp.service.Impl.*;

public class BeanFactory {

    public static CustomerDao getCustomerDAO() {
        return new CustomerDaoImpl();
    }

    public static ProductDao getProductDAO() {
        return new ProductDaoImpl();
    }

    public static SubscriptionDao getSubscriptionDAO() {
        return new SubscriptionDaoImpl();
    }

    public static OrderDao getOrderDAO() {
        return new OrderDaoImpl();
    }

    public static CustomerService getCustomerService() {
        return new CustomerServiceImpl();
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl();
    }

    public static SubscriptionService getSubscriptionService() {
        return new SubscriptionServiceImpl();
    }

    public static OrderService getOrderService() {
        return new OrderServiceImpl();
    }
}

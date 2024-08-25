package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.entity.Admin;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import java.util.List;

public interface AdminService {
    Admin getAdminById(int id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    List<Product> getAllProducts();
    List<Subscription> getAllSubscriptions();
    List<Order> getAllOrders();
    void addSubscription(Subscription subscription);
}

package in.co.hsbc.ecommerceApp.controller;

import in.co.hsbc.ecommerceApp.entity.Admin;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.service.AdminService;
import java.util.List;

public class AdminController {

    private AdminService adminService;

    public void addSubscription(Subscription subscription) {
        adminService.addSubscription(subscription);
    }

    // Constructor Injection
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public Admin getAdminById(int id) {
        return adminService.getAdminById(id);
    }

    public void addProduct(Product product) {
        adminService.addProduct(product);
    }

    public void updateProduct(Product product) {
        adminService.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        adminService.deleteProduct(productId);
    }

    public List<Product> getAllProducts() {
        return adminService.getAllProducts();
    }

    public List<Subscription> getAllSubscriptions() {
        return adminService.getAllSubscriptions();
    }

    public List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }
}

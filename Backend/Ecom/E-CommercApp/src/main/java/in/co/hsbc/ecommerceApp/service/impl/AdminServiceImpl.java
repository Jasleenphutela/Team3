package in.co.hsbc.ecommerceApp.service.impl;

import in.co.hsbc.ecommerceApp.dao.AdminDao;
import in.co.hsbc.ecommerceApp.entity.Admin;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscription;
import in.co.hsbc.ecommerceApp.entity.Order;
import in.co.hsbc.ecommerceApp.service.AdminService;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public void addSubscription(Subscription subscription) {
        adminDao.addSubscription(subscription);
    }

    private AdminDao adminDao;

    // Constructor Injection for better testability and loose coupling
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin getAdminById(int id) {
        return adminDao.getAdminById(id);
    }

    @Override
    public void addProduct(Product product) {
        adminDao.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        adminDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        adminDao.deleteProduct(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return adminDao.getAllProducts();
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return adminDao.getAllSubscriptions();
    }

    @Override
    public List<Order> getAllOrders() {
        return adminDao.getAllOrders();
    }
}

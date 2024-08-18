package in.co.hsbc.ecommerceApp.service;

import in.co.hsbc.ecommerceApp.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    void addProduct(Product product) throws SQLException;
    Product getProductById(int id) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int id) throws SQLException;
}

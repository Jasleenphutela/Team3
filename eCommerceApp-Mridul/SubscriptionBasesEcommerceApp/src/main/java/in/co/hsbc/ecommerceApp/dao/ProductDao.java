package in.co.hsbc.ecommerceApp.dao;

import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.exception.ProductNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void addProduct(Product product) throws SQLException;
    Product getProductById(int id) throws SQLException, ProductNotFoundException;
    List<Product> getAllProducts() throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int id) throws SQLException, ProductNotFoundException;
}

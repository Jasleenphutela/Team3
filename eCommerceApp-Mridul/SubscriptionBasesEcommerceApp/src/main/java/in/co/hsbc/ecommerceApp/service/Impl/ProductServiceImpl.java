package in.co.hsbc.ecommerceApp.service.Impl;

import in.co.hsbc.ecommerceApp.dao.ProductDao;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.exception.ProductNotFoundException;
import in.co.hsbc.ecommerceApp.service.ProductService;
import in.co.hsbc.ecommerceApp.util.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDAO = BeanFactory.getProductDAO();

    @Override
    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        try {
            return productDAO.getProductById(id);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
            return new Product();
        } catch (ProductNotFoundException e) {
            // Handle custom exceptions here
            e.printStackTrace();
            return new Product();
        }
    }


    @Override
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    @Override
    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        }
        catch (ProductNotFoundException e) {
            // Handle custom exceptions here
            e.printStackTrace();
        }
    }
}

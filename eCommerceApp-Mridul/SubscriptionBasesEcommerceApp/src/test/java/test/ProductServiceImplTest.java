package test;

import in.co.hsbc.ecommerceApp.service.Impl.ProductServiceImpl;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import in.co.hsbc.ecommerceApp.entity.Product;

public class ProductServiceImplTest {

    private ProductServiceImpl service = new ProductServiceImpl();

    @Test
    public void addProductTest() throws SQLException {
        service.addProduct(new Product(1, "Category1", "this is product1",100.00));
    }

    @Test
    public void getProductByIdTest() throws SQLException {
        Product product = service.getProductById(1);
        System.out.println(product);
    }

    @Test
    public void getAllProductsTest() throws SQLException {
        List<Product> products = service.getAllProducts();
        products.forEach(System.out::println);
    }

    @Test
    public void updateProductTest() throws SQLException {
        Product product = service.getProductById(1);
        product.setName("Updated Product");
        service.updateProduct(product);
    }

    @Test
    public void deleteProductTest() throws SQLException {
        service.deleteProduct(1);
    }
}

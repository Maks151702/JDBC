package DaoInterface;

import DaoClass.Product;

import java.util.List;

public interface ProductDao {
    void createProduct(Product product);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int productId);
}

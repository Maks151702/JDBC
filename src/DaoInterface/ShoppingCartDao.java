package DaoInterface;

import DaoClass.Product;


import java.util.List;

public interface ShoppingCartDao {
    void addProductToCart(int userId, String productName, double totalPrice);
    void removeProductFromCart(int userId, String productName);
    List<Product> getAllProductsInCart(int userId);
    void clearCartById(int userId);
}

package DaoClass;

import DaoInterface.ShoppingCartDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ShoppingCartDao {


    private static final String URL = "jdbc:mysql://localhost:3306/spacelab";
    private static final String USER = "mysql";
    private static final String PASSWORD = "mysql";

    private static final String ADD_PRODUCT_TO_CART = "INSERT INTO shoppingcart (UserID, ProductNames, TotalPrice) VALUES (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE TotalPrice = TotalPrice + VALUES(TotalPrice)";
    private static final String CALL_ADD_OR_UPDATE_PRODUCT = "CALL addOrUpdateProductToCart(?, ?, ?)";
    private static final String REMOVE_PRODUCT_FROM_CART = "DELETE FROM shoppingcart WHERE UserID = ? AND ProductNames LIKE ?";
    private static final String GET_ALL_PRODUCTS_IN_CART = "SELECT * FROM shoppingcart WHERE UserID = ?";
    private static final String REMOVE_ALL_PRODUCTS_FROM_CART = "DELETE FROM shoppingcart WHERE UserID = ?";

    @Override
    public void addProductToCart(int userId, String productName, double totalPrice) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_CART)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, productName);
            preparedStatement.setDouble(3, totalPrice);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOtherProduct(int userId, String productName, double totalPrice){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(CALL_ADD_OR_UPDATE_PRODUCT)) {

            callableStatement.setInt(1, userId);
            callableStatement.setString(2, productName);
            callableStatement.setDouble(3, totalPrice);
            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProductFromCart(int userId, String productName) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT_FROM_CART)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, "%" + productName + "%");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProductsInCart(int userId) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS_IN_CART)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductName(resultSet.getString("ProductNames"));
                product.setPrice(resultSet.getDouble("TotalPrice"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public void clearCartById(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ALL_PRODUCTS_FROM_CART)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

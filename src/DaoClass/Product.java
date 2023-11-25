package DaoClass;

import DaoInterface.ProductDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product implements ProductDao {
    private int ProductId;
    private String ProductName;

    private String category;
    private double price;
    private static final String URL = "jdbc:mysql://localhost:3306/spacelab";
    private static final String USER = "mysql";
    private static final String PASSWORD = "mysql";

    // SQL запросы
    private static final String CREATE_PRODUCT = "INSERT INTO products (ProductName, Category, Price) VALUES (?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE ProductId = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String UPDATE_PRODUCT = "UPDATE products SET ProductName = ?, Category = ?, Price = ? WHERE ProductId = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE ProductId = ?";

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }

    public int getProductId() {
        return ProductId;
    }

    @Override
    public void createProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();

            // Получаем сгенерированный ключ (если нужен)
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setProductId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {

            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategory(resultSet.getString("Category"));
                product.setPrice(resultSet.getDouble("Price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategory(resultSet.getString("Category"));
                product.setPrice(resultSet.getDouble("Price"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

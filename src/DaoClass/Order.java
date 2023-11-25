package DaoClass;

import DaoInterface.OrderDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order implements OrderDao {
    private int orderId;
    private int userId;
    private int productId; // Строка, содержащая информацию о продуктах в заказе
    private int Quantity;
    private double totalPrice;

    private static final String URL = "jdbc:mysql://localhost:3306/spacelab";
    private static final String USER = "mysql";
    private static final String PASSWORD = "mysql";

    private static final String SAVE_ORDER = "INSERT INTO orders (UserID, ProductID, Quantity, TotalPrice) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    private static final String GET_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE UserID = ?";

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public void saveOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getProductId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setDouble(4, order.getTotalPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setOrderId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS);

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("OrderID"));
                order.setUserId(resultSet.getInt("UserID"));
                order.setProductId(resultSet.getInt("ProductID"));
                order.setQuantity(resultSet.getInt("Quantity"));
                order.setTotalPrice(resultSet.getDouble("TotalPrice"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER_ID)) {

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("OrderID"));
                order.setUserId(resultSet.getInt("UserID"));
                order.setProductId(resultSet.getInt("ProductID"));
                order.setQuantity(resultSet.getInt("Quantity"));
                order.setTotalPrice(resultSet.getDouble("TotalPrice"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }
}

package DaoClass;

import DaoInterface.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User implements UserDao {
    private int id;
    private String FirstName;
    private String SecondName;

    private static final String URL = "jdbc:mysql://localhost:3306/spacelab";
    private static final String USER = "mysql";
    private static final String PASSWORD = "mysql";

    // SQL запросы
    private static final String CREATE_USER = "INSERT INTO user (firstname, secondname) VALUES (?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE userid = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM user";
    private static final String UPDATE_USER = "UPDATE user SET firstname = ?, secondname = ? WHERE userid = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE userid = ?";

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        this.SecondName = secondName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void createUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {

            preparedStatement.setString(1, user.FirstName);
            preparedStatement.setString(2, user.SecondName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  User getUserById(int userId) {
        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setSecondName(resultSet.getString("secondname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setSecondName(resultSet.getString("secondname"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

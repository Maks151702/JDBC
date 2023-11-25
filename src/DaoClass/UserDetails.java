package DaoClass;

import DaoInterface.UserDetailsDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDetails implements UserDetailsDao {
    private int id;
    private int age ;
    private String phoneNumber;

    private static final String URL = "jdbc:mysql://localhost:3306/spacelab";
    private static final String USER = "mysql";
    private static final String PASSWORD = "mysql";

    // SQL запросы
    private static final String CREATE_USER_DETAILS = "INSERT INTO userdetails (userid, age, phonenumber) VALUES (?,?, ?)";
    private static final String GET_USER_DETAILS_BY_ID = "SELECT * FROM userdetails WHERE userid = ?";
    private static final String GET_ALL_USER_DETAILS = "SELECT * FROM userdetails";
    private static final String UPDATE_USER_DETAILS = "UPDATE userdetails SET age = ?, phonenumber = ? WHERE userid = ?";
    private static final String DELETE_USER_DETAILS = "DELETE FROM userdetails WHERE userid = ?";


    public void setAge(int age) {
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void createUserDetails(UserDetails userDetails) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_DETAILS, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, userDetails.getId());
            preparedStatement.setInt(2, userDetails.getAge());
            preparedStatement.setString(3, userDetails.getPhoneNumber());
            preparedStatement.executeUpdate();

            // Получаем сгенерированный ключ (если нужен)
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userDetails.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails getUserDetailsById(int userDetailsId) {
        UserDetails userDetails = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_DETAILS_BY_ID)) {

            preparedStatement.setInt(1, userDetailsId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userDetails = new UserDetails();
                userDetails.setId(resultSet.getInt("userid"));
                userDetails.setAge(resultSet.getInt("age"));
                userDetails.setPhoneNumber(resultSet.getString("phonenumber"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDetails;
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        List<UserDetails> userDetailsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_USER_DETAILS);

            while (resultSet.next()) {
                UserDetails userDetails = new UserDetails();
                userDetails.setId(resultSet.getInt("userid"));
                userDetails.setAge(resultSet.getInt("age"));
                userDetails.setPhoneNumber(resultSet.getString("phonenumber"));
                userDetailsList.add(userDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDetailsList;
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_DETAILS)) {

            preparedStatement.setInt(1, userDetails.getAge());
            preparedStatement.setString(2, userDetails.getPhoneNumber());
            preparedStatement.setInt(3, userDetails.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserDetails(int userDetailsId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_DETAILS)) {

            preparedStatement.setInt(1, userDetailsId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

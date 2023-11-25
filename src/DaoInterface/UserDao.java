package DaoInterface;

import DaoClass.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);

}

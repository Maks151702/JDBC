package DaoInterface;

import DaoClass.UserDetails;

import java.util.List;

public interface UserDetailsDao {
    void createUserDetails(UserDetails userDetails);
    UserDetails getUserDetailsById(int userDetailsId);
    List<UserDetails> getAllUserDetails();
    void updateUserDetails(UserDetails userDetails);
    void deleteUserDetails(int userDetailsId);
}

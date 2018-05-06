package az.hackathon.dao;

import az.hackathon.model.User;

import java.util.List;

public interface UserDao {

    User getUserByFin(String fin);

    int createNewUser(User user);

    List<User> getAllUser();
}

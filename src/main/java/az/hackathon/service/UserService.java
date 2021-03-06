package az.hackathon.service;

import az.hackathon.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserService {
    User getUserByFin(String fin);

    int createNewUser(User user);

    List<User> getAllUser();
}

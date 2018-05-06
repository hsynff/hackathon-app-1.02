package az.hackathon.service;

import az.hackathon.dao.UserDao;
import az.hackathon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByFin(String fin) {
        return userDao.getUserByFin(fin);
    }

    @Override
    public int createNewUser(User user) {
        return userDao.createNewUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}

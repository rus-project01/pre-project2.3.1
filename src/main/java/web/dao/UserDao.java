package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User findUserByName(User user);
    void updateUser(User user);
    boolean checkUser(User user);
    void deleteUser(Long id);
}

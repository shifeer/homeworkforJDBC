package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao t = new UserDaoHibernateImpl();
    public void createUsersTable() {
        t.createUsersTable();
    }

    public void dropUsersTable() {
        t.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        t.saveUser(name, lastName, age);
        System.out.println("User с именем — " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        t.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return t.getAllUsers();
    }

    public void cleanUsersTable() {
        t.cleanUsersTable();
    }
}

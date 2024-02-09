package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoJDBCImpl t = new UserDaoJDBCImpl();
        t.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl t = new UserDaoJDBCImpl();
        t.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl t = new UserDaoJDBCImpl();
        t.saveUser(name, lastName, age);
        System.out.println("User с именем — "+name+" добавлен в базу данных");
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl t = new UserDaoJDBCImpl();
        t.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl t = new UserDaoJDBCImpl();
        return t.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl t = new UserDaoJDBCImpl();
        t.cleanUsersTable();
    }
}

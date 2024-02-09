package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service .createUsersTable();
        service.saveUser("Vanya", "Petrov", (byte) 14);
        service.saveUser("Larisa", "QOK", (byte) 10);
        service.saveUser("Stas", "Voron", (byte) 45);
        service.saveUser("Zhenya", "Rov", (byte) 4);
        List<User> users = service.getAllUsers();
        System.out.println(users);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}

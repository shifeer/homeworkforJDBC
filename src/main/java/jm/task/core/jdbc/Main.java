package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.saveUser("Ivan", "Golov", (byte) 17);
        service.saveUser("Serega", "Yritov", (byte) 36);
        service.saveUser("Masha", "Fak", (byte) 23);
        service.saveUser("Gas", "Hastle", (byte) 67);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}

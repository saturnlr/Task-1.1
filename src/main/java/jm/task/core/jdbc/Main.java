package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan", "Ivanov", (byte) 27);
        service.saveUser("Maria", "Ivanova", (byte) 23);
        service.saveUser("Sidor", "Petrov", (byte) 32);
        service.saveUser("Petr", "Sidorov", (byte) 34);
        service.removeUserById(1);
        service.getAllUsers();
        service.cleanUsersTable();
        service.getAllUsers();
        service.dropUsersTable();
    }
}

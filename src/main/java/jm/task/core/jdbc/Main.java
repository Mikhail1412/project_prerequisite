package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        try (var conn = Util.getConnection()) {
            System.out.println("DB connected: " + (conn != null && !conn.isClosed()));
        } catch (Exception e) {
            e.printStackTrace();
            return; // если коннекта нет — не идём дальше
        }

        UserService s = new UserServiceImpl();

        s.createUsersTable();
        s.saveUser("Имя1", "Фамилия1", (byte) 20);
        s.saveUser("Имя2", "Фамилия2", (byte) 21);
        s.saveUser("Имя3", "Фамилия3", (byte) 22);
        s.saveUser("Имя4", "Фамилия4", (byte) 23);

        for (var u : s.getAllUsers()) System.out.println(u);

        s.cleanUsersTable();
        s.dropUsersTable();
    }
}
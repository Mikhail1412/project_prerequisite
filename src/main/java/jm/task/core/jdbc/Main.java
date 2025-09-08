package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        try (var conn = Util.getConnection()) {
            System.out.println("DB connected: " + (conn != null && !conn.isClosed()));
        } catch (Exception e) {
            e.printStackTrace();
            return; // если коннекта нет — не идём дальше
        }
    }
}
//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    public UserDaoJDBCImpl() {
//
//    }
//
//    @Override
//    public void createUsersTable() {
//        final String sql = """
//        CREATE TABLE IF NOT EXISTS users (
//            id BIGINT NOT NULL AUTO_INCREMENT,
//            name VARCHAR(45) NOT NULL,
//            lastName VARCHAR(45) NOT NULL,
//            age TINYINT NOT NULL,
//            PRIMARY KEY (id)
//        )
//        """;
//        try (Connection con = Util.getConnection();
//             Statement st = con.createStatement()) {
//            st.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException("createUsersTable failed", e);
//        }
//    }
//
//    @Override
//    public void dropUsersTable() {
//        final String sql = """
//        DROP TABLE IF EXISTS users
//        """;
//        try (Connection con = Util.getConnection();
//             Statement st = con.createStatement()) {
//            st.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException("dropUsersTable failed", e);
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        final String sql = """
//        INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)
//        """;
//        try (Connection con = Util.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, name);
//            ps.setString(2, lastName);
//            ps.setByte(3, age);
//            ps.executeUpdate();
//            System.out.printf("Пользователь с именем – %s добавлен в базу данных%n", name);
//        } catch (SQLException e) {
//            throw new RuntimeException("saveUser failed", e);
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        final String sql = """
//        DELETE FROM users WHERE id = ?
//        """;
//        try (Connection con = Util.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setLong(1, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("removeUserById failed", e);
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        final String sql = """
//        SELECT id, name, lastName, age
//        FROM users
//        ORDER BY id
//        """;
//        List<User> users = new ArrayList<>();
//        try (Connection con = Util.getConnection();
//             Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//            while (rs.next()) {
//                User u = new User();
//                u.setId(rs.getLong("id"));
//                u.setName(rs.getString("name"));
//                u.setLastName(rs.getString("lastName"));
//                u.setAge(rs.getByte("age"));
//                users.add(u);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("getAllUsers failed", e);
//        }
//        return users;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//        final String sql = """
//        TRUNCATE TABLE users
//        """;
//        try (Connection con = Util.getConnection();
//             Statement st = con.createStatement()) {
//            st.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException("cleanUsersTable failed", e);
//        }
//    }
//}
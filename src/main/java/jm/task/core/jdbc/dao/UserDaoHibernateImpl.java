package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS users(
                id BIGINT NOT NULL AUTO_INCREMENT,
                name VARCHAR(45) NOT NULL,
                lastName VARCHAR(45) NOT NULL,
                age TINYINT NOT NULL,
                PRIMARY KEY (id)
            )
            """;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            s.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("createUsersTable failed", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            s.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("dropUsersTable failed", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            s.persist(new User(name, lastName, age));
            tx.commit();
            System.out.printf("Пользователь с именем – %s добавлен в базу данных%n", name);
        } catch (Exception e) {
            throw new RuntimeException("saveUser failed", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            User u = s.get(User.class, id);
            if (u != null) s.remove(u);
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("removeUserById failed", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from User", User.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("getAllUsers failed", e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            s.createNativeQuery("TRUNCATE TABLE users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("cleanUsersTable failed", e);
        }
    }
}

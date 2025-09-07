package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Properties p = new Properties();
            p.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            p.put(Environment.URL,
                    "jdbc:mysql://localhost:3306/task_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
            p.put(Environment.USER, "appuser");
            p.put(Environment.PASS, "MyStrongPass123!");
            p.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            p.put(Environment.SHOW_SQL, "true");
            p.put(Environment.HBM2DDL_AUTO, "none");

            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder().applySettings(p).build();

            SESSION_FACTORY = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}

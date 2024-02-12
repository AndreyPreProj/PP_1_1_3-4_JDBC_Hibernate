package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory factory = util.getSessionFactory(); Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory factory = util.getSessionFactory(); Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS kata.users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory factory = util.getSessionFactory(); Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory factory = util.getSessionFactory(); Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (SessionFactory factory = util.getSessionFactory(); Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("from User")
                    .getResultList();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory factory = util.getSessionFactory(); Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM kata.users").executeUpdate();
            session.getTransaction().commit();
        }
    }
}

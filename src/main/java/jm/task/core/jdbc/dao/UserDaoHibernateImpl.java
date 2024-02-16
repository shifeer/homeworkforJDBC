package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UtilForHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = UtilForHibernate.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = UtilForHibernate.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table if exists usertable").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = UtilForHibernate.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder().name(name).lastName(lastName).age(age).build();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = UtilForHibernate.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder().id(id).build();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory sessionFactory = UtilForHibernate.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = UtilForHibernate.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}

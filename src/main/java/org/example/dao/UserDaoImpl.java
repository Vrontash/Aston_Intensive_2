package org.example.dao;

import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> findById(int id) {
        Transaction tran = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tran = session.beginTransaction();
            User user = session.get(User.class, id);
            tran.commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            if (tran != null)
                tran.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        Transaction tran = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tran = session.beginTransaction();
            List<User> users = session.createSelectionQuery("FROM User", User.class).getResultList();
            tran.commit();
            return users;
        } catch (Exception e) {
            if (tran != null)
                tran.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        Transaction tran = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tran = session.beginTransaction();
            session.persist(user);
            tran.commit();
        } catch (Exception e) {
            if (tran != null)
                tran.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        Transaction tran = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tran = session.beginTransaction();
            session.merge(user);
            tran.commit();
        } catch (Exception e) {
            if (tran != null)
                tran.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        Transaction tran = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tran = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            tran.commit();
        } catch (Exception e) {
            if (tran != null)
                tran.rollback();
            throw new RuntimeException(e);
        }
    }
}

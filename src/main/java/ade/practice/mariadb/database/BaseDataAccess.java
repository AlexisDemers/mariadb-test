package ade.practice.mariadb.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class BaseDataAccess<T> implements AutoCloseable {
    private static  SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration conf = new Configuration();
            conf.configure();
            SessionFactory factory;
            try {
                factory = conf.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
            sessionFactory = factory;
        }

        return sessionFactory;
    }

    private Class<T> type;

    public BaseDataAccess(Class<T> type) {
        this.type = type;
    }

    public List<T> GetAll() {
        Session session = getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);

        List<T> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
    }

    public T Get(int id) {
        Session session = getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.where(builder.equal(criteria.from(type).get("id"), id));
        T data = session.createQuery(criteria).getSingleResult();

        session.close();

        return data;
    }

    public void Insert(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}

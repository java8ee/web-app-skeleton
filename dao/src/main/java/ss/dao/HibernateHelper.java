package ss.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class HibernateHelper<T> {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    private Session session;

    public T execute() {
        try {
            session = FACTORY.getCurrentSession();
            session.beginTransaction();
            T result = operation();
            session.getTransaction().commit();

            return result;
        } catch (RuntimeException cause) {
            session.getTransaction().rollback();
            throw cause;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Session getSession() {
        return session;
    }

    abstract T operation();
}
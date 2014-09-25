package ss.dao;

import java.util.List;

public abstract class AbstractDAO<T> implements CRUD<T> {
    private final Class<T> type;

    public AbstractDAO(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(final T entity) {
        new HibernateHelper<Void>() {
            @Override
            Void operation() {
                getSession().save(entity);
                return null;
            }
        }.execute();
    }

    @Override
    public void update(final T entity) {
        new HibernateHelper<Void>() {
            @Override
            Void operation() {
                getSession().update(entity);
                return null;
            }
        }.execute();
    }

    @Override
    public T getById(final int id) {
        return new HibernateHelper<T>() {
            @Override
            T operation() {
                return (T) getSession().get(type, id);
            }
        }.execute();
    }

    @Override
    public List<T> getAll() {
        return new HibernateHelper<List<T>>() {
            @Override
            List<T> operation() {
                return getSession().createCriteria(type).list();
            }
        }.execute();
    }

    @Override
    public void delete(final T entity) {
        new HibernateHelper<Void>() {
            @Override
            Void operation() {
                getSession().delete(entity);
                return null;
            }
        }.execute();
    }
}
package ss.dao;

import java.util.List;

public interface CRUD<T> {
    void create(T entity);
    void update(T entity);
    T getById(int id);
    List<T> getAll();
    void delete(T entity);
}
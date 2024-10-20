package homework.Repository;

import java.util.List;
import java.util.Map;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    void add(T entity);
    void update(T entity);
    void remove(int id);
}


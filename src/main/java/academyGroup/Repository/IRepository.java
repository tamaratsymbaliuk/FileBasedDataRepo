package academyGroup.Repository;

import java.util.List;
import java.util.Map;

/**
 * Generic repository interface for basic CRUD operations.
 */
public interface IRepository<T> {
    Map<String, T> getAll();
    T getById(String id);
    String add(T entity);
    void update(T entity);
    void remove(String id);
}

package academyGroup.Repository;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    void add(T entity);
    void update(T entity);
    void remove(int id);
}

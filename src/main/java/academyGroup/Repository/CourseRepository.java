package academyGroup.Repository;

import academyGroup.Entities.Course;
import java.util.Map;

/**
 * Manages CRUD operations for Academy entities using a DbSet.
 */
public class CourseRepository implements IRepository<Course> {
    @Override
    public Map<Integer,Course> getAll() {
        return null;
    }

    @Override
    public Course getById(int id) {
        return null;
    }

    @Override
    public void add(Course entity) {

    }
    @Override
    public void update(Course entity) {

    }
    @Override
    public void remove(int id) {

    }
}

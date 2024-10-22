package academyGroup.Repository;

import academyGroup.Entities.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Manages CRUD operations for Academy entities using a DbSet.
 */
public class CourseRepository implements IRepository<Course> {
    DbContext context;
    public CourseRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public Map<String,Course> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return new HashMap<String,Course>(dbSet.getCourses());
    }

    @Override
    public Course getById(String id) {
        Map<String,Course> courses = getAll();
        if (courses.containsKey(id)) {
            return courses.get(id);
        }

        return null;
    }

    @Override
    public String add(Course course) {
        DbSet dbSet = context.getDatabaseFromFile();

        // add to Course table
        String id = UUID.randomUUID().toString();
        course.setId(id); // generate new UUID random string and set it for Id of an entity
        Map<String,Course> courses = new HashMap<String,Course>(dbSet.getCourses());
        courses.put(course.getId(), course);
        dbSet.setCourses(courses);

        // update academyId index
        Set<String> indices = dbSet.getCoursesAcademyIndex(course.getAcademyId());
        indices.add(id);
        dbSet.setCoursesAcademyIndex(course.getAcademyId(), indices);

        context.SaveChangesToFile(dbSet);

        return id;
    }

    @Override
    public void update(Course course) {
        DbSet dbSet = context.getDatabaseFromFile();

        // Update Course table
        Map<String,Course> courses = new HashMap<String,Course>(dbSet.getCourses());
        courses.put(course.getId(), course);
        dbSet.setCourses(courses);

        // update academyId index
        Set<String> indices = dbSet.getCoursesAcademyIndex(course.getAcademyId());
        indices.add(course.getId());
        dbSet.setCoursesAcademyIndex(course.getAcademyId(), indices);

        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(String id) {
        DbSet dbSet = context.getDatabaseFromFile();

        // Remove from Course table
        Map<String,Course> courses = new HashMap<String,Course>(dbSet.getCourses());
        String academyId = courses.get(id).getAcademyId();
        courses.remove(id);
        dbSet.setCourses(courses);

        // remove from index
        Set<String> indices = dbSet.getCoursesAcademyIndex(academyId);
        indices.remove(id);
        dbSet.setCoursesAcademyIndex(academyId, indices);

        context.SaveChangesToFile(dbSet);
    }

}
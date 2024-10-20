package academyGroup.Repository;


import academyGroup.Entities.Group;

import java.util.List;
import java.util.Map;

/**
 * Repository class for managing Group entities with CRUD operations.
 */
public class GroupRepository implements IRepository<Group> {
    private DbContext context;
    public GroupRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public Map<Integer, Group> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getGroups();
    }

    @Override
    public Group getById(int id) {
        return getAll().get(id);  // O(1) lookup by Map key
    }

    @Override
    public void add(Group group) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.addGroup(group);  // Add the group using the DbSet method
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void update(Group group) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.addGroup(group);  // Replace if exists
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(int id) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.getGroups().remove(id);  // Remove by key in the Map
        context.SaveChangesToFile(dbSet);
    }
    public List<Group> getGroupsByAcademyId(int academyId) {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getGroupsByAcademyId(academyId);
    }
}

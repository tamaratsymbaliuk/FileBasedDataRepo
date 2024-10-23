package academyGroup.Repository;


import academyGroup.Entities.Group;

import java.util.*;

/**
 * Repository class for managing Group entities with CRUD operations.
 */
public class GroupRepository implements IRepository<Group> {
    private DbContext context;
    public GroupRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public Map<String, Group> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return new HashMap<String, Group>(dbSet.getGroups());
    }

    @Override
    public Group getById(String id) {
        Map<String, Group> groups = getAll();
        if (groups.containsKey(id)) {
            return groups.get(id);
        }

        return null;
    }

    @Override
    public String add(Group group) {
        DbSet dbSet = context.getDatabaseFromFile();

        // add to AcademyGroup table
        String id = UUID.randomUUID().toString();
        group.setId(id); // generate new UUID random string and set it for Id of an entity
        Map<String, Group> groups = new HashMap<String, Group>(dbSet.getGroups());
        groups.put(group.getId(), group);
        dbSet.setGroups(groups);

        // update academyId index
        Set<String> indices = dbSet.getGroupAcademyIndex(group.getAcademyId());
        indices.add(id);
        dbSet.setGroupAcademyIndex(group.getAcademyId(), indices);

        context.SaveChangesToFile(dbSet);

        return id;
    }

    @Override
    public void update(Group academyGroup) {
        DbSet dbSet = context.getDatabaseFromFile();

        // Update AcademyGroup table
        Map<String,Group> academyGroups = new HashMap<String, Group>(dbSet.getGroups());
        academyGroups.put(academyGroup.getId(), academyGroup);
        dbSet.setGroups(academyGroups);

        // update academyId index
        Set<String> indices = dbSet.getGroupAcademyIndex(academyGroup.getAcademyId());
        indices.add(academyGroup.getId());
        dbSet.setGroupAcademyIndex(academyGroup.getAcademyId(), indices);

        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(String id) {
        DbSet dbSet = context.getDatabaseFromFile();

        // Remove from AcademyGroup table
        Map<String, Group> academyGroups = new HashMap<String, Group>(dbSet.getGroups());
        String academyId = academyGroups.get(id).getAcademyId();
        academyGroups.remove(id);
        dbSet.setGroups(academyGroups);

        // remove from index
        Set<String> indices = dbSet.getGroupAcademyIndex(academyId);
        indices.remove(id);
        dbSet.setGroupAcademyIndex(academyId, indices);

        context.SaveChangesToFile(dbSet);
    }
}
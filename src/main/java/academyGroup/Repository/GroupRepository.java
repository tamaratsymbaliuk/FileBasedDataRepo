package academyGroup.Repository;

import academyGroup.Entities.Group;

import java.util.List;

public class GroupRepository implements IRepository<Group> {
    private DbContext context;
    public GroupRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public List<Group> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getGroups();
    }

    @Override
    public Group getById(int id) {
        List<Group> groups = getAll();

        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    @Override
    public void add(Group group) {
        List<Group> groups = getAll(); // application heap
        groups.add(group); // your changes affect only heap
        saveChanges(groups); // let's update our file with data from heap
    }

    private void saveChanges(List<Group> groups) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.setGroups(groups);
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void update(Group group) {
        List<Group> groups = getAll();

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == group.getId()) {
                groups.set(i, group);
                break;
            }
        }
        saveChanges(groups);
    }

    @Override
    public void remove(int id) {
        List<Group> groups = getAll();
        groups.removeIf(group -> group.getId() == id);
        saveChanges(groups);
    }
}

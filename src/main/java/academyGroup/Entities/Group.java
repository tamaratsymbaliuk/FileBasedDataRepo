package academyGroup.Entities;

import java.io.Serializable;

public class Group implements Serializable {
    private int id;
    private String name;

    public Group(int id, String groupName) {
        this.id = id;
        this.name = groupName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }





}

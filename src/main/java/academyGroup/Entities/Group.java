package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Group class represents a group or cohort of students in an academy.
 * It includes the group's ID, name, and associated academy ID.
 */
public class Group implements Serializable {
    private String id;
    private String name;
    private String academyId;

    public Group(String name, String academyId) {
        this.name = name;
        this.academyId = academyId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAcademyId() {
        return academyId;
    }
    public void setAcademyId(String academyId) {
        this.academyId = academyId;
    }
}

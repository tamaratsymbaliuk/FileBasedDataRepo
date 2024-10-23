package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Mentor class represents a mentor or instructor in an academy.
 * It includes the mentor's ID, name, and the associated academy ID.
 */
public class Mentor implements Serializable {
    private String id;
    private String name;
    private String academyId;

    public Mentor(String name, String academyId) {
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

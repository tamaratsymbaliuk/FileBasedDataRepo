package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Mentor class represents a mentor or instructor in an academy.
 * It includes the mentor's ID, name, and the associated academy ID.
 */
public class Mentor implements Serializable {
    private int id;
    private String name;
    private int academyId;

    public Mentor(int id, String name, int academyId) {
        this.id = id;
        this.name = name;
        this.academyId = academyId;
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

    public int getAcademyId() {
        return academyId;
    }

    public void setAcademyId(int academyId) {
        this.academyId = academyId;
    }
}

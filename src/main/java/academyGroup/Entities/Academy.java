package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Academy class represents an educational institution or academy.
 * It contains the academy's ID and a brief description.
 */
public class Academy implements Serializable {

    private int id;
    private String description;

    public Academy(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

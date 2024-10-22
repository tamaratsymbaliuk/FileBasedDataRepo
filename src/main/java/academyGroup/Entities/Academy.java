package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Academy class represents an educational institution or academy.
 * It contains the academy's ID and a brief description.
 */
public class Academy implements Serializable {

    private String id;
    private String description;

    public Academy(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

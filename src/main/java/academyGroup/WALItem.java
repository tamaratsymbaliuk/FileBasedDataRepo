package academyGroup;

import academyGroup.OperationType;

import java.io.Serializable;

public class WALItem<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private OperationType type; // INSERT, UPDATE, DELETE
    private String entityName;  // Entity name like "Academy", "Course"
    private T entity;           // The actual entity (Academy, Course, etc.)

    public WALItem(OperationType type, String entityName, T entity) {
        this.type = type;
        this.entityName = entityName;
        this.entity = entity;
    }

    public OperationType getType() {
        return type;
    }

    public String getEntityName() {
        return entityName;
    }

    public T getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "WALItem{type=" + type + ", entityName='" + entityName + "', entity=" + entity + "}";
    }
}


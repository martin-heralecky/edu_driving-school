package cz.martinheralecky.edu.driving_school.model;

import java.io.Serializable;

/**
 * Abstract class that represents an entity with an ID.
 */
public abstract class Entity implements Serializable {
    protected final ID id;

    protected Entity(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Entity) obj).getID().equals(getID());
    }

    @Override
    public int hashCode() {
        return getID().hashCode();
    }

    /**
     * ID of this entity type.
     */
    public static class ID extends AbstractID<ID> {
        public ID(int value) {
            super(value);
        }
    }
}

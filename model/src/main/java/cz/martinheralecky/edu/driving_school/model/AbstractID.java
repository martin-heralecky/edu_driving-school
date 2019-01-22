package cz.martinheralecky.edu.driving_school.model;

import java.io.Serializable;

/**
 * Abstract class that represents an id. The value is represented by {@code int}.
 * <p>
 * This construct is immutable.
 *
 * @param <T> Concrete id, child of this class.
 */
public abstract class AbstractID<T extends AbstractID> implements Comparable<T>, Serializable {
    protected final int value;

    public AbstractID(int value) {
        this.value = value;
    }

    /**
     * @return The value of the id.
     */
    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(T id) {
        return Integer.compare(value, id.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        return compareTo((T) obj) == 0;
    }

    @Override
    public int hashCode() {
        return value;
    }

    /**
     * @return The value of the id as an integer in base 10.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

package cz.martinheralecky.edu.driving_school.protocol;

import java.io.Serializable;

/**
 * An interface describing a command which can be executed.
 *
 * @param <T> Type of the result of the command execution.
 */
public interface Command<T> extends Serializable {
    /**
     * Executes the command and returns the result.
     */
    T execute();
}

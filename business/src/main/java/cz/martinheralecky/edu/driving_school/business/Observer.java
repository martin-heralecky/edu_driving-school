package cz.martinheralecky.edu.driving_school.business;

/**
 * Describes an object which observers another {@link Observable} object by implementing the {@link #update()}
 * method, which is called whenever the state changes.
 */
public interface Observer {
    /**
     * Reacts to the change of the observable object's state.
     */
    void update();
}

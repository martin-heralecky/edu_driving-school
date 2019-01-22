package cz.martinheralecky.edu.driving_school.business;

/**
 * Describes an object, the state of which can be observed through observers.
 */
public interface Observable {
    /**
     * Registers the observer, which will be notified about future changes of state.
     */
    void addObserver(Observer observer);

    /**
     * Unregisters the observer, which will stop being notified about changes of state.
     */
    void removeObserver(Observer observer);
}

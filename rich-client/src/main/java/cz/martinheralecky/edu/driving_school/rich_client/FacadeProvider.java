package cz.martinheralecky.edu.driving_school.rich_client;

import cz.martinheralecky.edu.driving_school.business.Facade;

/**
 * Implementations of this interface provide the {@link Facade} service.
 */
public interface FacadeProvider {
    Facade getFacade();
}

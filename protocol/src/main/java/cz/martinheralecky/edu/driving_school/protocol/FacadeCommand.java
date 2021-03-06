package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.business.Facade;
import org.osgi.framework.FrameworkUtil;

/**
 * Abstract {@link Command} that provides the {@link Facade} service.
 */
abstract class FacadeCommand<T> implements Command<T> {
    /**
     * The facade service.
     */
    private transient Facade facade;

    /**
     * Obtains the {@link Facade} service.
     */
    protected Facade getFacade() {
        if (facade == null) {
            var bundleContext = FrameworkUtil.getBundle(FacadeCommand.class).getBundleContext();
            var facadeServiceRef = bundleContext.getServiceReference(Facade.class);
            facade = bundleContext.getService(facadeServiceRef);
        }

        return facade;
    }
}

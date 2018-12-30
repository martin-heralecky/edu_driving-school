package cz.martinheralecky.edu.driving_school.integration;

import org.osgi.framework.BundleContext;

import java.lang.reflect.InvocationTargetException;

/**
 * Support class providing an instance of an abstract DAO class.
 */
class DAOManager {
    /**
     * DAO abstract class of which we provide the instance.
     */
    private final Class<? extends DAO> daoClass;

    /**
     * Default DAO implementation class..
     */
    private final Class<? extends DAO> daoImplDefault;

    /**
     * Instance of the abstract DAO class.
     */
    private DAO instance = null;

    /**
     * OSGi bundle context.
     */
    private static BundleContext bundleContext;

    /**
     * @param daoClass       DAO abstract class.
     * @param daoImplDefault Default DAO implementation class.
     */
    DAOManager(Class<? extends DAO> daoClass, Class<? extends DAO> daoImplDefault) {
        this.daoClass = daoClass;
        this.daoImplDefault = daoImplDefault;
    }

    /**
     * Sets the OSGi bundle context.
     */
    static void setBundleContext(BundleContext bundleContext) {
        DAOManager.bundleContext = bundleContext;
    }

    /**
     * Obtains an instance of the abstract DAO class, if not already available. If it is to be served as a service
     * via {@link #bundleContext}, {@link #setBundleContext(BundleContext)} must be called prior to calling this method.
     *
     * @return An instance of the abstract DAO class.
     */
    DAO getInstance() {
        if (instance == null) {
            if (bundleContext != null) {
                // try to locate a service
                var serviceReference = bundleContext.getServiceReference(daoClass);
                instance = bundleContext.getService(serviceReference);
            }

            // fallback
            if (instance == null) {
                try {
                    instance = daoImplDefault.getDeclaredConstructor().newInstance();
                } catch (NoSuchMethodException |
                    InstantiationException |
                    InvocationTargetException |
                    IllegalAccessException ex) {
                    throw new RuntimeException("Default DAO implementation class uninstantiatable.");
                }
            }
        }

        return instance;
    }
}

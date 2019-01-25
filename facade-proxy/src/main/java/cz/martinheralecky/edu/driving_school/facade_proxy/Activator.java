package cz.martinheralecky.edu.driving_school.facade_proxy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Facade-proxy OSGi bundle activator.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting facade-proxy bundle.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping facade-proxy bundle.");
    }
}

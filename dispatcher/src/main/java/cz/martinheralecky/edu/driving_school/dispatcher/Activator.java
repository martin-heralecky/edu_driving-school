package cz.martinheralecky.edu.driving_school.dispatcher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Dispatcher OSGi bundle activator.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting dispatcher bundle.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping dispatcher bundle.");
    }
}

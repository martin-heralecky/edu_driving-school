package cz.martinheralecky.edu.driving_school.integration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Integration OSGi bundle activator.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting integration bundle.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping integration bundle.");
    }
}
